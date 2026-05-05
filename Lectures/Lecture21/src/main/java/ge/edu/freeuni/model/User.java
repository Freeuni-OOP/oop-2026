package ge.edu.freeuni.model;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
