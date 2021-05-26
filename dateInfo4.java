package hw4;

import java.util.Calendar;

public class dateInfo4 {
	private int year;
	private int month;
	private int dayOfMonth;
	
	public dateInfo4(int Year, int Month, int Day) {
		year = Year;
		month = Month;
		dayOfMonth = Day;
	}
	public dateInfo4(dateInfo4 newDate) {
		year = newDate.getYear();
		month = newDate.getMonth();
		dayOfMonth = newDate.getDay();
	}
	
	//Current Date for transaction dates
	public dateInfo4() {
		Calendar Today = Calendar.getInstance();
		
		year = Today.get(Calendar.YEAR);
		month = Today.get(Calendar.MONTH) +1;
		dayOfMonth = Today.get(Calendar.DAY_OF_MONTH);
	}
	
	//Date for CD accounts
	public dateInfo4(int a) {}
	
	public void setYear(int Year) {
		year = Year;
	}
	public void setMonth(int Month) {
		month = Month;
	}
	
	public void setDay(int Day) {
		dayOfMonth = Day;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return dayOfMonth;
	}
	
	public void addMonths(int add) {
		Calendar Today = Calendar.getInstance();
		Today.clear(); 
		
		Today.set(Calendar.DAY_OF_MONTH,dayOfMonth);
		Today.set(Calendar.YEAR,year);
		Today.set(Calendar.MONTH,month);
		Today.add(Calendar.MONTH,+add);
		
		month = Today.get(Calendar.MONTH);
		dayOfMonth = Today.get(Calendar.DAY_OF_MONTH);
		year = Today.get(Calendar.YEAR);
	}
	public int compareDate() {
		int key;
		
		Calendar Check = Calendar.getInstance();
		Check.clear();
		Check.set(Calendar.MONTH, month-1);
		Check.set(Calendar.DATE, dayOfMonth);
		Check.set(Calendar.YEAR,  year);
		
		Calendar today = Calendar.getInstance();
		today.clear(Calendar.HOUR);
		today.clear(Calendar.SECOND);
		today.clear(Calendar.MILLISECOND);
		
		/*
		System.out.println("MONTH = " + Check.get(Calendar.MONTH));
		System.out.println("DAY = " + Check.get(Calendar.DATE));
		System.out.println("YEAR = " + Check.get(Calendar.YEAR));
		
		System.out.println("TODAY MONTH = " + today.get(Calendar.MONTH));
		System.out.println("Today DAY = " + today.get(Calendar.DATE));
		System.out.println("Today Year = " + today.get(Calendar.YEAR));
		*/
		if (Check.after(today)) {
			//Check is too early wait longer
			//check is after today
			key = -1;
		} else { 
			today.add(Calendar.MONTH,  -6);
			if (Check.before(today)) {
				//check is more than 6 months before today
				//Check is too late waited too long
				key = 0;
			}else {
				// check is before and within six months of today
				//Check is valid clear the check
				key = 1;
			}
		}
		return key;
	}
	
	public int checkCD() {
		
		Calendar Today = Calendar.getInstance();
		Calendar CD = Calendar.getInstance();
		CD.clear();
		
		CD.set(Calendar.MONTH, month-1);
		CD.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		CD.set(Calendar.YEAR, year);
		
		if (CD.before(Today)) {
			//CD is mature
			return 1;
		} else {
			//CD is not mature yet
			return -1;
		}
	}
}
