package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false, name = "first_name", length = 50)
  private String firstName;
  @Column(nullable = false, name = "first_name", length = 50)
  private String lastName;
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date registrationDate;

  @Transient
  private String idNumber;
}
