package codexbuilder.model.effects;

import codexbuilder.model.WargearDescription;

public class RemoveWargearEffect{

	private final String name;
	private WargearDescription remove;
	
	public RemoveWargearEffect(
			String name,
			WargearDescription remove) {
		this.name = name;
		this.remove = remove;
	}
	
	public RemoveWargearEffect(
			String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getRemove() {
		return remove;
	}
	
	public void setRemove(WargearDescription remove) {
		this.remove = remove;
	}
	
}
