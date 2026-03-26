package solid.d.good;

public class MySQLDatabase implements Database{
    @Override
    public void saveUser() {
        System.out.println("saving user into mysql db");
    }
}
