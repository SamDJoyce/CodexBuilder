package codexbuilder.model.effects;

import codexbuilder.model.WargearDescription;

public class AddWargearEffect{

	private final String name;
	private WargearDescription gear;
	
	public AddWargearEffect(String name) {
		this.name = name;
	}
	
	public AddWargearEffect(String name, WargearDescription gear) {
		this.name = name;
		this.gear = gear;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getGear() {
		return gear;
	}
	
	public void setGear(WargearDescription gear) {
		this.gear = gear;
	}
}
