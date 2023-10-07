package proj.servlets;

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
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("registration.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // Проверка наличия пользователя с таким же логином
        if (userService.getByLogin(login) != null) {
            // Отправить обратно на форму регистрации с сообщением об ошибке
            req.setAttribute("error", "User with this login already exists!");
            req.getRequestDispatcher("login.html").forward(req, resp);
            return;
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setSelfInfo(selfInfo);

        userService.save(newUser);

        // После успешной регистрации перенаправляем пользователя куда-либо, например, на главную страницу
        resp.sendRedirect("index.html");
    }
}
