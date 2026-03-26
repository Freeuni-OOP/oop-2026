package solid.l.bad;

public class Main {

    public static void method(Rectangle rectangle) {
        rectangle.setWidth(10);
        rectangle.setHeight(12);
    }

    public static void main(String[] args) {
        Rectangle rect = new Square(10, 12);
        rect.setWidth(10);
        rect.setHeight(12);
        System.out.println(rect.getArea());
    }
}