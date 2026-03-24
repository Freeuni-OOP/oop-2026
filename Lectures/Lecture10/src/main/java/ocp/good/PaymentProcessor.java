package ocp.good;

public class PaymentProcessor {
    public void process(PaymentMethod method) {
        method.pay();
    }
}