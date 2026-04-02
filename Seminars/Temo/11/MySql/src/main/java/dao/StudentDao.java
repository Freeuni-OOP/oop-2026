package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import org.apache.commons.dbcp2.BasicDataSource;

public class StudentDao {

  private final BasicDataSource dataSource;

  public StudentDao(BasicDataSource dataSource) {
    this.dataSource = dataSource;
  }

  private final String GET_ALL_STUDENTS_SCRIPT = "SELECT * FROM students";

  public List<Student> getStudents() throws SQLException {
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute(GET_ALL_STUDENTS_SCRIPT);
    List<Student> students = new ArrayList<>();

    ResultSet resultSet = statement.getResultSet();
    while (true) {
      if (!resultSet.next()) {
        break;
      }
      students.add(Student.from(resultSet));
    }
    return students;
  }
}
