package interfaces;

public class Mobile extends Phone implements Device {

    public Mobile(String s) {
        super(s);
    }

    @Override
    void makeCall(String number) {

    }

    @Override
    void plugin() {
        System.out.println("child phone");
    }

    @Override
    public void off() {

    }
}
