package proj.Dao;

import proj.models.Question;
import proj.models.TestsQuestions;

import java.util.List;

public interface TestsQuestionsDao extends Dao<TestsQuestions> {
    List<Question> getQuestions(int testId);
}
