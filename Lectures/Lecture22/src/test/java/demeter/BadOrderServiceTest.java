package demeter;

import demeter.bean.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BadOrderServiceTest {

    private BadOrderService service;

    @BeforeEach
    public void setUp() {
        service = new BadOrderService();
    }

    @Test
    public void testProcess() {
        Customer customer = mock(Customer.class);
        Address address = mock(Address.class);

        when(customer.getAddress()).thenReturn(address);
        when(address.getCity()).thenReturn("Tbilisi");

        service.process(customer);
    }
}
