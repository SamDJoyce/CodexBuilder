
package codexbuilder.viewmodel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codexbuilder.model.AbstractStatLine;
import codexbuilder.model.StatLineInfantry;
import codexbuilder.model.StatLineVehicle;
import codexbuilder.model.StatLineWalker;
import codexbuilder.repository.StatLineRepository;

class StatLineViewModelTest {

    private StatLineRepository statLineRepository;
    private StatLineViewModel viewModel;

    @BeforeEach
    void setUp() {

        statLineRepository = mock(StatLineRepository.class);

        when(statLineRepository.findAll())
                .thenReturn(new ArrayList<>());

        viewModel = new StatLineViewModel(statLineRepository);
    }

    private StatLineInfantry createInfantry() {
        return new StatLineInfantry(
                "Space Marine",
                4, 4, 4, 4, 1, 4, 1, 8, 3
        );
    }

    private StatLineVehicle createVehicle() {
        return new StatLineVehicle(
                "Rhino",
                4, 11, 11, 10
        );
    }

    private StatLineWalker createWalker() {
        return new StatLineWalker(
                "Dreadnought",
                4, 4, 6, 4, 2, 12, 12, 10
        );
    }

    // ---------------------------------------------------------
    // Loading stat lines
    // ---------------------------------------------------------

    @Test
    void constructorLoadsStatLines() {

        verify(statLineRepository).findAll();

        assertNotNull(viewModel.getStatLines());
        assertTrue(viewModel.getStatLines().isEmpty());
    }

    @Test
    void loadStatLinesUpdatesObservableList() {

        List<AbstractStatLine> statLines = List.of(
                createInfantry(),
                createVehicle(),
                createWalker()
        );

        when(statLineRepository.findAll())
                .thenReturn(statLines);

        viewModel.loadStatLines();

        assertEquals(3, viewModel.getStatLines().size());
        assertTrue(viewModel.getStatLines().containsAll(statLines));
    }

    // ---------------------------------------------------------
    // Selecting stat lines
    // ---------------------------------------------------------

    @Test
    void selectInfantryPopulatesProperties() {

        StatLineInfantry infantry = createInfantry();

        viewModel.selectStatLine(infantry);

        assertSame(infantry, viewModel.getSelectedStatLine());

        assertEquals("Space Marine", viewModel.nameProperty().get());
        assertEquals("infantry", viewModel.typeProperty().get());

        assertEquals(4, viewModel.wsProperty().get());
        assertEquals(4, viewModel.bsProperty().get());
        assertEquals(4, viewModel.sProperty().get());
        assertEquals(4, viewModel.tProperty().get());
        assertEquals(1, viewModel.wProperty().get());
        assertEquals(4, viewModel.iProperty().get());
        assertEquals(1, viewModel.aProperty().get());
        assertEquals(8, viewModel.ldProperty().get());
        assertEquals(3, viewModel.svProperty().get());

        assertEquals(0, viewModel.frontProperty().get());
        assertEquals(0, viewModel.sideProperty().get());
        assertEquals(0, viewModel.rearProperty().get());
    }

    @Test
    void selectVehiclePopulatesProperties() {

        StatLineVehicle vehicle = createVehicle();

        viewModel.selectStatLine(vehicle);

        assertSame(vehicle, viewModel.getSelectedStatLine());

        assertEquals("Rhino", viewModel.nameProperty().get());
        assertEquals("vehicle", viewModel.typeProperty().get());

        assertEquals(4, viewModel.bsProperty().get());
        assertEquals(11, viewModel.frontProperty().get());
        assertEquals(11, viewModel.sideProperty().get());
        assertEquals(10, viewModel.rearProperty().get());

        assertEquals(0, viewModel.wsProperty().get());
        assertEquals(0, viewModel.sProperty().get());
        assertEquals(0, viewModel.tProperty().get());
        assertEquals(0, viewModel.wProperty().get());
        assertEquals(0, viewModel.iProperty().get());
        assertEquals(0, viewModel.aProperty().get());
        assertEquals(0, viewModel.ldProperty().get());
        assertEquals(0, viewModel.svProperty().get());
    }

    @Test
    void selectWalkerPopulatesProperties() {

        StatLineWalker walker = createWalker();

        viewModel.selectStatLine(walker);

        assertSame(walker, viewModel.getSelectedStatLine());

        assertEquals("Dreadnought", viewModel.nameProperty().get());
        assertEquals("walker", viewModel.typeProperty().get());

        assertEquals(4, viewModel.wsProperty().get());
        assertEquals(4, viewModel.bsProperty().get());
        assertEquals(6, viewModel.sProperty().get());
        assertEquals(4, viewModel.iProperty().get());
        assertEquals(2, viewModel.aProperty().get());

        assertEquals(12, viewModel.frontProperty().get());
        assertEquals(12, viewModel.sideProperty().get());
        assertEquals(10, viewModel.rearProperty().get());

        assertEquals(0, viewModel.tProperty().get());
        assertEquals(0, viewModel.wProperty().get());
        assertEquals(0, viewModel.ldProperty().get());
        assertEquals(0, viewModel.svProperty().get());
    }

    @Test
    void selectNullClearsFields() {

        viewModel.selectStatLine(createInfantry());

        viewModel.selectStatLine(null);

        assertNull(viewModel.getSelectedStatLine());

        assertEquals("", viewModel.nameProperty().get());
        assertEquals("", viewModel.typeProperty().get());

        assertEquals(0, viewModel.wsProperty().get());
        assertEquals(0, viewModel.bsProperty().get());
        assertEquals(0, viewModel.sProperty().get());
        assertEquals(0, viewModel.tProperty().get());
        assertEquals(0, viewModel.wProperty().get());
        assertEquals(0, viewModel.iProperty().get());
        assertEquals(0, viewModel.aProperty().get());
        assertEquals(0, viewModel.ldProperty().get());
        assertEquals(0, viewModel.svProperty().get());

        assertEquals(0, viewModel.frontProperty().get());
        assertEquals(0, viewModel.sideProperty().get());
        assertEquals(0, viewModel.rearProperty().get());
    }

    // ---------------------------------------------------------
    // Creating stat lines
    // ---------------------------------------------------------

    @Test
    void newStatLineCreatesInfantry() {

        viewModel.newStatLine("infantry");

        assertNotNull(viewModel.getSelectedStatLine());
        assertInstanceOf(
                StatLineInfantry.class,
                viewModel.getSelectedStatLine()
        );

        assertEquals("infantry", viewModel.typeProperty().get());
    }

    @Test
    void newStatLineCreatesVehicle() {

        viewModel.newStatLine("vehicle");

        assertNotNull(viewModel.getSelectedStatLine());
        assertInstanceOf(
                StatLineVehicle.class,
                viewModel.getSelectedStatLine()
        );

        assertEquals("vehicle", viewModel.typeProperty().get());
    }

    @Test
    void newStatLineCreatesWalker() {

        viewModel.newStatLine("walker");

        assertNotNull(viewModel.getSelectedStatLine());
        assertInstanceOf(
                StatLineWalker.class,
                viewModel.getSelectedStatLine()
        );

        assertEquals("walker", viewModel.typeProperty().get());
    }

    @Test
    void newStatLineAcceptsUppercaseType() {

        viewModel.newStatLine("INFANTRY");

        assertInstanceOf(
                StatLineInfantry.class,
                viewModel.getSelectedStatLine()
        );
    }

    @Test
    void newStatLineRejectsUnsupportedType() {

        assertThrows(
                IllegalArgumentException.class,
                () -> viewModel.newStatLine("unknown")
        );
    }

    @Test
    void newStatLineRejectsNullType() {

        assertThrows(
                IllegalArgumentException.class,
                () -> viewModel.newStatLine(null)
        );
    }

    // ---------------------------------------------------------
    // Saving stat lines
    // ---------------------------------------------------------

    @Test
    void saveStatLineWithoutSelectionReturnsFalse() {

        assertFalse(viewModel.saveStatLine());

        assertFalse(
                viewModel.validationMessageProperty().get().isBlank()
        );

        verify(statLineRepository, never())
                .save(any(AbstractStatLine.class));
    }

    @Test
    void saveStatLineRejectsBlankName() {

        viewModel.newStatLine("infantry");
        viewModel.nameProperty().set("   ");

        assertFalse(viewModel.saveStatLine());

        assertFalse(
                viewModel.validationMessageProperty().get().isBlank()
        );

        verify(statLineRepository, never())
                .save(any(AbstractStatLine.class));
    }

    @Test
    void saveNewStatLineAddsSavedEntityToList() {

        viewModel.newStatLine("infantry");

        viewModel.nameProperty().set("Space Marine");
        viewModel.wsProperty().set(4);
        viewModel.bsProperty().set(4);
        viewModel.sProperty().set(4);
        viewModel.tProperty().set(4);
        viewModel.wProperty().set(1);
        viewModel.iProperty().set(4);
        viewModel.aProperty().set(1);
        viewModel.ldProperty().set(8);
        viewModel.svProperty().set(3);

        when(statLineRepository.save(any(AbstractStatLine.class)))
                .thenAnswer(invocation -> {

                    AbstractStatLine argument =
                            invocation.getArgument(0);

                    AbstractStatLine saved = spy(argument);

                    doReturn(101L).when(saved).getId();

                    return saved;
                });

        assertTrue(viewModel.saveStatLine());

        verify(statLineRepository).save(any(AbstractStatLine.class));

        assertEquals(1, viewModel.getStatLines().size());

        AbstractStatLine savedStatLine =
                viewModel.getStatLines().get(0);

        assertInstanceOf(StatLineInfantry.class, savedStatLine);
        assertEquals(101L, savedStatLine.getId());

        assertSame(savedStatLine, viewModel.getSelectedStatLine());

        StatLineInfantry savedInfantry =
                (StatLineInfantry) savedStatLine;

        assertEquals("Space Marine", savedInfantry.getName());
        assertEquals(4, savedInfantry.getWs());
        assertEquals(3, savedInfantry.getSv());
    }


	@Test
	void saveExistingStatLineUpdatesListInsteadOfAddingDuplicate() {
	
	    StatLineInfantry existing = spy(createInfantry());
	
	    doReturn(42L).when(existing).getId();
	
	    viewModel.getStatLines().add(existing);
	    viewModel.selectStatLine(existing);
	
	    viewModel.nameProperty().set("Veteran Space Marine");
	    viewModel.wsProperty().set(5);
	
	    when(statLineRepository.save(any(AbstractStatLine.class)))
	            .thenAnswer(invocation -> invocation.getArgument(0));
	
	    assertTrue(viewModel.saveStatLine());
	
	    verify(statLineRepository).save(existing);
	
	    assertEquals(1, viewModel.getStatLines().size());
	
	    AbstractStatLine updated =
	            viewModel.getStatLines().get(0);
	
	    assertSame(existing, updated);
	    assertEquals(42L, updated.getId());
	    assertSame(updated, viewModel.getSelectedStatLine());
	
	    StatLineInfantry updatedInfantry =
	            (StatLineInfantry) updated;
	
	    assertEquals("Veteran Space Marine", updatedInfantry.getName());
	    assertEquals(5, updatedInfantry.getWs());
	}


    // ---------------------------------------------------------
    // Deleting stat lines
    // ---------------------------------------------------------

    @Test
    void deleteStatLineRemovesSelectedStatLine() {

        StatLineVehicle vehicle = spy(createVehicle());

        doReturn(55L).when(vehicle).getId();

        viewModel.getStatLines().add(vehicle);
        viewModel.selectStatLine(vehicle);

        viewModel.deleteStatLine();

        verify(statLineRepository).delete(vehicle);

        assertTrue(viewModel.getStatLines().isEmpty());
        assertNull(viewModel.getSelectedStatLine());

        assertEquals("", viewModel.nameProperty().get());
        assertEquals("", viewModel.typeProperty().get());
    }

    @Test
    void deleteStatLineWithoutSelectionDoesNothing() {

        viewModel.deleteStatLine();

        verify(statLineRepository, never())
                .delete(any(AbstractStatLine.class));

        assertTrue(viewModel.getStatLines().isEmpty());
        assertNull(viewModel.getSelectedStatLine());
    }
}
