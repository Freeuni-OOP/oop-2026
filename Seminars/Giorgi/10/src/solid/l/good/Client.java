package solid.l.good;

public class Client {

    private static void method(Shape shape) {

        System.out.println(shape.getArea());
    }

    public static void main(String[] args) {
        Shape shape = new Square(10);

        System.out.println(shape.getArea());
    }
}
