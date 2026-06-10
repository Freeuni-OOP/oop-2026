package order;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String checkout(double amount) {
        boolean success = orderService.placeOrder(amount);

        return success ? "ORDER_CONFIRMED" : "ORDER_FAILED";
    }
}