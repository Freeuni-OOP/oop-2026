package reflection;

public class Person {

    public String name;
    private final int age;
    protected String email;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.email = "none@example.com";
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.email = name.toLowerCase() + "@example.com";
    }

    public String getName() {
        return name;
    }

    private int getAge() {
        return age;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

