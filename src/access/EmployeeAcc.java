package access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class EmployeeAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;



	public boolean registerEmployee(int empID, String fName, String otherNames,
			String userName, int phone, String address, String email,
			java.sql.Date birthDate, String birthPlace, int nic,
			int roleid, int salary, java.sql.Date recruitDate,
			String password) {
		
		Boolean flag = true;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");


			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		


			String sql = "	INSERT INTO  employee  (empID,	fName, otherNames,userName,password,phone, address,	email,	" +
					"DOB,POB,nIDCardNo, securityLev, salary,recruitDate	) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1	, empID);
		st.setString(2, fName);
		st.setString(3, otherNames);
		st.setString(4, userName);	
		st.setString(5, password);
		st.setInt(6, phone);
		st.setString(7, address);
		st.setString(8, email);
		st.setDate(9, birthDate);
		st.setString(10, birthPlace);
		st.setInt(11, nic);
		st.setInt(12, roleid);
		st.setInt(13, salary);
		st.setDate(14, recruitDate);
		
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



	public int generateID() {
		int lastID = 0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
			
			stmt = conn.createStatement();

			String sql = "SELECT  MAX(empID) as lastID FROM employee  ";
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



	public String[] getEmpInfo(int empID) {
		String[] info = new String[15];
		
		
		try{
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(DB_URL, USER, PASS);

	String sql = "SELECT * FROM Employee WHERE empID = ?";
		PreparedStatement st= conn.prepareStatement(sql);
		st.setInt(1, empID);
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
		info[0]=rs.getString("empID");
		info[1]=rs.getString("fName");
		info[2]=rs.getString("otherNames");
		info[3]=rs.getString("userName");
		info[4]=rs.getString("password");
		info[5]=rs.getString("phone");
		info[6]=rs.getString("address");
		info[7]=rs.getString("email");
		info[8]=rs.getString("DOB");
		info[9]=rs.getString("POB");
		info[10]=rs.getString("nIDCardNo");
		info[11]=rs.getString("securityLev");
		info[12]=rs.getString("salary");
		info[13]=rs.getString("recruitDate");
		info[14]=rs.getString("loginstatus");
		
			
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
	
	public String[][] searchString(String text) {
		// TODO Auto-generated method stub
		
		String[][] lookup = null;
		int size = 0;

		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection( DB_URL, USER, PASS);
			
			
				
			
				
			
		String 	sql = "SELECT empID, fName FROM  employee WHERE fName LIKE ? OR otherNames LIKE ? ORDER BY fName";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, text+"%");
			stm.setString(2, text+"%");
			 ResultSet rs2 = stm.executeQuery();

				while(rs2.next())
					 
			size = rs2.getRow();

			rs2.beforeFirst();
			
			lookup = new String [size][2];
				while(rs2.next())
					
				for(int r=0; r < size; r++ ){ 
				
			lookup[r][0]= rs2.getString("empID");
			lookup[r][1]= rs2.getString("fName");
			
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



	public int checkEmpID(int empID) {
int emID=0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT empID FROM employee  where empID  = "+empID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	emID = rs.getInt("empID");
			   
			
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
		return emID;

	}

	public String checkUserName(String un) {
		String u=null;
				
				try{
					//STEP 2: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					//STEP 3: Open a connection
					
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				

					//STEP 4: Execute a query
				
					stmt = conn.createStatement();

					String sql = "	SELECT username FROM employee  where username  = ?";
					
					PreparedStatement s = conn.prepareStatement(sql);
					s.setString(1, un);
					ResultSet rs = s.executeQuery();
					//STEP 5: Extract data from result set
				
					//Retrieve by column name
					 while(rs.next()){
			un = rs.getString("username");
					   
					
					//Display values

					  }

				}catch(SQLException se){
					//Handle errors for JDBC
					JOptionPane.showMessageDialog(null, se);
				}catch(Exception e){
					//Handle errors for Class.forName
						JOptionPane.showMessageDialog(null, "fDatabase Error!\n"+e);
				}finally{
					//finally block used to close resources
					
					try{
						if(conn!=null)
							conn.close();
					}catch(SQLException se){
						JOptionPane.showMessageDialog(null, se);
					}//end finally try

				}
				return u;

			}


	public boolean updateEmployee(int empID, String fName, String otherNames,
			String userName, int phone, String address, String email,
			Date birthDate, String birthPlace, int nic, int roleid, int salary,
			Date recruitDate, String password) {
		{Boolean flag = true;

		try{

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql ="UPDATE employee SET fName=?, otherNames=?,userName=?,password=?,phone=?, address=?,	email=?,"+	
					"DOB=?,POB=?,nIDCardNo=?, securityLev=?, salary=?,recruitDate=? WHERE empID =?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, fName);
			st.setString(2, otherNames);
			st.setString(3, userName);	
			st.setString(4,  password);
			st.setInt(5, phone);
			st.setString(6, address);
			st.setString(7, email);
			st.setDate(8, birthDate);
			st.setString(9, birthPlace);
			st.setInt(10, nic);
			st.setInt(11, roleid);
			st.setInt(12, salary);
			st.setDate(13, recruitDate);
			st.setInt(14, empID);
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
	}}



	
	public String  getRole(int roleid) {
		
				
				String role=null;
				try{
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(DB_URL, USER, PASS);


							
							String sql2 = "SELECT role  FROM role WHERE securityLev = ?";
							PreparedStatement st= conn.prepareStatement(sql2);
							st.setInt(1, roleid);
							ResultSet rs = st.executeQuery();
							
							
							while(rs.next())
							role = rs.getString("role");
					
				
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

				return role;
			}



	public String[][] getAllEmp() {
		int noRows = 0;//getEmpCount() ;
		String[][] allItems = null;// new String[noRows][14];
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "SELECT	empID,	fName ,otherNames,	userName,		password ,phone,address,	email,	DOB,	POB,	nIDCardNo,		(SELECT role FROM role  WHERE securityLev = employee.securityLev) AS role,	salary,	recruitDate, loginstatus  FROM employee  ORDER BY fName, empID";
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next())
				noRows = rs.getRow();
			allItems = new String[noRows][15];
			rs.beforeFirst();
			while(rs.next())
				for(int r=0; r < noRows; r++ )
					
			{
				
					allItems[r][0]=rs.getString("empID");
					allItems[r][1]=rs.getString("fName");
					allItems[r][2]=rs.getString("otherNames");
					allItems[r][3]=rs.getString("userName");
					allItems[r][4]=rs.getString("password");
					allItems[r][5]=rs.getString("phone");
					allItems[r][6]=rs.getString("address");
					allItems[r][7]=rs.getString("email");
					allItems[r][8]=rs.getString("DOB");
					allItems[r][9]=rs.getString("POB");
					allItems[r][10]=rs.getString("nIDCardNo");
					allItems[r][11]=rs.getString("role");
					allItems[r][12]=rs.getString("salary");
					allItems[r][13]=rs.getString("recruitDate");
					allItems[r][14]=rs.getString("loginstatus");
					
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



	public String getEmpFName(int parseInt) {
String fn=null;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT fName FROM employee  where empID  = "+parseInt+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	fn = rs.getString("fName");
			   
			
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
		return fn;

	}



	public int generateRoleID() {
		// TODO Auto-generated method stub
int lastID = 0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
			
			stmt = conn.createStatement();

			String sql = "SELECT  MAX(securityLev) as lastID FROM role  ";
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



	public Boolean registerRole(int roleid, String role) {
		// TODO Auto-generated method stub

		Boolean flag = true;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");


			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		


			String sql = "	INSERT INTO  role  (securityLev,	role	) VALUES(?,?)";
			
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1	, roleid);
		st.setString(2, role);
		
		
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



	public String[][] getAllEmpRoles() {
		int noRows = 0;//getEmpCount() ;
		String[][] allItems = null;// new String[noRows][14];
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "SELECT	securityLev, role FROM role  ORDER BY role";
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next())
				noRows = rs.getRow();
			allItems = new String[noRows][2];
			rs.beforeFirst();
			while(rs.next())
				for(int r=0; r < noRows; r++ )
					
			{
				
					allItems[r][0]=rs.getString("securityLev");
					allItems[r][1]=rs.getString("role");
					
				
					
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



	public String[] getEmpRoleInfo(int roleid) {
String[] info = new String[2];
		
		
		try{
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(DB_URL, USER, PASS);

	String sql = "SELECT * FROM role WHERE securityLev = ?";
		PreparedStatement st= conn.prepareStatement(sql);
		st.setInt(1, roleid);
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
		info[0]=rs.getString("securityLev");
		info[1]=rs.getString("role");
		
	
		
			
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