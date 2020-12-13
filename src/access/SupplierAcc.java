package access;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SupplierAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;
	
		 
		 public String[][] searchString(String text) {

				String[][] lookup = null;
				int size = 0;

				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection( DB_URL, USER, PASS);
					
				

						
							
					
				String 	sql = "SELECT supID,bzName, fName FROM  supplier WHERE fName LIKE ? OR bzName LIKE ? ORDER BY bzName";
					PreparedStatement stm = conn.prepareStatement(sql);
					stm.setString(1, text+"%");
					stm.setString(2, text+"%");
					 ResultSet rs2 = stm.executeQuery();

						while(rs2.next())
							 
					size = rs2.getRow();
					
						lookup = new String[size][3];
						
						
					rs2.beforeFirst();
						while(rs2.next())
							
						for(int r=0; r < size; r++ ){ 
						
					lookup[r][0]= rs2.getString("supID");
					lookup[r][1]= rs2.getString("bzName");
					lookup[r][2]= rs2.getString("fName");
					
					if(rs2.next() == false)
						break;
					}
						
						
					//JOptionPane.showMessageDialog(null, lookup[i]);}
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
		 
		 
		 public int getSupID(int supID) {
				int suID = 0;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				

			

					String sql = "	SELECT supID FROM supplier  where supID  = ?";
					
					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, supID);
					
					ResultSet rs = st.executeQuery();
					
					 while(rs.next()){
			suID = rs.getInt("supID");
					   
					
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
				return suID;
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

					String sql = "SELECT  MAX(supID) AS lastID FROM supplier ";
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


			public boolean registerSupplier(int supID, String fName,
					String otherNames, String bzName, int phone1, int phone2,
					String address, String email, String sampleProds, int grade) {
				Boolean flag = true;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				


					String sql = "	INSERT INTO  supplier  (supID,	fName, otherNames,bzName,phone1,phone2, address,	email,sampleProds, grade	) VALUES(?,?,?,?,?,?,?,?,?,?)";
					
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1	,supID);
				st.setString(2, fName);
				st.setString(3, otherNames);
				
				st.setString(4, bzName);
				st.setInt(5,phone1);
				st.setInt(6,phone2);
				st.setString(7, address);
				
				st.setString(8, email  );
				st.setString(9, sampleProds);
				st.setInt(10, grade);
				
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


			public boolean updateSupplier(int supID, String fName,
					String otherNames, String bzName, int phone1, int phone2,
					String address, String email, String sampleProds, int grade) {
				{Boolean flag = true;

				try{

					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql ="UPDATE supplier SET fName=?, otherNames=?,bzName = ? ,phone1=?, phone2=? , address=?,	email=?, sampleProds=?, grade=? WHERE supID =?";
					
					PreparedStatement st = conn.prepareStatement(sql);
					
					st.setString(1, fName);
					st.setString(2, otherNames);
					st.setString(3, bzName);
					st.setInt(4, phone1);
					st.setInt(5, phone2);
					st.setString(6, address);
					st.setString(7, email);
					
					st.setString(8, sampleProds);
					st.setInt(9, grade);
					st.setInt(10, supID);
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

				return flag;}}


		

			public String[][] getAllSup() {
				int noRows = 0;//getSupCount() ;
				String[][] allItems = null;//new String[noRows][10];
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql = "SELECT * FROM supplier ORDER BY bzName, fName";
					
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();
					while(rs.next())
						noRows = rs.getRow();
					
					allItems = new String[noRows][10];
					rs.beforeFirst();
					while(rs.next())
						for(int r=0; r < noRows; r++ )
							
					{
						
							allItems[r][0]=rs.getString("supID");
							allItems[r][1]=rs.getString("fName");
							allItems[r][2]=rs.getString("otherNames");
							
							
							allItems[r][3]=rs.getString("bzName");
							allItems[r][4]=rs.getString("phone1");
							allItems[r][5]=rs.getString("phone2");
						
							allItems[r][6]=rs.getString("address");
							allItems[r][7]=rs.getString("email");
							allItems[r][8]=rs.getString("sampleProds");
							allItems[r][9]=rs.getString("grade");
							
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


			public String[] getSupInfo(int supID) {
				String[] info = new String[10];
				
				
				try{
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "SELECT * FROM supplier WHERE supID = ?";
				PreparedStatement st= conn.prepareStatement(sql);
				st.setInt(1, supID);
				ResultSet rs = st.executeQuery();
				
				while(rs.next())
				{
				info[0]=rs.getString("supID");
				info[1]=rs.getString("fName");
				info[2]=rs.getString("otherNames");
				info[3]=rs.getString("bzName");
				
				info[4]=rs.getString("phone1");
				info[5]=rs.getString("phone2");
				info[6]=rs.getString("address");
				info[7]=rs.getString("email");
			
				info[8]=rs.getString("sampleProds");
			
				info[9]=rs.getString("grade");
			
				
					
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
			}
