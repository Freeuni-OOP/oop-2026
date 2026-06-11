package shape;

public class Cube extends Figure3D {

    private final double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    protected ShapeType getShape() {
        return ShapeType.Cube;
    }

    @Override
    protected double getVolume() {
        return Math.pow(side, 3);
    }
}
