package singleton;

public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (boo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("FOO"); // try NonThreadSafe
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("BAR"); // try NonThreadSafe
            System.out.println(singleton.value);
        }
    }
}
