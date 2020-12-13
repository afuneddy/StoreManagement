package access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.swing.JOptionPane;

public class WSaleAccess {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	//static int	wsp;
	//static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;
	
	public WSaleAccess(){}

	public int checkProdID(int prodID) {
	int pid = 0;
	try{
		
		Class.forName("com.mysql.jdbc.Driver");


		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	

		stmt = conn.createStatement();

		String sql = "	SELECT prodID FROM whouseinventory  where prodID  = "+prodID+"";
		ResultSet rs = stmt.executeQuery(sql);
		
		 while(rs.next()){
pid = rs.getInt("prodID");
		   
		
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
	return pid;

}

	public int getSellingPriceHwu(int prodID) {
	int wsp = 0;	try{
			
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		


			

				String sql = "SELECT hwuSp FROM whouseinventory WHERE prodID = ?";
			
				PreparedStatement st= conn.prepareStatement(sql);
				st.setInt(1, prodID);
				ResultSet rs = st.executeQuery();
				//STEP 5: Extract data from result set

				//Retrieve by column name
				   while(rs.next()){
			wsp = rs.getInt("hwuSp");
				   
				
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

		}//end try
		return wsp;

	


	}

	public String getDescriptionLwu(int prodID) {
		String desc = null;try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();
			

			String sql = "	SELECT lwuDesc FROM whouseinventory  WHERE prodID  ="+prodID+"";


			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next()){
		
		desc = rs.getString("lwuDesc");	} 
			 
				   
			  			
			   

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

		}//end try
		return desc;

	}
	
	public String getDescriptionHwu(int prodID) {
	String desc = null;	try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();
			

			String sql = "	SELECT hwuDesc FROM whouseinventory  WHERE prodID  ="+prodID+"";


			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next()){
		
		desc = rs.getString("hwuDesc");	} 
			 
				   
			  			
			   

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

		}//end try
		return desc;

	}

	public void reduceQtyLwu(int prodid, int qty) {

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
	
			stmt = conn.createStatement();

			String sql = "UPDATE whouseinventory SET lwuQty = lwuQty - "+qty+" WHERE prodID = "+prodid+"";
			stmt.executeUpdate(sql);
			//STEP 5: Extract data from result set
		
			
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
		
	}
	public void reduceQtyHwu(int prodid, int qty) {

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
	
			stmt = conn.createStatement();

			String sql = "UPDATE whouseinventory SET hwuQty = hwuQty - "+qty+" WHERE prodID = "+prodid+"";
			stmt.executeUpdate(sql);
			//STEP 5: Extract data from result set
		
			
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
		
	}


	public int getQuantityLwu(int prodID) {
		int qty = 0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT lwuQty FROM whouseinventory WHERE prodID  = "+prodID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	qty = rs.getInt("lwuQty");
			   
			
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
		return qty;

	}
	
	public int getQuantityHwu(int prodID) {
		int qty = 0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT hwuQty FROM whouseinventory WHERE prodID  = "+prodID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	qty = rs.getInt("hwuQty");
			   
			
			//Display values

			  }

		}catch(SQLException se){
			//Handle errors for JDBC
			JOptionPane.showMessageDialog(null, "Database Error!: location:WSaleAccess/getquantityHwu\n"+se);
		}catch(Exception e){
			//Handle errors for Class.forName
				JOptionPane.showMessageDialog(null, "Database Error!: location:WSaleAccess/getquantityHwu\n"+e);
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


	public int getLimitLevel(int prodID) {
	
		int limLevel = -1;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
	
			stmt = conn.createStatement();

			String sql = "	SELECT limitlevel FROM whouseinventory WHERE prodid  = "+prodID+"";
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


	}

	public String getDescLwu(int prodID) {
	String desc = null;	try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT lwuDesc FROM whouseinventory  WHERE prodID  ="+prodID+"";


			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next()){
		
		desc = rs.getString("lwuDesc");	   
			
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

		}//end try
		return desc;

	}

	
	
	public void deleteRow(int prodID) {
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql ="DELETE FROM WhProdBelowLim WHERE prodID = "+prodID+"";
			stmt.executeUpdate(sql);
			

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

			String sql = "SELECT  MAX(prodID) as lastID FROM WHouseInventory  ";
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

	public int getLwuPerHwu(int prodID) {
		int lph = 0;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT lwuPhwu FROM whouseinventory WHERE prodID  = "+prodID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	lph = rs.getInt("lwuPhwu");
			   
			
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
		return lph;

	}

	public int getSellingPriceLwu(int prodID) {
	int wsp = 0;	try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		


			String sql = "SELECT lwuSp FROM whouseinventory WHERE prodID = ?";
		
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, prodID);
			ResultSet rs = st.executeQuery();
			//STEP 5: Extract data from result set

			//Retrieve by column name
			   while(rs.next())
		wsp = rs.getInt("lwuSp");
			   
			
			
				   

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

		}//end try
		return wsp;

	}

	public void registerSoldItems(int prodID, int transID, String wsUnit,
			int qty, int wsPrice) {
		// TODO Auto-generated method stub
		try{
			
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
		String sql = "INSERT INTO wholesalesales (prodID, transIDw, wsUnit, wsPrice, qty) VALUES (?,?,?,?,?)";
			
			PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, prodID);
		st.setInt(2, transID);
		st.setString(3, wsUnit);
		st.setInt(4, wsPrice);
		st.setInt(5, qty);
		
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
			
		
	}

	public void deductInventory(int prodID, int newQtylwu, int newQtyHwu) {
		// TODO Auto-generated method stub
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			
			String sql = "UPDATE whouseinventory SET lwuQty = ?, hwuQty = ? WHERE prodID = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, newQtylwu);
			st.setInt(2, newQtyHwu);
			st.setInt(3, prodID);
			
			st.executeUpdate();
			//STEP 5: Extract data from result set
		
			
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
		
		
	}


		public float getTotalGProfitLwuSp( Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			// TODO Auto-generated method stub
			int totalGP=0;
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
				{;}
				String sql = "SELECT  FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice - (SELECT lwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid AND wholesalesales.wsUnit = 'LwuSp' )))) AS totalgp FROM wholesalesales, transaction WHERE transaction.transid = wholesalesales.transidw AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime BETWEEN  ? AND ? ";
				
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
		
		public float getTotalGProfitHwuSp( Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			// TODO Auto-generated method stub
			int totalGP=0;
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
				{;}
				String sql = "SELECT  FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice - (SELECT hwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid AND wholesalesales.wsUnit = 'HwuSp' )))) AS totalgp FROM wholesalesales, transaction WHERE transaction.transid = wholesalesales.transidw AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime BETWEEN  ? AND ? ";
				
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
				String sql = "SELECT  sum(wholesalesales.qty*wholesalesales.wsprice ) AS amount FROM wholesalesales, transaction WHERE transaction.transid=wholesalesales.transidw AND transaction.transtype='Wholesale_Paid' AND transaction.transDate  BETWEEN  ? AND ? AND  transaction.transTime BETWEEN ? AND ? ";
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
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public String[][] getSales(java.sql.Date frmDate, java.sql.Date toDate,
				Time frmTime, Time toTime) {
			int noRows = 0;//salesCount(frmDate, toDate, frmTime,	toTime) ;
			String[][] allItems = null;//new String[noRows][6];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT wholesalesales.prodID, wholesalesales.transidw, wholesalesales.wsunit,(SELECT lwuDesc FROM whouseinventory WHERE prodid =wholesalesales.prodid) AS wsDesc,wholesalesales.wsprice, qty,wholesalesales.qty*wholesalesales.wsprice AS total, transaction.transdate, transaction.transtime FROM wholesalesales, transaction WHERE  wholesalesales.transidw=transaction.transid AND wholesalesales.wsunit='LwuSp'AND transaction.transtype='Wholesale_Paid' AND transaction.transDate   BETWEEN  ? AND ? AND  transaction.transTime BETWEEN ? AND ? UNION  SELECT wholesalesales.prodID, wholesalesales.transidw, wholesalesales.wsunit,(SELECT hwuDesc FROM whouseinventory WHERE prodid =wholesalesales.prodid)  AS wsDesc,wholesalesales.wsprice,  qty,wholesalesales.qty*wholesalesales.wsprice AS total, transaction.transdate, transaction.transtime FROM wholesalesales, transaction WHERE  wholesalesales.transidw=transaction.transid AND wholesalesales.wsunit='HwuSp'AND transaction.transtype='Wholesale_Paid' AND transaction.transDate    BETWEEN  ? AND ? AND  transaction.transTime BETWEEN ? AND ? ORDER BY transdate ,transtime ";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				
				st.setDate(5, frmDate);
				st.setDate(6, toDate);
				st.setTime(7, frmTime);
				st.setTime(8, toTime);
				ResultSet rs = st.executeQuery();
				while(rs.next())
					noRows = rs.getRow();
				allItems = new String[noRows][9];
				rs.beforeFirst();
				while(rs.next())
					for(int r=0; r < noRows; r++ )
						
				{
					
					allItems[r][0]= rs.getString("prodID");
					allItems[r][1]= rs.getString("transidw");
					allItems[r][2]= rs.getString("wsunit");
					allItems[r][3]= rs.getString("wsdesc");
					allItems[r][4]= rs.getString("wsprice");
					allItems[r][5]= rs.getString("qty");
					allItems[r][6]= rs.getString("total");
					allItems[r][7]= rs.getString("transdate");
					allItems[r][8]= rs.getString("transtime");
					
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
			int noRows =0;// dailySalesCount(frmDate, toDate, frmTime,
					//toTime) ;
			String[][] allItems = null;//new String[noRows][2];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
				{;}
				String sql = "SELECT  transDate  ,sum(wholesalesales.qty*wholesalesales.wsprice ) AS amount FROM wholesalesales, transaction WHERE transaction.transid=wholesalesales.transidw AND transaction.transtype ='Wholesale_Paid' AND transDate   BETWEEN  ? AND ? AND  transTime  BETWEEN ? AND ?  GROUP BY transDate ORDER BY transdate";
				
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				ResultSet rs = st.executeQuery();
				
				
				while(rs.next())
				noRows =	rs.getRow();
				
				allItems= new String[noRows][2];
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
				String sql = "SELECT * FROM (SELECT transaction.transdate,  FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice -(SELECT lwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid)))) AS dailyProfit FROM wholesalesales, transaction WHERE transaction.transid =wholesalesales.transidw AND transaction.transtype='Wholesale_Paid' AND wholesalesales.wsunit='LwuSp'AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime  BETWEEN ? AND ? GROUP BY transdate 	UNION SELECT transaction.transdate,  FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice -(SELECT hwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid)))) AS dailyProfit FROM wholesalesales, transaction WHERE transaction.transid =wholesalesales.transidw AND transaction.transtype='Wholesale_Paid' AND wholesalesales.wsunit='HwuSp'AND transaction.transdate BETWEEN  ? AND ? AND transaction.transtime  BETWEEN ? AND ? ) AS mix GROUP BY mix.transdate ORDER BY mix.transdate";
				

		


				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				
				st.setDate(5, frmDate);
				st.setDate(6, toDate);
				st.setTime(7, frmTime);
				st.setTime(8, toTime);
				ResultSet rs = st.executeQuery();
				while(rs.next())
					noRows = rs.getRow();
				
				allItems = new String[noRows][2];
				rs.beforeFirst();
				while(rs.next())
					for(int r=0; r < noRows; r++ )
						
				{
					
					allItems[r][0]= rs.getString("transdate");
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
				String sql = "SELECT * FROM (SELECT  DATE_FORMAT (transDate, '%b') AS mn, YEAR (transDate)  AS yr ,FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice -(SELECT lwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid)))) AS monthlyprofit FROM wholesalesales, transaction WHERE transaction.transid =wholesalesales.transidw AND wholesalesales.wsunit='LwuSp' AND transaction.transtype = 'Wholesale_Paid'AND transaction.transdate BETWEEN ? AND ? AND transaction.transtime BETWEEN  ? AND ?  UNION SELECT  DATE_FORMAT (transDate, '%b') AS mn, YEAR (transDate)  AS yr ,FLOOR(SUM(wholesalesales.qty*(wholesalesales.wsprice -(SELECT hwucp FROM whouseinventory WHERE prodid = wholesalesales.prodid)))) AS monthlyprofit FROM wholesalesales, transaction WHERE transaction.transid =wholesalesales.transidw AND wholesalesales.wsunit='HwuSp' AND transaction.transtype = 'Wholesale_Paid'AND transaction.transdate BETWEEN ? AND ? AND transaction.transtime BETWEEN  ? AND ?) AS mix  GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
				


				
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				
				st.setDate(5, frmDate);
				st.setDate(6, toDate);
				st.setTime(7, frmTime);
				st.setTime(8, toTime);
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
				
				
				String sql = "SELECT  DATE_FORMAT (transDate, '%b') AS mn, YEAR (transDate)  AS yr, SUM(wholesalesales.qty*wholesalesales.wsprice )AS total FROM transaction,wholesalesales  WHERE wholesalesales.transidw = transaction.transid AND transaction.transtype = 'Wholesale_Paid' AND transDate   BETWEEN ? AND ? AND  transTime BETWEEN ? AND ? GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
				
				
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
		
}
