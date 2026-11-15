package codexbuilder.model;

public enum WargearType {
	ONE_HANDED("one handed"),
	TWO_HANDED("two handed"),
	GEAR("gear"),
	VEHICLE_UPGRADE("vehicle upgrade"),
	RELIC("relic"),
	PSYCHIC("psychic");
	
	private final String type;
	WargearType(String type){
		this.type = type;
	}
	
	public boolean isType(WargearType type) {
		return this == type;
	}
	
	public static WargearType fromString(String type) {
		for (WargearType t : WargearType.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return t;
			}
		}
		return null;
	}
	
	public String toTitleCase(String text) {
		if (text == null || text.isEmpty()) {
	        return text;
	    }

	    StringBuilder converted = new StringBuilder();

	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }
	    return converted.toString();
	}
	
	@Override
	public String toString() {
		return toTitleCase(this.type);
	}
}
