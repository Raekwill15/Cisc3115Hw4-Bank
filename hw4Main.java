package hw4;

import java.util.*;
import java.io.*;

public class hw4Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		char Command;

		File AccInfo = new File("C:\\Users\\moonk\\eclipse-workspace\\Homeworks\\src\\hw4\\AccInfoInput");
		Scanner in = new Scanner(AccInfo);
		File Commands = new File("C:\\Users\\moonk\\eclipse-workspace\\Homeworks\\src\\hw4\\CommandsHw4");
		Scanner in2 = new Scanner(Commands);
		Scanner in3 = new Scanner(System.in);
		File output = new File("C:\\Users\\moonk\\eclipse-workspace\\Homeworks\\src\\hw4\\hw4Output");
		PrintWriter out = new PrintWriter(output);
		// PrintWriter out = new PrintWriter(output);
		out.println("Raekwon Williams");
		out.println("");
		
		
		Bank4 Bank = new Bank4();

		readAccts(Bank, in);
		printAccts(Bank, out);
		do {
			out.println("");
			menu();
			System.out.println("Please enter a Command from the list above: ");
			Command = in2.next().charAt(0);
			switch (Command) {
			case 'W':
			case 'w':
				withdrawal(Bank, in2, out);
				out.println("");
				break;
			case 'D':
			case 'd':
				deposit(Bank, in2, out);
				out.println("");
				break;
			case 'C':
			case 'c':
				clearCheck(Bank, in2, out);
				out.println("");
				break;
			case 'N':
			case 'n':
				newAcct(Bank, in2, out);
				out.println("");
				break;
			case 'B':
			case 'b':
				balance(Bank, in2, out);
				out.println("");
				break;
			case 'I':
			case 'i':
				acctInfo(Bank, in2, out);
				out.println("");
				break;
			case 'H':
			case 'h':
				acctInfoHistory(Bank, in2, out);
				out.println("");
				break;
			case 'X':
			case 'x':
				deleteAcct(Bank, in2, out);
				out.println("");
				break;
			case 'R':
			case 'r':
				reOpenAcct(Bank, in2, out);
				out.println("");
				break;
			case 'S':
			case 's':
				closeAcct(Bank, in2, out);
				out.println("");
				break;
			case 'Q':
			case 'q':
				out.println("All transactions complete.");
				out.println("");
				printAccts(Bank,out);
				break;
			default:
				out.println("*Error*");
				out.println("Invalid Command(" + Command + ") entered.");
				out.println("");
				break;
			}
			out.flush();
			System.out.flush();
			// out.flush();
		} while (Command != 'q' && Command != 'Q');
	}

	/*
	 * 	Input: 
	 * None 
	 * 	Process:
	 * Prints the command/transaction menu 
	 * 	Output: 
	 * Menu is printed
	 */
	public static void menu() {
		System.out.println("Below is a list of all the operations.\nChoose one to Begin");
		System.out.println("W - Withdrawl");
		System.out.println("D - Deposit");
		System.out.println("N - New Account");
		System.out.println("B - Balance");
		System.out.println("I - Account Info");
		System.out.println("H - Account Info and History");
		System.out.println("R - Re-Open Account");
		System.out.println("S - Close Account");
		System.out.println("X - Delete Account");
		System.out.println("Q - Quit");
	}

	/*
	 * Input: Bank - Bank Object that hold accounts info in an arrayList and number
	 * of accts in- Scanner to read from a file Process: Goes through a file and
	 * reads info into Accounts Output: returns the amount of Accounts read into the
	 * Bank object accounts arrayList
	 */
	public static void readAccts(Bank4 Bank, Scanner in) throws IOException {
		int ML = 0, a = 0;

		while (in.hasNext()) {
			Accounts4 newAcc = new Accounts4();
			Depositor4 newDepo = new Depositor4();
			Name4 newName = new Name4();
			dateInfo4 newDate = new dateInfo4(a);
			Transaction4 newTrans = new Transaction4();

			newName.setFirstName(in.next());
			newName.setLastName(in.next());
			newDepo.setSSN(in.next());
			newAcc.setAccNum(in.nextInt());
			newAcc.setAccBal(in.nextDouble());
			newAcc.setAccType(in.next());
			newAcc.setAccStat(in.nextBoolean());
			newDepo.setName(newName);
			newTrans.setTranType("Create Account");
			newTrans.setTranVal(newAcc.getAccBal());
			newTrans.setTranDate(new dateInfo4());
			newTrans.setWorks(true);
			newAcc.setAccTrans(newTrans);
			newAcc.setDepo(newDepo);
			if (newAcc.getAccType().equals("CD")) {
				newDate.setYear(in.nextInt());
				newDate.setMonth(in.nextInt());
				newDate.setDay(in.nextInt());
				newAcc.setAccDate(newDate);
			} else {
				newAcc.setAccDate(newDate);
			}

			Bank.setAcct(newAcc);
			ML++;
		}
		Bank.setNumAccts(ML);
	}

	/*
	 * Input: Bank - Bank4 Object with Account information in an arrayList 
	 * and numAccts 
	 * out- PrintWriter to print to an output file 
	 * 	Process: 
	 * Prints the Accounts in the Bank with a table heading 
	 * 	Output: 
	 * Accounts and information are printed
	 */
	public static void printAccts(Bank4 bank, PrintWriter out) {
		int ML, ML2;

		out.printf("%-10s%-20s%-14s%-11s%-10s%-14s%-10s\n", "Status", "Name", "SSN#", "Acct Num", "Acct Bal",
				"Account Type", "Mat.Date (CD Only)");
		for (ML = 0; ML < bank.getNumAccts(); ML++) {
			for (ML = 0; ML < bank.getNumAccts(); ML++) {
				Accounts4 myAcc = new Accounts4();
				Depositor4 myDepo = new Depositor4();
				Name4 myName = new Name4();
				dateInfo4 myDate = new dateInfo4();
				Transaction4 myTrans = new Transaction4();

				myAcc = bank.getAccount(ML);
				myDepo = myAcc.getDepo();
				myName = myDepo.getName();
				myDate = myAcc.getAccDate();

				if (myAcc.getAccStat() == true) {
					out.printf("%-10s", "Open");
				} else
					out.printf("%-10s", "Closed");
				out.printf("%-10s%-10s", myName.getFirstName(), myName.getLastName());
				out.printf("%-14s%-9s", myDepo.getSSN(), myAcc.getAccNum());
				out.printf("$%9.2f%14s", myAcc.getAccBal(), myAcc.getAccType());
				out.printf("%4s/%s/%s", myDate.getMonth(), myDate.getDay(), myDate.getYear());
				out.println("");
				out.println("History:");
				for (ML2 = 0; ML2 < bank.getAccount(ML).getTransLength(); ML2++) {
					myTrans = myAcc.getAccTrans(ML2);
					if (myTrans.getWorks() == true)
						out.printf("%-23s","Successful Transaction");
					else if (myTrans.getWorks() == false)
						out.printf("%-23s","Failed Transaction");
					out.printf(" %-38s %s/%2s/%2s  %-28s" ,"Transaction: " + myTrans.getTranType(), 
							"Transaction Date: " + myTrans.getTranDate().getMonth(),myTrans.getTranDate().getDay(),myTrans.getTranDate().getYear(),
							"Transaction Value: " + myTrans.getTranVal() );
					if (myTrans.getWorks() == false)
						out.print(" Reason for Failure: " + myTrans.getFailReas());
					out.println("");
				}
				out.println("");
			}
			out.println("");
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 Class with Accounts arrayList 
	 * and number of accounts in arrayList 
	 * in - Scanner for reading input from a file/user 
	 * out - PrintWriter to print to an output file 
	 * 	Process: 
	 * Asks for a valid account number and account information 
	 * and creates a new account based on the info entered
	 * 	Output: 
	 * A new Account with information is added to the Accounts arrayList and
	 * the numAccts in the Bank class is updated
	 */
	public static void newAcct(Bank4 Bank, Scanner in, PrintWriter out ) {
		int accNum, result, a = 0;
		Accounts4 newAcc = new Accounts4();
		Depositor4 newDepo = new Depositor4();
		Name4 newName = new Name4();
		Transaction4 newTrans = new Transaction4();

		System.out.println("Transaction Type: New Account");
		System.out.println("Enter the new account's number");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			newAcc.setAccNum(accNum);
			System.out.println("You will now be prompted for the accounts information");
			System.out.println("First Name: ");
			newName.setFirstName(in.next());
			System.out.println("LastName: ");
			newName.setLastName(in.next());
			System.out.println("Social Security Number (xxx-xx-xxxx): ");
			newDepo.setSSN(in.next());
			System.out.println("Initial Deposit: ");
			newAcc.setAccBal(in.nextDouble());
			System.out.println("Account Type (CD, Checkings, Savings): ");
			newAcc.setAccType(in.next());
			if (newAcc.getAccType().equals("CD")) {
				dateInfo4 newDate = new dateInfo4();
				System.out.println("How long would you like your account to last");
				System.out.println("Your choices are for 6, 12, 18, or 24 months from now: ");
				newDate.addMonths(in.nextInt());
				newAcc.setAccDate(newDate);
			} else {
				dateInfo4 newDate = new dateInfo4(a);
				newAcc.setAccDate(newDate);
			}
			newTrans.setTranType("Create Account");
			newTrans.setTranVal(newAcc.getAccBal());
			newTrans.setWorks(true);

			newAcc.setAccTrans(newTrans);
			newDepo.setName(newName);
			newAcc.setDepo(newDepo);

			Bank.newAcct(newAcc);

			out.println("Receipt");
			out.println("Transaction Type: New Account");
			out.println("New Account Created");
			out.println("Account Number: " + accNum);
			out.println("Account Holder: " + newName.getFirstName() + " " + newName.getLastName());
		}
		if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: New Account");
			out.println("The account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else if (result >= 0) {
			out.println("*ERROR*");
			out.println("Transaction Type: New Account");
			out.println("an Account with the number " + accNum + " already exists");
		}
	}

	/*
	 * 	Input:
	 * Bank - Bank4 Object with accounts information in an arrayList and
	 * number of accounts
	 * in - Scanner to read input from user/file 
	 * out - PrintWriter to print to an output file 
	 * 	Process: 
	 * Asks for a valid account number and if that account exists then 
	 * The account is deleted if balance is 0
	 * Output: 
	 * Entered Account is deleted from the arrayList 
	 * and number of accounts in arrayList is adjusted
	 */
	public static void deleteAcct(Bank4 Bank, Scanner in, PrintWriter out ) {
		int accNum, result, result2;

		System.out.println("Transaction Type: Delete Account");
		System.out.println("Enter the desired account's number");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: Delete Account");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: Delete Account");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else {
			result2 = Bank.deleteAcct(accNum);
			if (result2 == -1) {
				out.println("Receipt");
				out.println("Transaction Type: Delete Account");
				out.println("Account number " + accNum + " has been deleted");
			} else if (result2 == -2) {
				out.println("*Error*");
				out.println("Transaction Type: Delete Account");
				out.println("Account number: " + accNum);
				out.println("The account must have a balance of 0 before being deleted");

				Transaction4 newTrans = new Transaction4();
				newTrans.setTranType("Delete Account");
				newTrans.setWorks(false);
				newTrans.setFailReas("Balance still remains");
				Bank.getAccount(result).setAccTrans(newTrans);
			}
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 Object with Accounts information in an arrayList 
	 * and number of accounts 
	 * in- Scanner for reading input from a file
	 * out - PrintWriter to print to an output file 
	 * 	Process: 
	 * Prompts for the user to enter an account number 
	 * Then finds the accounts balance and prints it 
	 * 	Output: 
	 * User receives the balance of a requested account
	 */
	public static void balance(Bank4 Bank, Scanner in, PrintWriter out ) {
		int accNum, result;

		System.out.println("Transaction Type: View Balance");
		System.out.println("Enter the desired account's number: ");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: View Balance");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: View Balance");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else {
			if (Bank.getAccount(result).getAccStat() == false) {
				out.println("ERROR");
				out.println("Transaction Type: View Balance");
				out.println("Account " + accNum + " is closed");
				
				Transaction4 newTrans = new Transaction4();
				newTrans.setTranType("View Balance");
				newTrans.setWorks(false);
				newTrans.setFailReas("Account was closed");
				Bank.getAccount(result).setAccTrans(newTrans);
			} else {
				double balance = Bank.getAccount(result).getAccBal();
				Transaction4 newTrans = new Transaction4();
				out.println("Receipt");
				out.println("Transaction Type: View Balance");
				out.println("Account Number: " + accNum);
				out.println("Current Balance: " + balance);
				
				newTrans.setTranType("View Balance");
				newTrans.setWorks(true);
				Bank.getAccount(result).setAccTrans(newTrans);
			}
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 object with arrayList of accounts 
	 * and the number of accounts in the arrayList 
	 * in - Scanner to read from a input file 
	 * out - PrintWriter to write output to a new file 
	 * 	Process: 
	 * Prompts the user for an account number then opens 
	 * the account if it's closed 
	 * 	Output: 
	 * Nothing is returned 
	 * Method re-Opens closed accounts
	 */
	public static void reOpenAcct(Bank4 Bank, Scanner in, PrintWriter out) {
		int accNum, result;

		System.out.println("Transaction Type: Re-Open Account");
		System.out.println("Enter the desired account's number");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: Re-Open Account");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: Re-Open Account");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else if (Bank.getAccount(result).reOpenAccount()) {
			out.println("Receipt");
			out.println("Transaction Type: Re-Open Account");
			out.println("Account number " + accNum + " has been re-Opened");

			Transaction4 newTrans = new Transaction4();
			newTrans.setTranType("Re-Open Account");
			newTrans.setWorks(true);
			Bank.getAccount(result).setAccTrans(newTrans);
		} else {
			out.println("*ERROR*");
			out.println("Transaction Type: Re-Open Account");
			out.println("Account number " + accNum + " is already open");

			Transaction4 newTrans = new Transaction4();
			newTrans.setTranType("Re-Open Account");
			newTrans.setWorks(false);
			newTrans.setFailReas("Account was already open");
			Bank.getAccount(result).setAccTrans(newTrans);
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 object with arrayList of accounts 
	 * and the number of accounts 
	 * in - Scanner to read input from a file 
	 * out - PrintWriter to write output to a file 
	 * 	Process: 
	 * Prompts the user for an account number if the
	 * account is open then it gets closed 
	 * 	Output:
	 * Nothing is returned 
	 * Method closes accounts
	 */
	public static void closeAcct(Bank4 Bank, Scanner in, PrintWriter out) {
		int accNum, result;

		System.out.println("Transaction Type: Close Account");
		System.out.println("Enter the desired account's number");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: Close Account");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: Close Account");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else if (Bank.getAccount(result).closeAccount()) {
			out.println("Receipt");
			out.println("Transaction Type: Close Account");
			out.println("Account number " + accNum + " has been closed");

			Transaction4 newTrans = new Transaction4();
			newTrans.setTranType("Close Account");
			newTrans.setWorks(true);
			Bank.getAccount(result).setAccTrans(newTrans);
		} else {
			out.println("*ERROR*");
			out.println("Transaction Type: Close Account");
			out.println("Account number " + accNum + " is already closed");

			Transaction4 newTrans = new Transaction4();
			newTrans.setTranType("Close Account");
			newTrans.setWorks(false);
			newTrans.setFailReas("Account was already closed");
			Bank.getAccount(result).setAccTrans(newTrans);
		}
	}

	/*
	 * 	Input:
	 * Bank - Bank4 Object with an arrayList of accounts 
	 * and number of accounts 
	 * in - Scanner to read input from a file 
	 * out - PrintWriter to write to an output file 
	 * 	Process: 
	 * Prompts the user for a valid social security number
	 * finds the account/s with the same number and prints the the account/s
	 * information 
	 * 	Output: 
	 * Nothing is returned Method prints account information
	 */
	public static void acctInfo(Bank4 Bank, Scanner in, PrintWriter out) {
		int result, ML;
		String SSN;

		System.out.println("Transaction Type: View Account Info");
		System.out.println("Enter the account holders SSN(xxx-xx-xxxx):");
		SSN = in.next();
		result = Bank.findAcctS(SSN);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: View Account Info");
			out.println("Social security number " + SSN + " does not have an account");
		} else {
			out.println("Receipt");
			out.println("Transaction Type: View Account Information");
			out.println("Account SSN: " + SSN);
			for (ML = 0; ML < Bank.getNumAccts(); ML++) {
				if (Bank.getAccount(ML).getDepo().getSSN().equals(SSN)) {
					Accounts4 myAcc = new Accounts4();
					myAcc = Bank.getAccount(ML);
					dateInfo4 myDate = new dateInfo4(myAcc.getAccDate());
					Depositor4 myDepo = new Depositor4(myAcc.getDepo());
					Name4 myName = new Name4(myDepo.getName());

					out.printf("%-10s%-20s%-14s%-11s%-10s%-14s%-10s\n", "Status", "Name", "SSN#", "Acct Num",
							"Acct Bal", "Account Type", "Mat.Date (CD Only)");
					if (myAcc.getAccStat() == true) {
						out.printf("%-10s", "Open");
						out.printf("%-10s%-10s", myName.getFirstName(), myName.getLastName());
						out.printf("%-14s%-9s", myDepo.getSSN(), myAcc.getAccNum());
						out.printf("$%9.2f%14s", myAcc.getAccBal(), myAcc.getAccType());
						out.printf("%4s/%s/%s", myDate.getMonth(), myDate.getDay(), myDate.getYear());
						out.println("\n");
						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("View Account Information");
						newTrans.setWorks(true);
						Bank.getAccount(ML).setAccTrans(newTrans);
					} else {
						out.println("*ERROR*");
						out.printf("%-10s", "Closed");
						out.println("Account Number: " + myAcc.getAccNum() + " is closed");
						out.println("You must open the account to view its content");
						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("View Account Information");
						newTrans.setWorks(false);
						newTrans.setFailReas("Account was closed");
						Bank.getAccount(ML).setAccTrans(newTrans);
					}
				}
			}
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 Object with an arrayList of accounts 
	 * and number of accounts 
	 * in - Scanner to read input from a file 
	 * out - PrintWriter to write to an output file 
	 * 	Process: 
	 * Prompts the user for a valid social security number
	 * finds the account/s with the same number and prints the the account/s
	 * information and history 
	 * 	Output:
	 * Nothing is returned Method prints account
	 * information and history
	 */
	public static void acctInfoHistory(Bank4 Bank, Scanner in, PrintWriter out) {
		int result, ML, ML2;
		String SSN;

		System.out.println("Transaction Type: View Account Info and History");
		System.out.println("Enter the account holders SSN(xxx-xx-xxxx):");
		SSN = in.next();
		result = Bank.findAcctS(SSN);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: View Account Info and History");
			out.println("Social security number " + SSN + " does not have an account");
		} else {
			out.println("Receipt");
			out.println("Transaction Type: Account Information and History");
			out.println("Account SSN: " + SSN);
			for (ML = 0; ML < Bank.getNumAccts(); ML++) {
				if (Bank.getAccount(ML).getDepo().getSSN().equals(SSN)) {
					Accounts4 myAcc = new Accounts4();
					myAcc = Bank.getAccount(ML);
					dateInfo4 myDate = new dateInfo4(myAcc.getAccDate());
					Depositor4 myDepo = new Depositor4(myAcc.getDepo());
					Name4 myName = new Name4(myDepo.getName());
					Transaction4 myTrans = new Transaction4();

					out.printf("%-10s%-20s%-14s%-11s%-10s%-14s%-10s\n", "Status", "Name", "SSN#", "Acct Num",
							"Acct Bal", "Account Type", "Mat.Date (CD Only)");
					if (myAcc.getAccStat() == true) {
						out.printf("%-10s", "Open");
						out.printf("%-10s%-10s", myName.getFirstName(), myName.getLastName());
						out.printf("%-14s%-9s", myDepo.getSSN(), myAcc.getAccNum());
						out.printf("$%9.2f%14s", myAcc.getAccBal(), myAcc.getAccType());
						out.printf("%4s/%s/%s", myDate.getMonth(), myDate.getDay(), myDate.getYear());
						out.println("");
						out.println("History:");
						for (ML2 = 0; ML2 < Bank.getAccount(ML).getTransLength(); ML2++) {
							myTrans = myAcc.getAccTrans(ML2);
							if (myTrans.getWorks() == true)
								out.printf("%-23s","Successful Transaction");
							else if (myTrans.getWorks() == false)
								out.printf("%-23s","Failed Transaction");
							out.printf(" %-38s %s/%2s/%2s  %-28s" ,"Transaction: " + myTrans.getTranType(), 
									"Transaction Date: " + myTrans.getTranDate().getMonth(),myTrans.getTranDate().getDay(),myTrans.getTranDate().getYear(),
									"Transaction Value: " + myTrans.getTranVal() );
							if (myTrans.getWorks() == false)
								out.print(" Reason for Failure: " + myTrans.getFailReas());
							out.println("");
						}
						out.println("\n");
						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Account Info and History");
						newTrans.setWorks(true);
						Bank.getAccount(ML).setAccTrans(newTrans);
					} else {
						out.println("*ERROR*");
						out.printf("%-10s", "Closed");
						out.println("Account Number: " + myAcc.getAccNum() + " is closed");
						out.println("You must open the account to view its content");
						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Account Info and History");
						newTrans.setWorks(false);
						newTrans.setFailReas("Account was closed");
						Bank.getAccount(ML).setAccTrans(newTrans);
					}
				}
			}
		}
	}

	/*
	 * 	Input:
	 * Bank - Bank4 Object with Accounts information in an arrayList 
	 * and the number of accounts 
	 * in - Scanner for reading input 
	 * out - PrintWriter to print to an outputfile 
	 * 	Process: 
	 * Prompts the user for a checking account number
	 * Creates a check and decides if the checks date is valid and can be cleared
	 * 	Output:
	 * The check's amount is withdrawn from the checking account
	 */
	public static void clearCheck(Bank4 Bank, Scanner in, PrintWriter out) {
		int result, accNum, month, day, year, result2, result3;
		double value;

		System.out.println("Trasaction Type: ClearCheck");
		System.out.println("Enter the accounts number:");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: Clear Check");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: Clear Check");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else {
			if (Bank.getAccount(result).getAccStat() == false) {
				out.println("*ERROR*");
				out.println("Transaction Type: Clear Check");
				out.println("Account number " + accNum + " is closed");
				out.println("You Must open the account before making changes to it");

				Transaction4 newTrans = new Transaction4();
				newTrans.setTranType("Clear Check");
				newTrans.setWorks(false);
				newTrans.setFailReas("Account was closed");
				Bank.getAccount(result).setAccTrans(newTrans);
			} else {
				if (Bank.getAccount(result).getAccType().equals("Checking")) {
					System.out.println("Enter the checks information here");
					System.out.println("Month: ");
					month = in.nextInt();
					System.out.println("Day: ");
					day = in.nextInt();
					System.out.println("Year: ");
					year = in.nextInt();
					System.out.println("Checks value: ");
					value = in.nextDouble();

					dateInfo4 checksDate = new dateInfo4(year, month, day);
					result2 = checksDate.compareDate();
					if (result2 == -1) {
						// too early wait longer Error
						out.println("*Error*");
						out.println("Transaction Type: Clear Check");
						out.println("Account Number: " + accNum);
						out.println("The date on the check has not arrived yet");
						out.println("Wait until " + month + "/" + day + "/" + year);

						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Clear Check");
						newTrans.setWorks(false);
						newTrans.setFailReas("Too early for withdrawal");
						Bank.getAccount(result).setAccTrans(newTrans);
					} else if (result2 == 0) {
						// too late check expired Error
						out.println("*Error");
						out.println("Transaction Type: Clear Check");
						out.println("Account Number: " + accNum);
						out.println("The check has expired and is now void");

						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Clear Check");
						newTrans.setWorks(false);
						newTrans.setFailReas("Check has expired");
						Bank.getAccount(result).setAccTrans(newTrans);
					} else if (result2 == 1) {
						// check is on time and valid
						result3 = Bank.getAccount(result).makeWithdrawal(value);
						if (result3 == -2) {
							out.println("*Error*");
							out.println("Transaction Type: Clear Check");
							out.println("Account Number: " + accNum);
							out.println("Insufficient Funds");

							Transaction4 newTrans = new Transaction4();
							newTrans.setTranType("Clear Check");
							newTrans.setWorks(false);
							newTrans.setFailReas("Insufficient Funds");
							newTrans.setTranVal(-value);
							Bank.getAccount(result).setAccTrans(newTrans);
						} else if (result3 == -1) {
							out.println("*Error*");
							out.println("Transaction Type: Clear Check");
							out.println("Account Number :" + accNum);
							out.println("Negative withdraw amount (" + value + ") was entered");

							Transaction4 newTrans = new Transaction4();
							newTrans.setTranType("Clear Check");
							newTrans.setWorks(false);
							newTrans.setFailReas("Invalid amount entered");
							Bank.getAccount(result).setAccTrans(newTrans);
						} else if (result3 == 1) {
							out.println("Receipt");
							out.println("Transaction Type: Clear Check");
							out.println("Check Cleared");
							out.println("Account Number: " + accNum);
							out.println("Current Balance: " + Bank.getAccount(result).getAccBal());

							Transaction4 newTrans = new Transaction4();
							newTrans.setTranType("Clear Check");
							newTrans.setWorks(true);
							newTrans.setTranVal(-value);
							Bank.getAccount(result).setAccTrans(newTrans);
						}
					}
				} else {
					out.println("*Error*");
					out.println("Transaction Type: Clear Check");
					out.println("Account number " + accNum + " is not a Checking account");
					out.println("Only checking accounts can clear checks");

					Transaction4 newTrans = new Transaction4();
					newTrans.setTranType("Clear Check");
					newTrans.setWorks(false);
					newTrans.setFailReas("Account cannot use checks");
					Bank.getAccount(result).setAccTrans(newTrans);
				}
			}
		}
	}

	/*
	 * 	Input: 
	 * Bank - Bank4 object with account information in an arrayList 
	 * and number of accounts 
	 * in - Scanner for reading input from a file
	 * out - PrintWriter to print to an output file 
	 * 	Process: 
	 * Takes in a valid account number and asks for a 
	 * valid deposit amount and deposits amount if valid
	 * 	Output:
	 * Adds a valid amount to the accounts balance
	 */
	public static void deposit(Bank4 Bank, Scanner in, PrintWriter out) {
		int result, accNum, result2, result3;
		double value;

		System.out.println("Transaction Type: Deposit");
		System.out.println("Enter the accounts number: ");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			// Account not found
			out.println("*ERROR*");
			out.println("Transaction Type: Deposit");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			// Invalid account number
			out.println("*Error*");
			out.println("Transaction Type: Deposit");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else {
			if (Bank.getAccount(result).getAccStat() == false) {
				// Account closed Error
				out.println("*Error*");
				out.println("Transaction Type: Deposit");
				out.println("Account number: " + accNum);
				out.println("Account number " + accNum + "is a closed account");
				out.println("You must open it before making changes to it");

				Transaction4 newTrans = new Transaction4();
				newTrans.setTranType("Deposit");
				newTrans.setWorks(false);
				newTrans.setFailReas("Account was closed");
				Bank.getAccount(result).setAccTrans(newTrans);
			} else {
				System.out.println("Enter the deposit amount: ");
				value = in.nextDouble();
				if (Bank.getAccount(result).getAccType().equals("CD")) {
					result2 = Bank.getAccount(result).getAccDate().checkCD();
					if (result2 == -1) {
						// CD not matured wait longer
						out.println("*Error");
						out.println("Transaction Type: Deposit");
						out.println("Account number: " + accNum);
						out.println("CD has not yet matured enough");

						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Deposit");
						newTrans.setWorks(false);
						newTrans.setFailReas("CD has not matured yet");
						Bank.getAccount(result).setAccTrans(newTrans);
					} else {
						// use make a deposit
						result3 = Bank.getAccount(result).makeDeposit(value);
						if (result3 == -1) {
							// negative number entered for deposit amount
							out.println("*Error*");
							out.println("Transaction Type: Deposit");
							out.println("Account number: " + accNum);
							out.println("Negative deposit amount (" + value + ") was entered");

							Transaction4 newTrans = new Transaction4();
							newTrans.setTranType("Deposit");
							newTrans.setWorks(false);
							newTrans.setFailReas("Invalid amount entered");
							Bank.getAccount(result).setAccTrans(newTrans);
						} else if (result3 == 1) {
							// New mat date
							System.out.println("Select a new maturity date");
							System.out.println("Enter for 6, 12, 18 or 24 months from now");
							dateInfo4 newDate = new dateInfo4();
							Bank.getAccount(result).setAccDate(newDate);
							Bank.getAccount(result).getAccDate().addMonths(in.nextInt());
							out.println("Reciept");
							out.println("Transaction Type: Deposit");
							out.println("Account number: " + accNum);
							out.println("Current Balance: " + Bank.getAccount(result).getAccBal());
							dateInfo4 myDate = new dateInfo4(Bank.getAccount(result).getAccDate());
							out.println("New maturity date: " + myDate.getMonth() + "/" + myDate.getDay() + "/"
									+ myDate.getYear());

							Transaction4 newTrans = new Transaction4();
							newTrans.setTranType("Deposit");
							newTrans.setWorks(true);
							newTrans.setTranVal(+value);
							Bank.getAccount(result).setAccTrans(newTrans);
						}
					}
				} else {
					// Regular Deposit
					result3 = Bank.getAccount(result).makeDeposit(value);
					if (result3 == -1) {
						// Negative amount entered
						out.println("*Error*");
						out.println("Transaction Type: Deposit");
						out.println("Account number: " + accNum);
						out.println("Negative deposit amount (" + value + ") was entered");

						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Deposit");
						newTrans.setWorks(false);
						newTrans.setFailReas("Invalid amount entered");
						Bank.getAccount(result).setAccTrans(newTrans);
					} else if (result3 == 1) {
						// Deposit Successful
						out.println("Receipt");
						out.println("Transaction Type: Deposit");
						out.println("Account number: " + accNum);
						out.println("Amount Deposited: " + value);
						out.println("Current Balance: " + Bank.getAccount(result).getAccBal());

						Transaction4 newTrans = new Transaction4();
						newTrans.setTranType("Deposit");
						newTrans.setWorks(true);
						newTrans.setTranVal(+value);
						Bank.getAccount(result).setAccTrans(newTrans);
					}
				}
			}
		}
	}

	/*
	 * 	Input: Bank - Bank4 object with account information in an arrayList and
	 * number of accounts 
	 * in - Scanner for reading input from a file 
	 * out - PrintWriter to print to an output file 
	 * 	Process:
	 * Takes in a valid account number and asks for a valid withdrawal 
	 * amount and withdraws amount if valid
	 * 	Output: 
	 * Takes out a valid amount from the accounts balance
	 */
	public static void withdrawal(Bank4 Bank, Scanner in,PrintWriter out) {
		int result, accNum, result2;
		double value;

		System.out.println("Transaction Type: Withdrawal");
		System.out.println("Enter the accounts number: ");
		accNum = in.nextInt();
		result = Bank.findAcct(accNum);
		if (result == -1) {
			out.println("*ERROR*");
			out.println("Transaction Type: Withdraw");
			out.println("Account number (" + accNum + ") does not exist");
		} else if (result == -2) {
			out.println("*ERROR*");
			out.println("Transaction Type: Withdraw");
			out.println("The Account number (" + accNum + ") is not within range");
			out.println("Try again with a number from 100000-999999");
		} else {
			if (Bank.getAccount(result).getAccStat() == false) {
				// Account closed Error
				out.println("*Error*");
				out.println("Transaction Type: Withdraw");
				out.println("Account number: " + accNum);
				out.println("Account number " + accNum + "is a closed account");
				out.println("You must open it before making changes to it");

				Transaction4 newTrans = new Transaction4();
				newTrans.setTranType("Withdraw");
				newTrans.setWorks(false);
				newTrans.setFailReas("Account was closed");
				Bank.getAccount(result).setAccTrans(newTrans);
			} else {
				System.out.println("Enter the withdrawal amount: ");
				value = in.nextDouble();
				result2 = Bank.getAccount(result).makeWithdrawal(value);
				if (result2 == -1) {
					// negative number entered for deposit amount
					out.println("*Error*");
					out.println("Transaction Type: Withdraw");
					out.println("Account number: " + accNum);
					out.println("Negative withdrawal amount (" + value + ") was entered");

					Transaction4 newTrans = new Transaction4();
					newTrans.setTranType("Withdraw");
					newTrans.setWorks(false);
					newTrans.setFailReas("Invalid amount entered");
					Bank.getAccount(result).setAccTrans(newTrans);
				} else if (result2 == -2) {
					out.println("*Error*");
					out.println("Transaction Type: Clear Check");
					out.println("Account Number: " + accNum);
					out.println("Insufficient Funds");

					Transaction4 newTrans = new Transaction4();
					newTrans.setTranType("Withdraw");
					newTrans.setWorks(false);
					newTrans.setFailReas("Insufficient Funds");
					newTrans.setTranVal(-value);
					Bank.getAccount(result).setAccTrans(newTrans);
				} else if (result2 == 1) {
					out.println("Receipt");
					out.println("Transaction Type: Withdraw");
					out.println("Account Number: " + accNum);
					out.println("Amount Withdrawn: " + value);
					out.println("Current Balance: " + Bank.getAccount(result).getAccBal());

					Transaction4 newTrans = new Transaction4();
					newTrans.setTranType("Withdraw");
					newTrans.setWorks(true);
					newTrans.setTranVal(value);
					Bank.getAccount(result).setAccTrans(newTrans);
				}
			}
		}
	}

}
