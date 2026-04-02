package src.model;

import java.util.Date;

public class Student {
    private Long id;
    private Long student_id;
    private Long idnumber;
    private String firstname;
    private String lastname;
    private Date register_date;

    public Student(Long id,
        Long student_id,
        Long idnumber,
        String firstname,
        String lastname,
        Date register_date)
    {
        this.id = id;
        this.student_id = student_id;
        this.idnumber = idnumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.register_date = register_date;
    }

    public Long getId() {
        return id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public Long getIdnumber() {
        return idnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getRegister_date() {
        return register_date;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", student_id=" + student_id +
            ", idnumber=" + idnumber +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", register_date=" + register_date +
            '}';
    }
}