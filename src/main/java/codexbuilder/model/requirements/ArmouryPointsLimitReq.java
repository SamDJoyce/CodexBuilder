package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armoury_points_limit")
public class ArmouryPointsLimitReq extends AbstractRequirement{
	@Column(nullable = false)
	private String name;
	@Column(name = "points_limit")
	private int limit;
	
	private String type = "armoury_points_limit";
	
	public ArmouryPointsLimitReq() {};
	
	public ArmouryPointsLimitReq(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getLimit() {
		return limit;
	}
	
	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

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
