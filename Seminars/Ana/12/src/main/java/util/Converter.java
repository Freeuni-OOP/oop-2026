package util;

import dto.StudentDTO;
import entity.Student;

public class Converter {
    public static StudentDTO toStudentDto(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentDTO(student.getFirstName(), student.getLastName());
    }
}