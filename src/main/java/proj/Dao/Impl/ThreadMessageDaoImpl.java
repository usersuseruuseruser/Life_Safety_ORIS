package proj.Dao.Impl;

import proj.Dao.ThreadMessageDao;
import proj.models.ThreadMessage;
import proj.models.User;
import proj.utils.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadMessageDaoImpl implements ThreadMessageDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public int saveAndGetId(ThreadMessage threadMessage) {
        try {
            String sql = "INSERT INTO thread_messages (username, text, data) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, threadMessage.getUsername());
            preparedStatement.setString(2, threadMessage.getText());
            preparedStatement.setTimestamp(3, threadMessage.getData());

            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating threadMessage failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ThreadMessage get(int id) {
        try {
            String sql = "SELECT * FROM thread_messages WHERE thread_message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                return new ThreadMessage(
                        resultset.getInt("thread_message_id"),
                        resultset.getString("username"),
                        resultset.getString("text"),
                        resultset.getTimestamp("data")
                        );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<ThreadMessage> getAll() {
        List<ThreadMessage> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM thread_messages";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                messages.add(new ThreadMessage(
                        resultset.getInt("thread_message_id"),
                        resultset.getString("username"),
                        resultset.getString("text"),
                        resultset.getTimestamp("data")
                ));
            }
            return messages;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(ThreadMessage threadMessage) {
        try {
            String sql = "INSERT INTO thread_messages (username, text, data) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, threadMessage.getUsername());
            preparedStatement.setString(2, threadMessage.getText());
            preparedStatement.setTimestamp(3, threadMessage.getData());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, ThreadMessage entity) {
        try {
            String sql = "UPDATE thread_messages SET username = ?, text = ?, data = ? WHERE thread_message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setTimestamp(3, entity.getData());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
