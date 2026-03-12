public class SingletonExample {

    private static SingletonExample instance; // null

    private SingletonExample() {
        System.out.println("singleton class initialized");
    }

    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }

    public void help() {
        System.out.println("helping");
    }
}
