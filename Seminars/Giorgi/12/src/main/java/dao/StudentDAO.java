package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class StudentDAO {

    public Student getStudentByID(Long id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, id);
            if (student == null) {
                throw new Exception("Student not found");
            }
            return student;
        }
    }

    public void addStudent(Student student) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
        } catch (Exception exception) {
            throw new Exception("Failed to add student", exception);
        }
    }
}
