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

@WebServlet(name="editUserInfo",urlPatterns = "/account/edit")
public class UserInfoEditServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect("/editUserAccountInfo.ftl");
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto dto = new UserDto();
        dto.setName(req.getParameter("name"));
        dto.setEmail(req.getParameter("email"));
        dto.setSelfInfo(req.getParameter("selfInfo"));

        HttpSession session = req.getSession(false);
        String login = (String) session.getAttribute("username");
        userService.updateUser(login,dto);
        resp.sendRedirect("/account");
    }
}
