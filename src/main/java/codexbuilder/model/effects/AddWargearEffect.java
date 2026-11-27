package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("add_wargear")
public class AddWargearEffect extends AbstractEffect{

	private final String type = "add_wargear";
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gear_id")
	private WargearDescription gear;
	
	// Constructor
	public AddWargearEffect() {};
	
	public AddWargearEffect(String name) {
		this.name = name;
	}
	
	public AddWargearEffect(String name, WargearDescription gear) {
		this.name = name;
		this.gear = gear;
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
	public WargearDescription getGear() {
		return gear;
	}
	
	@Override
	public void setGear(WargearDescription gear) {
		this.gear = gear;
	}

	// vvv Unused vvv

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
