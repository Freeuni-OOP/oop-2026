package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            Lecturer lecturer = new Lecturer("Nikoloz", "Tsimakuridze");

            session.persist(lecturer);

            transaction.commit();

            List<Lecturer> lecturers = session.createQuery("from Lecturer", Lecturer.class).list();
            lecturers.forEach(l -> System.out.println(l.getFirstName() + " " + l.getLastName()));
        }
    }
}
