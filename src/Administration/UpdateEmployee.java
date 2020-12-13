package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;

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
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import access.EmployeeAcc;

import planettaStore.Employee;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class UpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField empIDT;
	String[][] allEmp;
	int empID2;
	private JLabel label;
	private JTextField fNameT;
	private JLabel lblFirstName;
	private JTextField otherNamesT;
	private JLabel lblOtherNames;
	private JTextField userNameT;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField phoneT;
	private JLabel lblPhoneNo;
	private JTextField addressT;
	private JLabel lblAddress;
	private JTextField emailT;
	private JLabel lblEmail;
	private JTextField birthDateT;
	private JLabel lblBirthD;
	private JTextField BirthPlaceT;
	private JLabel lblBirthPlace;
	private JTextField nicT;
	private JLabel lblNicNo;
	private JLabel lblRole;
	private JTextField salaryT;
	private JLabel lblSalary;
	private JTextField recruitDateT;
	private JLabel lblRecruitmentDate;
	private JPasswordField passwordT;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnSave;
	private JButton btnCancel;
	private JComboBox<String> comboBox;
	private JComboBox<String> roleCombo;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	private JTextField loginStatusT;
	public static final int classID = 17;
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
					UpdateEmployee frame = new UpdateEmployee(trackID, roleid, empID, userName);
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
	 * @param empID3 
	 */
	public UpdateEmployee(final int trackID, int roleid, final int empID,  String userName) {
		setTitle("Employee Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1086, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		empIDT = new JTextField();
		empIDT.setToolTipText("Start typing name of employee and select below");
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
						
						 

							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1];
						
							comboBox.addItem(cmBoxItem);
						

					}

				}

			}
		});
		empIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		empIDT.setColumns(10);
		empIDT.setBounds(125, 81, 205, 23);
		contentPane.add(empIDT);

		label = new JLabel("Employee ID:");
		label.setBounds(41, 82, 102, 20);
		contentPane.add(label);

		fNameT = new JTextField();
		fNameT.setColumns(10);
		fNameT.setBounds(125, 136, 205, 23);
		contentPane.add(fNameT);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(41, 137, 102, 20);
		contentPane.add(lblFirstName);

		otherNamesT = new JTextField();
		otherNamesT.setColumns(10);
		otherNamesT.setBounds(125, 170, 205, 23);
		contentPane.add(otherNamesT);

		lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setBounds(41, 170, 102, 20);
		contentPane.add(lblOtherNames);

		userNameT = new JTextField();
		userNameT.setColumns(10);
		userNameT.setBounds(125, 204, 205, 23);
		contentPane.add(userNameT);

		lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(41, 205, 102, 20);
		contentPane.add(lblUserName);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 238, 102, 20);
		contentPane.add(lblPassword);

		phoneT = new JTextField();
		phoneT.setColumns(10);
		phoneT.setBounds(416, 102, 205, 23);
		contentPane.add(phoneT);

		lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(349, 100, 102, 20);
		contentPane.add(lblPhoneNo);

		addressT = new JTextField();
		addressT.setColumns(10);
		addressT.setBounds(416, 136, 205, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(349, 134, 102, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(416, 169, 205, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(349, 167, 102, 20);
		contentPane.add(lblEmail);

		birthDateT = new JTextField();
		birthDateT.setColumns(10);
		birthDateT.setBounds(416, 206, 205, 23);
		contentPane.add(birthDateT);

		lblBirthD = new JLabel("Birth Date:");
		lblBirthD.setBounds(349, 204, 102, 20);
		contentPane.add(lblBirthD);

		BirthPlaceT = new JTextField();
		BirthPlaceT.setColumns(10);
		BirthPlaceT.setBounds(416, 240, 205, 23);
		contentPane.add(BirthPlaceT);

		lblBirthPlace = new JLabel("Birth Place:");
		lblBirthPlace.setBounds(349, 238, 102, 20);
		contentPane.add(lblBirthPlace);

		nicT = new JTextField();
		nicT.setColumns(10);
		nicT.setBounds(730, 102, 205, 23);
		contentPane.add(nicT);

		lblNicNo = new JLabel("NIC no:");
		lblNicNo.setBounds(629, 105, 102, 20);
		contentPane.add(lblNicNo);

		lblRole = new JLabel("Role :");
		lblRole.setBounds(629, 139, 102, 20);
		contentPane.add(lblRole);

		salaryT = new JTextField();
		salaryT.setColumns(10);
		salaryT.setBounds(730, 170, 205, 23);
		contentPane.add(salaryT);

		lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(629, 171, 102, 20);
		contentPane.add(lblSalary);

		recruitDateT = new JTextField();
		recruitDateT.setColumns(10);
		recruitDateT.setBounds(730, 204, 205, 23);
		contentPane.add(recruitDateT);

		lblRecruitmentDate = new JLabel("Recruitment Date:");
		lblRecruitmentDate.setBounds(629, 205, 102, 20);
		contentPane.add(lblRecruitmentDate);

		passwordT = new JPasswordField();
		passwordT.setBounds(125, 238, 205, 20);
		contentPane.add(passwordT);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Update/View  Employee");
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
		btnSave.setToolTipText("Save added employee info");
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				IsDate id = new IsDate();

				String empIDs = empIDT.getText().trim().replaceAll("\\s", "");

				String fName = fNameT.getText().trim();
				String otherNames = otherNamesT.getText().trim();

				String userName = userNameT.getText().trim()
						.replaceAll("\\s", "");

				String phones = phoneT.getText().trim().replaceAll("\\s", "");

				String address = addressT.getText().trim();

				String email = emailT.getText().trim().replaceAll("\\s", "");

				String birthDates = birthDateT.getText().trim()
						.replaceAll("\\s", "");

				String birthPlace = BirthPlaceT.getText().trim();

				String nics = nicT.getText().trim().replaceAll("\\s", "");

				String salarys = salaryT.getText().trim().replaceAll("\\s", "");

				String recruitDates = recruitDateT.getText().trim()
						.replaceAll("\\s", "");

				String password = passwordT.getText();

				if (!(empIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate employee ID.");
					empIDT.requestFocus();
				} else {
					empID2 = Integer.parseInt(empIDs);

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

					else if (!(userName.length() > 0)) {
						JOptionPane.showMessageDialog(null, "Enter user name.");
						userNameT.requestFocus();
					}
	else{EmployeeAcc ea= new EmployeeAcc();
					
					if ((userName.equals(ea.checkUserName(userName)))) {
						JOptionPane.showMessageDialog(null, "Some one already has the user name you provided.\n Change it.");
						userNameT.requestFocus();
					}

					else if (!(password.length() > 0)) {
						JOptionPane.showMessageDialog(null, "Enter password.");
						passwordT.requestFocus();
					} else if (!(phones.length() >= 8)) {
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

						else if (!(birthDates.length() > 0)) {
							JOptionPane.showMessageDialog(null,
									"Enter date of birth.");
							birthDateT.requestFocus();
						} else if (!(id.isDate(birthDates))) {
							JOptionPane
									.showMessageDialog(
											null,
											"Use correct date format \"birth date\" : yyyy-mm-dd",
											"Wrong Date format",
											JOptionPane.ERROR_MESSAGE);
							birthDateT.setText(null);
							birthDateT.requestFocus();
						} else {
							Date birthDate = Date.valueOf(birthDates);
							if (!(birthPlace.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter place of birth.");
								BirthPlaceT.requestFocus();
							} else if (!(nics.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter national identity card number.");
								nicT.requestFocus();
							} else if (!(ii.isInteger(nics))) {
								JOptionPane.showMessageDialog(null,
										"Enter whole number values NIC no.",
										"Wrong Value",
										JOptionPane.ERROR_MESSAGE);
								nicT.setText(null);
								nicT.requestFocus();
							} else {
								int nic = Integer.parseInt(nics);
								if (roleCombo.getSelectedItem() == "--Choose--")
									JOptionPane.showMessageDialog(null,
											"Choose employee role.");
								else if(roleCombo.getSelectedItem() == null)
									JOptionPane.showMessageDialog(null,
											"Employee role not found.");
								else {int roleid=0;
									Employee e= new Employee();
									String[][] all = e.populateCombo();
									String  selected = roleCombo.getSelectedItem().toString();
									for (int r = 0; r < all.length; r++)
										if (selected
												.equals(all[r][1])) {
											roleid = Integer.parseInt(all[r][0]);
											break;
										} 
										
										
									if (!(salarys.length() > 0)) {
										JOptionPane.showMessageDialog(null,
												"Enter salary.");
										salaryT.requestFocus();
									} else if (!(ii.isInteger(salarys))) {
										JOptionPane
												.showMessageDialog(
														null,
														"Enter monetary value for salary.",
														"Wrong Value",
														JOptionPane.ERROR_MESSAGE);
										salaryT.setText(null);
										salaryT.requestFocus();
									}

									else {
										int salary = Integer.parseInt(salarys);
										if (!(recruitDates.length() > 0)) {
											JOptionPane.showMessageDialog(null,
													"Enter date of birth.");
											recruitDateT.requestFocus();
										} else if (!(id.isDate(recruitDates))) {
											JOptionPane
													.showMessageDialog(
															null,
															"Use correct date format for \"recruitment date\" : yyyy-mm-dd",
															"Wrong Date format",
															JOptionPane.ERROR_MESSAGE);
											recruitDateT.setText(null);
											recruitDateT.requestFocus();
										}

										else {
											Date recruitDate = Date
													.valueOf(birthDates);

											Employee em = new Employee();
											if (em.updateEmployee(

											empID2,

											fName, otherNames,

											userName,

											phone, address,

											email,

											birthDate,

											birthPlace,

											nic, 
											roleid, 
											salary,

											recruitDate,

											password

											))

												JOptionPane
														.showMessageDialog(
																null,
																"Employee successfully updated.");

											else

												JOptionPane
														.showMessageDialog(
																null,
																"Employee  update has failed.",
																"Database Error",
																JOptionPane.ERROR_MESSAGE);
											initialiseFields();
											empIDT.setEditable(true);

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
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(717, 266, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();
				empIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(883, 266, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(25, 270, 244, 31);
		contentPane.add(panel_2);

		JLabel lblEmployeeTable = new JLabel("Employee Table");
		lblEmployeeTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmployeeTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblEmployeeTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 300, 1035, 300);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Emp ID", "First Name",
				"Other Names", "User Name", "Password", "Phone ", "Address","Email",
				"Birth Date", "Birth Place", "NIC no ", "Role ", "Salary",
				"Recruitm Date","LoggedIn" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable employeeTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unused", "rawtypes" })
			Class[] columnTypes = new Class[] { Integer.class, String.class,
					String.class, String.class, String.class, Integer.class,
					String.class, String.class,String.class, Date.class, String.class,
					Integer.class, String.class,String.class, String.class, Date.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		employeeTable.setBounds(20, 375, 862, 243);

		employeeTable.getColumnModel().setColumnSelectionAllowed(true);
		employeeTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		employeeTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		employeeTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(100);
		employeeTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(120);
		employeeTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(75);
		employeeTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(75);
		employeeTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		employeeTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(135);
		employeeTable.getTableHeader().getColumnModel().getColumn(11)
				.setPreferredWidth(90);
		employeeTable.setRowHeight(23);

		employeeTable.setFillsViewportHeight(true);
		employeeTable.setColumnSelectionAllowed(true);
		employeeTable.setCellSelectionEnabled(true);
		employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(employeeTable);

		contentPane.add(scrollPane);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(500);
		comboBox.setToolTipText("Select employee here");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - "
										+ lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							empIDT.setText(lookup[r][0]);
							fNameT.setText(lookup[r][1]);
						}
				}

			}
		});

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.setBounds(125, 105, 205, 20);
		contentPane.add(comboBox);

		roleCombo = new JComboBox<String>();
		roleCombo.setMaximumRowCount(50);
		roleCombo.setBounds(730, 137, 205, 20);
		contentPane.add(roleCombo);
		
		
		Employee emp = new Employee();
		String[][] items = emp.populateCombo();
		//JOptionPane.showMessageDialog(null, items[1][1]);
		if(items.length > 0){
			roleCombo.insertItemAt("--Choose--", 0);
			roleCombo.setSelectedIndex(0);
		for (int i = 1; i <= items.length; i++)
			roleCombo.insertItemAt(items[i-1][1], i);
		}

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click here to view info about employee ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(10, 102, 105, 23);
		contentPane.add(btnDisplayInfo);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { empIDT, fNameT, otherNamesT, userNameT,
						phoneT, addressT, emailT, birthDateT, BirthPlaceT,
						nicT, salaryT, recruitDateT, passwordT }));

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
				Employee em = new Employee();

				allEmp = em.getAllEmp();
				int noRetItems = allEmp.length;//em.getEmpCount();

				for (int r = 0; r < noRetItems; r++) {


					String text;
					if(Integer.parseInt(allEmp[r][14]) == 1)
						{text = "True";}
					else text = "False";
					
					
					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1], allEmp[r][2], allEmp[r][3],
							allEmp[r][4], allEmp[r][5], allEmp[r][6],
							allEmp[r][7], allEmp[r][8], allEmp[r][9],
							allEmp[r][10], allEmp[r][11], allEmp[r][12],
							allEmp[r][13], text });

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

				Employee em = new Employee();
allEmp = em.getAllEmp();
				int noRetItems = allEmp.length;//em.getEmpCount();

			



				for (int r = 0; r < noRetItems; r++) {

					String text;
					if(Integer.parseInt(allEmp[r][14]) == 1)
						{text = "True";}
					else text = "False";
					
					
					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1], allEmp[r][2], allEmp[r][3],
							allEmp[r][4], allEmp[r][5], allEmp[r][6],
							allEmp[r][7], allEmp[r][8], allEmp[r][9],
							allEmp[r][10], allEmp[r][11], allEmp[r][12],
							allEmp[r][13], text });
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
						employeeTable.print();
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
		
		loginStatusT = new JTextField();
		loginStatusT.setColumns(10);
		loginStatusT.setBounds(732, 235, 205, 23);
		contentPane.add(loginStatusT);
		
		JLabel lblLoginStatus = new JLabel("Login Status:");
		lblLoginStatus.setBounds(631, 236, 102, 20);
		contentPane.add(lblLoginStatus);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(786, 11, 89, 23);
		contentPane.add(button);
	}

	public void initialiseFields() {
		empIDT.setText(null);

		fNameT.setText(null);

		otherNamesT.setText(null);

		userNameT.setText(null);

		phoneT.setText(null);

		addressT.setText(null);

		emailT.setText(null);

		birthDateT.setText(null);

		BirthPlaceT.setText(null);

		nicT.setText(null);

		salaryT.setText(null);

		recruitDateT.setText(null);
		passwordT.setText(null);
		loginStatusT.setText(null);

	}

	public void displayAction() {
		// row = 0;

		String txtid = empIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter employee ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for employee ID.");
				empIDT.setText(null);
			} else {
				empID = Integer.parseInt(txtid);
				Employee emp = new Employee();
				if (empID != emp.checkEmpID(empID)) {
					JOptionPane.showMessageDialog(null, "No employee with ID: "
							+ empID + " " + "is found on Database."
							+ "\nPlease, check ID and try again.");
					empIDT.setText("");
					empIDT.requestFocus();
				} else {
					String info[] = emp.getEmpInfo(empID);
					empIDT.setText(info[0]);

					fNameT.setText(info[1]);

					otherNamesT.setText(info[2]);

					userNameT.setText(info[3]);
					passwordT.setText(info[4]);
					phoneT.setText(info[5]);

					addressT.setText(info[6]);

					emailT.setText(info[7]);

					birthDateT.setText(info[8]);

					BirthPlaceT.setText(info[9]);

					nicT.setText(info[10]);
					
					roleCombo.removeAllItems();
					
					String[][] items = emp.populateCombo();
					if(items.length > 0){
						roleCombo.insertItemAt("--Choose--", 0);
					for (int i = 1; i < items.length; i++)
						roleCombo.insertItemAt(items[i][1], i);
					}
					roleCombo.setSelectedItem(info[11]);
					salaryT.setText(info[12]);

					recruitDateT.setText(info[13]);
					String text;
					if(Integer.parseInt(info[14]) == 1)
						{text = "True";}
					else text = "False";
					loginStatusT.setText(text);

					empIDT.setEditable(false);
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

		userNameT.setEditable(flag);

		phoneT.setEditable(flag);

		addressT.setEditable(flag);

		emailT.setEditable(flag);

		birthDateT.setEditable(flag);

		BirthPlaceT.setEditable(flag);

		nicT.setEditable(flag);

		salaryT.setEditable(flag);

		recruitDateT.setEditable(flag);
		passwordT.setEditable(flag);
	}
}
