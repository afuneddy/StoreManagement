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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Customer;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class UpdateCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField cusIDT;
	String[][] allCus;
	static int empID2;
	private JLabel lblCustomerId;
	private JTextField fNameT;
	private JLabel lblFirstName;
	private JTextField otherNamesT;
	private JLabel lblOtherNames;
	private JTextField phoneT;
	private JLabel lblPhoneNo;
	private JTextField addressT;
	private JLabel lblAddress;
	private JTextField emailT;
	private JLabel lblEmail;
	private JTextField nicT;
	private JLabel lblNicNo;
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
	private JLabel lblGrade;
	private JSpinner gradeSpin;
	int grade;
	protected static int trackid;
	public static final int classID = 16;
	static int roleid;
	
	static int empID3; static String userName;
 private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomer frame = new UpdateCustomer(trackid,roleid, empID3, userName);
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
	 * @param empID2 
	 * @param roleid 
	 * @param empID3 
	 * @param empID3 
	 */
	public UpdateCustomer(final int trackID,int roleid, final int empID2,  String userName) {
		setTitle("Customer Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1086, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cusIDT = new JTextField();
		cusIDT.setToolTipText("Start typing name of Customer and select below");
		cusIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Customer em = new Customer();
				IntegerInput ii = new IntegerInput();
				String text = cusIDT.getText().trim().replaceAll("\\s", "");
				if (ii.isInteger(text))
					;
				else if (!(text.length() > 0)) {
					comboBox.setSelectedIndex(1);
					cusIDT.setText(null);
					cusIDT.requestFocus();
				} else

				{

					lookup = em.searchString(text);
int size=lookup.length;
if (size==0) {

		JOptionPane.showMessageDialog(null,
				"No Customer name starting with" + "\""
						+ text + "\"  " + "is found."
						+ "\nTry other names.");
		cusIDT.setText(null);
		cusIDT.requestFocus();
	

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
		cusIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		cusIDT.setColumns(10);
		cusIDT.setBounds(125, 66, 205, 23);
		contentPane.add(cusIDT);

		lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(41, 67, 102, 20);
		contentPane.add(lblCustomerId);

		fNameT = new JTextField();
		fNameT.setColumns(10);
		fNameT.setBounds(125, 136, 205, 23);
		contentPane.add(fNameT);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(41, 137, 102, 20);
		contentPane.add(lblFirstName);

		otherNamesT = new JTextField();
		otherNamesT.setColumns(10);
		otherNamesT.setBounds(423, 66, 205, 23);
		contentPane.add(otherNamesT);

		lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOtherNames.setBounds(352, 67, 82, 20);
		contentPane.add(lblOtherNames);

		phoneT = new JTextField();
		phoneT.setColumns(10);
		phoneT.setBounds(423, 102, 205, 23);
		contentPane.add(phoneT);

		lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(352, 103, 58, 20);
		contentPane.add(lblPhoneNo);

		addressT = new JTextField();
		addressT.setColumns(10);
		addressT.setBounds(423, 136, 205, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(352, 137, 58, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(730, 66, 205, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(659, 67, 51, 20);
		contentPane.add(lblEmail);

		nicT = new JTextField();
		nicT.setColumns(10);
		nicT.setBounds(730, 102, 205, 23);
		contentPane.add(nicT);

		lblNicNo = new JLabel("NIC no:");
		lblNicNo.setBounds(659, 103, 64, 20);
		contentPane.add(lblNicNo);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 11, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Update/View  Customer");
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
		btnNewButton.setBounds(899, 11, 89, 23);
		contentPane.add(btnNewButton);

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save added Customer info");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				//IsDate id = new IsDate();

				String empIDs = cusIDT.getText().trim().replaceAll("\\s", "");

				String fName = fNameT.getText().trim();
				String otherNames = otherNamesT.getText().trim();

				String phones = phoneT.getText().trim().replaceAll("\\s", "");

				String address = addressT.getText().trim();

				String email = emailT.getText().trim().replaceAll("\\s", "");

				String nics = nicT.getText().trim().replaceAll("\\s", "");

				if (!(empIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate Customer ID.");
					cusIDT.requestFocus();
				} else {
				int	cusID = Integer.parseInt(empIDs);

					if (!(fName.length() > 0)) {
						JOptionPane
								.showMessageDialog(null, "Enter first name.");
						fNameT.requestFocus();
					} else

					if (!(otherNames.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter other names.");
						otherNamesT.requestFocus();
					}

					else if (!(phones.length() >= 8)) {
						JOptionPane.showMessageDialog(null,
								"Enter phone number, atleast 8 digits.");
						phoneT.setText(null);
						phoneT.requestFocus();
					} else if (!(ii.isInteger(phones))) {
						JOptionPane.showMessageDialog(null,
								"Enter whole number values for phone number.",
								"Wrong Values", JOptionPane.ERROR_MESSAGE);
						phoneT.setText(null);
						phoneT.requestFocus();
					} else {
						int phone = Integer.parseInt(phones);
						if (!(address.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter home address.");
							addressT.requestFocus();
						}

						else if (!(email.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter email address.");
							emailT.requestFocus();
						}

						else if (!(nics.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter national identity card number.");
							nicT.requestFocus();
						} else if (!(ii.isInteger(nics))) {
							JOptionPane.showMessageDialog(null,
									"Enter whole number values NIC no.",
									"Wrong Value", JOptionPane.ERROR_MESSAGE);
							nicT.setText(null);
							nicT.requestFocus();
						} else {
							int nic = Integer.parseInt(nics);

							String gradeS = gradeSpin.getValue().toString();
							if (!(ii.isInteger(gradeS)))
								JOptionPane
										.showConfirmDialog(
												null,
												"Number between -25 and 50 needed for grade.",
												"Input Error",
												JOptionPane.ERROR_MESSAGE);
							else {
								grade = Integer.parseInt(gradeS);

								if (!(grade <= 50) && (grade >= -25))
									JOptionPane
											.showConfirmDialog(
													null,
													"Number between -25 and 50 needed for grade.",
													"Input Error",
													JOptionPane.ERROR_MESSAGE);
								else {

									Customer c = new Customer();
									if (c.updateCustomer(

									cusID,

									fName, otherNames,

									phone, address,

									email,

									nic,

									grade

									))

										JOptionPane
												.showMessageDialog(null,
														"Customer successfully updated.");

									else

										JOptionPane.showMessageDialog(null,
												"Customer  update has failed.",
												"Database Error",
												JOptionPane.ERROR_MESSAGE);
									initialiseFields();
									cusIDT.setEditable(true);

								}
							}
						}
					}

				}
			}

		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(423, 183, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();
				cusIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(572, 183, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(30, 197, 244, 31);
		contentPane.add(panel_2);

		JLabel lblCustomerTable = new JLabel("Customer Table");
		lblCustomerTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustomerTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblCustomerTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 239, 1035, 361);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Cust ID", "First Name",
				"Other Names", "Phone ", "Address", "Email", "NIC no ", "Grade" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable CustomerTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					String.class, String.class, Integer.class, String.class,
					String.class, Integer.class, Integer.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		CustomerTable.setBounds(20, 375, 862, 243);

		CustomerTable.getColumnModel().setColumnSelectionAllowed(true);
		CustomerTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		CustomerTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		CustomerTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(120);
		CustomerTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(160);
		CustomerTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(75);
		CustomerTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(110);
		CustomerTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		CustomerTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(130);
		;
		CustomerTable.setRowHeight(23);

		CustomerTable.setFillsViewportHeight(true);
		CustomerTable.setColumnSelectionAllowed(true);
		CustomerTable.setCellSelectionEnabled(true);
		CustomerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CustomerTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(CustomerTable);

		contentPane.add(scrollPane);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(100);
		comboBox.setToolTipText("Select Customer here");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							cusIDT.setText(lookup[r][0]);
							fNameT.setText(lookup[r][1]);
						}
				}

			}
		});

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.setBounds(125, 100, 205, 20);
		contentPane.add(comboBox);

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click here to view info about Customer ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(10, 102, 105, 23);
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
				Customer em = new Customer();

				;

				allCus = em.getAllCus();
				int noItems = allCus.length;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allCus[r][0],
							allCus[r][1], allCus[r][2], allCus[r][3],
							allCus[r][4], allCus[r][5], allCus[r][6],
							allCus[r][7] });

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

				Customer em = new Customer();


				allCus = em.getAllCus();
				int noItems = allCus.length;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allCus[r][0],
							allCus[r][1], allCus[r][2], allCus[r][3],
							allCus[r][4], allCus[r][5], allCus[r][6],
							allCus[r][7] });

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
						CustomerTable.print();
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

		lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(659, 137, 51, 20);
		contentPane.add(lblGrade);

		gradeSpin = new JSpinner();
		gradeSpin.setFont(new Font("Tahoma", Font.BOLD, 12));
		gradeSpin.setModel(new SpinnerNumberModel(new Long(0), new Long(-25),
				new Long(50), new Long(1)));
		gradeSpin.setBounds(730, 137, 54, 31);
		contentPane.add(gradeSpin);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID2);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(781, 11, 89, 23);
		contentPane.add(button);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { cusIDT, fNameT, otherNamesT, phoneT,
						addressT, emailT, nicT }));
	}

	public void initialiseFields() {
		cusIDT.setText(null);

		fNameT.setText(null);

		otherNamesT.setText(null);

		phoneT.setText(null);

		addressT.setText(null);

		emailT.setText(null);
		nicT.setText(null);
		gradeSpin.setValue(0);

	}

	public void displayAction() {
		// row = 0;

		String txtid = cusIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Customer ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Customer ID.");
				cusIDT.setText(null);
			} else {
				int cusID = Integer.parseInt(txtid);
				Customer emp = new Customer();
				if (cusID != emp.checkCusID(cusID)) {
					JOptionPane.showMessageDialog(null, "No Customer with ID: "
							+ cusID + " " + "is found on Database."
							+ "\nPlease, check ID and try again.");
					cusIDT.setText("");
					cusIDT.requestFocus();
				} else {
					String info[] = emp.getCusInfo(cusID);
					cusIDT.setText(info[0]);

					fNameT.setText(info[1]);

					otherNamesT.setText(info[2]);

					phoneT.setText(info[3]);

					addressT.setText(info[4]);

					emailT.setText(info[5]);

					nicT.setText(info[6]);
					gradeSpin.setValue(Integer.parseInt(info[7]));

					cusIDT.setEditable(false);
					infoFieldsEditable(true);

					// JOptionPane.showMessageDialog(null, info[1]);

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

		fNameT.setEditable(flag);

		otherNamesT.setEditable(flag);

		phoneT.setEditable(flag);

		addressT.setEditable(flag);

		emailT.setEditable(flag);

		nicT.setEditable(flag);

	}
}
