package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.text.NumberFormat;

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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Purchase;
import planettaStore.WHouseInventory;
import security.SecurityAcc;

//import java.util.Date;

@SuppressWarnings("serial")
public class UpdateWarehouseInven extends JFrame {

	private JPanel contentPane;
	private JTextField prodIDT;
	private JTextField purchIDT;
	private JTextField locT;
	private JTextField limLevelT;
	private JTextField lwuQtyT;
	private JTextField lwuUcpT;
	private JTextField hwuQtyT;
	private JTextField hwuUcpT;
	private JTextField lwuT;
	private JTextField hwuT;
	private JTextField lwupHwuT;
	private JTextField noRetUperLwuT;
	private JTextField tQtyLwuT;
	private JTextField lwuSpT;
	private JTextField hwuSpT;
	private JTextField catT;
	private JTextField purchDateT;
	private JTextField expDateT;
	private JTextField tcostT;

	private String[][] whouseItems;
	private JTextField currT;
public static String currency = "XAF";
private JTextField userT;
private JTextField secLevT;
private JTextField lastloginT;
public static final int classID = 6;
static int roleid;
static int empID; static String userName;
static int trackID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateWarehouseInven frame = new UpdateWarehouseInven(trackID,roleid, empID,  userName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the frame.
	 * @param empID 
	 * @param roleid 
	 * @param empID2 
	 * @param currency2 
	 */
	public UpdateWarehouseInven(final int trackID,final int roleid, final int empID,  String currency2) {
		setTitle("Warehouse Inventory Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1177, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 41, 1064, 615);
		contentPane.add(panel);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProductId.setBounds(21, 10, 118, 17);
		panel.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();

			}
		});
		prodIDT.setForeground(Color.BLUE);
		prodIDT.setFont(new Font("Tahoma", Font.PLAIN, 16));

		prodIDT.setColumns(10);
		prodIDT.setBounds(21, 32, 118, 27);
		panel.add(prodIDT);

		JLabel lblSupplierId = new JLabel("Purchase ID:");
		lblSupplierId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSupplierId.setBounds(21, 130, 73, 14);
		panel.add(lblSupplierId);

		purchIDT = new JTextField();
		purchIDT.setColumns(10);
		purchIDT.setBounds(114, 125, 86, 27);
		panel.add(purchIDT);

		JLabel lblLocationAtWarehouse = new JLabel("Location at Warehouse:");
		lblLocationAtWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocationAtWarehouse.setBounds(682, 168, 143, 14);
		panel.add(lblLocationAtWarehouse);

		locT = new JTextField();
		locT.setColumns(10);
		locT.setBounds(823, 161, 231, 31);
		panel.add(locT);

		JLabel lblSetLimitLevel = new JLabel("Set Limit level in LWU:");
		lblSetLimitLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetLimitLevel.setBounds(765, 130, 135, 14);
		panel.add(lblSetLimitLevel);

		limLevelT = new JTextField();
		limLevelT.setColumns(10);
		limLevelT.setBounds(910, 125, 144, 27);
		panel.add(limLevelT);

		JLabel lblUseLowestWholesale = new JLabel("LWU:");
		lblUseLowestWholesale.setToolTipText("Lowest wholesale unit");
		lblUseLowestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseLowestWholesale.setBounds(240, 32, 73, 28);
		panel.add(lblUseLowestWholesale);

		JLabel lblQuantity = new JLabel("LWU Qty:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(430, 39, 73, 14);
		panel.add(lblQuantity);

		lwuQtyT = new JTextField();
		lwuQtyT.setBounds(492, 35, 53, 24);
		panel.add(lwuQtyT);
		lwuQtyT.setColumns(10);

		JLabel lblUcp = new JLabel("LWU ucp:");
		lblUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUcp.setBounds(555, 39, 59, 14);
		panel.add(lblUcp);

		lwuUcpT = new JTextField();
		lwuUcpT.setBounds(613, 34, 66, 27);
		panel.add(lwuUcpT);
		lwuUcpT.setColumns(10);

		JLabel lblUseHighestWholesale = new JLabel("HWU:");
		lblUseHighestWholesale.setToolTipText("Highest wholesale unit");
		lblUseHighestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseHighestWholesale.setBounds(240, 76, 53, 28);
		panel.add(lblUseHighestWholesale);

		JLabel lblHwuQty = new JLabel("HWU Qty:");
		lblHwuQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuQty.setBounds(430, 83, 77, 14);
		panel.add(lblHwuQty);

		hwuQtyT = new JTextField();
		hwuQtyT.setColumns(10);
		hwuQtyT.setBounds(492, 78, 53, 27);
		panel.add(hwuQtyT);

		JLabel lblUwuUcp = new JLabel("UWU ucp:");
		lblUwuUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUwuUcp.setBounds(555, 83, 59, 14);
		panel.add(lblUwuUcp);

		hwuUcpT = new JTextField();
		hwuUcpT.setColumns(10);
		hwuUcpT.setBounds(613, 77, 66, 28);
		panel.add(hwuUcpT);

		lwuT = new JTextField();
		lwuT.setColumns(10);
		lwuT.setBounds(273, 36, 147, 23);
		panel.add(lwuT);

		hwuT = new JTextField();
		hwuT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hwuT.setColumns(10);
		hwuT.setBounds(273, 79, 143, 23);
		panel.add(hwuT);

		JLabel lblTotalCostOf = new JLabel("Total Cost:");
		lblTotalCostOf.setToolTipText("Total cost of this item");
		lblTotalCostOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCostOf.setBounds(546, 123, 73, 14);
		panel.add(lblTotalCostOf);

		JLabel lblTotalQuantityIn = new JLabel("Total Qty in LWU units:");
		lblTotalQuantityIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQuantityIn.setBounds(240, 130, 138, 14);
		panel.add(lblTotalQuantityIn);

		JLabel lblNoOfLwu = new JLabel("No of LWU per HWU:");
		lblNoOfLwu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOfLwu.setBounds(250, 167, 147, 17);
		panel.add(lblNoOfLwu);

		lwupHwuT = new JTextField();
		lwupHwuT.setBounds(374, 163, 59, 27);
		panel.add(lwupHwuT);
		lwupHwuT.setColumns(10);

		JLabel lblNoOfRetail = new JLabel("Retail Units per LWU:");
		lblNoOfRetail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOfRetail.setBounds(898, 9, 123, 14);
		panel.add(lblNoOfRetail);

		noRetUperLwuT = new JTextField();
		noRetUperLwuT.setBounds(908, 32, 118, 27);
		panel.add(noRetUperLwuT);
		noRetUperLwuT.setColumns(10);

		tQtyLwuT = new JTextField();
		tQtyLwuT.setBounds(374, 125, 86, 27);
		panel.add(tQtyLwuT);
		tQtyLwuT.setColumns(10);

		JLabel lblSp = new JLabel("LWU S.P:");
		lblSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSp.setBounds(689, 37, 66, 14);
		panel.add(lblSp);

		lwuSpT = new JTextField();
		lwuSpT.setColumns(10);
		lwuSpT.setBounds(752, 32, 66, 27);
		panel.add(lwuSpT);

		JLabel lblHwuSp = new JLabel("HWU S.P:");
		lblHwuSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuSp.setBounds(689, 81, 66, 14);
		panel.add(lblHwuSp);

		hwuSpT = new JTextField();
		hwuSpT.setColumns(10);
		hwuSpT.setBounds(752, 76, 66, 27);
		panel.add(hwuSpT);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(848, 83, 73, 14);
		panel.add(lblCategory);

		catT = new JTextField();
		catT.setBounds(910, 82, 144, 27);
		panel.add(catT);
		catT.setColumns(10);

	
		
		JLabel lblPurchaseDate = new JLabel("Purchase  Date:");
		lblPurchaseDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPurchaseDate.setBounds(21, 92, 91, 14);
		panel.add(lblPurchaseDate);

		JLabel lblYyyymmdd = new JLabel("yyyy-mm-dd");
		lblYyyymmdd.setBounds(21, 105, 86, 14);
		panel.add(lblYyyymmdd);

		purchDateT = new JTextField();
		purchDateT.setToolTipText("yyyy-mm-dd");
		purchDateT.setBounds(114, 87, 107, 27);
		panel.add(purchDateT);
		purchDateT.setColumns(10);

		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExpiryDate.setBounds(459, 168, 84, 14);
		panel.add(lblExpiryDate);

		JLabel lblYyyymmdd_1 = new JLabel("yyyy-mm-dd");
		lblYyyymmdd_1.setBounds(459, 178, 86, 14);
		panel.add(lblYyyymmdd_1);

		expDateT = new JTextField();
		expDateT.setToolTipText("yyyy-mm-dd");
		expDateT.setBounds(535, 163, 144, 27);
		panel.add(expDateT);
		expDateT.setColumns(10);

		tcostT = new JTextField();
		tcostT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tcostT.setBounds(609, 117, 118, 27);
		panel.add(tcostT);
		tcostT.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 220, 1044, 333);
		panel.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Pro_ID", "Ret/lwu",
				"lwu Description", "lwu Qty", "lwu CP", "lwu SP", "lwu/hwu",
				"hwu Description", "hwu Qty", "hwu CP", "hwu SP", "Limit(lwu)",
				"Tot Cost", "TQty(lwu)", "Category", "location" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable table = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					Integer.class, String.class, Integer.class, Integer.class,
					Integer.class, Integer.class, String.class, Integer.class,
					Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, String.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.getColumnModel().setColumnSelectionAllowed(true);
		table.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		table.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(85);
		table.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(35);
		table.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(150);
		table.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(60);
		table.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(60);
		table.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(60);
		table.getTableHeader().getColumnModel().getColumn(8)
				.setPreferredWidth(150);
		table.getTableHeader().getColumnModel().getColumn(16)
				.setPreferredWidth(120);
		table.getTableHeader().setToolTipText("Warehouse Inventory table");

		table.setRowHeight(23);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(23);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);

		JButton button = new JButton("Populate Table");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
				WHouseInventory whi = new WHouseInventory();

			//	int noRetItems = whi.getInvCount();

				whouseItems = whi.getAllWHItems();
NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(true);
				
				
			int	noItems = whouseItems.length;
				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							whouseItems[r][0], whouseItems[r][1],
							whouseItems[r][2], whouseItems[r][3],
							format.format(Float.parseFloat(whouseItems[r][4])), format.format(Float.parseFloat(whouseItems[r][5])),
							whouseItems[r][6], whouseItems[r][7],
							whouseItems[r][8], format.format(Float.parseFloat(whouseItems[r][9])),
							format.format(Float.parseFloat(whouseItems[r][10])), whouseItems[r][11],
							format.format(Float.parseFloat(whouseItems[r][12])), whouseItems[r][13],
							whouseItems[r][14], whouseItems[r][15], });


				}
				UpdateRetailInven ur = new UpdateRetailInven(trackID,roleid, empID, userName);
				ur.tableUpdateAckn(flag);
			}
		});
		button.setBounds(107, 569, 123, 23);
		panel.add(button);

		JButton button_1 = new JButton("Print Table");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = myModel.getRowCount();
				if (rowCount == 0)
					JOptionPane
							.showMessageDialog(null,
									"Table is empty. Please populate it and try again.");
				else

					try {
						table.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Printing error occurred. Sorry.");
					}
			}
		});
		button_1.setBounds(600, 569, 112, 23);
		panel.add(button_1);

		JButton button_2 = new JButton("Update Table");
		button_2.addActionListener(new ActionListener() {
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

				WHouseInventory whi = new WHouseInventory();

				//int noRetItems = whi.getInvCount();

				whouseItems = whi.getAllWHItems();
				

NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(true);
				
				
				int noItems = whouseItems.length;
				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							whouseItems[r][0], whouseItems[r][1],
							whouseItems[r][2], whouseItems[r][3],
							format.format(Float.parseFloat(whouseItems[r][4])), format.format(Float.parseFloat(whouseItems[r][5])),
							whouseItems[r][6], whouseItems[r][7],
							whouseItems[r][8], format.format(Float.parseFloat(whouseItems[r][9])),
							format.format(Float.parseFloat(whouseItems[r][10])), whouseItems[r][11],
							format.format(Float.parseFloat(whouseItems[r][12])), whouseItems[r][13],
							whouseItems[r][14], whouseItems[r][15], });

				}
				UpdateRetailInven ur = new UpdateRetailInven(trackID,roleid, empID, userName);
				ur.tableUpdateAckn(flag);

			}
		});
		button_2.setBounds(375, 569, 112, 23);
		panel.add(button_2);

		JButton btnNewButton_1 = new JButton("Display Info");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();

			}
		});
		btnNewButton_1.setBounds(21, 61, 118, 23);
		panel.add(btnNewButton_1);

		JLabel lblWarehouseInventoryTable = new JLabel(
				"WAREHOUSE INVENTORY TABLE");
		lblWarehouseInventoryTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWarehouseInventoryTable.setForeground(Color.BLUE);
		lblWarehouseInventoryTable.setBounds(21, 199, 266, 14);
		panel.add(lblWarehouseInventoryTable);
		
		JButton btnCloseTable = new JButton("Close Table");
		btnCloseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				closeTable(myModel);

				
				
			}
		});
		btnCloseTable.setBounds(823, 569, 112, 23);
		panel.add(btnCloseTable);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { prodIDT, purchDateT, purchIDT, lwuQtyT,
						tQtyLwuT, hwuQtyT, tcostT, expDateT, locT, limLevelT,
						lwuUcpT, hwuUcpT, lwuT, hwuT, lwupHwuT, noRetUperLwuT,
						lwuSpT, hwuSpT, catT, lblYyyymmdd_1, scrollPane, table,
						button, button_1, button_2, btnNewButton_1 }));

		JButton wsCanBtn = new JButton("Cancel All");
		wsCanBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		wsCanBtn.setForeground(Color.RED);
		wsCanBtn.setBounds(200, 11, 89, 23);
		contentPane.add(wsCanBtn);

		final JToggleButton tglbtnSetFieldsEditable = new JToggleButton(
				"Set Fields Editable");
		tglbtnSetFieldsEditable.setBounds(25, 11, 147, 23);
		contentPane.add(tglbtnSetFieldsEditable);

		JButton wsRegSubBtn = new JButton("Submit");
		wsRegSubBtn.setForeground(Color.BLUE);
		wsRegSubBtn.setBounds(333, 10, 107, 23);
		contentPane.add(wsRegSubBtn);
		wsRegSubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				IntegerInput ii = new IntegerInput();
				IsDate id = new IsDate();

				String prID = prodIDT.getText().trim().replaceAll("\\s", "");

				String loc = locT.getText().trim();
				String limLevStr = limLevelT.getText().trim()
						.replaceAll("\\s", "");
				;

				String lwuQ = lwuQtyT.getText().trim().replaceAll("\\s", "");
				;
				String lwuUc = lwuUcpT.getText().trim().replaceAll("\\s", "");
				;
				String hwuQ = hwuQtyT.getText().trim().replaceAll("\\s", "");
				;
				String hwuUc = hwuUcpT.getText().trim().replaceAll("\\s", "");
				;

				String lwu = lwuT.getText().trim();
				String hwu = hwuT.getText().trim();
				String lwupHwuStr = lwupHwuT.getText().trim()
						.replaceAll("\\s", "");
				;
				String noRetUperLwuStr = noRetUperLwuT.getText().trim()
						.replaceAll("\\s", "");
				;
				String tQtyLwuStr = tQtyLwuT.getText().trim()
						.replaceAll("\\s", "");
				;
				String lwuSpStr = lwuSpT.getText().trim().replaceAll("\\s", "");
				;
				String hwuSpStr = hwuSpT.getText().trim().replaceAll("\\s", "");
				;

				String cat = catT.getText().trim();
				String purchIDs = purchIDT.getText().trim()
						.replaceAll("\\s", "");
				;
				String purchDateStr = purchDateT.getText().trim()
						.replaceAll("\\s", "");
				;
				String expDateStr = expDateT.getText().trim()
						.replaceAll("\\s", "");
				;
				String tcostStr = tcostT.getText().trim().replaceAll("\\s", "");
				;
				//String retPriceS = retPriceT.getText().trim().replaceAll("\\s", "");
				if (!(prID.length() > 0)) {
					JOptionPane.showMessageDialog(null,
							"Enter product warehouse ID.");
					prodIDT.requestFocus();
				} else {

					if (!(ii.isInteger(prID))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for product ID.");
						prodIDT.setText(null);
						prodIDT.requestFocus();
					}

					else {
						int prodID = Integer.parseInt(prID);
						if (!(purchDateStr.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Please specify purchase date");
							purchDateT.requestFocus();
						} else {

							if (!(id.isDate(purchDateStr))) {
								JOptionPane
										.showMessageDialog(null,
												"Please use correct date format: yyyy-mm-dd\nFor purchase Date");
								purchDateT.requestFocus();
								purchDateT.setText(null);
							} else {
								Date purchDate = Date.valueOf(purchDateStr);

								if (!(purchIDs.length() > 0)) {
									JOptionPane
											.showMessageDialog(null,
													"Please provide purchase ID of this product.");
									purchIDT.requestFocus();
								} else {
									if (!(ii.isInteger(purchIDs))) {
										JOptionPane
												.showMessageDialog(null,
														"Please provide whole number value for purchase ID.");
										purchIDT.setText(null);
										purchIDT.requestFocus();
									} else {

										int purchID = Integer
												.parseInt(purchIDs);
										Purchase p = new Purchase();
										if (purchID != p.checkPurchID(purchID)
												|| !purchDate.equals(p
														.getPurchDate(purchID)))

										{
											JOptionPane
													.showMessageDialog(
															null,
															"Purchase ID and date do not match. "

																	+ " "
																	+ "Do any of the following:\n"
																	+ "\n1. Check code and try again.\n2. Check purchase Date and try again. \n3. Make sure you have registered the purchase.");

											if (purchDate != p
													.getPurchDate(purchID))
												JOptionPane
														.showMessageDialog(
																null,
																"Hints:Check \"purchase date\" .");
											if (purchID != p
													.checkPurchID(purchID))
												JOptionPane
														.showMessageDialog(
																null,
																"Hints:Rather Check \"purchase ID\".");
										} else if (!(lwu.length() > 0)) {
											JOptionPane
													.showMessageDialog(null,
															"Please provide Lowest wholesale unit description of product.");
											lwuT.requestFocus();
										} else

										if (!(lwuQ.length() > 0)) {
											JOptionPane
													.showMessageDialog(null,
															"Please provide LWU quantity.");
											lwuQtyT.requestFocus();
										} else if (!(ii.isInteger(lwuQ))) {
											{
												JOptionPane
														.showMessageDialog(
																null,
																"Please provide whole number value for LWU quantity.");
												lwuQtyT.requestFocus();
												lwuQtyT.setText(null);
											}
										} else {
											int lwuQty = Integer.parseInt(lwuQ);
											if (!(lwuUc.length() > 0)) {
												JOptionPane
														.showMessageDialog(
																null,
																"Please provide LWU unit cost price.");
												lwuUcpT.requestFocus();
											}

											else if (!(ii.isInteger(lwuUc))) {
												JOptionPane
														.showMessageDialog(
																null,
																"Please provide monetary value for LWU unit cost price.");
												lwuUcpT.requestFocus();
												lwuUcpT.setText(null);
											}

											else {
												int lwuUcp = Integer
														.parseInt(lwuUc);
												if (!(lwuSpStr.length() > 0)) {
													JOptionPane
															.showMessageDialog(
																	null,
																	"Please provide LWU unit wholesale price.");
													lwuSpT.requestFocus();
												} else

												if (!(ii.isInteger(lwuSpStr))) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Please provide monetary value for LWU wholesale price.");

													lwuSpT.setText(null);

												} else {
													int lwuSp = Integer
															.parseInt(lwuSpStr);

													if (!(hwu.length() > 0)) {
														JOptionPane
																.showMessageDialog(
																		null,
																		"Please provide Highest wholesale unit description of this product.");
														hwuT.setText(null);
														hwuT.requestFocus();
													} else

													if (!(hwuQ.length() > 0)) {
														JOptionPane
																.showMessageDialog(
																		null,
																		"Please provide HWU quantity.");
														hwuQtyT.requestFocus();
													} else if (!(ii
															.isInteger(hwuQ))) {

														JOptionPane
																.showMessageDialog(
																		null,
																		"Please provide whole number value for HWU quantity.");

														hwuQtyT.setText(null);
														hwuQtyT.requestFocus();

													} else {
														int hwuQty = Integer
																.parseInt(hwuQ);
														if (!(hwuUc.length() > 0)) {
															JOptionPane
																	.showMessageDialog(
																			null,
																			"Please provide HWU unit cost price.");
															hwuUcpT.requestFocus();
														} else if (!(ii
																.isInteger(hwuUc))) {
															JOptionPane
																	.showMessageDialog(
																			null,
																			"Please provide monetary value for HWU unit cost price.");

															hwuUcpT.setText(null);
															hwuUcpT.requestFocus();

														} else {
															int hwuUcp = Integer
																	.parseInt(hwuUc);
															if (!(hwuSpStr
																	.length() > 0)) {
																JOptionPane
																		.showMessageDialog(
																				null,
																				"Please provide HWU unit wholesale price.");
																hwuSpT.requestFocus();
															} else

															if (!(ii.isInteger(hwuSpStr))) {

																JOptionPane
																		.showMessageDialog(
																				null,
																				"Please provide monetary value for HWU wholesale price.");

																hwuSpT.setText(null);
																hwuSpT.requestFocus();

															}

															else {
																int hwuSp = Integer
																		.parseInt(hwuSpStr);
																if (!(lwupHwuStr
																		.length() > 0))

																{
																	JOptionPane
																			.showMessageDialog(
																					null,
																					"Please provide number of LWU in a HWU.");
																	lwupHwuT.requestFocus();
																} else

																if (!(ii.isInteger(lwupHwuStr))) {
																	JOptionPane
																			.showMessageDialog(
																					null,
																					"Please provide whole number value for No of LWU per HWU.");

																	lwupHwuT.setText(null);
																	lwupHwuT.requestFocus();

																} else {
																	int lwupHwu = Integer
																			.parseInt(lwupHwuStr);
																	if (!(cat
																			.length() > 0)) {
																		JOptionPane
																				.showMessageDialog(
																						null,
																						"Please specify product category.");
																		catT.requestFocus();
																	}

																	else if (!(noRetUperLwuStr
																			.length() > 0)) {
																		JOptionPane
																				.showMessageDialog(
																						null,
																						"Please specify Number of retail units per LWU.");
																		noRetUperLwuT
																				.requestFocus();
																	} else if (!(ii
																			.isInteger(noRetUperLwuStr))) {

																		JOptionPane
																				.showMessageDialog(
																						null,
																						"Please provide whole number value for No of retail units per HWU.");

																		noRetUperLwuT
																				.setText(null);
																		noRetUperLwuT
																				.requestFocus();

																	} else {
																		int noRetUperLwu = Integer
																				.parseInt(noRetUperLwuStr);
																		if (!(tQtyLwuStr
																				.length() > 0)) {
																			JOptionPane
																					.showMessageDialog(
																							null,
																							"Please specify Total quantity in LWU units.");
																			tQtyLwuT.requestFocus();
																		} else if (!(ii
																				.isInteger(tQtyLwuStr))) {

																			JOptionPane
																					.showMessageDialog(
																							null,
																							"Please provide whole number value for total quantity.");

																			tQtyLwuT.setText(null);
																			tQtyLwuT.requestFocus();

																		} else {
																			int tQtyLwu = Integer
																					.parseInt(tQtyLwuStr);
																			if (!(tcostStr
																					.length() > 0)) {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please specify Total cost of product.");
																				tcostT.requestFocus();
																			} else

																			if (!(ii.isInteger(tcostStr))) {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please provide monetary value for total cost of this product.");

																				tcostT.setText(null);
																				tcostT.requestFocus();
																			} else {
																				int tCost = Integer
																						.parseInt(tcostStr);
																				if (!(expDateStr

																						.length() > 0)) {
																					JOptionPane
																							.showMessageDialog(
																									null,
																									"Please specify expiration date");
																					expDateT.requestFocus();
																				} else {
																					if (!(id.isDate(expDateStr))) {

																						JOptionPane
																								.showMessageDialog(
																										null,
																										"Please use correct date format: yyyy-mm-dd \nFor Expiration date");

																						expDateT.setText(null);
																						expDateT.requestFocus();
																					} else {
																						Date expDate = Date
																								.valueOf(expDateStr);

																						if (!(limLevStr
																								.length() > 0)) {
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Please set limit level in LWU units");
																							limLevelT
																									.requestFocus();
																						} else if (!(ii
																								.isInteger(limLevStr))) {

																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Please provide whole number value limit level.");

																							limLevelT
																									.setText(null);
																							limLevelT
																									.requestFocus();

																						} else {
																							int limitLevel = Integer
																									.parseInt(limLevStr);
																							
																							
																				
																							if (!(loc
																									.length() > 0)) {
																								JOptionPane
																										.showMessageDialog(
																												null,
																												"Please specify location of this product in the warehouse.");
																								locT.requestFocus();
																							} else {if(tQtyLwu == 0)
																							{
																								JOptionPane
																								.showMessageDialog(
																										null,
																										"Update will have no effect on inventory, zero quantity added");
																								
																							}
																							else {
																								WHouseInventory whi = new WHouseInventory();

																								int result = whi
																										.updateWHInventory(
																												prodID,
																												purchID,
																												noRetUperLwu,
																												lwu,
																												lwuQty,
																												lwuUcp,
																												lwuSp,
																												lwupHwu,
																												hwu,
																												hwuQty,
																												hwuUcp,
																												hwuSp,
																												limitLevel,
																												tCost,
																												tQtyLwu,
																												cat,
																												
																												loc,
																												purchDate,
																												expDate);

																								if (result == 1)

																								{
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Successful.");
																									initialiseFields();
																									prodIDT.setEditable(true);
																								} else if (result == 0) {

																									JOptionPane
																											.showMessageDialog(
																													null,
																													"An error occurred.");
																								} else if (result == 2) {
																									lwuUcpT.setText(null);
																									lwuSpT.setText(null);
																									lwuUcpT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else

																								if (result == 3) {
																									hwuUcpT.setText(null);
																									hwuSpT.setText(null);
																									hwuUcpT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 4) {
																									lwupHwuT.setText(null);
																									lwupHwuT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 5) {
																									hwuUcpT.setText(null);
																									lwuUcpT.setText(null);

																									lwuUcpT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 6) {

																									hwuQtyT.setText(null);
																									lwuQtyT.setText(null);
																									tQtyLwuT.setText(null);
																									lwuQtyT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 7) {
																									hwuQtyT.setText(null);
																									lwuQtyT.setText(null);
																									hwuUcpT.setText(null);
																									lwuUcpT.setText(null);
																									tcostT.setText(null);
																									lwuQtyT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 8) {

																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								} else if (result == 9) {
																									tcostT.setText(null);
																									tQtyLwuT.setText(null);
																									tQtyLwuT.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");

																								} else if (result == 10) {
																									noRetUperLwuT
																											.setText(null);
																									noRetUperLwuT
																											.requestFocus();
																									JOptionPane
																											.showMessageDialog(
																													null,
																													"Registration Unsuccessful.\nPlease Try Again.");
																								}
																									/*else if (result == 11) {
																										noRetUperLwuT
																												.setText(null);
																										noRetUperLwuT
																												.requestFocus();
																										retPriceT
																										.setText(null);
																										JOptionPane
																										.showMessageDialog(
																												null,
																												"Retail Price has not worked out with Number of retail units per LWU and others.\nReview Input and try again.");

																										JOptionPane
																												.showMessageDialog(
																														null,
																														"Registration Unsuccessful.\nPlease Try Again.");
}*/
																								
																							}
																						}
																					}
																				}
																			}
																		}
																	}}
																}

															}
														}
													}

												}
											}
										}
									}
								}
							}
						}
					}
				}
				prodIDT.requestFocus();
			}

		});
		wsRegSubBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblCurrency = new JLabel("Currency:");
		lblCurrency.setBounds(472, 15, 62, 14);
		contentPane.add(lblCurrency);
		
		currT = new JTextField();
		currT.setEditable(false);
		currT.setForeground(Color.RED);
		currT.setBounds(531, 10, 86, 20);
		contentPane.add(currT);
		currT.setColumns(10);
		
		currT.setText(currency);
		
		userT = new JTextField(userName+"");
	
		userT.setForeground(Color.RED);
		userT.setEditable(false);
		userT.setColumns(10);
		userT.setBounds(675, 11, 116, 20);
		contentPane.add(userT);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(646, 16, 62, 14);
		contentPane.add(lblUser);
		
		secLevT = new JTextField(roleid+"");
		secLevT.setForeground(Color.RED);
		secLevT.setEditable(false);
		secLevT.setColumns(10);
		secLevT.setBounds(860, 10, 54, 20);
		contentPane.add(secLevT);
		
		JLabel lblSecLev = new JLabel("Sec. Lev:");
		lblSecLev.setBounds(801, 15, 62, 14);
		contentPane.add(lblSecLev);
		
		lastloginT = new JTextField();
		lastloginT.setForeground(Color.RED);
		lastloginT.setEditable(false);
		lastloginT.setColumns(10);
		lastloginT.setBounds(1010, 12, 107, 20);
		contentPane.add(lastloginT);
		
		JLabel lblLastLogin = new JLabel("Last Login:");
		lblLastLogin.setBounds(924, 15, 76, 14);
		contentPane.add(lblLastLogin);
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		logout.setForeground(Color.RED);
		logout.setBounds(1084, 37, 76, 89);
		contentPane.add(logout);
		tglbtnSetFieldsEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tglbtnSetFieldsEditable.isSelected() == false) {

					infoFieldsEditable(false);

				}

				else {

					infoFieldsEditable(true);
				}

			}
		});
		wsCanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});

		seconDegInfoFieldsEdit(true);
		infoFieldsEditable(false);
		prodIDT.requestFocus();
	}

	protected void displayAction() {
		 
		//infoFieldsEditable(true);
		String txtid = prodIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Enter Product warehouse ID.");
			prodIDT.requestFocus();
		}

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null, 	"Please provide whole number value for Product ID.");
				prodIDT.setText(null);
				prodIDT.requestFocus();
			} else {
				int prodID = Integer.parseInt(txtid);
				WHouseInventory whi = new WHouseInventory();
				if (prodID != whi.checkProdId(prodID)) {
					JOptionPane.showMessageDialog(null,
							"No product with code: " + prodID + " "
									+ "is found on Database."
									+ "\nPlease, check code and try again.");
					prodIDT.setText("");
					prodIDT.requestFocus();
				} else {
					String info[] = whi.getProdInfo(prodID);

					prodIDT.setText(info[0]);
					noRetUperLwuT.setText(info[1]);

					lwuT.setText(info[2]);

					lwuQtyT.setText("0");
					lwuUcpT.setText(info[4]);
					lwuSpT.setText(info[5]);

					lwupHwuT.setText(info[6]);

					hwuT.setText(info[7]);
					hwuQtyT.setText(null);
					hwuUcpT.setText(info[9]);
					hwuSpT.setText(info[10]);
					limLevelT.setText(info[11]);
					tcostT.setText(null);

					tQtyLwuT.setText(null);
					catT.setText(info[14]);
					
					//retPriceT.setText(Integer.parseInt(arg0));
					locT.setText(info[15]);

					expDateT.setText(null);

					prodIDT.setEditable(false);

					purchDateT.requestFocus();
				}
			}
		}

	}

	private void seconDegInfoFieldsEdit(boolean flag) {
		lwuQtyT.setEditable(flag);

		hwuQtyT.setEditable(flag);

		tQtyLwuT.setEditable(flag);
		purchIDT.setEditable(flag);
		purchDateT.setEditable(flag);
		prodIDT.setEditable(flag);
		tcostT.setEditable(flag);
		expDateT.setEditable(flag);
	}

	private void infoFieldsEditable(boolean flag) {

		locT.setEditable(flag);
		limLevelT.setEditable(flag);

		lwuT.setEditable(flag);
		hwuT.setEditable(flag);
		lwupHwuT.setEditable(flag);
		lwuSpT.setEditable(flag);
		hwuSpT.setEditable(flag);
		hwuUcpT.setEditable(flag);
		noRetUperLwuT.setEditable(flag);
		lwuUcpT.setEditable(flag);
		catT.setEditable(flag);

	}

	void closeTable(DefaultTableModel model) {

		int rowCount = model.getRowCount();
		if (rowCount == 0)
			JOptionPane.showMessageDialog(null, "Table is Emty");
		else
			for (int r = rowCount - 1; r >= 0; r--)
				model.removeRow(r);

	}
	
	public void initialiseFields() {

		prodIDT.setText(null);
		prodIDT.setEditable(true);
		purchIDT.setText(null);
		locT.setText(null);
		limLevelT.setText(null);

		lwuQtyT.setText(null);
		lwuUcpT.setText(null);
		hwuQtyT.setText(null);
		hwuUcpT.setText(null);

		lwuT.setText(null);
		hwuT.setText(null);
		lwupHwuT.setText(null);
		noRetUperLwuT.setText(null);

		tQtyLwuT.setText(null);
		lwuSpT.setText(null);
		hwuSpT.setText(null);

		catT.setText(null);
		purchDateT.setText(null);
		expDateT.setText(null);
		tcostT.setText(null);
	}
}
