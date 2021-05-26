package hw4;

public class Name4 {
	private String fName;
	private String lName;
	
	public Name4() {}
	
	public Name4(Name4 newName) {
		fName = newName.getFirstName();
		lName = newName.getLastName();
	}
	
	public void setFirstName(String first) {
		fName = first;
	}
	
	public void setLastName(String last) {
		lName = last;
	}
	
	public String getFirstName() {
		return fName;
	}
	
	public String getLastName() {
		return lName;
	}
}
