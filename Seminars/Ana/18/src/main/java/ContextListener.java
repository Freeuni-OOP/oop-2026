import dao.ListStudentDAO;
import dao.StudentDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentDAO studentDAO = new ListStudentDAO();
        sce.getServletContext().setAttribute("studentDAO", studentDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // close db connection
    }
}
