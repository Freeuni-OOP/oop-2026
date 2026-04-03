import dao.HibernateStudentRepository;
import dao.StudentRepository;
import hibernate.HibernateUtils;
import model.Student;
import org.hibernate.SessionFactory;
import service.StudentService;

public class Main {

  public static void main(String[] args) {
    StudentRepository studentRepository = new HibernateStudentRepository();
    StudentService service = new StudentService(studentRepository);

    Student student = new Student();
    service.saveStudent(student);
  }
}
