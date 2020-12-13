package access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class CustomerAcc {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	static Connection conn = null;
		 static Statement stmt = null;







	public int generateID() {
		int lastID = 0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
		
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			//STEP 4: Execute a query
			
			stmt = conn.createStatement();

			String sql = "SELECT  MAX(custID) as lastID FROM customer  ";
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



	
	public String[][] searchString(String text) {
		// TODO Auto-generated method stub
		
		String[][] lookup = null;
		int size = 0;

		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection( DB_URL, USER, PASS);
			
						
			
		String 	sql = "SELECT custID, fName FROM  customer WHERE fName LIKE ? OR otherNames LIKE ? ORDER BY fName";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, text+"%");
			stm.setString(2, text+"%");
			 ResultSet rs2 = stm.executeQuery();
			 
			 while(rs2.next())
				 
					size = rs2.getRow();
					
						lookup = new String[size][2];

			rs2.beforeFirst();
				while(rs2.next())
					
				for(int r=0; r < size; r++ ){ 
				
			lookup[r][0]= rs2.getString("custID");
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



	



	


	/*public String[] populateCombo() {
String[] info = new String[14];
		
		int count = 0;
		try{
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(DB_URL, USER, PASS);

		
	
			 
				
					
					String sql2 = "SELECT DISTINCT(role)  FROM role ";
					PreparedStatement st2= conn.prepareStatement(sql2);
					
					ResultSet rs2 = st2.executeQuery();
					while(rs2.next())
						count = rs2.getRow();
					
					while(rs2.next())
					for(int r=0; r < count; r++ ){ 
						
						info[r]= rs2.getString("role");
					
						
						if(rs2.next() == false)
							break;
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
*/




	public boolean registerCustomer(int cusID, String fName, String otherNames,
			int phone, String address, String email, int nic, int grade) {
		Boolean flag = true;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");


			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		


			String sql = "	INSERT INTO  customer  (custID,	fName, otherNames,phone, address,	email,nIDCardNo, grade	) VALUES(?,?,?,?,?,?,?,?)";
			
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1	, cusID);
		st.setString(2, fName);
		st.setString(3, otherNames);
		
		st.setInt(4, phone);
		st.setString(5, address);
		st.setString(6, email);
		
		st.setInt(7, nic);
		st.setInt(8, grade);
		
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



	public int checkCusID(int cusID) {
int cuID=0;
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		

			//STEP 4: Execute a query
		
			stmt = conn.createStatement();

			String sql = "	SELECT custID FROM customer  where custID  = "+cusID+"";
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
		
			//Retrieve by column name
			 while(rs.next()){
	cuID = rs.getInt("custID");
			   
			
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
		return cuID;

	}



	public String[] getCusInfo(int cusID) {
String[] info = new String[8];
		
		
		try{
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(DB_URL, USER, PASS);

	String sql = "SELECT * FROM customer WHERE custID = ?";
		PreparedStatement st= conn.prepareStatement(sql);
		st.setInt(1, cusID);
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
		info[0]=rs.getString("custID");
		info[1]=rs.getString("fName");
		info[2]=rs.getString("otherNames");

		
		info[3]=rs.getString("phone");
		info[4]=rs.getString("address");
		info[5]=rs.getString("email");
	
		info[6]=rs.getString("nIDCardNo");
	
		info[7]=rs.getString("grade");
	
		
			
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



	


	public String[][] getallCus() {
		int noRows = 0;//getCusCount() ;
		String[][] allItems = null;//new String[noRows][8];
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "SELECT * FROM customer  ORDER BY fName, custID";
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next())
				noRows=rs.getRow();
			allItems = new String[noRows][8];
			rs.beforeFirst();
			while(rs.next())
				for(int r=0; r < noRows; r++ )
					
			{
				
					allItems[r][0]=rs.getString("custID");
					allItems[r][1]=rs.getString("fName");
					allItems[r][2]=rs.getString("otherNames");
					
					
					allItems[r][3]=rs.getString("phone");
					allItems[r][4]=rs.getString("address");
					allItems[r][5]=rs.getString("email");
				
					allItems[r][6]=rs.getString("nIDCardNo");
					allItems[r][7]=rs.getString("grade");
				
					
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




	public boolean updateCustomer(int cusID, String fName, String otherNames,
			int phone, String address, String email, int nic, int grade) {
		{Boolean flag = true;

		try{

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql ="UPDATE customer SET fName=?, otherNames=?,phone=?, address=?,	email=?, nIDCardNo=?, grade=? WHERE custID =?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, fName);
			st.setString(2, otherNames);
			
			st.setInt(3, phone);
			st.setString(4, address);
			st.setString(5, email);
			
			st.setInt(6, nic);
			st.setInt(7, grade);
			st.setInt(8, cusID);
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
}