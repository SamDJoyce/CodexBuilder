package codexbuilder.model.requirements;

import codexbuilder.model.UnitType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("must_have_type")
public class MustHaveTypeReq{

	@Column(nullable = false)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column
	private UnitType requiredType;
	
	public MustHaveTypeReq() {};
	
	public MustHaveTypeReq(String name, UnitType requiredType) {
		this.name = name;
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredType(UnitType requiredType) {
		this.requiredType = requiredType;
	}
}
