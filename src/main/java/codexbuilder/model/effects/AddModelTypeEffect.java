package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("add_model_type")
public class AddModelTypeEffect  extends AbstractEffect{

	private final String type = "add_model_type";
	
	@Column(nullable = false)
	private String   name;
	
	@Column
	@Enumerated(EnumType.STRING)
	private UnitType unitType;
	
	// Constructor
	public AddModelTypeEffect() {};
	
	public AddModelTypeEffect(String name) {
		this.name = name;
	}
	
	public AddModelTypeEffect(
			String name,
			UnitType unitType
			) {
		this.name = name;
		this.unitType = unitType;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public UnitType getUnitType() {
		return unitType;
	}
	@Override
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	// Unused
	
	@Override
	public WargearDescription getGear() {
		// Unused 
		return null;
	}

	@Override
	public void setGear(WargearDescription gear) {
		// Unused 
		
	}

	@Override
	public int getPointsPerModel() {
		// Unused 
		return 0;
	}

	@Override
	public void setPointsPerModel(int pointsPerModel) {
		// Unused 
		
	}

	@Override
	public ModelDescription getModel() {
		// Unused 
		return null;
	}

	@Override
	public void setModel(ModelDescription model) {
		// Unused 
		
	}

	@Override
	public String getNewName() {
		// Unused 
		return null;
	}

	@Override
	public void setNewName(String newName) {
		// Unused 
		
	}

	@Override
	public String getStat() {
		// Unused 
		return null;
	}

	@Override
	public void setStat(String stat) {
		// Unused 
		
	}

	@Override
	public int getModifier() {
		// Unused 
		return 0;
	}

	@Override
	public void setModifier(int modifier) {
		// Unused 
		
	}

	@Override
	public WargearDescription getRemove() {
		// Unused 
		return null;
	}

	@Override
	public void setRemove(WargearDescription remove) {
		// Unused 
		
	}

	@Override
	public ModelDescription getNewModel() {
		// Unused 
		return null;
	}

	@Override
	public void setNewModel(ModelDescription newModel) {
		// Unused 
		
	}

	@Override
	public ModelDescription getOldModel() {
		// Unused 
		return null;
	}

	@Override
	public void setOldModel(ModelDescription oldModel) {
		// Unused 
		
	}

	@Override
	public WargearDescription getAdd() {
		// Unused 
		return null;
	}

	@Override
	public void setAdd(WargearDescription add) {
		// Unused 
		
	}

}
