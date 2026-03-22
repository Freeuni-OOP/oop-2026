package v3;

public class Multiply extends Operation {
  public Multiply(Node left, Node right) {
    super(left, right);
  }

  @Override
  public double evaluate() {
    return left.evaluate() * right.evaluate();
  }

  @Override
  public char getOperator() {
    return '*';
  }
}
