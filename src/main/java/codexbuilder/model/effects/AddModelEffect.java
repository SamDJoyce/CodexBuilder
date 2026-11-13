package codexbuilder.model.effects;

import codexbuilder.model.ModelDescription;

public class AddModelEffect{

	private final String name;
	private ModelDescription model;
	
	public AddModelEffect(String name) {
		this.name = name;
	}
	
	public AddModelEffect(String name, ModelDescription model) {
		this.name = name;
		this.model = model;
	}
	
	public String getName() {
		return name;
	}

	public ModelDescription getModel() {
		return model;
	}
	
	public void setModel(ModelDescription model) {
		this.model = model;
	}

}
