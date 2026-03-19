package v3;

public class DivideNode extends Operation {
    public DivideNode(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected char symbol() {
        return '/';
    }

    @Override
    public double evaluate() {
        return left.evaluate() / right.evaluate();
    }
}