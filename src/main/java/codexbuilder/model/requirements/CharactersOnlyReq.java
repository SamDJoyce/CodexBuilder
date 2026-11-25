package codexbuilder.model.requirements;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("characters_only")
public class CharactersOnlyReq extends AbstractRequirement {
	
	@Column(nullable = false)
	private String name;

	public CharactersOnlyReq() {}
	
	public CharactersOnlyReq(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
