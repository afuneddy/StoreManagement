package interfaceClass;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import planettaStore.Inventory;
import planettaStore.Transaction;
import planettaStore.WHouseInventory;
import security.SecurityAcc;

import java.awt.Color;

public class WholesaleWindow extends JFrame {
	/**
	 * @author Afundoh Edward Launch the application.
	 * @version 1.0
	 */

	private static final long serialVersionUID = 1L;
	public static final int classID = 2;
	public static int rowCountPrint;
	static int prodID;
	int l;
	int WSPrice;
	String description;
	String location;
	int limit;
	int row;
	int prodWSQty;
	String transType = "Wholesale_Paid";
	float prodTot;
	JFrame frame;
	JComboBox<String> wsUnitCom;
	String wsUnit;
	public TableModelListener tml;
	int No = 0;
	JTable table;
	JTextField tf;
	int oldQty, newQty;
	Object columns;
	private static JTextField grandTotT;
	//private JTextField amtPayabTB;
	public float gtot;
	Graphics g;

	private JPanel contentPane;
	private JTextField prodIDT;
	//private JTable table_1;
	static int roleid;
	static int empID; static String userName;
	static int trackID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WholesaleWindow frame = new WholesaleWindow(trackID,roleid, empID, userName);
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
	public WholesaleWindow(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Wholesale Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 897, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(31, 96, 687, 389);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 39, 333, 279);
		panel.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "Pr.Desc", "ID", "Description", "Qty",
				"U.Price", "Total" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 * @author Afundoh Edward
		 */

		final JTable WSaleTable = new JTable(myModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			 
			 

			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Object.class,
					String.class, Integer.class, Integer.class, Integer.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		WSaleTable.setFillsViewportHeight(true);
		WSaleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		WSaleTable.setColumnSelectionAllowed(true);
		WSaleTable.setCellSelectionEnabled(true);

		scrollPane.setViewportView(WSaleTable);

		prodIDT = new JTextField();

		prodIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(false);
				
				row = 0;
				wsUnit = wsUnitCom.getSelectedItem().toString();
				String txtid;
				if (wsUnit.equals("--Choose--"))
					JOptionPane
							.showMessageDialog(null,
									"First choose wholesale selling price type, then enter product ID");
				else if (!((txtid = prodIDT.getText()).length() > 0))

					JOptionPane.showMessageDialog(null, "Enter Product Code");

				else {
					IntegerInput ii = new IntegerInput();
					if (!(ii.isInteger(txtid))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for Product code.");
						prodIDT.setText(null);
					} else {
						prodID = Integer.parseInt(txtid);
						WHouseInventory whi = new WHouseInventory();
						if (prodID != whi.checkProdId(prodID))
							JOptionPane
									.showMessageDialog(
											null,
											"No product with ID:  "
													+ prodID
													+ " "
													+ "is found on Database."
													+ "\nPlease, check code and try again.");
						else {
							prodWSQty = 1;

							int rc = WSaleTable.getRowCount();
							if (rc == 0) {

								getProdPropDB(prodID, prodWSQty, wsUnit);

								myModel.insertRow(row, new Object[] { wsUnit,
										prodID, description, prodWSQty,
										WSPrice, 
										format.format(prodTot) });
								displayGrandTotal(prodTot, grandTotT, gtot);

							}

							else

							{
								WHouseInventory i = new WHouseInventory();

								int key = i.searchProdIdTable(prodID,wsUnit,
										WSaleTable);
								// check if product is
								// already on the sale table

								/**
								 * @param prodID
								 *            Unique identify for each type of
								 *            product in the warehouse
								 * 
								 * @param WSaleTable
								 *            Product display table for
								 *            wholesale items
								 */

								if (key != -1) {
									// Key = -1, product is not found on table.
									// Else it is there
									String retq = myModel.getValueAt(key, 3)
											.toString();
									int rq = Integer.parseInt(retq); // Instead
																		// of
																		// inserting
																		// new
																		// row,
																		// increase
																		// quantity
																		// by
																		// one.

									prodWSQty = rq + 1;

									getProdPropDB(prodID, prodWSQty, wsUnit);
									// set new quantity
									WSaleTable.setValueAt(prodWSQty, key, 3);
									// set new total
									WSaleTable.setValueAt(format.format(prodTot), key, 5);

									String olTot = grandTotT.getText();
									Float oldTot = Float.parseFloat(olTot);
									// set new grand total.
									displayGrandTotal(WSPrice, grandTotT,
											oldTot);

								} else

								{
									getProdPropDB(prodID, prodWSQty, wsUnit);
									String olgt = grandTotT.getText();
									Float newGtot = Float.parseFloat(olgt);
									myModel.insertRow(row, new Object[] { wsUnit,
											prodID, description, prodWSQty,
											WSPrice, format.format(prodTot)  });
									displayGrandTotal(prodTot, grandTotT,
											newGtot);

								}
							}
						}
					}
				}
				prodIDT.setText(null);
				// row++; }

			}
		});

		prodIDT.setBounds(23, 73, 86, 20);
		panel.add(prodIDT);
		prodIDT.setColumns(10);

		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setBounds(23, 50, 99, 24);
		panel.add(lblNewLabel);

		JLabel lblProduct = new JLabel("Item Description");
		lblProduct.setBounds(23, 110, 86, 24);
		panel.add(lblProduct);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(119, 110, 86, 20);
		panel.add(comboBox);

		grandTotT = new JTextField();
		grandTotT.setEditable(false);
		grandTotT.setBounds(401, 338, 86, 20);
		panel.add(grandTotT);
		grandTotT.setColumns(10);

		@SuppressWarnings("serial")
		Action action = new AbstractAction() {
			/**
			 * 
			 */

			public void actionPerformed(ActionEvent e) {
				int newQty;
				
NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(false);
				IntegerInput ii = new IntegerInput();
			
				TableCellListener tcl = (TableCellListener) e.getSource();
				int column = tcl.getColumn();

				if (column == 3) {
					int ro = tcl.getRow();

					Object olQty = tcl.getOldValue();
					String oQty = olQty.toString();
					int oldQty = Integer.parseInt(oQty);

					String nQty = (String) WSaleTable.getValueAt(ro, 3);
					
					if (!(ii.isInteger(nQty))) {
						JOptionPane.showMessageDialog(null,
								"Please provide whole number value for new quantity at row: "
										+ ro, "Error!", JOptionPane.ERROR_MESSAGE);

						myModel.setValueAt(oldQty, ro, 3);
						newQty = oldQty;
					} 
					else
					
					newQty = Integer.parseInt(nQty);

					int retailPrice = (int) WSaleTable.getValueAt(ro, 4);
					
					String olGT = grandTotT.getText();
					Float oldGT = Float.parseFloat(olGT);
					Inventory i = new Inventory();
					int oldTot = i.prodTot(retailPrice, oldQty);
					int prodTot = i.prodTot(retailPrice, newQty);
					Float gtot = i.grandTotMinusPTot(oldGT, oldTot);

					WSaleTable.setValueAt(format.format(prodTot), ro, 5);

					displayGrandTotal(prodTot, grandTotT, gtot);
					if (newQty == 0)
						myModel.removeRow(ro);
				}
			}
		};

		new TableCellListener(WSaleTable, action);

		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
					
					Transaction t = new Transaction();
					int amount = Integer.parseInt(grandTotT.getText().trim()
							.replaceAll("\\s", ""));
					String custName = "customer";
					int empID = 1;
				int custID = 0;
					int transID = t.getTransID();
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
						grandTotT.setText(null);

						row = 0;
					} else {
						WSaleTable.getTableHeader().requestFocus();
						if (myModel.getRowCount() == 0)
							JOptionPane
									.showMessageDialog(null,
											"Wholesale table empty. Thus, no action is taken.");

						else {

							int prodID = 0;
							int qty = 0;
						WHouseInventory whi = new WHouseInventory();

							int rc = myModel.getRowCount();

						//	int grandTot = Integer.parseInt(grandTotT.getText());
							// registerTrans(grandTot, )
							for (int i = 0; i < rc; i++) {
String wsUnit = WSaleTable.getValueAt(i, 0)
.toString();
								prodID = (int) WSaleTable.getValueAt(i, 1);
								String wsDesc = WSaleTable.getValueAt(i, 2)
										.toString();
								String sqty = WSaleTable.getValueAt(i, 3)
										.toString();
								String swsPrice = WSaleTable.getValueAt(i, 4)
										.toString();
								String stot = WSaleTable.getValueAt(i, 5)
										.toString();
								int wsPrice = Integer.parseInt(swsPrice);
								Float tot = Float.parseFloat(stot);

								qty = Integer.parseInt(sqty);
								whi.saleFinalisation(prodID, transID, wsUnit, wsDesc,  qty,
										wsPrice, tot);

					
							}
							JOptionPane
									.showMessageDialog(null,
											"Transaction successful.\nClick 'OK' for receipt to print.");
					
				
					table = WSaleTable;
					tf = grandTotT;
					PageFormat pf = new PageFormat();
					PrintClass p = new PrintClass();
					try {
						p.print(g, pf, 1);
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// btnValidate.addActionListene(new Print());

					rowCountPrint = myModel.getRowCount();

					p.actionPerformed(e);

					for (int r = rc - 1; r >= 0; r--)

						myModel.removeRow(r);
					grandTotT.setText(null);

					row = 0;

							}}
			}

		});
		btnValidate.setBounds(182, 355, 110, 23);
		panel.add(btnValidate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rc = myModel.getRowCount();
				for (int r = rc - 1; r >= 0; r--)

					myModel.removeRow(r);
				grandTotT.setText(null);

				row = 0;

			}
		});
		btnCancel.setBounds(576, 355, 89, 23);
		panel.add(btnCancel);

		wsUnitCom = new JComboBox<String>();
		wsUnitCom.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "LwuSp", "HwuSp" }));
		wsUnitCom.setSelectedIndex(0);
		wsUnitCom.setBounds(23, 35, 110, 20);
		panel.add(wsUnitCom);

		JButton btnInvoice = new JButton("Print Invoice");
		btnInvoice.setBounds(0, 355, 109, 23);
		panel.add(btnInvoice);

		JLabel lblChooseWholesaleUnits = new JLabel("Choose Wholesale units");
		lblChooseWholesaleUnits.setBounds(23, 11, 160, 14);
		panel.add(lblChooseWholesaleUnits);
		
		JButton button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(116, 11, 175, 41);
		contentPane.add(button);

	}



	protected void displayGrandTotal(float prodTot, JTextField jtf, float gtot) {
		//NumberFormat format = NumberFormat.getNumberInstance();
		
		WHouseInventory whi = new WHouseInventory();

		int gt = (int) whi.grandTotal(prodTot, gtot);
		jtf.setText("" + gt);
	}

	public void getProdPropDB(int prodID, int qty, String wsUnit) {

		WHouseInventory whi = new WHouseInventory();

		WSPrice = whi.getWholesalePrice(prodID, wsUnit);
		description = whi.getDescription(prodID, wsUnit);
		prodTot = (int) whi.productTotal(prodID, prodWSQty, wsUnit);

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

			printData(table, tf, g);

			/* tell the caller that this page is part of the printed document */
			return PAGE_EXISTS;
		}

		public void printData(JTable table, JTextField tf, Graphics g)

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
			g.drawString(" " + inv.insertDateNow(), 170, 140);
			JOptionPane.showMessageDialog(null, inv.insertDateNow());
			int rc = WholesaleWindow.rowCountPrint;

			g.drawString(
					"---------------------------------------------------------",
					100, 160);

			g.drawString("|" + " " + "Description", 100, 170);

			g.drawString("|" + " " + "Qty", 190, 170);
			g.drawString("|" + " " + "U.Price", 220, 170);
			g.drawString("|" + " " + "Total" + "        " + "|", 270, 170);
			g.drawString(
					"---------------------------------------------------------",
					100, 183);

			int y = 200;
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
				}
			}
		}

	}
}
