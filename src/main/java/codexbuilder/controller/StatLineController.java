
package codexbuilder.controller;

import java.util.Locale;

import codexbuilder.model.AbstractStatLine;
import codexbuilder.viewmodel.StatLineViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

public class StatLineController {

    private StatLineViewModel viewModel;

    @FXML
    private ListView<AbstractStatLine> existingStatLinesListView;

    @FXML
    private TextField statLineNameField;

    @FXML
    private ComboBox<String> statLineTypeComboBox;

    @FXML
    private VBox infantryCharacteristics;

    @FXML
    private VBox vehicleCharacteristics;

    @FXML
    private VBox walkerCharacteristics;

    @FXML
    private TextField wsField;

    @FXML
    private TextField bsField;

    @FXML
    private TextField sField;

    @FXML
    private TextField tField;

    @FXML
    private TextField wField;

    @FXML
    private TextField iField;

    @FXML
    private TextField aField;

    @FXML
    private TextField ldField;

    @FXML
    private TextField svField;

    @FXML
    private TextField vehicleBsField;

    @FXML
    private TextField frontField;

    @FXML
    private TextField sideField;

    @FXML
    private TextField rearField;

    @FXML
    private TextField walkerWsField;

    @FXML
    private TextField walkerBsField;

    @FXML
    private TextField walkerSField;

    @FXML
    private TextField walkerIField;

    @FXML
    private TextField walkerAField;

    @FXML
    private TextField walkerFrontField;

    @FXML
    private TextField walkerSideField;

    @FXML
    private TextField walkerRearField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button newStatLineButton;

    @FXML
    private Button saveStatLineButton;

    @FXML
    private Button deleteStatLineButton;

    public void setViewModel(StatLineViewModel viewModel) {
        this.viewModel = viewModel;
        initializeView();
    }

    private void initializeView() {
        // Populate the StatLine type selector.
        statLineTypeComboBox.setItems(
            FXCollections.observableArrayList(
                "Infantry",
                "Vehicle",
                "Walker"
            )
        );
        // Populate the existing StatLines list.
        existingStatLinesListView.setItems(viewModel.getStatLines());

        existingStatLinesListView.setCellFactory(listView ->
            new ListCell<AbstractStatLine>() {
                @Override
                protected void updateItem(AbstractStatLine statLine, boolean empty) {
                    super.updateItem(statLine, empty);

                    if (empty || statLine == null) {
                        setText(null);
                    } else {
                        setText(statLine.getName() + " (" + statLine.getType() + ")");
                    }
                }
            }
        );

        // Bind the name and validation message.
        statLineNameField.textProperty()
            .bindBidirectional(viewModel.nameProperty());

        errorLabel.textProperty()
            .bind(viewModel.validationMessageProperty());

        // Synchronize characteristic fields with ViewModel properties.
        bindIntegerField(wsField, viewModel.wsProperty());
        bindIntegerField(bsField, viewModel.bsProperty());
        bindIntegerField(sField, viewModel.sProperty());
        bindIntegerField(tField, viewModel.tProperty());
        bindIntegerField(wField, viewModel.wProperty());
        bindIntegerField(iField, viewModel.iProperty());
        bindIntegerField(aField, viewModel.aProperty());
        bindIntegerField(ldField, viewModel.ldProperty());
        bindIntegerField(svField, viewModel.svProperty());

        bindIntegerField(vehicleBsField, viewModel.bsProperty());
        bindIntegerField(frontField, viewModel.frontProperty());
        bindIntegerField(sideField, viewModel.sideProperty());
        bindIntegerField(rearField, viewModel.rearProperty());

        bindIntegerField(walkerWsField, viewModel.wsProperty());
        bindIntegerField(walkerBsField, viewModel.bsProperty());
        bindIntegerField(walkerSField, viewModel.sProperty());
        bindIntegerField(walkerIField, viewModel.iProperty());
        bindIntegerField(walkerAField, viewModel.aProperty());
        bindIntegerField(walkerFrontField, viewModel.frontProperty());
        bindIntegerField(walkerSideField, viewModel.sideProperty());
        bindIntegerField(walkerRearField, viewModel.rearProperty());

        // Respond to selection changes in the existing StatLines list.
        existingStatLinesListView.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                viewModel.selectStatLine(newValue);
            });

        // Update the visible characteristics section when the
        // selected StatLine changes.
        viewModel.selectedStatLineProperty()
            .addListener((observable, oldValue, newValue) -> {
                updateCharacteristicsVisibility(newValue);

                if (newValue != null) {
                    statLineTypeComboBox.getSelectionModel().select(
                        capitalize(newValue.getType())
                    );
                }
            });

        // Display the appropriate section when the view initializes.
        updateCharacteristicsVisibility(viewModel.getSelectedStatLine());
    }

    private void bindIntegerField(
        TextField field,
        javafx.beans.property.IntegerProperty property
    ) {

        // Restrict input to non-negative whole numbers.
        field.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();

            if (newText.matches("\\d*")) {
                return change;
            }

            return null;
        }));

        // Update the ViewModel when the user edits the field.
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                property.set(0);
            } else {
                property.set(Integer.parseInt(newValue));
            }
        });

        // Update the field when the ViewModel changes.
        property.addListener((observable, oldValue, newValue) -> {
            String value = newValue.toString();

            if (!field.getText().equals(value)) {
                field.setText(value);
            }
        });

        // Initialize the field with the current ViewModel value.
        field.setText(Integer.toString(property.get()));
    }

    private void updateCharacteristicsVisibility(AbstractStatLine statLine) {

        boolean infantry = statLine != null
            && statLine.getType().equalsIgnoreCase("infantry");

        boolean vehicle = statLine != null
            && statLine.getType().equalsIgnoreCase("vehicle");

        boolean walker = statLine != null
            && statLine.getType().equalsIgnoreCase("walker");

        infantryCharacteristics.setVisible(infantry);
        infantryCharacteristics.setManaged(infantry);

        vehicleCharacteristics.setVisible(vehicle);
        vehicleCharacteristics.setManaged(vehicle);

        walkerCharacteristics.setVisible(walker);
        walkerCharacteristics.setManaged(walker);
    }

    private String capitalize(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }

        return value.substring(0, 1).toUpperCase(Locale.ROOT)
            + value.substring(1).toLowerCase(Locale.ROOT);
    }
    
    /**
     * handle creation of new StatLines
     */
    @FXML
    private void handleNew() {

        String selectedType = statLineTypeComboBox.getValue();

        if (selectedType == null) {
            viewModel.validationMessageProperty()
                .set("Select a StatLine type before creating a new entry.");
            return;
        }

        // Clear the current ListView selection before creating
        // the new, unsaved StatLine.
        existingStatLinesListView.getSelectionModel().clearSelection();

        viewModel.newStatLine(selectedType.toLowerCase(Locale.ROOT));
    }
    /**
     * Handle saving StatLines to the DB
     */
    @FXML
    private void handleSave() {

        if (viewModel.saveStatLine()) {
            // Select the saved StatLine in the ListView.
            existingStatLinesListView.getSelectionModel().select(
                viewModel.getSelectedStatLine()
            );
        }
    }
    /**
     * Handle deleting StatLines from the DB
     */
    @FXML
    private void handleDelete() {
        viewModel.deleteStatLine();
        existingStatLinesListView.getSelectionModel().clearSelection();
    }
}
