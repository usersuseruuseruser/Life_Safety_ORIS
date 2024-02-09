package proj.servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import proj.Dao.Impl.ThreadDaoImpl;
import proj.Dao.Impl.ThreadsMessagesDaoImpl;
import proj.Dao.ThreadDao;
import proj.Dao.ThreadMessageDao;
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
    ThreadDao threadDao;
    ThreadsMessagesDao threadsMessagesDao;
    Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    public void init() {
       this.threadDao = (ThreadDao) getServletContext().getAttribute("threadDao");
       this.threadsMessagesDao = (ThreadsMessagesDao) getServletContext().getAttribute("ThreadsMessagesDao");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<Thread> threadList = threadDao.getAll();
            req.setAttribute("threads", threadList);
            req.getRequestDispatcher("/forum.ftl").forward(req, resp);
        } else {
            Thread thread = threadDao.get(Integer.parseInt(id));
            if (thread == null){
                throw new RuntimeException("Такого треда нет");
            }
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
        Part part = req.getPart("threadImage");
        //сделаю самую простую проверку и самую простую обработку. сил нет уже возиться с этим всем т.т
        if (title == null || title.trim().equals("") || !(Boolean)req.getAttribute("isLoggedIn")){
            // ты отправляешься в бразилию(на форум)
            resp.sendRedirect("/forum");
            return;
        }
        InputStream fileContent = part.getInputStream();
        Map uploadResult = cloudinary.uploader().upload(fileContent.readAllBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        Thread thread = new Thread(0,title,description,imageUrl);
        threadDao.save(thread);

        resp.sendRedirect("/forum");
    }
}
