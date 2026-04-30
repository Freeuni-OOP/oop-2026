import dao.JdbcStudentDAO;
import dao.StudentDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String URL = "jdbc:mysql://localhost:3306/students_db_19";
        String USER = "root";
        String PASSWORD = "root";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);

        StudentDAO dao = new JdbcStudentDAO(basicDataSource);
        sce.getServletContext().setAttribute("studentDAO", dao);
        sce.getServletContext().setAttribute("dataSource", basicDataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // close db connection
        BasicDataSource basicDataSource = (BasicDataSource) sce.getServletContext().getAttribute("dataSource");
        if (basicDataSource != null) {
            try {
                basicDataSource.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
