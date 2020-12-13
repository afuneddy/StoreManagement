package Administration;

import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.RetailInventory;
import planettaStore.WHouseInventory;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class UpdateRetailInven extends JFrame {

	private JPanel contentPane;
	
	private int prodRetID;
	private JTextField prodRetIDT;
	private JTextField retDescT;
	private JTextField retPriceT;
	private JTextField qtyT;
	private JTextField locT;
	private JTextField limLevT;
	private JLabel lblRetailCode;
	private JLabel lblDescription;
	private JLabel lblUnitPrice;
	private JLabel lblQuantity;
	private JLabel lblLocation;
	private JLabel lblLimitLevel;

	private JTextField prodIDT;
	private JButton btnPopulateTable;
	private JButton btnPrintTable;
	JTable displaytableRetItems;
	String[][] retailItems;
	DefaultTableModel myModel;
	private JButton btnDisplayInfo;
	private JLabel lblUpdateRetailInventory;
	private JPanel panel;
	public static final int classID = 20;
	static int roleid;
	static int empID; static String userName;
static int trackID;
private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateRetailInven frame = new UpdateRetailInven(trackID, roleid, empID, userName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userName 
	 * @param empID 
	 * @param roleid 
	 * @param empID2 
	 */
	public UpdateRetailInven(final int trackID, int roleid, final int empID,  String userName) {
		setTitle("Retail Inventory Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1064, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		prodRetIDT = new JTextField();
		prodRetIDT.setForeground(Color.BLUE);
		prodRetIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		prodRetIDT.setToolTipText("Retail ID for Retail Inventory item");
		prodRetIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		prodRetIDT.setBounds(34, 132, 123, 30);
		contentPane.add(prodRetIDT);
		prodRetIDT.setColumns(10);
		/*
		 * retDesCom = new JComboBox(); retDesCom.setBounds(65, 166, 103, 20);
		 * retDesCom.addActionListener(new ActionListener() { /*public void
		 * actionPerformed(ActionEvent arg0) { RetailInventory ri = new
		 * RetailInventory();
		 * 
		 * String retDesc = retDesCom.getSelectedItem().toString();
		 * prodRetIDT.setText("" + ri.searchItem(retDesc));
		 * 
		 * } }); retDesCom.setEditable(true); retDesCom.setModel(new
		 * DefaultComboBoxModel(new String[] { "--Choose--", "Bread-300", "g"
		 * })); contentPane.add(retDesCom);
		 */
		retDescT = new JTextField();
		retDescT.setForeground(Color.BLUE);
		retDescT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retDescT.setToolTipText("Description as shall be printed on customer receipts");
		retDescT.setBounds(347, 132, 123, 30);
		contentPane.add(retDescT);
		retDescT.setColumns(10);
		

		retPriceT = new JTextField();
		retPriceT.setForeground(Color.BLUE);
		retPriceT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retPriceT.setBounds(205, 216, 123, 30);
		contentPane.add(retPriceT);
		retPriceT.setColumns(10);

		qtyT = new JTextField();
		qtyT.setForeground(Color.BLUE);
		qtyT.setFont(new Font("Tahoma", Font.BOLD, 12));
		qtyT.setToolTipText("Quantity to be added now");
		qtyT.setBounds(347, 216, 123, 30);
		contentPane.add(qtyT);
		qtyT.setColumns(10);

		locT = new JTextField();
		locT.setForeground(Color.BLUE);
		locT.setFont(new Font("Tahoma", Font.BOLD, 12));
		locT.setBounds(205, 308, 123, 30);
		locT.setColumns(10);
		contentPane.add(locT);

		limLevT = new JTextField();
		limLevT.setForeground(Color.BLUE);
		limLevT.setFont(new Font("Tahoma", Font.BOLD, 12));
		limLevT.setBounds(347, 308, 123, 30);
		limLevT.setColumns(10);
		contentPane.add(limLevT);

		lblRetailCode = new JLabel("Retail Code");
		lblRetailCode.setBounds(34, 107, 103, 14);
		lblRetailCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblRetailCode);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(347, 107, 103, 14);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDescription);

		lblUnitPrice = new JLabel("Unit Retail Price");
		lblUnitPrice.setBounds(205, 191, 103, 14);
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblUnitPrice);

		lblQuantity = new JLabel("Quantity Added");
		lblQuantity.setBounds(347, 191, 118, 14);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblQuantity);

		lblLocation = new JLabel("Location");
		lblLocation.setBounds(205, 283, 103, 14);
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblLocation);

		lblLimitLevel = new JLabel("Limit Level");
		lblLimitLevel.setBounds(347, 283, 103, 14);
		lblLimitLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblLimitLevel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(480, 78, 534, 383);
		contentPane.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Pro_ID", "Description",
				"Ret UPrice", "Qty", "Location", "Limit Level" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable displaytableRetItems = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					String.class, Integer.class, Integer.class, String.class,
					Integer.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		displaytableRetItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displaytableRetItems.getColumnModel().setColumnSelectionAllowed(true);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(85);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(180);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(90);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(60);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(120);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(50);

		displaytableRetItems.setRowHeight(23);
		contentPane.add(scrollPane);

		displaytableRetItems.setFillsViewportHeight(true);
		displaytableRetItems.setColumnSelectionAllowed(true);
		displaytableRetItems.setCellSelectionEnabled(true);

		displaytableRetItems
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displaytableRetItems.setSurrendersFocusOnKeystroke(true);

		scrollPane.setViewportView(displaytableRetItems);

		JButton btvalidate = new JButton("Validate");
		btvalidate.setToolTipText("Click to submit update.");
		btvalidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String prodIDs = prodIDT.getText().trim().replaceAll("\\s", "");
				String prodRetIDs = prodRetIDT.getText().trim()
						.replaceAll("\\s", "");
				String retDesc = retDescT.getText().trim();
				String retPriceSt = retPriceT.getText().trim()
						.replaceAll("\\s", "");
				String qtys = qtyT.getText().trim().replaceAll("\\s", "");
				String loc = locT.getText().trim().replaceAll("\\s", "");
				String limLevs = limLevT.getText().trim().replaceAll("\\s", "");

				IntegerInput ii = new IntegerInput();

				if (!(prodRetIDs.length() > 0)) {
					JOptionPane.showMessageDialog(null,
							"Enter product retail code.");
					prodRetIDT.requestFocus();
				} else if (!(ii.isInteger(prodRetIDs))) {
					JOptionPane
							.showMessageDialog(null,
									"Please provide whole number value for Product retail code.");
					prodRetIDT.setText(null);
					prodRetIDT.requestFocus();
				} else {
					int prodRetID = Integer.parseInt(prodRetIDs);

					if (!(prodIDs.length() > 0))

					{
						JOptionPane
								.showMessageDialog(
										null,
										"Please provide new warehouse identity of product.\nBetter Still, maintain the former.");
						prodIDT.requestFocus();
					} else {

						if (!(ii.isInteger(prodIDs))) {
							JOptionPane
									.showMessageDialog(null,
											"Please provide whole number value for Product ID.");
							prodIDT.setText(null);
							prodIDT.requestFocus();
						}

						else {
							int prodID = Integer.parseInt(prodIDs);
							WHouseInventory wsi = new WHouseInventory();
							if (prodID != wsi.checkProdId(prodID))// checks
																	// availability
																	// of
																	// product
																	// on
																	// database
							{
								JOptionPane
										.showMessageDialog(
												null,
												"No product with ID : "
														+ prodID
														+ " "

														+ "is found on Database."
														+ "\nPlease, check code and try again.\nBetter still, choose from the dropdown list on the right of ID field.");
								prodIDT.setText(null);
								prodIDT.requestFocus();
							}

							else if (!(retDesc.length() > 0))

							{
								JOptionPane
										.showMessageDialog(null,
												"Please specify new retail product description.");

								retDescT.requestFocus();
							} else if (!(qtys.length() > 0)) {
								JOptionPane
										.showMessageDialog(null,
												"Please specify quantity of items  to be added on inventory.");
								qtyT.requestFocus();
							} else if (!(ii.isInteger(qtys))) {
								JOptionPane
										.showMessageDialog(null,
												"Please provide whole number value for quantity.");
								qtyT.setText(null);
								qtyT.requestFocus();

							} else {
								int qty = Integer.parseInt(qtys);
								if (!(retPriceSt.length() > 0)) {
									JOptionPane
											.showMessageDialog(null,
													"Please specify new retail price of product.");
									retPriceT.requestFocus();
								} else if (!(ii.isInteger(retPriceSt))) {
									JOptionPane
											.showMessageDialog(null,
													"Please provide whole number value for new retail price.");
									retPriceT.setText(null);
									retPriceT.requestFocus();
								} else

								{
									int retPrice = Integer.parseInt(retPriceSt);
									/**
									 * @param rp
									 *            retail price of product to be
									 *            registered to retail
									 *            inventory.
									 */
									
									int decision = -1;
									if(retPrice <= wsi.checkCostPrice(prodID))
										 decision = JOptionPane.showConfirmDialog(null,  "Retail Price is less than or equal to Unit Cost Price\nDo you wish to maintaim this price?","Decide", JOptionPane.YES_NO_OPTION);
										
									//JOptionPane.YES_NO_OPTION.showMessageDialog(null, decision);
									if(decision == 1)
									{
										retPriceT.setText(null);
									retPriceT.requestFocus();
									}else
									if (!(loc.length() > 0)) {
										JOptionPane
												.showMessageDialog(null,
														"Please specify product retail price.");
										locT.requestFocus();
									} else {
										if (!(limLevs.length() > 0)) {

											JOptionPane
													.showMessageDialog(null,
															"Please specify retail product limit level.");
											limLevT.requestFocus();
										} else {
											if (!(ii.isInteger(limLevs))) {
												JOptionPane
														.showMessageDialog(
																null,
																"Please provide whole number value for product limit level.");
												limLevT.setText(null);
												limLevT.requestFocus();

											} else

											{

												int limLev = Integer
														.parseInt(limLevs);
												

												int dec =-1;
												RetailInventory ri = new RetailInventory();
												JOptionPane.showMessageDialog(null, ri.checkDoubleReg(prodID, prodRetID).length);
												if(ri.checkDoubleReg(prodID, prodRetID).length == 1)
													dec=JOptionPane.showConfirmDialog(null, "The following item is already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID, prodRetID)[0]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
												else
													if(ri.checkDoubleReg(prodID, prodRetID).length == 2)
														dec=JOptionPane.showConfirmDialog(null, "The following items  are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID, prodRetID)[0]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[1]+"\nDo you wish to register with same ID  any way?", "Decision", JOptionPane.YES_NO_OPTION);
													else
														if(ri.checkDoubleReg(prodID, prodRetID).length == 3)
															dec=JOptionPane.showConfirmDialog(null, "The following items  are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID, prodRetID)[0]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[1]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[2]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
														else
															if(ri.checkDoubleReg(prodID, prodRetID).length > 3)
																dec=JOptionPane.showConfirmDialog(null, "The following items and maybe others are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID, prodRetID)[0]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[1]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[2]+"\n"+ ri.checkDoubleReg(prodID, prodRetID)[3]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
															if(dec == 1){
																JOptionPane.showMessageDialog(null, "Then go back and retry","Advice", JOptionPane.OK_OPTION);
														initialiseFields();
															}else
													
												

												if (ri.updateRetInv(prodRetID,
														retDesc, prodID, qty,
														retPrice, loc, limLev)) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Product registration has succeeded");

													initialiseFields();
													prodRetIDT
															.setEditable(true);
												}

												else

													JOptionPane
															.showMessageDialog(
																	null,
																	"Product not fully registerred. See message.");
											}
										}
									}
								}
							}
						}
					}
				}

			}
		});
		btvalidate.setForeground(Color.BLUE);
		btvalidate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btvalidate.setBounds(205, 406, 89, 23);
		contentPane.add(btvalidate);

		JButton btcancel = new JButton("Cancel");
		btcancel.setToolTipText("Click to cancel update.");
		btcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btcancel.setForeground(Color.RED);
		btcancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btcancel.setBounds(347, 407, 89, 23);
		contentPane.add(btcancel);

		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProductId.setBounds(205, 104, 87, 23);
		contentPane.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.setForeground(Color.BLUE);
		prodIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		prodIDT.setToolTipText("Warehouse ID of item");
		prodIDT.setBounds(205, 132, 123, 30);
		contentPane.add(prodIDT);
		prodIDT.setColumns(10);

		final JToggleButton btTogleEditableFields = new JToggleButton(
				"SET Editable Fields");
		btTogleEditableFields
				.setToolTipText("Click to provide data to the disabled fields.");
		btTogleEditableFields.setSelected(false);
		infoFieldsEditable(false);

		btTogleEditableFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btTogleEditableFields.isSelected() == false) {

					infoFieldsEditable(false);

				}

				else {

					infoFieldsEditable(true);
				}

			}
		});
		btTogleEditableFields.setBounds(34, 254, 145, 26);
		contentPane.add(btTogleEditableFields);

		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable
				.setToolTipText("Click to display all retail items on the table.");
		btnPopulateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int rowCount = myModel.getRowCount();
				if (rowCount > 0) {
					flag = 1;
					JOptionPane
							.showMessageDialog(null,
									"Table already populated. \nIt will be updated now.");
					for (int r = rowCount - 1; r >= 0; r--)

						myModel.removeRow(r);

				}
				RetailInventory ri = new RetailInventory();

				retailItems = ri.getAllretItems();

				int noRetItems = retailItems.length;


				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							retailItems[r][0], retailItems[r][1],
							retailItems[r][2], retailItems[r][3],
							retailItems[r][4], retailItems[r][5] });

				}
				if (flag == 1)
					JOptionPane.showMessageDialog(null,
							"Table successfully updated.");
				else
					JOptionPane.showMessageDialog(null,
							"Table successfully populated.");
			}
		});
		btnPopulateTable.setBounds(464, 490, 123, 23);
		contentPane.add(btnPopulateTable);

		btnPrintTable = new JButton("Print Table");
		btnPrintTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rowCount = myModel.getRowCount();
				if (rowCount == 0)
					JOptionPane
							.showMessageDialog(null,
									"Table is empty. Please populate it and try again.");
				else

				{
					try {
						displaytableRetItems.print();
					} catch (PrinterException e1) { // TODO Auto-generated catch
													// block
						JOptionPane.showMessageDialog(null,
								"Printing Error occurred:\n " + e1);
					}
				}

			}
		});
		btnPrintTable.setBounds(902, 490, 112, 23);
		contentPane.add(btnPrintTable);

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click display info partain to code entered above.");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();

			}
		});
		btnDisplayInfo.setBounds(35, 168, 102, 23);
		contentPane.add(btnDisplayInfo);

		JButton updateTable = new JButton("Update Table");
		updateTable.setToolTipText("Click to update table");
		updateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int flag = 1;
				int rowCount = myModel.getRowCount();
				if (rowCount == 0) {
					flag = 0;
					JOptionPane
							.showMessageDialog(null,
									"Table not yet populated. \nIt will be populated now.");
				} else

					for (int r = rowCount - 1; r >= 0; r--)

						myModel.removeRow(r);

				RetailInventory ri = new RetailInventory();

				retailItems = ri.getAllretItems();

				int noRetItems = retailItems.length;



				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							retailItems[r][0], retailItems[r][1],
							retailItems[r][2], retailItems[r][3],
							retailItems[r][4], retailItems[r][5] });

				}
				tableUpdateAckn(flag);

			}
		});
		updateTable.setBounds(624, 490, 112, 23);
		contentPane.add(updateTable);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(35, 11, 415, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		lblUpdateRetailInventory = new JLabel("UPDATE RETAIL INVENTORY");
		lblUpdateRetailInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUpdateRetailInventory.setBounds(137, 23, 183, 14);
		panel.add(lblUpdateRetailInventory);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setLayout(null);
		panel_1.setBounds(491, 11, 365, 48);
		contentPane.add(panel_1);

		JLabel lblInventoryTable = new JLabel("RETAIL INVENTORY TABLE");
		lblInventoryTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInventoryTable.setBounds(105, 23, 202, 14);
		panel_1.add(lblInventoryTable);

		JButton btnHelp = new JButton("HELP ?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Help not yet available. Sorry.");
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setBounds(902, 11, 89, 23);
		contentPane.add(btnHelp);

		JButton closeTable = new JButton("Close Table");
		closeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rowCount = myModel.getRowCount();
				if (rowCount == 0)
					JOptionPane.showMessageDialog(null, "Table is Emty");
				else
					for (int r = rowCount - 1; r >= 0; r--)
						myModel.removeRow(r);

			}
		});
		closeTable.setBounds(773, 490, 103, 23);
		contentPane.add(closeTable);
		
		final JButton btnDelete = new JButton("Delete From System");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dec=-1;
				if(displayAction() == true)
				//	authenticate(userID)
				 dec =JOptionPane.showConfirmDialog(null, "Are you sure you wish delete item with Retail ID: " + prodRetID+ "Completely from Retail Inventory?\n Remember that this action cannot be undone and if \n this item is still remaining in Retail, the system would not recognise it.\n Do you really want to continue?", "Deletion Decision", JOptionPane.YES_NO_OPTION);
					if (dec== 0)
					{
						RetailInventory ri = new RetailInventory();
					if(ri.delete(prodRetID))
						JOptionPane.showMessageDialog(null, "Item successfully deleted from Retail Inventory");
					initialiseFields();
					}

					else{
							
				;
				
					}
				
				
			}
		});
		btnDelete.setBounds(34, 490, 166, 23);
		contentPane.add(btnDelete);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(902, 45, 89, 23);
		contentPane.add(button);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{prodRetIDT, prodIDT, retDescT, retPriceT, qtyT, locT, limLevT, btvalidate, btcancel}));

	}

	public void infoFieldsEditable(Boolean truthValue) {

		prodIDT.setEditable(truthValue);

		retDescT.setEditable(truthValue);
		retPriceT.setEditable(truthValue);

		locT.setEditable(truthValue);
		limLevT.setEditable(truthValue);
	}

	public void initialiseFields() {

		prodIDT.setText(null);
		prodRetIDT.setText(null);
		retDescT.setText(null);
		retPriceT.setText(null);
		qtyT.setText(null);
		locT.setText(null);
		limLevT.setText(null);
		prodRetIDT.setEditable(true);

	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	}
/*
	protected boolean updateRetInventory(int prodRetID2, int qty) {
		// TODO Auto-generated method stub
		return false;
	}
*/
	public boolean displayAction() {
		// row = 0;
boolean display = false;
		String txtid = prodRetIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Product Code");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Product code.");
				prodRetIDT.setText(null);
			} else {
				prodRetID = Integer.parseInt(txtid);
				RetailInventory ri = new RetailInventory();
				if (prodRetID != ri.checkProdRID(prodRetID)) {
					JOptionPane.showMessageDialog(null,
							"No product with code: " + prodRetID + " "
									+ "is found on Database."
									+ "\nPlease, check code and try again.");
					prodRetIDT.setText("");
					prodRetIDT.requestFocus();
				} else {
					String info[] = ri.getProdRetInfo(prodRetID);
					int prodID = ri.getProdID(prodRetID);

					prodIDT.setText("" + prodID);
					prodRetIDT.setText("" + info[0]);
					retDescT.setText(info[1]);
					retPriceT.setText(info[2]);
					qtyT.setText(null);
					locT.setText(info[4]);
					limLevT.setText(info[5]);

					qtyT.requestFocus();
					prodRetIDT.setEditable(false);
					infoFieldsEditable(false);
					
					display = true;

					// JOptionPane.showMessageDialog(null, info[1]);

				}
			}
		}
		return display;

	}
}
