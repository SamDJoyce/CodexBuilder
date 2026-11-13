package codexbuilder.model.requirements;

import codexbuilder.model.options.OptionGroup;

public class MutualExclusionReq {

	private final String name;
	private OptionGroup excludedGroup;
	
	public MutualExclusionReq(String name){
		this.name = name;
	}
	
	public MutualExclusionReq(
			String name,
			OptionGroup excludedGroup){
		this.name = name;
		this.excludedGroup = excludedGroup;
	}
	
	public String getName() {
		return name;
	}

	public OptionGroup getExcluded() {
		return excludedGroup;
	}

	public void setExcludedGroup(OptionGroup excludedGroup) {
		this.excludedGroup = excludedGroup;
	}

}
