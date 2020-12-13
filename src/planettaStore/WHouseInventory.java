package planettaStore;

import java.sql.Time;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import access.PurchaseAcc;
import access.WHouseInventoryAccess;
import access.WSaleAccess;




public class WHouseInventory extends Inventory {
	
	public Purchase _registers;

	public  WHouseInventory(){}

	public int searchProdIdTable(int prodID, String wsUnit, JTable table) {

		int pid = prodID;
		int w = -1;
		int r;
		int rc = table.getRowCount();

		int c = 1;

		for (r = 0; r < rc; r++) {

			int id = (int) table.getValueAt(r, c);
			//int id = Integer.parseInt((String) table.getValueAt(r, c));

			if ((pid == id) && (getDescription(prodID, wsUnit).equals(table.getValueAt(r, 2).toString()))){
				w = r;
				break;
			}

		}

		return w;

	}

	public int checkProdId(int prodID)
	{
		WSaleAccess wsa = new WSaleAccess();

		return wsa.checkProdID(prodID);
	}



	public  int getWholesalePrice(int prodID, String wsPrice) { //retrieves product wholesale price from database and sent back to WSaleWindow

		
		if(wsPrice.equals("LwuSp"))
			return getSellingPriceLwu(prodID);
		else 
		
				return getSellingPriceHwu(prodID);
			
	}



	public  String getDescription(int prodID, String wsUnit) {

		
		if(wsUnit.equals("LwuSp"))
			return getDescriptionLwu(prodID);
		else 
			
				return getDescriptionHwu(prodID);
			
	}



	public float productTotal(int prodID, int prodWSQty, String wsUnit) {

		float total = getWholesalePrice(prodID, wsUnit)*  prodWSQty;
		return total;

	}

	public void deductInventory(int prodid, int qty,String  wsUnit)
	{WSaleAccess wsa = new WSaleAccess();
	if(wsUnit.equals("LwuSp"))
		wsa.reduceQtyLwu(prodid, qty);
	else if(wsUnit.equals("HwuSp"))
		wsa.reduceQtyHwu(prodid, qty);
	else JOptionPane.showMessageDialog(null, "Specify wholesale unit");
	}




	public float grandTotal(float total, float gtot) {
		
		return  gtot = gtot + total;
	}



	public int getLimitLevel(int prodID)
	{
		WSaleAccess wsa = new WSaleAccess();

		return wsa.getLimitLevel(prodID);

	}


	public int getQuantity(int prodID, String wsUnit) {

		WSaleAccess wsa = new WSaleAccess();
		if(wsUnit.equals("LwuSp"))
			return wsa.getQuantityLwu(prodID);
		else if(wsUnit.equals("HwuSp"))
			return wsa.getQuantityHwu(prodID);
		else{
			JOptionPane.showMessageDialog(null, "Specify wholesale unit type");
			return 0;}
	}



	public boolean delete(int prodID) {
		WSaleAccess wsa = new WSaleAccess();
		wsa.deleteRow(prodID);
		return false;
	}


	private String getDescriptionHwu(int prodID) {
		WSaleAccess wsa = new WSaleAccess();
		return wsa.getDescriptionHwu(prodID);
	}

	private int getSellingPriceLwu(int prodID) {
		WSaleAccess wsa = new WSaleAccess();
		return wsa.getSellingPriceLwu(prodID);
	}
	private int getSellingPriceHwu(int prodID) {
		WSaleAccess wsa = new WSaleAccess();
		return wsa.getSellingPriceHwu(prodID);
	}

	private String getDescriptionLwu(int prodID) {
		WSaleAccess wsa = new WSaleAccess();
		return wsa.getDescriptionLwu(prodID);
	}

	public int generateID() {

		WSaleAccess wsa = new WSaleAccess();

		int lastID =	wsa.generateCode();
		int newID = lastID +1;
		return newID;
	}

	public void registerKeys(int prodID,   int purchID, java.sql.Date expDate)
	{WHouseInventoryAccess whia = new WHouseInventoryAccess();

	whia.registerKeys(prodID, purchID, expDate);

	}

	public int registerWSaleInventory(int prodID, int supID, int purchID, int noRetUperLwu, String lwu, int lwuQty, int lwuUcp, int lwuSp,
			int lwupHwu, String hwu, int hwuQty, int hwuUcp, int hwuSp,
			int limitLevel, int tCost, int tQtyLwu, String cat, int retPrice, String loc,
			java.sql.Date purchDate, java.sql.Date expDate) {
		WHouseInventoryAccess whia = new WHouseInventoryAccess();
		int ack = 0;

		Purchase p=  new Purchase();
		int retP = retPrice;
		int	 lwQ = lwuQty;
		int lSp = lwuSp;
		int lCp = lwuUcp;
		int	 hwQ = hwuQty;
		int hSp = hwuSp;
		int hCp = hwuUcp;
		int lPh = lwupHwu;
		int totC = tCost;
		int totQ =tQtyLwu;
		if (lSp <= lCp){
			JOptionPane.showMessageDialog(null, "LWU unit cost price cannot be more than or equalt  its unit selling price.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE); return 2;}
		else
			if (hSp <= hCp){
				JOptionPane.showMessageDialog(null, "HWU unit cost price cannot be more than or equalt its unit selling price.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
				return 3;}
			else if(lPh <= 0){
				JOptionPane.showMessageDialog(null, " \"LWU units per HWU\" cannot be less than one.\n Check table for correct value", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);return 4;}
			else
				if(lCp != hCp/lPh){
					JOptionPane.showMessageDialog(null, "LWU unit cost price and HWU unit cost price have not worked out\n based on value of  \"LWU units per HWU\"", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);	
					return 5;}else 
						if(totQ != ((lPh * hwQ) + lwQ)){
							JOptionPane.showMessageDialog(null, "Total quantity does not total HWU and LWU quantities. Try again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
							return 6;}else
								if(totC != ((lCp * lwQ) + (hCp * hwQ))){
									JOptionPane.showMessageDialog(null, " Amount you provided for total cost of this item does not \ncoincide with unit cost and quantity values provided.\n Check values and enter them again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
									return 7;}else
										if(totC != (totQ * lCp)){
											JOptionPane.showMessageDialog(null, " Amount you provided for total cost of this item does not \ncoincide with LWU unit cost and quantity values provided.\n Check values and enter them again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
											return 9;}else

												if (noRetUperLwu <= 0){
													JOptionPane.showMessageDialog(null, "Number of retail units per LWU unit cannot be less than 1.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE); return 10;}

												else
													if (retP <= lCp/noRetUperLwu){
														JOptionPane.showMessageDialog(null, "It seems Retail Price is less than or equal to cost price per unit retail quantity.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE); return 11;}

													else
														if(isDoubleReg(prodID, purchID, expDate))

														{int op = JOptionPane.showConfirmDialog(null, "Product already registered with the purchase ID provided.\n Do you want to update provided values?", "Message", JOptionPane.YES_NO_OPTION);
														if(op == 0)
															return whia.updateWHInventory(prodID,

																	noRetUperLwu,
																	lwu,
																	lwuQty,
																	lwuUcp,
																	lwuSp,
																	lwupHwu,
																	hwu,
																	hwuQty,
																	hwuUcp,
																	hwuSp,
																	limitLevel,
																	tCost,
																	tQtyLwu,
																	cat,
																	loc,
																	purchDate,
																	expDate);

														else if(op == 1)
															return 8;	}	





														else


															if((getTotalForPurchaseID(purchID) + tCost )> p.getPurchaseCost(purchID)){
																JOptionPane.showMessageDialog(null, "Your are attempting to register more that you bought.\n Ware house inventory for this purchase ID will cost:" +( getTotalForPurchaseID(purchID) + tCost ) + "\nMeanwhile, total purchase for this ID is :" + p.getPurchaseCost(purchID) +"\nPlease review Purchase list.");
																return 8;}

															else
															{

																ack =  whia.registerWSaleInventory(prodID,

																		noRetUperLwu,
																		lwu,
																		lwuQty,
																		lwuUcp,
																		lwuSp,
																		lwupHwu,
																		hwu,
																		hwuQty,
																		hwuUcp,
																		hwuSp,
																		limitLevel,
																		tCost,
																		tQtyLwu,
																		cat,
																		loc,
																		purchDate,
																		expDate);
																registerKeys(prodID,  purchID, expDate);}
		return ack;


	}

	@SuppressWarnings("unused")
	private int updateQties(int prodID, int lwuQty, int hwuQty) {
		// TODO Auto-generated method stub
		WHouseInventoryAccess whia = new WHouseInventoryAccess();
		return whia.updateQties(prodID, lwuQty, hwuQty);
	}



	private boolean isDoubleReg(int prodID, int purchID, java.sql.Date expDate) {
		WHouseInventoryAccess whia = new WHouseInventoryAccess();
		return whia.isDoubleReg(prodID, purchID, expDate);
	}



	public int updateWHInventory(
			int prodID,int purchID, int noRetUperLwu, String lwu, int lwuQty, int lwuUcp, int lwuSp,
			int lwupHwu, String hwu, int hwuQty, int hwuUcp, int hwuSp,
			int limitLevel, int tCost, int tQtyLwu, String cat,  String loc,
			java.sql.Date purchDate, java.sql.Date expDate)
	{

		int ack = 0;
		WHouseInventoryAccess whia = new WHouseInventoryAccess();
		//int retP = retPrice;
		int	 lwQ = lwuQty;
		int lSp = lwuSp;
		int lCp = lwuUcp;
		int	 hwQ = hwuQty;
		int hSp = hwuSp;
		int hCp = hwuUcp;
		int lPh = lwupHwu;
		int totC = tCost;
		int totQ =tQtyLwu;

		int lwqOld = getQuantity(prodID, "LwuSp");
		//JOptionPane.showMessageDialog(null, "here");
		int hwqOld = getQuantity(prodID, "HwuSp");
		int tqOut = ((lPh * hwqOld) + lwqOld);
		int tqIn = ((lPh * hwQ) + lwQ);

		int tqlwu = tqOut + tqIn;
		int tqhwu = tqlwu/lPh;
		int qtylw = tqlwu % lPh;
		hwuQty = tqhwu;
		lwuQty = qtylw;
		lwupHwu = lPh;

		Purchase p=  new Purchase();

		if (lSp <= lCp){
			JOptionPane.showMessageDialog(null, "LWU unit cost price cannot be more than or equalt  its unit selling price.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE); return 2;}
		else
			if (hSp <= hCp){
				JOptionPane.showMessageDialog(null, "HWU unit cost price cannot be more than or equalt its unit selling price.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
				return 3;}
			else if(lPh <= 0){
				JOptionPane.showMessageDialog(null, " \"LWU units per HWU\" cannot be less than one.\n Check table for correct value", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);return 4;}
			else
				if(lCp != hCp/lPh){
					JOptionPane.showMessageDialog(null, "LWU unit cost price and HWU unit cost price have not worked out\n based on value of  \"LWU units per HWU\"", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);	
					return 5;}else 
						if(totQ != ((lPh * hwQ) + lwQ)){
							JOptionPane.showMessageDialog(null, "Total quantity does not total HWU and LWU quantities. Try again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
							return 6;}else
								if(totC != ((lCp * lwQ) + (hCp * hwQ))){
									JOptionPane.showMessageDialog(null, " Amount you provided for total cost of this item does not \ncoincide with unit cost and quantity values provided.\n Check values and enter them again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
									return 7;}else
										if(totC != (totQ * lCp)){
											JOptionPane.showMessageDialog(null, " Amount you provided for total cost of this item does not \ncoincide with LWU unit cost and quantity values provided.\n Check values and enter them again.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE);
											return 9;}else

												if (noRetUperLwu <= 0){
													JOptionPane.showMessageDialog(null, "Number of retail units per LWU unit cannot be less than 1.", "Inconsistent Input", JOptionPane.ERROR_MESSAGE); return 10;}

												else
										
													if(isDoubleReg(prodID, purchID, expDate))

													{
														int op = JOptionPane.showConfirmDialog(null, "Product already registered with the purchase ID provided.\n Do you want to update provide values?", "Message", JOptionPane.YES_NO_OPTION);
													if(op == 0){
													ack = whia.updateWHInventory(prodID,

																noRetUperLwu,
																lwu,
																lwuQty,
																lwuUcp,
																lwuSp,
																lwupHwu,
																hwu,
																hwuQty,
																hwuUcp,
																hwuSp,
																limitLevel,
																tCost,
																tQtyLwu,
																cat,
																loc,
																purchDate,
																expDate);
														
whia.UpdateLog(0, purchID, prodID,tqIn,  purchDate);
													}

													else if(op == 1)
														return 8;	
													}	
	else//Not double registration




		if((getTotalForPurchaseID(purchID) + tCost )> p.getPurchaseCost(purchID))
		{
			int tot =getTotalForPurchaseID(purchID) + tCost ;
			JOptionPane.showMessageDialog(null, "Your are attempting to register more that you bought.\n Warehouse inventory for this purchase ID would cost:" +tot + "\nMeanwhile, total purchase for this ID is :" + p.getPurchaseCost(purchID) +"\nPlease review Purchase list.");
			return 8;}
		else//Not more total purchase with id provided


		{

			
				 ack = whia.updateWHInventory(prodID,

						 noRetUperLwu,
						 lwu,
						 lwuQty,
						 lwuUcp,
						 lwuSp,
						 lwupHwu,
						 hwu,
						 hwuQty,
						 hwuUcp,
						 hwuSp,
						 limitLevel,
						 tCost,
						 tQtyLwu,
						 cat,
						 loc,
						 purchDate,
						 expDate);

				 registerKeys(prodID,  purchID, expDate);
				 whia.UpdateLog(0, purchID, prodID,tqIn,  purchDate);		 
		}

			
				
			
	return ack;
}



@SuppressWarnings("unused")
private int getLwuPerHwu(int prodID) {
	// TODO Auto-generated method stub
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getLwuPerHwu(prodID);
}



public String[] getProdInfo(int prodID) {
	// TODO Auto-generated method stub
	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	String[] info = whia.getProdInfo(prodID) ;
	info[13] = ""+(Integer.parseInt(info[6]))*(Integer.parseInt(info[8]) )+ (Integer.parseInt(info[3]));
	info[12] = ""+(Integer.parseInt(info[13])*Integer.parseInt(info[4]));
	return info;
}


/*
public int getInvCount() {
	// TODO Auto-generated method stub

	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	return whia. getInvCount() ;
}
*/


public String[][] getAllWHItems() {
	// TODO Auto-generated method stub
	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	String[][] allItems = whia.getAllWHItems();
	int count = allItems.length;//getInvCount() ;
	for(int r =0; r < count; r++)
	{
		//		totQty in lwu =		lwu/hwu	* hwuQty + lwuQty
		allItems[r][13] = ""+((Integer.parseInt(allItems[r][6]) * Integer.parseInt(allItems[r][8]) ) + Integer.parseInt(allItems[r][3]));
		//tot cost = totQty in lwu * lwuCp
		allItems[r][12] = ""+(Integer.parseInt(allItems[r][13])*Integer.parseInt(allItems[r][4]));
	}
	return allItems;}



public String[][] searchDesc(String text) {
	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	return whia.searchProd(text);
}



public Date getMinExpDate(int parseInt) {
	PurchaseAcc p =  new PurchaseAcc();
	return p.getMinExpDate(parseInt);
}



public Date getExpDate(int parseInt, int i) {
	PurchaseAcc p =  new PurchaseAcc();
	return p.getExpDate(parseInt, i);
}



public int getTotalForPurchaseID(int purchID){
	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	return whia.totalForPurchaseID(purchID);


}



public int checkCostPrice(int prodID) {
	// TODO Auto-generated method stub
	WHouseInventoryAccess whia = new WHouseInventoryAccess();
	return whia.checkCostPrice(prodID);
}

public void saleFinalisation(int prodID, int transID,String wsUnit, String wsDesc, int qty,
		int wsPrice, Float tot) {
	// TODO Auto-generated method stub
	WSaleAccess wsa = new WSaleAccess();
	wsa.registerSoldItems( prodID, transID, wsUnit, qty, wsPrice);

	int lPh = wsa.getLwuPerHwu(prodID);

	int oldLwuQty = getQuantity(prodID, "LwuSp");
	//JOptionPane.showMessageDialog(null, "here");
	int oldHwuQty = getQuantity(prodID, "HwuSp");
	int oldTotalQty = ((lPh * oldHwuQty) + oldLwuQty);
	

	int newtotalQtyLwu = (oldTotalQty - qty);
	int newQtyHwu = newtotalQtyLwu/lPh;
	int newQtylwu = newtotalQtyLwu%lPh;
  
	wsa.deductInventory(prodID,newQtylwu, newQtyHwu );
	
}

public float getTotalSales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	// TODO Auto-generated method stub
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getTotalSales( frmDate, toDate,
			frmTime, toTime);
}
public float getTotalGProfit(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime)
{WSaleAccess wsa = new WSaleAccess();
	return (wsa.getTotalGProfitLwuSp( frmDate, toDate,
			frmTime, toTime) + wsa.getTotalGProfitHwuSp(frmDate, toDate,
					frmTime, toTime));	

}

public String[][] getSales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getSales(frmDate, toDate,frmTime,
			toTime);
	
	
}

public String[][] getDailySales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getDailySales(frmDate, toDate,frmTime,
			toTime);
}
public String[][] getDailyGProfit(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getDailyGProfit(frmDate, toDate,frmTime,
			toTime);
}



public String[][] getMonthlySales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getMonthlySales(frmDate, toDate,frmTime,
			toTime);
}
public String[][] getMonthlyGProfit(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	// TODO Auto-generated method stub
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getMonthlyGProfit(frmDate, toDate,frmTime,
			toTime);
}

public String[][] getRetailShortage() {
	// TODO Auto-generated method stub
	WSaleAccess wsa = new WSaleAccess();
	return wsa.getRetailShortage();

}

public String[][] getWarehouseShortage() {
	// TODO Auto-generated method stub
	WHouseInventoryAccess whi = new WHouseInventoryAccess();
	return whi. getWarehouseShortage();
}}