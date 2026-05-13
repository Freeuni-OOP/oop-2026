package order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderScenarioTest {

    @Test
    void userCanPlaceOrderSuccessfully() {
        PaymentService paymentService = mock(PaymentService.class);
        when(paymentService.pay(200)).thenReturn(true);

        OrderService orderService = new OrderService(paymentService);
        OrderController controller = new OrderController(orderService);

        String result = controller.checkout(200);

        assertEquals("ORDER_CONFIRMED", result);
    }

    @Test
    void userSeesFailureWhenPaymentFails() {
        PaymentService paymentService = mock(PaymentService.class);
        when(paymentService.pay(200)).thenReturn(false);

        OrderService orderService = new OrderService(paymentService);
        OrderController controller = new OrderController(orderService);

        String result = controller.checkout(200);

        assertEquals("ORDER_FAILED", result);
    }
}