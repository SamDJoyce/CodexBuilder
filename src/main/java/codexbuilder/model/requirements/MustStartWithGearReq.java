package codexbuilder.model.requirements;

import codexbuilder.model.WargearDescription;

public class MustStartWithGearReq {

	final String name;
	private WargearDescription requiredGear;
	
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
