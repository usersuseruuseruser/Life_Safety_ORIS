package proj.models;

public class Question {
    private int questionId;
    private String text;
    private int answerId;

    public Question(int questionId, String text, int answerId) {
        this.questionId = questionId;
        this.text = text;
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
