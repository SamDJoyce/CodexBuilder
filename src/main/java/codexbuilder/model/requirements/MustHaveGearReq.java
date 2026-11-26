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
@DiscriminatorValue("must_have_gear")
public class MustHaveGearReq extends AbstractRequirement{

	@Column(nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "required_gear_id")
	private WargearDescription requiredGear;
	
	private final String type = "must_have_gear";
	
	public MustHaveGearReq() {};
	
	public MustHaveGearReq(String name){
		this.name = name;
	}
	
	public MustHaveGearReq(String name, WargearDescription requiredGear){
		this.name = name;
		this.requiredGear = requiredGear;
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
	public void setRequiredGear(WargearDescription requiredGear) {
		this.requiredGear = requiredGear;
	}
	
	@Override
	public WargearDescription getRequiredGear() {
		return this.requiredGear;
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
	public WargearDescription getBlockingGear() {
		// Unused
		return null;
	}

	@Override
	public void setBlockingGear(WargearDescription blockingGear) {
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
