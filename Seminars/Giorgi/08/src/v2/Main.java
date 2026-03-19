package v2;

public class Main {

    public static void main(String[] args) {
        Node left = new ValueNode(1);
        Node right = new ValueNode(2);
        System.out.println(new PlusNode(left, right));
    }
}
