package proj.servlets;

import proj.models.User;
import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;
import proj.utils.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "registration",urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("login") != null) {
            resp.sendRedirect("/main");
        } else {
            resp.sendRedirect("registration.ftl");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String errorMessage = "";

        if(userService.get(login) != null) {
            errorMessage = "Логин уже существует.";
        } else if (userService.getByName(name) != null) {
            errorMessage = "Имя пользователя уже существует.";
        } else if (!UserValidator.validateName(name) || Objects.equals("", name)) {
            errorMessage = "Имя не должно быть пустым, а также долнжо быть в пределах от 2 до 50 символов.";
        } else if (!UserValidator.validateLogin(login) || Objects.equals("", login)) {
            errorMessage = "Логин не должен быть пустым, а также должен быт ьв пределах от 3 до 30 символов.";
        } else if (!UserValidator.validatePassword(password) || Objects.equals("", password)) {
            errorMessage = "Пароль не должен быть пустым а также быть в пределах от 8 до 100 символов";
        }

        if (!errorMessage.isEmpty()) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("registration.ftl").forward(req, resp);
            return;
        }

        User newUser = new User(0,name,null,login,password,null,null,null);

        userService.save(newUser);

        resp.sendRedirect("/main");
    }
}
