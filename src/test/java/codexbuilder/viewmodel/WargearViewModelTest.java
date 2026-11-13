
package codexbuilder.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codexbuilder.model.WargearDescription;
import codexbuilder.model.WargearType;
import codexbuilder.repository.WargearRepository;

class WargearViewModelTest {

    private WargearRepository repository;
    private WargearViewModel viewModel;

    @BeforeEach
    void setUp() {
        repository = mock(WargearRepository.class);
        viewModel = new WargearViewModel(repository);
    }

    // Loading tests

    @Test
    void shouldLoadWargear() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        WargearDescription chainsword =
                new WargearDescription(
                        "Chainsword",
                        WargearType.ONE_HANDED);

        when(repository.findAll())
                .thenReturn(List.of(boltgun, chainsword));

        viewModel.loadWargear();

        assertEquals(2, viewModel.getWargearItems().size());
        assertTrue(viewModel.getWargearItems().contains(boltgun));
        assertTrue(viewModel.getWargearItems().contains(chainsword));

        verify(repository).findAll();
    }

    @Test
    void shouldLoadEmptyWargearList() {

        when(repository.findAll()).thenReturn(List.of());

        viewModel.loadWargear();

        assertTrue(viewModel.getWargearItems().isEmpty());
    }

    // Selection tests

    @Test
    void shouldSelectWargear() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        viewModel.selectWargear(boltgun);

        assertEquals(boltgun,
                viewModel.selectedWargearProperty().get());

        assertEquals("Boltgun",
                viewModel.nameProperty().get());

        assertEquals(WargearType.ONE_HANDED,
                viewModel.typeProperty().get());
    }

    @Test
    void shouldClearFieldsWhenSelectionIsNull() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        viewModel.selectWargear(boltgun);
        viewModel.selectWargear(null);

        assertNull(viewModel.selectedWargearProperty().get());
        assertEquals("", viewModel.nameProperty().get());
        assertNull(viewModel.typeProperty().get());
    }

    @Test
    void shouldPrepareNewWargear() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        viewModel.selectWargear(boltgun);
        viewModel.newWargear();

        assertNull(viewModel.selectedWargearProperty().get());
        assertEquals("", viewModel.nameProperty().get());
        assertNull(viewModel.typeProperty().get());
    }

    // Saving tests

    @Test
    void shouldSaveNewWargear() {

        when(repository.save(any(WargearDescription.class)))
                .thenAnswer(invocation -> {
                    WargearDescription wargear =
                            invocation.getArgument(0);

                    wargear.setId(1L);

                    return wargear;
                });

        viewModel.nameProperty().set("Boltgun");
        viewModel.typeProperty().set(WargearType.ONE_HANDED);

        viewModel.saveWargear();

        assertEquals(1, viewModel.getWargearItems().size());

        WargearDescription savedWargear =
                viewModel.getWargearItems().get(0);

        assertEquals(1L, savedWargear.getId());
        assertEquals("Boltgun", savedWargear.getName());
        assertEquals(WargearType.ONE_HANDED, savedWargear.getType());

        assertEquals(savedWargear,
                viewModel.selectedWargearProperty().get());

        verify(repository).save(any(WargearDescription.class));
    }

    @Test
    void shouldTrimWargearNameWhenSaving() {

        when(repository.save(any(WargearDescription.class)))
                .thenAnswer(invocation -> {
                    WargearDescription wargear =
                            invocation.getArgument(0);

                    wargear.setId(1L);

                    return wargear;
                });

        viewModel.nameProperty().set("  Boltgun  ");
        viewModel.typeProperty().set(WargearType.ONE_HANDED);

        viewModel.saveWargear();

        assertEquals("Boltgun",
                viewModel.getWargearItems().get(0).getName());
    }

    @Test
    void shouldUpdateExistingWargear() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        boltgun.setId(1L);

        viewModel.getWargearItems().add(boltgun);
        viewModel.selectWargear(boltgun);

        when(repository.save(any(WargearDescription.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        viewModel.nameProperty().set("Heavy Boltgun");
        viewModel.typeProperty().set(WargearType.TWO_HANDED);

        viewModel.saveWargear();

        assertEquals(1, viewModel.getWargearItems().size());

        assertEquals("Heavy Boltgun",
                viewModel.getWargearItems().get(0).getName());

        assertEquals(WargearType.TWO_HANDED,
                viewModel.getWargearItems().get(0).getType());

        verify(repository).save(boltgun);
    }

    @Test
    void shouldRejectEmptyWargearName() {

        viewModel.nameProperty().set("");
        viewModel.typeProperty().set(WargearType.ONE_HANDED);

        assertThrows(IllegalArgumentException.class,
                () -> viewModel.saveWargear());

        verify(repository, never()).save(any());
    }

    @Test
    void shouldRejectNullWargearType() {

        viewModel.nameProperty().set("Boltgun");
        viewModel.typeProperty().set(null);

        assertThrows(IllegalArgumentException.class,
                () -> viewModel.saveWargear());

        verify(repository, never()).save(any());
    }

    // Deletion tests

    @Test
    void shouldDeleteSelectedWargear() {

        WargearDescription boltgun =
                new WargearDescription(
                        "Boltgun",
                        WargearType.ONE_HANDED);

        boltgun.setId(1L);

        viewModel.getWargearItems().add(boltgun);
        viewModel.selectWargear(boltgun);

        viewModel.deleteWargear();

        assertTrue(viewModel.getWargearItems().isEmpty());
        assertNull(viewModel.selectedWargearProperty().get());
        assertEquals("", viewModel.nameProperty().get());
        assertNull(viewModel.typeProperty().get());

        verify(repository).delete(boltgun);
    }

    @Test
    void shouldNotDeleteWhenNothingIsSelected() {

        viewModel.deleteWargear();

        verify(repository, never()).delete(any());
        assertTrue(viewModel.getWargearItems().isEmpty());
    }
}
