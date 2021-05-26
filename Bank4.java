package hw4;

import java.util.ArrayList;

public class Bank4 {
	private int numAccts;
	private ArrayList<Accounts4> accounts;

	public Bank4() {
		numAccts = 0;
		accounts = new ArrayList<>();
	}
	public void setNumAccts(int num) {
		numAccts = num;
	}
	
	//setAcct
	public void setAcct(Accounts4 Accounts) {
		accounts.add(Accounts); 		
	}
	
	public int getNumAccts() {
		return numAccts;
	}
	
	public Accounts4 getAccount(int index) {
		return accounts.get(index);
	}
	
	public int findAcct(int accNum) {
		int ML,index=-1;
		
		if (accNum < 999999 && accNum > 100000) { 
			for (ML=0;ML < numAccts;ML++) {
				if (accounts.get(ML).getAccNum() == accNum) 
					index = ML;
			}
		}
		else if (accNum > 999999 || accNum < 100000) 
			index = -2;
		
		//-1 is returned if account doesn't exist (error #)
		// 0 is returned if account number is not in range
		return index;
	}
	
	public int deleteAcct(int accNum) {
		int index;
		
		index = findAcct(accNum);
		if(index >= 0) {
		if (getAccount(index).getAccBal()==0) {
				 //account removed
				 accounts.remove(index);
				 numAccts--;
				 index = -1;
			 } else {
				 //balance is not zero
				 index = -2;
			 }
		} 
		//-1 is returned if accounts doesn't exist (error #)
		return index;
	}
	
	public int newAcct(Accounts4 newAcc) {
		int index;
		
		index = findAcct(newAcc.getAccNum());
		if (index == -1) {
			accounts.add(newAcc);
			numAccts++;
		}
		//-1 returned = success
		return index;
	}
	
	public int findAcctS(String social) {
		int ML,result=-1;
		for (ML=0;ML < numAccts; ML++) {
			if (getAccount(ML).getDepo().getSSN().equals(social)) {
				result = ML;
			}
		}
		return result;
	}
}
