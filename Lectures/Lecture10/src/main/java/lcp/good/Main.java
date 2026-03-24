package lcp.good;

public class Main {

    public static void method(Shape rectangle) {
        System.out.println(rectangle.getArea());
    }

    public static void main(String[] args) {
        Shape square = new Square(10);
        method(square);
    }
}
