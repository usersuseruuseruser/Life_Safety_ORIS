package proj.servlets;

import proj.models.User;
import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration",urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.get(login) != null) {
            //обработать исключение. создать страницу с exception
            //заглушка
            resp.sendRedirect("/registration");
            return;
        }
        User newUser = new User(name,null,login,password,null);

        userService.save(newUser);

        resp.sendRedirect("/main");
    }
}
