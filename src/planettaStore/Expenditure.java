package planettaStore;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

import access.ExpenditureAcc;

public class Expenditure {
	
	String _expenditureID;
	String _purpose;
	String _amount;
	Date _date;
	Timer _time;


	public void search() {
		throw new UnsupportedOperationException();
	}

	public void getForm() {
		throw new UnsupportedOperationException();
	}

	public void checkValidate() {
		throw new UnsupportedOperationException();
	}

	public void getTotal() {
		throw new UnsupportedOperationException();
	}

	public int generateID() {
		// TODO Auto-generated method stub
		
		ExpenditureAcc ea=new ExpenditureAcc();
				
		return ea.generateID() + 1;}

	public boolean registerExpenditure(int expID, int empID, int amount,
			String target, String reason) {
		// TODO Auto-generated method stub
	Inventory in = new Inventory();
	in.insertDateNow();
		ExpenditureAcc ea=new ExpenditureAcc();
		return ea.registerExpenditure(expID, empID, amount,
			target, reason,  in.insertDateNow(), in.insertDateNow());
	}

	public int checkExpID(int expID) {
		// TODO Auto-generated method stub
		ExpenditureAcc ea=new ExpenditureAcc();
		return ea.checkExpID(expID);
	}

	public String[] getPurInfo(int expID) {
		// TODO Auto-generated method stub
		ExpenditureAcc ea=new ExpenditureAcc();
		return ea.getExpInfo( expID);
	}


	public String[][] getAllExp() {
		ExpenditureAcc ea=new ExpenditureAcc();
		return ea.getAllExp();

	}

	public boolean updateExpenditure(int expID, int empID, int amount,
			String target, String reason, Date date, Time time) {
		ExpenditureAcc ea=new ExpenditureAcc();
		return ea.updateExpenditure(expID, empID, amount, target, reason, date, time);
	}

	public float getTotalExp(java.sql.Date frmDate, java.sql.Date toDate,
			Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		ExpenditureAcc ea=new ExpenditureAcc();
		
		return ea.getTotalExp( frmDate, toDate,
				frmTime, toTime);
	}

	public String[][] getExp(java.sql.Date frmDate, java.sql.Date toDate,
			Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
ExpenditureAcc ea=new ExpenditureAcc();
		
		return ea.getExp( frmDate, toDate,
				frmTime, toTime);
	}

	public String[][] getMonthlyExp(java.sql.Date frmDate,
			java.sql.Date toDate, Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
ExpenditureAcc ea=new ExpenditureAcc();
		
		return ea.getMonthlyExp( frmDate, toDate,
				frmTime, toTime);
	}

	public String[][] getDailyExp(java.sql.Date frmDate, java.sql.Date toDate,
			Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
ExpenditureAcc ea=new ExpenditureAcc();
		
		return ea.getDailyExp( frmDate, toDate,
				frmTime, toTime);
	}
	}

