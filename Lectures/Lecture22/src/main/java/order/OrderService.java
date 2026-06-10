package order;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean placeOrder(double amount) {
        if (amount <= 0) return false;

        return paymentService.pay(amount);
    }
}