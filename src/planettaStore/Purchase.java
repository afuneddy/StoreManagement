package planettaStore;

import interfaceClass.IsDate;

import java.sql.Time;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import access.PurchaseAcc;



public class Purchase {
	

	

	public void delete() {
		throw new UnsupportedOperationException();
	}
	
	public int checkPurchID(int purchID) 
	{
		
		PurchaseAcc pa = new PurchaseAcc();
		return pa.checkPurchID(purchID) ;
	}
	
	public String getBzName(int id)
	{PurchaseAcc pa = new PurchaseAcc();
	return pa.getBzName(id);
	}
	public Date getPurchDate(int purchID){
		
		PurchaseAcc pa = new PurchaseAcc();
		return pa.checkPurchDate(purchID) ;
		
		
	}

	public String[][] searchString(String text) {
	Supplier s= new Supplier();
		return s.searchString(text) ;
	}

	public int generateID() {
		PurchaseAcc pa= new PurchaseAcc();
		return pa.generateID() + 1 ;
	}

	public boolean registerPurchase(int purID, int suID, int tCost,
			int advance, java.sql.Date dueDate, java.sql.Date date) {
		Inventory in = new Inventory();
		
		PurchaseAcc pa= new PurchaseAcc();
		return pa.registerPurchase(purID,suID, tCost,
				advance, dueDate,  date,  in.insertDateNow());
	}

	public boolean checkValues(int advance, int tCost, JTextField dueDateT,JTextField advanceT, String dueDates) {
		IsDate id=new IsDate();
		if(advance < tCost)
		{
		if((!(dueDateT.getText().length() >0 )) || !(id.isDate(dueDates))){
			JOptionPane.showMessageDialog(null, "Due date payment required.");
		dueDateT.setText(null);dueDateT.requestFocus();return false;}}
		else if(advance == tCost){
			dueDateT.setText(null);return true;}
		else if(advance > tCost){
			JOptionPane.showMessageDialog(null, "Advanced payment can't be more than total cost of purchase.");
		advanceT.setText(null);
		
		return false;}
		return true;
		
		
	}

	

	public String[] getPurInfo(int purID) {
		PurchaseAcc pa = new PurchaseAcc();
		
		
		String[] Arr = pa.getPurInfo(purID);
		
		Arr[7] = pa.getBzName(Integer.parseInt(Arr[1]));
		
		return Arr;
	}
/*
	public int getPurCount() {
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getPurCount();
	}
*/

	public String[][] getAllPur() {
		
		PurchaseAcc pa = new PurchaseAcc();
		
		String[][] all= pa.getAllPur();
		int c =all.length;
		for(int r=0;r<c; r++)
			//JOptionPane.showMessageDialog(null, all[r][1]);
			all[r][7]= pa.getBzName(Integer.parseInt(all[r][1]));
		return all;
	}

	public boolean updatePurchase(int purID, int supID, int advance,
			int tCost, java.sql.Date dueDate, java.sql.Date date) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
			Inventory in = new Inventory();
			if((advance + pa.getPayment(purID)) >= tCost)
				{JOptionPane.showMessageDialog(null, "You might have paid more than the total cost of this purchase.\nReview total cost of purchase and total amount paid.");
				
				return false;
				}
			else
		return pa.updatePurchase(purID,supID,advance,
				tCost, dueDate,  date,in.insertDateNow());
			
	}

	public int getTotDebt(int id) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		
		return pa.getTotDebt( id) ;
	}

	public int getSup(int purchID) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getSup( purchID);
	}
	public int getPurchaseCost(int purchID) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getPurchaseCost( purchID);
	}

	public float getTotalPurch(java.sql.Date frmDate, java.sql.Date toDate,
			Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getTotalPurch(  frmDate,  toDate,  frmTime,  toTime);
	}

	public String[][] getPurch(java.sql.Date frmDate, java.sql.Date toDate,
			Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
PurchaseAcc pa = new PurchaseAcc();
		String[][] all= pa.getAllPur(frmDate,  toDate,  frmTime,  toTime);
		int c =all.length;
		for(int r=0;r<c; r++)
			//JOptionPane.showMessageDialog(null, all[r][1]);
			all[r][7]= pa.getBzName(Integer.parseInt(all[r][1]));
		
		
		return all;
	}

	public String[][] getDailyPurch(java.sql.Date frmDate,
			java.sql.Date toDate, Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getDailyPurch (  frmDate,  toDate,  frmTime,  toTime);
	}

	public String[][] getMonthlyPurch(java.sql.Date frmDate,
			java.sql.Date toDate, Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getMonthlyPurch (  frmDate,  toDate,  frmTime,  toTime);
	}

	public String[][] unpaidDaily(java.sql.Date frmDate, java.sql.Date toDate, Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.unpaidDaily (  frmDate,  toDate,  frmTime,  toTime);
	}

	public String[][] unpaidMonthlyly(java.sql.Date frmDate,
			java.sql.Date toDate, Time frmTime, Time toTime) {
		// TODO Auto-generated method stub
		PurchaseAcc pa = new PurchaseAcc();
		return pa.getUnpaidMonthly(frmDate, toDate, frmTime, toTime) ;
	}
	
}