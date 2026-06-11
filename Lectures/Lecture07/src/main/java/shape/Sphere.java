package shape;

public class Sphere extends Figure3D {

    private final double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    protected ShapeType getShape() {
        return ShapeType.Sphere;
    }

    @Override
    protected double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}
