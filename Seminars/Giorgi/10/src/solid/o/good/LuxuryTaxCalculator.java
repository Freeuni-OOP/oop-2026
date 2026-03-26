package solid.o.good;

public class LuxuryTaxCalculator extends TaxCalculator {
    public LuxuryTaxCalculator(int amount) {
        super(amount);
    }

    @Override
    public double calculateTax() {
        return super.amount * 0.3;
    }
}
