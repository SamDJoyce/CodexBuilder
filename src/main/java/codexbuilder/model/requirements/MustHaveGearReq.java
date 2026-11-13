package codexbuilder.model.requirements;

import codexbuilder.model.WargearDescription;

public class MustHaveGearReq {

	private final String name;
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
