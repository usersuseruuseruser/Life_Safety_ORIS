package proj.Dao.Impl;

import proj.Dao.ThreadMessageDao;
import proj.Dao.ThreadsMessagesDao;
import proj.models.ThreadMessage;
import proj.models.ThreadsMessages;
import proj.utils.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreadsMessagesDaoImpl implements ThreadsMessagesDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();
    ThreadMessageDao threadMessageDao = new ThreadMessageDaoImpl();

    @Override
    public ThreadsMessages get(int id) {
        try {
            String sql = "SELECT * FROM threads_messages WHERE threads_messages_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                return new ThreadsMessages(
                        resultset.getInt("threads_messages_id"),
                        resultset.getInt("thread_id"),
                        resultset.getInt("thread_message_id")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<ThreadsMessages> getAll() {
        List<ThreadsMessages> threadMessagesList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM threads_messages";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                threadMessagesList.add(new ThreadsMessages(
                        resultset.getInt("threads_messages_id"),
                        resultset.getInt("thread_id"),
                        resultset.getInt("thread_message_id")
                ));
            }
            return threadMessagesList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(ThreadsMessages threadsMessages) {
        try {
            String sql = "INSERT INTO threads_messages (thread_id, thread_message_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, threadsMessages.getThreadId());
            preparedStatement.setInt(2, threadsMessages.getThreadMessageId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(int id, ThreadsMessages entity) {
        try {
            String sql = "UPDATE threads_messages SET thread_id = ?, thread_message_id = ? WHERE threads_messages_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getThreadId());
            preparedStatement.setInt(2, entity.getThreadMessageId());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<ThreadsMessages> getAll(int threadId) {
        List<ThreadsMessages> threadsMessagesList = new ArrayList<>();

        String sql = "SELECT * FROM threads_messages WHERE thread_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, threadId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ThreadsMessages message = new ThreadsMessages();
                message.setThreadsMessagesId(rs.getInt("threads_messages_id"));
                message.setThreadId(rs.getInt("thread_id"));
                message.setThreadMessageId(rs.getInt("thread_message_id"));
                threadsMessagesList.add(message);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return threadsMessagesList;
    }

    @Override
    public List<ThreadMessage> getAllMessages(int threadId) {
        List<ThreadMessage> threadMessages = new ArrayList<>();
        List<ThreadsMessages> threadsMessagesList = getAll(threadId);
        for (ThreadsMessages threadsMessages: threadsMessagesList){
            threadMessages.add(threadMessageDao.get(threadsMessages.getThreadMessageId()));
        }
        return threadMessages;
    }
}
