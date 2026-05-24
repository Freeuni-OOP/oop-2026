package ge.edu.freeuni.dto;

import ge.edu.freeuni.model.Student;
import lombok.*;

/**
 * FEATURE: DTO (response)
 * What the client receives – a controlled view of the entity.
 */
@Getter
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private int age;
    private Student.Major major;

    /** Factory method: Entity -> DTO */
    public static StudentResponse from(Student student) {
        return new StudentResponse(
            student.getId(),
            student.getName(),
            student.getEmail(),
            student.getAge(),
            student.getMajor()
        );
    }
}
