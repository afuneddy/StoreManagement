package planettaStore;

import java.sql.Date;
import java.sql.Time;

import access.AccessClass;
import access.WHouseInventoryAccess;



	public class RetailInventory extends Inventory {
		
		static int prodRetID = 13000;
		int retailPrice;
		int prodRetQty;
		String supplierID;
		String description;
		String location;
		int limit;
		static float customerQty;
		float grandTotal ;
		float gtot;
		float prodTot;
		
		Transaction retailTrans =  new Transaction();
		public RetailInventory()
		{}
		
		public static void main (String args[])
		{
	//RetailInventory ri = new RetailInventory();
	
	//System.out.println("Retail desc = " + ri.insertProdBelowLim(130003));
		
		}
		/*public void insertProdBelowLim(int prodRID) {
			int prodRetID = prodRID;
		
			int qtyLeft = getQuantity(prodRetID);
			int limLevel = getLimitLevel(prodRetID);
		String retDes = getProductDescription(prodRetID);

		 Inventory i = new Inventory();
		 Timestamp insertDate = i.insertDateNow();
			AccessClass.insertRetProdBelowLim(prodRetID, retDes, qtyLeft, limLevel, insertDate  );
		
		}*/
	
		public int getQuantity(int prodRetID) {
			
			return AccessClass.getRetQuantity(prodRetID);
		}
		public int getLimitLevel(int prodRetID) {
			
		return	 AccessClass.getRetLimitLevel(prodRetID);
		}
		public static int getProductRetailPrice(int prodRetID)
		{
			return access.AccessClass.retrieveRetailPrice(prodRetID);
			
			
		}
		public static float productTotal(int prodRetID, int prodRetQty)
		{
			
			float total = getProductRetailPrice(prodRetID)* prodRetQty;
	return total;

		}
		public float grandTotal(float total,  float gtot)
		{
	
		
			
		 return  gtot = gtot + total;
		 
		}


		public static String getProductDescription(int prodRetID) {
			return access.AccessClass.retrieveDescription(prodRetID);
		}
	
		
		public void deductInventory(int prodretid, int qty)
		{
			access.AccessClass.reduceQtyDB(prodretid, qty);
			
		}
		
		public int checkProdRID(int prodretid)
		{
		
			return AccessClass.checkProdRetidDB(prodretid);
		}
		
		public int generateID()
		{
			AccessClass ac = new AccessClass();
		int lastID =	ac.generateCode();
		if(lastID == 0)
			lastID = 130000;
		int newID = lastID +1;
		return newID;
		}
		
		
		//register new retail item on database (retail inventory table) and also insert retail code and product id onto the retprodids table
		

		public boolean registerNewRetProd(int prodRID, String retDesc, int prodID, int qty,
				int retPrice, String loca, int limLev) {
			// TODO Auto-generated method stub

			AccessClass ac = new AccessClass();
			

		return ac.registerNewProd(prodRID,prodID,retDesc, qty,retPrice, loca, limLev);
		}
		public boolean delete(int prodID) {
			AccessClass ac = new AccessClass();
			return ac.deleteRow(prodID);
		}

		public void saleFinalisation(int prodRid, int transID, String retDesc, int qty, int retPrice, int tot) {
			AccessClass ac = new AccessClass();
			boolean flag = true;
			//String transType = "Retail_Paid";
		
			if (getQuantity(prodRid) - getLimitLevel(prodRid) < 0)
			flag = false;
			ac.registerSoldItems( prodRid, transID,  qty, retPrice);
			deductInventory(prodRid, qty);
			
			if ((getQuantity(prodRid) - getLimitLevel(prodRid) < 0) && flag == true)
			{Inventory in = new Inventory();
ac.insertShortageDT(prodRid, in.insertDateNow(),in.insertDateNow());
			}
			
		}

		public int searchItem(String retDesc) {
			AccessClass ac = new AccessClass();
			return ac.searchItem(retDesc);
			// TODO Auto-generated method stub
			
		}
		

		public String[][] searchString(String text) {
			AccessClass ac = new AccessClass();
			return ac.searchString(text);
		}

		public String[] getProdRetInfo(int prodRetID) {
			AccessClass ac = new AccessClass();
			return ac.getProdInfo(prodRetID);
		}

		public int getProdID(int prodRetID) {
		WHouseInventoryAccess whia = new WHouseInventoryAccess();
		
			return whia.getProdID( prodRetID);
		}

		public boolean updateRetInv(int prodRetID2, String retDesc, int prodID,
				int qty, int retPrice, String loc, int limLev) {
			// TODO Auto-generated method stub
			
			Date  date = null;
			Time time = null;
			
			
			AccessClass ac = new AccessClass();
			boolean res= ac.updateRetInv(prodRetID2,  retDesc,prodID,
					qty, retPrice,  loc, limLev);
			
			if (getQuantity(prodRetID2) >= getLimitLevel(prodRetID2))
				
				ac.updateShortageDate(prodRetID2, date, time);
			return res;
				
		}

	

		public String[][] getAllretItems() {
			// TODO Auto-generated method stub
			 AccessClass ac = new AccessClass();
			return ac.getAllretItems();
		}

		public String[][] searchProd(String text) {
			WHouseInventoryAccess whi = new WHouseInventoryAccess();
			return whi.searchProd(text);
		}

		public String[][] searchRetDesc(String text) {
			 AccessClass ac = new AccessClass();
			 return ac.searchString(text);
		}

		/*public int salesCount(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			// TODO Auto-generated method stub
			
			 AccessClass ac = new AccessClass();
			return ac.salesCount(frmDate, toDate,frmTime,
					toTime);
		}
*/
	/*	public int countTransID(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			AccessClass ac = new AccessClass();
			return ac.countTransID(frmDate, toDate,frmTime,
					toTime);
		}*/

		public String[][] getSales(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			AccessClass ac = new AccessClass();
			return ac.getSales(frmDate, toDate,frmTime,
					toTime);
			
			
		}

		public String[][] getDailySales(Date frmDate, Date toDate,
				Time frmTime, Time toTime) {
			AccessClass ac = new AccessClass();
			return ac.getDailySales(frmDate, toDate,frmTime,
					toTime);
		}
		public String[][] getDailyGProfit(Date frmDate, Date toDate,
				Time frmTime, Time toTime) {
			AccessClass ac = new AccessClass();
			return ac.getDailyGProfit(frmDate, toDate,frmTime,
					toTime);
		}

	
		
		public String[][] getMonthlySales(Date frmDate, Date toDate,
				Time frmTime, Time toTime) {
			AccessClass ac = new AccessClass();
			return ac.getMonthlySales(frmDate, toDate,frmTime,
					toTime);
		}
		public String[][] getMonthlyGProfit(Date frmDate, Date toDate,
				Time frmTime, Time toTime) {
			// TODO Auto-generated method stub
			AccessClass ac = new AccessClass();
			return ac.getMonthlyGProfit(frmDate, toDate,frmTime,
					toTime);
		}

		public String[][] getRetailShortage() {
			// TODO Auto-generated method stub
			AccessClass ac = new AccessClass();
			return ac.getRetailShortage();
		}


		public float getTotalGProfit(Date frmDate, Date toDate,
				Time frmTime, Time toTime) {
			// TODO Auto-generated method stub
			AccessClass ac = new AccessClass();
			return ac.getTotalGProfit(frmDate, toDate,frmTime,
					toTime);
		}

		public float getTotalSales(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			// TODO Auto-generated method stub
			AccessClass ac = new AccessClass();
			return ac.getTotalSales(frmDate, toDate,frmTime,
					toTime);
		}

		public String[] checkDoubleReg(int prodID, int prodRID) {
			// TODO Auto-generated method stub
			AccessClass ac = new AccessClass();
			return ac.checkDoubleReg(prodID,prodRID);
		}

		
		
		
		}
		

