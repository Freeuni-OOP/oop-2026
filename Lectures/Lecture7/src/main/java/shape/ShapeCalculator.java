package shape;

public class ShapeCalculator {
    public void calculateAndPrintVolume(String type, double a, double b) {
        if (type.equals("sphere")) {
            double radius = a;
            double volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
            System.out.println("Shape: Sphere, Volume: " + volume);
        } else if (type.equals("cube")) {
            double side = a;
            double volume = Math.pow(side, 3);
            System.out.println("Shape: Cube, Volume: " + volume);
        } else if (type.equals("cylinder")) {
            double radius = a;
            double height = b;
            double volume = Math.PI * Math.pow(radius, 2) * height;
            System.out.println("Shape: Cylinder, Volume: " + volume);
        } else {
            System.out.println("Unknown shape");
        }
    }

    public static void main(String[] args) {
        ShapeCalculator sc = new ShapeCalculator();
        sc.calculateAndPrintVolume("sphere", 5, 0);
        sc.calculateAndPrintVolume("cube", 3, 0);
        sc.calculateAndPrintVolume("cylinder", 2, 10);
    }
}