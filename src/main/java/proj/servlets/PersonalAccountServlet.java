package proj.servlets;

import proj.Dto.UserDto;
import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Account",urlPatterns = "/users/*")
public class PersonalAccountServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        List<UserDto> listUserDto = new ArrayList<>();
        if (path.equals("/users")) {
            listUserDto =  userService.getAll();
        } else{
            String userName = path.split("/")[2];
            UserDto userDto = userService.getByName(userName);
            listUserDto.add(userDto);
        }
        req.setAttribute("users",listUserDto);
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sorting = req.getParameter("sorting");

        List<UserDto> listUserDto = switch (sorting) {
            case "byPhoto" -> userService.getAll().stream()
                    .filter(user -> user.getProfilePictureUrl() != null && !user.getProfilePictureUrl().isEmpty())
                    .collect(Collectors.toList());
            case "byEmail" -> userService.getAll().stream()
                    .filter(user -> user.getEmail() != null && !user.getEmail().isEmpty())
                    .collect(Collectors.toList());
            case "byName" -> userService.getAll().stream()
                    .sorted(Comparator.comparing(UserDto::getName))
                    .collect(Collectors.toList());
            default -> userService.getAll();
        };

        req.setAttribute("users", listUserDto);
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

}
