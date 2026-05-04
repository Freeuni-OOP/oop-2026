package dao;

import bean.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDAOTest {

    private StudentDAO studentDAO;

    @BeforeEach
    public void setUp() {
        studentDAO = new ListStudentDAO();
    }

    @Test
    public void testGetAllStudents() {
        studentDAO.addStudent(new Student("a", "b"));
        studentDAO.addStudent(new Student("c", "d"));

        List<Student> students = studentDAO.getAllStudents();
        assertEquals(2, students.size());
        assertEquals("c", students.get(0).getLastName());
    }
}
