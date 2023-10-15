package proj.service.service;



import proj.Dto.UserDto;
import proj.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);
    UserDto get(String login);
    boolean exists(String login, String password);
    void saveLoginToken(String login, String loginToken);
    String getLoginByToken(String token);
    void updateUser(String login, UserDto user);
}
