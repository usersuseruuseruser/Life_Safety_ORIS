package proj.handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handle")
public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req,resp);
    }
    private void handleException(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = (String) req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Integer code = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        req.setAttribute("message", msg);
        req.setAttribute("code",code);
        req.getRequestDispatcher("/Exception.ftl").forward(req,resp);
    }
}
