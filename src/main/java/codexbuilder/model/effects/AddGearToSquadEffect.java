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
@DiscriminatorValue("add_gear_to_squad")
public class AddGearToSquadEffect extends AbstractEffect{

	private final String type = "add_gear_to_squad";
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gear_id")
	private WargearDescription gear;
	
	@Column
	private int pointsPerModel;
	
	// Constructor
	public AddGearToSquadEffect() {};
	
	public AddGearToSquadEffect(String name) {
		this.name = name;
	};
	
	public AddGearToSquadEffect(
			String name,
			WargearDescription gear,
			int pointsPerModel) {
		this.name = name;
		this.gear = gear;
		this.pointsPerModel = pointsPerModel;
		
		
	};
	
	@Override
	public WargearDescription getGear() {
		return gear;
	}
	
	@Override
	public void setGear(WargearDescription gear) {
		this.gear = gear;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public void setPointsPerModel(int points) {
		this.pointsPerModel = points;
	}
	
	@Override
	public int getPointsPerModel() {
		return pointsPerModel;
	}

	// Unused Methods
	
	@Override
	public void setName(String name) {
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
