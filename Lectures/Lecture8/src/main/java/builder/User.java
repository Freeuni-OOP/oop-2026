package builder;

public class User {
    // Required
    private final String username;
    private final String email;

    // Optional
    private final String phone;
    private final String address;
    private final int age;
    private final boolean isVerified;

    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.age = builder.age;
        this.isVerified = builder.isVerified;
    }

    // Getters only (immutable)
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public int getAge() { return age; }
    public boolean isVerified() { return isVerified; }

    public static class Builder {
        // Required
        private final String username;
        private final String email;

        // Optional (defaults)
        private String phone = "";
        private String address = "";
        private int age = 0;
        private boolean isVerified = false;

        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder verified(boolean verified) {
            this.isVerified = verified;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}