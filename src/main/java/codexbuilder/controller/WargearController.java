
package codexbuilder.controller;

import codexbuilder.model.WargearDescription;
import codexbuilder.model.WargearType;
import codexbuilder.viewmodel.WargearViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class WargearController {

    @FXML
    private ListView<WargearDescription> existingWargearListView;

    @FXML
    private TextField wargearNameField;

    @FXML
    private ComboBox<WargearType> wargearTypeComboBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Button deleteWargearButton;

    private WargearViewModel viewModel;

    /**
     * Connects the controller to its ViewModel.
     */
    public void setViewModel(WargearViewModel viewModel) {
        this.viewModel = viewModel;

        // Populate the type selection.
        wargearTypeComboBox.getItems().setAll(WargearType.values());

        // Connect the ViewModel's data to the controls.
        existingWargearListView.setItems(
                viewModel.getWargearItems());

        wargearNameField.textProperty().bindBidirectional(
                viewModel.nameProperty());

        wargearTypeComboBox.valueProperty().bindBidirectional(
                viewModel.typeProperty());

        // Disable Delete when no item is selected.
        deleteWargearButton.disableProperty().bind(
                viewModel.selectedWargearProperty().isNull());

        // Respond to changes in the selected wargear.
        existingWargearListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    viewModel.selectWargear(newValue);
                    clearError();
                });

        // Load existing wargear from the database.
        try {
            viewModel.loadWargear();
        } catch (RuntimeException e) {
            errorLabel.setText(
                    "Unable to load wargear: " + e.getMessage());
        }
    }

    /**
     * Clears the form for a new wargear entry.
     */
    @FXML
    private void handleNew() {
        viewModel.newWargear();

        existingWargearListView.getSelectionModel()
                .clearSelection();

        clearError();
        wargearNameField.requestFocus();
    }

    /**
     * Saves a new or edited wargear entry.
     */
    @FXML
    private void handleSave() {
        clearError();

        try {
            viewModel.saveWargear();

            // Select the saved entry in the ListView.
            existingWargearListView.getSelectionModel()
                    .select(viewModel.selectedWargearProperty().get());

        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());

        } catch (RuntimeException e) {
            errorLabel.setText(
                    "Unable to save wargear: " + e.getMessage());
        }
    }

    /**
     * Deletes the currently selected wargear entry.
     */
    @FXML
    private void handleDelete() {
        clearError();

        try {
            viewModel.deleteWargear();

            existingWargearListView.getSelectionModel()
                    .clearSelection();

        } catch (RuntimeException e) {
            errorLabel.setText(
                    "Unable to delete wargear: " + e.getMessage());
        }
    }

    /**
     * Clears the error message.
     */
    private void clearError() {
        errorLabel.setText("");
    }
}
