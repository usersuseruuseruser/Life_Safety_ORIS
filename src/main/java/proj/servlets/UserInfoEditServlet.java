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
        maxFileSize = 5 * 1024 * 1024 * 8,
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
            UserDto userDTO = userService.getByName(userName);
            req.setAttribute("user", userDTO);
            req.getRequestDispatcher("editUserAccountInfo.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null){
            resp.sendRedirect("/main");
            return;
        }
        String username = (String) session.getAttribute("username");
        UserDto dto = userService.getByName(username);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String selfInfo = req.getParameter("selfInfo");
        String errorMessage = "";
        if (!name.equals("") && (!UserValidator.validateName(name) || userService.getByName(name) != null)) {
            errorMessage = "не пройдена проверка по имени." +
                    " или такой пользователь уже существует или имя не в пределах от 2 до 50 символов";
        }
        else if (!email.equals("") && !UserValidator.validateEmail(email)) {
            errorMessage = "не пройдена проверка по email. попробуйте другой email";
        }
        else if (!selfInfo.equals("") && !UserValidator.validateSelfInfo(selfInfo)) {
            errorMessage = "не пройдена проверка описания своего профиля. оно должно быть меньше 500 символов.";
        }
        if (!errorMessage.equals("")) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("editUserAccountInfo.ftl").forward(req, resp);
            return;
        }

        if (!name.equals("") && UserValidator.validateName(name) && userService.getByName(name) == null) {
            dto.setName(name);
            session.setAttribute("username",name);
        }
        if (!email.equals("") && UserValidator.validateEmail(email)) {
            dto.setEmail(email);
        }
        if (!selfInfo.equals("") && UserValidator.validateSelfInfo(selfInfo)) {
            dto.setSelfInfo(selfInfo);
        }

        if (req.getContentType() != null && req.getContentType().startsWith("multipart/form-data")) {
            Part part = req.getPart("picture");

            if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                InputStream fileContent = part.getInputStream();
                Map uploadResult = cloudinary.uploader().upload(fileContent.readAllBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("url");
                dto.setProfilePictureUrl(imageUrl);
            }
        }

        userService.updateUser((String) session.getAttribute("login"),dto);
        resp.sendRedirect("/users/" + (name.equals("")? username: name));
    }
}
