package hw4;

public class Transaction4 {
	private String tranType;
	private double tranVal;
	private dateInfo4 tranDate;
	//TransactionSuccessIndicator
	private boolean works;
	private String failReason;

	public Transaction4() {
	tranType = null;
	tranVal = 0.0;
	tranDate = new dateInfo4();
	works = false;
	failReason = null;
	}

	public Transaction4(dateInfo4 Date) {
	tranDate = Date;
	}

	public Transaction4(String type, double value, dateInfo4 Date, boolean worked) {
	tranType = type;
	tranVal = value;
	tranDate = Date;
	works = worked;
	}

	public void setTranType(String type) {
	tranType = type;
	}

	public void setTranVal(double value) {
	tranVal = value;
	}

	public void setTranDate(dateInfo4 date) {
	tranDate = date;
	}

	public void setWorks(boolean work) {
	works = work;
	}

	public void setFailReas(String reason) {
	failReason = reason;
	}

	public String getTranType() {
	return tranType;
	}

	public double getTranVal() {
	return tranVal;
	}

	public dateInfo4 getTranDate() {
	return tranDate;
	}

	public boolean getWorks() {
	return works;
	}

	public String getFailReas() {
	return failReason;
	}
}
