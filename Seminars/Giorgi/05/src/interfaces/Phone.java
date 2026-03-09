package interfaces;

public abstract class Phone extends Electronic {

    private String number;

    public Phone(String number) {
        this.number = number;
    }

    @Override
    void plugin() {
        System.out.println("phone");
    }

    abstract void makeCall(String number);

    void testCall(String number) {
        System.out.println("number: " + number);
    }

}
