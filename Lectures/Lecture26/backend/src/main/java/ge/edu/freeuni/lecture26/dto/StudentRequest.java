package ge.edu.freeuni.lecture26.dto;

import ge.edu.freeuni.lecture26.model.Student;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO for incoming POST / PUT requests.
 * Validation annotations are checked automatically via @Valid in the controller.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be 2-100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Min(value = 16, message = "Age must be at least 16")
    @Max(value = 100, message = "Age must be at most 100")
    private int age;

    @NotNull(message = "Major is required")
    private Student.Major major;
}
