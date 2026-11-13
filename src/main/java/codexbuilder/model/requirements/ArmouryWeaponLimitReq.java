package codexbuilder.model.requirements;

public class ArmouryWeaponLimitReq {

	
	private final static int MAX_WEAPON_COUNT = 2;
	private final static int MAX_TWO_HANDED   = 1;
	
	private final String name;
	
	public ArmouryWeaponLimitReq(String name) {
		this.name = name;
	}

}
