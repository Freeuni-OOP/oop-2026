import dao.StudentDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import util.DatabaseConstants;

import java.sql.SQLException;

public class Client implements DatabaseConstants {

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
