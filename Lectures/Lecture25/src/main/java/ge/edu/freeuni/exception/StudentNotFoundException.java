package ge.edu.freeuni.exception;

/**
 * FEATURE: Custom Exception
 * Thrown when a student with a given id does not exist.
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("Student not found with id: " + id);
    }
}
