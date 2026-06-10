package demeter.bean;

public class Address {

    private final String city;

    Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}