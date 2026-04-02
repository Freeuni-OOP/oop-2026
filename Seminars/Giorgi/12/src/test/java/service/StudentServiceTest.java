package service;

import dao.StudentDAO;
import entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    public void setUp() throws Exception {
        StudentDAO dao = mock(StudentDAO.class);
        when(dao.getStudentByID((long) 1)).thenReturn(new Student(1L, "a", "b"));

        service = new StudentService(dao);
    }

    @Test
    public void testGetStudentByID() throws Exception {
        assertEquals("b", service.getStudentByID(1L).getLastName());
    }

}
