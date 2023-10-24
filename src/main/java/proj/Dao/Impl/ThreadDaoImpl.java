package proj.Dao.Impl;

import proj.Dao.ThreadDao;
import proj.models.Thread;
import proj.models.User;
import proj.utils.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadDaoImpl implements ThreadDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();
    @Override
    public Thread get(int id) {
        try {
            String sql = "SELECT * FROM forum_threads WHERE forum_thread_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                return new Thread(
                        resultset.getInt("forum_thread_id"),
                        resultset.getString("title"),
                        resultset.getString("description"),
                        resultset.getString("image_url")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Thread> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from forum_threads";
            ResultSet resultset = statement.executeQuery(sql);
            List<Thread> threads = new ArrayList<>();
            if (resultset != null) {
                while (resultset.next()) {
                    threads.add(new Thread(
                            resultset.getInt("forum_thread_id"),
                            resultset.getString("title"),
                            resultset.getString("description"),
                            resultset.getString("image_url")));
                }
            }
            return threads;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(Thread thread) {
        String sql = "insert into forum_threads " +
                "(title, description, image_url)" +
                " VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, thread.getTitle());
            preparedStatement.setString(2, thread.getDescription());
            preparedStatement.setString(3, thread.getImage_url());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Thread entity) {
        try {
            String sql = "UPDATE forum_threads SET title = ?," +
                    " description = ?, image_url = ? WHERE forum_thread_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setString(3, entity.getImage_url());
            preparedStatement.setInt(4, entity.getForum_thread_id());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
