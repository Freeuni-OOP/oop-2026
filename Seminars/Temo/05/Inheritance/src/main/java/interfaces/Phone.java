package interfaces;

public abstract class Phone extends Electronic {
  abstract void makeCall(String number);

  void testCall(String number) {
    System.out.println("Calling Phone.makeCall(" + number + ")");
  }
}
