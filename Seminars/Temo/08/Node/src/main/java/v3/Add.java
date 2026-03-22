package v3;

public class Add extends Operation {

  public Add(Node left, Node right) {
    super(left, right);
  }

  @Override
  public double evaluate() {
    return left.evaluate() + right.evaluate();
  }

  @Override
  public char getOperator() {
    return '+';
  }
}
