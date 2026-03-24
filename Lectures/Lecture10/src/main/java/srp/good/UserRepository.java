package srp.good;

public class UserRepository {

    public void save(User user) {
        System.out.println("saving user: " + user + " in database...");
    }
}
