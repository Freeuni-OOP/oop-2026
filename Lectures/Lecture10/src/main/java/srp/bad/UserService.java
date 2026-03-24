package srp.bad;

public class UserService {
    public void registerUser(User user) {
        System.out.println("registering user: " + user);
    }

    public void sendEmail(User user) {
        System.out.println("sending email to: " + user);
    }

    public void writeLog(User user) {
        System.out.println("saving logs about: " + user);
    }

}
