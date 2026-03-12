public class Main {

    public static void main(String[] args) {
        SingletonExample.getInstance().help(); // initialize only here
        SingletonExample.getInstance().help();
        SingletonExample.getInstance().help();
        SingletonExample.getInstance().help();
    }
}
