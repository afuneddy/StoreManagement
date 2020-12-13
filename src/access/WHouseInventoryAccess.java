package access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class WHouseInventoryAccess {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static	final String DB_URL = "jdbc:mysql://localhost/PLANETTASTORE";
	static int	wsp;
	static String desc;
		//  Database credentials
	static	final String USER = "root";
	static	final String PASS = "m1n3";
		

	  


	 Connection conn = null;
		 Statement stmt = null;
	
		 public  WHouseInventoryAccess(){}

		public int registerWSaleInventory(int prodID, int noRetUperLwu, String lwu, int lwuQty, int lwuUcp,
				int lwuSp, int lwupHwu, String hwu, int hwuQty, int hwuUcp,
				int hwuSp, int limitLevel, int tCost, int tQtyLwu, String cat,
				String loc, Date purchDate, Date expDate) {
			int flag = 1;
						try{
				
				Class.forName("com.mysql.jdbc.Driver");


				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			

				String sql = "	INSERT INTO  whouseinventory (prodID, noRetUPerLwu, lwuDesc, lwuQty, lwuCp,lwuSp, lwuPhwu, hwuDesc, hwuQty,hwuCp, hwuSp, lwuLimLev, category, location) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setInt(1, prodID);
				
				stm.setInt(2, noRetUperLwu);
				stm.setString(3, lwu);
				stm.setInt(4, lwuQty);
				stm.setInt(5, lwuUcp);
				stm.setInt(6, lwuSp);
				stm.setInt(7, lwupHwu);
				stm.setString(8, hwu);
				stm.setInt(9, hwuQty);
				stm.setInt(10, hwuUcp);
				stm.setInt(11, hwuSp); 
				stm.setInt(12, limitLevel);
				
				
				stm.setString(13, cat);
				stm.setString(14, loc);
		
				
			stm.executeUpdate();

				  

			}catch(SQLException se){
				//Handle errors for JDBC
				flag = 0;
				JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
			}catch(Exception e){
				//Handle errors for Class.forName
				flag = 0;
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

		public int getProdID(int prodRetID) {
		
				int pid = 0;
				try{
					
					Class.forName("com.mysql.jdbc.Driver");


					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				

			

					String sql = "	SELECT prodID FROM prodretids  where prodRetID  = ?";
					
					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, prodRetID);
					
					ResultSet rs = st.executeQuery();
					
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

		public int updateWHInventory(int prodID, int noRetUperLwu,
				String lwu, int lwuQty, int lwuUcp, int lwuSp, int lwupHwu,
				String hwu, int hwuQty, int hwuUcp, int hwuSp, int limitLevel,
				int tCost, int tQtyLwu, String cat, String loc, Date purchDate,
				Date expDate) {
			// TODO Auto-generated method stub
			int flag = 1;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			String sql = "	UPDATE  whouseinventory SET noRetUPerLwu = ?, lwuDesc = ?, lwuQty =  ?, lwuCp = ?,lwuSp = ?, lwuPhwu =?, hwuDesc =?, hwuQty =  ?,hwuCp =?, hwuSp=?, lwuLimLev = ?, category = ?, location = ? WHERE prodID = ?";
			PreparedStatement stm = conn.prepareStatement(sql);
		
			
			stm.setInt(1, noRetUperLwu);
			stm.setString(2, lwu);
			stm.setInt(3, lwuQty);
			stm.setInt(4, lwuUcp);
			stm.setInt(5, lwuSp);
			stm.setInt(6, lwupHwu);
			stm.setString(7, hwu);
			stm.setInt(8, hwuQty);
			stm.setInt(9, hwuUcp);
			stm.setInt(10, hwuSp); 
			stm.setInt(11, limitLevel);
			
			stm.setString(12, cat);
			stm.setString(13, loc);
			
			
			stm.setInt(14, prodID);
		stm.executeUpdate();
			
		}catch(SQLException se){
			//Handle errors for JDBC
			flag = 0;
			JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
		}catch(Exception e){
			//Handle errors for Class.forName
			flag = 0;JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
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

		public String[] getProdInfo(int prodID) {
			String[] info = new String[16];
			
			
			try{
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(DB_URL, USER, PASS);

		String sql = "SELECT * FROM whouseinventory WHERE prodID = ?";
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, prodID);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
			
	       
			info[0]=rs.getString( "prodID");
			
			info[1]=rs.getString("noRetUperLwu");
			info[2]=rs.getString("lwuDesc");
			info[3]=rs.getString("lwuQty");
			info[4]=rs.getString("lwuCp");
			info[5]=rs.getString("lwuSp");
			info[6]=rs.getString("lwuPhwu");
			info[7]=rs.getString("hwuDesc");
			info[8]=rs.getString("hwuQty");
			info[9]=rs.getString("hwuCp");
			info[10]=rs.getString("hwuSp"); 
			info[11]=rs.getString("lwuLimLev");
			info[12]="";
			info[13]="";
			
			info[14]=rs.getString("category");
			info[15]=rs.getString("location");
		
			
				
				
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

		
		public String[][] getAllWHItems() {
			int noRows = 0; //getInvCount() ;
			String[][] allItems = null;//new String[noRows][16];
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT * FROM whouseinventory  ORDER BY lwuDesc, hwuDesc";
				
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while(rs.next())
					
					noRows = rs.getRow();
				
				allItems = new String[noRows][16];
					
					
				rs.beforeFirst();
				while(rs.next())
					for(int r=0; r < noRows; r++ )
						
				{
					
					
					
						allItems[r][0]=rs.getString( "prodID");
					
						allItems[r][1]=rs.getString("noRetUperLwu");
						allItems[r][2]=rs.getString("lwuDesc");
						allItems[r][3]=rs.getString("lwuQty");
						allItems[r][4]=rs.getString("lwuCp");
						allItems[r][5]=rs.getString("lwuSp");
						allItems[r][6]=rs.getString("lwuPhwu");
						allItems[r][7]=rs.getString("hwuDesc");
						allItems[r][8]=rs.getString("hwuQty");
						allItems[r][9]=rs.getString("hwuCp");
						allItems[r][10]=rs.getString("hwuSp"); 
						allItems[r][11]=rs.getString("lwuLimLev");
						allItems[r][12]="";
						allItems[r][13]="";
						allItems[r][14]=rs.getString("category");
						allItems[r][15]=rs.getString("location");
				
						
						
					
					
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

		public void registerKeys(int prodID, int purchID, Date expDate) {
			try{

Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "INSERT INTO prodpurchase (proID, purchID, expiryDate) VALUES(?,?,?)";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, prodID);
			st.setInt(2, purchID);
			st.setDate(3, expDate);
			st.executeUpdate();
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
		}

		public boolean isDoubleReg(int prodID, int purchID, Date expDate) {
		boolean flag = false;
		int proID = 0;int purID = 0;
			try{

				Class.forName("com.mysql.jdbc.Driver");
						 conn = DriverManager.getConnection(DB_URL, USER, PASS);
						
						String sql = "SELECT proID, purchID FROM prodpurchase WHERE proID = ? AND purchID = ? ";
							
							PreparedStatement st = conn.prepareStatement(sql);
							st.setInt(1, prodID);
							st.setInt(2, purchID);
							
							ResultSet rs = st.executeQuery();
							
							
							while(rs.next())
							{
								
					 proID =rs.getInt("proID");
				 purID = rs.getInt("purchID");
							}
							
							if( proID > 0 && purID>0)
								flag = true;
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
			return flag;

		

}

		public int updateQties(int prodID, int lwuQty, int hwuQty) {
			int flag = 1;
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

			String sql = "	UPDATE  whouseinventory SET  lwuQty = lwuQty + ?,  hwuQty = hwuQty +? WHERE prodID = ?";
			PreparedStatement stm = conn.prepareStatement(sql);
			
			
		
			stm.setInt(1, lwuQty);
			
		
		
	
			stm.setInt(2, hwuQty);
			stm.setInt(3, prodID);
		stm.executeUpdate();
			
		}catch(SQLException se){
			//Handle errors for JDBC
			flag = 8;
			JOptionPane.showMessageDialog(null, "Database Error!\n"+se);
		}catch(Exception e){
			//Handle errors for Class.forName
			flag = 8;JOptionPane.showMessageDialog(null, "Database Error!\n"+e);
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

		public String[][] searchProd(String text) {

			String[][] lookup = null;
			int size = 0;

			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection( DB_URL, USER, PASS);
				
				
				
			String 	sql = "SELECT prodID, hwuDesc FROM  whouseinventory WHERE hwuDesc LIKE ? OR lwuDesc LIKE ? ORDER BY hwuDesc";
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setString(1, text+"%");
				stm.setString(2, text+"%");
				 ResultSet rs2 = stm.executeQuery();
				 size= rs2.getRow();
					
					lookup = new String[size][2];
						
				rs2.beforeFirst();
					while(rs2.next())
						
					for(int r=0; r < size; r++ ){ 
					
				lookup[r][0]= rs2.getString("prodID");
				lookup[r][1]= rs2.getString("hwuDesc");
				
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

		
		public int totalForPurchaseID(int purchID) {
			int total = 0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT sum((hwuQty*hwuCp) + (lwuQty *lwuCp)) AS total  FROM whouseInventory WHERE prodID IN (SELECT proID FROM prodpurchase WHERE purchID =?)";
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, purchID);
				ResultSet rs = st.executeQuery();
				
				while(rs.next())
					
					total = rs.getInt("total");
				
			}
					catch(SQLException se){
						//Handle errors for JDBC
						
						JOptionPane.showMessageDialog(null, "Database Error!\nLocation:whouseInventoryAccess/totalForPurchaseID"+se);
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
			return total;
		}

		public int checkCostPrice(int prodID) {
			// TODO Auto-generated method stub
			int cp = 0;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				
				String sql = "SELECT lwuCp/noretuperlwu AS unitcostprice  FROM whouseInventory WHERE prodID = ?";
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, prodID);
				ResultSet rs = st.executeQuery();
				
				while(rs.next())
					
					cp = rs.getInt("unitcostprice");
				
			}
					catch(SQLException se){
						//Handle errors for JDBC
						
						JOptionPane.showMessageDialog(null, "Database Error!\nLocation:whouseInventoryAccess/checkCostPrice"+se);
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
			return cp;
		}

		public String[][] getWarehouseShortage() {
			// TODO Auto-generated method stub
			int noRows=0;
			String[][] allItems = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
				{;}
				
				
				String sql = "SELECT prodid,hwudesc,  hwuqty, lwuqty, location, lwulimlev FROM whouseinventory WHERE (lwuqty + (hwuqty*lwuphwu)) < lwulimlev ORDER BY hwudesc";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				
				ResultSet rs = st.executeQuery();
				
		while(rs.next())
					noRows= rs.getRow();
		 allItems = new String[noRows][6];
				rs.beforeFirst();
				while(rs.next())
					
					
					for(int r=0; r < noRows; r++ )
						
				{
					
					allItems[r][0]= rs.getString("prodid");
					allItems[r][1]= rs.getString("hwudesc");
					
					allItems[r][2]= rs.getString("lwuqty");
					allItems[r][3]= rs.getString("lwuqty");
					allItems[r][4]= rs.getString("limitlevel");
					allItems[r][5]= rs.getString("location");
					
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

		public void UpdateLog(int updateID, int purchID, int prodID, int tqIn,
				java.sql.Date purchDate) {
			// TODO Auto-generated method stub
try{
Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String sql = "INSERT INTO updatelog (updateID, purchID, prodidl,qtyAdded,  date, time) VALUES(?,?,?,?,?,NOW())";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, updateID);
			st.setInt(2, purchID);
			st.setInt(3, prodID);
			st.setInt(4, tqIn);
			st.setDate(5, purchDate);
			
			st.executeUpdate();
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
		
			
			}}	
		
		public String[][] getUpdateLog() {
			// TODO Auto-generated method stub
			int noRows=0;
			String[][] allItems = null;
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn= DriverManager.getConnection(DB_URL, USER, PASS);
				// for(Date d= frmDate; !(d.after(toDate));d.setHours(arg0))
				{;}
				
				
				String sql = "SELECT updateID, purchID, prodidl,qtyAdded,  date, time FROM updatelog";
				
				PreparedStatement st = conn.prepareStatement(sql);
				
				
				ResultSet rs = st.executeQuery();
				
		while(rs.next())
					noRows= rs.getRow();
		 allItems = new String[noRows][6];
				rs.beforeFirst();
				while(rs.next())
					
					
					for(int r=0; r < noRows; r++ )
						
				{
					
					allItems[r][0]= rs.getString("updateID");
					allItems[r][1]= rs.getString("purchID");
					
					allItems[r][2]= rs.getString("prodidl");
					allItems[r][3]= rs.getString("qtyAdded");
					allItems[r][4]= rs.getString("date");
					allItems[r][5]= rs.getString("time");
					
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