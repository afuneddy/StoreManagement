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
public class TransactionAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;
		 
	
		 public int getLastID() {

				int lastID = 0;
				
				try{
					//STEP 2: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//STEP 3: Open a connection
				
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					

					//STEP 4: Execute a query
					
					stmt = conn.createStatement();

					String sql = "SELECT  MAX(transID) AS lastID FROM Transaction";
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
			
			
		public boolean createTrans(int transID, String transType, int amount,
				Timestamp date, Timestamp time, int spID, int custID,
				String custName) {
			Boolean flag = true;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			


				String sql = "	INSERT INTO transaction (transID,	transType,amount, transDate,transTime,empid, custIDt, name) VALUES(?,?,?,?,?,?,?,?)";
				
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1	, transID);
			st.setString(2, transType);
		
			st.setInt(3	, amount);
			st.setTimestamp(4, date);
			st.setTimestamp(5, time);
			st.setInt(6, spID);
			st.setInt(7, custID);
			st.setString(8, custName);
		
			
			
			
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
		public String[] getTransDT(int parseInt) {
			
			
			String[] DT = new String[2];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT transDate, transTime FROM transaction WHERE transID = ? ";
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, parseInt);
				ResultSet rs = st.executeQuery();
				
				
				while(rs.next())
					
						
				{
					
						
						
						DT[0]=rs.getString("transDate");
					
						DT[1]=rs.getString("transTime");
						
					
						
			
		
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
			return DT;
		}


		public String[][] getAllTrans(Date frmDate, Date toDate, Time frmTime,
				Time toTime) {
			int noRows=0;
			String[][] allItems = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
				{;}
				
				
				String sql = "SELECT * from transaction WHERE  transDate  BETWEEN  ? AND ? AND transTime BETWEEN ? AND ? ORDER BY  transtype, transid" ;
				
				PreparedStatement st = conn.prepareStatement(sql);

				st.setDate(1, frmDate);
				st.setDate(2, toDate);
				st.setTime(3, frmTime);
				st.setTime(4, toTime);
				
				ResultSet rs = st.executeQuery();
				
		while(rs.next())
					noRows= rs.getRow();
		 allItems = new String[noRows][8];
				rs.beforeFirst();
				while(rs.next())
					
					
					for(int r=0; r < noRows; r++ )
						
				{
					
					allItems[r][0]= rs.getString("transid");
					allItems[r][1]= rs.getString("transtype");
					allItems[r][2]= rs.getString("amount");
					allItems[r][3]= rs.getString("transdate");
					allItems[r][4]= rs.getString("transtime");
					allItems[r][5]= rs.getString("empid");
					allItems[r][6]= rs.getString("custidt");
					allItems[r][7]= rs.getString("name");
					
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


		public String[] getTrans(int transID, String transType, int empID) {
			String[] allItems = new String[6];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
				{;}
				
				
				String sql = "SELECT amount, transDate, transtime, empid, custidt, name FROM transaction WHERE  transid = ? AND transtype =? AND empid =?" ;
				
				PreparedStatement st = conn.prepareStatement(sql);

				st.setInt(1, transID);
				st.setString(2, transType);
				st.setInt(3, empID);
				
				
				ResultSet rs = st.executeQuery();
				
		
				while(rs.next())
					
					
			
						
				{
					
					allItems[0]= rs.getString("amount");
					
					
					allItems[1]= rs.getString("transdate");
					allItems[2]= rs.getString("transtime");
					allItems[3]= rs.getString("empid");
					allItems[4]= rs.getString("custidt");
					allItems[5]= rs.getString("name");
					
			//JOptionPane.showMessageDialog(null, allItems[r][0]);
			//if(rs.next() == false)
			//	break;
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

		}}
	


