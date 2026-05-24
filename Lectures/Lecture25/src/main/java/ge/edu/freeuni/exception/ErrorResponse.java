package ge.edu.freeuni.exception;

import java.time.LocalDateTime;
import lombok.*;

/** Uniform error envelope returned to clients. */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
