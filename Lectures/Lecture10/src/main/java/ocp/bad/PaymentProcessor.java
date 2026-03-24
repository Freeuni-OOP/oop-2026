package ocp.bad;

public class PaymentProcessor {

    public void processPayment(String type) {
        switch (type) {
            case "CreditCard":
                System.out.println("paying with credit card...");
                break;
            case "PayPal":
                System.out.println("paying with PayPal card...");
                break;
        }
    }
}
