import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private int balance;

    private final Lock lock;

    private final Condition newDeposit;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        lock = new ReentrantLock();
        newDeposit = lock.newCondition();
    }

    public void withdraw(int amount) throws InterruptedException {
        lock.lock();

        while (balance < amount) {
            newDeposit.await();
        }

        balance -= amount;

        lock.unlock();
    }

    public void deposit(int amount) {
        lock.lock();

        balance += amount;
        newDeposit.signalAll();

        lock.unlock();
    }

    public int getBalance() {
        return balance;
    }
}
