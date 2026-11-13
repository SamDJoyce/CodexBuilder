package codexbuilder.model.effects;

public class ReplaceModelEffect {

	private final String name;
	private String newModelName;
	private String oldModelName;
	
	public ReplaceModelEffect(String name) {
		this.name = name;
	}
	
	public ReplaceModelEffect(
			String name,
			String newModelName, 
			String oldModelName) {
		this.name = name;
		this.newModelName = newModelName;
		this.oldModelName = oldModelName;
	}
	
	public String getName() {
		return name;
	}

	public String getNewModelName() {
		return newModelName;
	}

	public String getOldModelName() {
		return oldModelName;
	}
	
	public void setNewModelName(String newModelName) {
		this.newModelName = newModelName;
	}
	
	public void setOldModel(String oldModelName) {
		this.oldModelName = oldModelName;
	}


}
