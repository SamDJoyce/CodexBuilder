package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;

public class MaxPerModelCountReq{

	private final String name;
	private final int rate;
	private ModelDescription model;
	private String message;
	
	public MaxPerModelCountReq(
			String name, int rate) {
		this.name = name;
		this.rate = rate;
	}
	
	public MaxPerModelCountReq(
			String name,
			ModelDescription model,
			int rate
			) {
		this.name = name;
		this.rate = rate;
		this.model = model;
	}
	
	public String getName() {
		return name;
	}

	public void setModel(ModelDescription model) {
		this.model = model;
	}

}
