package interfaces;

public interface Electronic extends Device {
    void plugin();

    default void defaultPlugin() {
        System.out.println("This is default implementation");
    }
}