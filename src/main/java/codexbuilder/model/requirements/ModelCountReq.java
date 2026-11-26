package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
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
    
	private final String type= "model_count";
	
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
	
    @Override
	public String getName() {
		return name;
	}
    
    @Override
	public void setName(String name) {
		this.name = name;
	}
	
    @Override
	public int getMinimum() {
		return minimum;
	}
    
    @Override
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
    @Override
	public int getMaximum() {
		return maximum;
	}
    
    @Override
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	@Override
	public String getType() {
		return type;
	}
    
    // vvv Unused vvv
    
	@Override
	public int getRate() {
		// Unused
		return 0;
	}

	@Override
	public void setRate(int rate) {
		// Unused
		
	}

	@Override
	public int getMaxSelection() {
		// Unused
		return 0;
	}

	@Override
	public void setMaxSelection(int maxSelection) {
		// Unused
		
	}

	@Override
	public int getLimit() {
		// Unused
		return 0;
	}

	@Override
	public void setLimit(int limit) {
		// Unused
		
	}

	@Override
	public WargearDescription getBlockingGear() {
		// Unused
		return null;
	}

	@Override
	public void setBlockingGear(WargearDescription blockingGear) {
		// Unused
		
	}

	@Override
	public WargearDescription getRequiredGear() {
		// Unused
		return null;
	}

	@Override
	public void setRequiredGear(WargearDescription requiredGear) {
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
	public UnitType getRequiredType() {
		// Unused
		return null;
	}

	@Override
	public void setRequiredType(UnitType type) {
		// Unused
		
	}
}
