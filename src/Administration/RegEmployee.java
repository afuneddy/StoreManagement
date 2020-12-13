package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import access.EmployeeAcc;

import planettaStore.Employee;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class RegEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField empIDT;
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
	JComboBox<String> roleCombo;
	private JTextField recruitDateT;
	private JLabel lblRecruitmentDate;
	private JPasswordField passwordT;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton empIDGenB;
	private JButton btnSave;
	private JButton btnCancel;
	protected static int trackid;
	public static final int classID = 9;
	static int roleid;
	static int empID; static String userName;
 private JButton btnLogOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegEmployee frame = new RegEmployee(trackid,roleid, empID, userName);
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
	public RegEmployee(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Employee Registration                                                              Developer: Afundoh Edward_ email: afuneddy@gmail.com");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		empIDT = new JTextField();
		empIDT.setEditable(false);
		empIDT.setColumns(10);
		empIDT.setBounds(216, 102, 205, 23);
		contentPane.add(empIDT);

		label = new JLabel("Employee ID:");
		label.setBounds(115, 103, 102, 20);
		contentPane.add(label);

		fNameT = new JTextField();
		fNameT.setColumns(10);
		fNameT.setBounds(216, 136, 205, 23);
		contentPane.add(fNameT);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(115, 139, 102, 20);
		contentPane.add(lblFirstName);

		otherNamesT = new JTextField();
		otherNamesT.setColumns(10);
		otherNamesT.setBounds(216, 181, 205, 23);
		contentPane.add(otherNamesT);

		lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setBounds(115, 184, 102, 20);
		contentPane.add(lblOtherNames);

		userNameT = new JTextField();
		userNameT.setColumns(10);
		userNameT.setBounds(216, 220, 205, 23);
		contentPane.add(userNameT);

		lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(115, 223, 102, 20);
		contentPane.add(lblUserName);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(115, 257, 102, 20);
		contentPane.add(lblPassword);

		phoneT = new JTextField();
		phoneT.setColumns(10);
		phoneT.setBounds(216, 288, 205, 23);
		contentPane.add(phoneT);

		lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(115, 291, 102, 20);
		contentPane.add(lblPhoneNo);

		addressT = new JTextField();
		addressT.setColumns(10);
		addressT.setBounds(216, 322, 205, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(115, 325, 102, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(216, 355, 205, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(115, 358, 102, 20);
		contentPane.add(lblEmail);

		birthDateT = new JTextField();
		birthDateT.setColumns(10);
		birthDateT.setBounds(216, 392, 205, 23);
		contentPane.add(birthDateT);

		lblBirthD = new JLabel("Birth Date:");
		lblBirthD.setBounds(115, 395, 102, 20);
		contentPane.add(lblBirthD);

		BirthPlaceT = new JTextField();
		BirthPlaceT.setColumns(10);
		BirthPlaceT.setBounds(216, 426, 205, 23);
		contentPane.add(BirthPlaceT);

		lblBirthPlace = new JLabel("Birth Place:");
		lblBirthPlace.setBounds(115, 429, 102, 20);
		contentPane.add(lblBirthPlace);

		nicT = new JTextField();
		nicT.setColumns(10);
		nicT.setBounds(216, 460, 205, 23);
		contentPane.add(nicT);

		lblNicNo = new JLabel("NIC no:");
		lblNicNo.setBounds(115, 463, 102, 20);
		contentPane.add(lblNicNo);

		lblRole = new JLabel("Role :");
		lblRole.setBounds(115, 497, 102, 20);
		contentPane.add(lblRole);

		salaryT = new JTextField();
		salaryT.setColumns(10);
		salaryT.setBounds(216, 535, 205, 23);
		contentPane.add(salaryT);

		lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(115, 538, 102, 20);
		contentPane.add(lblSalary);

		recruitDateT = new JTextField();
		recruitDateT.setColumns(10);
		recruitDateT.setBounds(216, 575, 205, 23);
		contentPane.add(recruitDateT);

		lblRecruitmentDate = new JLabel("Recruitment Date:");
		lblRecruitmentDate.setBounds(115, 578, 102, 20);
		contentPane.add(lblRecruitmentDate);

		passwordT = new JPasswordField();
		passwordT.setBounds(216, 254, 205, 20);
		contentPane.add(passwordT);

		roleCombo = new JComboBox<String>();
		roleCombo.setBounds(216, 497, 205, 20);
		contentPane.add(roleCombo);
		Employee emp = new Employee();
		String[][] items = emp.populateCombo();
		//JOptionPane.showMessageDialog(null, items[1][1]);
		if(items.length > 0){
			roleCombo.insertItemAt("--Choose--", 0);
			roleCombo.setSelectedIndex(0);
		for (int i = 1; i <= items.length; i++)
			roleCombo.insertItemAt(items[i-1][0] + "_"+items[i-1][1], i);
		}


		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(115, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register New Employee");
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
		btnNewButton.setBounds(647, 11, 89, 23);
		contentPane.add(btnNewButton);

		empIDGenB = new JButton("Generate");
		empIDGenB.setToolTipText("Click  to generate employee ID");
		empIDGenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Employee em = new Employee();
				empIDT.setText("" + em.generateID());

			}
		});
		empIDGenB.setForeground(Color.RED);
		empIDGenB.setBounds(216, 82, 89, 23);
		contentPane.add(empIDGenB);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
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

				@SuppressWarnings("deprecation")
				String password = passwordT.getText().replaceAll("\\s", "");

				if (!(empIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate employee ID.");
					empIDT.requestFocus();
				} else {
					int empID = Integer.parseInt(empIDs);

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
					else{EmployeeAcc e= new EmployeeAcc();
					
					if ((userName.equals(e.checkUserName(userName)))) {
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
								if (roleCombo.getSelectedIndex() == 0)
									JOptionPane.showMessageDialog(null,
											"Choose employee role.");
								else 
									if (roleCombo.getSelectedItem() == null)
										JOptionPane.showMessageDialog(null,
												"Employee role not found");
								else {
									int roleid=0;
									Employee em= new Employee();
									String[][] all = em.populateCombo();
									String  selected = roleCombo.getSelectedItem().toString();
									for (int r = 0; r < all.length; r++)
										if (selected
												.equals(all[r][0] + "_" + all[r][1])) {
											roleid = Integer.parseInt(all[r][0]);
											break;
										} /*
										else {
											JOptionPane.showMessageDialog(null,
													"Role ID could not be matched from Role combobox");
										*/
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

											//Employee em = new Employee();
											if (em.registerEmployee(

											empID,

											fName, otherNames,

											userName,

											phone, address,

											email,

											birthDate,

											birthPlace,

											nic, roleid, salary,

											recruitDate,

											password

											))

												JOptionPane
														.showMessageDialog(
																null,
																"Employee successfully registered.");

											else

												JOptionPane
														.showMessageDialog(
																null,
																"Employee  registration has failed.",
																"Database Error",
																JOptionPane.ERROR_MESSAGE);
											initialiseFields();

										}
									}

								}
							}
						}}
					}
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(216, 654, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(381, 654, 89, 23);
		contentPane.add(btnCancel);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(533, 11, 89, 23);
		contentPane.add(btnLogOut);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { empIDT, fNameT, otherNamesT, userNameT,
						phoneT, addressT, emailT, birthDateT, BirthPlaceT,
						nicT, salaryT, recruitDateT, passwordT, roleCombo }));
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
		roleCombo.setSelectedIndex(0);
	}
}
