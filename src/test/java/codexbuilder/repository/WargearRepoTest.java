
package codexbuilder.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codexbuilder.model.WargearDescription;
import codexbuilder.model.WargearType;

class WargearRepoTest {

    private static SessionFactory sessionFactory;

    private WargearRepository repository;

    @BeforeAll
    static void setUpClass() {

        sessionFactory = new Configuration()
            .addAnnotatedClass(WargearDescription.class)
            .setProperty(
                "hibernate.connection.driver_class",
                "org.h2.Driver"
            )
            .setProperty(
                "hibernate.connection.url",
                "jdbc:h2:mem:wargeartest;DB_CLOSE_DELAY=-1"
            )
            .setProperty(
                "hibernate.connection.username",
                "sa"
            )
            .setProperty(
                "hibernate.connection.password",
                ""
            )
            .setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.H2Dialect"
            )
            .setProperty(
                "hibernate.hbm2ddl.auto",
                "create-drop"
            )
            .buildSessionFactory();
    }

    @AfterAll
    static void tearDownClass() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @BeforeEach
    void setUp() {
        repository = new WargearRepository(sessionFactory);

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            session.createMutationQuery(
                "delete from WargearDescription"
            ).executeUpdate();

            transaction.commit();
        }
    }

    @Test
    void shouldSaveWargear() {

        WargearDescription wargear =
            new WargearDescription("Boltgun", WargearType.TWO_HANDED);

        WargearDescription saved = repository.save(wargear);

        assertNotNull(saved.getId());
        assertEquals("Boltgun", saved.getName());
        assertEquals(WargearType.TWO_HANDED, saved.getType());
    }

    @Test
    void shouldFindAllWargear() {

        repository.save(
            new WargearDescription("Boltgun", WargearType.TWO_HANDED)
        );

        repository.save(
            new WargearDescription("Chainsword", WargearType.ONE_HANDED)
        );

        List<WargearDescription> results = repository.findAll();

        assertEquals(2, results.size());
    }

    @Test
    void shouldFindWargearById() {

        WargearDescription wargear =
            new WargearDescription("Boltgun", WargearType.TWO_HANDED);

        WargearDescription saved = repository.save(wargear);

        Optional<WargearDescription> result =
            repository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Boltgun", result.get().getName());
        assertEquals(WargearType.TWO_HANDED, result.get().getType());
    }

    @Test
    void shouldReturnEmptyWhenWargearIdDoesNotExist() {

        Optional<WargearDescription> result =
            repository.findById(-1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldUpdateWargear() {

        WargearDescription wargear =
            new WargearDescription("Boltgun", WargearType.TWO_HANDED);

        WargearDescription saved = repository.save(wargear);

        saved.setName("Heavy Boltgun");

        repository.save(saved);

        Optional<WargearDescription> result =
            repository.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Heavy Boltgun", result.get().getName());
    }

    @Test
    void shouldDeleteWargear() {

        WargearDescription wargear =
            new WargearDescription("Boltgun", WargearType.TWO_HANDED);

        WargearDescription saved = repository.save(wargear);

        repository.delete(saved);

        Optional<WargearDescription> result =
            repository.findById(saved.getId());

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldPersistWargearAcrossSessions() {

        WargearDescription wargear =
            new WargearDescription("Chainsword", WargearType.TWO_HANDED);

        WargearDescription saved = repository.save(wargear);

        Long id = saved.getId();

        // findById opens a new Hibernate session
        Optional<WargearDescription> result =
            repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Chainsword", result.get().getName());
    }
}
