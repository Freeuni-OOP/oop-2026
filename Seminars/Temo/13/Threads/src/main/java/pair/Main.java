package pair;

import thread.MyThread;

public class Main {

    public static void main(String[] args) {
      MyThread myThread = new MyThread();
      myThread.start();

      MyPair<String, String> pair = new MyPair<>("Hello", "World");
      Runnable runnable = () -> {
        pair.setPair("Hello", "World");
        pair.setPair("Hello", "World1");
      };

      Thread thread1 = new Thread(runnable);

      Runnable runnable2 = () -> {
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
      };

      Thread thread2 = new Thread(runnable2);

      thread1.start();
      thread2.start();
    }

}
