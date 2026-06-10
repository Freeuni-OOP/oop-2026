package ge.edu.freeuni.listener;

import ge.edu.freeuni.dao.UserDao;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.*;
import java.sql.SQLException;

public class AppListener implements ServletContextListener {

    private BasicDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("appName", "Lecture 19 App");

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(context.getInitParameter("jdbcUrl"));
        dataSource.setUsername(context.getInitParameter("dbUser"));
        dataSource.setPassword(context.getInitParameter("dbPassword"));
        dataSource.setInitialSize(2); // number of connections initially
        dataSource.setMaxTotal(10);

        try {
            UserDao userDao = new UserDao(dataSource);
            context.setAttribute("userDao", userDao);
        } catch (SQLException e) {
            context.setAttribute("dbInitError", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (dataSource != null) {
                dataSource.close();
            }
        } catch (SQLException e) {
            sce.getServletContext().log("Error closing DataSource", e);
        }
    }
}
