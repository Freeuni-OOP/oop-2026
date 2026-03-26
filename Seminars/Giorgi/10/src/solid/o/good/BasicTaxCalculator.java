package solid.o.good;

public class BasicTaxCalculator extends TaxCalculator {
    public BasicTaxCalculator(int amount) {
        super(amount);
    }

    @Override
    public double calculateTax() {
        return super.amount * 0.1;
    }
}
