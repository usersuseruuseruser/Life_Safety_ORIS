package proj.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "logoutServlet",urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          clear(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clear(req,resp);
    }
    private void clear(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            for (Cookie cookie: cookies){
                cookie.setMaxAge(1);
            }
        }
        HttpSession session = req.getSession();
        if (session != null){
            session.invalidate();
        }
        resp.sendRedirect("login.html");
    }
}
