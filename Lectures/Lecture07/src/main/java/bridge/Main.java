package bridge;

public class Main {
    public static void main(String[] args) {
        Parent<String> parent = new Child();

        System.out.println(parent.get());
    }
}
