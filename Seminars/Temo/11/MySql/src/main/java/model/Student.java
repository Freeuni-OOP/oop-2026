package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Student {
  private long id;
  private long studentId;
  private String idNumber;
  private String firstName;
  private String lastName;
  private Date registrationDate;

  public void setId(long id) {
    this.id = id;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public static Student from(ResultSet rs) throws SQLException {
    Student student = new Student();
    student.setId(rs.getLong("id"));
    student.setStudentId(rs.getLong("student_id"));
    student.setIdNumber(rs.getString("id_number"));
    student.setFirstName(rs.getString("first_name"));
    student.setLastName(rs.getString("last_name"));
    student.setRegistrationDate(rs.getDate("registration_date"));
    return student;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", idNumber='" + idNumber + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", registrationDate=" + registrationDate +
        '}';
  }
}
