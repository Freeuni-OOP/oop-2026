package v3;

public abstract class Operation implements Node {
    protected final Node left;
    protected final Node right;

    public Operation(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + symbol() + right.toString() + ")";
    }

    protected abstract char symbol();
}