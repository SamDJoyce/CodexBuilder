package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("modify_stat")
public class ModifyStatEffect extends AbstractEffect{
	
	private final String type = "modify_stat";
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String stat;
	
	@Column
	private int    modifier;
	
	// Constructor
	public ModifyStatEffect() {};
	
	public ModifyStatEffect(String name) {
		this.name = name;
	}
	
	public ModifyStatEffect(String name, String stat, int modifier) {
		this.name = name;
		this.stat = stat;
		this.modifier = modifier;
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
		return this.type;
	}
	
	@Override
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	@Override
	public String getStat() {
		return this.stat;
	}
	
	@Override
	public int getModifier() {
		return this.modifier;
	}
	
	@Override
	public void setModifier(int modifier) {
		this.modifier = modifier;
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
