package interfaceClass;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import planettaStore.Customer;
import planettaStore.Inventory;
import planettaStore.RetailInventory;
import planettaStore.Transaction;
import security.SecurityAcc;

import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import access.EmployeeAcc;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RetailWindow {

	/**
	 * <h4>Main Retail Window</h4>
	 * 
	 * @author Afundoh Edward
	 * @version 1.0
	 */
	String[][] lookup;

	public static int rowCountPrint;
	private JComboBox<String> comboBox;
	static int prodRetID;
	int l;
	int transID;
	String[][] lu;
	int retailPrice;
	String description;
	String location;
	int limit;
	int row;
	int prodRetQty;
	String transType = "Retail_Paid";
	float prodTot;
	JFrame frame;
	public static JTable proSaleTable;
	private JTextField proCodeName;
	// private JButton btnValidate;
	public TableModelListener tml;
	int No = 0;
	JTable table;
	JTextField tf;
	int oldQty, newQty;
	Object columns;
	private static JTextField grandTotTB;
	public float gtot;
	Graphics g;
	public static JComboBox<String> retDescCom;
	private JTextField retDescTB;
	
	private JTextField rowT;
	static int roleid2;
	static int empID2;
 static String userName2;
 public static final int classID = 1;
 static int trackID2;
 private JTextField txtXaf;
 private JTextField userT;
 private JTextField secLevT;
 private JTextField roleT;
 private JTextField cusidT;
 private JTextField cusfnT;
 private JTextField cusphoneT;
 private JTextField cusaddT;
 private JTextField cusnicT;
 private JTextField cusemailT;
 private JTextField cusonT;
 private JTextField empIDT;
 private JTextField rowQtyT2;
 private JTextField GtT;
 private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetailWindow window = new RetailWindow(trackID2, roleid2, empID2, userName2);
					window.frame.setVisible(true);

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
	public RetailWindow(int trackID, int roleid, int empID, String userName) {
		initialize(trackID, roleid, empID, userName);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param userName2 
	 * @param empID 
	 * @param roleid 
	 * @param trackID 
	 */
	private void initialize(final int trackID, final int roleid, final int empID, String userName) {
		frame = new JFrame("Retail Window");
		frame.setBounds(100, 100, 1151, 788);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProduct = new JLabel("Product Code");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProduct.setBounds(198, 212, 142, 20);
		frame.getContentPane().add(lblProduct);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No.", "ID", "Description", "Qty",
				"U.Price", "Total" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable proSaleTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[] { Integer.class, Object.class,
					String.class, Integer.class, Integer.class, Float.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		proSaleTable.getTableHeader().getColumnModel().getColumn(0)
		.setPreferredWidth(14);
		proSaleTable.getTableHeader().getColumnModel().getColumn(1)
		.setPreferredWidth(70);
		proSaleTable.getTableHeader().getColumnModel().getColumn(2)
		.setPreferredWidth(90);
		proSaleTable.getTableHeader().getColumnModel().getColumn(3)
		.setPreferredWidth(30);
		proSaleTable.getTableHeader().getColumnModel().getColumn(5)
		.setPreferredWidth(50);
		proCodeName = new JTextField();
		proCodeName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		proCodeName.setBounds(194, 237, 170, 47);

		frame.getContentPane().add(proCodeName);
		proCodeName.setColumns(10);

		proCodeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//row = 0;

				String txtid;
				if (!((txtid = proCodeName.getText()).length() > 0))

					JOptionPane.showMessageDialog(null, "Enter Product Code");

				else {

					IntegerInput ii = new IntegerInput();
					if (!(ii.isInteger(txtid))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for Product code.");
						proCodeName.setText(null);
					} else {
						prodRetID = Integer.parseInt(txtid);
						RetailInventory ri = new RetailInventory();
						if (prodRetID != ri.checkProdRID(prodRetID))
							JOptionPane
									.showMessageDialog(
											null,
											"No product with code = "
													+ prodRetID
													+ " "
													+ "is found on Database."
													+ "\nPlease, check code and try again.");
						else {
							prodRetQty = 1;

							int rc = proSaleTable.getRowCount();
							if (rc == 0) {

								getProdPropDB(prodRetID, prodRetQty);

								myModel.insertRow(row, new Object[] { row++,
										prodRetID, description, prodRetQty,
										retailPrice, (int) prodTot });
								displayGrandTotal(prodTot, grandTotTB, gtot);

							}

							else

							{
								Inventory i = new Inventory();

								int key = i.searchProdIdTable(prodRetID,
										proSaleTable);

								if (key != -1) {
									String retq = myModel.getValueAt(key, 3)
											.toString();
									int rq = Integer.parseInt(retq);
									prodRetQty = rq + 1;

									getProdPropDB(prodRetID, prodRetQty);

									proSaleTable.setValueAt(prodRetQty, key, 3);
									proSaleTable.setValueAt(prodTot, key, 5);

									String olTot = grandTotTB.getText();
									Float oldTot = Float.parseFloat(olTot);

									displayGrandTotal(retailPrice, grandTotTB,
											oldTot);

								} else

								{
									getProdPropDB(prodRetID, prodRetQty);
									String olgt = grandTotTB.getText();
									Float newGtot = Float.parseFloat(olgt);
									myModel.insertRow(row, new Object[] { row++,
											prodRetID, description, prodRetQty,
											retailPrice, (int) prodTot });
									displayGrandTotal(prodTot, grandTotTB,
											newGtot);

								}

							}
						}

					}
				}
				proCodeName.setText(null);
				// row++;
			}

		});

		final JButton btnValidate = new JButton("Validate");
		btnValidate.setForeground(Color.BLUE);
		btnValidate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnValidate.setBounds(476, 688, 133, 34);
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction t = new Transaction();
				IntegerInput ii = new IntegerInput();
				
				int amount = Integer.parseInt(grandTotTB.getText().trim()
						.replaceAll("\\s", ""));
				String custName = cusfnT.getText().trim()
						.replaceAll("\\s", "");
				
				
				String custIDs = cusidT.getText().trim()
						.replaceAll("\\s", "");
				int custID = -1;

				if (custIDs.length() > 0) 
					
						if(ii.isInteger(custIDs))
							 custID = Integer.parseInt(custIDs);	
				
					

				transID = t.getTransID();
				if (!(t.createTrans(transID, transType, amount, empID, custID, custName))) {
					JOptionPane
							.showMessageDialog(
									null,
									"Unable to create transaction.\nOperation will be terminated, Sorry.",
									"Database Error!",
									JOptionPane.ERROR_MESSAGE);
					int rc = myModel.getRowCount();
					for (int r = rc - 1; r >= 0; r--)

						myModel.removeRow(r);
					grandTotTB.setText(null);
					GtT.setText(null);

					row = 0;
				} else {
					proSaleTable.getTableHeader().requestFocus();
					if (myModel.getRowCount() == 0)
						JOptionPane
								.showMessageDialog(null,
										"Retail table empty. Thus, no action is taken.");

					else {

						int prodRid = 0;
						int qty = 0;
						RetailInventory ri = new RetailInventory();

						int rc = myModel.getRowCount();

						//int grandTot = Integer.parseInt(grandTotTB.getText());
						// registerTrans(grandTot, )
						for (int i = 0; i < rc; i++) {

							prodRid = (int) proSaleTable.getValueAt(i, 1);
							String retDesc = proSaleTable.getValueAt(i, 2)
									.toString();
							String sqty = proSaleTable.getValueAt(i, 3)
									.toString();
							String sretPrice = proSaleTable.getValueAt(i, 4)
									.toString();
							String stot = proSaleTable.getValueAt(i, 5)
									.toString();
							int retPrice = Integer.parseInt(sretPrice);
							int tot = Integer.parseInt(stot);

							qty = Integer.parseInt(sqty);
							ri.saleFinalisation(prodRid, transID, retDesc, qty,
									retPrice, tot);

						}
						JOptionPane
								.showMessageDialog(null,
										"Transaction successful.\nClick 'OK' for receipt to print.");
						table = proSaleTable;
						tf = grandTotTB;
						PageFormat pf = new PageFormat();
						PrintClass p = new PrintClass();
						try {
							p.print(g, pf, 1);
						} catch (PrinterException e1) {
							// TODO Auto-generated catch block
							JOptionPane
									.showMessageDialog(null,
											"Printing Error: See message that follows:");
						}
						// btnValidate.addActionListene(new Print());

						rowCountPrint = myModel.getRowCount();

						p.actionPerformed(e);

						for (int r = rc - 1; r >= 0; r--)

							myModel.removeRow(r);
						grandTotTB.setText(null);
GtT.setText(null);
						row = 0;

					}
				}
			}
		});
		frame.getContentPane().add(btnValidate);

		retDescCom = new JComboBox<String>();
		retDescCom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == 1) {

					//row = 0;
					RetailInventory ri = new RetailInventory();
					prodRetQty = 1;

					String selected = retDescCom.getSelectedItem().toString();

					if (selected.equals(null) || selected.equals("--Choose--") || selected.equals(""))
						;

					else {
						String text = retDescTB.getText();
						if (text.length() > 0) {

							lu = ri.searchString(text);
							for (int r = 0; r < lu.length; r++)
								if (selected
										.equals(lu[r][0] + " - " + lu[r][1])) {
									prodRetID = Integer.parseInt(lu[r][0]);
									break;
								} else if (!(lu[r][0].length() > 0))

								{
									JOptionPane
											.showMessageDialog(null,
													"Sorry!\nProduct code error occurred.Refer code otherwise.  ");
									break;
								}
							int rc = proSaleTable.getRowCount();

							if (rc == 0) {

								getProdPropDB(prodRetID, prodRetQty);

								myModel.insertRow(row, new Object[] { row++,
										prodRetID, description, prodRetQty,
										retailPrice, (int) prodTot });
								displayGrandTotal(prodTot, grandTotTB, gtot);
								retDescTB.setText(null);
								retDescCom.setSelectedIndex(1);
							}

							else

							{
								Inventory i = new Inventory();

								int key = i.searchProdIdTable(prodRetID,
										proSaleTable);

								if (key != -1) {
									String retq = myModel.getValueAt(key, 3)
											.toString();
									int rq = Integer.parseInt(retq);
									prodRetQty = rq + 1;

									getProdPropDB(prodRetID, prodRetQty);

									proSaleTable.setValueAt(prodRetQty, key, 3);
									proSaleTable.setValueAt(prodTot, key, 5);

									String olTot = grandTotTB.getText();
									Float oldTot = Float.parseFloat(olTot);

									displayGrandTotal(retailPrice, grandTotTB,
											oldTot);
									retDescTB.setText(null);
									retDescCom.setSelectedIndex(1);

								} else

								{
									getProdPropDB(prodRetID, prodRetQty);
									String olgt = grandTotTB.getText();
									Float newGtot = Float.parseFloat(olgt);
									myModel.insertRow(row, new Object[] { row++,
											prodRetID, description, prodRetQty,
											retailPrice, (int) prodTot });
									displayGrandTotal(prodTot, grandTotTB,
											newGtot);
									retDescTB.setText(null);
									retDescCom.setSelectedIndex(1);

								}
							}
						}
					}
				}

			}

		});

		retDescCom.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));

		retDescCom.setBounds(366, 264, 194, 20);
		retDescCom.setToolTipText("product name");
		retDescCom.setAutoscrolls(true);
		retDescCom.setSelectedIndex(1);

		frame.getContentPane().add(retDescCom);

		JLabel lblProductName = new JLabel("Product Description");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductName.setBounds(366, 212, 194, 20);
		frame.getContentPane().add(lblProductName);
		/*
		 * JScrollPane scrollPane = new JScrollPane(); scrollPane.setBounds(265,
		 * 613, 311, -217); frame.getContentPane().add(scrollPane);
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 295, 955, 353);
		frame.getContentPane().add(scrollPane_1);

		proSaleTable.setFillsViewportHeight(true);
		proSaleTable.setColumnSelectionAllowed(true);
		proSaleTable.setCellSelectionEnabled(true);

		proSaleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proSaleTable.setSurrendersFocusOnKeystroke(true);

		scrollPane_1.setViewportView(proSaleTable);

		grandTotTB = new JTextField();
		grandTotTB.setForeground(Color.RED);
		grandTotTB.setFont(new Font("Tahoma", Font.BOLD, 28));
		grandTotTB.setEditable(false);
		grandTotTB.setBounds(871, 237, 155, 47);
		frame.getContentPane().add(grandTotTB);
		grandTotTB.setColumns(10);

		JLabel lblGrandTotal = new JLabel("Total:");
		lblGrandTotal.setForeground(Color.RED);
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblGrandTotal.setBounds(716, 237, 114, 47);
		frame.getContentPane().add(lblGrandTotal);
/*
		Action action = new AbstractAction() {
		
			private static final long serialVersionUID = -6319652940262699451L;

			public void actionPerformed(ActionEvent e) {
				int newQty;
				TableCellListener tcl = (TableCellListener) e.getSource();
				int column = tcl.getColumn();

				IntegerInput ii = new IntegerInput();
				if (column == 3) {
					int ro = tcl.getRow();

					Object olQty = tcl.getOldValue();
					String oQty = olQty.toString();
					int oldQty = Integer.parseInt(oQty);

					String nQty = (String) proSaleTable.getValueAt(ro, 3);
					if (!(ii.isInteger(nQty))) {
						JOptionPane.showMessageDialog(null,
								"Please provide whole number value for new quantity at row: "
										+ ro, "Error!", JOptionPane.ERROR_MESSAGE);

						myModel.setValueAt(oldQty, ro, 3);
						newQty = oldQty;
					} else
						newQty = Integer.parseInt(nQty);

					int retailPrice = (int) proSaleTable.getValueAt(ro, 4);
					
					//int retailPrice = Integer.parseInt((String) proSaleTable.getValueAt(ro, 4));
					String olGT = grandTotTB.getText();
					Float oldGT = Float.parseFloat(olGT);
					Inventory i = new Inventory();
					int oldTot = i.prodTot(retailPrice, oldQty);
					int prodTot = i.prodTot(retailPrice, newQty);
					Float gtot = i.grandTotMinusPTot(oldGT, oldTot);

					proSaleTable.setValueAt(prodTot, ro, 5);

					displayGrandTotal(prodTot, grandTotTB, gtot);
					if (newQty == 0)
						myModel.removeRow(ro);
				}
			}
		};

		new TableCellListener(proSaleTable, action);
*/
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rc = myModel.getRowCount();
				for (int r = rc - 1; r >= 0; r--)

					myModel.removeRow(r);
				grandTotTB.setText(null);
GtT.setText(null);
				row = 0;

			}
		});
		btnCancel.setBounds(735, 689, 126, 32);
		frame.getContentPane().add(btnCancel);

		retDescTB = new JTextField();
		retDescTB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		retDescTB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = retDescCom.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						retDescCom.removeItemAt(i);

				RetailInventory ri = new RetailInventory();

				String text = retDescTB.getText();
				if (text.length() > 0) {

					lu = ri.searchString(text);
int size = lu.length;
if (size == 0){
	JOptionPane.showMessageDialog(null,
			"No product name starting with" + "\""
					+ text + "\"  " + "is found."
					+ "\nTry other names.");
retDescTB.setText(null);
retDescTB.requestFocus();
retDescCom.setSelectedIndex(1);

} else
					for (int r = 0; r < size; r++) {

					
							retDescCom.setSelectedIndex(0);
						
						
							String cmBoxItem = lu[r][0] + " - " + lu[r][1];
							
							retDescCom.addItem(cmBoxItem);
							
					
							
									
						
					}

				}
				// retDescCom.setSelectedIndex(0);
			}
		});
		retDescTB.setBounds(366, 237, 194, 23);
		frame.getContentPane().add(retDescTB);
		retDescTB.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(264, 11, 455, 179);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 455, 31);
		panel_3.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		
		JLabel lblCustomer = new JLabel("Customer Details");
		lblCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCustomer.setBounds(145, 1, 148, 25);
		panel_2.add(lblCustomer);
		
		JButton cancelbt = new JButton("Cancel");
		cancelbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initialiseFields();
			}
		});
		cancelbt.setFont(new Font("Tahoma", Font.BOLD, 13));
		cancelbt.setForeground(Color.RED);
		cancelbt.setBounds(347, 3, 89, 23);
		panel_2.add(cancelbt);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				IntegerInput ii = new IntegerInput();

				String cusIDs = cusidT.getText().trim().replaceAll("\\s", "");

				String fName = cusfnT.getText().trim();
				String otherNames = cusonT.getText().trim();

				String phones = cusphoneT.getText().trim().replaceAll("\\s", "");

				String address = cusaddT.getText().trim();

				String email = cusemailT.getText().trim().replaceAll("\\s", "");

				String nics = cusnicT.getText().trim().replaceAll("\\s", "");
			
if((cusidT.isEditable() == true ))
	JOptionPane.showMessageDialog(null	, "Customer already registered\nGo to Administration and update customer if that is what you want");
else
				if (!(cusIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate customer ID.");
					cusidT.requestFocus();
				} else {
					int cusID = Integer.parseInt(cusIDs);

					if (!(fName.length() > 0)) {
						JOptionPane
								.showMessageDialog(null, "Enter first name.");
						cusfnT.requestFocus();
					} else

					if (!(otherNames.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter other names.");
						cusonT.requestFocus();
					} else if (!(phones.length() >= 8)) {
						JOptionPane.showMessageDialog(null,
								"Enter phone number, atleast 8 digits.");
						cusphoneT.setText(null);
						cusphoneT.requestFocus();
					} else if (!(ii.isInteger(phones))) {
						JOptionPane.showMessageDialog(null,
								"Enter whole number values for phone number.",
								"Wrong Values", JOptionPane.ERROR_MESSAGE);
						cusphoneT.setText(null);
						cusphoneT.requestFocus();
					} else {
						int phone = Integer.parseInt(phones);
						if (!(address.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter home address.");
							cusaddT.requestFocus();
						}

						else if (!(email.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter email address.");
							cusemailT.requestFocus();
						}

						else if (!(nics.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter national identity card number.");
							cusnicT.requestFocus();
						} else if (!(ii.isInteger(nics))) {
							JOptionPane.showMessageDialog(null,
									"Enter whole number values NIC no.",
									"Wrong Value", JOptionPane.ERROR_MESSAGE);
							cusnicT.setText(null);
							cusnicT.requestFocus();
						} else {
							int nic = Integer.parseInt(nics);
							int grade = 0;
								Customer c = new Customer();
								if (c.registerCustomer(

								cusID,

								fName, otherNames,

								phone, address,

								email,

								nic, grade

								))

									JOptionPane
											.showMessageDialog(null,
													"Customer successfully registered.");

								else

									JOptionPane
											.showMessageDialog(
													null,
													"Customer  registration has failed.",
													"Database Error",
													JOptionPane.ERROR_MESSAGE);
								initialiseFields();
cusidT.setEditable(true);
							}
						}
					

				}
				
				
			}
		});
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(24, 3, 89, 23);
		panel_2.add(btnRegister);
		
		cusidT = new JTextField();
		cusidT.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyReleased(KeyEvent arg0) {
				
				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Customer em = new Customer();
				IntegerInput ii = new IntegerInput();
				String text = cusidT.getText().trim().replaceAll("\\s", "");
				if (ii.isInteger(text))
					;
				else if (!(text.length() > 0)) {
					comboBox.setSelectedIndex(1);
					cusidT.setText(null);
					cusidT.requestFocus();
				} else

				{

					lookup = em.searchString(text);
int size=lookup.length;
if (size==0) {

		JOptionPane.showMessageDialog(null,
				"No Customer name starting with" + "\""
						+ text + "\"  " + "is found."
						+ "\nTry other names.");
		cusidT.setText(null);
		cusidT.requestFocus();
	

} else
					for (int r = 0; r < lookup.length; r++) {

						
							comboBox.setSelectedIndex(0);
						
						 

							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1];
							
							comboBox.addItem(cmBoxItem);
						

					}

				}

			}
		});
		cusidT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayAction();
			}
		});
		cusidT.setBounds(84, 42, 138, 23);
		panel_3.add(cusidT);
		cusidT.setToolTipText("Start typing name of Customer and select below");
		cusidT.setColumns(10);
		
		JButton label_4 = new JButton("Cust ID:");
		label_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer cus = new Customer();
				cusidT.setText("" + cus.generateID());
				cusidT.setEditable(false);

			}
		});
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setForeground(Color.RED);
		label_4.setBounds(0, 42, 83, 23);
		panel_3.add(label_4);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayAction();
			}
		});
		btnDisplay.setBounds(0, 74, 76, 23);
		panel_3.add(btnDisplay);
		btnDisplay.setToolTipText("Click here to view info about Customer ID entered above");
		
		 comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
Customer c = new Customer();
 lookup = c.searchString(cusidT.getText());
				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							cusidT.setText(lookup[r][0]);
							cusfnT.setText(lookup[r][1]);
						}
				}
			}
		});
		comboBox.setBounds(84, 77, 138, 20);
		panel_3.add(comboBox);
		comboBox.setToolTipText("Select Customer here");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--", ""}));
		comboBox.setSelectedIndex(1);
		comboBox.setMaximumRowCount(100);
		
		cusfnT = new JTextField();
		cusfnT.setBounds(84, 112, 138, 23);
		panel_3.add(cusfnT);
		cusfnT.setText("Customer");
		cusfnT.setColumns(10);
		
		JLabel label_5 = new JLabel("First Name:");
		label_5.setBounds(0, 113, 76, 20);
		panel_3.add(label_5);
		
		cusphoneT = new JTextField();
		cusphoneT.setBounds(300, 42, 155, 23);
		panel_3.add(cusphoneT);
		cusphoneT.setToolTipText("Start typing name of Customer and select below");
		cusphoneT.setColumns(10);
		
		cusaddT = new JTextField();
		cusaddT.setBounds(300, 78, 155, 23);
		panel_3.add(cusaddT);
		cusaddT.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(247, 79, 69, 20);
		panel_3.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(247, 43, 102, 20);
		panel_3.add(lblPhone);
		
		cusnicT = new JTextField();
		cusnicT.setBounds(300, 148, 155, 23);
		panel_3.add(cusnicT);
		cusnicT.setColumns(10);
		
		JLabel lblNic = new JLabel("NIC:");
		lblNic.setBounds(247, 149, 69, 20);
		panel_3.add(lblNic);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(247, 113, 102, 20);
		panel_3.add(lblEmail);
		
		cusemailT = new JTextField();
		cusemailT.setBounds(300, 112, 155, 23);
		panel_3.add(cusemailT);
		cusemailT.setToolTipText("Start typing name of Customer and select below");
		cusemailT.setColumns(10);
		
		JLabel lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setBounds(0, 147, 102, 20);
		panel_3.add(lblOtherNames);
		
		cusonT = new JTextField();
		cusonT.setBounds(84, 146, 138, 23);
		panel_3.add(cusonT);
		cusonT.setToolTipText("Start typing name of Customer and select below");
		cusonT.setColumns(10);
		
		rowT = new JTextField();
		rowT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rowT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String rows = rowT.getText().trim().replaceAll("\\s", "");
				String rowQtys = rowQtyT2.getText().trim().replaceAll("\\s", "");
				
				if (!(rows.length()> 0))

					JOptionPane.showMessageDialog(null, "Enter row at which you need to change quantity");

				else {

					IntegerInput ii = new IntegerInput();
					if (!(ii.isInteger(rows))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for row.");
						rowT.setText(null);
					} 
					else {
						int row = Integer.parseInt(rows);
					
						if (!(rowQtys.length()> 0))

							JOptionPane.showMessageDialog(null, "Enter quantity you wish to add");

						else {

						
							if (!(ii.isInteger(rowQtys))) {
								JOptionPane
										.showMessageDialog(null,
												"Please provide whole number value for quantity.");
								rowQtyT2.setText(null);
							} 
							else 
						
						
							{
								
								int rowQty = Integer.parseInt(rowQtys);
						
						
					
				
				updateRowValues(row, rowQty, proSaleTable , grandTotTB, myModel );
							}}
						}}
				
			}
		});
		rowT.setColumns(10);
		rowT.setBounds(71, 237, 119, 47);
		frame.getContentPane().add(rowT);
		
		JLabel lblQty = new JLabel("Quantity");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQty.setBounds(570, 212, 107, 20);
		frame.getContentPane().add(lblQty);
		
		JLabel lblRow = new JLabel("Row No");
		lblRow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRow.setBounds(71, 212, 76, 20);
		frame.getContentPane().add(lblRow);
		
		JButton button = new JButton("Log Out");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(989, 11, 86, 41);
		frame.getContentPane().add(button);
		
		txtXaf = new JTextField();
		txtXaf.setEditable(false);
		txtXaf.setForeground(Color.BLUE);
		txtXaf.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtXaf.setText("XAF");
		txtXaf.setBounds(827, 237, 45, 47);
		frame.getContentPane().add(txtXaf);
		txtXaf.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 202, 179);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 202, 31);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		
		JLabel label = new JLabel("User Status");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(10, 0, 182, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Current User:");
		label_1.setBounds(10, 42, 90, 14);
		panel_1.add(label_1);
		
		userT = new JTextField(userName);
		userT.setBounds(115, 42, 86, 20);
		panel_1.add(userT);
		userT.setForeground(Color.BLACK);
		userT.setFont(new Font("Tahoma", Font.BOLD, 11));
		userT.setEditable(false);
		userT.setColumns(10);
		
		JLabel label_2 = new JLabel("Security Level:");
		label_2.setBounds(10, 102, 90, 14);
		panel_1.add(label_2);
		
		secLevT = new JTextField(roleid+"");
		secLevT.setBounds(115, 99, 86, 20);
		panel_1.add(secLevT);
		secLevT.setForeground(Color.BLACK);
		secLevT.setFont(new Font("Tahoma", Font.BOLD, 11));
		secLevT.setEditable(false);
		secLevT.setColumns(10);
		
		JLabel label_3 = new JLabel("Role:");
		label_3.setBounds(10, 133, 49, 14);
		panel_1.add(label_3);
		EmployeeAcc ea = new EmployeeAcc();
		
		roleT = new JTextField(ea.getRole(roleid));
		roleT.setBounds(115, 130, 86, 20);
		panel_1.add(roleT);
		roleT.setForeground(Color.BLACK);
		roleT.setFont(new Font("Tahoma", Font.BOLD, 11));
		roleT.setEditable(false);
		roleT.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(11, 74, 90, 14);
		panel_1.add(lblUserId);
		
		empIDT = new JTextField(10);
		empIDT.setText(empID+"");
		empIDT.setForeground(Color.BLACK);
		empIDT.setFont(new Font("Tahoma", Font.BOLD, 11));
		empIDT.setEditable(false);
		empIDT.setBounds(116, 71, 86, 20);
		panel_1.add(empIDT);
		
		rowQtyT2 = new JTextField();
		rowQtyT2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String rows = rowT.getText().trim().replaceAll("\\s", "");
				String rowQtys = rowQtyT2.getText().trim().replaceAll("\\s", "");
				
				if (!(rows.length()> 0))

					JOptionPane.showMessageDialog(null, "Enter row at which you need to change quantity");

				else {

					IntegerInput ii = new IntegerInput();
					if (!(ii.isInteger(rows))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for row.");
						rowT.setText(null);
					} 
					else {
						int row = Integer.parseInt(rows);
					
						if (!(rowQtys.length()> 0))

							JOptionPane.showMessageDialog(null, "Enter quantity you wish to add");

						else {

						
							if (!(ii.isInteger(rowQtys))) {
								JOptionPane
										.showMessageDialog(null,
												"Please provide whole number value for quantity.");
								rowQtyT2.setText(null);
							} 
							else 
						
						
							{
								
								int rowQty = Integer.parseInt(rowQtys);
						
						
					
				
				updateRowValues(row, rowQty, proSaleTable , grandTotTB, myModel );
							}}
						}}
			}
		});
		rowQtyT2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rowQtyT2.setColumns(10);
		rowQtyT2.setBounds(562, 237, 133, 47);
		frame.getContentPane().add(rowQtyT2);
		
		GtT = new JTextField();
		GtT.setForeground(Color.RED);
		GtT.setFont(new Font("Tahoma", Font.BOLD, 28));
		GtT.setEditable(false);
		GtT.setColumns(10);
		GtT.setBounds(187, 675, 155, 47);
		frame.getContentPane().add(GtT);
		
		textField_1 = new JTextField();
		textField_1.setText("XAF");
		textField_1.setForeground(Color.BLUE);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(143, 675, 45, 47);
		frame.getContentPane().add(textField_1);
		
		JLabel label_6 = new JLabel("Total:");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		label_6.setBounds(32, 675, 114, 47);
		frame.getContentPane().add(label_6);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{rowQtyT2, rowT}));

	}
	
	private void updateRowValues(int row, int addQty, JTable table, JTextField  Grtot, DefaultTableModel model ) {
		
		if (row >= table.getRowCount())
			JOptionPane.showMessageDialog(null, "Row: "+ row + " is not found on the table.\n Revise and try again.");
		else
		{
			int oldQ = Integer.parseInt(table.getValueAt(row, 3).toString());
			int oldGT = Integer.parseInt(Grtot.getText());
			int retPrice = Integer.parseInt(table.getValueAt(row, 4).toString());
			
			int newQ = oldQ + addQty;
			if(newQ < 0)
				newQ = 0;
			int newtot = newQ*retPrice;
			int newGt = oldGT - (oldQ * retPrice) + newtot;
			
			table.setValueAt(newQ, row, 3);
			table.setValueAt( newtot, row, 5);
			Grtot.setText(newGt+"");
			if(newQ == 0)
				model.removeRow(row);
			for(int r= row; r < table.getRowCount(); r++ )
				table.setValueAt(r, r, 0);
			
			
		}
		
		
		
		
		
	}

	protected int wrongQty(DefaultTableModel myModel) {
		// TODO Auto-generated method stub
		IntegerInput ii = new IntegerInput();
		int errorSource = -1;
		for (int r = 0; r < myModel.getRowCount(); r++)

			if (!(ii.isInteger(myModel.getValueAt(r, 3).toString()))) {
				errorSource = r;
				break;
			} else
				;

		return errorSource;
	}

	public int inCombo(JComboBox<String> jcb, String txt) {
		int i;
		int size = jcb.getItemCount();
		for (i = 2; i < size; i++)
			if (jcb.getItemAt(i).toString().compareToIgnoreCase(txt) > 0)
				return i;

		return i + 1;

	}

	public void displayGrandTotal(float prodTot, JTextField jtf, float gtot) {

		RetailInventory ri = new RetailInventory();

		int gt = (int) ri.grandTotal(prodTot, gtot);
		jtf.setText("" + gt);
		GtT.setText("" + gt);

	}

	public void DisplayOnTable(String prodRetID, String description,
			int prodRetQty, float retailPrice, float prodTot, int row) {

		proSaleTable.setValueAt(description, row, 2);
		proSaleTable.setValueAt(prodRetQty, row, 3);
		proSaleTable.setValueAt(retailPrice, row, 4);
		proSaleTable.setValueAt(prodTot, row, 5);
		JOptionPane.showMessageDialog(null, "Display Works !");
	}

	public void getProdPropDB(int prodID, int qty) {

		retailPrice = RetailInventory.getProductRetailPrice(prodRetID);
		description = RetailInventory.getProductDescription(prodRetID);
		prodTot = (int) RetailInventory.productTotal(prodRetID, prodRetQty);

	}

	public Object getCellData(int row, int col, JTable proSaleTable) {
		Object dat = proSaleTable.getValueAt(row, col);
		return dat;
	}

	public class PrintClass implements Printable, ActionListener {

		// JTable table;

		public int print(Graphics g, PageFormat pf, int page)
				throws PrinterException {

			if (page > 0) { /* We have only one page, and 'page' is zero-based */
				return NO_SUCH_PAGE;
			}

			/*
			 * User (0,0) is typically outside the imageable area, so we must
			 * translate by the X and Y values in the PageFormat to avoid
			 * clipping
			 */
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());

			/* Now we perform our rendering */

			// table = RetailWindow.proSaleTable;

			printData(table, tf, g, transID);

			/* tell the caller that this page is part of the printed document */
			return PAGE_EXISTS;
		}

		@SuppressWarnings("deprecation")
		public void printData(JTable table, JTextField tf, Graphics g,
				int transID)

		{
			String qty;
			String desc;
			String uPrice;
			String total;
			String gTot;
			g.drawString("Planetta Store", 100, 100);
			g.drawString("\nP O Box xxx\n Bambili", 100, 120);
			g.drawString("\nDate_Time:", 100, 140);

			Inventory inv = new Inventory();
			g.drawString(" " + inv.insertDateNow().toLocaleString(), 170, 140);
			g.drawString("\nOperation ID:", 100, 170);
			g.drawString(" " + transID, 170, 170);
			int rc = RetailWindow.rowCountPrint;

			g.drawString(
					"---------------------------------------------------------",
					100, 190);

			g.drawString("|" + " " + "Description", 100, 200);

			g.drawString("|" + " " + "Qty", 190, 200);
			g.drawString("|" + " " + "U.Price", 220, 200);
			g.drawString("|" + " " + "Total" + "        " + "|", 270, 200);
			g.drawString(
					"---------------------------------------------------------",
					100, 213);

			int y = 217;
			for (int i = 0; i < rc; i++) {
				desc = table.getModel().getValueAt(i, 2).toString();
				uPrice = table.getValueAt(i, 4).toString();

				qty = table.getValueAt(i, 3).toString();

				total = table.getValueAt(i, 5).toString();

				g.drawString("|" + " " + desc, 100, y);
				g.drawString("|" + " " + qty, 190, y);
				g.drawString("|" + " " + uPrice, 220, y);
				g.drawString("|" + " " + total, 270, y);
				g.drawString("      " + "|", 310, y);
				g.drawString(
						"---------------------------------------------------------",
						100, y + 10);
				y += 20;

			}
			gTot = tf.getText();
			g.drawString("Grand Total = " + gTot, 130, y + 30);
		}

		public void actionPerformed(ActionEvent e) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
			// JOptionPane.showMessageDialog(null, this.toString());
			boolean ok = job.printDialog();
			if (ok) {
				try {
					job.print();
				} catch (PrinterException ex) {
					/* The job did not successfully complete */
					JOptionPane.showMessageDialog(null,
							"Printing did not succeed. See message.");
				}
			}
		}

	}
	
	public void initialiseFields() {
		cusidT.setText(null);
		cusidT.setEditable(true);

	cusfnT.setText(null);

		cusonT.setText(null);

		cusphoneT.setText(null);

		cusaddT.setText(null);

		cusemailT.setText(null);

		cusnicT.setText(null);

	}
	public void displayAction() {
		// row = 0;

		String txtid = cusidT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Customer ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Customer ID.");
				cusidT.setText(null);
			} else {
				int cusID = Integer.parseInt(txtid);
				Customer emp = new Customer();
				if (cusID != emp.checkCusID(cusID)) {
					JOptionPane.showMessageDialog(null, "No Customer with ID: "
							+ cusID + " " + "is found on Database."
							+ "\nPlease, check ID and try again.");
					cusidT.setText("");
					cusidT.requestFocus();
				} else {
					String info[] = emp.getCusInfo(cusID);
					cusidT.setText(info[0]);

					cusfnT.setText(info[1]);

					cusonT.setText(info[2]);

					cusphoneT.setText(info[3]);

					cusaddT.setText(info[4]);

					cusemailT.setText(info[5]);

					cusnicT.setText(info[6]);
					

					cusidT.setEditable(false);
					

					// JOptionPane.showMessageDialog(null, info[1]);

				}
			}
		}

	}
}
