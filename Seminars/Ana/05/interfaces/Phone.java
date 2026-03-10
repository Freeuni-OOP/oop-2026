package interfaces;

public abstract class Phone implements Electronic {
    public abstract void makeCall(String number);

    public void testCall(String number) {
        System.out.println("Calling " + number);
    }
}