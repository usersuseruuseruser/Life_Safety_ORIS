package proj.utils;

import proj.Dao.Impl.*;
import proj.Dao.UserDao;
import proj.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        sce.getServletContext().setAttribute("userDao",new UserDaoImpl());
        sce.getServletContext().setAttribute("answerDao",new AnswerDaoImpl());
        sce.getServletContext().setAttribute("questionDao",new QuestionDaoImpl());
        sce.getServletContext().setAttribute("questionsAnswersDao",new QuestionsAnswersDaoImpl());
        sce.getServletContext().setAttribute("testDao",new TestDaoImpl());
        sce.getServletContext().setAttribute("testsQuestionsDao",new TestsQuestionsDaoImpl());
        sce.getServletContext().setAttribute("threadDao",new ThreadDaoImpl());
        sce.getServletContext().setAttribute("ThreadMessageDao",new ThreadMessageDaoImpl());
        sce.getServletContext().setAttribute("ThreadsMessagesDao",new ThreadsMessagesDaoImpl());

        sce.getServletContext().setAttribute("userService",new UserServiceImpl(
                (UserDao) sce.getServletContext().getAttribute("userDao")
        ));
    }
}
