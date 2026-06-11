package shape;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Shape sphere = new Sphere(1);
        Shape cube = new Cube(2);
        Shape randomShape = new RandomShape();

        List<Shape> figures = new ArrayList<>();
        figures.add(sphere);
        figures.add(cube);
        figures.add(randomShape);

        for (Shape shape : figures) {
            shape.printInfo();
        }
    }
}
