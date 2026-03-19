package v3;

public class Subtract extends Operation {
  public Subtract(Node left, Node right) {
   super(left, right);
  }

  @Override
  public double evaluate() {
    return left.evaluate() - right.evaluate();
  }

  @Override
  public char getOperator() {
    return '-';
  }
}
