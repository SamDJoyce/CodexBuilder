
package codexbuilder.viewmodel;

import codexbuilder.model.AbstractStatLine;
import codexbuilder.model.StatLine;
import codexbuilder.model.StatLineFactory;
import codexbuilder.repository.StatLineRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatLineViewModel {
	// Repository
    private final StatLineRepository statLineRepository;

    // List of StatLines
    private final ObservableList<AbstractStatLine> statLines =
            FXCollections.observableArrayList();

    // StatLine Properties
    private final ObjectProperty<AbstractStatLine> selectedStatLine =
            		new SimpleObjectProperty<>();

    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty type = new SimpleStringProperty("");
    private final StringProperty validationMessage =
            		new SimpleStringProperty("");

    private final IntegerProperty ws = new SimpleIntegerProperty();
    private final IntegerProperty bs = new SimpleIntegerProperty();
    private final IntegerProperty s  = new SimpleIntegerProperty();
    private final IntegerProperty t  = new SimpleIntegerProperty();
    private final IntegerProperty w  = new SimpleIntegerProperty();
    private final IntegerProperty i  = new SimpleIntegerProperty();
    private final IntegerProperty a  = new SimpleIntegerProperty();
    private final IntegerProperty ld = new SimpleIntegerProperty();
    private final IntegerProperty sv = new SimpleIntegerProperty();
    private final IntegerProperty front = new SimpleIntegerProperty();
    private final IntegerProperty side  = new SimpleIntegerProperty();
    private final IntegerProperty rear  = new SimpleIntegerProperty();

    // Constructor
    public StatLineViewModel(StatLineRepository statLineRepository) {
        this.statLineRepository = statLineRepository;
        loadStatLines();
    }

    // Methods
    
    /**
     * Load all StatLines from the StatLine Repository
     */
    public void loadStatLines() {
        statLines.setAll(statLineRepository.findAll());
    }

    public void selectStatLine(AbstractStatLine statLine) {

        selectedStatLine.set(statLine);
        validationMessage.set("");

        if (statLine == null) {
            clearFields();
            return;
        }

        name.set(statLine.getName());
        type.set(statLine.getType());

        ws.set(statLine.getWs());
        bs.set(statLine.getBs());
        s.set(statLine.getS());
        t.set(statLine.getT());
        w.set(statLine.getW());
        i.set(statLine.getI());
        a.set(statLine.getA());
        ld.set(statLine.getLd());
        sv.set(statLine.getSv());

        front.set(statLine.getFront());
        side.set(statLine.getSide());
        rear.set(statLine.getRear());
    }

    /**
     * Instance a new StatLine based on the user selected type.
     * @param type
     */
    public void newStatLine(String type) {

        if (type == null) {
            throw new IllegalArgumentException(
            		"Stat line type cannot be null.");
        }

        switch (type.toLowerCase()) {
            case "infantry":
            case "vehicle":
            case "walker":
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported stat line type: " + type
                );
        }

        StatLine newStatLine = StatLineFactory
        		.createStatLine(type.toLowerCase()
        );

        selectStatLine((AbstractStatLine) newStatLine);
    }

    public boolean saveStatLine() {

        if (selectedStatLine.get() == null) {
            validationMessage.set("Select or create a stat line first.");
            return false;
        }

        if (name.get() == null || name.get().isBlank()) {
            validationMessage.set("A stat line name is required.");
            return false;
        }

        AbstractStatLine statLine = selectedStatLine.get();

        applyFields(statLine);

        AbstractStatLine savedStatLine = statLineRepository.save(statLine);

        int existingIndex = -1;

        for (int index = 0; index < statLines.size(); index++) {
            if (statLines.get(index).getId().equals(savedStatLine.getId())) {
                existingIndex = index;
                break;
            }
        }

        if (existingIndex >= 0) {
            statLines.set(existingIndex, savedStatLine);
        } else {
            statLines.add(savedStatLine);
        }

        selectStatLine(savedStatLine);
        return true;
    }

    /**
     * Remove a statline from the StatLine Repository
     */
    public void deleteStatLine() {

        AbstractStatLine statLine = selectedStatLine.get();

        if (statLine == null) {
            return;
        }

        statLineRepository.delete(statLine);

        statLines.removeIf(
                existingStatLine ->
                        existingStatLine.getId().equals(statLine.getId())
        );

        selectStatLine(null);
    }

    private void applyFields(AbstractStatLine statLine) {

        statLine.setName(name.get());

        statLine.setWs(ws.get());
        statLine.setBs(bs.get());
        statLine.setS(s.get());
        statLine.setT(t.get());
        statLine.setW(w.get());
        statLine.setI(i.get());
        statLine.setA(a.get());
        statLine.setLd(ld.get());
        statLine.setSv(sv.get());

        statLine.setFront(front.get());
        statLine.setSide(side.get());
        statLine.setRear(rear.get());
    }

    /**
     * Clear all StatLine properties
     */
    private void clearFields() {

        name.set("");
        type.set("");

        ws.set(0);
        bs.set(0);
        s.set(0);
        t.set(0);
        w.set(0);
        i.set(0);
        a.set(0);
        ld.set(0);
        sv.set(0);

        front.set(0);
        side.set(0);
        rear.set(0);
    }

    public ObservableList<AbstractStatLine> getStatLines() {
        return statLines;
    }

    public ObjectProperty<AbstractStatLine> selectedStatLineProperty() {
        return selectedStatLine;
    }

    public AbstractStatLine getSelectedStatLine() {
        return selectedStatLine.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty validationMessageProperty() {
        return validationMessage;
    }

    public IntegerProperty wsProperty() {
        return ws;
    }

    public IntegerProperty bsProperty() {
        return bs;
    }

    public IntegerProperty sProperty() {
        return s;
    }

    public IntegerProperty tProperty() {
        return t;
    }

    public IntegerProperty wProperty() {
        return w;
    }

    public IntegerProperty iProperty() {
        return i;
    }

    public IntegerProperty aProperty() {
        return a;
    }

    public IntegerProperty ldProperty() {
        return ld;
    }

    public IntegerProperty svProperty() {
        return sv;
    }

    public IntegerProperty frontProperty() {
        return front;
    }

    public IntegerProperty sideProperty() {
        return side;
    }

    public IntegerProperty rearProperty() {
        return rear;
    }
}
