package codexbuilder.model.requirements;

import codexbuilder.model.ModelDescription;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("max_per_model_count")
public class MaxPerModelCountReq extends AbstractRequirement {

	@Column(nullable = false)
	private String name;
	@Column
	private int rate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "count_model_id")
	private ModelDescription model;
	
	public MaxPerModelCountReq() {};
	
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
