
package codexbuilder.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import codexbuilder.model.WargearDescription;

public class WargearRepository {

    private final SessionFactory sessionFactory;

    public WargearRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<WargearDescription> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "FROM WargearDescription",
                WargearDescription.class
            ).list();
        }
    }

    public Optional<WargearDescription> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(
                session.get(WargearDescription.class, id)
            );
        }
    }

    public WargearDescription save(WargearDescription wargear) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            WargearDescription savedWargear = session.merge(wargear);

            transaction.commit();

            return savedWargear;

        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(WargearDescription wargear) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            WargearDescription managed = session.merge(wargear);
            session.remove(managed);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
