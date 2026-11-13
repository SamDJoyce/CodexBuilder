package codexbuilder.model.options;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OptionChoice {
	
	// Fields
	private final String 	name;
	private final int		points;
	private OptionGroup     parentGroup;
	private Set<String> requirements;
	private Set<String> effects;
	
	public OptionChoice(
			String name,
			int points,
			Set<String> requirements,
			Set<String> effects
			) {
		this.name = name;
		this.points = points;
		this.requirements = new HashSet<>(requirements);
		this.effects = new HashSet<>(effects);
	}
	
	public OptionChoice(
			String name,
			int points) {
		this.name = name;
		this.points = points;
		this.effects = new HashSet<>();
		this.requirements = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public OptionGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(OptionGroup parentGroup) {
		this.parentGroup = parentGroup;
	}

	public Set<String> getRequirements() {
		return Collections.unmodifiableSet(requirements);
	}
	
	public void setRequirements(Set<String> requirements) {
		this.requirements = requirements;
	}
	
	public Set<String> getEffects(){
		return Collections.unmodifiableSet(effects);
	}
	
	public void setEffects(Set<String> effects) {
		this.effects = effects;
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (!(obj instanceof OptionChoice other)) {
	        return false;
	    }

	    return points == other.points
	            && Objects.equals(name, other.name)
	            && Objects.equals(requirements, other.requirements)
	            && Objects.equals(effects, other.effects);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(name, points, requirements, effects);
	}
	
	public static OptionChoice create(String name, int points) {
		return new OptionChoice(name, points);
	}

}
