package hw4;

public class Depositor4 {
	private Name4 name;
	private String accSSN;

	public Depositor4() {}
	
	public Depositor4(Depositor4 newDepo) {
		name = newDepo.getName();
		accSSN = newDepo.getSSN();
	}
	
	public void setName(Name4 NamE) {
		name = NamE;
	}

	public void setSSN(String social) {
		accSSN = social;
	}

	public Name4 getName() {
		return name;
	}

	public String getSSN() {
		return accSSN;
	}
}
