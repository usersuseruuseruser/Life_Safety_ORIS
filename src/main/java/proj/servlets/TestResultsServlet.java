package proj.servlets;

import proj.Dao.*;
import proj.Dao.Impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/testResults")
public class TestResultsServlet extends HttpServlet {
    AnswerDao answerDao = new AnswerDaoImpl();
    QuestionDao questionDao = new QuestionDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> answerIds = new ArrayList<>();

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if (paramName.startsWith("answerGroup_")) {
                String answerId = req.getParameter(paramName);
                answerIds.add(Integer.valueOf(answerId));
            }
        }

        int totalAnswers = answerIds.size();
        int rightAnswers = 0;
        for (Integer id:
             answerIds) {
            if (id == questionDao.get(answerDao.get(id).getQuestionId()).getAnswerId()){
                rightAnswers++;
            }
        }
        req.setAttribute("results",rightAnswers + "/" + totalAnswers);
        req.getRequestDispatcher("TestResults.ftl").forward(req,resp);
    }
}
