package codexbuilder.model.effects;

public class ChangeModelNameEffect{

	private final String effectName;
	private String newName;
	
	public ChangeModelNameEffect(String effectName) {
		this.effectName = effectName;
	}
	
	public ChangeModelNameEffect(String effectName, String newName) {
		this.effectName = effectName;
		this.newName = newName;
	}
	
	public String getEffectName() {
		return effectName;
	}

	public String getNewName() {
		return newName;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}
}
