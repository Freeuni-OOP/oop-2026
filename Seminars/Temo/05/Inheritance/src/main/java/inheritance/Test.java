package inheritance;

public class Test {
  public static void invoke(Parent parent) {
    parent.go();
  }

  public static void invoke(Child child) {
     child.go();
  }

  public static void main(String[] args) {
    Parent parent = new Parent();
    invoke(parent); // parent
    Child child = new Child();
    invoke(child); // child
    Parent parent1 = new Child();
    invoke(parent1); // child
    //Child child1 = new Parent(); // error
    //invoke(child1);
  }
}
