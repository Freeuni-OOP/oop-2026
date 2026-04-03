import dao.HibernateStudentRepository;
import dao.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.StudentService;

public class StudentServiceTest {

  @Test
  public void testCreateStudent() {
    StudentRepository studentRepository = Mockito.mock(HibernateStudentRepository.class);
    Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(null);

    StudentService service = new StudentService(studentRepository);
    // assert that an exception is thrown when the student is not found
    Assertions.assertThrows(RuntimeException.class, () -> service.findStudentById(1L));
    Mockito.verify(studentRepository, Mockito.times(1)).findById(Mockito.anyLong());
  }
}
