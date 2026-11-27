package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("replace_model")
public class ReplaceModelEffect extends AbstractEffect{

	private final String type = "replace_model";
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private ModelDescription newModel;
	
	@Column
	private ModelDescription oldModel;
	
	// Constructor
	
	public ReplaceModelEffect() {};
	
	public ReplaceModelEffect(String name) {
		this.name = name;
	}
	
	public ReplaceModelEffect(
			String name,
			ModelDescription newModel, 
			ModelDescription oldModel) {
		this.name = name;
		this.newModel = newModel;
		this.oldModel = oldModel;
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
	public ModelDescription getNewModel() {
		return newModel;
	}

	@Override
	public ModelDescription getOldModel() {
		return oldModel;
	}
	
	@Override
	public void setNewModel(ModelDescription newModel) {
		this.newModel = newModel;
	}
	
	@Override
	public void setOldModel(ModelDescription oldModel) {
		this.oldModel = oldModel;
	}
	
	// vvv Unused vvv
	
	@Override
	public WargearDescription getGear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGear(WargearDescription gear) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPointsPerModel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPointsPerModel(int pointsPerModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelDescription getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(ModelDescription model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNewName(String newName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnitType getUnitType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUnitType(UnitType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStat(String stat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setModifier(int modifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WargearDescription getRemove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRemove(WargearDescription remove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WargearDescription getAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdd(WargearDescription add) {
		// TODO Auto-generated method stub
		
	}


}
