package v1;

public class Node {
    private Double value;
    private Character operator;
    private Node left;
    private Node right;

    public Node(double value) {
        this.value = value;
    }

    public Node(char operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Node(char operator, double left, double right) {
        this.operator = operator;
        this.left = new Node(left);
        this.right = new Node(right);
    }

    public double evaluate() {
        return switch (operator) {
            case '+' -> left.evaluate() + right.evaluate();
            case '-' -> left.evaluate() - right.evaluate();
            case '*' -> left.evaluate() * right.evaluate();
            case '/' -> {
                if (right.evaluate() == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield left.evaluate() / right.evaluate();
            }
            case null -> value;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String toString() {
        return switch (operator) {
            case '+' -> "(" + left.toString() + "+" + right.toString() + ")";
            case '-' -> "(" + left.toString() + "-" + right.toString() + ")";
            case '*' -> "(" + left.toString() + "*" + right.toString() + ")";
            case '/' -> {
                if (right.evaluate() == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield "(" + left.toString() + "/" + right.toString() + ")";
            }
            case null -> String.valueOf(value);
            default -> throw new IllegalArgumentException();
        };
    }
}