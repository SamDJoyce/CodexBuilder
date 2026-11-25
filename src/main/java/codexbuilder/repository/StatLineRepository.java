
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

    /**
     * Retrieve all StatLines from the DB
     * 
     * @return	a list of all StatLines in the DB
     */
    public List<AbstractStatLine> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM AbstractStatLine",
                    AbstractStatLine.class
            ).list();
        }
    }

    /**
     * Retrieve a StatLine from the DB by its ID
     * 
     * @param id	StatLine ID
     * @return		the StatLine matching the provided ID
     */
    public AbstractStatLine findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(AbstractStatLine.class, id);
        }
    }

    /**
     * Save a StatLine to the DB
     * 
     * @param statLine	the StatLine to be saved
     * @return			the StatLine as saved in the DB
     */
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

    /**
     * Delete a StatLine from the DB
     * 
     * @param statLine		The StatLine to be deleted
     */
    public void delete(AbstractStatLine statLine) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            AbstractStatLine toDelete = session.merge(statLine);
            session.remove(toDelete);

            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw e;
        }
    }
}
