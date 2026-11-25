package codexbuilder.model.options;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import codexbuilder.model.requirements.AbstractRequirement;

public class OptionGroup {
	private String name;
	private Set<OptionChoice> choices;
	private Set<AbstractRequirement> requirements;
	private int minSelections;
	private int maxSelections;
	
	public OptionGroup(
			String name,
			int minSelections,
			int maxSelections) {
		this.name = name;
		this.minSelections = minSelections;
		this.maxSelections = maxSelections;
		this.choices = new HashSet<>();
		this.requirements = new HashSet<>();
	}
	
	public OptionGroup(
			String name,
			Set<OptionChoice> choices,
			Set<AbstractRequirement> requirements,
			int minSelections,
			int maxSelections) {
		this.name 		   = name;
		this.choices 	   = new HashSet<>(choices) ;
		this.requirements  = new HashSet<>(requirements);
		this.minSelections = minSelections;
		this.maxSelections = maxSelections;
	}
	


	public String getName() {
		return name;
	}

	public Set<OptionChoice> getChoices() {
		return choices;
	}
	
	public boolean containsChoice(OptionChoice choice) {
		return choices.contains(choice);
	}

	public Set<AbstractRequirement> getRequirements() {
		return requirements;
	}
	
	public int getMinSelections() {
		return minSelections;
	}
	
	public int getMaxSelections() {
		return maxSelections;
	}

	public void setRequirements(Set<AbstractRequirement> requirements) {
		this.requirements = requirements;
	}

	public static OptionGroup create(
			String name,
			Set<OptionChoice> choices,
			int minSelections,
			int maxSelections) {
		return new OptionGroup(
					name,
					choices,
					new HashSet<AbstractRequirement>(),
					minSelections,
					maxSelections);
	}
	
	public static OptionGroup create(
			String name,
			Set<OptionChoice> choices,
			Set<AbstractRequirement> requirements,
			int minSelections,
			int maxSelections) {
		return new OptionGroup(
					name,
					choices,
					requirements,
					minSelections,
					maxSelections);
	}
	
	@Override
	public String toString() {
		String group = "\nGroup: " + name + "\n";
		
		for (OptionChoice c : choices) {
			group += "	Option: " + c.getName() + "\n";
		}
		
		return group;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (!(obj instanceof OptionGroup other)) {
	        return false;
	    }
	    return name.equalsIgnoreCase(other.name)
	    	&& minSelections == other.minSelections
	    	&& maxSelections == other.maxSelections;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, minSelections, maxSelections);
	}
	
}
