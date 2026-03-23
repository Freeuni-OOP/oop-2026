package factory;

public class ShapeFactory {

    public static Shape createShape(ShapeType type) {

        switch (type) {
            case CIRCLE:
                return new Circle();
            case RECTANGLE:
                return new Rectangle();
            default:
                throw new InvalidShapeException();
        }
    }
}