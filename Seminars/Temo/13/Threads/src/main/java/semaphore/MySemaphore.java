package semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {

  private int permits;
  private final Lock lock = new ReentrantLock();
  private final Condition condition = lock.newCondition();

  public MySemaphore(int permits) {
    this.permits = permits;
  }

  public void semWait() {
    lock.lock();
    while (this.permits <= 0) {
      condition.awaitUninterruptibly();
    }
    this.permits -= 1;
    lock.unlock();
  }

  public void semPost() {
    lock.lock();

    this.permits += 1;
    condition.signalAll();

    lock.unlock();
  }

  public static void main(String[] args) {
    MySemaphore semaphore = new MySemaphore(2);

    Runnable task = () -> {
      System.out.println("Thread " + Thread.currentThread().getName() + " is waiting...");
      semaphore.semWait();
      System.out.println("Thread " + Thread.currentThread().getName() + " has entered the critical section.");
      try {
        Thread.sleep(2000); // Simulate work in the critical section
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      System.out.println("Thread " + Thread.currentThread().getName() + " is leaving the critical section.");
      semaphore.semPost();
    };

    for (int i = 0; i < 5; i++) {
      new Thread(task).start();
    }

  }
}
