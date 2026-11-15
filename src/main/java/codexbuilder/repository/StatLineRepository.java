
package codexbuilder.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import codexbuilder.model.AbstractStatLine;

public class StatLineRepository {

    private final SessionFactory sessionFactory;

    public StatLineRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AbstractStatLine> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM AbstractStatLine",
                    AbstractStatLine.class
            ).list();
        }
    }

    public AbstractStatLine findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(AbstractStatLine.class, id);
        }
    }

    public AbstractStatLine save(AbstractStatLine statLine) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            AbstractStatLine savedStatLine = session.merge(statLine);

            transaction.commit();

            return savedStatLine;

        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        }
    }

    public void delete(AbstractStatLine statLine) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            AbstractStatLine managedStatLine = session.merge(statLine);
            session.remove(managedStatLine);

            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        }
    }
}
