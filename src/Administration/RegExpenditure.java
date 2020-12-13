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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Employee;
import planettaStore.Expenditure;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class RegExpenditure extends JFrame {
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
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton purIDGenB;
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField fNameT;
	private JLabel lblEmployeeName;
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
					RegExpenditure frame = new RegExpenditure(trackid,roleid, empID, userName);
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
	public RegExpenditure(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Expenditure Registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		expIDT = new JTextField();
		expIDT.setEditable(false);
		expIDT.setColumns(10);
		expIDT.setBounds(216, 102, 205, 23);
		contentPane.add(expIDT);

		lblExpenditureId = new JLabel("Exp. ID:");
		lblExpenditureId.setBounds(126, 103, 92, 20);
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
int size = lookup.length;
if (size==0) {
	
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
		empIDT.setToolTipText("Start typin employee name & select from List below.");
		empIDT.setColumns(10);
		empIDT.setBounds(216, 150, 205, 23);
		contentPane.add(empIDT);

		empidlb = new JLabel("Employee ID:");
		empidlb.setBounds(126, 167, 82, 20);
		contentPane.add(empidlb);

		amountTB = new JTextField();

		amountTB.setColumns(10);
		amountTB.setBounds(216, 272, 205, 23);
		contentPane.add(amountTB);

		amountlb = new JLabel("Amount:");
		amountlb.setBounds(126, 272, 72, 20);
		contentPane.add(amountlb);

		targetTB = new JTextField();
		targetTB.setColumns(10);
		targetTB.setBounds(216, 316, 205, 23);
		contentPane.add(targetTB);

		target = new JLabel("Target Name:");
		target.setBounds(126, 317, 100, 20);
		contentPane.add(target);

		reasonTB = new JTextArea();
		reasonTB.setWrapStyleWord(true);
		reasonTB.setLineWrap(true);
		reasonTB.setColumns(10);
		reasonTB.setBounds(216, 350, 205, 45);
		contentPane.add(reasonTB);

		reasonlb = new JLabel("Reason:");
		reasonlb.setBounds(126, 362, 59, 20);
		contentPane.add(reasonlb);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(115, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register New Expenditure");
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

		purIDGenB = new JButton("Generate");
		purIDGenB.setToolTipText("Click  to generate Expenditure ID");
		purIDGenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Expenditure e = new Expenditure();
				expIDT.setText("" + e.generateID());
				try {
					finalize();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		purIDGenB.setForeground(Color.RED);
		purIDGenB.setBounds(216, 82, 89, 23);
		contentPane.add(purIDGenB);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				//IsDate id = new IsDate();
				String expIDs = expIDT.getText().trim().replaceAll("\\s", "");

				String empIDs = empIDT.getText().trim().replaceAll("\\s", "");
				String amounts = amountTB.getText().trim()
						.replaceAll("\\s", "");

				String target = targetTB.getText().trim();
				//Date dueDate = null;
				String reason = reasonTB.getText().trim();

				String fName = fNameT.getText().trim();

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

							} else {

								Expenditure em = new Expenditure();

								if (em.registerExpenditure(

								expID, empID, amount, target, reason

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

							}

						}

					}
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(273, 501, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(448, 501, 89, 23);
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

		fNameT = new JTextField();
		fNameT.setText((String) null);
		fNameT.setColumns(10);
		fNameT.setBounds(216, 227, 205, 23);
		contentPane.add(fNameT);

		lblEmployeeName = new JLabel("Emp.  Name:");
		lblEmployeeName.setBounds(126, 228, 100, 20);
		contentPane.add(lblEmployeeName);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(600, 48, 89, 23);
		contentPane.add(btnLogOut);
		contentPane
				.setFocusTraversalPolicy(new FocusTraversalOnArray(
						new Component[] { expIDT, empIDT, amountTB, targetTB,
								reasonTB }));
		initialiseFields();
	}

	public void initialiseFields() {
		expIDT.setText(null);

		empIDT.setText(null);
		fNameT.setText(null);
		amountTB.setText(null);

		targetTB.setText(null);

		reasonTB.setText(null);
		comboBox.setSelectedIndex(1);

	}
}
