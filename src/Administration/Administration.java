

package Administration;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import security.SecurityAcc;

public class Administration {

	
	public JFrame AdminLinks;
	public static final int classID = 3;
	static int roleid2;
	static int empID2; static String userName2;
static int trackID2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Administration window = new Administration(trackID2, roleid2, empID2, userName2);
					window.AdminLinks.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param userName 
	 * @param empID 
	 * @param roleid 
	 */
	public Administration(int trackID,int roleid, int empID, String userName) {
		initialize(trackID,roleid, empID, userName);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int trackID,final int roleid, final int empID, final String userName) {
		AdminLinks = new JFrame();
		AdminLinks.setTitle("Administration");
		AdminLinks.setBounds(100, 100, 986, 616);
		AdminLinks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AdminLinks.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(481, 68, 195, 266);
		AdminLinks.getContentPane().add(panel);

		JButton registerRetail = new JButton("Register Retail Items");
		registerRetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegRetail win = new RegRetail(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		registerRetail.setBounds(13, 11, 169, 31);
		
		panel.setLayout(null);
		panel.add(registerRetail);
		registerRetail.setFont(new Font("Tahoma", Font.PLAIN, 13)); 
		registerRetail.setForeground(new Color(0, 0, 0));

		JButton btnUpdateRetail = new JButton("Update Retail");
		btnUpdateRetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateRetailInven win = new UpdateRetailInven(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdateRetail.setForeground(new Color(0, 0, 0));
		btnUpdateRetail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateRetail.setBounds(13, 64, 169, 31);
		panel.add(btnUpdateRetail);

		JButton btnViewItemsBelow = new JButton("Sales&G.Profit Analysis");
		btnViewItemsBelow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RetailSalesAnalysis win = new RetailSalesAnalysis(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewItemsBelow.setForeground(new Color(0, 0, 0));
		btnViewItemsBelow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewItemsBelow.setBounds(13, 171, 169, 31);
		panel.add(btnViewItemsBelow);

		JButton btnViewRetailCredit = new JButton("View Retail Items");
		btnViewRetailCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewRetailInven win = new ViewRetailInven(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewRetailCredit.setForeground(new Color(0, 0, 0));
		btnViewRetailCredit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewRetailCredit.setBounds(10, 120, 172, 31);
		panel.add(btnViewRetailCredit);

		JButton button_5 = new JButton("View Retail Shortage");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RetailShortage win = new RetailShortage(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_5.setBounds(13, 224, 169, 31);
		panel.add(button_5);

		JLabel lblRegistration = new JLabel("Retail Inventory");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegistration.setBackground(Color.WHITE);
		lblRegistration.setForeground(Color.BLUE);
		lblRegistration.setBounds(533, 35, 114, 25);
		AdminLinks.getContentPane().add(lblRegistration);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setLayout(null);
		panel_1.setBounds(727, 68, 195, 266);
		AdminLinks.getContentPane().add(panel_1);

		JButton btnUpdateWarehouseIt = new JButton("Update W-house Items");
		btnUpdateWarehouseIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UpdateWarehouseInven win = new UpdateWarehouseInven(trackID,roleid, empID, userName);
				win.setVisible(true);

			}
		});
		btnUpdateWarehouseIt.setForeground(new Color(0, 0, 0));
		btnUpdateWarehouseIt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateWarehouseIt.setBounds(10, 61, 175, 32);
		panel_1.add(btnUpdateWarehouseIt);

		JButton btnRegisterWhouseVs = new JButton("View  W-House Items");
		btnRegisterWhouseVs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewWHouseInventory win = new ViewWHouseInventory(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterWhouseVs.setForeground(new Color(0, 0, 0));
		btnRegisterWhouseVs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterWhouseVs.setBounds(10, 120, 175, 32);
		panel_1.add(btnRegisterWhouseVs);

		JButton regWHouse = new JButton("Register W-House items");
		regWHouse.setBounds(10, 12, 175, 32);
		panel_1.add(regWHouse);
		regWHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegWarehouseInven win = new RegWarehouseInven (trackID,roleid, empID, userName);
				win.setVisible(true);
				
			}
		});

		regWHouse.setForeground(new Color(0, 0, 0));
		regWHouse.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JButton button_7 = new JButton("View W-House Shortage");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WHouseShortage win = new WHouseShortage(trackID,roleid, empID, userName);
				win.setVisible(true);
				
			}
		});
		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_7.setBounds(10, 223, 175, 32);
		panel_1.add(button_7);
		
		JButton button_3 = new JButton("Sales&G.Profit Analysis");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				WholesaleSalesAnalysis win = new WholesaleSalesAnalysis(trackID,roleid, empID, userName);
				win.setVisible(true);
				
			}
		});
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_3.setBounds(10, 173, 175, 32);
		panel_1.add(button_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setLayout(null);
		panel_2.setBounds(35, 364, 195, 205);
		AdminLinks.getContentPane().add(panel_2);

		JButton btnRegisterSupplier = new JButton("Register Supplier");
		btnRegisterSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegSupplier win = new RegSupplier(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterSupplier.setForeground(new Color(0, 0, 0));
		btnRegisterSupplier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterSupplier.setBounds(13, 12, 169, 35);
		panel_2.add(btnRegisterSupplier);

		JButton btnUpdateSupplier = new JButton("Update Supplier");
		btnUpdateSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateSupplier win = new UpdateSupplier(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdateSupplier.setForeground(new Color(0, 0, 0));
		btnUpdateSupplier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateSupplier.setBounds(13, 71, 169, 35);
		panel_2.add(btnUpdateSupplier);

		JButton btnView = new JButton("View Supplier Info");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewSupplier win = new ViewSupplier(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnView.setForeground(new Color(0, 0, 0));
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnView.setBounds(13, 140, 169, 35);
		panel_2.add(btnView);

		JLabel lblWholesaleInventory = new JLabel("Wholesale Inventory");
		lblWholesaleInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWholesaleInventory.setBackground(Color.WHITE);
		lblWholesaleInventory.setForeground(Color.BLUE);
		lblWholesaleInventory.setBounds(749, 35, 144, 25);
		AdminLinks.getContentPane().add(lblWholesaleInventory);

		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupplier.setBackground(Color.WHITE);
		lblSupplier.setForeground(Color.BLUE);
		lblSupplier.setBounds(96, 340, 114, 25);
		AdminLinks.getContentPane().add(lblSupplier);

		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomer.setBackground(Color.WHITE);
		lblCustomer.setForeground(Color.BLUE);
		lblCustomer.setBounds(783, 340, 114, 25);
		AdminLinks.getContentPane().add(lblCustomer);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.CYAN);
		panel_4.setLayout(null);
		panel_4.setBounds(481, 364, 195, 205);
		AdminLinks.getContentPane().add(panel_4);

		JButton btnRegisterEmployee = new JButton("Register Employee");
		btnRegisterEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegEmployee win = new RegEmployee(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterEmployee.setForeground(new Color(0, 0, 0));
		btnRegisterEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterEmployee.setBounds(13, 12, 169, 33);
		panel_4.add(btnRegisterEmployee);

		JButton btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateEmployee win = new UpdateEmployee(trackID, roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdateEmployee.setForeground(new Color(0, 0, 0));
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateEmployee.setBounds(13, 69, 169, 33);
		panel_4.add(btnUpdateEmployee);

		JButton btnViewEmployeeInfo = new JButton("View Employee Info");
		btnViewEmployeeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewEmployee win = new ViewEmployee(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewEmployeeInfo.setForeground(new Color(0, 0, 0));
		btnViewEmployeeInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewEmployeeInfo.setBounds(13, 140, 169, 33);
		panel_4.add(btnViewEmployeeInfo);

		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmployee.setBackground(Color.WHITE);
		lblEmployee.setForeground(Color.BLUE);
		lblEmployee.setBounds(533, 340, 114, 25);
		AdminLinks.getContentPane().add(lblEmployee);

		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransaction.setBackground(Color.WHITE);
		lblTransaction.setForeground(Color.BLUE);
		lblTransaction.setBounds(77, 119, 114, 25);
		AdminLinks.getContentPane().add(lblTransaction);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.PINK);
		panel_3.setLayout(null);
		panel_3.setBounds(35, 155, 195, 176);
		AdminLinks.getContentPane().add(panel_3);

		JButton btnRegisterCustomer = new JButton("View A Transaction");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ViewTransaction win = new ViewTransaction(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterCustomer.setForeground(new Color(0, 0, 0));
		btnRegisterCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterCustomer.setBounds(13, 38, 169, 34);
		panel_3.add(btnRegisterCustomer);

		JButton btnUpdate = new JButton("View All Transactions");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ViewTransaction win = new ViewTransaction(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(13, 110, 169, 41);
		panel_3.add(btnUpdate);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(128, 0, 128));
		panel_5.setLayout(null);
		panel_5.setBounds(727, 364, 195, 205);
		AdminLinks.getContentPane().add(panel_5);

		JButton button = new JButton("Register Customer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						RegCustomer win = new RegCustomer(trackID,roleid, empID, userName);
						win.setVisible(true);
					
		
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBounds(13, 12, 169, 34);
		panel_5.add(button);

		JButton button_1 = new JButton("Update  Customer");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						UpdateCustomer win = new UpdateCustomer(trackID,roleid, empID, userName);
						win.setVisible(true);
					
		
			}
		});
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_1.setBounds(13, 73, 169, 34);
		panel_5.add(button_1);

		JButton button_2 = new JButton("View Customer Info");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						ViewCustomer win = new ViewCustomer(trackID,roleid, empID, userName);
						win.setVisible(true);
					
		
			}
		});
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_2.setBounds(13, 142, 169, 34);
		panel_5.add(button_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.MAGENTA);
		panel_6.setLayout(null);
		panel_6.setBounds(252, 364, 195, 205);
		AdminLinks.getContentPane().add(panel_6);

		JButton btnRegisterPurchase = new JButton("Register Purchase");
		btnRegisterPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterPurchase win = new RegisterPurchase(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterPurchase.setForeground(new Color(0, 0, 0));
		btnRegisterPurchase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterPurchase.setBounds(13, 12, 169, 35);
		panel_6.add(btnRegisterPurchase);

		JButton btnUpdatePurchase = new JButton("Update Purchase");
		btnUpdatePurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PurchaseUpdate win = new PurchaseUpdate(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdatePurchase.setForeground(new Color(0, 0, 0));
		btnUpdatePurchase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdatePurchase.setBounds(13, 70, 169, 35);
		panel_6.add(btnUpdatePurchase);

		JButton btnViewPurchaseInfo = new JButton("View Purchase Info");
		btnViewPurchaseInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewPurchase win = new ViewPurchase(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewPurchaseInfo.setForeground(new Color(0, 0, 0));
		btnViewPurchaseInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewPurchaseInfo.setBounds(13, 137, 169, 35);
		panel_6.add(btnViewPurchaseInfo);

		JLabel lblPurchase = new JLabel("Purchase");
		lblPurchase.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPurchase.setBackground(Color.WHITE);
		lblPurchase.setForeground(Color.BLUE);
		lblPurchase.setBounds(299, 340, 114, 25);
		AdminLinks.getContentPane().add(lblPurchase);

		JLabel lblAdministrationHome = new JLabel("Administration Home");
		lblAdministrationHome.setForeground(Color.BLUE);
		lblAdministrationHome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdministrationHome.setBackground(Color.WHITE);
		lblAdministrationHome.setBounds(23, 65, 175, 25);
		AdminLinks.getContentPane().add(lblAdministrationHome);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(252, 68, 195, 266);
		AdminLinks.getContentPane().add(panel_7);

		JButton btnRegisterExpenditure = new JButton("Register Expenditure");
		btnRegisterExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegExpenditure win = new RegExpenditure(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnRegisterExpenditure.setForeground(Color.BLACK);
		btnRegisterExpenditure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegisterExpenditure.setBounds(13, 11, 169, 34);
		panel_7.add(btnRegisterExpenditure);

		JButton btnUpdateExpenditure = new JButton("Update Expenditure");
		btnUpdateExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				UpdateExpenditure win = new UpdateExpenditure(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnUpdateExpenditure.setForeground(Color.BLACK);
		btnUpdateExpenditure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateExpenditure.setBounds(13, 64, 169, 34);
		panel_7.add(btnUpdateExpenditure);

		JButton btnViewExpenditure = new JButton("View Expenditure");
		btnViewExpenditure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				ViewExpenditure win = new ViewExpenditure(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewExpenditure.setForeground(Color.BLACK);
		btnViewExpenditure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewExpenditure.setBounds(13, 124, 172, 34);
		panel_7.add(btnViewExpenditure);
		
		JButton btnGlobalSaleprofitexpAnalysis = new JButton("Sales,Prof&Exp Analysis");
		btnGlobalSaleprofitexpAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GlobalSalesProfExpAnalysis win = new GlobalSalesProfExpAnalysis(trackID,roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnGlobalSaleprofitexpAnalysis.setHorizontalAlignment(SwingConstants.LEADING);
		btnGlobalSaleprofitexpAnalysis.setForeground(Color.BLACK);
		btnGlobalSaleprofitexpAnalysis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGlobalSaleprofitexpAnalysis.setBounds(13, 189, 172, 55);
		panel_7.add(btnGlobalSaleprofitexpAnalysis);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(62, 180, 4, 22);
		AdminLinks.getContentPane().add(textArea);

		JLabel lblExpenditureBalancing = new JLabel("Expenditure & Balancing");
		lblExpenditureBalancing.setForeground(Color.BLUE);
		lblExpenditureBalancing.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpenditureBalancing.setBackground(Color.WHITE);
		lblExpenditureBalancing.setBounds(262, 35, 184, 25);
		AdminLinks.getContentPane().add(lblExpenditureBalancing);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(10, 11, 89, 31);
		AdminLinks.getContentPane().add(btnNewButton);
		AdminLinks.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { AdminLinks.getContentPane() }));
	}
}
