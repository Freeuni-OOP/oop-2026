package bridge;

public class Child extends Parent<String> {

    @Override
    String get() {
        return "Hello";
    }

}
