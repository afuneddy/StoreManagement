package planettaStore;

import access.CustomerAcc;

public class Customer {
	protected String _customerID;
	protected String _firstName;
	protected String _otherNames;
	protected String _address;
	protected int _phone;
	protected String _email;
	protected String _rating;


	public void getForm() {
		throw new UnsupportedOperationException();
	}

	public void checkValidate() {
		throw new UnsupportedOperationException();
	}

	public boolean registerCustomer(int cusID, String fName, String otherNames,
			int phone, String address, String email, int nic, int grade) {
		CustomerAcc ca= new CustomerAcc();
		return ca.registerCustomer(cusID,fName,otherNames,
				phone, address, email, nic, grade);
	}

	public int generateID() {
		CustomerAcc ca= new CustomerAcc();
		return ca.generateID()+1;
	}

	public int checkCusID(int cusID) {
		CustomerAcc ca= new CustomerAcc();
		return ca.checkCusID(cusID);
	}

	public String[] getCusInfo(int cusID) {
		CustomerAcc ca= new CustomerAcc();
		return ca.getCusInfo(cusID);	}

	

	public String[][] getAllCus() {
		CustomerAcc ca= new CustomerAcc();
	return ca.getallCus();}

	public String[][] searchString(String text) {
		CustomerAcc ca= new CustomerAcc();
		return ca.searchString(text);
	}

	public boolean updateCustomer(int empID, String fName, String otherNames,
			int phone, String address, String email, int nic, int grade) {
		CustomerAcc ca= new CustomerAcc();
		return ca.updateCustomer(empID, fName, otherNames,
			 phone, address,email, nic, grade);
	}
}
