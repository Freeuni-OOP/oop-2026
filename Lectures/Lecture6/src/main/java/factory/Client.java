package factory;

public class Client {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape(ShapeType.CIRCLE);
        circle.draw();  // Output: Drawing a circle

        Shape rectangle = ShapeFactory.createShape(ShapeType.RECTANGLE);
        rectangle.draw();  // Output: Drawing a rectangle

        Shape triangle = ShapeFactory.createShape(ShapeType.TRIANGLE);
        triangle.draw();  // invalid shape
    }
}