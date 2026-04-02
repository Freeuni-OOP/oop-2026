package util;

import dto.StudentDTO;
import entity.Student;

public class Converter {

    public static StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(
                student.getIdNumber(),
                student.getFirstName(),
                student.getLastName()
        );
    }

    public static Student toStudent(StudentDTO studentDTO) {
        return new Student(
                studentDTO.getIdNumber(),
                studentDTO.getFirstName(),
                studentDTO.getLastName()
        );
    }
}
