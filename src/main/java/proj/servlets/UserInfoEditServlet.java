package proj.servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import proj.Dto.UserDto;
import proj.models.User;
import proj.service.impl.UserServiceImpl;
import proj.service.service.UserService;
import proj.utils.CloudinaryUtil;
import proj.utils.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@WebServlet(name="editUserInfo",urlPatterns = "/edit")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 5 * 1024 * 1024 * 10
)
public class UserInfoEditServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    Cloudinary cloudinary = CloudinaryUtil.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String userName = (String) session.getAttribute("username");
            UserDto userDTO = userService.get(userName);
            req.setAttribute("user", userDTO);
            req.getRequestDispatcher("editUserAccountInfo.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String login = (String) session.getAttribute("username");
        UserDto dto = userService.get(login);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String selfInfo = req.getParameter("selfInfo");

        if (UserValidator.validateName(name) && !Objects.equals("",name)) dto.setName(name);
        if (UserValidator.validateEmail(email) && !Objects.equals("",email)) dto.setEmail(email);
        if (UserValidator.validateSelfInfo(selfInfo) && !Objects.equals("",selfInfo)) dto.setSelfInfo(selfInfo);
        if (req.getContentType() != null && req.getContentType().startsWith("multipart/form-data")) {
            Part part = req.getPart("picture");

            if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                InputStream fileContent = part.getInputStream();
                Map uploadResult = cloudinary.uploader().upload(fileContent.readAllBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("url");
                dto.setProfilePictureUrl(imageUrl);
            }
        }

        userService.updateUser(login,dto);
        resp.sendRedirect("/account");
    }
}
