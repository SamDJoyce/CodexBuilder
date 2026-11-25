package codexbuilder.model.requirements;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("model_count")
public class ModelCountReq extends AbstractRequirement {

	@Column(nullable = false)
	private String name;
	@Column
    private int minimum;
	@Column
    private int maximum;
	@Column
    private String message;
    
    public ModelCountReq() {};
    
    public ModelCountReq(
    		String name,
    		int minimum,
    		int maximum
    		) {
    	this.name = name;
    	this.minimum = minimum;
    	this.maximum = maximum;
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
