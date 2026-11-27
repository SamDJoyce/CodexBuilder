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
@DiscriminatorValue("replace_wargear")
public class ReplaceWargearEffect extends AbstractEffect{

	private final String type = "replace_wargear";
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "remove_id")
	private WargearDescription remove;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "add_id")
	private WargearDescription add;
	
	// Constructor
	public ReplaceWargearEffect() {};
	
	public ReplaceWargearEffect(String name) {
		this.name = name;
	}
	
	public ReplaceWargearEffect(
			String name,
			WargearDescription remove,
			WargearDescription add) {
		this.name = name;
		this.remove = remove;
		this.add = add;
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
	public WargearDescription getRemove() {
		return remove;
	}
	
	@Override
	public void setRemove(WargearDescription remove) {
		this.remove = remove;
	}

	@Override
	public WargearDescription getAdd() {
		return add;
	}

	@Override
	public void setAdd(WargearDescription add) {
		this.add = add;
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

}
