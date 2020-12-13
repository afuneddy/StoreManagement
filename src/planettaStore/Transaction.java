 package planettaStore;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import access.TransactionAcc;
	
	public class Transaction {
		protected String _transID;
		protected String _transDate;
		protected String _transTime;
		protected String _payMethod;
		protected Float amountPayable=(float) 0;
		protected String _transType;
		
		protected float amountGiven;
		protected float change;
		

		public void search() {
			throw new UnsupportedOperationException();
		}

		

		public void checkValidate() {
			throw new UnsupportedOperationException();
		}

		public void pay() {
			throw new UnsupportedOperationException();
		}

		

	


		public int getTransID() {
			// TODO Auto-generated method stub
			TransactionAcc ta = new TransactionAcc();
			return ta.getLastID() +1;
		}



		public boolean createTrans(int transID, String transType, int amount, int sp, int custID, String custName) {
			TransactionAcc ta = new TransactionAcc();
			
			Inventory i = new Inventory();
			Timestamp date = i.insertDateNow();
			Timestamp time =i.insertDateNow();
			//String transType = "retailPaid";
			return ta.createTrans(transID,transType, amount, date, time,   sp, custID, custName);
			
		}



		


		public String[] getTransDT(int parseInt) {
			// TODO Auto-generated method stub
			TransactionAcc ta = new TransactionAcc();
			return ta.getTransDT(parseInt);
		}



		public String[][] getAllTrans(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			// TODO Auto-generated method stub
			TransactionAcc ta = new TransactionAcc();
			return ta.getAllTrans(frmDate, toDate, frmTime,
					toTime);
		}



		public String[] getTrans(int transID, String transType, int empID) {
			// TODO Auto-generated method stub
			TransactionAcc ta = new TransactionAcc();
			return ta.getTrans(transID, transType, empID);
		}

}
