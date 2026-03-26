package solid.o.good;

public class Main {
    public static void main(String[] args) {
        TaxCalculator basicCalculator = new BasicCalculator();
        System.out.println(basicCalculator.calculate(1000));

        TaxCalculator corporateCalculator = new CorporateCalculator();
        System.out.println(corporateCalculator.calculate(1000));
    }
}