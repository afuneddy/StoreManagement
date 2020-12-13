package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Date;
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
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Purchase;
import planettaStore.Supplier;
import security.SecurityAcc;

public class PurchaseUpdate extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -1103167316668406410L;
	private JPanel contentPane;
	private JTextField purIDT;
	String[][] allPurch;
	int supID;
	private JLabel purchL;
	private JTextField supIDT;
	private JLabel supidl;
	private JTextField tCostT;
	private JLabel tcostl;
	private JTextField dueDateT;
	private JLabel dueDatel;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnSave;
	private JButton btnCancel;
	private JComboBox<String> comboBox;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	int grade;
	private JTextField purchDateT;
	private JLabel lblPhoneLine;
	private JTextField advanceRepT;
	private JLabel AdvanceL;
	private JTextField supBzNameT;
	int purID;
	private JTextField timeT;
	private JLabel timel;
	private JLabel lblTotalPayment;
	private JTextField advanceT;
	JTextField bzNameT;
	protected static int trackid;
	public static final int classID = 7;
	static int roleid;
	static int empID; static String userName;
 private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseUpdate frame = new PurchaseUpdate(trackid, roleid, empID, userName);
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
	public PurchaseUpdate(final int trackID,int roleid, final int empID,  String userName) {
		setForeground(Color.BLUE);
		setTitle("Update or View Purchase");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1012, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		purIDT = new JTextField();
		purIDT.setToolTipText("Start typing name of Purchase and select below");
		purIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

			}
		});
		purIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		purIDT.setColumns(10);
		purIDT.setBounds(125, 66, 119, 23);
		contentPane.add(purIDT);

		purchL = new JLabel("Purchase ID:");
		purchL.setBounds(40, 67, 75, 20);
		contentPane.add(purchL);

		supIDT = new JTextField();
		supIDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				/*
				 * String txtid = supIDT.getText().trim().replaceAll("\\s", "");
				 * if (txtid.length() > 0)
				 * 
				 * { IntegerInput ii = new IntegerInput(); if
				 * (!(ii.isInteger(txtid))) { JOptionPane
				 * .showMessageDialog(null,
				 * "Please provide whole number value for Supplier ID.");
				 * supIDT.setText(null); } else {
				 * 
				 * supID = Integer.parseInt(txtid); Supplier sup = new
				 * Supplier(); if (supID != sup.getSupID(supID)) { JOptionPane
				 * .showMessageDialog( null, "No Supplier with ID: " + supID +
				 * " " + "is found on Database." +
				 * "\nPlease, check ID and try again."); supIDT.setText("");
				 * 
				 * supIDT.requestFocus(); } else { Purchase p = new Purchase();
				 * 
				 * bzNameT.setText(p.getBzName(supID));
				 * 
				 * } } }
				 */
			}
		});
		supIDT.setColumns(10);
		supIDT.setBounds(125, 171, 125, 23);
		contentPane.add(supIDT);

		supidl = new JLabel("Supplier ID");
		supidl.setBounds(26, 172, 74, 20);
		contentPane.add(supidl);

		tCostT = new JTextField();
		tCostT.setColumns(10);
		tCostT.setBounds(481, 66, 205, 23);
		contentPane.add(tCostT);

		tcostl = new JLabel("Total Cost:");
		tcostl.setBounds(389, 67, 82, 20);
		contentPane.add(tcostl);

		dueDateT = new JTextField();
		dueDateT.setColumns(10);
		dueDateT.setBounds(483, 173, 205, 23);
		contentPane.add(dueDateT);

		dueDatel = new JLabel("Due Date:");
		dueDatel.setBounds(389, 174, 82, 20);
		contentPane.add(dueDatel);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 11, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Update/View  Purchase");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(109, 11, 168, 14);
		panel.add(lblNewLabel);

		btnNewButton = new JButton("HELP ?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Help not yet available. Sorry.");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(859, 11, 89, 23);
		contentPane.add(btnNewButton);

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save added Purchase info");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				Supplier s = new Supplier();
				IsDate id = new IsDate();
				Purchase p = new Purchase();

				String purIDs = purIDT.getText().trim().replaceAll("\\s", "");

				String supIDs = supIDT.getText().trim().replaceAll("\\s", "");
				String tCosts = tCostT.getText().trim().replaceAll("\\s", "");
				String advances = advanceRepT.getText().trim()
						.replaceAll("\\s", "");
				String dueDates = dueDateT.getText().trim()
						.replaceAll("\\s", "");
				String dates = purchDateT.getText().trim()
						.replaceAll("\\s", "");

				if (!(purIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null,
									"Enter Purchase ID.");
					purIDT.requestFocus();
				} else if (!(ii.isInteger(purIDs))) {
					JOptionPane.showMessageDialog(null,
							"Enter whole number value for purchase ID.",
							"Wrong Values", JOptionPane.ERROR_MESSAGE);
					purIDT.setText(null);
					purIDT.requestFocus();
				} else {
					int purID = Integer.parseInt(purIDs);

					if (purID != p.checkPurchID(purID)) {
						JOptionPane.showMessageDialog(null,
								"No Purchase with ID: " + purID + " "
										+ "is found on Database."
										+ "\nPlease, check ID and try again.");
						purIDT.setText("");

						purIDT.requestFocus();
					}

					if (!(supIDs.length() > 0)) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter Supplier ID.\nHints: Start typing name or business name of supplier and choose from list below it.");
						bzNameT.requestFocus();
					} else if (!(ii.isInteger(supIDs))) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter whole number value for Supplier ID or search and select from list.",
										"Wrong Values",
										JOptionPane.ERROR_MESSAGE);
						supIDT.setText(null);
						supIDT.requestFocus();
					} else {
						int supID = Integer.parseInt(supIDs);
						if (supID != s.getSupID(supID)) {
							JOptionPane
									.showMessageDialog(
											null,
											"No Supplier with ID: "
													+ supID
													+ " "
													+ "is found on Database."
													+ "\nPlease, check ID and try again.");
							supIDT.setText("");

							supIDT.requestFocus();
						} else if (!(tCosts.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter total cost.");
							tCostT.requestFocus();
						} else if (!(ii.isInteger(tCosts))) {
							JOptionPane.showMessageDialog(null,
									"Enter monetary value for total cost.",
									"Wrong Values", JOptionPane.ERROR_MESSAGE);
							tCostT.setText(null);
							tCostT.requestFocus();
						} else {
							int tCost = Integer.parseInt(tCosts);
							if (!(advances.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter repay amount.");
								advanceRepT.setText(null);
								advanceRepT.requestFocus();
							} else if (!(ii.isInteger(advances))) {
								JOptionPane
										.showMessageDialog(
												null,
												"Enter monetary value for repay amount.",
												"Wrong Values",
												JOptionPane.ERROR_MESSAGE);
								advanceRepT.setText(null);
								advanceRepT.requestFocus();
							} else {
								int advance = Integer.parseInt(advances);
								if (!(dueDates.length() > 0)) {
									JOptionPane
											.showMessageDialog(null,
													"Enter deadline to complete payment.");
									dueDateT.setText(null);
									dueDateT.requestFocus();
								} else if (!(id.isDate(dueDates))) {
									JOptionPane
											.showMessageDialog(
													null,
													"Use the right date format: yyyy-mm-dd",
													"Wrong Values",
													JOptionPane.ERROR_MESSAGE);
									dueDateT.setText(null);
									dueDateT.requestFocus();
								}

								else {
									Date dueDate = Date.valueOf(dueDates);
									if (!(dates.length() > 0)) {

										JOptionPane.showMessageDialog(null,
												"Enter purchase date.");
										purchDateT.setText(null);
										purchDateT.requestFocus();
									} else if (!(id.isDate(dates))) {
										JOptionPane
												.showMessageDialog(
														null,
														"Use the right date format: yyyy-mm-dd",
														"Wrong Values",
														JOptionPane.ERROR_MESSAGE);
										purchDateT.setText(null);
										purchDateT.requestFocus();
									} else {
										Date date = Date.valueOf(dates);

										Purchase c = new Purchase();

										if (c.updatePurchase(purID, supID,

										advance, tCost, dueDate, date))

											JOptionPane
													.showMessageDialog(null,
															"Purchase successfully updated.");

										else

											JOptionPane
													.showMessageDialog(
															null,
															"Purchase  update has failed.",
															"Database Error",
															JOptionPane.ERROR_MESSAGE);
										initialiseFields();
										purIDT.setEditable(true);

									}
								}
							}
						}
					}
				}
			}

		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(765, 207, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();
				purIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(765, 135, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(26, 252, 244, 31);
		contentPane.add(panel_2);

		JLabel lblPurchaseTable = new JLabel("Purchase Table");
		lblPurchaseTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPurchaseTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblPurchaseTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 294, 941, 306);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Purch ID", "Sup ID",
				"Bz Name", "Total Cost", "Tot Advance", "Balance Owed",
				"dueDate", "PurchaseDate", "Reg Time " };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable PurchaseTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					Integer.class, String.class, Integer.class, Integer.class,
					Integer.class, String.class, String.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		PurchaseTable.setBounds(20, 375, 862, 243);

		PurchaseTable.getColumnModel().setColumnSelectionAllowed(true);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(75);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(150);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(120);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(80);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		PurchaseTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(100);

		PurchaseTable.setRowHeight(23);

		PurchaseTable.setFillsViewportHeight(true);
		PurchaseTable.setColumnSelectionAllowed(true);
		PurchaseTable.setCellSelectionEnabled(true);
		PurchaseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PurchaseTable.setSurrendersFocusOnKeystroke(true);
		// scrollPane.setColumnHeaderView(PurchaseTable);
		scrollPane.setViewportView(PurchaseTable);
		contentPane.add(scrollPane);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(1000);
		comboBox.setToolTipText("Select Supplier here");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1] + " - "
										+ lookup[r][2])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							supIDT.setText(lookup[r][0]);
							supBzNameT.setText(lookup[r][1]);
							// supBzNameT.setText(lookup[r][2]);
						}
				}

			}
		});

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--", ""}));
		comboBox.setBounds(125, 137, 205, 20);
		contentPane.add(comboBox);

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click here to view info about Purchase ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(254, 66, 105, 23);
		contentPane.add(btnDisplayInfo);

		comboBox.setSelectedIndex(1);

		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable.addActionListener(new ActionListener() {
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
				Purchase p = new Purchase();

				int noItems = 0;//p.getPurCount();

				allPurch = p.getAllPur();
				noItems=allPurch.length;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(
							r,
							new Object[] {
									r + 1,
									allPurch[r][0],
									allPurch[r][1],
									allPurch[r][7],
									allPurch[r][2],
									allPurch[r][3],
									Integer.parseInt(allPurch[r][2])
											- Integer.parseInt(allPurch[r][3]),
									allPurch[r][4], allPurch[r][5],
									allPurch[r][6] });

				}
				tableUpdateAckn(flag);

			}
		});
		btnPopulateTable.setBounds(87, 637, 118, 23);
		contentPane.add(btnPopulateTable);

		btnUpdateTable = new JButton("Update Table");
		btnUpdateTable.addActionListener(new ActionListener() {
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

				Purchase em = new Purchase();

				int noItems = 0;//em.getPurCount();

				allPurch = em.getAllPur();
			noItems = allPurch.length;


				NumberFormat format = NumberFormat.getNumberInstance();
				
				format.setMaximumFractionDigits(1);
				format.setGroupingUsed(true);
				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(
							r,
							new Object[] {
									r + 1,
									allPurch[r][0],
									allPurch[r][1],
									allPurch[r][7],
									format.format(Float.parseFloat(allPurch[r][2])),
									format.format(Float.parseFloat(allPurch[r][3])),
									format.format(Float.parseFloat(allPurch[r][2])
											- Float.parseFloat(allPurch[r][3])),
									allPurch[r][4], allPurch[r][5],
									allPurch[r][6], });

				}
				tableUpdateAckn(flag);

			}
		});
		btnUpdateTable.setBounds(263, 637, 118, 23);
		contentPane.add(btnUpdateTable);

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
		btnCloseTable.setBounds(437, 637, 118, 23);
		contentPane.add(btnCloseTable);

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
						PurchaseTable.print();
					} catch (PrinterException e1) { // TODO Auto-generated catch
													// block
						JOptionPane.showMessageDialog(null,
								"Printing Error occurred:\n " + e1);
					}
				}

			}
		});
		btnPrintTable.setBounds(602, 637, 118, 23);
		contentPane.add(btnPrintTable);

		purchDateT = new JTextField();
		purchDateT.setColumns(10);
		purchDateT.setBounds(483, 207, 205, 23);
		contentPane.add(purchDateT);

		lblPhoneLine = new JLabel("Purchase Date:");
		lblPhoneLine.setBounds(389, 210, 94, 20);
		contentPane.add(lblPhoneLine);

		advanceRepT = new JTextField();
		advanceRepT.setColumns(10);
		advanceRepT.setBounds(483, 134, 205, 23);
		contentPane.add(advanceRepT);

		AdvanceL = new JLabel("Repay Amount:");
		AdvanceL.setBounds(389, 141, 94, 14);
		contentPane.add(AdvanceL);

		final JToggleButton tglbtnSetFieldsEditable = new JToggleButton(
				"Set Fields Editable");
		tglbtnSetFieldsEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tglbtnSetFieldsEditable.isSelected() == true)
					infoFieldsEditable(true);
				else
					infoFieldsEditable(false);

			}
		});
		tglbtnSetFieldsEditable.setBounds(765, 99, 139, 23);
		contentPane.add(tglbtnSetFieldsEditable);

		supBzNameT = new JTextField();
		supBzNameT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Purchase em = new Purchase();
				IntegerInput ii = new IntegerInput();
				String text = supBzNameT.getText().trim().replaceAll("\\s", "");
				if (ii.isInteger(text))
					;
				else if (!(text.length() > 0)) {
					comboBox.setSelectedIndex(1);
					supBzNameT.setText(null);
					supBzNameT.requestFocus();
				} else

				{

					lookup = em.searchString(text);
int size = lookup.length;
if (!(size == 0)) {
	
		JOptionPane.showMessageDialog(null,
				"No Supplier business Name or name starting with"
						+ "\"" + text + "\"  "
						+ "is found."
						+ "\nTry other names.");
		supBzNameT.setText(null);
		supBzNameT.requestFocus();
	}
else
					for (int r = 0; r < size; r++) {

				
							comboBox.setSelectedIndex(0);
						

							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1] + " - " + lookup[r][2];

							comboBox.addItem(cmBoxItem);
						

					}

				}

			}
		});
		supBzNameT.setColumns(10);
		supBzNameT.setBounds(125, 104, 205, 23);
		contentPane.add(supBzNameT);

		JLabel supbznamel = new JLabel("Supplier bzName:");
		supbznamel.setHorizontalAlignment(SwingConstants.RIGHT);
		supbznamel.setBounds(0, 105, 105, 20);
		contentPane.add(supbznamel);

		timeT = new JTextField();
		timeT.setEditable(false);
		timeT.setColumns(10);
		timeT.setBounds(125, 208, 125, 23);
		contentPane.add(timeT);

		timel = new JLabel("Registration Time:");
		timel.setBounds(26, 209, 105, 20);
		contentPane.add(timel);

		lblTotalPayment = new JLabel("Total Payment:");
		lblTotalPayment.setBounds(389, 103, 94, 14);
		contentPane.add(lblTotalPayment);

		advanceT = new JTextField();
		advanceT.setColumns(10);
		advanceT.setBounds(481, 100, 205, 23);
		contentPane.add(advanceT);

		JButton btnGetTdebt = new JButton("Get T-Debt");
		btnGetTdebt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Purchase p = new Purchase();

				int id = 0;
				String ids = supIDT.getText().trim().replaceAll("\\s", "");

				IntegerInput ii = new IntegerInput();

				if (!(ids.length() > 0)) {
					JOptionPane.showMessageDialog(null, "Enter supplier ID.");
					supIDT.requestFocus();
					supIDT.setText(null);
				} else if (!(ii.isInteger(ids))) {
					JOptionPane
							.showMessageDialog(
									null,
									"Enter whole number value for Supplier ID or select from list.",
									"Wrong Values", JOptionPane.ERROR_MESSAGE);
					supIDT.setText(null);
					supIDT.requestFocus();
				} else {
					id = Integer.parseInt(ids);
					Supplier s = new Supplier();
					if((s.getSupID(id) != id) || (id ==0) ){
						JOptionPane.showMessageDialog(null,"No supplier with ID: "+id+" is found in the system.\nReview and try again.");
					supIDT.setText(null);supIDT.requestFocus();}else

					JOptionPane.showMessageDialog(null,
							"Total amount owed  \"" + p.getBzName(id)
									+ "\" : CFAF " + p.getTotDebt(id),
							"Total Debt", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGetTdebt
				.setToolTipText("Total amount owed to Supplier with ID  in Supplier ID box");
		btnGetTdebt.setBounds(254, 171, 112, 23);
		contentPane.add(btnGetTdebt);
		
		btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(743, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { purIDT, supIDT, tCostT, dueDateT }));
		infoFieldsEditable(false);
	}

	public void initialiseFields() {
		purIDT.setText(null);

		supIDT.setText(null);
		comboBox.setSelectedIndex(1);
		tCostT.setText(null);
		advanceRepT.setText(null);
		dueDateT.setText(null);
		purchDateT.setText(null);
		advanceT.setText(null);
		timeT.setText(null);
		supBzNameT.setText(null);
	}

	public void displayAction() {
		// row = 0;

		String txtid = purIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Purchase ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Purchase ID.");
				purIDT.setText(null);
			} else {

				purID = Integer.parseInt(txtid);
				Purchase p = new Purchase();
				if (purID != p.checkPurchID(purID)) {
					JOptionPane.showMessageDialog(null, "No Purchase with ID: "
							+ purID + " " + "is found on Database."
							+ "\nPlease, check ID and try again.");
					purIDT.setText("");

					purIDT.requestFocus();
				} else {

					String info[] = p.getPurInfo(purID);

					purIDT.setText(info[0]);

					supIDT.setText(info[1]);

					tCostT.setText(info[2]);
					advanceT.setText(info[3]);
					dueDateT.setText(info[4]);
					purchDateT.setText(info[5]);
					timeT.setText(info[6]);
					supBzNameT.setText(info[7]);

					purIDT.setEditable(false);
					advanceT.setEditable(false);
					infoFieldsEditable(true);

				}
			}
		}

	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	}

	public void infoFieldsEditable(boolean flag) {

		tCostT.setEditable(flag);

		dueDateT.setEditable(flag);
		purchDateT.setEditable(flag);
		//advanceT.setEditable(flag);

	}
}
