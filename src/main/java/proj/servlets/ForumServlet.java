package proj.servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import proj.Dao.Impl.ThreadDaoImpl;
import proj.Dao.ThreadDao;
import proj.models.Thread;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/forum/*")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024 * 8,
        maxRequestSize = 5 * 1024 * 1024 * 10
)
public class ForumServlet extends HttpServlet {
    ThreadDao threadDao = new ThreadDaoImpl();
    Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        List<Thread> threadList = threadDao.getAll();
        if (path.equals("/forum")) {
            req.setAttribute("threads", threadList);
            req.getRequestDispatcher("forum.ftl").forward(req, resp);
        } else{
            String threadId = path.split("/")[2];
            Thread thread = threadDao.get(Integer.parseInt(threadId));
            //получить массив сообщений по id треда
            //отправить в страницу
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
