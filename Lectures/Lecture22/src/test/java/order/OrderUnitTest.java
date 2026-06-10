package order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUnitTest {

    @Mock
    PaymentService paymentService;

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {
        when(paymentService.pay(100)).thenReturn(true);

        boolean result = orderService.placeOrder(100);

        assertTrue(result);
        verify(paymentService).pay(100);
    }
}
