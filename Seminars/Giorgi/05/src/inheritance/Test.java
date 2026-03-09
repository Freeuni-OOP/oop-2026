package inheritance;

public class Test {

    public static void invoke(Parent p) {
        p.go();
    }

    public static void invoke(Child c) {
        c.go();
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        invoke(parent);

        Child childChild = new Child();
        invoke(childChild);

        Parent parentChild = new Child();
        invoke(parentChild);

        // Child childParent = new Parent(); wrong!!!
    }
}
