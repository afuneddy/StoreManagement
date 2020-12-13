/**
 * 
 */
package planettaStore;

import access.SupplierAcc;

/**
 * @author Edward
 *
 */
public class Supplier {

	
	
	public Supplier(){}
	
	
	public int getSupID(int supID) {
		SupplierAcc sa= new SupplierAcc();
		return sa.getSupID(supID);
	}




	public String[][] searchString(String text) {
		SupplierAcc sa= new SupplierAcc();
		return sa.searchString(text);

	}


	public int generateID() {
		SupplierAcc sa= new SupplierAcc();
		return sa.generateID() + 1 ;
	}


	public boolean registerSupplier(int supID, String fName, String otherNames,
			String bzName, int phone1, int phone2, String address,
			String email, String sampleProds, int grade) {
		SupplierAcc sa= new SupplierAcc();
		return sa.registerSupplier(supID, fName, otherNames,
				 bzName,phone1, phone2, address,
				email, sampleProds,grade);
	}


	public boolean updateSupplier(int supID, String fName, String otherNames,
			String bzName, int phone1, int phone2, String address,
			String email, String sampleProds, int grade) {
		SupplierAcc sa= new SupplierAcc();
		return sa.updateSupplier(supID, fName, otherNames,
				bzName,phone1, phone2, address,
				email, sampleProds, grade);
	}



	public String[][] getAllSup() {
		SupplierAcc sa= new SupplierAcc();
		return sa.getAllSup();	}


	public String[] getSupInfo(int supID) {
		SupplierAcc sa= new SupplierAcc();return sa.getSupInfo(supID);
	}
	
	
}


