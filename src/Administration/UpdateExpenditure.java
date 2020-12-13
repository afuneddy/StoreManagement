
package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;
import interfaceClass.IsTime;

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
import java.sql.Time;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Employee;
import planettaStore.Expenditure;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class UpdateExpenditure extends JFrame {
	String[][] lookup;
	JComboBox<String> comboBox;
	private JPanel contentPane;
	private JTextField expIDT;
	private JLabel lblExpenditureId;
	private JTextField empIDT;
	private JLabel empidlb;
	private JTextField amountTB;
	private JLabel amountlb;
	private JTextField targetTB;
	private JLabel target;
	private JTextArea reasonTB;
	private JLabel reasonlb;
	private JTextField dateT;
	private JLabel lbldates;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField timeT;
	private JTextField fNameT;
	private JLabel lblEmployeeName;
	//private JTable table;
	private JPanel panel_1;
	private JLabel lblExpenditureTable;
	private String[][] allExp;
	private JButton btnShowDetails;
	protected static int trackid;
	public static final int classID = 19;
	static int roleid;
	static int empID; static String userName;
 private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateExpenditure frame = new UpdateExpenditure(trackid,roleid, empID, userName);
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
	public UpdateExpenditure(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Expenditure Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		expIDT = new JTextField();
		expIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();

			}
		});
		expIDT.setColumns(10);
		expIDT.setBounds(216, 102, 94, 23);
		contentPane.add(expIDT);

		lblExpenditureId = new JLabel("Expenditure ID:");
		lblExpenditureId.setBounds(129, 103, 89, 20);
		contentPane.add(lblExpenditureId);

		empIDT = new JTextField();
		empIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Employee em = new Employee();
				IntegerInput ii = new IntegerInput();
				String text = empIDT.getText().trim().replaceAll("\\s", "");
				if (ii.isInteger(text))
					;
				else if (!(text.length() > 0)) {
					comboBox.setSelectedIndex(1);
					empIDT.setText(null);
					empIDT.requestFocus();
				} else

				{

					lookup = em.searchString(text);
int size =lookup.length;

if (size == 0) {
	
		JOptionPane.showMessageDialog(null,
				"No employee name starting with" + "\""
						+ text + "\"  " + "is found."
						+ "\nTry other names.");
		empIDT.setText(null);
		empIDT.requestFocus();
	

} else
					for (int r = 0; r < lookup.length; r++) {

						
							comboBox.setSelectedIndex(0);
					 {

							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1];
							
							comboBox.addItem(cmBoxItem);
						}

					}

				}
			}
		});
		empIDT.setToolTipText("Start typing name  or business name of supplier and select frm he list below.");
		empIDT.setColumns(10);
		empIDT.setBounds(216, 150, 205, 23);
		contentPane.add(empIDT);

		empidlb = new JLabel("Employee ID:");
		empidlb.setBounds(129, 167, 79, 20);
		contentPane.add(empidlb);

		amountTB = new JTextField();
		amountTB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				targetTB.setText(amountTB.getText());

			}
		});
		amountTB.setColumns(10);
		amountTB.setBounds(532, 137, 172, 23);
		contentPane.add(amountTB);

		amountlb = new JLabel("Amount:");
		amountlb.setBounds(450, 139, 72, 20);
		contentPane.add(amountlb);

		targetTB = new JTextField();
		targetTB.setColumns(10);
		targetTB.setBounds(535, 184, 172, 23);
		contentPane.add(targetTB);

		target = new JLabel("Target Name:");
		target.setBounds(450, 184, 100, 20);
		contentPane.add(target);

		reasonTB = new JTextArea();
		reasonTB.setToolTipText("purpose of expenditure");
		reasonTB.setWrapStyleWord(true);
		reasonTB.setRows(4);
		reasonTB.setLineWrap(true);
		reasonTB.setColumns(10);
		reasonTB.setBounds(816, 86, 240, 53);
		contentPane.add(reasonTB);

		reasonlb = new JLabel("Reason:");
		reasonlb.setBounds(717, 92, 59, 20);
		contentPane.add(reasonlb);

		dateT = new JTextField();
		dateT.setColumns(10);
		dateT.setBounds(816, 150, 133, 23);
		contentPane.add(dateT);

		lbldates = new JLabel("Expenditure Date:");
		lbldates.setBounds(714, 151, 108, 20);
		contentPane.add(lbldates);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(25, 22, 295, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("View/ Update Expenditure ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(39, 11, 168, 14);
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
		btnNewButton.setBounds(913, 11, 89, 23);
		contentPane.add(btnNewButton);

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save only if you have changed some values");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				IsDate id = new IsDate();
				String expIDs = expIDT.getText().trim().replaceAll("\\s", "");

				String empIDs = empIDT.getText().trim().replaceAll("\\s", "");
				String amounts = amountTB.getText().trim()
						.replaceAll("\\s", "");

				String target = targetTB.getText().trim();

				String reason = reasonTB.getText().trim();

				String fName = fNameT.getText().trim();
				String dates = dateT.getText().trim().replaceAll("\\s", "");
				String times = timeT.getText().trim().replaceAll("\\s", "");

				if (!(expIDs.length() > 0)) {
					JOptionPane.showMessageDialog(null,
							"Generate Expenditure ID.");
					expIDT.requestFocus();
				} else {
					int expID = Integer.parseInt(expIDs);

					if (!(empIDs.length() > 0)) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter supplier ID.\n Hints: Start typing first Name \nof Employee and choose from list.");
						empIDT.requestFocus();
					} else if (!(ii.isInteger(empIDs))) {
						JOptionPane.showMessageDialog(null,
								"Enter whole number values for employee ID.",
								"Wrong Values", JOptionPane.ERROR_MESSAGE);
						empIDT.setText(null);
						empIDT.requestFocus();
					} else {
						Employee e = new Employee();
						int empID = Integer.parseInt(empIDs);
						if (!(e.checkEmpID(empID) == empID)) {
							JOptionPane.showMessageDialog(null,
									"No Employee has been found with this ID: "
											+ empID);
							empIDT.requestFocus();
							empIDT.setText(null);
						} else if (!(fName.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter employee first name.");
							fNameT.requestFocus();
						} else if (!(amounts.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter cost of Expenditure.");
							amountTB.requestFocus();
						} else if (!(ii.isInteger(amounts))) {
							JOptionPane
									.showMessageDialog(
											null,
											"Enter monetary value for expenditure cost.",
											"Wrong Values",
											JOptionPane.ERROR_MESSAGE);
							amountTB.setText(null);
							amountTB.requestFocus();
						} else {
							int amount = Integer.parseInt(amounts);
							if (!(target.length() > 0)) {
								JOptionPane
										.showMessageDialog(null,
												"Enter name of entity to which the expenditure is made (target name).");
								targetTB.setText(null);
								targetTB.requestFocus();

							} else if (!(reason.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter purpose of expenditure.");
								reasonTB.requestFocus();

							}

							else if (!(dates.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter Expenditure date.");
								dateT.requestFocus();
							} else if (!(id.isDate(dates))) {
								JOptionPane
										.showMessageDialog(null,
												"Enter Expenditure date in this format: yyyy-mm-dd");
								dateT.requestFocus();
								dateT.setText(null);
							} else {
								Date date = Date.valueOf(dates);
								IsTime it = new IsTime();
								if (!(times.length() > 0)) {
									JOptionPane.showMessageDialog(null,
											"Enter time.");
									timeT.requestFocus();
								} else if (!(it.isTime(times))) {
									JOptionPane
											.showMessageDialog(null,
													"Enter Expenditure time in this format: HH:MM:SS");
									timeT.requestFocus();
									timeT.setText(null);
								} else {
									Time time = Time.valueOf(times);

									Expenditure em = new Expenditure();

									if (em.updateExpenditure(

									expID, empID, amount, target, reason, date,
											time

									))

										JOptionPane
												.showMessageDialog(null,
														"Expenditure successfully registered.");

									else

										JOptionPane
												.showMessageDialog(
														null,
														"Expenditure  registration has failed.",
														"Database Error",
														JOptionPane.ERROR_MESSAGE);
									initialiseFields();
									expIDT.setEditable(true);
								}
							}
						}

					}
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(482, 240, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel to obtain empty fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expIDT.setEditable(true);
				initialiseFields();

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(656, 240, 89, 23);
		contentPane.add(btnCancel);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(500);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							empIDT.setText(lookup[r][0]);
							fNameT.setText(lookup[r][1]);
						}
				}
			}
		});
		comboBox.setBounds(216, 184, 205, 20);
		contentPane.add(comboBox);

		timeT = new JTextField();
		timeT.setText((String) null);
		timeT.setColumns(10);
		timeT.setBounds(816, 184, 133, 23);
		contentPane.add(timeT);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(717, 185, 59, 20);
		contentPane.add(lblTime);

		fNameT = new JTextField();
		fNameT.setText((String) null);
		fNameT.setColumns(10);
		fNameT.setBounds(532, 102, 172, 23);
		contentPane.add(fNameT);

		lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(431, 103, 100, 20);
		contentPane.add(lblEmployeeName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 289, 1035, 310);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Exp ID", "Emp ID",
				"First Name", "Amount ", "Target's Name", "Purpose", "Date",
				"Time" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable ExpenditureTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					Integer.class, String.class, Integer.class, String.class,
					String.class, Date.class, Date.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		ExpenditureTable.setBounds(20, 375, 862, 243);

		ExpenditureTable.getColumnModel().setColumnSelectionAllowed(true);

		ExpenditureTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(38);
		ExpenditureTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(50);
		ExpenditureTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(50);

		ExpenditureTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(100);
		ExpenditureTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(120);

		ExpenditureTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(80);
		ExpenditureTable.getTableHeader().getColumnModel().getColumn(8)
				.setPreferredWidth(50);

		ExpenditureTable.setRowHeight(23);

		ExpenditureTable.setFillsViewportHeight(true);
		ExpenditureTable.setColumnSelectionAllowed(true);
		ExpenditureTable.setCellSelectionEnabled(true);
		ExpenditureTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ExpenditureTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(ExpenditureTable);

		contentPane.add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(25, 244, 199, 45);
		contentPane.add(panel_1);

		lblExpenditureTable = new JLabel("Expenditure Table");
		lblExpenditureTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblExpenditureTable.setBounds(49, 11, 168, 14);
		panel_1.add(lblExpenditureTable);

		JButton btnPopulateTable = new JButton("Populate Table");
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
				Expenditure ex = new Expenditure();
				Employee em = new Employee();
				
				allExp = ex.getAllExp();

				
				int noItems = allExp .length;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(
							r,
							new Object[] {
									r + 1,
									allExp[r][0],
									allExp[r][1],
									em.getEmpFName(Integer
											.parseInt(allExp[r][1])),
									allExp[r][2], allExp[r][3],

									allExp[r][4], allExp[r][5], allExp[r][6], });

				}
				tableUpdateAckn(flag);

			}
		});
		btnPopulateTable.setBounds(148, 625, 125, 23);
		contentPane.add(btnPopulateTable);

		JButton btnUpdateTable = new JButton("Update Table");
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
				Expenditure ex = new Expenditure();
				Employee em = new Employee();
allExp = ex.getAllExp();

				
				int noItems = allExp .length;
				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(
							r,
							new Object[] {
									r + 1,
									allExp[r][0],
									allExp[r][1],
									em.getEmpFName(Integer
											.parseInt(allExp[r][1])),
									allExp[r][2], allExp[r][3],

									allExp[r][4], allExp[r][5], allExp[r][6], });

				}
				tableUpdateAckn(flag);

			}
		});
		btnUpdateTable.setBounds(340, 625, 125, 23);
		contentPane.add(btnUpdateTable);

		JButton btnCloseTable = new JButton("Close Table");
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
		btnCloseTable.setBounds(528, 625, 125, 23);
		contentPane.add(btnCloseTable);

		JButton btnPrintTable = new JButton("Print Table");
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
						ExpenditureTable.print();
					} catch (PrinterException e1) { // TODO Auto-generated catch
													// block
						JOptionPane.showMessageDialog(null,
								"Printing Error occurred. Details:\n " + e1);
					}
				}

			}
		});
		btnPrintTable.setBounds(733, 625, 125, 23);
		contentPane.add(btnPrintTable);

		btnShowDetails = new JButton("Show Details");
		btnShowDetails
				.setToolTipText("Click to display data relative to expenditure ID");
		btnShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnShowDetails.setBounds(317, 102, 108, 23);
		contentPane.add(btnShowDetails);

		final JToggleButton tglbtnNewToggleButton = new JToggleButton(
				"Editable Fields");
		tglbtnNewToggleButton
				.setToolTipText("Render fields  ready to receive data");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tglbtnNewToggleButton.isSelected() == true)
					infoFieldsEditable(true);
				else
					infoFieldsEditable(false);

			}
		});
		tglbtnNewToggleButton.setBounds(816, 241, 141, 23);
		contentPane.add(tglbtnNewToggleButton);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(798, 11, 89, 23);
		contentPane.add(button);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { expIDT, empIDT, fNameT, amountTB, targetTB,
						reasonTB, dateT, timeT }));
		initialiseFields();
		infoFieldsEditable(false);
	}

	public void initialiseFields() {
		expIDT.setText(null);

		empIDT.setText(null);
		fNameT.setText(null);
		amountTB.setText(null);

		targetTB.setText(null);

		reasonTB.setText(null);
		timeT.setText(null);
		dateT.setText(null);
		comboBox.setSelectedIndex(1);

	}

	public void displayAction() {
		// row = 0;
		int expID;
		String txtid = expIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Expenditure ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane
						.showMessageDialog(null,
								"Please provide whole number value for Expenditure ID.");
				expIDT.setText(null);
				expIDT.requestFocus();
			} else {

				expID = Integer.parseInt(txtid);
				Expenditure e = new Expenditure();
				if (expID != e.checkExpID(expID)) {
					JOptionPane.showMessageDialog(null,
							"No Expenditure with ID: " + expID + " "
									+ "is found on Database."
									+ "\nPlease, check ID and try again.");
					expIDT.setText("");

					expIDT.requestFocus();
				} else {

					String info[] = e.getPurInfo(expID);

					expIDT.setText(info[0]);

					empIDT.setText(info[1]);
					Employee em = new Employee();
					fNameT.setText(em.getEmpFName(Integer.parseInt(info[1])));
					amountTB.setText(info[2]);
					targetTB.setText(info[3]);
					reasonTB.setText(info[4]);
					dateT.setText(info[5]);
					timeT.setText(info[6]);

					expIDT.setEditable(false);
					infoFieldsEditable(true);

				}
			}
		}

	}

	private void infoFieldsEditable(boolean flag) {

		empIDT.setEditable(flag);
		fNameT.setEditable(flag);
		amountTB.setEditable(flag);

		targetTB.setEditable(flag);

		reasonTB.setEditable(flag);
		timeT.setEditable(flag);
		dateT.setEditable(flag);

	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	}
}