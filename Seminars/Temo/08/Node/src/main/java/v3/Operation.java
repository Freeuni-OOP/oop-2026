package v3;

public abstract class Operation implements Node {

  protected final Node left;
  protected final Node right;

  public Operation(Node left, Node right) {
    this.left = left;
    this.right = right;
  }

  abstract char getOperator();

  @Override
  public String toString() {
    return "(" + left.toString() + getOperator() + right.toString() + ")";
  }
}
