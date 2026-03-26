package solid.o.good;

public class BasicCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount * 0.1;
    }
}