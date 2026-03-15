// Rep Invariant:
//   denominator > 0
//   gcd(|numerator|, denominator) == 1

// Abstract Function:
//   AF(numerator, denominator) = numerator / denominator

// Safety from Rep Exposure:
//   All fields are private and of primitive type (int),
//   so they cannot be directly accessed or aliased by clients.
public class RationalNumber {
  private int numerator;
  private int denominator;

  public RationalNumber(int numerator, int denominator) {
    normalize(numerator, denominator);
    checkRepresentation();
  }

  private void checkRepresentation() {
    if (denominator == 0) {
      throw new IllegalArgumentException("denominator is zero");
    }
    int gcd = gcd(numerator, denominator);
    if (gcd != 1) {
      throw new IllegalArgumentException("numerator and denominator are not in lowest terms");
    }
    if (denominator < 0) {
      throw new IllegalArgumentException("denominator is negative");
    }
  }

  private int gcd(int a, int b) {
    if (b == 0) {
      return Math.abs(a);
    }
    return gcd(b, a % b);
  }

  private void normalize(int newNumerator, int newDenominator) {
    if (newDenominator == 0) {
      throw new IllegalArgumentException("denominator is zero");
    }
    int gcd = gcd(newNumerator, newDenominator);
    if (gcd != 0) {
      newNumerator /= gcd;
      newDenominator /= gcd;
    }

    boolean isNegative = (newDenominator < 0) ^ (newNumerator < 0);
    this.numerator = isNegative ? -Math.abs(newNumerator) : Math.abs(newNumerator);
    this.denominator = Math.abs(newDenominator);
  }

  public void add(RationalNumber rationalNumber) {
    int newDenominator = this.denominator * rationalNumber.denominator;
    int newNumerator = this.numerator * rationalNumber.denominator + rationalNumber.numerator * this.denominator;

    normalize(newNumerator, newDenominator);
    checkRepresentation();
  }

  public void subtract(RationalNumber rationalNumber) {
    int newDenominator = this.denominator * rationalNumber.denominator;
    int newNumerator = this.numerator * rationalNumber.denominator - rationalNumber.numerator * this.denominator;

    normalize(newNumerator, newDenominator);
    checkRepresentation();
  }

  public void multiply(RationalNumber rationalNumber) {
    int newDenominator = this.denominator * rationalNumber.denominator;
    int newNumerator = this.numerator * rationalNumber.numerator;

    normalize(newNumerator, newDenominator);
    checkRepresentation();
  }

  public void divide(RationalNumber rationalNumber) {
    int newDenominator = this.denominator * rationalNumber.numerator;
    int newNumerator = this.numerator * rationalNumber.denominator;

    normalize(newNumerator, newDenominator);
    checkRepresentation();
  }

  @Override
  public String toString() {
    checkRepresentation();
    return numerator + "/" + denominator;
  }
}
