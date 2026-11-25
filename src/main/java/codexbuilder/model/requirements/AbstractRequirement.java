package codexbuilder.model.requirements;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
/**
 * This abstract class allows hibernate to map sub-classes
 * and store them all in one table.
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
}
