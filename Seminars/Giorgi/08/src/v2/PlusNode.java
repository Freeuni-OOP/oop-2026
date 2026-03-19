package v2;

public class PlusNode implements Node {

    private final Node left;

    private final Node right;

    public PlusNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }

    @Override
    public String toString() {
        return "(" + left + '+' + right + ")";
    }

}
