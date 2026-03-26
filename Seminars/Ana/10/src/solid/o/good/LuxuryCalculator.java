package solid.o.good;

public class LuxuryCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount * 0.3;
    }
}