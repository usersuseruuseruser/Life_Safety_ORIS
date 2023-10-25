package proj.Dao.Impl;

import proj.Dao.TestsQuestionsDao;
import proj.models.Question;
import proj.models.TestsQuestions;
import proj.utils.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestsQuestionsDaoImpl implements TestsQuestionsDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public TestsQuestions get(int id) {
        TestsQuestions testsQuestions = null;
        String query = "SELECT * FROM tests_questions WHERE tests_questions_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                testsQuestions = new TestsQuestions(
                        resultSet.getInt("tests_questions_id"),
                        resultSet.getInt("test_id"),
                        resultSet.getInt("question_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testsQuestions;
    }

    @Override
    public List<TestsQuestions> getAll() {
        List<TestsQuestions> testsQuestionsList = new ArrayList<>();
        String query = "SELECT * FROM tests_questions";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                TestsQuestions testsQuestions = new TestsQuestions(
                        resultSet.getInt("tests_questions_id"),
                        resultSet.getInt("test_id"),
                        resultSet.getInt("question_id")
                );
                testsQuestionsList.add(testsQuestions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testsQuestionsList;
    }

    @Override
    public void save(TestsQuestions testsQuestions) {
        String query = "INSERT INTO tests_questions(tests_questions_id, test_id, question_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, testsQuestions.getTests_questions_id());
            preparedStatement.setInt(2, testsQuestions.getTest_id());
            preparedStatement.setInt(3, testsQuestions.getQuestion_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, TestsQuestions entity) {
        String query = "UPDATE tests_questions SET test_id = ?, question_id = ? WHERE tests_questions_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, entity.getTest_id());
            preparedStatement.setInt(2, entity.getQuestion_id());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getQuestions(int testId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM test_questions WHERE question_id IN (SELECT question_id FROM tests_questions WHERE test_id = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question(
                        resultSet.getInt("question_id"),
                        resultSet.getString("text"),
                        resultSet.getInt("answer_id")
                );
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

}
