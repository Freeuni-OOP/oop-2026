package solid.d.good;

public class Main {

    public static void main(String[] args) {
        Database database = new MySQLDatabase();
        database.connect();

        Database database2 = new PostgreSQLDatabase();
        database2.connect();

        DatabaseService databaseService = new DatabaseService(database2);
        databaseService.saveUser("Ana Sepiashvili");
    }
}