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

import planettaStore.Supplier;
import security.SecurityAcc;

import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class RegSupplier extends JFrame {

	private JPanel contentPane;
	private JTextField supIDT;
	private JLabel lblSupplierId;
	private JTextField fNameT;
	private JLabel lblFirstName;
	private JTextField otherNamesT;
	private JLabel lblOtherNames;
	private JTextField phone1T;
	private JLabel phone1Lb;
	private JTextField addressT;
	private JLabel lblAddress;
	private JTextField emailT;
	private JLabel lblEmail;
	private JTextField sampleProdT;
	private JLabel sampleProdsLb;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton supIDGenB;
	private JButton btnSave;
	private JButton btnCancel;
	private JSpinner gradeSpin;
	private JTextField bzNameT;
	private JTextField phone2T;
	protected static int trackid;
	public static final int classID = 12;
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
					RegSupplier frame = new RegSupplier(trackid,roleid, empID, userName);
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
	public RegSupplier(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Supplier Registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		supIDT = new JTextField();
		supIDT.setEditable(false);
		supIDT.setColumns(10);
		supIDT.setBounds(216, 102, 205, 23);
		contentPane.add(supIDT);

		lblSupplierId = new JLabel("Supplier ID:");
		lblSupplierId.setBounds(129, 103, 79, 20);
		contentPane.add(lblSupplierId);

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
		lblOtherNames.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOtherNames.setBounds(129, 182, 89, 20);
		contentPane.add(lblOtherNames);

		phone1T = new JTextField();
		phone1T.setColumns(10);
		phone1T.setBounds(216, 272, 205, 23);
		contentPane.add(phone1T);

		phone1Lb = new JLabel("Phone line 1:");
		phone1Lb.setBounds(129, 273, 72, 20);
		contentPane.add(phone1Lb);

		addressT = new JTextField();
		addressT.setColumns(10);
		addressT.setBounds(216, 352, 205, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(129, 353, 59, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setColumns(10);
		emailT.setBounds(216, 399, 205, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(129, 400, 59, 20);
		contentPane.add(lblEmail);

		sampleProdT = new JTextField();
		sampleProdT.setColumns(10);
		sampleProdT.setBounds(216, 433, 205, 23);
		contentPane.add(sampleProdT);

		sampleProdsLb = new JLabel("Sample Products:");
		sampleProdsLb.setFont(new Font("Tahoma", Font.BOLD, 10));
		sampleProdsLb.setBounds(129, 433, 89, 20);
		contentPane.add(sampleProdsLb);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(115, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register New Supplier");
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
		btnNewButton.setBounds(588, 11, 89, 23);
		contentPane.add(btnNewButton);

		supIDGenB = new JButton("Generate");
		supIDGenB.setToolTipText("Click  to generate Supplier ID");
		supIDGenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Supplier sup = new Supplier();
				supIDT.setText("" + sup.generateID());

			}
		});
		supIDGenB.setForeground(Color.RED);
		supIDGenB.setBounds(216, 82, 89, 23);
		contentPane.add(supIDGenB);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();

				String supIDs = supIDT.getText().trim().replaceAll("\\s", "");

				String fName = fNameT.getText().trim();
				String otherNames = otherNamesT.getText().trim();
				String bzName = bzNameT.getText().trim();
				String phone1s = phone1T.getText().trim().replaceAll("\\s", "");
				String phone2s = phone2T.getText().trim().replaceAll("\\s", "");

				String address = addressT.getText().trim();

				String email = emailT.getText().trim().replaceAll("\\s", "");

				String sampleProds = sampleProdT.getText().trim()
						.replaceAll("\\s", "");

				String grades = gradeSpin.getValue().toString().trim()
						.replaceAll("\\s", "");

				if (!(supIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate Supplier ID.");
					supIDT.requestFocus();
				} else {
					int supID = Integer.parseInt(supIDs);

					if (!(fName.length() > 0)) {
						JOptionPane
								.showMessageDialog(null, "Enter first name.");
						fNameT.requestFocus();
					} else

					if (!(otherNames.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter other names.");
						otherNamesT.requestFocus();
					} else

					if (!(bzName.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter Business Name.");
						bzNameT.requestFocus();
					} else if (!(phone1s.length() >= 8)) {
						JOptionPane.showMessageDialog(null,
								"Enter phone line 1 number, atleast 8 digits.");
						phone1T.setText(null);
						phone1T.requestFocus();
					} else if (!(ii.isInteger(phone1s))) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter whole number values for phone line 1 number.",
										"Wrong Values",
										JOptionPane.ERROR_MESSAGE);
						phone1T.setText(null);
						phone1T.requestFocus();
					}

					else {
						int phone1 = Integer.parseInt(phone1s);
						if (!(phone2s.length() >= 8)) {

							JOptionPane
									.showMessageDialog(null,
											"Enter phone line 2 number, atleast 8 digits.");
							phone2T.setText(null);
							phone2T.requestFocus();
						} else if (!(ii.isInteger(phone2s))) {
							JOptionPane
									.showMessageDialog(
											null,
											"Enter whole number values for phone line 2 number.",
											"Wrong Values",
											JOptionPane.ERROR_MESSAGE);
							phone2T.setText(null);
							phone2T.requestFocus();
						} else {
							int phone2 = Integer.parseInt(phone2s);
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

							else if (!(sampleProds.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter sample products of supplier.");
								sampleProdT.requestFocus();
							} else {

								if (!(grades.length() > 0) || grades == "0") {
									JOptionPane
											.showMessageDialog(null,
													"Give non zero grade to supplier, spin button to select.");
									gradeSpin.requestFocus();
								} else if (!(ii.isInteger(grades))) {
									JOptionPane
											.showMessageDialog(
													null,
													"Enter whole number values for phone line 2 number.",
													"Wrong Values",
													JOptionPane.ERROR_MESSAGE);
									gradeSpin.setValue(0);
									gradeSpin.requestFocus();
								} else {
									int grade = Integer.parseInt(grades);
									Supplier c = new Supplier();

									if (c.registerSupplier(

									supID,

									fName, otherNames, bzName,

									phone1, phone2, address,

									email, sampleProds, grade

									))

										JOptionPane
												.showMessageDialog(null,
														"Supplier successfully registered.");

									else

										JOptionPane
												.showMessageDialog(
														null,
														"Supplier  registration has failed.",
														"Database Error",
														JOptionPane.ERROR_MESSAGE);
									initialiseFields();

								}
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
		lblGrade.setBounds(129, 477, 73, 20);
		contentPane.add(lblGrade);

		gradeSpin = new JSpinner();
		gradeSpin.setBounds(216, 467, 72, 23);
		contentPane.add(gradeSpin);

		bzNameT = new JTextField();
		bzNameT.setColumns(10);
		bzNameT.setBounds(216, 227, 205, 23);
		contentPane.add(bzNameT);

		phone2T = new JTextField();
		phone2T.setColumns(10);
		phone2T.setBounds(216, 314, 205, 23);
		contentPane.add(phone2T);

		JLabel phone2Lb = new JLabel("Phone line 2:");
		phone2Lb.setBounds(129, 315, 72, 20);
		contentPane.add(phone2Lb);

		JLabel lblBusinessName = new JLabel("Business Name:");
		lblBusinessName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblBusinessName.setBounds(129, 231, 100, 14);
		contentPane.add(lblBusinessName);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(588, 58, 89, 23);
		contentPane.add(btnLogOut);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { supIDT, fNameT, otherNamesT, phone1T,
						addressT, emailT, sampleProdT }));
	}

	public void initialiseFields() {
		supIDT.setText(null);

		fNameT.setText(null);

		otherNamesT.setText(null);
		bzNameT.setText(null);
		phone1T.setText(null);
		phone2T.setText(null);

		addressT.setText(null);

		emailT.setText(null);
		gradeSpin.setValue(0);

		sampleProdT.setText(null);

	}
}
