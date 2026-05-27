package ge.edu.freeuni.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // tells hibernate to map this class to a db called "book"
@NoArgsConstructor // lombok generates Book() for JPA use
@Getter // lombok generates getters
public class Book {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db auto-increments it from 1, 2...
    private int id;
    private String title;

    public Book(String title) {
        this.title = title;
    }
}
