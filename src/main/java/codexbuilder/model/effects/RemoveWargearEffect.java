package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("remove_wargear")
public class RemoveWargearEffect extends AbstractEffect{
		
	private final String type = "remove_wargear";
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private WargearDescription remove;
	
	// Constructor
	public RemoveWargearEffect() {};
	
	public RemoveWargearEffect(
			String name,
			WargearDescription remove) {
		this.name = name;
		this.remove = remove;
	}
	
	public RemoveWargearEffect(
			String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public WargearDescription getRemove() {
		return remove;
	}
	
	@Override
	public void setRemove(WargearDescription remove) {
		this.remove = remove;
	}

	// vvv Unused vvv
	
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
	public UnitType getUnitType() {
		// Unused
		return null;
	}

	@Override
	public void setUnitType(UnitType type) {
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
