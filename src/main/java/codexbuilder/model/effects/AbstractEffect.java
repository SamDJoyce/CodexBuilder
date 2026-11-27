package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * This abstract class allows hibernate to map Effect
 * sub-classes and store them all in one table.
 * 
 * @author Sam Joyce
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "effect_type")
public abstract class AbstractEffect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	protected AbstractEffect() {};
	
	public void setid(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	
	// Abstract Effect methods
	public abstract String getName();
	public abstract void setName(String name);
	
	public abstract String getType();
	
	public abstract WargearDescription getGear();
	public abstract void setGear(WargearDescription gear);
	
	public abstract int getPointsPerModel();
	public abstract void setPointsPerModel(int pointsPerModel);
	
	public abstract ModelDescription getModel();
	public abstract void setModel(ModelDescription model);
	
	public abstract String getNewName();
	public abstract void setNewName(String newName);
	
	public abstract UnitType getUnitType();
	public abstract void setUnitType(UnitType type);
	
	public abstract String getStat();
	public abstract void setStat(String stat);
	
	public abstract int getModifier();
	public abstract void setModifier(int modifier);
	
	public abstract WargearDescription getRemove();
	public abstract void setRemove(WargearDescription remove);
	
	public abstract ModelDescription getNewModel();
	public abstract void setNewModel(ModelDescription newModel);
	
	public abstract ModelDescription getOldModel();
	public abstract void setOldModel(ModelDescription oldModel);
	
	public abstract WargearDescription getAdd();
	public abstract void setAdd (WargearDescription add);
	
}
