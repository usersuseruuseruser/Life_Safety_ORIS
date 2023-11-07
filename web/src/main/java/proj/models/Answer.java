package proj.models;

public class Answer {
    private int questionId;
    private String text;
    private int answer_id;

    public Answer(int questionId, String text, int answer_id) {
        this.questionId = questionId;
        this.text = text;
        this.answer_id = answer_id;
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

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
}
