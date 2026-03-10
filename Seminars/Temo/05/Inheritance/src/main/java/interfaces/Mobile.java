package interfaces;

public abstract class Mobile extends Phone {

  @Override
  void makeCall(String number) {
    System.out.println("Make call " + number);
  }

}
