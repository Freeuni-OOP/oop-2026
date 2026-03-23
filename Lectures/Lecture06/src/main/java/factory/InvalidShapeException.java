package factory;

public class InvalidShapeException extends RuntimeException {

    public InvalidShapeException() {
        super("Invalid or unsupported shape type");
    }

    public InvalidShapeException(String message) {
        super(message);
    }
}