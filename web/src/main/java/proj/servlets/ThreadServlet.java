package proj.servlets;

import proj.Dao.Impl.ThreadMessageDaoImpl;
import proj.Dao.Impl.ThreadsMessagesDaoImpl;
import proj.Dao.ThreadMessageDao;
import proj.Dao.ThreadsMessagesDao;
import proj.models.ThreadMessage;
import proj.models.ThreadsMessages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

//этот сервлет обрабатывает отправленный запрос на пост сообщения в тред
@WebServlet(name = "thread",urlPatterns = "/thread")
public class ThreadServlet extends HttpServlet {
    ThreadMessageDao threadMessageDao = new ThreadMessageDaoImpl();
    ThreadsMessagesDao threadsMessagesDao = new ThreadsMessagesDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getAttribute("username");
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null || httpSession.getAttribute("username") == null){
            resp.sendRedirect("/forum");
            return;
        }
        String text = req.getParameter("text");
        if (text.length() == 0 || text.length() > 500){
            throw new RuntimeException("длина сообщения должна быть в пределах от 1 до 500 символов");
        }
        int threadId = Integer.parseInt(req.getParameter("threadId"));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ThreadMessage threadMessage = new ThreadMessage(0,username,text,timestamp);
        int messageId = threadMessageDao.saveAndGetId(threadMessage);
        threadsMessagesDao.save(new ThreadsMessages(0,threadId,messageId));
        resp.sendRedirect("/forum?id=" + threadId);
    }
}
