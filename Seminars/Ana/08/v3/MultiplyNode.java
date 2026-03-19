package v3;

public class MultiplyNode extends Operation {
    public MultiplyNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected char symbol() {
        return '*';
    }

    @Override
    public double evaluate() {
        return left.evaluate() * right.evaluate();
    }
}