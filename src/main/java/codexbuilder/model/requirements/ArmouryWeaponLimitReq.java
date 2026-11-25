package codexbuilder.model.requirements;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armoury_weapon_limit")

public class ArmouryWeaponLimitReq extends AbstractRequirement {
	@Column(nullable = false)
	private String name;
	
	public ArmouryWeaponLimitReq() {};
	
	public ArmouryWeaponLimitReq(String name) {
		this.name = name;
	}

}
