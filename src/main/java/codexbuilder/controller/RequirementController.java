package codexbuilder.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import codexbuilder.model.requirements.AbstractRequirement;
import codexbuilder.viewmodel.RequirementViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

/**
 * Controller for RequirementView.fxml.
 */
public class RequirementController {

    // Existing requirements
    @FXML private ListView<AbstractRequirement> existingRequirementsListView;

    // Common fields
    @FXML private TextField requirementNameField;
    @FXML private ComboBox<String> requirementTypeComboBox;

    // Type-specific fields
    @FXML private TextField limitField;
    @FXML private ComboBox<WargearDescription> blockingGearComboBox;
    @FXML private TextField rateField;
    @FXML private ComboBox<ModelDescription> modelComboBox;
    @FXML private TextField maxSelectionField;
    @FXML private TextField minimumField;
    @FXML private TextField maximumField;
    @FXML private ComboBox<WargearDescription> requiredGearComboBox;
    @FXML private ComboBox<UnitType> requiredTypeComboBox;

    // Rows shown only for applicable requirement subtypes
    @FXML private HBox limitRow;
    @FXML private HBox blockingGearRow;
    @FXML private HBox rateRow;
    @FXML private HBox modelRow;
    @FXML private HBox maxSelectionRow;
    @FXML private HBox modelCountRow;
    @FXML private HBox requiredGearRow;
    @FXML private HBox requiredTypeRow;

    // Feedback
    @FXML private Label errorLabel;

    private RequirementViewModel viewModel;

    private ObservableList<WargearDescription> availableWargear =
            FXCollections.observableArrayList();
    private final ObservableList<ModelDescription> availableModels =
            FXCollections.observableArrayList();

    /** Prevents programmatic control updates from being treated as user input. */
    private boolean updatingControls;
    private final Set<TextField> invalidNumericFields = new HashSet<>();

    private static final Map<String, String> REQUIREMENT_TYPE_LABELS =
            createRequirementTypeLabels();

    @FXML
    private void initialize() {
        requirementTypeComboBox.setItems(
                FXCollections.observableArrayList(REQUIREMENT_TYPE_LABELS.keySet()));
        requirementTypeComboBox.setConverter(createRequirementTypeConverter());

        blockingGearComboBox.setItems(availableWargear);
        requiredGearComboBox.setItems(availableWargear);
        modelComboBox.setItems(availableModels);

        configureComboBox(blockingGearComboBox, createWargearConverter());
        configureComboBox(requiredGearComboBox, createWargearConverter());
        configureComboBox(modelComboBox, createModelConverter());
        configureComboBox(requiredTypeComboBox, createUnitTypeConverter());

        requiredTypeComboBox.setItems(
                FXCollections.observableArrayList(UnitType.values()));

        configureIntegerField(limitField);
        configureIntegerField(rateField);
        configureIntegerField(maxSelectionField);
        configureIntegerField(minimumField);
        configureIntegerField(maximumField);

        existingRequirementsListView.setCellFactory(listView ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(AbstractRequirement requirement,
                                              boolean empty) {
                        super.updateItem(requirement, empty);
                        if (empty || requirement == null) {
                            setText(null);
                        } else {
                            String name = requirement.getName();
                            if (name == null || name.isBlank()) {
                                name = "(Unnamed requirement)";
                            }
                            setText(name + " — " + formatTypeLabel(requirement.getType()));
                        }
                    }
                });

        existingRequirementsListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldRequirement, newRequirement) -> {
                    if (updatingControls || viewModel == null) {
                        return;
                    }

                    updatingControls = true;
                    try {
                        viewModel.selectRequirement(newRequirement);
                    } finally {
                        updatingControls = false;
                    }
                    invalidNumericFields.clear();
                    updateFormFromViewModel();
                });

        showRowsFor(null);
    }

    /**
     * Supplies the reference entities used by the requirement ComboBoxes.
     * Call this from the parent controller after loading the model and wargear
     * records from their respective repositories.
     */
    public void setReferenceData(
            ObservableList<WargearDescription> wargear,
            Collection<ModelDescription> models) {

        availableWargear = wargear == null
                ? FXCollections.observableArrayList()
                : wargear;

        availableModels.setAll(models == null
                ? Collections.emptyList() : models);

        blockingGearComboBox.setItems(availableWargear);
        requiredGearComboBox.setItems(availableWargear);

        if (viewModel != null) {
            updateFormFromViewModel();
        }
    }

    /** Connects the view to its ViewModel. */
    public void setViewModel(RequirementViewModel viewModel) {
        if (viewModel == null) {
            throw new IllegalArgumentException("RequirementViewModel cannot be null.");
        }
        if (this.viewModel != null) {
            throw new IllegalStateException("RequirementViewModel has already been set.");
        }

        this.viewModel = viewModel;

        existingRequirementsListView.setItems(viewModel.getRequirements());

        requirementNameField.textProperty()
                .bindBidirectional(viewModel.nameProperty());
        requirementTypeComboBox.valueProperty()
                .bindBidirectional(viewModel.typeProperty());

        blockingGearComboBox.valueProperty()
                .bindBidirectional(viewModel.blockingGearProperty());
        requiredGearComboBox.valueProperty()
                .bindBidirectional(viewModel.requiredGearProperty());
        modelComboBox.valueProperty()
                .bindBidirectional(viewModel.modelProperty());
        requiredTypeComboBox.valueProperty()
                .bindBidirectional(viewModel.unitTypeProperty());

        errorLabel.textProperty()
                .bind(viewModel.validationMessageProperty());

        updateFormFromViewModel();
    }

    /** Creates a new requirement after the user chooses its subtype. */
    @FXML
    private void handleRequirementTypeChanged() {
        if (updatingControls || viewModel == null) {
            return;
        }

        String selectedType = requirementTypeComboBox.getValue();
        if (selectedType == null || selectedType.isBlank()) {
            showRowsFor(null);
            return;
        }

        try {
            invalidNumericFields.clear();
            viewModel.validationMessageProperty().set("");
            // The ViewModel currently exposes this factory-backed operation
            // under the name newStatLine().
            viewModel.newStatLine(selectedType);
            updateFormFromViewModel();
        } catch (IllegalArgumentException ex) {
            viewModel.validationMessageProperty().set(ex.getMessage());
        }
    }

    @FXML
    private void handleNew() {
        if (viewModel == null) {
            return;
        }

        updatingControls = true;
        try {
            existingRequirementsListView.getSelectionModel().clearSelection();
            viewModel.selectRequirement(null);
        } finally {
            updatingControls = false;
        }

        invalidNumericFields.clear();
        updateFormFromViewModel();
        requirementNameField.requestFocus();
    }

    @FXML
    private void handleSave() {
        if (viewModel == null) {
            return;
        }
        if (!invalidNumericFields.isEmpty()) {
            viewModel.validationMessageProperty().set(
                    "Enter a valid whole number in each numeric field.");
            return;
        }

        if (viewModel.saveRequirement()) {
            invalidNumericFields.clear();
            AbstractRequirement savedRequirement =
                    viewModel.getSelectedRequirement();
            if (savedRequirement != null) {
                existingRequirementsListView.getSelectionModel()
                        .select(savedRequirement);
                existingRequirementsListView.scrollTo(savedRequirement);
            }
            updateFormFromViewModel();
        }
    }

    @FXML
    private void handleDelete() {
        if (viewModel == null) {
            return;
        }

        invalidNumericFields.clear();
        updatingControls = true;
        try {
            viewModel.deleteRequirement();
            existingRequirementsListView.getSelectionModel().clearSelection();
        } finally {
            updatingControls = false;
        }
        updateFormFromViewModel();
    }

    /** Refreshes fields and the subtype-specific parameter rows. */
    private void updateFormFromViewModel() {
        if (viewModel == null) {
            showRowsFor(null);
            return;
        }

        updatingControls = true;
        try {
            limitField.setText(Integer.toString(viewModel.limitProperty().get()));
            rateField.setText(Integer.toString(viewModel.rateProperty().get()));
            maxSelectionField.setText(
                    Integer.toString(viewModel.maxSelectionProperty().get()));
            minimumField.setText(Integer.toString(viewModel.minimumProperty().get()));
            maximumField.setText(Integer.toString(viewModel.maximumProperty().get()));

            AbstractRequirement selected = viewModel.getSelectedRequirement();
            showRowsFor(selected == null ? null : selected.getType());
        } finally {
            updatingControls = false;
        }
    }

    /** Shows only the parameter controls relevant to the selected subtype. */
    private void showRowsFor(String type) {
        hideAllParameterRows();
        if (type == null) {
            return;
        }

        switch (type.toLowerCase()) {
            case "armoury_points_limit" -> setRowVisible(limitRow, true);
            case "cannot_have_gear" -> setRowVisible(blockingGearRow, true);
            case "max_per_model_count" -> {
                setRowVisible(rateRow, true);
                setRowVisible(modelRow, true);
            }
            case "max_selection" -> setRowVisible(maxSelectionRow, true);
            case "model_count" -> setRowVisible(modelCountRow, true);
            case "must_have_gear", "must_start_with_gear" ->
                    setRowVisible(requiredGearRow, true);
            case "must_have_type" -> setRowVisible(requiredTypeRow, true);
            case "armoury_weapon_limit", "characters_only" -> {
                // These requirement types have no additional parameters.
            }
            default -> {
                // Unknown types intentionally leave all optional rows hidden.
            }
        }
    }

    private void hideAllParameterRows() {
        setRowVisible(limitRow, false);
        setRowVisible(blockingGearRow, false);
        setRowVisible(rateRow, false);
        setRowVisible(modelRow, false);
        setRowVisible(maxSelectionRow, false);
        setRowVisible(modelCountRow, false);
        setRowVisible(requiredGearRow, false);
        setRowVisible(requiredTypeRow, false);
    }

    private void setRowVisible(HBox row, boolean visible) {
        row.setVisible(visible);
        row.setManaged(visible);
    }

    /** Restricts integer fields to digits and writes valid edits to the ViewModel. */
    private void configureIntegerField(TextField field) {
        field.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (updatingControls || viewModel == null) {
                return;
            }

            // Empty fields represent zero until the user enters a value.
            int value = 0;
            if (newValue != null && !newValue.isBlank()) {
                try {
                    value = Integer.parseInt(newValue);
                } catch (NumberFormatException ex) {
                    invalidNumericFields.add(field);
                    viewModel.validationMessageProperty().set(
                            "Enter a valid whole number.");
                    return;
                }
            }

            invalidNumericFields.remove(field);
            if (invalidNumericFields.isEmpty()) {
                viewModel.validationMessageProperty().set("");
            }

            if (field == limitField) {
                viewModel.limitProperty().set(value);
            } else if (field == rateField) {
                viewModel.rateProperty().set(value);
            } else if (field == maxSelectionField) {
                viewModel.maxSelectionProperty().set(value);
            } else if (field == minimumField) {
                viewModel.minimumProperty().set(value);
            } else if (field == maximumField) {
                viewModel.maximumProperty().set(value);
            }
        });
    }

    private static <T> void configureComboBox(ComboBox<T> comboBox,
                                              StringConverter<T> converter) {
        comboBox.setConverter(converter);
        comboBox.setButtonCell(new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : converter.toString(item));
            }
        });
        comboBox.setCellFactory(listView -> new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : converter.toString(item));
            }
        });
    }

    private static StringConverter<String> createRequirementTypeConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(String type) {
                return type == null ? "" : REQUIREMENT_TYPE_LABELS.getOrDefault(
                        type, formatTypeLabel(type));
            }

            @Override
            public String fromString(String label) {
                if (label == null) {
                    return null;
                }
                return REQUIREMENT_TYPE_LABELS.entrySet().stream()
                        .filter(entry -> entry.getValue().equalsIgnoreCase(label))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(label);
            }
        };
    }

    private static StringConverter<WargearDescription> createWargearConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(WargearDescription gear) {
                return gear == null ? "" : gear.toString();
            }

            @Override
            public WargearDescription fromString(String value) {
                return null; // These ComboBoxes are not editable.
            }
        };
    }

    private static StringConverter<ModelDescription> createModelConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(ModelDescription model) {
                return model == null ? "" : model.getName();
            }

            @Override
            public ModelDescription fromString(String value) {
                return null; // This ComboBox is not editable.
            }
        };
    }

    private static StringConverter<UnitType> createUnitTypeConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(UnitType type) {
                return type == null ? "" : formatTypeLabel(type.name());
            }

            @Override
            public UnitType fromString(String value) {
                if (value == null) {
                    return null;
                }
                return UnitType.valueOf(value.trim().toUpperCase().replace(' ', '_'));
            }
        };
    }

    private static Map<String, String> createRequirementTypeLabels() {
        Map<String, String> labels = new LinkedHashMap<>();
        labels.put("armoury_points_limit", "Armoury Points Limit");
        labels.put("armoury_weapon_limit", "Armoury Weapon Limit");
        labels.put("cannot_have_gear", "Cannot Have Gear");
        labels.put("characters_only", "Characters Only");
        labels.put("max_per_model_count", "Maximum Per Model Count");
        labels.put("max_selection", "Maximum Selection");
        labels.put("model_count", "Model Count");
        labels.put("must_have_gear", "Must Have Gear");
        labels.put("must_have_type", "Must Have Type");
        labels.put("must_start_with_gear", "Must Start With Gear");
        return Collections.unmodifiableMap(labels);
    }

    private static String formatTypeLabel(String type) {
        if (type == null || type.isBlank()) {
            return "";
        }

        String[] words = type.toLowerCase().replace('_', ' ').split("\\s+");
        StringBuilder label = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            if (label.length() > 0) {
                label.append(' ');
            }
            label.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1));
        }
        return label.toString();
    }
}
