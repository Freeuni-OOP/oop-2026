package solid.o.good;

public abstract class TaxCalculator {

    protected int amount;

    public TaxCalculator(int amount) {
        this.amount = amount;
    }

    public abstract double calculateTax();
}
