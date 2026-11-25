package codexbuilder.model.requirements;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("max_selection")
public class MaxSelectionReq extends AbstractRequirement {

	@Column(nullable = false)
	private String name;
	@Column
	private int maxSelection;
	@Column
	private String message;
	
	public MaxSelectionReq() {};
	
	public MaxSelectionReq(
			String name,
			int maxSelection
			) {
		this.name = name;
		this.maxSelection = maxSelection;
	}
	
	public int getMaxSelection() {
		return maxSelection;
	}

	public void setMaxSelection(int maxSelection) {
		this.maxSelection = maxSelection;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}
	
}
