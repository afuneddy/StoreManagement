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
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Purchase;
import planettaStore.Supplier;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class RegisterPurchase extends JFrame {
	String[][] lookup;
	JComboBox<String> comboBox;
	private JPanel contentPane;
	private JTextField purIDT;
	private JLabel lblPurchaseId;
	private JTextField supIDT;
	private JLabel lblFirstName;
	private JTextField tCostT;
	private JLabel lbltCostl;
	private JTextField advanceT;
	private JLabel lblPhoneNo;
	private JTextField dueDateT;
	private JLabel lblAddress;
	private JTextField dateT;
	private JLabel lbldates;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton purIDGenB;
	private JButton btnSave;
	private JButton btnCancel;
	protected static int trackid;
	public static final int classID = 10;
	static int roleid;
	static int empID; 
	static String userName;
	private JButton btnLogOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPurchase frame = new RegisterPurchase(trackid,roleid, empID, userName);
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
	public RegisterPurchase(final int trackID,final int roleid, final int empID,  final String userName) {
		setTitle("Purchase Registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		purIDT = new JTextField();
		purIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		purIDT.setEditable(false);
		purIDT.setColumns(10);
		purIDT.setBounds(216, 102, 205, 29);
		contentPane.add(purIDT);

		lblPurchaseId = new JLabel("Purchase ID:");
		lblPurchaseId.setBounds(118, 107, 72, 20);
		contentPane.add(lblPurchaseId);

		supIDT = new JTextField();
		supIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		supIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			
				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Supplier s = new Supplier();
				IntegerInput ii = new IntegerInput();
				String text = supIDT.getText().trim().replaceAll("\\s", "");
				if (ii.isInteger(text))
					;
				else if (!(text.length() > 0)) {
					comboBox.setSelectedIndex(1);
					supIDT.setText(null);
					supIDT.requestFocus();
				} else

				{
				
					lookup = s.searchString(text);
					int size = lookup.length;
					if (size == 0)
					{
							JOptionPane.showMessageDialog(null,
									"No Supplier name or business name starting with  "
											+ "\"" + text + "\"  "
											+ "is found."
											+ "\nTry other names.");
							supIDT.setText(null);
							supIDT.requestFocus();
						
					

					} 
					else
				
					for (int r = 0; r < size; r++)
					{

					
							comboBox.setSelectedIndex(0);
						
						
							
							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1] + " - " + lookup[r][2];

							comboBox.addItem(cmBoxItem);
						

					}

				}

			}
		});
		supIDT.setToolTipText("Start typing name  or business name of supplier and select frm he list below.");
		supIDT.setColumns(10);
		supIDT.setBounds(216, 150, 205, 29);
		contentPane.add(supIDT);

		lblFirstName = new JLabel("Supplier ID:");
		lblFirstName.setBounds(118, 168, 72, 20);
		contentPane.add(lblFirstName);

		tCostT = new JTextField();
		tCostT.setFont(new Font("Tahoma", Font.BOLD, 12));
		tCostT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				advanceT.setText(tCostT.getText());

			}
		});
		tCostT.setColumns(10);
		tCostT.setBounds(216, 227, 205, 29);
		contentPane.add(tCostT);

		lbltCostl = new JLabel("Total Cost:");
		lbltCostl.setBounds(118, 232, 72, 20);
		contentPane.add(lbltCostl);

		advanceT = new JTextField();
		advanceT.setFont(new Font("Tahoma", Font.BOLD, 12));
		advanceT.setColumns(10);
		advanceT.setBounds(216, 271, 205, 29);
		contentPane.add(advanceT);

		lblPhoneNo = new JLabel("Advanced Pay't:");
		lblPhoneNo.setBounds(118, 272, 100, 20);
		contentPane.add(lblPhoneNo);

		dueDateT = new JTextField();
		dueDateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		dueDateT.setColumns(10);
		dueDateT.setBounds(216, 317, 205, 29);
		contentPane.add(dueDateT);

		lblAddress = new JLabel("Due Date:");
		lblAddress.setBounds(118, 322, 59, 20);
		contentPane.add(lblAddress);

		dateT = new JTextField();
		dateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		dateT.setColumns(10);
		dateT.setBounds(216, 361, 205, 29);
		contentPane.add(dateT);

		lbldates = new JLabel("Purchase Date:");
		lbldates.setBounds(118, 366, 89, 20);
		contentPane.add(lbldates);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(115, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register New Purchase");
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
		purIDGenB.setToolTipText("Click  to generate Purchase ID");
		purIDGenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Purchase p = new Purchase();
				purIDT.setText("" + p.generateID());

			}
		});
		purIDGenB.setForeground(Color.RED);
		purIDGenB.setBounds(216, 82, 89, 23);
		contentPane.add(purIDGenB);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				IsDate id = new IsDate();
				String purIDs = purIDT.getText().trim().replaceAll("\\s", "");

				String supIDs = supIDT.getText().trim().replaceAll("\\s", "");
				String tCosts = tCostT.getText().trim().replaceAll("\\s", "");

				String advances = advanceT.getText().trim()
						.replaceAll("\\s", "");
				Date dueDate = null;
				String dueDates = dueDateT.getText().trim()
						.replaceAll("\\s", "");

				String dates = dateT.getText().trim().replaceAll("\\s", "");

				if (!(purIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate Purchase ID.");
					purIDT.requestFocus();
				} else {
					int purID = Integer.parseInt(purIDs);

					if (!(supIDs.length() > 0)) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter supplier ID.\n Hints: Start typing name or business name \nof supplier and choose from list.");
						supIDT.requestFocus();
					} else if (!(ii.isInteger(supIDs))) {
						JOptionPane.showMessageDialog(null,
								"Enter whole number values for supplier ID.",
								"Wrong Values", JOptionPane.ERROR_MESSAGE);
						supIDT.setText(null);
						supIDT.requestFocus();
					} else {
						Supplier s = new Supplier();
						int suID = Integer.parseInt(supIDs);
						if (!(s.getSupID(suID) == suID))
							JOptionPane
									.showMessageDialog(
											null,
											"No supplier has been found with this ID: "
													+ suID
													+ ".\n Click \"Register Supplier\" button to register supplier.");
						else if (!(tCosts.length() > 0)) {
							JOptionPane
									.showMessageDialog(null,
											"Enter cost of purchase from this supplier.");
							tCostT.requestFocus();
						} else if (!(ii.isInteger(tCosts))) {
							JOptionPane.showMessageDialog(null,
									"Enter monetary value for Total cost.",
									"Wrong Values", JOptionPane.ERROR_MESSAGE);
							tCostT.setText(null);
							tCostT.requestFocus();
						} else {
							int tCost = Integer.parseInt(tCosts);
							if (!(advances.length() >= 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter advanced payment.");
								advanceT.setText(null);
								advanceT.requestFocus();
							} else if (!(ii.isInteger(advances))) {
								JOptionPane
										.showMessageDialog(
												null,
												"Enter monetary value for Advanced payment.",
												"Wrong Values",
												JOptionPane.ERROR_MESSAGE);
								advanceT.setText(null);
								advanceT.requestFocus();
							} else {
								int advance = Integer.parseInt(advances);

								if ((advance < tCost)
										&& !(dueDates.length() > 0)) {
									JOptionPane.showMessageDialog(null,
											"Enter Payment deadline.");
									dueDateT.requestFocus();
								} else if ((advance < tCost)
										&& !(id.isDate(dueDates))) {
									JOptionPane
											.showMessageDialog(null,
													"Enter due date in this format:yyyy-mm-dd");
									dueDateT.requestFocus();
									dueDateT.setText(null);
								}

								else {
									if (advance < tCost) {
										dueDate = Date.valueOf(dueDates);
									}

									if (!(dates.length() > 0)) {
										JOptionPane.showMessageDialog(null,
												"Enter purchase date.");
										dateT.requestFocus();
									} else if (!(id.isDate(dates))) {
										JOptionPane
												.showMessageDialog(null,
														"Enter purchase date in this format:yyyy-mm-dd");
										dateT.requestFocus();
										dateT.setText(null);
									} else {
										Date date = Date.valueOf(dates);
										if (advance == tCost)
											dueDate = date;
										Purchase p = new Purchase();
										if (!(p.checkValues(advance, tCost,
												dueDateT, advanceT, dueDates)))
											JOptionPane
													.showMessageDialog(
															null,
															"Purchase  registration has failed.",
															"Value Inconsistency",
															JOptionPane.ERROR_MESSAGE);
										else {
											if (p.registerPurchase(

											purID, suID, tCost, advance,
													dueDate, date

											))

												JOptionPane
														.showMessageDialog(
																null,
																"Purchase successfully registered.");

											else

												JOptionPane
														.showMessageDialog(
																null,
																"Purchase  registration has failed.",
																"Database Error",
																JOptionPane.ERROR_MESSAGE);
											initialiseFields();

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
		btnSave.setBounds(248, 436, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(418, 436, 89, 23);
		contentPane.add(btnCancel);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(500);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--",""}));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1] + " - "
										+ lookup[r][2])) {
							supIDT.setText(lookup[r][0]);
						}
				}
			}
		});
		comboBox.setBounds(216, 181, 205, 29);
		contentPane.add(comboBox);

		JButton btnRegisterSupplier = new JButton("Register Supplier");
		btnRegisterSupplier
				.setToolTipText("Click to register supplier if they are new.");
		btnRegisterSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				RegSupplier rs = new RegSupplier(trackID,roleid, empID, userName);
				rs.setVisible(true);

			}
		});
		btnRegisterSupplier.setForeground(Color.RED);
		btnRegisterSupplier.setBounds(476, 150, 156, 23);
		contentPane.add(btnRegisterSupplier);

		JLabel lblNewSupplier = new JLabel("New Supplier?");
		lblNewSupplier.setBounds(500, 133, 121, 14);
		contentPane.add(lblNewSupplier);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(191, 108, 15, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(39, 497, 15, 14);
		contentPane.add(label);
		
		JLabel lblTakeNotein = new JLabel("Take note (in writing) of PurchaseID and SupplierID because you'll be required to provide when registering Warehouse Inventory.");
		lblTakeNotein.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblTakeNotein.setBounds(49, 492, 610, 29);
		contentPane.add(lblTakeNotein);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(190, 169, 15, 14);
		contentPane.add(label_1);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setBounds(600, 56, 89, 23);
		contentPane.add(btnLogOut);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { purIDT, supIDT, tCostT, advanceT, dueDateT,
						dateT }));
		initialiseFields();
	}

	public void initialiseFields() {
		purIDT.setText(null);

		supIDT.setText(null);

		tCostT.setText(null);

		advanceT.setText(null);

		dueDateT.setText(null);

		dateT.setText(null);
		comboBox.setSelectedIndex(1);

	}
}
