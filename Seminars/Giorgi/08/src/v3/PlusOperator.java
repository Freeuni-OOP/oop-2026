package v3;

public class PlusOperator extends Operator {
    public PlusOperator(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected char getOperator() {
        return '+';
    }

    @Override
    protected double calculate(double left, double right) {
        return left + right;
    }
}
