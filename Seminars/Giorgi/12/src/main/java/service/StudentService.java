package service;

import dao.StudentDAO;
import dto.StudentDTO;
import lombok.AllArgsConstructor;

import static util.Converter.toStudent;
import static util.Converter.toStudentDTO;

@AllArgsConstructor
public class StudentService {

    private StudentDAO studentDAO;

    public StudentDTO getStudentByID(Long id) throws Exception {
        return toStudentDTO(studentDAO.getStudentByID((long) id));
    }

    public void addStudent(StudentDTO studentDTO) throws Exception {
        studentDAO.addStudent(toStudent(studentDTO));
    }
}
