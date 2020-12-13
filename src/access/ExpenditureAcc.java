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
public class ExpenditureAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;
		 
		 
	
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
			
			
			String sql = "SELECT MIN(expiryDate) FROM prodExpenditureWHERE proID = ?";
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
			
			
			String sql = "SELECT expiryDate FROM prodExpenditureWHERE proID = ? AND purchID = ?";
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

				String sql = "SELECT  MAX(expID) AS lastID FROM Expenditure";
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
		
		
		
		
		
		public boolean registerExpenditure(int expID, int empID, int amount,
				String target, String reason, Timestamp date, Timestamp time) {
			Boolean flag = true;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			


				String sql = "	INSERT INTO  Expenditure (expID,	sourceID, amount,targetName,reason, date,time	) VALUES(?,?,?,?,?,?,?)";
				
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1	, expID);
			st.setInt(2, empID);
			st.setInt(3, amount);
			
			st.setString(4, target);
			st.setString(5, reason);
			st.setTimestamp(6, date);
			
			st.setTimestamp(7, time);
			
			
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
		public int checkExpID(int expID) {
			int pd = 0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			

				String sql = "SELECT expID  FROM Expenditure WHERE expID  = ?";
				PreparedStatement st=conn.prepareStatement(sql);
				st.setInt(1, expID);
				ResultSet rs = st.executeQuery();
				
				 while(rs.next()){
		pd = rs.getInt("expID");
				   
				
				//Display values

				  }

			}catch(SQLException se){
				//Handle errors for JDBC
				
				JOptionPane.showMessageDialog(null, "Database Error!\n  "+se);
			}catch(Exception e){
				//Handle errors for Class.forName
					JOptionPane.showMessageDialog(null, "Database Error!\n "+e);
			}finally{
				//finally block used to close resources
				
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					JOptionPane.showMessageDialog(null, "Database Error!\n "+se);
				}//end finally try

			}
			return pd;
		}
		public String[] getExpInfo(int expID) {
String[] info = new String[7];
			
			
			try{
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);

		String sql = "SELECT * FROM Expenditure WHERE expID = ?";
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, expID);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
			info[0]=rs.getString("expID");
			info[1]=rs.getString("sourceID");
			info[2]=rs.getString("amount");
			info[3]=rs.getString("targetName");
			
			info[4]=rs.getString("reason");
			info[5]=rs.getString("date");
			info[6]=rs.getString("time");
			
		
			
				
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
		
		public String[][] getAllExp() {
			int noRows = 0;//getExpCount() ;
			String[][] allItems = null;// new String[noRows][7];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT * FROM Expenditure ORDER BY date, time";
				
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while(rs.next())
					noRows = rs.getRow();
				
				allItems = new String [noRows][7];
				rs.beforeFirst();
				while(rs.next())
					for(int r=0; r < noRows; r++ )
						
				{
					
						allItems[r][0]=rs.getString("expID");
						allItems[r][1]=rs.getString("sourceID");
						allItems[r][2]=rs.getString("amount");
						
						
						allItems[r][3]=rs.getString("targetName");
						allItems[r][4]=rs.getString("reason");
						allItems[r][5]=rs.getString("date");
					
						allItems[r][6]=rs.getString("time");
						
					
						
			
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
		public boolean updateExpenditure(int expID, int empID, int amount,
				String target, String reason, java.util.Date date, Time time) {
			{Boolean flag = true;

			try{

				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				String sql ="UPDATE Expenditure SET sourceID=?, amount=?, targetName =  ?, reason = ?,date=?, time =? WHERE expID =?";
				
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setInt(1, empID);
				st.setInt(2, amount);
			
				st.setString(3, target);
				
				st.setString(4, reason);
				st.setDate(5, (Date) date);
				st.setTime(6, time); 
				st.setInt(7, expID);
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
		
		}
		
			public String[][] getExp(java.sql.Date frmDate, java.sql.Date toDate,
					Time frmTime, Time toTime) {
				int noRows = 0;//salesCount(frmDate, toDate, frmTime,	toTime) ;
				String[][] allItems = null;//new String[noRows][6];
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql = "SELECT  expid,sourceid, (SELECT fname FROM employee WHERE empid = expenditure.sourceid) AS fname, amount, targetname, reason, date, time FROM expenditure WHERE   date BETWEEN  ? AND ? AND  time BETWEEN ? AND ? ORDER BY date, time ";
					
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
						
						allItems[r][0]= rs.getString("expid");
						allItems[r][1]= rs.getString("sourceid");
						allItems[r][2]= rs.getString("fname");
						allItems[r][3]= rs.getString("amount");
						allItems[r][4]= rs.getString("targetname");
						allItems[r][5]= rs.getString("reason");
						
						allItems[r][6]= rs.getString("date");
						allItems[r][7]= rs.getString("time");
						
						
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

			public String[][] getDailyExp(java.sql.Date frmDate, java.sql.Date toDate,
					Time frmTime, Time toTime) {
				int noRows = 0;
				String[][] allItems = null;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);
					// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
					{;}
					String sql = "SELECT  date  ,sum(amount ) AS amount FROM expenditure  WHERE date   BETWEEN  ? AND ? AND  time BETWEEN ? AND ?  GROUP BY date ORDER BY date";
					
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
						
						allItems[r][0]= rs.getString("date");
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

		
		
		
			public String[][] getMonthlyExp(java.sql.Date frmDate, java.sql.Date toDate,
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
					
					
					String sql = "SELECT  DATE_FORMAT (date, '%b') AS mn, YEAR (date)  AS yr, SUM(amount )AS total FROM expenditure  WHERE date   BETWEEN ? AND ? AND  time BETWEEN ? AND ? GROUP BY  mn, yr ORDER BY yr, FIELD(mn, 'Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')";
					
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
			public float getTotalExp(java.sql.Date frmDate,
					java.sql.Date toDate, Time frmTime, Time toTime) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				int totalSales=0;
					
					try{
						
						Class.forName("com.mysql.jdbc.Driver");
						conn= DriverManager.getConnection(DB_URL, USER, PASS);
						// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0) )
						{;}
						String sql = "SELECT  sum(amount ) AS amount FROM expenditure WHERE date  BETWEEN  ? AND ? AND  time BETWEEN ? AND ? ";
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

		
		
		}
		