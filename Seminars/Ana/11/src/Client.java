package src;

import org.apache.commons.dbcp2.BasicDataSource;
import src.dao.StudentDAO;

import java.sql.SQLException;

import static src.util.DatabaseConstants.PASSWORD;
import static src.util.DatabaseConstants.URL;
import static src.util.DatabaseConstants.USER;

public class Client {
    public static void main(String[] args) {
        try (BasicDataSource basicDataSource = new BasicDataSource();) {
            basicDataSource.setUrl(URL);
            basicDataSource.setUsername(USER);
            basicDataSource.setPassword(PASSWORD);

            StudentDAO dao = new StudentDAO(basicDataSource);

            System.out.println(dao.getAllStudents());
            System.out.println(dao.getStudentCourses(1));
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}