package engine.api.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Solve {

    @JsonProperty("answer")
    private List<Integer> answer;

    public Solve() {
    }

    public Solve(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
