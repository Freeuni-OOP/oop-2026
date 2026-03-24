package lcp.bad;

public class Main {

    public static void method(Rectangle rectangle) {
        rectangle.setWidth(10);
        rectangle.setHeight(12);

        System.out.println(rectangle.getArea());
    }

    public static void main(String[] args) {
        Rectangle square = new Square(10);
        method(square);
    }
}
