package proj.servlets;

import proj.Dao.*;
import proj.Dao.Impl.*;
import proj.models.Answer;
import proj.models.Question;
import proj.models.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/test/*")
public class TestsServlet extends HttpServlet {
    AnswerDao answerDao;
    TestsQuestionsDao testsQuestionsDao;
    TestDao testDao;
    @Override
    public void init() {
        this.answerDao = (AnswerDao) getServletContext().getAttribute("answerDao");
        this.testsQuestionsDao = (TestsQuestionsDao) getServletContext().getAttribute("testsQuestionsDao");
        this.testDao = (TestDao) getServletContext().getAttribute("testDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            List<Test> test = testDao.getAll();
            req.setAttribute("tests", test);

            req.getRequestDispatcher("/tests.ftl").forward(req, resp);
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            HttpSession httpSession = req.getSession(false);
            if (httpSession == null || httpSession.getAttribute("username") == null){
                resp.sendRedirect("/test");
                return;
            }
            Test test = testDao.get(id);
            if (test == null){
                throw new RuntimeException("тест не найден");
            }
            List<Question> questions = testsQuestionsDao.getQuestions(id);
            List<Integer> questionIds = questions.stream().map(Question::getQuestionId).toList();
            List<Answer> answers = answerDao.getAll().stream().filter(a -> questionIds.contains(a.getQuestionId())).toList();

            req.setAttribute("questions",questions);
            req.setAttribute("answers",answers);
            req.setAttribute("headInfo",test);
            req.getRequestDispatcher("testPage.ftl").forward(req,resp);
        }
    }

}
