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

import planettaStore.Employee;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField empIDT;
	String[][] allEmp;
	int empID;
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
	private JButton btnCancel;
	private JComboBox<String> comboBox;
	private JComboBox<String> roleCombo;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	protected static int trackid;
	public static final int classID = 23;
	static int roleid;
	static int empID2; static String userName;
 private JButton button;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmployee frame = new ViewEmployee(trackid,roleid, empID2, userName);
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
	 */
	public ViewEmployee(final int trackID,int roleid, final int empID2,  String userName) {
		setTitle("View Employee");
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
int size = lookup.length;

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

		lblNewLabel = new JLabel("View  Employee");
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
		btnCancel.setBounds(882, 251, 89, 23);
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
				"Other Names", "User Name", "Password", "Phone ", "Address",
				"Email","Birth Date", "Birth Place", "NIC no ", "Role ", "Salary",
				"Recruitm Date" };

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
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, String.class,
					String.class, String.class, String.class, Integer.class,
					String.class, String.class,String.class, Date.class, String.class,
					Integer.class, String.class, String.class, Date.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false };

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
				.setPreferredWidth(120);
		employeeTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(160);
		employeeTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(75);
		employeeTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(75);
		employeeTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		employeeTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(165);
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
		comboBox.setToolTipText("Select employee here");
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

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.setBounds(125, 105, 205, 20);
		contentPane.add(comboBox);

		roleCombo = new JComboBox<String>();
		roleCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--", "Cashier", "Delivery", "Manager", "Owner", "Retail_Attendant", "Sales_person", "Warehouse_Attendant"}));
		roleCombo.setBounds(730, 137, 205, 20);
		contentPane.add(roleCombo);

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
				int noItems = allEmp.length;

				;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1], allEmp[r][2], allEmp[r][3],
							allEmp[r][4], allEmp[r][5], allEmp[r][6],
							allEmp[r][7], allEmp[r][8], allEmp[r][9],
							allEmp[r][10], allEmp[r][11], allEmp[r][12],
							allEmp[r][13], allEmp[r][14]});

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
				int noItems = allEmp.length;

				;

				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1], allEmp[r][2], allEmp[r][3],
							allEmp[r][4], allEmp[r][5], allEmp[r][6],
							allEmp[r][7], allEmp[r][8], allEmp[r][9],
							allEmp[r][10], allEmp[r][11], allEmp[r][12],
							allEmp[r][13], allEmp[r][14]});
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
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID2);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(783, 11, 89, 23);
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
					String[][] items = emp.populateCombo();
					for (int i = 0; i < items.length; i++)
						roleCombo.insertItemAt(items[i][0] + "_"+items[i][1]  , i);

					roleCombo.setSelectedItem(info[11]);
					salaryT.setText(info[12]);

					recruitDateT.setText(info[13]);

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
