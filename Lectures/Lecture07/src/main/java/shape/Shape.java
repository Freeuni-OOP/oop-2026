package shape;

public interface Shape {

    default void printInfo() {
        System.out.println("Unknown shape");
    }
}
