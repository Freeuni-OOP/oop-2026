package thread;

public class MyRunnable implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println("Thread is running: " + i);
    }
  }

  public static void main(String[] args) {
    Thread thread1 = new Thread(new MyRunnable());
    Thread thread2 = new Thread(new MyRunnable());

    thread1.start();
    thread2.start();
  }

}
