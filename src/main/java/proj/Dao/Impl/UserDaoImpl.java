package proj.Dao.Impl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import proj.Dao.Dao;
import proj.models.User;
import proj.utils.DatabaseConnectionUtil;

public class UserDaoImpl implements UserDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public User get(String login) {
        try {
            String sql = "SELECT * FROM userz WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                return new User(
                        resultset.getString("name"),
                        resultset.getString("email"),
                        resultset.getString("login"),
                        resultset.getString("password"),
                        resultset.getString("selfInfo")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User get(int id) {
        try {
            String sql = "SELECT * FROM userz WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                return new User(
                        resultset.getString("name"),
                        resultset.getString("email"),
                        resultset.getString("login"),
                        resultset.getString("password"),
                        resultset.getString("selfInfo")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from userz";
            ResultSet resultset = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            if (resultset != null) {
                while (resultset.next()) {
                    users.add(new User(
                            resultset.getString("name"),
                            resultset.getString("lastname"),
                            resultset.getString("email"),
                            resultset.getString("login"),
                            resultset.getString("password")
                    ));
                }
            }
            return users;
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        String sql = "insert into userz (name, email, login, password,selfInfo) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getLogin());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getSelfInfo());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
