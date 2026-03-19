package v3;

public class NumberNode implements Node {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}