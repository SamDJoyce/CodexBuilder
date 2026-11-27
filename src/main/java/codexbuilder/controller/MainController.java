
package codexbuilder.controller;

import java.util.Collections;

import org.hibernate.SessionFactory;

import codexbuilder.config.HibernateUtil;
import codexbuilder.repository.RequirementRepository;
import codexbuilder.repository.StatLineRepository;
import codexbuilder.repository.WargearRepository;
import codexbuilder.viewmodel.RequirementViewModel;
import codexbuilder.viewmodel.StatLineViewModel;
import codexbuilder.viewmodel.WargearViewModel;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private WargearController wargearViewController;
    @FXML
    private StatLineController statLineViewController;
//    @FXML
//    private ModelController modelViewController;
//    @FXML
//    private UnitController unitViewController;
//    @FXML
//    private GroupController groupViewController;
//    @FXML 
//    private OptionController optionViewController;
    @FXML
    private RequirementController requirementViewController;
//    @FXML
//    private EffectController effectViewController;
    

    
    private SessionFactory sessionFactory;
    
    private WargearRepository     	wargearRepository;
    private StatLineRepository 	  	statRepository;
    private RequirementRepository 	reqRepository;
    
    private WargearViewModel 	 	wargearViewModel;
    private StatLineViewModel 	 	statViewModel;
    private RequirementViewModel	reqViewModel;
    
    /**
     * Initializes the main application view and its tab views.
     */
    @FXML
    private void initialize() {
       sessionFactory = HibernateUtil.getSessionFactory();
       initializeWargear();
       initializeStatLines();
       initializeRequirements();
       //initializeModels();
       //initializeUnits();
       //initializeGroups();
       //initializeOptions();
       //initializeEffects();
    }
    
    /**
     * Sets up and connects the wargear repository and view model.
     */
    private void initializeWargear() {
        // Create the Wargear repository.
        wargearRepository =
                new WargearRepository(sessionFactory);
        // Create the Wargear ViewModel.
        wargearViewModel =
                new WargearViewModel(wargearRepository);
        // Connect the ViewModel to the Wargear controller.
        wargearViewController.setViewModel(wargearViewModel);
    }
    
    /**
     * Sets up and connects the StatLine repository and view model
     */
    private void initializeStatLines() {
    	// Create the StatLine repository
        statRepository =
            new StatLineRepository(sessionFactory);
        // Create the StatLine viewmodel
        statViewModel =
            new StatLineViewModel(statRepository);
        // Connect the viewmodel to the StatLine controller
        statLineViewController.setViewModel(statViewModel);
    }
    
    /**
     * Sets up and connects the Requirements repository and view model
     */
    private void initializeRequirements() {
    	// Create the Requirement repository
        reqRepository =
                new RequirementRepository(sessionFactory);

        reqViewModel =
                new RequirementViewModel(reqRepository);

        requirementViewController.setViewModel(reqViewModel);

        requirementViewController.setReferenceData(
                wargearViewModel.getWargearItems(),
                Collections.emptyList()
        );
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
     * Sets up and connects the Effects repository and view model
     */
    private void initializeEffects() {
    	// TODO
    }
}
