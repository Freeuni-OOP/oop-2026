package open_close.good;

public class TaxCalculator {

  // could also be calculateTax as interface instead of getCoefficient, both are valid

  public double calculateTax(long amount, Type type) {
    return amount * type.getCoefficient();
  }

}
