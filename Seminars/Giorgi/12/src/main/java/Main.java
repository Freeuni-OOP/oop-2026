import dao.StudentDAO;
import dto.StudentDTO;
import service.StudentService;

public class Main {

    public static void main(String[] args) throws Exception {
        StudentService service = new StudentService(new StudentDAO());

        service.addStudent(new StudentDTO(1L, "a", "b"));

        System.out.println(service.getStudentByID((long) 1));
    }
}
