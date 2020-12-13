package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;
import interfaceClass.IsTime;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;

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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import planettaStore.Inventory;
import planettaStore.Transaction;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewTransaction extends JFrame {
	private String[][] alltrans;
	private JPanel contentPane;
	private JTextField frmDateT;
	private JTextField frmTimeT;
	private JLabel lbltoDate;
	private JTextField toDateT;
	private JLabel label;
	private JTextField toTimeT;
	private JScrollPane transPane;
	private JTable transTable;
	private JButton btnPopAllTrans;
	private JButton btnUpdateAllTrans;
	private JButton btnPrintTrans;
	private JButton btnCloseTrans;
	private JPanel transTitle;
	private JLabel lbltransDetailsTable;
	private JPanel panel_3;
	private JLabel lblObligatoryInfosRequired;
	private JButton btnHelp;

	Date frmDate;
	Date toDate;
	Time frmTime;
	Time toTime;
	String currency = "XAF ";
	int flag;
	private JTextField transIDT;
	private JTextField transTypeT;
	private JTextField empIDT;
	private JTextField custIDT;
	private JLabel lblAmount;
	private JTextField amountT;
	private JLabel lblName;
	private JTextField nameT;
	private JLabel lblDate;
	private JTextField dateT;
	private JTextField timeT;
	private JLabel lblTime;
	private JPanel panel;
	private JButton btnViewTransaction;
	private JPanel panel_2;
	private JButton btnCancel;
	protected static int trackID;
	
	public static final int classID = 25;
	static int roleid;
	static int empID; static String userName;
 private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTransaction frame = new ViewTransaction(trackID,roleid, empID, userName);
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
	public ViewTransaction(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("View Transaction");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1095, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPrecedingDate = new JLabel("\"From\" Date:");
		lblPrecedingDate.setBounds(42, 45, 85, 14);
		contentPane.add(lblPrecedingDate);
		new Inventory();
		frmDateT = new JTextField("2013-10-01");
		frmDateT.setFont(new Font("Tahoma", Font.BOLD, 12));

		frmDateT.setBounds(21, 62, 122, 27);
		frmDateT.setToolTipText("Inclusive date to which calculations should begin");
		contentPane.add(frmDateT);
		frmDateT.setColumns(10);

		JLabel lblPrecedingTime = new JLabel("\" From \" Time:");
		lblPrecedingTime.setBounds(170, 45, 85, 14);
		contentPane.add(lblPrecedingTime);

		frmTimeT = new JTextField();
		frmTimeT.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmTimeT.setBounds(170, 62, 86, 27);
		frmTimeT.setText("00:00:00");
		contentPane.add(frmTimeT);
		frmTimeT.setColumns(10);

		lbltoDate = new JLabel("\"To\" Date:");
		lbltoDate.setBounds(42, 93, 72, 14);
		contentPane.add(lbltoDate);

		toDateT = new JTextField("2014-10-01");
		toDateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		toDateT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				toDateT.setText(frmDateT.getText());
			}
		});
		toDateT.setBounds(21, 110, 122, 27);
		toDateT.setToolTipText("Inclusive date to which calculations should end");
		toDateT.setColumns(10);
		contentPane.add(toDateT);

		label = new JLabel("\"To\" Time:");
		label.setBounds(170, 93, 85, 14);
		contentPane.add(label);

		toTimeT = new JTextField();
		toTimeT.setFont(new Font("Tahoma", Font.BOLD, 12));
		toTimeT.setBounds(170, 110, 86, 27);
		toTimeT.setText("23:59:59");
		toTimeT.setColumns(10);
		contentPane.add(toTimeT);

		

		
	
		transPane = new JScrollPane();
		transPane.setBounds(20, 195, 875, 415);
		contentPane.add(transPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "TransID", "Trans_Type","Amount",
				"Trans_Date", "Trans_Time", "Emp_ID", "Cus_ID",
				 "Name" };

		final DefaultTableModel transModel = new DefaultTableModel(data, columns);

		
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		 transTable = new JTable(transModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false,false,  false, false,
					false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		transTable.setBounds(20, 375, 862, 243);

		transTable.getColumnModel().setColumnSelectionAllowed(true);
		transTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		transTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		transTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(75);
		transTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(100);
		transTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(100);
		transTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(80);
	
		transTable.getTableHeader().getColumnModel().getColumn(8)
				.setPreferredWidth(150);

		transTable.setRowHeight(23);

		
		

		transTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transTable.setSurrendersFocusOnKeystroke(true);
		transTable.setFillsViewportHeight(true);
		transTable.setColumnSelectionAllowed(true);
		transTable.setCellSelectionEnabled(true);
		transPane.setViewportView(transTable);

		btnPopAllTrans = new JButton("Populate ");
		btnPopAllTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(transModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true)

				{
					insertTrans(transModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnPopAllTrans.setBounds(916, 269, 132, 33);
		contentPane.add(btnPopAllTrans);

		btnUpdateAllTrans = new JButton("Update ");
		btnUpdateAllTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(transModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {

					insertTrans(transModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnUpdateAllTrans.setBounds(916, 335, 132, 33);
		contentPane.add(btnUpdateAllTrans);

		btnPrintTrans = new JButton("Print ");
		btnPrintTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(transModel, transTable);
			}
		});
		btnPrintTrans.setBounds(916, 395, 132, 33);
		contentPane.add(btnPrintTrans);

		btnCloseTrans = new JButton("Close ");
		btnCloseTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(transModel);

			}
		});
		btnCloseTrans.setBounds(916, 455, 132, 33);
		contentPane.add(btnCloseTrans);

		transTitle = new JPanel();
		transTitle.setBackground(Color.CYAN);
		transTitle.setBounds(21, 168, 223, 23);
		contentPane.add(transTitle);

		lbltransDetailsTable = new JLabel("All Transactions");
		transTitle.add(lbltransDetailsTable);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel_3.setBounds(21, 12, 223, 23);
		contentPane.add(panel_3);

		lblObligatoryInfosRequired = new JLabel("Obligatory Infos Required to Display Table");
		panel_3.add(lblObligatoryInfosRequired);

		btnHelp = new JButton("Help ?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane
						.showMessageDialog(null, "Help not available, Sorry");
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setBounds(980, 0, 89, 23);
		contentPane.add(btnHelp);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(276, 37, 274, 114);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		transIDT = new JTextField("");
		transIDT.setBounds(146, 10, 122, 27);
		panel_2.add(transIDT);
		transIDT.setToolTipText("");
		transIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		transIDT.setColumns(10);
		
		transTypeT = new JTextField("retailPaid");
		transTypeT.setBounds(10, 65, 122, 27);
		panel_2.add(transTypeT);
		transTypeT.setToolTipText("");
		transTypeT.setFont(new Font("Tahoma", Font.BOLD, 12));
		transTypeT.setColumns(10);
		
		empIDT = new JTextField("");
		empIDT.setBounds(146, 65, 122, 27);
		panel_2.add(empIDT);
		empIDT.setToolTipText("");
		empIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		empIDT.setColumns(10);
		
		JLabel lblTransId = new JLabel("Trans ID:");
		lblTransId.setBounds(81, 17, 55, 14);
		panel_2.add(lblTransId);
		
		JLabel lblTransType = new JLabel("Trans Type");
		lblTransType.setBounds(19, 48, 103, 14);
		panel_2.add(lblTransType);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(146, 48, 105, 14);
		panel_2.add(lblEmployeeId);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(576, 23, 416, 128);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setBounds(10, 0, 105, 14);
		panel.add(lblCustomerId);
		
		custIDT = new JTextField("");
		custIDT.setForeground(Color.BLUE);
		custIDT.setEditable(false);
		custIDT.setBounds(10, 17, 122, 27);
		panel.add(custIDT);
		custIDT.setToolTipText("");
		custIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		custIDT.setColumns(10);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 61, 105, 14);
		panel.add(lblAmount);
		
		amountT = new JTextField("");
		amountT.setForeground(Color.BLUE);
		amountT.setEditable(false);
		amountT.setBounds(10, 78, 122, 27);
		panel.add(amountT);
		amountT.setToolTipText("");
		amountT.setFont(new Font("Tahoma", Font.BOLD, 12));
		amountT.setColumns(10);
		
		lblName = new JLabel("Name");
		lblName.setBounds(155, 0, 105, 14);
		panel.add(lblName);
		
		nameT = new JTextField("");
		nameT.setForeground(Color.BLUE);
		nameT.setEditable(false);
		nameT.setBounds(155, 17, 254, 27);
		panel.add(nameT);
		nameT.setToolTipText("");
		nameT.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameT.setColumns(10);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(155, 61, 105, 14);
		panel.add(lblDate);
		
		dateT = new JTextField("");
		dateT.setForeground(Color.BLUE);
		dateT.setEditable(false);
		dateT.setBounds(155, 78, 122, 27);
		panel.add(dateT);
		dateT.setToolTipText("");
		dateT.setFont(new Font("Tahoma", Font.BOLD, 12));
		dateT.setColumns(10);
		
		timeT = new JTextField("");
		timeT.setForeground(Color.BLUE);
		timeT.setEditable(false);
		timeT.setBounds(287, 78, 122, 27);
		panel.add(timeT);
		timeT.setToolTipText("");
		timeT.setFont(new Font("Tahoma", Font.BOLD, 12));
		timeT.setColumns(10);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(287, 61, 105, 14);
		panel.add(lblTime);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(276, 12, 274, 23);
		contentPane.add(panel_1);
		
		JLabel lblObligatoryInfosRequired_1 = new JLabel("Obligatory Infos Required to View a Transaction");
		panel_1.add(lblObligatoryInfosRequired_1);
		
		btnViewTransaction = new JButton("View Transaction");
		btnViewTransaction.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				IntegerInput ii = new IntegerInput();
				String empIDs = empIDT.getText().trim().replaceAll("\\s", "");
				String transIDs = transIDT.getText().trim().replaceAll("\\s", "");
				String transType = transTypeT.getText().trim().replaceAll("\\s", "");
				
				
				
				if (!(transIDs.length() > 0)) {
					JOptionPane.showMessageDialog(null,
							"Enter Transaction ID.");
					transIDT.requestFocus();
				} else if (!(ii.isInteger(transIDs))) {
					JOptionPane
							.showMessageDialog(
									null,
									"Enter integer value for Transaction ID.",
									"Wrong Value",
									JOptionPane.ERROR_MESSAGE);
					transIDT.setText(null);
					transIDT.requestFocus();
				}
				else {
					int transID = Integer.parseInt(transIDs);
				if (!(empIDs.length() > 0)) {
					JOptionPane.showMessageDialog(null,
							"Enter employee ID.");
					empIDT.requestFocus();
				} else if (!(ii.isInteger(empIDs))) {
					JOptionPane
							.showMessageDialog(
									null,
									"Enter integer value for employee ID.",
									"Wrong Value",
									JOptionPane.ERROR_MESSAGE);
					empIDT.setText(null);
					empIDT.requestFocus();
				}
				else {
					int empID = Integer.parseInt(empIDs);
					if (!(transType.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Enter Transaction type ID.");
						transTypeT.requestFocus();
					}
					else {
						Transaction t = new Transaction();
					t.getTrans(transID, transType, empID);
						amountT.setText(t.getTrans(transID, transType, empID)[0]);
						dateT.setText(t.getTrans(transID, transType, empID)[1]);
						timeT.setText(t.getTrans(transID, transType, empID)[2]);
						custIDT.setText(t.getTrans(transID, transType, empID)[3]);
						nameT.setText(t.getTrans(transID, transType, empID)[4]);
						
						
					}}
				}
				
				
			}
		});
		btnViewTransaction.setBounds(346, 157, 164, 33);
		contentPane.add(btnViewTransaction);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				amountT.setText(null);
				dateT.setText(null);
				timeT.setText(null);
				nameT.setText(null);
				
				custIDT.setText(null);			
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(698, 156, 132, 33);
		contentPane.add(btnCancel);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(855, 0, 89, 23);
		contentPane.add(button);

	}

	boolean validateInput(DefaultTableModel model, String frmDateS,
			String toDateS, String frmTimeS, String toTimeS) {
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

				JOptionPane.showMessageDialog(null, "Enter END date.",
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

	void insertTrans(DefaultTableModel myModel, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
	
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

NumberFormat format = NumberFormat.getNumberInstance();
format.setGroupingUsed(true);
format.setMaximumFractionDigits(1);

Transaction t = new Transaction();

int noItems;// = p.transCount(frmDate, toDate, frmTime, toTime);

alltrans = t.getAllTrans(frmDate, toDate, frmTime, toTime);
noItems = alltrans.length;

for (int r = 0; r < noItems; r++) {

	myModel.insertRow(
			r,
			new Object[] {
					r + 1,
					alltrans[r][0],
					alltrans[r][1],
				//	alltrans[r][7],
					alltrans[r][2],
							alltrans[r][3],
								
					alltrans[r][4], alltrans[r][5],
					alltrans[r][6], alltrans[r][7] });

}
tableUpdateAckn(flag);

	}
/*
	void insertDailyGProfit(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
if(model.getRowCount() < 1)
	JOptionPane.showMessageDialog(null, "First display Daily trans and Try again");
else{
	
}
		
		
		Transaction s = new Transaction();

		// int noItems = s.dailytransCount(frmDate, toDate, frmTime, toTime);

		dailyGProfit = s.getDailyGProfit(frmDate, toDate, frmTime, toTime);
		int noItems = model.getRowCount();
		Transaction t = new Transaction();
		//String[] dateTime;
		NumberFormat format = NumberFormat.getNumberInstance();
		
		format.setMaximumFractionDigits(1);
		
		
		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailytrans[r][1]));
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1])), r, 3);
		
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1]) / Float.parseFloat(dailytrans[r][1])*100), r, 4);
			

		} */
	/*	if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}//}

	void insertDailytrans(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		Transaction p = new Transaction();

		// int noItems = s.dailytransCount(frmDate, toDate, frmTime, toTime);

		dailytrans = p.getDailytrans(frmDate, toDate, frmTime, toTime);
		unpaidDaily = p.unpaidDaily(frmDate, toDate, frmTime, toTime);
		int noItems = dailytrans.length;
		Transaction t = new Transaction();
		String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			dateTime = t.getTransDT(Integer.parseInt(dailytrans[r][1]));
			model.insertRow(r, new Object[] { r + 1, dailytrans[r][0],
					format.format(Float.parseFloat(dailytrans[r][1])), format.format(Float.parseFloat(unpaidDaily[r][1])), null

			});

		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}

	void insertMonthlytrans(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);

		Transaction p = new Transaction();

		// int noItems = s.monthlytransCount(frmDate, toDate, frmTime, toTime);

		monthlytrans = p.getMonthlytrans(frmDate, toDate, frmTime, toTime);
	unpaidMonthly=	p.unpaidMonthlyly(frmDate, toDate, frmTime, toTime);
		// JOptionPane.showMessageDialog(null, noItems + "len" +
		// monthlytrans.length);
		Transaction t = new Transaction();
		String[] dateTime;
		int noItems = monthlytrans.length;

		for (int r = 0; r < noItems; r++) {
			dateTime = t.getTransDT(Integer.parseInt(monthlytrans[r][1]));
			model.insertRow(r, new Object[] { r + 1, monthlytrans[r][0],
					monthlytrans[r][1], format.format(Float.parseFloat(monthlytrans[r][2])),format.format(Float.parseFloat(unpaidMonthly[r][2])) , null	});

		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}
	

*/
	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	}

	void closeTable(DefaultTableModel model) {

		int rowCount = model.getRowCount();
		if (rowCount == 0)
			JOptionPane.showMessageDialog(null, "Table is Emty");
		else
			for (int r = rowCount - 1; r >= 0; r--)
				model.removeRow(r);

	}

	void printTable(DefaultTableModel model, JTable table) {

		int rowCount = model.getRowCount();
		if (rowCount == 0)
			JOptionPane.showMessageDialog(null,
					"Table is empty. Please populate it and try again.");
		else

		{
			try {
				table.print();
			} catch (PrinterException e1) { // TODO Auto-generated catch
											// block
				JOptionPane.showMessageDialog(null,
						"Printing Error occurred:\n " + e1);
			}
		}

	}
	public boolean validateInputForTotal( String frmDateS,
			String toDateS, String frmTimeS, String toTimeS)
	{
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

				JOptionPane.showMessageDialog(null, "Enter END date.",
						"Input Error", JOptionPane.ERROR_MESSAGE);
				toDateT.requestFocus();
			} else if (!(id.isDate(toDateS))) {
				JOptionPane
						.showMessageDialog(
								null,
								"Enter END date in this format: yyyy-mm-dd ",
								"Wrong Date Format!",
								JOptionPane.ERROR_MESSAGE);

				toDateT.requestFocus();
				toDateT.setText(null);
			} else {

				toDate = Date.valueOf(toDateS);

				if (!(frmTimeS.length() > 0)) {

					JOptionPane.showMessageDialog(null,
							"Enter START time.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					frmTimeT.requestFocus();
				} else if (!(it.isTime(frmTimeS))) {
					JOptionPane
							.showMessageDialog(
									null,
									"Enter START time in this format: hh:mm:ss ",
									"Wrong Time Format!",
									JOptionPane.ERROR_MESSAGE);

					frmTimeT.requestFocus();
					frmTimeT.setText(null);
				} else {

					frmTime = Time.valueOf(frmTimeS);

					if (!(toTimeS.length() > 0)) {

						JOptionPane.showMessageDialog(null,
								"Enter END time.", "Input Error",
								JOptionPane.ERROR_MESSAGE);
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
					}
				}}}
		return validate;
	}
}
