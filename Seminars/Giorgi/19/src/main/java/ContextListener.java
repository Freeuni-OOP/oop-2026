import dao.ListStudentDAO;
import dao.MySQLStudentDAO;
import dao.StudentDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener {

    private BasicDataSource dataSource;

    private static final String URL = "jdbc:mysql://localhost:3306/students_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        StudentDAO dao;
        try {
            dao = new MySQLStudentDAO(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ServletContext context = sce.getServletContext();
        context.setAttribute(Constants.DAO_KEY, dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
