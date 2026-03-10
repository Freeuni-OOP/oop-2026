package inheritance;

public class Test {
    public static void main(String[] args) {
        Parent parent = new Parent();
        invoke(parent);

        Child child = new Child();
        invoke(child);

        Parent parent1 = new Child();
        invoke(parent1);

        //        Child child1 = new Parent(); // wrong
    }

    public static void invoke(Parent parent) {
        parent.go();
    }

    public static void invoke(Child child) {
        child.go();
    }
}