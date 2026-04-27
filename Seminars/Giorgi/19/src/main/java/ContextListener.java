import dao.ListStudentDAO;
import dao.StudentDAO;
import util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentDAO dao = new ListStudentDAO();

        ServletContext context = sce.getServletContext();
        context.setAttribute(Constants.DAO_KEY, dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // close database connection
    }
}
