package solid.o.bad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadTaxCalculatorTest {

    private BadTaxCalculator badTaxCalculator;

    @BeforeEach
    public void setUp() {
        badTaxCalculator = new BadTaxCalculator();
    }

    @Test
    public void testCalculateTax() {
        assertEquals(10, badTaxCalculator.calculateTax("basic", 100));
        assertEquals(30, badTaxCalculator.calculateTax("luxury", 100));
        assertThrows(RuntimeException.class, () -> badTaxCalculator.calculateTax("unknown", 100));
    }
}
