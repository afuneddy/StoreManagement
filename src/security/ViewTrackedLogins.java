package security;

import interfaceClass.IsDate;
import interfaceClass.IsTime;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.Time;

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

@SuppressWarnings("serial")
public class ViewTrackedLogins extends JFrame {

	private JPanel contentPane;
	String[][] allEmp;
	int empID2;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnCancel;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	public static final int classID = 18;
	static int roleid;
	static int empID; static String userName;
 private JTextField toDateT;
 private JTextField toTimeT;
 private JTextField frmDateT;
 private JLabel lblFromDate;
 private JLabel lblFromTime;
 private JTextField frmTimeT;
 private Date frmDate;
 private Date toDate;
 private Time toTime;
 private Time frmTime;
 @SuppressWarnings("unused")
private int flag;
 private JButton btnNewButton_1;
static int trackID;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTrackedLogins frame = new ViewTrackedLogins(trackID, roleid, empID, userName);
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
	public ViewTrackedLogins(final int trackID, int roleID, final int empID, String userName) {
		setTitle("Tracked Logins");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 26, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("View Tracked Logins");
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

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toDateT.setText(null);
				frmDateT.setText(null);
				toTimeT.setText(null);
				frmTimeT.setText(null);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(588, 252, 89, 37);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(25, 270, 244, 31);
		contentPane.add(panel_2);

		JLabel lblroleTable = new JLabel("Login /Logout");
		lblroleTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblroleTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblroleTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 300, 640, 300);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "Track ID", "Emp ID", " User Name","Login Date","Login Time", "Logout Date"," Logout Time"
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
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,String.class, String.class,String.class,String.class,String.class,
					 };
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false};

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

		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				
				if (validateInput(myModel, frmDateS, frmTimeS, toDateS,		toTimeS) == true)
				{
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
				SecurityAcc em = new SecurityAcc();

				allEmp = em.getAllLogins(frmDate,frmTime, toDate, toTime);
				int noRetItems = allEmp.length;//em.getEmpCount();


				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { allEmp[r][0],
							allEmp[r][1], allEmp[r][2],
							allEmp[r][3],allEmp[r][4],
							allEmp[r][5], allEmp[r][6],
						
							 });

				}
				tableUpdateAckn(flag);



			}}
		});
		btnPopulateTable.setBounds(292, 253, 118, 37);
		contentPane.add(btnPopulateTable);

		btnUpdateTable = new JButton("Update Table");
		btnUpdateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				
				if (validateInput(myModel, frmDateS, frmTimeS, toDateS,		toTimeS) == true)
				{
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
				SecurityAcc em = new SecurityAcc();
				allEmp = em.getAllLogins(frmDate,frmTime, toDate, toTime);
				int noRetItems = allEmp.length;//em.getEmpCount();

				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { allEmp[r][0],
							allEmp[r][1], allEmp[r][2],
							allEmp[r][3],allEmp[r][4],
							allEmp[r][5], allEmp[r][6],
						
							 });

				}
				tableUpdateAckn(flag);

							}}
		});
		btnUpdateTable.setBounds(437, 253, 118, 37);
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
		btnCloseTable.setBounds(153, 629, 118, 31);
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
		btnPrintTable.setBounds(474, 629, 118, 27);
		contentPane.add(btnPrintTable);
		
		toDateT = new JTextField();
		toDateT.setToolTipText("Start typing name of role and select below");
		toDateT.setColumns(10);
		toDateT.setBounds(460, 126, 140, 23);
		contentPane.add(toDateT);
		
		JLabel lblLoginTime = new JLabel("To Date:");
		lblLoginTime.setBounds(399, 127, 71, 20);
		contentPane.add(lblLoginTime);
		
		toTimeT = new JTextField();
		toTimeT.setToolTipText("Start typing name of role and select below");
		toTimeT.setColumns(10);
		toTimeT.setBounds(460, 170, 140, 23);
		contentPane.add(toTimeT);
		
		JLabel lblLogoutTime = new JLabel("To Time:");
		lblLogoutTime.setBounds(399, 171, 71, 20);
		contentPane.add(lblLogoutTime);
		
		frmDateT = new JTextField();
		frmDateT.setToolTipText("Start typing name of role and select below");
		frmDateT.setColumns(10);
		frmDateT.setBounds(125, 123, 140, 23);
		contentPane.add(frmDateT);
		
		lblFromDate = new JLabel("From Date:");
		lblFromDate.setBounds(25, 124, 71, 20);
		contentPane.add(lblFromDate);
		
		lblFromTime = new JLabel("From Time:");
		lblFromTime.setBounds(25, 169, 71, 20);
		contentPane.add(lblFromTime);
		
		frmTimeT = new JTextField();
		frmTimeT.setToolTipText("Start typing name of role and select below");
		frmTimeT.setColumns(10);
		frmTimeT.setBounds(125, 168, 140, 23);
		contentPane.add(frmTimeT);
		
		btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(588, 26, 111, 31);
		contentPane.add(btnNewButton_1);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmDateT, toDateT, frmTimeT, toTimeT}));
	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	


	}
	boolean validateInput(DefaultTableModel model, String frmDateS,
			String frmTimeS, String toDateS, String toTimeS) {
		IsDate id = new IsDate();
		IsTime it = new IsTime();
		boolean validate = false;
		if (!(frmDateS.length() > 0)) {

			JOptionPane.showMessageDialog(null, "Enter START date.",
					"Input Error", JOptionPane.ERROR_MESSAGE);
			frmDateT.requestFocus();
		} else if (!(id.isDate(frmDateS))) {
			JOptionPane.showMessageDialog(null,
					"Enter START date in this format: yyyy-mm-dd ",
					"Wrong Date Format!", JOptionPane.ERROR_MESSAGE);

			frmDateT.requestFocus();
			frmDateT.setText(null);
		} else {

			frmDate = Date.valueOf(frmDateS);

			if (!(toDateS.length() > 0)) {

				JOptionPane.showMessageDialog(null, "Enter END date."+ toDateS.length(),
						"Input Error", JOptionPane.ERROR_MESSAGE);
				toDateT.requestFocus();
			} else if (!(id.isDate(toDateS))) {
				JOptionPane.showMessageDialog(null,
						"Enter END date in this format: yyyy-mm-dd ",
						"Wrong Date Format!", JOptionPane.ERROR_MESSAGE);

				toDateT.requestFocus();
				toDateT.setText(null);
			} else {

				toDate = Date.valueOf(toDateS);

				if (!(frmTimeS.length() > 0)) {

					JOptionPane.showMessageDialog(null, "Enter START time.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
					frmTimeT.requestFocus();
				} else if (!(it.isTime(frmTimeS))) {
					JOptionPane.showMessageDialog(null,
							"Enter START time in this format: hh:mm:ss ",
							"Wrong Time Format!", JOptionPane.ERROR_MESSAGE);

					frmTimeT.requestFocus();
					frmTimeT.setText(null);
				} else {

					frmTime = Time.valueOf(frmTimeS);

					if (!(toTimeS.length() > 0)) {

						JOptionPane.showMessageDialog(null, "Enter END time.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
						toTimeT.requestFocus();
					} else if (!(it.isTime(toTimeS))) {
						JOptionPane
								.showMessageDialog(
										null,
										"Enter END time in this format: hh:mm:ss ",
										"Wrong Time Format!",
										JOptionPane.ERROR_MESSAGE);

						toTimeT.requestFocus();
						toTimeT.setText(null);
					} else {

						toTime = Time.valueOf(toTimeS);
						validate = true;
						flag = 1;
						int rowCount = model.getRowCount();
						if (!(rowCount > 0)) {
							flag = 0;
							// JOptionPane
							// .showMessageDialog(null,"Table not yet populated. \nIt will be populated now.");
						} else
							for (int r = rowCount - 1; r >= 0; r--)
								model.removeRow(r);

					}
				}
			}
		}
		return validate;

	}
}
