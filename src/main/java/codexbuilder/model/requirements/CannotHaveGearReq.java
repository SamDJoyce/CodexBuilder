package codexbuilder.model.requirements;

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
@DiscriminatorValue("cannot_have_gear")
public class CannotHaveGearReq extends AbstractRequirement{
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blocking_gear_id")
	private WargearDescription blockingGear;
	
	private final String type = "cannot_have_gear";
	
	public CannotHaveGearReq() {};
	
	public CannotHaveGearReq(String name) {
		this.name = name;
	}
	
	public CannotHaveGearReq(String name, WargearDescription blockingGear) {
		this.name = name;
		this.blockingGear = blockingGear;
	}
	
	@Override
	public WargearDescription getBlockingGear() {
		return blockingGear;
	}

	@Override
	public void setBlockingGear(WargearDescription blockingGear) {
		this.blockingGear = blockingGear;
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
