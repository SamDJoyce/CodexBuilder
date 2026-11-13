package codexbuilder.model.requirements;

import codexbuilder.model.WargearDescription;

public class CannotHaveGearReq{

	private final String name;
	private WargearDescription blockingGear;
	
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
