package solid.o.good;

public class CorporateCalculator implements TaxCalculator {
    @Override
    public double calculate(double amount) {
        return amount * 0.2;
    }
}