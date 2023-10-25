package proj.models;

public class QuestionsAnswers {
    private int questionsAnswerId;
    private int questionId;
    private int answerId;

    public QuestionsAnswers(int questionsAnswerId, int questionId, int answerId) {
        this.questionsAnswerId = questionsAnswerId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public int getQuestionsAnswerId() {
        return questionsAnswerId;
    }

    public void setQuestionsAnswerId(int questionsAnswerId) {
        this.questionsAnswerId = questionsAnswerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
