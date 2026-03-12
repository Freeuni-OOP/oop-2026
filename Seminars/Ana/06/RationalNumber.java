import java.util.Objects;

/**
 * Rep Invariant: denominator > 0 and rational number will be in its reduced form (gcd = 1)
 * Abstract Function: AF(numerator, denominator) = numerator / denominator
 * Safety from REP Exposure: checkRepresentation(), fields are private, final keyword
 */
public class RationalNumber {
    private final int numerator;
    private final int denominator;

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int g = gcd(Math.abs(numerator), denominator);
        numerator /= g;
        denominator /= g;

        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        checkRepresentation();
    }

    private void checkRepresentation() {
        assert denominator > 0 : "Denominator must be greater than 0";
        assert gcd(Math.abs(numerator), denominator) == 1 : "Numerator and denominator are not reduced: " + numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public RationalNumber subtract(RationalNumber r) {
        return add(new RationalNumber(-r.numerator, denominator));
    }

    public RationalNumber add(RationalNumber r) {
        int n = numerator * r.denominator + denominator * r.numerator;
        int d = denominator * r.denominator;
        return new RationalNumber(n, d);
    }

    public RationalNumber multiply(RationalNumber r) {
        return new RationalNumber(numerator * r.numerator, denominator * r.denominator);
    }

    public RationalNumber divide(RationalNumber r) {

        return new RationalNumber(numerator * r.denominator, denominator * r.numerator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RationalNumber))
            return false;
        RationalNumber r = (RationalNumber) o;
        return numerator == r.numerator && denominator == r.denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}