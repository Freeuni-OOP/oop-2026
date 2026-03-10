package interfaces;

public class Mobile extends Phone implements Electronic {
    @Override
    public void plugin() {

    }

    @Override
    public void on() {

    }

    @Override
    public void off() {
        defaultPlugin();
    }

    @Override
    public void makeCall(String number) {

    }
}