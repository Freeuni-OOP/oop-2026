package v3;

public abstract class Operator implements Node {

    private final Node left;

    private final Node right;

    public Operator(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return calculate(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        return "(" + left + this.getOperator() + right + ")";
    }

    protected abstract char getOperator();

    protected abstract double calculate(double left, double right);
}
