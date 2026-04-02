public class DaemonExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RunnableExample());
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(10);
    }
}
