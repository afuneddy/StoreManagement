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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Employee;

@SuppressWarnings("serial")
public class UpdateEmployeeRole extends JFrame {

	private JPanel contentPane;
	private JTextField roleIDT;
	String[][] allEmp;
	static int empID2;
	private JLabel lblRoleId;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	private JTextField roleT;
	private JLabel label;
	public static final int classID = 18;
	static int roleid;
	static int empID; static String userName;
 private JButton btnNewButton_1;
static int trackID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployeeRole frame = new UpdateEmployeeRole(trackID, roleid, empID2, userName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateEmployeeRole(final int trackID, int roleid,final int empID, String userName) {
		setTitle("Employee Role Update");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		roleIDT = new JTextField();
		roleIDT.setToolTipText("Start typing name of role and select below");
		
		roleIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		roleIDT.setColumns(10);
		roleIDT.setBounds(125, 81, 205, 23);
		contentPane.add(roleIDT);

		lblRoleId = new JLabel("Role ID");
		lblRoleId.setBounds(41, 82, 102, 20);
		contentPane.add(lblRoleId);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Update/View  role Role");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(109, 11, 209, 14);
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
		btnSave.setToolTipText("Save added role info");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput ii = new IntegerInput();
				String roles = roleIDT.getText().trim();			
String role = roleT.getText().trim();
				if(!ii.isInteger(roles))
					JOptionPane.showMessageDialog(null, "Enter whole number value");
				else {
				int roleid = Integer.parseInt(roleIDT.getText());
				
				Employee e = new Employee();
				if(e.updateRole(roleid, role)){
					JOptionPane.showMessageDialog(null, "Role update has Succeeded");
				roleIDT.setText(null);
				roleT.setText(null);
				}
				else
					JOptionPane.showMessageDialog(null, "Role update has Failed");}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(396, 176, 89, 23);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				roleT.setText(null);
				roleIDT.setText(null);
				roleIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(609, 176, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(25, 270, 244, 31);
		contentPane.add(panel_2);

		JLabel lblroleTable = new JLabel("Employee Role Table");
		lblroleTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblroleTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblroleTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 300, 640, 300);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Role ID", "Role"
				};

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable roleTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, String.class,
					 };
			boolean[] columnEditables = new boolean[] { false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		roleTable.setBounds(20, 375, 862, 243);

		roleTable.getColumnModel().setColumnSelectionAllowed(true);
		roleTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		roleTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		

		roleTable.setFillsViewportHeight(true);
		roleTable.setColumnSelectionAllowed(true);
		roleTable.setCellSelectionEnabled(true);
		roleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roleTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(roleTable);

		contentPane.add(scrollPane);

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo
				.setToolTipText("Click here to view info about role ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(125, 177, 105, 23);
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
				Employee em = new Employee();

				allEmp = em.getAllEmpRoles();
				int noRetItems = allEmp.length;//em.getEmpCount();


				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1]
							 });

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
				allEmp = em.getAllEmpRoles();
				int noRetItems = allEmp.length;//em.getEmpCount();


				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allEmp[r][0],
							allEmp[r][1]
							 });

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
						roleTable.print();
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
		
		roleT = new JTextField();
		roleT.setToolTipText("Start typing name of role and select below");
		roleT.setColumns(10);
		roleT.setBounds(429, 81, 205, 23);
		contentPane.add(roleT);
		
		label = new JLabel("Role ID");
		label.setBounds(345, 82, 102, 20);
		contentPane.add(label);
		
		btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnNewButton_1.setBounds(602, 11, 118, 31);
		contentPane.add(btnNewButton_1);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{roleIDT}));
	}

	
	public void displayAction() {
		// row = 0;

		String txtid = roleIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter role ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for role ID.");
				roleIDT.setText(null);
				roleIDT.requestFocus();
			}  else {
				int roleid =Integer.parseInt(txtid);
				Employee emp = new Employee();
					String info[] = emp.getEmpRoleInfo(roleid);
					
					if(Integer.parseInt(info[0]) ==0)
						JOptionPane.showMessageDialog(null,
								"Security Level " +roleid +" hasn't been implemented");
					roleIDT.setText(info[0]);
					roleT.setText(info[1]);
					
					

					//roleIDT.setEditable(false);
					
					// JOptionPane.showMessageDialog(null, info[1]);
				}}

	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	


	}
}
