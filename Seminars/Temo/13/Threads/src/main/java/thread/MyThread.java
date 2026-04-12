package thread;

public class MyThread extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println("Hello from MyThread! Count: " + i);
    }
  }

  public static void main(String[] args) {
    Thread thread1 = new MyThread();
    Thread thread2 = new MyThread();
    thread1.start();
    thread2.start();
  }

}
