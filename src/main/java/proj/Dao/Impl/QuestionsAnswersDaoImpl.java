package proj.Dao.Impl;

import proj.Dao.AnswerDao;
import proj.Dao.QuestionsAnswerDao;
import proj.models.Answer;
import proj.models.QuestionsAnswers;
import proj.utils.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsAnswersDaoImpl implements QuestionsAnswerDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();
    AnswerDao answerDao = new AnswerDaoImpl();

    @Override
    public QuestionsAnswers get(int id) {
        String sql = "SELECT * FROM questions_answers WHERE questions_answers_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new QuestionsAnswers(
                        resultSet.getInt("questions_answers_id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("answer_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QuestionsAnswers> getAll() {
        List<QuestionsAnswers> questionsAnswersList = new ArrayList<>();
        String sql = "SELECT * FROM questions_answers";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                questionsAnswersList.add(new QuestionsAnswers(
                        resultSet.getInt("questions_answers_id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("answer_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionsAnswersList;
    }

    @Override
    public void save(QuestionsAnswers questionsAnswers) {
        String sql = "INSERT INTO questions_answers (question_id, answer_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, questionsAnswers.getQuestionId());
            statement.setInt(2, questionsAnswers.getAnswerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, QuestionsAnswers entity) {
        String sql = "UPDATE questions_answers SET question_id = ?, answer_id = ? WHERE questions_answers_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getQuestionId());
            statement.setInt(2, entity.getAnswerId());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Answer> getAnswers(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT answer_id FROM questions_answers WHERE question_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(answerDao.get(resultSet.getInt("answer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
