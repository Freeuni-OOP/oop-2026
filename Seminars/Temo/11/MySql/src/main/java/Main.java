import dao.StudentDao;
import model.Student;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
  public static void main(String[] args) {

    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/students_db");
    dataSource.setUsername("root");
    dataSource.setPassword("root");

    StudentDao studentDao = new StudentDao(dataSource);;
    try {
      List<Student> studentList = studentDao.getStudents();
      for (Student student : studentList) {
        System.out.println(student);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
