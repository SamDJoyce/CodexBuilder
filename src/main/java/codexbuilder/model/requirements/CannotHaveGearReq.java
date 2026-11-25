package codexbuilder.model.requirements;

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
	
	public CannotHaveGearReq() {};
	
	public CannotHaveGearReq(String name) {
		this.name = name;
	}
	
	public CannotHaveGearReq(String name, WargearDescription blockingGear) {
		this.name = name;
		this.blockingGear = blockingGear;
	}
	
	public WargearDescription getBlockingGear() {
		return blockingGear;
	}

	public void setBlockingGear(WargearDescription blockingGear) {
		this.blockingGear = blockingGear;
	}

	public String getName() {
		return name;
	}

}
