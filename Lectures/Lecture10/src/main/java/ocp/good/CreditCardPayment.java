package ocp.good;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("paying with credit card...");
    }
}
