package proj.servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import proj.Dao.Impl.ThreadDaoImpl;
import proj.Dao.Impl.ThreadsMessagesDaoImpl;
import proj.Dao.ThreadDao;
import proj.Dao.ThreadsMessagesDao;
import proj.Dto.UserDto;
import proj.models.Thread;
import proj.models.ThreadMessage;
import proj.models.ThreadsMessages;
import proj.utils.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/forum/*")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024 * 8,
        maxRequestSize = 5 * 1024 * 1024 * 10
)
public class ForumServlet extends HttpServlet {
    ThreadDao threadDao = new ThreadDaoImpl();
    ThreadsMessagesDao threadsMessagesDao = new ThreadsMessagesDaoImpl();
    Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Thread> threadList = threadDao.getAll();
        if (id == null) {
            req.setAttribute("threads", threadList);
            req.getRequestDispatcher("/forum.ftl").forward(req, resp);
        } else {
            Thread thread = threadDao.get(Integer.parseInt(id));
            List<ThreadMessage> messages = threadsMessagesDao.getAllMessages(Integer.parseInt(id));
            req.setAttribute("headInfo",thread);
            req.setAttribute("messages",messages);
            req.getRequestDispatcher("/thread.ftl").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("threadTitle");
        String description = req.getParameter("threadDescription");

        Thread thread = new Thread(0,title,description,null);
        Part part = req.getPart("threadImage");

        InputStream fileContent = part.getInputStream();
        Map uploadResult = cloudinary.uploader().upload(fileContent.readAllBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        thread.setImage_url(imageUrl);
        threadDao.save(thread);

        resp.sendRedirect("/forum");
    }
}
