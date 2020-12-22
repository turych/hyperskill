package engine.api.quiz.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class QuizCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @JsonProperty("id")
    private Integer quizId;

    @JsonIgnore
    private Integer accountId;

    @CreationTimestamp
    private LocalDateTime completedAt;

    public QuizCompleted() {
    }

    public QuizCompleted(Integer quizId, Integer accountId) {
        this.quizId = quizId;
        this.accountId = accountId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
