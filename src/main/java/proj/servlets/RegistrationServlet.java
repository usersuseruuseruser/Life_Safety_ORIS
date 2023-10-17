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
        if (userService.get(login) != null || userService.getByName(name) != null
        || (!UserValidator.validateName(name) || Objects.equals("",name))
        || (!UserValidator.validateLogin(login) || Objects.equals("",login))
        || (!UserValidator.validatePassword(password) || Objects.equals("",password))) {
            resp.sendRedirect("/registration");
            return;
        }
        User newUser = new User(0,name,null,login,password,null,null,null);

        userService.save(newUser);

        resp.sendRedirect("/main");
    }
}
