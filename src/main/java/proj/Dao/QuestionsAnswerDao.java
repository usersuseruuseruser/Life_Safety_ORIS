package proj.Dao;

import proj.Dao.Dao;
import proj.models.Answer;
import proj.models.QuestionsAnswers;

import java.util.List;

public interface QuestionsAnswerDao extends Dao<QuestionsAnswers> {
    List<Answer> getAnswers(int questionId);
}
