package interfaceClass;

import java.awt.Dimension;
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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import planettaStore.Inventory;
import planettaStore.RetailInventory;
import planettaStore.Transaction;

public class NewRetWin {

	/**
	 * <h4>Main Retail Window</h4>
	 * 
	 * @author Afundoh Edward
	 * @version 1.0
	 */

	public static int rowCountPrint;
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
	private JFrame frame;
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
	private JTextField amtPayabTB;
	public float gtot;
	Graphics g;
	public static JComboBox<String> retDescCom;
	public JPopupMenu retDescPMenu;
	private JTextField retDescTB;
	private JTextField rowQtyT;
	private JTextField rowT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewRetWin window = new NewRetWin();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewRetWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Retail Window");
		frame.setBounds(100, 100, 1255, 857);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProduct = new JLabel("Product Code");
		lblProduct.setBounds(46, 83, 86, 14);
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

			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Object.class,
					String.class, Integer.class, Integer.class, Float.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		proCodeName = new JTextField();
		proCodeName.setBounds(46, 103, 86, 20);

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
		btnValidate.setBounds(180, 605, 71, 23);
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction t = new Transaction();
				int amount = Integer.parseInt(grandTotTB.getText().trim()
						.replaceAll("\\s", ""));
				String custName = "customer";
				int empID = 1;
				int custID = 0;
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

					if (selected.equals("") || selected.equals("--Choose--"))
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

			}

		});

		retDescCom.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));

		retDescCom.setBounds(379, 103, 134, 20);
		retDescCom.setToolTipText("product name");
		retDescCom.setAutoscrolls(true);
		retDescCom.setSelectedIndex(1);

		frame.getContentPane().add(retDescCom);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(0, 251, 83, 14);
		frame.getContentPane().add(lblProductName);
		/*
		 * JScrollPane scrollPane = new JScrollPane(); scrollPane.setBounds(265,
		 * 613, 311, -217); frame.getContentPane().add(scrollPane);
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 181, 955, 312);
		frame.getContentPane().add(scrollPane_1);

		proSaleTable.setFillsViewportHeight(true);
		proSaleTable.setColumnSelectionAllowed(true);
		proSaleTable.setCellSelectionEnabled(true);

		proSaleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proSaleTable.setSurrendersFocusOnKeystroke(true);

		scrollPane_1.setViewportView(proSaleTable);

		grandTotTB = new JTextField();
		grandTotTB.setEditable(false);
		grandTotTB.setBounds(412, 556, 86, 20);
		frame.getContentPane().add(grandTotTB);
		grandTotTB.setColumns(10);

		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGrandTotal.setBounds(303, 558, 99, 14);
		frame.getContentPane().add(lblGrandTotal);

		JLabel lblAmountPayable = new JLabel("Amount Payable");
		lblAmountPayable.setBounds(313, 590, 89, 14);
		frame.getContentPane().add(lblAmountPayable);

		amtPayabTB = new JTextField();
		amtPayabTB.setEditable(false);
		amtPayabTB.setBounds(412, 587, 86, 20);
		frame.getContentPane().add(amtPayabTB);
		amtPayabTB.setColumns(10);
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
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int rc = myModel.getRowCount();
				for (int r = rc - 1; r >= 0; r--)

					myModel.removeRow(r);
				grandTotTB.setText(null);

				row = 0;

			}
		});
		btnCancel.setBounds(530, 605, 89, 23);
		frame.getContentPane().add(btnCancel);

		retDescPMenu = new JPopupMenu();
		retDescPMenu.setPopupSize(new Dimension(0, 0));
		retDescPMenu.setEnabled(true);
	
		retDescPMenu.setBounds(0, 0, 200, 28);
		frame.getContentPane().add(retDescPMenu);

		JComboBox<String> comboBox = new JComboBox<String>();

		comboBox.setBounds(303, 74, 28, 20);
		frame.getContentPane().add(comboBox);

		retDescTB = new JTextField();
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

					for (int r = 0; r < lu.length; r++) {

						if (retDescCom.getItemCount() > 2)
							retDescCom.setSelectedIndex(0);
						else
							retDescCom.setSelectedIndex(1);
						if (!(lu[r][0].length() > 0))

							break;

						else {
							// JOptionPane.showMessageDialog(null,
							// "retDescCom ="
							// + retDescCom.getItemAt(r));
							String cmBoxItem = lu[r][0] + " - " + lu[r][1];
							// int index = inCombo(retDescCom, cmBoxItem);

							// if (index < retDescCom.getItemCount())

							// retDescCom.insertItemAt(cmBoxItem, index);
							// else
							retDescCom.addItem(cmBoxItem);

						}
					}

				}
				// retDescCom.setSelectedIndex(0);
			}
		});
		retDescTB.setBounds(379, 81, 134, 20);
		frame.getContentPane().add(retDescTB);
		retDescTB.setColumns(10);
		
		rowQtyT = new JTextField();
		rowQtyT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String rows = rowT.getText().trim().replaceAll("\\s", "");
				String rowQtys = rowQtyT.getText().trim().replaceAll("\\s", "");
				
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
								rowQtyT.setText(null);
							} 
							else 
						
						
							{
								
								int rowQty = Integer.parseInt(rowQtys);
						
						
					
				
				updateRowValues(row, rowQty, proSaleTable , grandTotTB, myModel );
							}}
						}}
				
			}
		});
		rowQtyT.setBounds(581, 160, 38, 20);
		frame.getContentPane().add(rowQtyT);
		rowQtyT.setColumns(10);
		
		rowT = new JTextField();
		rowT.setColumns(10);
		rowT.setBounds(303, 150, 38, 20);
		frame.getContentPane().add(rowT);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(585, 135, 46, 14);
		frame.getContentPane().add(lblQty);
		
		JLabel lblRow = new JLabel("Row");
		lblRow.setBounds(303, 125, 46, 14);
		frame.getContentPane().add(lblRow);

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
			int rc = NewRetWin.rowCountPrint;

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

	/*private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}*/
}
