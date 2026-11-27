package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("max_per_model_count")
public class MaxPerModelCountReq extends AbstractRequirement {

	@Column(nullable = false)
	private String name;
	
	@Column
	private int rate;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "count_model_id")
	@Transient
	private ModelDescription model;
	
	private final String type = "max_per_model_count";
	
	public MaxPerModelCountReq() {};
	
	public MaxPerModelCountReq(
			String name, int rate) {
		this.name = name;
		this.rate = rate;
	}
	
	public MaxPerModelCountReq(
			String name,
			ModelDescription model,
			int rate
			) {
		this.name = name;
		this.rate = rate;
		this.model = model;
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
	public void setModel(ModelDescription model) {
		this.model = model;
	}
	
	@Override
	public ModelDescription getModel() {
		return model;
	}
	
	@Override
	public int getRate () {
		return rate;
	}
	
	@Override
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	// vvv Unused vvv

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
	public int getMinimum() {
		// Unused
		return 0;
	}

	@Override
	public void setMinimum(int minimum) {
		// Unused
		
	}

	@Override
	public int getMaximum() {
		// Unused
		return 0;
	}

	@Override
	public void setMaximum(int maximum) {
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
	public UnitType getRequiredType() {
		// Unused
		return null;
	}

	@Override
	public void setRequiredType(UnitType type) {
		// Unused
		
	}



}
