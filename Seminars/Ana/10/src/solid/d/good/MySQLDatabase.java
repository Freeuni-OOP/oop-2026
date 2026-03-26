package solid.d.good;

public class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("MySQL Database connecting...");
        // connecting logic
    }

    @Override
    public void saveUser(String user) {
        System.out.println("Saving user: " + user + " to MySQL database...");
        // saving logic
    }
}