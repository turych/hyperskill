package engine.api.quiz;

import engine.api.quiz.jpa.Quiz;
import engine.api.quiz.jpa.QuizCompleted;
import engine.security.QuizUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/api/quizzes")
    public Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        quiz.setAuthor(getUserFromAuth().getUsername());
        quizService.save(quiz);
        return quiz;
    }

    @ResponseBody
    @GetMapping("/api/quizzes/{id}")
    public Quiz getOne(@PathVariable Integer id) {
        Quiz quiz = quizService.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        return quiz;
    }

    @ResponseBody
    @GetMapping("/api/quizzes")
    public Page<Quiz> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy
    ) {
        return quizService.getAll(page, pageSize, sortBy);
    }

    @ResponseBody
    @GetMapping("/api/quizzes/completed")
    public Slice<QuizCompleted> getAllCompleted(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "completedAt") String sortBy
    ) {
        return quizService.getCompleted(getUserFromAuth().getAccount(), page, pageSize, sortBy);
    }

    @ResponseBody
    @PostMapping("/api/quizzes/{id}/solve")
    public Feedback solve(@PathVariable Integer id, @RequestBody Solve answer) {
        Quiz quiz = quizService.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        Feedback feedback = new Feedback(quiz.solve(answer));
        if (feedback.isSuccess()) {
            quizService.addCompleted(new QuizCompleted(quiz.getId(), getUserFromAuth().getAccount().getId()));
        }
        return feedback;
    }

    @DeleteMapping("/api/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Quiz quiz = quizService.getById(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        } else if (!quiz.getAuthor().equals(getUserFromAuth().getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        quizService.delete(quiz);
    }

    private QuizUser getUserFromAuth() {
        return (QuizUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
