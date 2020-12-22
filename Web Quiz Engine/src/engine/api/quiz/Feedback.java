package engine.api.quiz;

public class Feedback {

    private final String FEEDBACK_GOOD = "Congratulations, you're right!";
    private final String FEEDBACK_BAD = "Wrong answer! Please, try again.";

    private boolean success;
    private String feedback;

    public Feedback(boolean status) {
        this.success = status;
        this.feedback = status ? FEEDBACK_GOOD : FEEDBACK_BAD;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
