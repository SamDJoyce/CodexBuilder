package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;
import codexbuilder.model.UnitType;
import codexbuilder.model.WargearDescription;

/**
 * 
 */
public class EffectFactory {
	/**
	 * Construct an AddModelEffect object
	 */
	public static AddModelEffect addModel(String name) {
		return new AddModelEffect(name);
	}
	/**
	 * Construct an AddModelEffect object
	 */
	public static AddModelEffect addModel(String name, ModelDescription model) {
		return new AddModelEffect(name, model);
	}
	
	/**
	 * Construct a ReplaceModelEffect object
	 */
	public static ReplaceModelEffect replaceModel(
			String name) {
		return new ReplaceModelEffect(name);
	}
	
	/**
	 * Construct a ReplaceModelEffect object
	 */
	public static ReplaceModelEffect replaceModel(
			String name,
			ModelDescription newModel, 
			ModelDescription oldModel) {
		return new ReplaceModelEffect(name, newModel, oldModel);
	}
	
	/**
	 * Construct a AddWargearEffect object
	 */
	public static AddWargearEffect addWargear(String name) {
		return new AddWargearEffect(name);
	}
	
	/**
	 * Construct a AddWargearEffect object
	 */
	public static AddWargearEffect addWargear(String name,WargearDescription gear) {
		return new AddWargearEffect(name, gear);
	}
	
	/**
	 * Construct a RemoveWargearEffect object
	 */
	public static RemoveWargearEffect removeWargear(String name) {
		return new RemoveWargearEffect(name);
	}
	
	/**
	 * Construct a RemoveWargearEffect object
	 */
	public static RemoveWargearEffect removeWargear(String name,WargearDescription remove) {
		return new RemoveWargearEffect(name, remove);
	}
	
	/**
	 * Construct a ReplaceWargearEffect object
	 */
	public static ReplaceWargearEffect replaceWargear(String name) {
		return new ReplaceWargearEffect(name);
	}
	
	/**
	 * Construct a ReplaceWargearEffect object
	 */
	public static ReplaceWargearEffect replaceWargear(
			String name,
			WargearDescription remove,
			WargearDescription add) {
		return new ReplaceWargearEffect(name,remove,add);
	}
	/**
	 * Construct an AddGearToSquadEffect object
	 * 
	 * @param name
	 * @return
	 */
	public static AddGearToSquadEffect addGearToSquad(String name) {
		return new AddGearToSquadEffect(name);
	}
	
	/**
	 * Construct an AddGearToSquadEffect object
	 * 
	 * @param name
	 * @return
	 */
	public static AddGearToSquadEffect addGearToSquad(
			String name,
			WargearDescription gear,
			int pointsPerModel) {
		return new AddGearToSquadEffect(name, gear, pointsPerModel);
	}
	
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static ModifyStatEffect modifyStat(String name) {
		return new ModifyStatEffect(name);
	}
	
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static ModifyStatEffect modifyStat(String name, String stat, int modifier) {
		return new ModifyStatEffect(name, stat,modifier);
	}
	
	public static ChangeModelNameEffect changeModelName(String effectName) {
		return new ChangeModelNameEffect(effectName);
	}
	
	public static ChangeModelNameEffect changeModelName(String effectName, String newName) {
		return new ChangeModelNameEffect(effectName, newName);
	}
	
	public static AddModelTypeEffect addModelType(String name, UnitType type) {
		return new AddModelTypeEffect(name, type);
	}


}
