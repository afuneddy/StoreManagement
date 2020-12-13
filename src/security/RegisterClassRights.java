package security;

import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

@SuppressWarnings("serial")
public class RegisterClassRights extends JFrame {

	private JPanel contentPane;
	private JTextField classIDT;
	String[][] allEmp;
	int empID;
	private JLabel lblClassComponent;
	private JTextField securityLevT;
	private JTextField classcomT;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton update;
	private JButton btnCancel;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	private JLabel lblSecurityLevel;
	public static final int classID = 32;
	static int roleid;
	static int empID2; static String userName; static int trackID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterClassRights frame = new RegisterClassRights(trackID, roleid, empID2, userName);
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
	 * @param empID22 
	 */
	public RegisterClassRights(final int trackID, int roleid, final int empID, String userName) {
		setTitle("Class Rights");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 828, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		classIDT = new JTextField();
		classIDT.setToolTipText("Start typing name of employee and select below");
		
		classIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		classIDT.setColumns(10);
		classIDT.setBounds(115, 142, 147, 23);
		contentPane.add(classIDT);

		lblClassComponent = new JLabel("Class Component:");
		lblClassComponent.setBounds(298, 111, 140, 20);
		contentPane.add(lblClassComponent);

		securityLevT = new JTextField();
		securityLevT.setColumns(10);
		securityLevT.setBounds(539, 142, 147, 23);
		contentPane.add(securityLevT);

		classcomT = new JTextField();
		classcomT.setColumns(10);
		classcomT.setBounds(298, 142, 205, 23);
		contentPane.add(classcomT);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(25, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Register/ Update/ View  Class Rights");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 336, 14);
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

		update = new JButton("Update");
		update.setToolTipText("Save added employee info");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				//IsDate id = new IsDate();

				String classIDs = classIDT.getText().trim().replaceAll("\\s", "");

				
				String securityLevs = securityLevT.getText().trim().replaceAll("\\s", "");

				

				String classcom = classcomT.getText().trim()
						.replaceAll("\\s", "");

				
				if (!(classIDs.length() > 0)) {
					JOptionPane
							.showMessageDialog(null, "Generate Class ID.");
					classIDT.requestFocus();
				} else {
					if(!(ii.isInteger(classIDs)))
						{JOptionPane.showMessageDialog(null,
								"Enter whole number value for class ID.");
						classIDT.requestFocus();classIDT.setText(null);}
					else{
					int classID = Integer.parseInt(classIDs);

					
							if (!(classcom.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Enter class component");
								classcomT.requestFocus();
							} 
							if (!(securityLevs.length() > 0)) {
								JOptionPane.showMessageDialog(null,
										"Specify minimum security level for this class");
								securityLevT.requestFocus();
							} else if (!(ii.isInteger(securityLevs))) {
								JOptionPane.showMessageDialog(null,
										"Enter whole number for Security level",
										"Wrong Value",
										JOptionPane.ERROR_MESSAGE);
								securityLevT.setText(null);
								securityLevT.requestFocus();
							} 

										else {
											int securitylev = Integer.parseInt(securityLevs);
											
											SecurityAcc s = new SecurityAcc();
											if (s.updateClassRights(classID, classcom, securitylev))

												JOptionPane
														.showMessageDialog(
																null,
																"Class right successfully updated.");

											else

												JOptionPane
														.showMessageDialog(
																null,
																"Class update has failed.",
																"Database Error",
																JOptionPane.ERROR_MESSAGE);
											initialiseFields();
											classIDT.setEditable(true);

										}
									}
										
								}
						
			}
		});
		update.setFont(new Font("Tahoma", Font.BOLD, 12));
		update.setForeground(Color.BLUE);
		update.setBounds(466, 221, 89, 23);
		contentPane.add(update);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();
				classIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(631, 221, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(25, 270, 244, 31);
		contentPane.add(panel_2);

		JLabel lblEmployeeTable = new JLabel("Class Rights Table:");
		lblEmployeeTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmployeeTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblEmployeeTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 300, 656, 300);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Class ID", "Class Component",
				"Security Level" };

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
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					String.class,
					Integer.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		employeeTable.setBounds(20, 375, 862, 243);

		employeeTable.getColumnModel().setColumnSelectionAllowed(true);
		employeeTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		

		employeeTable.setFillsViewportHeight(true);
		employeeTable.setColumnSelectionAllowed(true);
		employeeTable.setCellSelectionEnabled(true);
		employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(employeeTable);

		contentPane.add(scrollPane);
		
		
		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click here to view info about employee ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(126, 202, 105, 31);
		contentPane.add(btnDisplayInfo);

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
				SecurityAcc s = new SecurityAcc();

				String[][] all = s.getAllClassRights();
				int noRetItems = all.length;//em.getEmpCount();

				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, all[r][0],
							all[r][1], all[r][2]
							 });

				}
				tableUpdateAckn(flag);

			}
		});
		btnPopulateTable.setBounds(87, 619, 118, 23);
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

				SecurityAcc s = new SecurityAcc();

				String[][] all = s.getAllClassRights();
				int noRetItems = all.length;//em.getEmpCount();

				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, all[r][0],
							all[r][1], all[r][2]
							 });

				
				}
				tableUpdateAckn(flag);

			}
		});
		btnUpdateTable.setBounds(259, 619, 118, 23);
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
		btnCloseTable.setBounds(432, 619, 118, 23);
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
		btnPrintTable.setBounds(595, 619, 118, 23);
		contentPane.add(btnPrintTable);
		
		lblSecurityLevel = new JLabel("Security Level:");
		lblSecurityLevel.setBounds(539, 111, 102, 20);
		contentPane.add(lblSecurityLevel);
		
		JLabel lblClassId = new JLabel("Class ID:");
		lblClassId.setBounds(115, 111, 140, 20);
		contentPane.add(lblClassId);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnNewButton_1.setBounds(539, 48, 147, 31);
		contentPane.add(btnNewButton_1);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{classIDT, securityLevT, classcomT}));
	}

	public void initialiseFields() {
		classIDT.setText(null);

	

		securityLevT.setText(null);

	

		classcomT.setText(null);
	

	}

	public void displayAction() {
		// row = 0;
		int	classID;
		String txtid = classIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter employee ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for employee ID.");
				classIDT.setText(null);
			} else {
			classID = Integer.parseInt(txtid);
				SecurityAcc s= new SecurityAcc();
					String info[] = s.getClassRightsInfo(classID);;
					classIDT.setText(info[0]);

					classcomT.setText(info[1]);

					securityLevT.setText(info[2]);

					
classcomT.setEditable(false);
					classIDT.setEditable(false);
					infoFieldsEditable(true);

					// JOptionPane.showMessageDialog(null, info[1]);

				
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

	

		securityLevT.setEditable(flag);

		//classIDT.setEditable(flag);

		//classcomT.setEditable(flag);
		
	}
}
