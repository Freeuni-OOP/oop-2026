package order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderFunctionalTest {

    PaymentService paymentService = mock(PaymentService.class);
    OrderService orderService = new OrderService(paymentService);

    @Test
    void shouldProcessValidOrderFlow() {
        when(paymentService.pay(50)).thenReturn(true);

        boolean result = orderService.placeOrder(50);

        assertTrue(result);
    }

    @Test
    void shouldRejectInvalidOrder() {
        boolean result = orderService.placeOrder(-10);

        assertFalse(result);
        verifyNoInteractions(paymentService);
    }
}