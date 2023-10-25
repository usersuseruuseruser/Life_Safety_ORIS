package proj.Dao.Impl;

import proj.Dao.TestDao;
import proj.models.Test;
import proj.utils.DatabaseConnectionUtil;

import java.sql.Connection;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class TestDaoImpl implements TestDao {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Test get(int id) {
        String sql = "SELECT * FROM tests WHERE test_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int testId = rs.getInt("test_id");
                String title = rs.getString("title");
                String imageUrl = rs.getString("image_url");
                return new Test(testId, title, imageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Test> getAll() {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM tests";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int testId = rs.getInt("test_id");
                String title = rs.getString("title");
                String imageUrl = rs.getString("image_url");
                tests.add(new Test(testId, title, imageUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    @Override
    public void save(Test test) {
        String sql = "INSERT INTO tests (test_id, title, image_url) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, test.getTestId());
            statement.setString(2, test.getTitle());
            statement.setString(3, test.getImageUrl());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Test entity) {
        String sql = "UPDATE tests SET title = ?, image_url = ? WHERE test_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getImageUrl());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

