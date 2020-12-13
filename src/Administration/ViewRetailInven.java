package Administration;

import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

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
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.RetailInventory;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewRetailInven extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> retDesCom;

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
	private JButton btnCloseTable;
	private String[][] lookup;
	public static final int classID = 31;
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
					ViewRetailInven frame = new ViewRetailInven(trackID,roleid, empID, userName);
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
	public ViewRetailInven(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("View Retail Inventory");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1064, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		prodRetIDT = new JTextField();
		prodRetIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		prodRetIDT.setToolTipText("Retail Code");
		prodRetIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		prodRetIDT.setBounds(23, 127, 123, 25);
		contentPane.add(prodRetIDT);
		prodRetIDT.setColumns(10);

		retDescT = new JTextField();
		retDescT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retDescT.setToolTipText("Start typing the description on an item and choose from drop down box below");
		retDescT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = retDesCom.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						retDesCom.removeItemAt(i);

				RetailInventory ri = new RetailInventory();
				IntegerInput ii = new IntegerInput();
				String text = retDescT.getText().trim().replaceAll("\\s", "");

				if (ii.isInteger(text))
					;
				else {

					if (!(text.length() > 0)) {
						retDesCom.setSelectedIndex(1);
						retDescT.setText(null);
						retDescT.requestFocus();
					} else

					{

						lookup = ri.searchRetDesc(text);
int size =lookup.length;

if (size==0) {

		JOptionPane.showMessageDialog(null,
				"No warehouse product starting with"
						+ "\"" + text + "\"  "
						+ "is found."
						+ "\nTry other names.");
		retDescT.setText(null);
		retDescT.requestFocus();


} else
						for (int r = 0; r < lookup.length; r++) {

					
								retDesCom.setSelectedIndex(0);
							 

								String cmBoxItem = lookup[r][0] + " - ";
								
								retDesCom.addItem(cmBoxItem);
							
						}
					}

				}

			}
		});
		retDescT.setBounds(155, 127, 236, 30);
		contentPane.add(retDescT);
		retDescT.setColumns(10);

		retPriceT = new JTextField();
		retPriceT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retPriceT.setForeground(Color.BLUE);
		retPriceT.setBounds(143, 351, 123, 30);
		contentPane.add(retPriceT);
		retPriceT.setColumns(10);

		qtyT = new JTextField();
		qtyT.setFont(new Font("Tahoma", Font.BOLD, 12));
		qtyT.setForeground(Color.BLUE);
		qtyT.setToolTipText("Qty Added");
		qtyT.setBounds(288, 259, 123, 30);
		contentPane.add(qtyT);
		qtyT.setColumns(10);

		locT = new JTextField();
		locT.setFont(new Font("Tahoma", Font.BOLD, 12));
		locT.setForeground(Color.BLUE);
		locT.setBounds(143, 431, 208, 30);
		locT.setColumns(10);
		contentPane.add(locT);

		limLevT = new JTextField();
		limLevT.setFont(new Font("Tahoma", Font.BOLD, 12));
		limLevT.setForeground(Color.BLUE);
		limLevT.setBounds(288, 351, 123, 30);
		limLevT.setColumns(10);
		contentPane.add(limLevT);

		lblRetailCode = new JLabel("Retail Code");
		lblRetailCode.setBounds(23, 107, 103, 14);
		lblRetailCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblRetailCode);

		lblDescription = new JLabel("Description");
		lblDescription.setBounds(163, 107, 103, 14);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDescription);

		lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(143, 326, 103, 14);
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblUnitPrice);

		lblQuantity = new JLabel("Quantity ");
		lblQuantity.setBounds(288, 234, 118, 14);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblQuantity);

		lblLocation = new JLabel("Location");
		lblLocation.setBounds(143, 413, 103, 14);
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblLocation);

		lblLimitLevel = new JLabel("Limit Level");
		lblLimitLevel.setBounds(288, 326, 103, 14);
		lblLimitLevel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblLimitLevel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(421, 78, 617, 383);
		contentPane.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Pro_ID", "Description",
				"Ret UPrice", "Qty", "Location", "Limit Lvl" };

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
		displaytableRetItems.setFont(new Font("Tahoma", Font.PLAIN, 11));
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

		displaytableRetItems.setRowHeight(25);
		contentPane.add(scrollPane);

		displaytableRetItems.setFillsViewportHeight(true);
		displaytableRetItems.setColumnSelectionAllowed(true);
		displaytableRetItems.setCellSelectionEnabled(true);

		displaytableRetItems
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displaytableRetItems.setSurrendersFocusOnKeystroke(true);

		scrollPane.setViewportView(displaytableRetItems);

		JButton btcancel = new JButton("Cancel");
		btcancel.setToolTipText("Click to cancel update.");
		btcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btcancel.setForeground(Color.RED);
		btcancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btcancel.setBounds(23, 218, 89, 23);
		contentPane.add(btcancel);

		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProductId.setBounds(143, 230, 87, 23);
		contentPane.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		prodIDT.setForeground(Color.BLUE);
		prodIDT.setToolTipText("Warehouse ID");
		prodIDT.setBounds(143, 259, 123, 30);
		contentPane.add(prodIDT);
		prodIDT.setColumns(10);
		infoFieldsEditable(false);

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
		btnPopulateTable.setBounds(480, 490, 123, 23);
		contentPane.add(btnPopulateTable);

		btnPrintTable = new JButton("Print Table");
		btnPrintTable.setToolTipText("prints the entire table and its displayed content");
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
		btnDisplayInfo.setBounds(23, 170, 102, 23);
		contentPane.add(btnDisplayInfo);

		JButton btnNewButton = new JButton("Update Table");
		btnNewButton.setToolTipText("Click to update table");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(644, 490, 112, 23);
		contentPane.add(btnNewButton);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 11, 381, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		lblUpdateRetailInventory = new JLabel("VIEW SINGLE RETAIL PRODUCT");
		lblUpdateRetailInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUpdateRetailInventory.setBounds(80, 11, 246, 14);
		panel.add(lblUpdateRetailInventory);

		JLabel lblEnterRetailCode = new JLabel(
				"N.B:  Enter retail code OR start type Description and select");
		lblEnterRetailCode.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblEnterRetailCode.setBounds(10, 34, 395, 14);
		panel.add(lblEnterRetailCode);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setLayout(null);
		panel_1.setBounds(491, 11, 365, 48);
		contentPane.add(panel_1);

		JLabel lblInventoryTable = new JLabel(
				"VIEW ALL RETAIL INVENTORY ON TABLE");
		lblInventoryTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInventoryTable.setBounds(47, 23, 297, 14);
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
		btnHelp.setBounds(935, 0, 89, 23);
		contentPane.add(btnHelp);

		btnCloseTable = new JButton("Close Table");
		btnCloseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rowCount = myModel.getRowCount();
				if (rowCount == 0)
					JOptionPane.showMessageDialog(null, "Table is Emty");
				else
					for (int r = rowCount - 1; r >= 0; r--)
						myModel.removeRow(r);

			}
		});
		btnCloseTable.setBounds(779, 490, 102, 23);
		contentPane.add(btnCloseTable);

		retDesCom = new JComboBox<String>();
		retDesCom.setMaximumRowCount(1000);
		retDesCom.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		retDesCom.setSelectedIndex(1);
		retDesCom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(retDesCom.getSelectedIndex() == 0 || retDesCom
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (retDesCom.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							prodRetIDT.setText(lookup[r][0]);
							retDescT.setText(lookup[r][1]);
						}
				}

			}
		});
		retDesCom.setBounds(155, 157, 236, 25);
		contentPane.add(retDesCom);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(935, 34, 89, 23);
		contentPane.add(button);
		contentPane
				.setFocusTraversalPolicy(new FocusTraversalOnArray(
						new Component[] { prodRetIDT, retDescT, retPriceT,
								qtyT, locT, limLevT, btcancel, lblRetailCode,
								lblDescription, lblUnitPrice, lblQuantity,
								lblLocation, lblLimitLevel, scrollPane,
								displaytableRetItems, lblProductId, prodIDT }));

	}

	public void infoFieldsEditable(Boolean truthValue) {

		prodIDT.setEditable(truthValue);
		retPriceT.setEditable(truthValue);
		qtyT.setEditable(truthValue);
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

	protected boolean updateRetInventory(int prodRetID2, int qty) {
		// TODO Auto-generated method stub
		return false;
	}

	public void displayAction() {
		// row = 0;

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
					prodRetIDT.setText(info[0]);
					retDescT.setText(info[1]);
					retPriceT.setText(info[2]);
					qtyT.setText(info[3]);
					locT.setText(info[4]);
					limLevT.setText(info[5]);

					qtyT.requestFocus();
					prodRetIDT.setEditable(false);
					infoFieldsEditable(false);

					// JOptionPane.showMessageDialog(null, info[1]);

				}
			}
		}

	}
}
