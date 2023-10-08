package proj.servlets;

import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.exists(login,password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(60 * 60);
        }
        resp.sendRedirect("Main.jsp");
    }
}
