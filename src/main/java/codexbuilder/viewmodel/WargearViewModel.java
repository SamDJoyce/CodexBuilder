
package codexbuilder.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import codexbuilder.model.WargearDescription;
import codexbuilder.model.WargearType;
import codexbuilder.repository.WargearRepository;

public class WargearViewModel {

    private final WargearRepository repository;

    // Collection of available wargear
    private final ObservableList<WargearDescription> wargearItems =
            FXCollections.observableArrayList();

    // Currently selected wargear
    private final ObjectProperty<WargearDescription> selectedWargear =
            new SimpleObjectProperty<>();

    // Editable wargear properties
    private final StringProperty name =
            new SimpleStringProperty("");

    private final ObjectProperty<WargearType> type =
            new SimpleObjectProperty<>();

    // Constructor
    public WargearViewModel(WargearRepository repository) {
        this.repository = repository;
    }

    // Property accessors
    public ObservableList<WargearDescription> getWargearItems() {
        return wargearItems;
    }

    public ObjectProperty<WargearDescription> selectedWargearProperty() {
        return selectedWargear;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObjectProperty<WargearType> typeProperty() {
        return type;
    }

    // Load all wargear from the database
    public void loadWargear() {
        wargearItems.setAll(repository.findAll());
    }

    // Select an existing wargear record
    public void selectWargear(WargearDescription wargear) {

        selectedWargear.set(wargear);

        if (wargear == null) {
            clearFields();
            return;
        }

        name.set(wargear.getName());
        type.set(wargear.getType());
    }

    // Prepare the ViewModel for a new record
    public void newWargear() {
        selectedWargear.set(null);
        clearFields();
    }

    // Save a new or existing wargear record
    public void saveWargear() {

        if (name.get() == null || name.get().isBlank()) {
            throw new IllegalArgumentException(
                    "Wargear name cannot be empty.");
        }

        if (type.get() == null) {
            throw new IllegalArgumentException(
                    "A wargear type must be selected.");
        }

        WargearDescription wargear = selectedWargear.get();

        // Create a new entity if no existing record is selected
        if (wargear == null) {
            wargear = new WargearDescription(
                    name.get().trim(),
                    type.get());
        } else {
            // Update the existing entity
            wargear.setName(name.get().trim());
            wargear.setType(type.get());
        }

        // Save and obtain the managed entity returned by Hibernate
        WargearDescription savedWargear =
                repository.save(wargear);

        selectedWargear.set(savedWargear);

        // Update the ObservableList
        boolean updated = false;

        for (int i = 0; i < wargearItems.size(); i++) {

            if (wargearItems.get(i).getId()
                    .equals(savedWargear.getId())) {

                wargearItems.set(i, savedWargear);
                updated = true;
                break;
            }
        }

        if (!updated) {
            wargearItems.add(savedWargear);
        }
    }

    // Delete the currently selected wargear record
    public void deleteWargear() {

        WargearDescription wargear = selectedWargear.get();

        if (wargear == null) {
            return;
        }

        repository.delete(wargear);

        wargearItems.removeIf(item ->
                item.getId().equals(wargear.getId()));

        newWargear();
    }

    // Clear the editable fields
    private void clearFields() {
        name.set("");
        type.set(null);
    }
}
