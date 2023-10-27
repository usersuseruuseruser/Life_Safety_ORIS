package proj.Dao.Impl;

import proj.Dao.QuestionDao;
import proj.models.Question;
import proj.utils.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Question get(int id) {
        String sql = "SELECT * FROM test_questions WHERE question_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Question(
                        resultSet.getInt("question_id"),
                        resultSet.getString("text"),
                        resultSet.getInt("answer_id")
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM test_questions";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                questions.add(new Question(
                        resultSet.getInt("question_id"),
                        resultSet.getString("text"),
                        resultSet.getInt("answer_id")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return questions;
    }

    @Override
    public void save(Question question) {
        String sql = "INSERT INTO test_questions (text, answer_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getAnswerId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Question entity) {
        String sql = "UPDATE test_questions SET text = ?, answer_id = ? WHERE question_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getText());
            statement.setInt(2, entity.getAnswerId());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

