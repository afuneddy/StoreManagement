package Administration;

import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Customer;
import security.SecurityAcc;

import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class RegCustomer extends JFrame {

	/**
	 * 
	 */
	
	private JPanel contentPane;
	private JTextField cusIDT;
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
	private JButton cusIDGenB;
	private JButton btnSave;
	private JButton btnCancel;
	private JSpinner gradeSpin;
	protected static int trackid;
	public static final int classID = 8;
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
					RegCustomer frame = new RegCustomer(trackid,roleid, empID, userName);
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
	public RegCustomer(final int trackID,int roleid, final int empID, String userName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Customer Registration");
		setBounds(100, 100, 715, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cusIDT = new JTextField();
		cusIDT.setEditable(false);
		cusIDT.setColumns(10);
		cusIDT.setBounds(216, 102, 205, 23);
		contentPane.add(cusIDT);

		lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(129, 103, 79, 20);
		contentPane.add(lblCustomerId);

		fNameT = new JTextField();
		fNameT.setColumns(10);
		fNameT.setBounds(216, 136, 205, 23);
		contentPane.add(fNameT);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(129, 137, 79, 20);
		contentPane.add(lblFirstName);

		otherNamesT = new JTextField();
		otherNamesT.setColumns(10);
		otherNamesT.setBounds(216, 181, 205, 23);
		contentPane.add(otherNamesT);

		lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setBounds(129, 182, 79, 20);
		contentPane.add(lblOtherNames);

		phoneT = new JTextField();
		phoneT.setColumns(10);
		phoneT.setBounds(216, 225, 205, 23);
		contentPane.add(phoneT);

		lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(129, 226, 72, 20);
		contentPane.add(lblPhoneNo);

		addressT = new JTextField();
		addressT.setColumns(10);
		addressT.setBounds(216, 271, 205, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(129, 272, 59, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(216, 315, 205, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(129, 316, 59, 20);
		contentPane.add(lblEmail);

		nicT = new JTextField();
		nicT.setColumns(10);
		nicT.setBounds(216, 361, 205, 23);
		contentPane.add(nicT);

		lblNicNo = new JLabel("NIC no:");
		lblNicNo.setBounds(129, 362, 73, 20);
		contentPane.add(lblNicNo);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(115, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register New Customer");
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
		btnNewButton.setBounds(600, 11, 89, 23);
		contentPane.add(btnNewButton);

		cusIDGenB = new JButton("Generate");
		cusIDGenB.setToolTipText("Click  to generate customer ID");
		cusIDGenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Customer cus = new Customer();
				cusIDT.setText("" + cus.generateID());

			}
		});
		cusIDGenB.setForeground(Color.RED);
		cusIDGenB.setBounds(216, 82, 89, 23);
		contentPane.add(cusIDGenB);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();

				String cusIDs = cusIDT.getText().trim().replaceAll("\\s", "");

				String fName = fNameT.getText().trim();
				String otherNames = otherNamesT.getText().trim();

				String phones = phoneT.getText().trim().replaceAll("\\s", "");

				String address = addressT.getText().trim();

				String email = emailT.getText().trim().replaceAll("\\s", "");

				String nics = nicT.getText().trim().replaceAll("\\s", "");
				String grades = gradeSpin.getValue().toString().trim()
						.replaceAll("\\s", "");

				if (!(cusIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate customer ID.");
					cusIDT.requestFocus();
				} else {
					int cusID = Integer.parseInt(cusIDs);

					if (!(fName.length() > 0)) {
						JOptionPane
								.showMessageDialog(null, "Enter first name.");
						fNameT.requestFocus();
					} else

					if (!(otherNames.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter other names.");
						otherNamesT.requestFocus();
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
							if (!(grades.length() > 0) || grades == "0") {
								JOptionPane
										.showMessageDialog(null,
												"Give non zero grade to supplier, spin button to select.");
								gradeSpin.requestFocus();
							} else if (!(ii.isInteger(grades))) {
								JOptionPane
										.showMessageDialog(
												null,
												"Whole number value required for grade - negative or positive.",
												"Wrong Values",
												JOptionPane.ERROR_MESSAGE);
								gradeSpin.setValue(0);
								gradeSpin.requestFocus();
							} else {
								int grade = Integer.parseInt(grades);
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

							}
						}
					}

				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(216, 513, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(386, 513, 89, 23);
		contentPane.add(btnCancel);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(129, 403, 73, 20);
		contentPane.add(lblGrade);

		gradeSpin = new JSpinner();
		gradeSpin.setBounds(216, 399, 72, 29);
		contentPane.add(gradeSpin);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(600, 45, 89, 23);
		contentPane.add(btnLogOut);
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

	}
}
