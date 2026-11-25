package codexbuilder.model.requirements;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armoury_points_limit")
public class ArmouryPointsLimitReq extends AbstractRequirement{
	@Column(nullable = false)
	private String name;
	@Column
	private int limit;
	
	public ArmouryPointsLimitReq() {};
	
	public ArmouryPointsLimitReq(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}
	
	public String getName() {
		return name;
	}

	public int getLimit() {
		return limit;
	}
}
