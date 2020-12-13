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
public class PurchaseAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;
		 
		 
		public int checkPurchID(int purchID) {
			int pid = 0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

				String sql = "SELECT purchaseID FROM purchase   WHERE purchaseID =?";
				
				PreparedStatement ps= conn.prepareStatement(sql);
				ps.setInt(1, purchID);
				ResultSet rs = ps.executeQuery();
				
				 while(rs.next()){
		pid = rs.getInt("purchaseID");
				   
				
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
		public int getPayment(int purchID) {
			int tpay = 0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

				String sql = "SELECT  advance FROM purchase   WHERE purchaseID =?";
				
				PreparedStatement ps= conn.prepareStatement(sql);
				ps.setInt(1, purchID);
				ResultSet rs = ps.executeQuery();
				
				 while(rs.next()){
		tpay= rs.getInt("advance");
				   
				
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
			return tpay;
		}
		public java.util.Date checkPurchDate(int purchID) {
			Date pd = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

				stmt = conn.createStatement();

				String sql = "	SELECT date FROM purchase  WHERE purchaseID  = "+purchID+"";
				ResultSet rs = stmt.executeQuery(sql);
				
				 while(rs.next()){
		pd = rs.getDate("date");
				   
				
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
			return pd;
		}
		public String getBzName(int id) {
		String	bzName = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			
				String sql = "	SELECT bzName FROM supplier  WHERE supID  = ?";
				PreparedStatement ps= conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				 while(rs.next()){
		bzName = rs.getString("bzName");
				   
				
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
				}//end finally trye
				

			}
			return bzName;
		}
		public java.util.Date getMinExpDate(int parseInt) {
			Date date = null;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			String sql = "SELECT MIN(expiryDate) FROM prodpurchase WHERE proID = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, parseInt);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
				
			date = rs.getDate("MIN(expiryDate)");
			
			
			

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
				}//end finally trye
				

			}
			return date;}
		public java.util.Date getExpDate(int parseInt, int purchID) {
			Date date = null;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			String sql = "SELECT expiryDate FROM prodpurchase WHERE proID = ? AND purchID = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, parseInt);
			st.setInt(2,purchID);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
				
			date = rs.getDate("expiryDate");
			
			
			

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
				}//end finally trye
				

			}
			return date;
		}
		public int generateID() {

			int lastID = 0;
			
			try{
				//STEP 2: Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");

				//STEP 3: Open a connection
			
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				

				//STEP 4: Execute a query
				
				stmt = conn.createStatement();

				String sql = "SELECT  MAX(purchaseID) AS lastID FROM purchase ";
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
		public boolean registerPurchase(int purID, int suID, int tCost,
				int advance, java.sql.Date dueDate, java.sql.Date date,
				Timestamp insertDateNow) {
			Boolean flag = true;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			


				String sql = "	INSERT INTO  purchase  (purchaseID,	suID, amount,advance,dueDate, date,	time	) VALUES(?,?,?,?,?,?,?)";
				
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1	, purID);
			st.setInt(2, suID);
			st.setInt(3, tCost);
			
			st.setInt(4, advance);
			st.setDate(5, dueDate);
			st.setDate(6, date);
			
			st.setTimestamp(7, insertDateNow);
			
			
			st.executeUpdate();
				

				  

			}catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
				flag = false;
			}catch(Exception e){
				//Handle errors for Class.forName
					JOptionPane.showMessageDialog(null, "Database Error!\n"+e);flag = false;
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
		public String[] getPurInfo(int purID) {
			String[] info = new String[8];
			
			
			try{
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);

		String sql = "SELECT * FROM purchase WHERE purchaseID = ?";
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, purID);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
			info[0]=rs.getString("purchaseID");
			info[1]=rs.getString("suID");
			info[2]=rs.getString("amount");
			info[3]=rs.getString("advance");
			
			info[4]=rs.getString("dueDate");
			info[5]=rs.getString("date");
			info[6]=rs.getString("time");
			info[7]="";
		
			
				
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
				public String[][] getAllPur() {
			int noRows = 0;
			String[][] allItems = null;// = ;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT * FROM purchase ORDER BY date";
				
				PreparedStatement st = conn.prepareStatement(sql);
			
				
				ResultSet rs = st.executeQuery();
				while(rs.next())
					noRows = rs.getRow();
				
				allItems = new String[noRows][8];
				rs.beforeFirst();
				while(rs.next())
					for(int r=0; r < noRows; r++ )
						
				{
					
						allItems[r][0]=rs.getString("purchaseID");
						allItems[r][1]=rs.getString("suID");
						allItems[r][2]=rs.getString("amount");
						
						
						allItems[r][3]=rs.getString("advance");
						allItems[r][4]=rs.getString("dueDate");
						allItems[r][5]=rs.getString("date");
					
						allItems[r][6]=rs.getString("time");
						
					
						
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
		public boolean updatePurchase(int purID, int supID, int advance,
				int tCost, java.sql.Date dueDate, java.sql.Date date,
				Timestamp insertDateNow) 
			{Boolean flag = true;

			try{

				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				String sql ="UPDATE purchase SET suID=?, amount=?, advance = advance + ?, dueDate=?, date=? WHERE purchaseID =?";
				
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setInt(1, supID);
				st.setInt(2, tCost);
			//	JOptionPane.showConfirmDialog(null, "ok");
				st.setInt(3, advance);
				st.setDate(4, dueDate);
				st.setDate(5, date); 
				st.setInt(6, purID);
				st.executeUpdate();
                
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
		
		
		public int getTotDebt(int id) {
			int debt=0;
try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT SUM(amount - advance) AS tDebt FROM purchase  WHERE suID= ?";
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, id);
				ResultSet rs = st.executeQuery();
				
				
				while(rs.next())
					
				debt = rs.getInt("tDebt");	
				
				
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
			return debt;
		}
		public int getSup(int purchID) {
			int id = 0;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			String sql = "SELECT suID FROM purchase WHERE purchaseID = ? ";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, purchID);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
				
			id = rs.getInt("suID");
			
			
			

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
				}//end finally trye
				

			}
			return id;
		}
	
		public int getPurchaseCost(int purchID) {
			int cost = 0;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			String sql = "SELECT amount FROM purchase WHERE purchaseID = ? ";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, purchID);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
				
			cost = rs.getInt("amount");
			
			
			

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
				}//end finally trye
				

			}
			return cost;
		}


		public float getTotalPurch(Date frmDate, Date toDate, Time frmTime, Time toTime) {
			// TODO Auto-generated method stub
		int totalPurch=0;
			
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
				{;}
				String sql = "SELECT  SUM(amount) AS amount FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? ";
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				ResultSet rs = st.executeQuery();
				while(rs.next())
					totalPurch = rs.getInt("amount");
				
				
				
			}
					catch(SQLException se){
						//Handle errors for JDBC
						
						JOptionPane.showMessageDialog(null, "Database Error!source:purchaseAcc/getTotalSales\n"+se);
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
			return totalPurch;
		}
	
		
				public String[][] getAllPur(java.sql.Date frmDate, java.sql.Date toDate,
				Time frmTime, Time toTime) {
					int noRows = 0;
					String[][] allItems = null;// = ;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT * FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? ORDER BY date";
						
						PreparedStatement st = conn.prepareStatement(sql);
						st.setDate(1, frmDate);
						st.setDate(2, toDate);
						st.setTime(3, frmTime);
						st.setTime(4, toTime);
						
						ResultSet rs = st.executeQuery();
						while(rs.next())
							noRows = rs.getRow();
						
						allItems = new String[noRows][8];
						rs.beforeFirst();
						while(rs.next())
							for(int r=0; r < noRows; r++ )
								
						{
							
								allItems[r][0]=rs.getString("purchaseID");
								allItems[r][1]=rs.getString("suID");
								allItems[r][2]=rs.getString("amount");
								
								
								allItems[r][3]=rs.getString("advance");
								allItems[r][4]=rs.getString("dueDate");
								allItems[r][5]=rs.getString("date");
							
								allItems[r][6]=rs.getString("time");
								
							
								
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
				public String[][] getDailyPurch(java.sql.Date frmDate,
						java.sql.Date toDate, Time frmTime, Time toTime) {
					// TODO Auto-generated method stub
					int noRows = 0;
					String[][] allItems = null;// = ;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT date, SUM(amount) AS total FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? GROUP BY date ORDER BY date";
						
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
							
								allItems[r][0]=rs.getString("date");
								allItems[r][1]=rs.getString("total");
								
								
							
							
								
							
								
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
				public String[][] getMonthlyPurch(java.sql.Date frmDate,
						java.sql.Date toDate, Time frmTime, Time toTime) {
					// TODO Auto-generated method stub
					int noRows = 0;
					String[][] allItems = null;// = ;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT  DATE_FORMAT (date, '%b') AS mn, YEAR (date)  AS yr, SUM(amount) AS total FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
						
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
							
								allItems[r][0]=rs.getString("mn");
								allItems[r][1]=rs.getString("yr");
								allItems[r][2]=rs.getString("total");
								
							
							
								
							
								
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
				public String[][] unpaidDaily(java.sql.Date frmDate,
						java.sql.Date toDate, Time frmTime, Time toTime) {
					int noRows = 0;
					String[][] allItems = null;// = ;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT date, SUM(amount - advance) AS total FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? GROUP BY date ORDER BY date";
						
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
							
								allItems[r][0]=rs.getString("date");
								allItems[r][1]=rs.getString("total");
								
								
							
							
								
							
								
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
				public String[][] getUnpaidMonthly(java.sql.Date frmDate,
						java.sql.Date toDate, Time frmTime, Time toTime) {
					// TODO Auto-generated method stub
					int noRows = 0;
					String[][] allItems = null;// = ;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT  DATE_FORMAT (date, '%b') AS mn, YEAR (date)  AS yr, SUM(amount - advance) AS total FROM purchase WHERE  date  BETWEEN  ? AND ? AND time BETWEEN ? AND ? GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
						
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
							
								allItems[r][0]=rs.getString("mn");
								allItems[r][1]=rs.getString("yr");
								allItems[r][2]=rs.getString("total");
								
							
							
								
							
								
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


	


