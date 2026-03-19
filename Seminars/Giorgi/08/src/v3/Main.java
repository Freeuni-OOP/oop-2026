package v3;

public class Main {
    public static void main(String[] args) {
        Operator left = new PlusOperator(new ValueNode(1), new ValueNode(-1));

        System.out.println(left.evaluate());
    }
}
