package v3;

public class MinusNode extends Operation {
    public MinusNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected char symbol() {
        return '-';
    }

    @Override
    public double evaluate() {
        return left.evaluate() - right.evaluate();
    }
}