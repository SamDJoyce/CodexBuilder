package codexbuilder.model.effects;

import codexbuilder.model.WargearDescription;

public class ReplaceWargearEffect{

	private final String name;
	private WargearDescription remove;
	private WargearDescription add;
	
	public ReplaceWargearEffect(String name) {
		this.name = name;
	}
	
	public ReplaceWargearEffect(
			String name,
			WargearDescription remove,
			WargearDescription add) {
		this.name = name;
		this.remove = remove;
		this.add = add;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getRemove() {
		return remove;
	}

	public WargearDescription getAdd() {
		return add;
	}

	public void setAdd(WargearDescription add) {
		this.add = add;
	}

}
