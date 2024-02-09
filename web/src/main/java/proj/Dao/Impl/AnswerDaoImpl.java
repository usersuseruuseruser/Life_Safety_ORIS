package proj.Dao.Impl;

import proj.Dao.AnswerDao;
import proj.models.Answer;
import proj.utils.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    public Answer get(int id) {
        String sql = "SELECT * FROM answers WHERE answer_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if (resultSet.next()){
                return new Answer(resultSet.getInt("question_id"),
                        resultSet.getString("answer_text"),
                        resultSet.getInt("answer_id"));
            }
            return null;
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Answer> getAll() {
        String sql = "SELECT * FROM answers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Answer> answers = new ArrayList<>();

            while(resultSet.next()){
                answers.add(new Answer(resultSet.getInt("question_id"),
                        resultSet.getString("answer_text"),
                        resultSet.getInt("answer_id")));
            }
            return answers;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(Answer answer) {
        String sql = "INSERT INTO answers (answer_id,question_id,answer_text) values (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,answer.getAnswer_id());
            preparedStatement.setInt(2,answer.getQuestionId());
            preparedStatement.setString(3, answer.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, Answer entity) {
        String sql = "update answers set question_id = ?, answer_text = ? where answer_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,entity.getQuestionId());
            preparedStatement.setString(2,entity.getText());
            preparedStatement.setInt(3,id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
