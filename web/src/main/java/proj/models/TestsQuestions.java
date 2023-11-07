package proj.models;

public class TestsQuestions {
    private int tests_questions_id;
    private int test_id;
    private int question_id;

    public TestsQuestions(int tests_questions_id, int test_id, int question_id) {
        this.tests_questions_id = tests_questions_id;
        this.test_id = test_id;
        this.question_id = question_id;
    }

    public int getTests_questions_id() {
        return tests_questions_id;
    }

    public void setTests_questions_id(int tests_questions_id) {
        this.tests_questions_id = tests_questions_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
