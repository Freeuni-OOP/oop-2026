package ocp.good;

public class PayPalPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("paying with PayPal card...");
    }
}
