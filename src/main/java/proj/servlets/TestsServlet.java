package proj.servlets;

import proj.Dao.*;
import proj.Dao.Impl.*;
import proj.models.Question;
import proj.models.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/test/*")
public class TestsServlet extends HttpServlet {
    AnswerDao answerDao = new AnswerDaoImpl();
    QuestionDao questionDao = new QuestionDaoImpl();
    QuestionsAnswerDao questionsAnswerDao = new QuestionsAnswersDaoImpl();
    TestsQuestionsDao testsQuestionsDao = new TestsQuestionsDaoImpl();
    TestDao testDao = new TestDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            List<Test> test = testDao.getAll();
            req.setAttribute("tests", test);

            req.getRequestDispatcher("/tests.ftl").forward(req, resp);
        } else{
            int id = Integer.parseInt(req.getParameter("id"));
            Test test = testDao.get(id);

        }
    }

}
