import bean.Student;
import dao.ListStudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListStudentDAOTest {
    private ListStudentDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new ListStudentDAO();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("Ana", "Sep");
        dao.addStudent(student);
        assertEquals(1, dao.getAllStudents().size());
    }

    @Test
    public void testGetAllStudents() {
        dao.addStudent(new Student("Ana", "Sep"));
        dao.addStudent(new Student("Barbare", "Ugr"));
        dao.addStudent(new Student("Sandro", "Kob"));

        List<Student> students = dao.getAllStudents();

        assertEquals(3, students.size());
        assertEquals("Ana", students.get(0).getFirstName());
        assertEquals("Barbare", students.get(1).getFirstName());
        assertEquals("Sandro", students.get(2).getFirstName());
    }
}
