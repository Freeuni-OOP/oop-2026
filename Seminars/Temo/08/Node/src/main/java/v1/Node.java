package v1;

public class Node {

  private Node left;
  private Node right;
  private char operator;
  private double value;

  public Node(char operator, Node left, Node right)  {
    if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
      throw new RuntimeException("Illegal operator: " + operator);
    }
    this.left = left;
    this.right = right;
    this.operator = operator;
  }

  public Node(double value)  {
    this.value = value;
  }

  public double evaluate() {
    return switch (operator) {
      case '+' -> left.evaluate() + right.evaluate();
      case '-' -> left.evaluate() - right.evaluate();
      case '*' -> left.evaluate() * right.evaluate();
      case '/' -> left.evaluate() / right.evaluate();
      default -> value;
    };
  }

  @Override
  public String toString() {
    return switch (operator) {
      case '+' -> "(" + left.toString() + "+" + right.toString() + ")";
      case '-' -> "(" + left.toString() + "-" + right.toString() + ")";
      case '*' -> "(" + left.toString() + "*" + right.toString() + ")";
      case '/' -> "(" + left.toString() + "/" + right.toString() + ")";
      default -> Double.toString(value);
    };
  }
}
