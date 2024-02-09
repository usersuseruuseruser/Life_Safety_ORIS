package proj.service.impl;



import proj.Dao.UserDao;
import proj.Dao.Impl.UserDaoImpl;
import proj.Dto.UserDto;
import proj.models.User;
import proj.service.service.UserService;
import proj.utils.PasswordUtil;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private UserDao dao;
    public UserServiceImpl(UserDao userDao){
        dao = userDao;
    }
    @Override
    public List<UserDto> getAll() {
        return dao.getAll().stream().map(
                u -> new UserDto(u.getId(),u.getName(),u.getEmail(),u.getSelfInfo(),u.getProfilePictureURl())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        User user = dao.get(id);
        if (user == null){
            return null;
        }
        else {
            return new UserDto(user.getId(),user.getName(), user.getEmail(), user.getSelfInfo(),user.getProfilePictureURl());
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
            return new UserDto(user.getId(),user.getName(), user.getEmail(), user.getSelfInfo(),user.getProfilePictureURl());
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

    @Override
    public void saveLoginToken(String login, String loginToken) {
        User user = dao.get(login);
        user.setLoginToken(loginToken);
        dao.update(login, user);
    }

    @Override
    public String getLoginByToken(String token) {
        User user = dao.getByToken(token);
        return user.getLogin();
    }

    @Override
    public void updateUser(String login,UserDto userDto) {
        User user = dao.get(login);
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSelfInfo(userDto.getSelfInfo());
        user.setEmail(userDto.getEmail());
        user.setProfilePictureURl(userDto.getProfilePictureUrl());
        dao.update(login,user);
    }

    @Override
    public UserDto getByName(String username) {
        User user = dao.getByUsername(username);
        if (user == null){
            return null;
        }
        else {
            return new UserDto(user.getId(),user.getName(), user.getEmail(), user.getSelfInfo(),user.getProfilePictureURl());
        }
    }
}
