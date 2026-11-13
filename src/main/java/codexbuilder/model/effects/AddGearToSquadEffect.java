package codexbuilder.model.effects;

import codexbuilder.model.WargearDescription;
import codexbuilder.model.options.OptionChoice;
import codexbuilder.model.options.OptionGroup;

public class AddGearToSquadEffect {

	private final String name;
	private WargearDescription gear;
	private int pointsPerModel;
	
	public AddGearToSquadEffect(String name) {
		this.name = name;
	};
	
	public AddGearToSquadEffect(
			String name,
			WargearDescription gear,
			int pointsPerModel) {
		this.name = name;
		this.gear = gear;
		this.pointsPerModel = pointsPerModel;
		
		
	};
	
	public WargearDescription getGear() {
		return gear;
	}
	public void setGear(WargearDescription gear) {
		this.gear = gear;
	}
	public String getName() {
		return name;
	}
	public void setpointsPerModel(int points) {
		this.pointsPerModel = points;
	}
	public int getPointsPerModel() {
		return pointsPerModel;
	}
}
