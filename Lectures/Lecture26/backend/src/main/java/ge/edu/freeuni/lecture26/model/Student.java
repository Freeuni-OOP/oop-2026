package ge.edu.freeuni.lecture26.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * JPA Entity – unchanged from Lecture 25.
 * Mapped to the "students" table in MySQL.
 */
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Major major;

    public enum Major { COMPUTER_SCIENCE, MATHEMATICS, PHYSICS, BUSINESS }
}
