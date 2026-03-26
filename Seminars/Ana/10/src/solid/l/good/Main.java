package solid.l.good;

public class Main {
    public static void main(String[] args) {
        Shape rect = new Rectangle(10, 15);
        Shape square = new Square(10);

        System.out.println(rect.getArea());
        System.out.println(square.getArea());
    }
}