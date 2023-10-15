package proj.servlets;

import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberMe")) {
                    String token = cookie.getValue();
                    String userLogin = userService.getLoginByToken(token);
                    if (userLogin != null) {
                        HttpSession httpSession = req.getSession();
                        httpSession.setAttribute("username", userLogin);
                        httpSession.setAttribute("isLoggedIn",true);
                        httpSession.setMaxInactiveInterval(60 * 60);
                        resp.sendRedirect("/main");
                        return;
                    }
                }
            }
        }
        resp.sendRedirect("login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // проверяем, что по такому логину и паролю есть юзер. заодно смотрим нужно ли
        // отправить ему несколько куки чтобы потом было легче входить
        if (userService.exists(login,password)) {
            boolean rememberMe = "yes".equals(req.getParameter("rememberMe"));
            if (rememberMe) {
                String token = UUID.randomUUID().toString();
                userService.saveLoginToken(login, token);
                Cookie rememberMeCookie = new Cookie("rememberMe", token);
                rememberMeCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
                resp.addCookie(rememberMeCookie);
            }
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setAttribute("isLoggedIn",true);
            httpSession.setMaxInactiveInterval(60 * 60);
            resp.sendRedirect("/main");
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
