package demeter.bean;

public class Customer {

    private final Address address;

    Customer(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}