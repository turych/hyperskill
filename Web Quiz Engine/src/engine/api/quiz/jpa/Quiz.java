package engine.api.quiz.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.api.quiz.Solve;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @NotNull
    @Size(min = 2)
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @CollectionTable(name = "quiz_answer", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "answer_index")
    private List<Integer> answer;

    @JsonIgnore
    private String author;

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, List<Integer> answer, String author) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean solve(Solve solve) {
        List<Integer> answers = solve.getAnswer();

        List<Integer> one = new ArrayList<>(answer);
        List<Integer> two = new ArrayList<>(answers);

        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }
}
