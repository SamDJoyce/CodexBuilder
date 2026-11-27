package codexbuilder.model.requirements;

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
 * This abstract class allows hibernate to map Requirement 
 * sub-classes and store them all in one table.
 * 
 * @author Sam Joyce
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "requirement_type")
public abstract class AbstractRequirement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	protected AbstractRequirement() {}
	
	public void setid(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	
	public abstract String getName();
	public abstract void   setName(String name);
	
	public abstract String getType();
	
	public abstract int	 getRate();
	public abstract void setRate(int rate);
	
	public abstract int	 getMaxSelection();
	public abstract void setMaxSelection(int maxSelection);
	
	public abstract int	 getMinimum();
	public abstract void setMinimum(int minimum);
	
	public abstract int	 getMaximum();
	public abstract void setMaximum(int maximum);
	
	public abstract int	 getLimit();
	public abstract void setLimit(int limit);
	
	public abstract WargearDescription	
						 getBlockingGear();
	public abstract void setBlockingGear
					(WargearDescription blockingGear);
	
	public abstract WargearDescription	
						 getRequiredGear();
	public abstract void setRequiredGear
					(WargearDescription requiredGear);
	
	public abstract ModelDescription
						 getModel();
	public abstract void setModel
					(ModelDescription model);
	
	public abstract UnitType getRequiredType();
	public abstract void setRequiredType(UnitType type);
}
