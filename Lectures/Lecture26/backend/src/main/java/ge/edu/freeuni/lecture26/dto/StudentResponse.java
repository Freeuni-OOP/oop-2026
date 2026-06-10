package ge.edu.freeuni.lecture26.dto;

import ge.edu.freeuni.lecture26.model.Student;
import lombok.*;

/**
 * DTO for outgoing responses.
 * Shields the API from accidental entity changes (open/closed principle).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private int age;
    private Student.Major major;

    public static StudentResponse from(Student s) {
        return new StudentResponse(s.getId(), s.getName(), s.getEmail(), s.getAge(), s.getMajor());
    }
}
