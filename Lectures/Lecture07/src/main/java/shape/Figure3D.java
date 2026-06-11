package shape;

public abstract class Figure3D implements Shape {

    @Override
    public void printInfo() {
        System.out.println("Shape: " + getShape()
                + ", Volume: " + getVolume());
    }

    protected abstract ShapeType getShape();

    protected abstract double getVolume();

}
