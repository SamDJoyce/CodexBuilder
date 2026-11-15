
package codexbuilder.controller;

import codexbuilder.config.HibernateUtil;
import codexbuilder.repository.WargearRepository;
import codexbuilder.viewmodel.WargearViewModel;

import javafx.fxml.FXML;

public class MainController {

    @FXML
    private WargearController wargearViewController;

    /**
     * Initializes the main application view and its tab views.
     */
    @FXML
    private void initialize() {
       initializeWargear();
       initializeStatLines();
       //initializeModels();
       //initializeUnits();
       //initializeGroups();
       //initializeOptions();
       //initializeRequirements();
       //initializeEffects();
    }
    
    private void initializeWargear() {
        // Create the Wargear repository.
        WargearRepository wargearRepository =
                new WargearRepository(
                        HibernateUtil.getSessionFactory());

        // Create the Wargear ViewModel.
        WargearViewModel wargearViewModel =
                new WargearViewModel(wargearRepository);

        // Connect the ViewModel to the Wargear controller.
        wargearViewController.setViewModel(wargearViewModel);
    }
    
    private void initializeStatLines() {
    	// TODO
    }
    
    private void initializeModels() {
    	// TODO
    }
    
    private void initializeUnits() {
    	// TODO
    }
    
    private void initializeGroups() {
    	// TODO
    }
    
    private void initializeOptions() {
    	// TODO
    }
    
    private void initializeRequirements() {
    	// TODO
    }
    
    private void initializeEffects() {
    	// TODO
    }
}
