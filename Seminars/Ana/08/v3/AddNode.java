package v3;

public class AddNode extends Operation {
    public AddNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }

    @Override
    protected char symbol() {
        return '+';
    }
}