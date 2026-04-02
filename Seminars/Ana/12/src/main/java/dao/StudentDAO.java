package dao;

import entity.Student;
import org.hibernate.Session;
import util.HibernateUtil;

public class StudentDAO {
    public Student getStudentById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);

        }
    }
}