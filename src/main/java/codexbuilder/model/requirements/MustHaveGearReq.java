package codexbuilder.model.requirements;

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
	private final String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "required_gear_id")
	private WargearDescription requiredGear;
	
	public MustHaveGearReq(String name){
		this.name = name;
	}
	
	public MustHaveGearReq(String name, WargearDescription requiredGear){
		this.name = name;
		this.requiredGear = requiredGear;
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredGear(WargearDescription requiredGear) {
		this.requiredGear = requiredGear;
	}
}
