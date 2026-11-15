
package codexbuilder.controller;

import org.hibernate.SessionFactory;

import codexbuilder.config.HibernateUtil;
import codexbuilder.repository.StatLineRepository;
import codexbuilder.repository.WargearRepository;
import codexbuilder.viewmodel.StatLineViewModel;
import codexbuilder.viewmodel.WargearViewModel;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private WargearController wargearViewController;
    @FXML
    private StatLineController statLineViewController;
//    @FXML
//    private ModelController modelController;
//    @FXML
//    private UnitController unitController;
//    @FXML
//    private GroupController groupController;
//    @FXML 
//    private OptionController optionController;
//    @FXML
//    private RequirementController requirementController;
//    @FXML
//    private EffectController effectController;
    

    
    private SessionFactory sessionFactory;

    /**
     * Initializes the main application view and its tab views.
     */
    @FXML
    private void initialize() {
       sessionFactory = HibernateUtil.getSessionFactory();
       initializeWargear();
       initializeStatLines();
       //initializeModels();
       //initializeUnits();
       //initializeGroups();
       //initializeOptions();
       //initializeRequirements();
       //initializeEffects();
    }
    /**
     * Sets up and connects the wargear repository and view model.
     */
    private void initializeWargear() {
        // Create the Wargear repository.
        WargearRepository wargearRepository =
                new WargearRepository(sessionFactory);
        // Create the Wargear ViewModel.
        WargearViewModel wargearViewModel =
                new WargearViewModel(wargearRepository);
        // Connect the ViewModel to the Wargear controller.
        wargearViewController.setViewModel(wargearViewModel);
    }
    /**
     * Sets up and connects the StatLine repository and view model
     */
    private void initializeStatLines() {
    	// Create the StatLine repository
        StatLineRepository statLineRepository =
            new StatLineRepository(sessionFactory);
        // Create the StatLine viewmodel
        StatLineViewModel statLineViewModel =
            new StatLineViewModel(statLineRepository);
        // Connect the viewmodel to the StatLine controller
        statLineViewController.setViewModel(statLineViewModel);
    }
    /**
     * Sets up and connects the model repository and view model
     */
    private void initializeModels() {
    	// TODO
    }
    /**
     * Sets up and connects the Unit repository and view model
     */
    private void initializeUnits() {
    	// TODO
    }
    /**
     * Sets up and connects the Option Group repository and view model
     */
    private void initializeGroups() {
    	// TODO
    }
    /**
     * Sets up and connects the Options repository and view model
     */
    private void initializeOptions() {
    	// TODO
    }
    /**
     * Sets up and connects the Requirements repository and view model
     */
    private void initializeRequirements() {
    	// TODO
    }
    /**
     * Sets up and connects the Effects repository and view model
     */
    private void initializeEffects() {
    	// TODO
    }
}
