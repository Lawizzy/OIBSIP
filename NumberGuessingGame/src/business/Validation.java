package business;

public class Validation {
	
	private String lineEnd;
	
	public Validation() {
		this.lineEnd = "\n";
	}
	
	public Validation(String lineEnd) {
		
		this.lineEnd = lineEnd;
	}
	
	public String isPresent(String value, String name) {
		
		String message = "";
		
		if(value.isEmpty()) {
			
			message = name + " is required" + lineEnd;
		}
		
		return message;
	}
	
	public String isInteger(String value, String name) {
		
		String message = "";
		
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			

			message = e.getMessage() +" " +name+ " must be a number" + lineEnd;
		}
		
		return message;
	}
	
	public String isLarger(String value, String name) {
		
		String message = "";
		
		if(Integer.parseInt(value) > 100) {
			
			message = name + " is larger than 100\nCan't enter a number greater than 100" + lineEnd;
		}
		
		return message;
	}
	
	public String isSmaller(String value, String name) {
		
		String message = "";
		
		if(Integer.parseInt(value) < 1) {
			
			message = name + " is less than 1\nCan't enter a number less than 1" + lineEnd;
		}
		
		return message;
	}

}
