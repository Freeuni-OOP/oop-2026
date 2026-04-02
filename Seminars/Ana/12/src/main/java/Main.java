import dao.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import util.Converter;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(1);
        StudentDTO studentDTO = Converter.toStudentDto(student);
        System.out.println("Student DTO: " + studentDTO.getFirstName() + " " + studentDTO.getLastName());
    }
}