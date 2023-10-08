package proj.Dao.Impl;

import proj.Dao.Dao;
import proj.models.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User get(int id);
    List<User> getAll();
    void save(User t);
    User get(String login);
}
