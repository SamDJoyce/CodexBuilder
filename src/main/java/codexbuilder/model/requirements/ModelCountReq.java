package codexbuilder.model.requirements;

public class ModelCountReq{

	private final String name;
    private final int minimum;
    private final int maximum;
    private String message;
    
    public ModelCountReq(
    		String name,
    		int minimum,
    		int maximum
    		) {
    	this.name = name;
    	this.minimum = minimum;
    	this.maximum = maximum;
    }
	
	public String getName() {
		return name;
	}
}
