package planettaStore;

import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JTable;

import access.AccessClass;

public class Inventory {
	protected String _productID;
	protected String _name;
	protected String _description;
	protected String _category;
	protected int _uCP;
	protected int _quantity;
	protected String _location;

	public  Inventory()
	{}
	public void getForm() {
		throw new UnsupportedOperationException();
	}

	public void register() {
		throw new UnsupportedOperationException();
	}

	public int getQuantity() {
		throw new UnsupportedOperationException();
	}
	public int getLimitLevel() {
		throw new UnsupportedOperationException();
	}
	public void insertProdBelowLim() {
		throw new UnsupportedOperationException();
	}

	public void search() {
		throw new UnsupportedOperationException();
	}

	public void limitAlert() {
		throw new UnsupportedOperationException();
	}

	public void checkvalidate() {
		throw new UnsupportedOperationException();
	}

	public void update() {
		throw new UnsupportedOperationException();
	}

	public boolean delete(int prodID) {
	AccessClass ac = new AccessClass();
		return ac.deleteRow(prodID);
	}

	public void getLocation() {
		throw new UnsupportedOperationException();
		
	}
	public int prodTot(int sPrice, int qty)
	{
		return sPrice*qty;
	}
	public float grandTotMinusPTot(Float oldGrandTot, int oldPTot)
	{
		return oldGrandTot - oldPTot;
	}
	
	public int searchProdIdTable(int prodRetID, JTable table) {

		int pid = prodRetID;
		int w = -1;
		int r;
		int rc = table.getRowCount();

		int c = 1;

		for (r = 0; r < rc; r++) {

			int id = (int) table.getValueAt(r, c);
			//int id = Integer.parseInt((String) table.getValueAt(r, c));

			if (pid == id) {
				w = r;
				break;
			}

		}

		return w;

	}
	public Timestamp insertDateNow()
	{
		Date dateNow = new Date();
		return new Timestamp (dateNow.getTime());
		
	
	}
	
	/*
	public int year()
	{Date dateNow = new Date();
	return  Timestamp(dateNow.getTime());
		
		*/
		
	
	
}