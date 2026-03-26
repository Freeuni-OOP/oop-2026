package solid.d.good;

public class FakeDatabase implements Database {
    @Override
    public void saveUser(String user) {
        System.out.println("fake save");
    }
}
