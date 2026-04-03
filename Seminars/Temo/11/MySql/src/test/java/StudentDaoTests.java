import dao.StudentDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.Student;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StudentDaoTests {

  private static StudentDao studentDao;

  @BeforeAll
  public static void init() throws SQLException {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");

    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();

    createTables(statement);
    insertData(statement);

    studentDao = new StudentDao(dataSource);
  }

  private static void createTables(Statement statement) throws SQLException {
    statement.execute("CREATE TABLE students\n" +
        "(\n" +
        "    id            INT PRIMARY KEY AUTO_INCREMENT,\n" +
        "    student_id    INT          NOT NULL,\n" +
        "    id_number      CHAR(11)     NOT NULL,\n" +
        "    first_name     VARCHAR(100) NOT NULL,\n" +
        "    last_name      VARCHAR(100) NOT NULL,\n" +
        "    registration_date DATETIME     NOT NULL DEFAULT NOW(),\n" +
        "    CONSTRAINT students_uk1 UNIQUE (student_id),\n" +
        "    CONSTRAINT students_uk2 UNIQUE (id_number)\n" +
        ");");
  }

  private static void insertData(Statement statement) throws SQLException {
    statement.execute("insert into students (student_id, id_number, first_name, last_name)\n" +
        "values (1, '11111111111', 'temur', 'arustashvili'),\n" +
        "       (2, '11111111112', 'giorgi', 'adikashvili'),\n" +
        "       (3, '11111111113', 'ana', 'sepiashvili');\n");
  }

  @Test
  public void testGetStudents() throws SQLException {
    List<Student> students = studentDao.getStudents();
    Assertions.assertEquals(3, students.size());
  }

}
