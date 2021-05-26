package hw4;

import java.util.ArrayList;

public class Accounts4 {
	private Depositor4 Depo;
	private int accNum;
	private double accBal;
	private String accType;
	private boolean accStat;
	private ArrayList<Transaction4> accTrans;
	private dateInfo4 accDate;

	public Accounts4() {
		Depo = new Depositor4();
		accNum = 0;
		accBal = 0.0;
		accType = null;
		accStat = true;
		accTrans = new ArrayList<>();
		accDate = new dateInfo4();
	}
	
	public Accounts4(Depositor4 newDepo, int AccNum, double AccBal, String AccType, boolean AccStat, Transaction4 AccTrans, dateInfo4 AccDate) {
		Depo = newDepo;
		accNum = AccNum;
		accBal = AccBal;
		accType = AccType;
		accStat = AccStat;
		accTrans.add(AccTrans);
		accDate = AccDate;
	}
	
	public void setDepo(Depositor4 dep) {
		Depo = dep;
	}

	public void setAccNum(int AccNum) {
		accNum = AccNum;
	}

	public void setAccType(String AccTyp) {
		accType = AccTyp;
	}

	public void setAccStat(boolean AccStat) {
		accStat = AccStat;
	}
	
	public void setAccBal(double AccBal) {
		accBal = AccBal;
	}
	
	public void setAccTrans(Transaction4 Trans) {
		accTrans.add(Trans);
	}
	
	public void setAccDate(dateInfo4 AccDate) {
		accDate = AccDate;
	}

	public Depositor4 getDepo() {
		return Depo;
	}

	public int getAccNum() {
		return accNum;
	}

	public String getAccType() {
		return accType;
	}
	
	public boolean getAccStat() {
		return accStat;
	}

	public double getAccBal() {
		return accBal;
	}
	
	public Transaction4 getAccTrans(int index) {
		return accTrans.get(index);
	}
	
	public int getTransLength() {
		return accTrans.size();
	}
	
	public dateInfo4 getAccDate() {
		return accDate;
	}
	
	public boolean closeAccount() {
		if (accStat == true) {
			accStat = false;
			return  true;
		} else return false;
	}
	
	public boolean reOpenAccount() {
		if (accStat == false) {
			accStat = true;
			return true;
		} else return false;
	}
	
	public int makeDeposit(double depoAmount) {
		
		if (depoAmount < 0) {
			//negative deposit amount
			return -1;
		}
		else {
			accBal += depoAmount;
			return 1;
		}
	}
	
	public int makeWithdrawal(double withAmount) {
		
		if (withAmount < 0) {
			//negative withdraw amount
			return -1;
		} else if (withAmount > accBal) {
			//Insufficient Funds
			return -2;
		} else {
			//Successful withdraw
			accBal -= withAmount;
			return 1;
		}
	}
}

