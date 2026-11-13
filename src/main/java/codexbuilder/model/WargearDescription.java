package codexbuilder.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WargearDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WargearType type;
	
	// Constructors
	public WargearDescription() {};
	
	public WargearDescription(
			String name,
			WargearType type) {
		this.name = name;
		this.type = type;
	}
	
	// Getters and Setters
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public WargearType getType() {
		return this.type;
	}
	
	public void setType(WargearType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
	    String wargear = String.format(
	        "%s - %s",
	        name,
	        type.toString().replace('_', ' ')
	    );
		return wargear;
	}

	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WargearDescription other)) return false;

        return Objects.equals(name, other.name)
            && Objects.equals(type, other.type);
    }
	
	@Override
	public int hashCode() {
	    return Objects.hash(
	        name,
	        type);
	}
}
