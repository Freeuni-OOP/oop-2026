package builder;

public class Main {
    public static void main(String[] args) {

        // Minimal user (only required fields)
        User user1 = new User.Builder("gio123", "gio@gmail.com")
                .build();

        // User with some optional fields
        User user2 = new User.Builder("temo99", "temo@gmail.com")
                .age(25)
                .phone("1111111")
                .build();

        // Fully customized user
        User user3 = new User.Builder("ana_dev", "ana@gmail.com")
                .age(28)
                .address("Tbilisi")
                .phone("2222222")
                .verified(true)
                .build();

        System.out.println(user1.getUsername());
        System.out.println(user2.getAge());
        System.out.println(user3.isVerified());
    }
}