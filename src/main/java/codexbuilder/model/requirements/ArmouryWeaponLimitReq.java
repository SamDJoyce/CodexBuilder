package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armoury_weapon_limit")

public class ArmouryWeaponLimitReq extends AbstractRequirement {
	@Column(nullable = false)
	private String name;
	
	private final String type = "armoury_weapon_limit";
	
	public ArmouryWeaponLimitReq() {};
	
	public ArmouryWeaponLimitReq(String name) {
		this.name = name;
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
