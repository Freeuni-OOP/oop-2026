package solid.d.good;

public class PostgreSQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("PostgreSQL Database connecting...");
        // connecting logic
    }

    @Override
    public void saveUser(String user) {
        System.out.println("Saving user: " + user + " to PostgreSQL database...");
        // saving logic
    }
}