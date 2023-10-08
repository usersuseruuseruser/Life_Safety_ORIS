package proj.service.impl;



import proj.Dao.Dao;
import proj.Dao.Impl.UserDao;
import proj.Dao.Impl.UserDaoImpl;
import proj.Dto.UserDto;
import proj.models.User;
import proj.service.service.UserService;
import proj.utils.PasswordUtil;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao dao = new UserDaoImpl();
    @Override
    public List<UserDto> getAll() {
        return dao.getAll().stream().map(
                u -> new UserDto(u.getName(),u.getEmail(),u.getSelfInfo())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        User user = dao.get(id);
        if (user == null){
            return null;
        }
        else {
            return new UserDto(user.getName(), user.getEmail(), user.getSelfInfo());
        }
    }

    @Override
    public void save(User user) {
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        dao.save(user);
    }

    @Override
    public UserDto get(String login) {
        User user = dao.get(login);
        if (user == null){
            return null;
        }
        else {
            return new UserDto(user.getName(), user.getEmail(), user.getSelfInfo());
        }
    }

    @Override
    public boolean exists(String login, String password) {
        User user = dao.get(login);
        if (user == null){
            return false;
        }
        else {
            return PasswordUtil.encrypt(password).equals(user.getPassword());
        }
    }
}
