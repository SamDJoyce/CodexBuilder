package codexbuilder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
/**
 * This abstract class allows Hibernate to map sub-classes
 * and store them all in one table.
 * 
 * @author Sam Joyce
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "stat_line_type")
public abstract class AbstractStatLine implements StatLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AbstractStatLine() {}

    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
        return id;
    }
}