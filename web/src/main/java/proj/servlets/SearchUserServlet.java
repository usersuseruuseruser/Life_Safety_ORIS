package proj.servlets;

import com.google.gson.Gson;
import proj.Dto.UserDto;
import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() {
        this.userService = (UserService) getServletContext().getAttribute("userService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username != null && !username.trim().isEmpty()) {
            UserDto userDTO = userService.getByName(username);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            if (userDTO != null) {
                String userJson = new Gson().toJson(userDTO);
                resp.getWriter().write(userJson);
            } else {
                resp.getWriter().write("{\"error\":\"User not found\"}");
            }
        } else {
            resp.getWriter().write("{\"error\":\"Invalid request\"}");
        }
    }

}
