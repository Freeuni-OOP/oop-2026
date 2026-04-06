import java.util.concurrent.Semaphore;

public class Main {

    private static class WithdrawWorker extends Thread {

        private final Account account;

        public WithdrawWorker(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100000; i++) {
                    account.withdraw(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class DepositWorker extends Thread {
        private final Account account;

        public DepositWorker(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                account.deposit(100);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);

        Thread w = new WithdrawWorker(account);
        Thread d = new DepositWorker(account);

        w.start();
        d.start();

        w.join();
        d.join();

        System.out.println(account.getBalance());
    }
}
