package singleton;

public class ThreadSafeSingleton {
    /*
        without volatile this could happen (reordering):
        1. Allocate memory
        2. Assign reference to instance ❗
        3. Run constructor

        another thread might see instance != null and returns partially-constructed object :(.
     */
    private static volatile ThreadSafeSingleton instance;

    protected String value;

    private ThreadSafeSingleton(String value) {
        this.value = value;
    }

    public static ThreadSafeSingleton getInstance(String value) {
        /*
            The approach taken here is called double-checked locking (DCL). It
            exists to prevent race condition between multiple threads that may
            attempt to get singleton instance at the same time, creating separate
            instances as a result.

            It may seem that having the `result` variable here is completely
            pointless. There is, however, a very important caveat when
            implementing double-checked locking in Java, which is solved by
            introducing this local variable.
         */

        ThreadSafeSingleton result = instance; // to avoid multiple volatile reads
        if (result != null) {
            return result;
        }
        synchronized (ThreadSafeSingleton.class) {
            if (instance == null) {
                instance = new ThreadSafeSingleton(value);
            }
            return instance;
        }
    }
}
