package solid.o.good;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @Test
    public void testCalculateLuxuryTax() {
        taxCalculator = new LuxuryTaxCalculator(100);

        assertEquals(30, taxCalculator.calculateTax());
    }

    // adds new simple test method
    @Test
    public void testCalculateBasicTax() {
        taxCalculator = new BasicTaxCalculator(100);

        assertEquals(10, taxCalculator.calculateTax());
    }
}

