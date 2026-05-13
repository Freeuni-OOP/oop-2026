package demeter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoodOrderServiceTest {

    private GoodOrderService service;

    @BeforeEach
    public void setUp() {
        service = new GoodOrderService();
    }

    @Test
    public void testProcess() {
        service.process("Tbilisi");
    }
}
