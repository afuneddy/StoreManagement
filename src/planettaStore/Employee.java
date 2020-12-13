/**
 * 
 */
package planettaStore;


import security.SecurityAcc;
import access.EmployeeAcc;

/**
 * @author Edward
 *
 */
public class Employee {

	

	
	public int login(String password, String employeeID) 
	{
		throw new UnsupportedOperationException();
		
		
		
		
	}

	public int logout(String password, String employeeID) {
		throw new UnsupportedOperationException();
	}

	


	public void authenticate() {
		throw new UnsupportedOperationException();
		
		
		
		
	}

	public boolean registerEmployee(int empID, String fName, String otherNames, String userName, int phone, String address, String email, java.sql.Date birthDate, String birthPlace, int nic, int roleid, int salary, java.sql.Date recruitDate, String password) {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		
		
		
	return ema.registerEmployee(empID,

			fName, otherNames,

			userName,

			phone, address,

			email,

			birthDate,

			birthPlace,

			nic, roleid, salary,

			recruitDate,

			password);
	
}

	public int generateID() {
		// TODO Auto-generated method stub#
		EmployeeAcc ema = new EmployeeAcc();
		int lastID =	ema.generateID();
		if(lastID == 0)
			lastID = 0;
		int newID = lastID +1;
		return newID;
	}

	public String[] getEmpInfo(int empID) {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		return ema.getEmpInfo(empID);
	}

	

	public int checkEmpID(int empID) {
		EmployeeAcc ema = new EmployeeAcc();
		return ema.checkEmpID(empID);
	}

	public boolean updateEmployee(int empID, String fName, String otherNames,
			String userName, int phone, String address, String email,
			java.sql.Date birthDate, String birthPlace, int nic, int roleid, int salary,
			java.sql.Date recruitDate, String password) {
		EmployeeAcc ema = new EmployeeAcc();
		return ema.updateEmployee(

				empID,

				fName, otherNames,

				userName,

				phone, address,

				email,

				birthDate,

				birthPlace,

				nic, roleid, salary,

				recruitDate,

				password

				);
	}

	public String[][] searchString(String text) {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		return ema.searchString(text);
	}

	public String[][] populateCombo() {
		EmployeeAcc ema = new EmployeeAcc();
		return ema.getAllEmpRoles() ;
		
	}

	

	public String[][] getAllEmp() {
		EmployeeAcc ema = new EmployeeAcc();
		return ema.getAllEmp();
	}

	public String getEmpFName(int parseInt) {
		// TODO Auto-generated method stub
		
		EmployeeAcc ema = new EmployeeAcc();
		return ema. getEmpFName(parseInt);
	}

	public int generateRoleID() {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		int lastID =	ema.generateRoleID();
		
		int newID = lastID +1;
		return newID;
	}

	public boolean registerRole(int roleid, String role) {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		 return ema. registerRole(roleid, role);
	}

	public String[][] getAllEmpRoles() {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		return ema.getAllEmpRoles();
	}

	public String[] getEmpRoleInfo(int empID) {
		// TODO Auto-generated method stub
		EmployeeAcc ema = new EmployeeAcc();
		return ema.getEmpRoleInfo(empID);
	}

	public boolean updateRole(int roleid, String role) {
		// TODO Auto-generated method stub
		SecurityAcc s = new SecurityAcc();
		return s.updateRole(roleid,  role);
	}}
