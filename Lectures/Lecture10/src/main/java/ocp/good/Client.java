package ocp.good;

public class Client {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process(new PayPalPayment());
    }
}
