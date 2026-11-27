package codexbuilder.viewmodel;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import codexbuilder.model.requirements.AbstractRequirement;
import codexbuilder.model.requirements.ReqFactory;
import codexbuilder.repository.RequirementRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RequirementViewModel {
	
	// Repository
	private final RequirementRepository reqRepo;
	
	// List of Existing Requirements
	private final ObservableList<AbstractRequirement> requirements = 
					FXCollections.observableArrayList();
	
	// Requirement Properties
	private final StringProperty name 			   = new SimpleStringProperty();
	private final StringProperty type			   = new SimpleStringProperty();
    private final StringProperty validationMessage = new SimpleStringProperty();
	
	private final IntegerProperty rate 			= new SimpleIntegerProperty();
	private final IntegerProperty maxSelection 	= new SimpleIntegerProperty();
	private final IntegerProperty maximum 		= new SimpleIntegerProperty();
	private final IntegerProperty minimum 		= new SimpleIntegerProperty();
	private final IntegerProperty limit 		= new SimpleIntegerProperty();
	
	private final ObjectProperty<WargearDescription>  blockingGear =
					new SimpleObjectProperty<>();
	private final ObjectProperty<WargearDescription>  requiredGear = 
					new SimpleObjectProperty<>();
	private final ObjectProperty<ModelDescription>    model = 
					new SimpleObjectProperty<>();
	private final ObjectProperty<UnitType> 			  requiredType = 
					new SimpleObjectProperty<>();
	
	// Selected Requirement
	private final ObjectProperty<AbstractRequirement> selectedReq =
					new SimpleObjectProperty<>();
	
	// Constructor
	public RequirementViewModel(RequirementRepository reqRepo) {
		this.reqRepo = reqRepo;
		loadRequirements();
	}
	
	// Methods
	
	/**
	 * Load all Requirements from the Requirement Repository
	 */
	public void loadRequirements() {
		requirements.setAll(reqRepo.findAll());
	}
	
	/**
	 * Set the currently selected Requirement and
	 * assign its characteristics to the ViewModel
	 * properties.
	 * 
	 * @param req	The Requirement to be selected
	 */
	public void selectRequirement(AbstractRequirement req) {
		selectedReq.set(req);
		validationMessage.set("");
		
		if (req == null) {
			clearFields();
			return;
		}
		
		name.set(req.getName());
		type.set(req.getType());
		
		rate.set(req.getRate());
		maxSelection.set(req.getMaxSelection());
		maximum.set(req.getMaximum());
		minimum.set(req.getMinimum());
		limit.set(req.getLimit());
		
        blockingGear.set(req.getBlockingGear());
        requiredGear.set(req.getRequiredGear());
        model.set(req.getModel());
        requiredType.set(req.getRequiredType());
	}
	
	/**
	 * Instance a new Requirement based on user selected type
	 * @param type	Variety of Requirement to be created.
	 */
	public void newStatLine(String type) {
		
        if (type == null) {
            throw new IllegalArgumentException(
            		"Requirement type cannot be null.");
        }
        
        switch (type.toLowerCase()) {
        	case "armoury_points_limit":
        	case "armoury_weapon_limit":
        	case "cannot_have_gear":
        	case "characters_only":
        	case "max_per_model_count":
        	case "max_selection":
        	case "model_count":
        	case "must_have_gear":
        	case "must_have_type":
        	case "must_start_with_gear":
        		break;
	    	 default:
	             throw new IllegalArgumentException(
	                     "Unsupported Requirement type: " + type
	             );
        }
        
        AbstractRequirement newRequirement = ReqFactory
        		.create(type.toLowerCase());
        selectRequirement(newRequirement);
	}
	
	/**
	 * Save a Requirement to the Requirement Repository
	 * @return	The requirement as it was saved in the repo
	 */
	public boolean saveRequirement() {
		// Validate there is a Requirement present
		if (selectedReq.get() == null) {
            validationMessage.set("Select or create a Requirement first.");
            return false;
        }
        // Validate a name is present
        if (name.get() == null || name.get().isBlank()) {
            validationMessage.set("A Requirement name is required.");
            return false;
        }
        // Save the Requirement to the repository
        AbstractRequirement req = selectedReq.get();
        applyFields(req);
        AbstractRequirement savedReq = reqRepo.save(req);
        
        selectRequirement(savedReq);
        
        int existingIndex = -1;
        
        for (int i = 0; i < requirements.size(); i ++) {
        	if (requirements
        			.get(i)
        			.getId()
        			.equals(savedReq
        					.getId())
        	){
        		existingIndex = i;
        		break;
        	}
        }
        
        if (existingIndex >= 0) {
        	requirements.set(existingIndex, savedReq);
        } else {
        	requirements.add(savedReq);
        }
        
        
        return true;
	}
	
	/**
	 * Remove a Requirement from the
	 * Requirement Repository
	 */
	public void deleteRequirement() {
		AbstractRequirement req = selectedReq.get();
		
		if (req == null) {
			return;
		}
		
		reqRepo.delete(req);
		
		requirements.removeIf(
				existingReq -> 
					existingReq
						.getId()
						.equals(req.getId())
		);
		// Set selection to null
		selectRequirement(null);
	}
	
	/**
	 * Apply values entered in form fields to 
	 * the selected Requirement.
	 * @param req	The Requirement to be modified
	 */
	private void applyFields(AbstractRequirement req) {
		req.setName(name.get());
		req.setRate(rate.get());
		req.setMaxSelection(maxSelection.get());
		req.setMaximum(maximum.get());
		req.setMinimum(minimum.get());
		req.setModel(model.get());
		req.setLimit(limit.get());
		req.setBlockingGear(blockingGear.get());
		req.setRequiredGear(requiredGear.get());
		req.setRequiredType(requiredType.get());
	}
	
	/**
	 * Clear all Requirement properties
	 */
	private void clearFields() {
		name.set("");
		
		rate.set(0);
		maxSelection.set(0);
		maximum.set(0);
		minimum.set(0);
		limit.set(0);
		
		blockingGear.set(null);
		requiredGear.set(null);
		model.set(null);
		requiredType.set(null);
		type.set(null);
	}
	
	// Getters
	
	public ObservableList<AbstractRequirement> getRequirements(){
		return requirements;
	}
	
	public AbstractRequirement getSelectedRequirement() {
		return selectedReq.get();
	}
	
    public StringProperty nameProperty() {
        return name;
    }
    
    public IntegerProperty rateProperty() {
    	return rate;
    }
    
    public IntegerProperty maxSelectionProperty() {
    	return maxSelection;
    }
    
    public IntegerProperty maximumProperty() {
    	return maximum;
    }
    
    public IntegerProperty minimumProperty() {
    	return minimum;
    }
    
    public IntegerProperty limitProperty() {
    	return limit;
    }
    
    public ObjectProperty<WargearDescription> blockingGearProperty() {
    	return blockingGear;
    }
    
    public ObjectProperty<WargearDescription> requiredGearProperty() {
    	return requiredGear;
    }
    
    public ObjectProperty<ModelDescription> modelProperty() {
    	return model;
    }
    
    public ObjectProperty<UnitType> unitTypeProperty() {
    	return requiredType;
    }
    
    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty validationMessageProperty() {
        return validationMessage;
    }
}
