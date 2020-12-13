package access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.JOptionPane;



public  class AccessClass {





static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";

	//  Database credentials
static	final String USER = "root";
static	final String PASS = "m1n3";
	

  


static Connection conn = null;
	static Statement stmt = null;

	public static void main (String args[])
	{	
		
	
		//insertRetProdBelowLim(prodRetID, retDes, qtyLeft, limLevel, null);
	}

	
	public static String retrieveDescription(int prodRID)
	{
		
		String desc = null;
		try{
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		//STEP 3: Open a connection
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

		//STEP 4: Execute a query
	
		stmt = conn.createStatement();

		String sql = "	SELECT retDesc FROM retailinventory  WHERE prodRetID  ="+prodRID+"";


		ResultSet rs = stmt.executeQuery(sql);
		//STEP 5: Extract data from result set

		//Retrieve by column name
		   while(rs.next()){
	
	desc = rs.getString("retDesc");	   
		
		//Display values
//System.out.println("Retail price = " +rp);
		   }

	}catch(SQLException se){
		//Handle errors for JDBC
		JOptionPane.showMessageDialog(null, se);
	}catch(Exception e){
		//Handle errors for Class.forName
			JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, se);
		}//end finally try

	}//end try
	return desc;

	}

	public static int retrieveRetailPrice(int prodRID) {
		int rp = 0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
			
		stmt = conn.createStatement();

			String sql = "SELECT retailPrice FROM RetailInventory WHERE prodRetID = "+prodRID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next()){
		rp = rs.getInt("retailPrice");
			   
			
			//Display values

			   }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, se);
			}//end finally try

		}//end try
		return rp;

	


		

	}
	public int generateCode() {
		int lastID = 0;
	
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
			
			stmt = conn.createStatement();

			String sql = "SELECT  MAX(prodRetID) as lastID FROM RetailInventory  ";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next()){
		lastID = rs.getInt("lastID");
			   
		
			//Display values

			   }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, se);
			}//end finally try

		}//end try
		return lastID;

	}

	public static int checkProdRetidDB(int prodretid) {
		int proRID=0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT prodretid FROM retailinventory  where prodretid  = "+prodretid+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
			System.out.println("Statement created...");
			//Retrieve by column name
			 while(rs.next()){
	proRID = rs.getInt("prodRetID");
			   
			
			//Display values

			  }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, se);
			}//end finally try

		}
		return proRID;

}

	public static void reduceQtyDB(int prodretid, int qty) {
		
		
				try{
					//STEP 2: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//STEP 3: Open a connection
					
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

					//STEP 4: Execute a query

					stmt = conn.createStatement();

					String sql = "UPDATE RetailInventory SET quantity = quantity - "+qty+" WHERE prodretid = "+prodretid+"";
					stmt.executeUpdate(sql);
					//STEP 5: Extract data from result set
			
					//Retrieve by column name
					 //  while(rs.next()){
				//rp = rs.getInt("retailPrice");
					   
					
					//Display values

					  // }

				}catch(SQLException se){
					//Handle errors for JDBC
					JOptionPane.showMessageDialog(null, se);
				}catch(Exception e){
					//Handle errors for Class.forName
						JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
				}finally{
					//finally block used to close resources
					
					try{
						if(conn!=null)
							conn.close();
					}catch(SQLException se){
						JOptionPane.showMessageDialog(null, se);
					}//end finally try

				}

	}

	public static int getRetQuantity(int prodRetID) {
		int qty = 0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT quantity FROM retailinventory WHERE prodRetID  = "+prodRetID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
	
			//Retrieve by column name
			 while(rs.next()){
	qty = rs.getInt("quantity");
			   
			
			//Display values

			  }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}//end finally try

		}
		return qty;
	

	}
public static int getRetLimitLevel(int prodRetID) {
		int limLevel =0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT limitlevel FROM retailinventory WHERE  prodRetID  = "+prodRetID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	limLevel = rs.getInt("limitLevel");
			   
			
			//Display values

			  }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}//end finally try

		}
		return limLevel;


	// JDBC driver name and database URL

}

public  boolean deleteRow(int prodRetID) {
	boolean flag = true;
	try{
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		//STEP 3: Open a connection

		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
		//STEP 4: Execute a query
	
	

		String sql ="DELETE FROM prodretids WHERE prodretid = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, prodRetID); 
		st.executeUpdate();
		
		String sql2 ="DELETE FROM retailinventory WHERE prodretid = ?";
		PreparedStatement st2 = conn.prepareStatement(sql2);
		st2.setInt(1, prodRetID); 
		st2.executeUpdate();
		

	}catch(SQLException se){
		//Handle errors for JDBC
		flag=false;
		JOptionPane.showMessageDialog(null, "Database Error!:source:AccessClass/delete \n"+se);
	}catch(Exception e){
		//Handle errors for Class.forName
			JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
		}//end finally try

	}
	return flag;
}

public Boolean updateRetInventory(int prodRID, int qty)
{Boolean flag = true;

try{

	Class.forName("com.mysql.jdbc.Driver");

	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	String sql ="UPDATE retailinventory SET quantity = quantity + ? WHERE prodRetID = ?";
	
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setInt(1, qty);
	stmt.setInt(2, prodRID);
	
	stmt.executeUpdate();
	

}catch(SQLException se){
	//Handle errors for JDBC
	flag = false;
	JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
}catch(Exception e){
	//Handle errors for Class.forName
	flag = false;
		JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
}finally{
	//finally block used to close resources
	
	try{
		if(conn!=null)
			conn.close();
	}catch(SQLException se){	flag = false;
		JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
	
	}//end finally try

}

return flag;
}

public boolean registerNewProd(int prodRID, int prodID, String retDesc, int qty,int retPrice, String loca,
		int limLev) {
	boolean flag = true;
	try{
		//STEP 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");

		//STEP 3: Open a connection
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

		//STEP 4: Execute a query
	
	

			String sql ="INSERT INTO  RetailInventory(prodRetID, retDesc, retailPrice, quantity,  location, limitLevel)	VALUES (?, ?,?,?,?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, prodRID);
			stmt.setString(2, retDesc);
			stmt.setInt(4, qty);
			stmt.setInt(3, retPrice);
			stmt.setString(5, loca);
			stmt.setInt(6, limLev);
			
		stmt.executeUpdate();
			
		String sql2 ="INSERT INTO  prodretids(prodRetID, prodID)	VALUES (?,?)";
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setInt(1, prodRID);
		stmt2.setInt(2, prodID);
		stmt2.executeUpdate();
		
		
		

	}catch(SQLException se){
		//Handle errors for JDBC
		flag = false;
		JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
	}catch(Exception e){
		//Handle errors for Class.forName
		flag = false;JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}
	return flag;
}

public int searchItem(String retDes) {
	
	int prID = 0;
	try{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
		
		stmt = conn.createStatement();
		
		String sql = "SELECT prodRetID FROM retailinventory WHERE retDesc = ?";
		
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, retDes);
		
		
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) 
			
			 prID = rs.getInt("prodRetID");
		
		
			 
	
}catch(SQLException se){
	//Handle errors for JDBC

	JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
}catch(Exception e){
	//Handle errors for Class.forName
	JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
}finally{
	//finally block used to close resources
	
	try{
		if(conn!=null)
			conn.close();
	}catch(SQLException se){
		
	}//end finally try

}
	return prID;
}

public String[][] searchString(String text) {
	
	String[][] lookup = null ;
	int size = 0;

	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection( DB_URL, USER, PASS);
		
		

			
		
				
		
	String 	sql = "SELECT prodRetID, retDesc FROM  retailinventory WHERE retDesc LIKE ? ORDER BY retDesc";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, text+"%");
		 ResultSet rs = stm.executeQuery();
while (rs.next())
	
	size = rs.getRow();
	 lookup = new String[size][2];
	 rs.beforeFirst();
	 
			while(rs.next())
				
			for(int r=0; r < size; r++ ){ 
				
			
		lookup[r][0]= rs.getString("prodRetID");
		lookup[r][1]= rs.getString("retDesc");
		
		
		if(rs.next() == false)
			break;
	
		}
			
			
		
	}catch(Exception e){
		//Handle errors for Class.forName
		JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}
		return lookup;
}

public int getProdRetID(String retDesc) {
	int id = 0;
	
	try{
Class.forName("com.mysql.jdbc.Driver");
conn = DriverManager.getConnection(DB_URL, USER, PASS);
String sql ="SELECT prodRetID FROM retailinventory WHERE retDesc = ?";
PreparedStatement st = conn.prepareStatement(sql);

st.setString(1, retDesc);
st.executeQuery();

ResultSet rs = st.getResultSet();while(rs.next())
	
	
	id = rs.getInt("prodRetID");

	}
	catch(Exception e)
	{	JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}

	return id;
}

public String[] getProdInfo(int prodRetID) {
String[] info = new String[6];
	
	
	try{
Class.forName("com.mysql.jdbc.Driver");
conn= DriverManager.getConnection(DB_URL, USER, PASS);

String sql = "SELECT * FROM retailinventory WHERE prodRetID = ?";
	PreparedStatement st= conn.prepareStatement(sql);
	st.setInt(1, prodRetID);
	ResultSet rs = st.executeQuery();
	
	while(rs.next())
	{
	info[0]=rs.getString("prodRetID");
	info[1]=rs.getString("retDesc");
	info[2]=rs.getString("retailPrice");
	info[3]=rs.getString("quantity");
	info[4]=rs.getString("location");
	info[5]=rs.getString("limitLevel");
	
		
		
	}
	}
	catch(Exception e)
	{	JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}

	return info;
	
	
	
}

public boolean updateRetInv(int prodRetID2, String retDesc, int prodID,
		int qty2, int retPrice, String loc, int limLev) {
	
	
	Boolean flag = true;
	try{
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	
	String sql = "UPDATE retailinventory SET retDesc = ?, retailPrice= ?,quantity = quantity + ?, location = ?, limitLevel = ? WHERE prodRetID = ? ";
	
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, retDesc);
	st.setInt(2, retPrice);
	st.setInt(3, qty2);
	st.setString(4, loc);
	st.setInt(5, limLev);
	st.setInt(6, prodRetID2);
	st.executeUpdate();
	
	String sql2 = "UPDATE prodretids SET prodID = ? WHERE prodRetID = ?";
	PreparedStatement st2 = conn.prepareStatement(sql2);
	st2.setInt(1, prodID);
	st2.setInt(2, prodRetID2);
	st2.executeUpdate();
	
	
}catch(SQLException se){
	//Handle errors for JDBC
	flag = false;
	JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
}catch(Exception e){
	//Handle errors for Class.forName
	flag = false;JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
}finally{
	//finally block used to close resources
	
	try{
		if(conn!=null)
			conn.close();
	}catch(SQLException se){
		
	}//end finally try

}
return flag;
}
public void updateShortageDate(int rid, Date date , Time time){
//Boolean flag = true;
try{
Class.forName("com.mysql.jdbc.Driver");
conn = DriverManager.getConnection(DB_URL, USER, PASS);

String sql = "UPDATE retailinventory SET shortagedate = ?, shortagetime= ? WHERE prodRetID = ? ";

PreparedStatement st = conn.prepareStatement(sql);
st.setDate(1, date);
st.setTime(2, time);
st.setInt(3, rid);

st.executeUpdate();


}catch(SQLException se){
//Handle errors for JDBC
//flag = false;
JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
}catch(Exception e){
//Handle errors for Class.forName
//flag = false;
	JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
}finally{
//finally block used to close resources

try{
	if(conn!=null)
		conn.close();
}catch(SQLException se){
	
}//end finally try

}
}



public String[][] getAllretItems() {
	int noRows = 0;//getRetInvCount() ;
String[][] allItems = null;//new String[noRows][6];
try{
	
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(DB_URL, USER, PASS);
	
	String sql = "SELECT * FROM retailInventory  ORDER BY retDesc, prodRetID";
	
	PreparedStatement st = conn.prepareStatement(sql);
	ResultSet rs = st.executeQuery();
	while(rs.next())
		noRows = rs.getRow();
	
	allItems = new String [noRows][6];
	rs.beforeFirst();
	while(rs.next())
		for(int r=0; r < noRows; r++ )
			
	{
		
		allItems[r][0]= rs.getString("prodRetID");
		allItems[r][1]= rs.getString("retDesc");
		allItems[r][2]= rs.getString("retailPrice");
		allItems[r][3]= rs.getString("quantity");
		allItems[r][4]= rs.getString("location");
		allItems[r][5]= rs.getString("limitLevel");
//JOptionPane.showMessageDialog(null, allItems[r][0]);
if(rs.next() == false)
	break;
}
	
	
}
		catch(SQLException se){
			//Handle errors for JDBC
			
			JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
		}catch(Exception e){
			//Handle errors for Class.forName
			JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
		}finally{
			//finally block used to close resources
			
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				
			}//end finally try

		}
return allItems;
	
}

public void registerSoldItems(int prodRid, int transID,  int qty,
		int retPrice) {
	try{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
	String sql = "INSERT INTO retailSales (prodRetIDs, transID, retPrice, qty) VALUES (?,?,?,?)";
		
		PreparedStatement st = conn.prepareStatement(sql);
	st.setInt(1, prodRid);
	st.setInt(2, transID);
	
	st.setInt(3, retPrice);
	st.setInt(4, qty);
	
	st.executeUpdate();
		
	}
	catch(SQLException se){
		//Handle errors for JDBC
		
		JOptionPane.showMessageDialog(null, "Database Error!: Source:AccessClass/registerSoldItems\n"+se);
	}catch(Exception e){
		//Handle errors for Class.forName
		JOptionPane.showMessageDialog(null, "Database Error!: Source:AccessClass/registerSoldItems\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}
		
	}// TODO Auto-generated method stub

public String[][] getSales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	int noRows = 0;//salesCount(frmDate, toDate, frmTime,	toTime) ;
	String[][] allItems = null;//new String[noRows][6];
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "SELECT  retailsales.prodRetIDs,retailsales.transid, (SELECT retDesc FROM retailinventory WHERE prodretid = retailsales.prodretids) AS retDesc, retailsales.retprice, qty, retailsales.qty*retailsales.retprice AS total FROM retailsales, transaction WHERE transaction.transid = retailsales.transid AND transaction.transtype='Retail_Paid' AND transaction.transdate  BETWEEN  ? AND ? AND transaction.transtime BETWEEN ? AND ? ORDER by transdate, transtime ";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			noRows = rs.getRow();
		allItems = new String[noRows][6];
		rs.beforeFirst();
		while(rs.next())
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("prodRetIDs");
			allItems[r][1]= rs.getString("transID");
			allItems[r][2]= rs.getString("retDesc");
			allItems[r][3]= rs.getString("retPrice");
			allItems[r][4]= rs.getString("qty");
			allItems[r][5]= rs.getString("total");
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;
}

public String[][] getDailySales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	int noRows = 0;
	String[][] allItems = null;//new String[noRows][2];
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
		{;}
		String sql = "SELECT  transDate  ,sum(retailsales.qty*retailsales.retprice ) AS amount FROM retailsales, transaction WHERE transaction.transid=retailsales.transid AND transaction.transtype='Retail_Paid' AND transDate   BETWEEN  ? AND ? AND  transTime BETWEEN ? AND ?  GROUP BY transDate ORDER BY transdate";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			noRows=rs.getRow();
		
		allItems = new String[noRows][2];
		rs.beforeFirst();
		while(rs.next())
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("transDate");
			allItems[r][1]= rs.getString("amount");
			
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;
}

public String[][] getDailyGProfit(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	int noRows=0;
	String[][] allItems = null;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
		{;}
		String sql = "SELECT transaction.transdate,  FLOOR(SUM(Retailsales.qty*(retailsales.retprice -(SELECT lwucp/noretuperlwu FROM whouseinventory WHERE prodid = (SELECT prodid FROM prodretids WHERE prodretid= retailsales.prodretids))))) AS dailyProfit FROM retailsales, transaction WHERE transaction.transid = retailsales.transid AND transaction.transtype='Retail_Paid' AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime BETWEEN  ? AND ? GROUP BY transdate ORDER BY transdate";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			noRows = rs.getRow();
		
		allItems = new String[noRows][2];
		rs.beforeFirst();
		while(rs.next())
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("transaction.transdate");
			allItems[r][1]= rs.getString("dailyProfit");
			
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;}



public String[][] getMonthlyGProfit(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	int noRows=0;
	String[][] allItems = null;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
		{;}
		String sql = "SELECT  DATE_FORMAT (transDate, '%b') AS mn, YEAR (transDate)  AS yr ,fLOOR(SUM(Retailsales.qty*(retailsales.retprice -(SELECT lwucp/noretuperlwu FROM whouseinventory WHERE prodid = (SELECT prodid FROM prodretids WHERE prodretid= retailsales.prodretids))))) AS monthlyprofit FROM retailsales, transaction WHERE transaction.transtype='Retail_Paid' AND transaction.transid = retailsales.transid AND transaction.transdate BETWEEN ? AND ? AND transaction.transtime BETWEEN  ? AND ?  GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			noRows = rs.getRow();
		
		allItems = new String[noRows][3];
		rs.beforeFirst();
		while(rs.next())
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("mn");
			allItems[r][1]= rs.getString("yr");
			allItems[r][2]= rs.getString("monthlyprofit");

	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;}


public String[][] getMonthlySales(java.sql.Date frmDate, java.sql.Date toDate,
		Time frmTime, Time toTime) {
	int noRows = 0; //= monthlySalesCount(frmDate, toDate, frmTime,
			//toTime) ;
	//String[][] allItems = new String[noRows][3];
	String[][] allItems = null;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
		{;}
		
		
		String sql = "SELECT  DATE_FORMAT (transDate, '%b') AS mn, YEAR (transDate)  AS yr, SUM(retailsales.qty*retailsales.retprice )AS total FROM transaction, retailsales  WHERE transaction.transtype='Retail_Paid' AND retailsales.transid = transaction.transid AND transDate   BETWEEN ? AND ? AND  transTime BETWEEN ? AND ? GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		
while(rs.next())
			noRows= rs.getRow();
 allItems = new String[noRows][3];
		rs.beforeFirst();
		while(rs.next())
			
			
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("mn");
			allItems[r][1]= rs.getString("yr");
			allItems[r][2]= rs.getString("total");
			
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;
}

public String[][] getRetailShortage() {
	// TODO Auto-generated method stub
	int noRows=0;
	String[][] allItems = null;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
		{;}
		
		
		String sql = "SELECT prodretid,retdesc,  quantity, location, limitlevel, shortagedate, shortagetime FROM retailinventory WHERE quantity < limitlevel ORDER BY retdesc;";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		
		ResultSet rs = st.executeQuery();
		
while(rs.next())
			noRows= rs.getRow();
 allItems = new String[noRows][7];
		rs.beforeFirst();
		while(rs.next())
			
			
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r][0]= rs.getString("prodretid");
			allItems[r][1]= rs.getString("retdesc");
			
			allItems[r][2]= rs.getString("quantity");
			allItems[r][3]= rs.getString("limitlevel");
			allItems[r][4]= rs.getString("location");
			allItems[r][5]= rs.getString("shortagedate");
			allItems[r][6]= rs.getString("shortagetime");
			
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;
}

public void insertShortageDT(int prodRid, Timestamp insertDateNow, Timestamp insertDateNow2) {
	// TODO Auto-generated method stub
try{
		
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
	String sql = "UPDATE retailinventory SET shortagedate = ?, shortagetime = ? WHERE prodretid = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
	st.setTimestamp(1, insertDateNow);
	st.setTimestamp(2, insertDateNow);
	
	st.setInt(3, prodRid);
	
	st.executeUpdate();
		
	}
	catch(SQLException se){
		//Handle errors for JDBC
		
		JOptionPane.showMessageDialog(null, "Database Error!: Source:AccessClass/InsertShortage\n"+se);
	}catch(Exception e){
		//Handle errors for Class.forName
		JOptionPane.showMessageDialog(null, "Database Error!: Source:AccessClass/Insertshortage\n"+e);
	}finally{
		//finally block used to close resources
		
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			
		}//end finally try

	}
		
}


public float getTotalGProfit(Date frmDate, Date toDate, Time frmTime,
		Time toTime) {
	// TODO Auto-generated method stub
	int totalGP=0;
	
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
		{;}
		String sql = "SELECT  FLOOR(SUM(Retailsales.qty*(retailsales.retprice -(SELECT lwucp/noretuperlwu FROM whouseinventory WHERE prodid = (SELECT prodid FROM prodretids WHERE prodretid= retailsales.prodretids))))) AS totalgp FROM retailsales, transaction WHERE transaction.transtype='Retail_Paid' AND transaction.transid = retailsales.transid AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime BETWEEN  ? AND ? ";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			totalGP = rs.getInt("totalgp");
		
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return totalGP;}
public float getTotalSales(Date frmDate, Date toDate, Time frmTime, Time toTime) {
	// TODO Auto-generated method stub
int totalSales=0;
	
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
		// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
		{;}
		String sql = "SELECT  sum(retailsales.qty*retailsales.retprice ) AS amount FROM retailsales, transaction WHERE transaction.transtype='Retail_Paid' AND transaction.transid=retailsales.transid AND transaction.transDate  BETWEEN  ? AND ? AND  transaction.transTime BETWEEN ? AND ? ";
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setDate(1, frmDate);
		st.setDate(2, toDate);
		st.setTime(3, frmTime);
		st.setTime(4, toTime);
		ResultSet rs = st.executeQuery();
		while(rs.next())
			totalSales = rs.getInt("amount");
		
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!source:AccessClass/getTotalSales\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return totalSales;
}


public String[] checkDoubleReg(int prodID, int prodRID) {
	// TODO Auto-generated method stub
	String[] allItems = null;
	int noRows =0;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);
	
		
		String sql = "SELECT  retDesc  FROM retailinventory, prodretids  WHERE  retailinventory.prodretid = prodretids.prodretid AND prodretids.prodid = ? ";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		
		st.setInt(1, prodID);
		
		ResultSet rs = st.executeQuery();
		
while(rs.next())
			noRows= rs.getRow();
 allItems = new String[noRows];
		rs.beforeFirst();
		while(rs.next())
			
			
			for(int r=0; r < noRows; r++ )
				
		{
			
			allItems[r]= rs.getString("retDesc");
			
			
	//JOptionPane.showMessageDialog(null, allItems[r][0]);
	if(rs.next() == false)
		break;
	}
		
		
	}
			catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					
				}//end finally try

			}
	return allItems;
}



	
}


