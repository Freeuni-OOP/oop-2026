package dao;

import model.Student;

public interface StudentRepository {
  void save(Student student);

  Student findById(long id);

  void deleteById(long id);
}
