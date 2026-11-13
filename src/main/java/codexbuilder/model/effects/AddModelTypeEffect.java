package codexbuilder.model.effects;

import codexbuilder.model.UnitType;

public class AddModelTypeEffect {

	private final String   name;
	private 	  UnitType type;
	
	public AddModelTypeEffect(String name) {
		this.name = name;
	}
	
	public AddModelTypeEffect(
			String name,
			UnitType type
			) {
		this.name = name;
		this.type = type;
	}
	
	public UnitType getType() {
		return type;
	}

	public void setType(UnitType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

}
