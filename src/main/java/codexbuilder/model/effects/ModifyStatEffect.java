package codexbuilder.model.effects;

public class ModifyStatEffect {
	private final String name;
	private String stat;
	private int    modifier;
	
	public ModifyStatEffect(String name) {
		this.name = name;
	}
	
	public ModifyStatEffect(String name, String stat, int modifier) {
		this.name = name;
		this.stat = stat;
		this.modifier = modifier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
}
