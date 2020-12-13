package Administration;

import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import planettaStore.Purchase;
import planettaStore.WHouseInventory;
import security.SecurityAcc;

//import java.util.Date;

@SuppressWarnings("serial")
public class ViewWHouseInventory extends JFrame {

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
	private JComboBox<String> lwuCom;

	private String[][] whouseItems;
	private String[][] lookup;
	private JTextField currT;
	//protected static String currency;
String  currency = "XAF";
public static final int classID = 26;
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
					ViewWHouseInventory frame = new ViewWHouseInventory(trackID,roleid, empID, userName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ViewWHouseInventory(final int trackID,final int roleid, final int empID,  final String userName) {
		setTitle("Warehouse Inventory Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 41, 1064, 615);
		contentPane.add(panel);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProductId.setBounds(21, 10, 118, 17);
		panel.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.setToolTipText("Enter warehouse ID of product.");
		prodIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();

			}
		});
		prodIDT.setForeground(Color.RED);
		prodIDT.setFont(new Font("Tahoma", Font.PLAIN, 16));

		prodIDT.setColumns(10);
		prodIDT.setBounds(21, 32, 118, 27);
		panel.add(prodIDT);

		JLabel lblSupplierId = new JLabel("Purchase ID:");
		lblSupplierId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSupplierId.setBounds(21, 130, 73, 14);
		panel.add(lblSupplierId);

		purchIDT = new JTextField();
		purchIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		purchIDT.setForeground(Color.BLUE);
		purchIDT.setColumns(10);
		purchIDT.setBounds(114, 125, 86, 27);
		panel.add(purchIDT);

		JLabel lblLocationAtWarehouse = new JLabel("Location at Warehouse:");
		lblLocationAtWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocationAtWarehouse.setBounds(682, 168, 143, 14);
		panel.add(lblLocationAtWarehouse);

		locT = new JTextField();
		locT.setFont(new Font("Tahoma", Font.BOLD, 12));
		locT.setForeground(Color.BLUE);
		locT.setColumns(10);
		locT.setBounds(823, 161, 231, 31);
		panel.add(locT);

		JLabel lblSetLimitLevel = new JLabel("Set Limit level in LWU:");
		lblSetLimitLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetLimitLevel.setBounds(765, 130, 135, 14);
		panel.add(lblSetLimitLevel);

		limLevelT = new JTextField();
		limLevelT.setFont(new Font("Tahoma", Font.BOLD, 12));
		limLevelT.setForeground(Color.BLUE);
		limLevelT.setColumns(10);
		limLevelT.setBounds(910, 125, 144, 27);
		panel.add(limLevelT);

		JLabel lblUseLowestWholesale = new JLabel("LWU:");
		lblUseLowestWholesale.setToolTipText("Lowest wholesale unit");
		lblUseLowestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseLowestWholesale.setBounds(202, 10, 36, 28);
		panel.add(lblUseLowestWholesale);

		JLabel lblQuantity = new JLabel("LWU Qty:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(430, 39, 73, 14);
		panel.add(lblQuantity);

		lwuQtyT = new JTextField();
		lwuQtyT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lwuQtyT.setForeground(Color.BLUE);
		lwuQtyT.setBounds(492, 35, 53, 24);
		panel.add(lwuQtyT);
		lwuQtyT.setColumns(10);

		JLabel lblUcp = new JLabel("LWU ucp:");
		lblUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUcp.setBounds(555, 39, 59, 14);
		panel.add(lblUcp);

		lwuUcpT = new JTextField();
		lwuUcpT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lwuUcpT.setForeground(Color.BLUE);
		lwuUcpT.setBounds(613, 34, 66, 27);
		panel.add(lwuUcpT);
		lwuUcpT.setColumns(10);

		JLabel lblUseHighestWholesale = new JLabel("HWU:");
		lblUseHighestWholesale.setToolTipText("Highest wholesale unit");
		lblUseHighestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseHighestWholesale.setBounds(240, 83, 53, 28);
		panel.add(lblUseHighestWholesale);

		JLabel lblHwuQty = new JLabel("HWU Qty:");
		lblHwuQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuQty.setBounds(430, 83, 77, 14);
		panel.add(lblHwuQty);

		hwuQtyT = new JTextField();
		hwuQtyT.setFont(new Font("Tahoma", Font.BOLD, 12));
		hwuQtyT.setForeground(Color.BLUE);
		hwuQtyT.setColumns(10);
		hwuQtyT.setBounds(492, 78, 53, 27);
		panel.add(hwuQtyT);

		JLabel lblUwuUcp = new JLabel("UWU ucp:");
		lblUwuUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUwuUcp.setBounds(555, 83, 59, 14);
		panel.add(lblUwuUcp);

		hwuUcpT = new JTextField();
		hwuUcpT.setFont(new Font("Tahoma", Font.BOLD, 12));
		hwuUcpT.setForeground(Color.BLUE);
		hwuUcpT.setColumns(10);
		hwuUcpT.setBounds(613, 77, 66, 28);
		panel.add(hwuUcpT);
		


		lwuT = new JTextField();
		lwuT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lwuT.setToolTipText("Start typing the prefix of product description (LWU or HWU) and choose from list below.");
		lwuT.setForeground(Color.BLUE);
		lwuT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = lwuCom.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						lwuCom.removeItemAt(i);

				WHouseInventory whi = new WHouseInventory();
				IntegerInput ii = new IntegerInput();
				String text = lwuT.getText().trim().replaceAll("\\s", "");

				if (ii.isInteger(text))
					;
				else {

					if (!(text.length() > 0)) {
						lwuCom.setSelectedIndex(1);
						lwuT.setText(null);
						lwuT.requestFocus();
					} else

					{

						lookup = whi.searchDesc(text);
int size=lookup.length;

if (size==0) {

		JOptionPane.showMessageDialog(null,
				"No warehouse product starting with"
						+ "\"" + text + "\"  "
						+ "is found."
						+ "\nTry other names.");
		lwuT.setText(null);
		lwuT.requestFocus();


} else
						for (int r = 0; r < lookup.length; r++) {

							
								lwuCom.setSelectedIndex(0);
							 

								String cmBoxItem = lookup[r][0] + " - "
										+ lookup[r][1];
								
								lwuCom.addItem(cmBoxItem);
							
						}
					}

				}

			}
		});
		lwuT.setColumns(10);
		lwuT.setBounds(248, 14, 168, 23);
		panel.add(lwuT);

		hwuT = new JTextField();
		hwuT.setForeground(Color.BLUE);
		hwuT.setFont(new Font("Tahoma", Font.BOLD, 12));
		hwuT.setColumns(10);
		hwuT.setBounds(273, 86, 143, 23);
		panel.add(hwuT);

		JLabel lblTotalCostOf = new JLabel("Total Cost:");
		lblTotalCostOf.setToolTipText("Total cost of this item");
		lblTotalCostOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCostOf.setBounds(541, 125, 73, 14);
		panel.add(lblTotalCostOf);

		JLabel lblTotalQuantityIn = new JLabel("Total Qty in LWU units:");
		lblTotalQuantityIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQuantityIn.setBounds(240, 130, 138, 14);
		panel.add(lblTotalQuantityIn);

		JLabel lblNoOfLwu = new JLabel("No of LWU per HWU:");
		lblNoOfLwu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOfLwu.setBounds(240, 167, 147, 17);
		panel.add(lblNoOfLwu);

		lwupHwuT = new JTextField();
		lwupHwuT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lwupHwuT.setForeground(Color.BLUE);
		lwupHwuT.setBounds(374, 163, 59, 27);
		panel.add(lwupHwuT);
		lwupHwuT.setColumns(10);

		JLabel lblNoOfRetail = new JLabel("No of Retail Units per LWU:");
		lblNoOfRetail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOfRetail.setBounds(848, 11, 160, 14);
		panel.add(lblNoOfRetail);

		noRetUperLwuT = new JTextField();
		noRetUperLwuT.setFont(new Font("Tahoma", Font.BOLD, 12));
		noRetUperLwuT.setForeground(Color.BLUE);
		noRetUperLwuT.setBounds(910, 34, 144, 27);
		panel.add(noRetUperLwuT);
		noRetUperLwuT.setColumns(10);

		tQtyLwuT = new JTextField();
		tQtyLwuT.setFont(new Font("Tahoma", Font.BOLD, 12));
		tQtyLwuT.setForeground(Color.BLUE);
		tQtyLwuT.setBounds(374, 125, 86, 27);
		panel.add(tQtyLwuT);
		tQtyLwuT.setColumns(10);

		JLabel lblSp = new JLabel("LWU S.P:");
		lblSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSp.setBounds(689, 37, 66, 14);
		panel.add(lblSp);

		lwuSpT = new JTextField();
		lwuSpT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lwuSpT.setForeground(Color.BLUE);
		lwuSpT.setColumns(10);
		lwuSpT.setBounds(752, 32, 66, 27);
		panel.add(lwuSpT);

		JLabel lblHwuSp = new JLabel("HWU S.P:");
		lblHwuSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuSp.setBounds(689, 81, 66, 14);
		panel.add(lblHwuSp);

		hwuSpT = new JTextField();
		hwuSpT.setFont(new Font("Tahoma", Font.BOLD, 12));
		hwuSpT.setForeground(Color.BLUE);
		hwuSpT.setColumns(10);
		hwuSpT.setBounds(752, 76, 66, 27);
		panel.add(hwuSpT);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(848, 83, 73, 14);
		panel.add(lblCategory);

		catT = new JTextField();
		catT.setFont(new Font("Tahoma", Font.BOLD, 12));
		catT.setForeground(Color.BLUE);
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
		purchDateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		purchDateT.setForeground(Color.BLUE);
		purchDateT.setToolTipText("yyyy-mm-dd");
		purchDateT.setBounds(114, 90, 107, 27);
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
		expDateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		expDateT.setForeground(Color.BLUE);
		expDateT.setToolTipText("yyyy-mm-dd");
		expDateT.setBounds(535, 163, 144, 27);
		panel.add(expDateT);
		expDateT.setColumns(10);

		tcostT = new JTextField();
		tcostT.setForeground(Color.BLUE);
		tcostT.setFont(new Font("Tahoma", Font.BOLD, 12));
		tcostT.setBounds(613, 124, 118, 27);
		panel.add(tcostT);
		tcostT.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 220, 1044, 333);
		panel.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "ProID", "Ret/lwu",
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
			@SuppressWarnings({ "unused", "rawtypes" })
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
				.setPreferredWidth(50);
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

				int noItems;// = whi.getInvCount();

				whouseItems = whi.getAllWHItems();

NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(true);
				
				
				noItems = whouseItems.length;
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
		button_1.setToolTipText("Print table");
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
		button_1.setBounds(750, 569, 112, 23);
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

				int noItems;// = whi.getInvCount();

				whouseItems = whi.getAllWHItems();

				NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(true);
				
				
				noItems = whouseItems.length;
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
		button_2.setBounds(351, 569, 112, 23);
		panel.add(button_2);

		JButton btnNewButton_1 = new JButton("Display Info");
		btnNewButton_1
				.setToolTipText("Having provided product ID, click here to display its details.");
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

		JButton btnNewButton = new JButton("Close Table");
		btnNewButton.setToolTipText("Close table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rowCount = myModel.getRowCount();
				for (int r = rowCount - 1; r >= 0; r--)
					myModel.removeRow(r);

			}
		});
		btnNewButton.setBounds(555, 569, 98, 23);
		panel.add(btnNewButton);

		lwuCom = new JComboBox<String>();
		lwuCom.setMaximumRowCount(1000);
		lwuCom.setToolTipText("Select product here.");
		lwuCom.setModel(new DefaultComboBoxModel<String>(new String[] { "--Choose--",
				"" }));
		lwuCom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(lwuCom.getSelectedIndex() == 0 || lwuCom
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (lwuCom.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							prodIDT.setText(lookup[r][0]);
							hwuT.setText(lookup[r][1]);

							JOptionPane.showMessageDialog(null,
									"Click the Display button");
						}
				}

			}
		});
		lwuCom.setBounds(249, 41, 168, 20);
		panel.add(lwuCom);

		JButton wsCanBtn = new JButton("Cancel All");
		wsCanBtn.setToolTipText("Click here to empty all fields.");
		wsCanBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		wsCanBtn.setForeground(Color.RED);
		wsCanBtn.setBounds(310, 7, 117, 30);
		contentPane.add(wsCanBtn);

		JButton btnHelp = new JButton("HELP ?");
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setBounds(911, 0, 89, 23);
		contentPane.add(btnHelp);
		wsCanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});

		seconDegInfoFieldsEdit(false);
		infoFieldsEditable(false);
		lwuT.setEditable(true);
		prodIDT.setEditable(true);
		purchIDT.setEditable(true);
		
		JLabel lblCurrency = new JLabel("CURRENCY:");
		lblCurrency.setForeground(Color.DARK_GRAY);
		lblCurrency.setBounds(19, 11, 74, 14);
		contentPane.add(lblCurrency);
		
		currT = new JTextField();
		currT.setFont(new Font("Tahoma", Font.BOLD, 12));
		currT.setForeground(Color.RED);
		currT.setEditable(false);
		currT.setBounds(103, 10, 61, 20);
		contentPane.add(currT);
		currT.setColumns(10);
		prodIDT.requestFocus();
		currT.setText(currency);
		
		JButton button_3 = new JButton("Log Out");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button_3.setForeground(Color.RED);
		button_3.setBounds(795, 0, 89, 23);
		contentPane.add(button_3);
	}

	protected void displayAction() {

		String txtid = prodIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0)) {
			JOptionPane
					.showMessageDialog(null, "Enter Product's warehouse ID.");
			prodIDT.requestFocus();
		}

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Product ID.");
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
					
					NumberFormat format = NumberFormat.getNumberInstance();
					
					format.setMaximumFractionDigits(1);
					format.setGroupingUsed(true);
					String info[] = whi.getProdInfo(prodID);

					prodIDT.setText(info[0]);
					noRetUperLwuT.setText(info[1]);

					lwuT.setText(info[2]);

					lwuQtyT.setText(info[3]);
					lwuUcpT.setText(format.format(Float.parseFloat(info[4])));
					lwuSpT.setText(format.format(Float.parseFloat(info[5])));

					lwupHwuT.setText(info[6]);

					hwuT.setText(info[7]);
					hwuQtyT.setText(info[8]);
					hwuUcpT.setText(format.format(Float.parseFloat(info[9])));
					hwuSpT.setText(format.format(Float.parseFloat(info[10])));
					limLevelT.setText(info[11]);
					tcostT.setText(format.format(Float.parseFloat(info[12])))
					;

					tQtyLwuT.setText(info[13]);
					catT.setText(info[14]);
					locT.setText(info[15]);

					String pd = purchIDT.getText().trim().replaceAll("\\s", "");
					if (!(pd.length() > 0)) {

						expDateT.setText(""
								+ whi.getMinExpDate(Integer.parseInt(info[0])));
						JOptionPane
								.showMessageDialog(
										null,
										"N.B: Expiry date provided is the earliest for this product.\n If you need the precise expiry date re-enter product ID and Purchase ID.\n Then click \"Display Info\" button.");
					} else {
						Purchase p = new Purchase();
						if (ii.isInteger(pd)
								&& (Integer.parseInt(pd) == p
										.checkPurchID(Integer.parseInt(pd))))

							expDateT.setText(""
									+ whi.getExpDate(Integer.parseInt(pd),
											Integer.parseInt(info[0])));
					}
					prodIDT.setEditable(true);

					prodIDT.requestFocus();
				}
			}
		}

	}

	private void seconDegInfoFieldsEdit(boolean flag) {
		lwuQtyT.setEditable(flag);

		hwuQtyT.setEditable(flag);

		tQtyLwuT.setEditable(flag);

		tcostT.setEditable(flag);
		expDateT.setEditable(flag);
	}

	private void infoFieldsEditable(boolean flag) {

		locT.setEditable(flag);
		limLevelT.setEditable(flag);

		hwuT.setEditable(flag);
		lwupHwuT.setEditable(flag);
		lwuSpT.setEditable(flag);
		hwuSpT.setEditable(flag);
		hwuUcpT.setEditable(flag);
		noRetUperLwuT.setEditable(flag);
		lwuUcpT.setEditable(flag);
		catT.setEditable(flag);

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
