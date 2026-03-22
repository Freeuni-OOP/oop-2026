package v2;

public class NumberNode implements Node {

  private double value;

  public NumberNode(double value) {
    this.value = value;
  }

  @Override
  public double evaluate() {
    return value;
  }

  @Override
  public String toString() {
    return Double.toString(value);
  }
}
