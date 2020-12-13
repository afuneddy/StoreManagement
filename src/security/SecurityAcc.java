package security;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.swing.JOptionPane;

public class SecurityAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	 Connection conn = null;
		 Statement stmt = null;
			public String getPassword(int empID) {
		 String pswd = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");


				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

		

				String sql = "	SELECT password FROM employee  where empid  = ?";
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, empID);
				
				ResultSet rs = st.executeQuery();
				
				 while(rs.next()){
		pswd = rs.getString("password");
				   
				
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
			return pswd;

	}

	
		
			public String getUserName(int empID) {
				 String un = null;
					try{
						
						Class.forName("com.mysql.jdbc.Driver");


						conn = DriverManager.getConnection(DB_URL, USER, PASS);
					

				

						String sql = "	SELECT username FROM employee  where empid  = ?";
						
						PreparedStatement st = conn.prepareStatement(sql);
						st.setInt(1, empID);
						
						ResultSet rs = st.executeQuery();
						
						 while(rs.next()){
				un = rs.getString("username");
						   
						
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
					return un;

			}



			public int getSecurityLevel(int empID) {
				// TODO Auto-generated method stub
				int pswd = 0;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				

			

					String sql = "	SELECT securityLev FROM employee  where empid  = ?";
					
					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, empID);
					
					ResultSet rs = st.executeQuery();
					
					 while(rs.next()){
			pswd = rs.getInt("securityLev");
					   
					
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
				return pswd;

			}



			public boolean updateRole(int securitylev, String role) {
				// TODO Auto-generated method stub
				{Boolean flag = true;

				try{

					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql ="UPDATE role SET role=? WHERE securitylev =?";
					PreparedStatement st = conn.prepareStatement(sql);
					
					st.setString(1, role);
					st.setInt(2, securitylev);
				
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
			
			public String[] getClassRightsInfo(int classID) {
				String[] info = new String[3];
						
						
						try{
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);

					String sql = "SELECT * FROM classrights WHERE classid = ?";
						PreparedStatement st= conn.prepareStatement(sql);
						st.setInt(1, classID);
						ResultSet rs = st.executeQuery();
						
						while(rs.next())
						{
						info[0]=rs.getString("classID");
						info[1]=rs.getString("classcom");
						info[2]=rs.getString("securitylev");
						
					
						
							
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
			public boolean updateClassRights(int classID, String classcom, int securitylev) {
				// TODO Auto-generated method stub
				{Boolean flag = true;

				try{

					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql ="UPDATE classrights SET classcom =?, securitylev=? WHERE classid =?";
					PreparedStatement st = conn.prepareStatement(sql);
					
					st.setString(1, classcom);
					st.setInt(2, securitylev);
					st.setInt(3, classID);
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
			public Boolean registerClassrights(int classID, String classcom, int securitylev) {
				// TODO Auto-generated method stub

				Boolean flag = true;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				


					String sql = "	INSERT INTO  classrights  (classID, classcom, securityLev	) VALUES(?,?,?)";
					
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, classID);
				st.setString(2, classcom);
				st.setInt(3, securitylev);
				
				
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



			public String[][] getAllClassRights() {
				int noRows = 0;//getEmpCount() ;
				String[][] allItems = null;// new String[noRows][14];
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql = "SELECT	* FROM classrights  ORDER BY classcom";
					
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();
					while (rs.next())
						noRows = rs.getRow();
					allItems = new String[noRows][3];
					rs.beforeFirst();
					while(rs.next())
						for(int r=0; r < noRows; r++ )
							
					{
						
							allItems[r][0]=rs.getString("classid");
							allItems[r][1]=rs.getString("classcom");
							allItems[r][2]=rs.getString("securitylev");
							
						
							
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
			public int generateClassRightsID() {
				int lastID = 0;
				
				try{
					//STEP 2: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//STEP 3: Open a connection
				
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					

					//STEP 4: Execute a query
					
					stmt = conn.createStatement();

					String sql = "SELECT  MAX(classID) as lastID FROM classrights  ";
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
			public String[][] getAllLogins(Date d, Time t, Date d2, Time t2) {
				int noRows = 0;//getEmpCount() ;
				String[][] allItems = null;// new String[noRows][14];
				try{
					
					Class.forName("com.mysql.jdbc.Driver");
					conn= DriverManager.getConnection(DB_URL, USER, PASS);
					
					String sql = "SELECT	trackid, empidt, (SELECT username FROM Employee WHERE empID = tracklogin.empidt) AS username, logindate, logintime, logoutdate, logouttime FROM tracklogin WHERE logindate BETWEEN ? AND ? AND logintime BETWEEN ? AND ? ORDER BY logindate, logintime";
					
					PreparedStatement st = conn.prepareStatement(sql);
				st.setDate(1, d);
				st.setDate(2, d2);
				st.setTime(3, t);
				
				st.setTime(4, t2);
			
					ResultSet rs = st.executeQuery();
					while (rs.next())
						noRows = rs.getRow();
					allItems = new String[noRows][7];
					rs.beforeFirst();
					while(rs.next())
						for(int r=0; r < noRows; r++ )
							
					{
						
							allItems[r][0]=rs.getString("trackid");
							allItems[r][1]=rs.getString("empidt");
							allItems[r][2]=rs.getString("username");
							
							allItems[r][3]=rs.getString("logindate");
							allItems[r][4]=rs.getString("logintime");
							allItems[r][5]=rs.getString("logoutdate");
							allItems[r][6]=rs.getString("logouttime");
						
							
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
			
			public int generateLoginID() {
				int lastID = 0;
				
				try{
					//STEP 2: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//STEP 3: Open a connection
				
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					

					//STEP 4: Execute a query
					
					stmt = conn.createStatement();

					String sql = "SELECT  MAX(trackid) as lastID FROM tracklogin  ";
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
			
			public void insertLogin(int trackid, int empID) {
				// TODO Auto-generated method stub

				//Boolean flag = true;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				


					String sql = "	INSERT INTO  tracklogin  (trackid, empidt,logindate, logintime) VALUES(?,?,NOW() ,NOW())";
					
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, trackid);
				st.setInt(2, empID);
						
				
				
				st.executeUpdate();
				
				String sql2 = "UPDATE employee set loginstatus = ? WHERE empid = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setInt(1, 1);
				st2.setInt(2, empID);
				st2.executeUpdate();  

				}catch(SQLException se){
					//Handle errors for JDBC
					
					JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
					//flag = false;
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
			
			public void insertLogout(int trackid, int empid) {
				// TODO Auto-generated method stub

				//Boolean flag = true;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				


					String sql = "	UPDATE  tracklogin  SET logoutdate = NOW() ,logouttime = NOW() WHERE trackid = ?";
					
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, trackid);
			
					
				st.executeUpdate();
					
				String sql2 = "UPDATE employee set loginstatus = ? WHERE empid = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setInt(1, 1);
				st2.setInt(2, empid);
				st2.executeUpdate();  

					  

				}catch(SQLException se){
					//Handle errors for JDBC
					
					JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
					//flag = false;
				}catch(Exception e){
					//Handle errors for Class.forName
						JOptionPane.showMessageDialog(null, "Database Error!\n"+e);//flag = false;
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


}