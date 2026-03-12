import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class RationalNumber {

    /*
        abstraction function: numerator / denominator
        rep invariant: gcd = 1, denominator > 0
        safety from rep exposure: checkRep(), final
     */

    private final int numerator;

    private final int denominator;

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Denominator can't be 0");
        }

        if (denominator < 0) { // denominator > 0
            numerator = -numerator;
            denominator = -denominator;
        }
        int g = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= g;
        denominator /= g;

        this.numerator = numerator;
        this.denominator = denominator;

        checkRepresentation();
    }

    public double evaluate() {
        return (double) numerator / denominator;
    }

    public RationalNumber add(RationalNumber other) {
        int numerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int denominator = this.denominator * other.denominator;
        return new RationalNumber(numerator, denominator);
    }

    public RationalNumber subtract(RationalNumber other) {
        int numerator = this.numerator * other.denominator - this.denominator * other.numerator;
        int denominator = this.denominator * other.denominator;
        return new RationalNumber(numerator, denominator);
    }

    public RationalNumber multiply(RationalNumber other) {
        return new RationalNumber(this.numerator * other.numerator,
                this.denominator * other.denominator);
    }

    public RationalNumber divide(RationalNumber other) {
        return multiply(new RationalNumber(other.denominator, other.numerator));
    }

    // verifies invariant, enable assert: -ea
    private void checkRepresentation() {
        assert gcd(Math.abs(numerator), Math.abs(denominator)) == 1
                && denominator > 0;
    }

    private static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RationalNumber that = (RationalNumber) o;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
