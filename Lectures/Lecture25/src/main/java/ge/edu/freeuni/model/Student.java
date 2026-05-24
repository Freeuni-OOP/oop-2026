package ge.edu.freeuni.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * FEATURE: JPA Entity
 * Mapped to the "students" table.  Hibernate creates the table automatically
 * because spring.jpa.hibernate.ddl-auto=create-drop.
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
