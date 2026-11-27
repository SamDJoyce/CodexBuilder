package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;

public class ReqFactory {
	
	public static AbstractRequirement create(String type) {
		switch (type.toLowerCase()) {
	    	case "armoury_weapon_limit":
	    		return new ArmouryWeaponLimitReq();
	    	case "armoury_points_limit":
	    		return new ArmouryPointsLimitReq();
	    	case "cannot_have_gear":
	    		return new CannotHaveGearReq();
	    	case "characters_only":
	    		return new CharactersOnlyReq();
	    	case "max_per_model_count":
	    		return new MaxPerModelCountReq();
	    	case "max_selection":
	    		return new MaxSelectionReq();
	    	case "model_count":
	    		return new ModelCountReq();
	    	case "must_have_gear":
	    		return new MustHaveGearReq();
	    	case "must_have_type":
	    		return new MustHaveTypeReq();
	    	case "must_start_with_gear":
	    		return new MustStartWithGearReq();
	    	 default:
	             throw new IllegalArgumentException(
	                     "Requirement type unsupported by factory: " + type
	             );
		}
	}
	
	public static CharactersOnlyReq charactersOnly(String name) {
		return new CharactersOnlyReq(name);
	}
	
	
	public static MaxSelectionReq maxSelection(
			String name, 
			int maxSelection
	){
		return new MaxSelectionReq(name, maxSelection);
	}
	
	public static MaxPerModelCountReq maxPerModelCount(
			String name,
			int rate
	){
		return new MaxPerModelCountReq(name, rate);
	}
	
	public static MaxPerModelCountReq maxPerModelCount(
			String name,
			ModelDescription model,
			int rate
	){
		return new MaxPerModelCountReq(name, model, rate);
	}
	
	public static ModelCountReq modelCount(
			String name,
			int minimum,
			int maximum
	){
		return new ModelCountReq(
				name,
				minimum,
				maximum);
	}
	
	public static MustHaveTypeReq mustHaveType(
			String name, 
			UnitType type
	){
		return new MustHaveTypeReq(name,type);
	}
	
	public static MustHaveGearReq mustHaveGear(
			String name
	){
		return new MustHaveGearReq(name);
	}
	
	public static MustHaveGearReq mustHaveGear(
			String name, 
			WargearDescription requiredGear
	){
		return new MustHaveGearReq(name, requiredGear);
	}
	
	public static MustStartWithGearReq mustStartWithGear(String name) {
		return new MustStartWithGearReq(name);
	}
	
	public static MustStartWithGearReq mustStartWithGear(
			String name,
			WargearDescription requiredGear
	){
		return new MustStartWithGearReq(name);
	}
	
	public static CannotHaveGearReq cannotHaveGear(
			String name
	){
		return new CannotHaveGearReq(name);
	}
	
	public static CannotHaveGearReq cannotHaveGear(
			String name, 
			WargearDescription blockingGear
	){
		return new CannotHaveGearReq(name, blockingGear);
	}
	
	public static ArmouryPointsLimitReq armouryPointsLimit(String name, int limit) {
		return new ArmouryPointsLimitReq(name, limit);
	}
	
	public static ArmouryWeaponLimitReq armouryWeaponLimit(String name) {
		return new ArmouryWeaponLimitReq(name); 
	}
}
