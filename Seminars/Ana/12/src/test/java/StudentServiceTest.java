import dao.StudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest {
    private StudentDAO mockDao;
    private StudentService service;

    @BeforeEach
    public void setUp() {
        mockDao = mock(StudentDAO.class);
        service = new StudentService(mockDao);

        when(mockDao.getStudentById(1)).thenReturn(new entity.Student("John", "Doe"));
    }

    @Test
    public void getStudentById() {
        dto.StudentDTO studentDTO = service.getStudentById(1);
        assertEquals("John", studentDTO.getFirstName());
        verify(mockDao, times(1)).getStudentById(1);
    }
}