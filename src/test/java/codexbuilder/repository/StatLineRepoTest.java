
package codexbuilder.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codexbuilder.model.AbstractStatLine;
import codexbuilder.model.StatLineInfantry;
import codexbuilder.model.StatLineVehicle;
import codexbuilder.model.StatLineWalker;

class StatLineRepoTest {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private StatLineRepository statLineRepository;

    @BeforeAll
    static void setUpClass() {

        registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.driver_class", "org.h2.Driver")
                .applySetting("hibernate.connection.url",
                        "jdbc:h2:mem:statline_test;DB_CLOSE_DELAY=-1")
                .applySetting("hibernate.connection.username", "sa")
                .applySetting("hibernate.connection.password", "")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .applySetting("hibernate.hbm2ddl.auto", "create-drop")
                .applySetting("hibernate.show_sql", "false")
                .build();

        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(AbstractStatLine.class)
                .addAnnotatedClass(StatLineInfantry.class)
                .addAnnotatedClass(StatLineVehicle.class)
                .addAnnotatedClass(StatLineWalker.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    @AfterAll
    static void tearDownClass() {

        if (sessionFactory != null) {
            sessionFactory.close();
        }

        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @BeforeEach
    void setUp() {

        statLineRepository = new StatLineRepository(sessionFactory);

        // Ensure each test starts with an empty database.
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createMutationQuery("DELETE FROM AbstractStatLine")
                    .executeUpdate();

            transaction.commit();
        }
    }

    @Test
    void saveInfantryStatLine() {

        StatLineInfantry infantry = new StatLineInfantry(
                "Space Marine", 4, 4, 4, 4, 1, 4, 1, 8, 3
        );

        AbstractStatLine savedStatLine = statLineRepository.save(infantry);

        assertNotNull(savedStatLine.getId());
        assertInstanceOf(StatLineInfantry.class, savedStatLine);
        assertEquals("Space Marine", savedStatLine.getName());
        assertEquals("infantry", savedStatLine.getType());
    }

    @Test
    void saveVehicleStatLine() {

        StatLineVehicle vehicle = new StatLineVehicle(
                "Rhino", 4, 11, 11, 10
        );

        AbstractStatLine savedStatLine = statLineRepository.save(vehicle);

        assertNotNull(savedStatLine.getId());
        assertInstanceOf(StatLineVehicle.class, savedStatLine);
        assertEquals("Rhino", savedStatLine.getName());
        assertEquals("vehicle", savedStatLine.getType());
    }

    @Test
    void saveWalkerStatLine() {

        StatLineWalker walker = new StatLineWalker(
                "Dreadnought", 4, 4, 6, 4, 2, 12, 12, 10
        );

        AbstractStatLine savedStatLine = statLineRepository.save(walker);

        assertNotNull(savedStatLine.getId());
        assertInstanceOf(StatLineWalker.class, savedStatLine);
        assertEquals("Dreadnought", savedStatLine.getName());
        assertEquals("walker", savedStatLine.getType());
    }

    @Test
    void findByIdReturnsSavedInfantryStatLine() {

        StatLineInfantry infantry = new StatLineInfantry(
                "Space Marine", 4, 4, 4, 4, 1, 4, 1, 8, 3
        );

        AbstractStatLine savedStatLine = statLineRepository.save(infantry);

        AbstractStatLine retrievedStatLine =
                statLineRepository.findById(savedStatLine.getId());

        assertNotNull(retrievedStatLine);
        assertInstanceOf(StatLineInfantry.class, retrievedStatLine);
        assertEquals(savedStatLine.getId(), retrievedStatLine.getId());
        assertEquals("Space Marine", retrievedStatLine.getName());

        StatLineInfantry retrievedInfantry =
                (StatLineInfantry) retrievedStatLine;

        assertEquals(4, retrievedInfantry.getWs());
        assertEquals(4, retrievedInfantry.getBs());
        assertEquals(3, retrievedInfantry.getSv());
    }

    @Test
    void findByIdReturnsNullForMissingId() {

        AbstractStatLine retrievedStatLine =
                statLineRepository.findById(-1L);

        assertNull(retrievedStatLine);
    }

    @Test
    void findAllReturnsAllStatLineTypes() {

        statLineRepository.save(new StatLineInfantry(
                "Space Marine", 4, 4, 4, 4, 1, 4, 1, 8, 3
        ));

        statLineRepository.save(new StatLineVehicle(
                "Rhino", 4, 11, 11, 10
        ));

        statLineRepository.save(new StatLineWalker(
                "Dreadnought", 4, 4, 6, 4, 2, 12, 12, 10
        ));

        List<AbstractStatLine> statLines = statLineRepository.findAll();

        assertEquals(3, statLines.size());

        assertTrue(statLines.stream()
                .anyMatch(StatLineInfantry.class::isInstance));

        assertTrue(statLines.stream()
                .anyMatch(StatLineVehicle.class::isInstance));

        assertTrue(statLines.stream()
                .anyMatch(StatLineWalker.class::isInstance));
    }

    @Test
    void saveUpdatesExistingStatLine() {

        StatLineInfantry infantry = new StatLineInfantry(
                "Space Marine", 4, 4, 4, 4, 1, 4, 1, 8, 3
        );

        AbstractStatLine savedStatLine = statLineRepository.save(infantry);

        StatLineInfantry updatedInfantry =
                (StatLineInfantry) savedStatLine;

        updatedInfantry.setWs(5);
        updatedInfantry.setName("Veteran Space Marine");

        statLineRepository.save(updatedInfantry);

        AbstractStatLine retrievedStatLine =
                statLineRepository.findById(savedStatLine.getId());

        assertNotNull(retrievedStatLine);
        assertInstanceOf(StatLineInfantry.class, retrievedStatLine);

        StatLineInfantry retrievedInfantry =
                (StatLineInfantry) retrievedStatLine;

        assertEquals("Veteran Space Marine", retrievedInfantry.getName());
        assertEquals(5, retrievedInfantry.getWs());
    }

    @Test
    void deleteRemovesStatLine() {

        StatLineVehicle vehicle = new StatLineVehicle(
                "Rhino", 4, 11, 11, 10
        );

        AbstractStatLine savedStatLine = statLineRepository.save(vehicle);

        assertNotNull(statLineRepository.findById(savedStatLine.getId()));

        statLineRepository.delete(savedStatLine);

        assertNull(statLineRepository.findById(savedStatLine.getId()));
    }
}
