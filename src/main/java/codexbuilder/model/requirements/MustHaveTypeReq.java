package codexbuilder.model.requirements;

import codexbuilder.model.UnitType;

public class MustHaveTypeReq{

	private final String name;
	private UnitType requiredType;
	
	public MustHaveTypeReq(String name, UnitType requiredType) {
		this.name = name;
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredType(UnitType requiredType) {
		this.requiredType = requiredType;
	}
}
