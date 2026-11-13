package codexbuilder.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import codexbuilder.model.WargearDescription;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
        buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                .addAnnotatedClass(WargearDescription.class)
                .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println(
                "SessionFactory creation failed: " + ex
            );

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}