package codexbuilder.model.requirements;

import codexbuilder.model.WargearDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("must_start_with_gear")
public class MustStartWithGearReq {

	@Column(nullable = false)
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "required_gear_id")
	private WargearDescription requiredGear;
	
	public MustStartWithGearReq() {};
	
	public MustStartWithGearReq(String name) {
		this.name = name;
		
	}
	
	public MustStartWithGearReq(String name, WargearDescription requiredGear) {
		this.name = name;
		this.requiredGear = requiredGear;
	}
	
	public WargearDescription getRequiredGear() {
		return requiredGear;
	}

	public void setRequiredGear(WargearDescription requiredGear) {
		this.requiredGear = requiredGear;
	}

	public String getName() {
		return name;
	}


}
