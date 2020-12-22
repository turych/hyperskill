package engine.api.quiz;

import engine.account.Account;
import engine.api.quiz.jpa.Quiz;
import engine.api.quiz.jpa.QuizCompleted;
import engine.api.quiz.jpa.QuizCompletedRepository;
import engine.api.quiz.jpa.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCompletedRepository completedRepository;

    public Page<Quiz> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return quizRepository.findAll(paging);
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public Quiz getById(Integer id) {
        return quizRepository.getById(id);
    }

    public void delete(Quiz quiz) {
        quizRepository.delete(quiz);
    }

    public Slice<QuizCompleted> getCompleted(Account account, Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return completedRepository.findByAccountId(account.getId(), paging);
    }

    public void addCompleted(QuizCompleted quizCompleted) {
        completedRepository.save(quizCompleted);
    }
}
