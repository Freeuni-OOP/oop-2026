package dao;

import hibernate.HibernateUtils;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateStudentRepository implements StudentRepository {

  @Override
  public void save(Student student) {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.saveOrUpdate(student);
      transaction.commit();
    }
  }

  @Override
  public Student findById(long id) {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      return session.get(Student.class, id);
    }
  }

  @Override
  public void deleteById(long id) {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      Student student = sessionFactory.getCurrentSession().get(Student.class, id);
      if (student != null) {
        session.delete(student);
      }
      transaction.commit();
    }
  }

}
