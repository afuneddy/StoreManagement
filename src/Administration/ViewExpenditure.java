package Administration;

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
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import planettaStore.Expenditure;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewExpenditure extends JFrame {
	private String[][] Exp;
	private String[][] dailyExp;
	private String[][] monthlyExp;
	
	
	private JPanel contentPane;
	private JTextField frmDateT;
	private JTextField frmTimeT;
	private JLabel lbltoDate;
	private JTextField toDateT;
	private JLabel label;
	private JTextField toTimeT;
	private JTable dailyTable;
	private JTable monthlyTable;
	private JScrollPane ExpPane;
	private JTable ExpTable;
	private JButton btnPopulateDaily;
	private JButton btnPrintDaily;
	private JButton btnPopulateMonthly;
	private JButton btnPrintMonthly;
	private JTextField totExpT;
	private JButton btnPopulateExp;
	private JButton btnUpdateExp;
	private JButton btnPrintExp;
	private JButton btnCloseExp;
	private JPanel panel;
	private JLabel lblDailyExp;
	private JPanel panel_1;
	private JLabel lblMonthlyExp;
	private JPanel panel_2;
	private JLabel lblExpDetailsTable;
	private JPanel panel_3;
	private JLabel lblObligatoryInfosRequired;
	private JButton btnUpdateDaily;
	private JButton btnCloseDaily;
	private JButton btnUpdateMonthly;
	private JButton btnCloseMonthly;
	private JButton btnHelp;

	Date frmDate;
	Date toDate;
	Time frmTime;
	Time toTime;
	int flag;
	String currency = "XAF ";
	protected static int trackid;
	public static final int classID = 29;
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
					ViewExpenditure frame = new ViewExpenditure (trackid,roleid, empID, userName);
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
	 * @param empID2 
	 */
	public ViewExpenditure(final int trackID,int roleid, final int empID,   String userName) {
		setTitle("View Expenditure");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1095, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPrecedingDate = new JLabel("\"From\" Date:");
		lblPrecedingDate.setBounds(42, 45, 85, 14);
		contentPane.add(lblPrecedingDate);
		//Inventory in = new Inventory();
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

		final JToggleButton getTotExp = new JToggleButton("Total Expenditure");
		getTotExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
if(getTotExp.isSelected() == true)
{
				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				

				if (validateInputForTotal(frmDateS, toDateS, frmTimeS,
						toTimeS) == true)
				{
					NumberFormat format = NumberFormat.getNumberInstance();
					
					format.setMaximumFractionDigits(1);
					format.setGroupingUsed(true);
								Expenditure ex = new Expenditure();
							
								float total = ex.getTotalExp(frmDate, toDate,
										frmTime, toTime);


								totExpT.setText(currency+format.format(total));

						
				}}
else
	if(getTotExp.isSelected() == false)
		totExpT.setText(null);
			}
		});
		getTotExp
				.setToolTipText("Click to get monetary value of Exp based on the data provided above");
		getTotExp.setBounds(42, 169, 166, 30);
		contentPane.add(getTotExp);

		JScrollPane dailyPane = new JScrollPane();
		dailyPane.setBounds(306, 88, 356, 206);
		contentPane.add(dailyPane);

		Object[][] dailyData = new Object[][] {};
		String[] dailyColumns = new String[] { "No", "Date", "Amount"
				 };

		final DefaultTableModel dailyModel = new DefaultTableModel(dailyData,
				dailyColumns);

		contentPane.add(dailyPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		dailyTable = new JTable(dailyModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			private Class[] columnTypes = new Class[] { Integer.class,
					String.class, Integer.class};
			boolean[] columnEditables = new boolean[] {  false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public Class[] getColumnTypes() {
				return columnTypes;
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public void setColumnTypes(Class[] columnTypes) {
				this.columnTypes = columnTypes;
			}
		};

		dailyTable.setBounds(20, 375, 862, 243);

		dailyTable.getColumnModel().setColumnSelectionAllowed(true);
		dailyTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		dailyTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);

		dailyTable.setRowHeight(23);

		dailyTable.setFillsViewportHeight(true);
		dailyTable.setCellSelectionEnabled(true);
		dailyTable.setColumnSelectionAllowed(true);
		dailyPane.setViewportView(dailyTable);

		dailyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyTable.setSurrendersFocusOnKeystroke(true);

		JScrollPane monthlyPane = new JScrollPane();
		monthlyPane.setBounds(697, 88, 372, 200);
		contentPane.add(monthlyPane);

		Object[][] monthlyData = new Object[][] {};
		String[] monthlyColumns = new String[] { "No", "Month", "Year",
				"Amount" };

		final DefaultTableModel monthlyModel = new DefaultTableModel(
				monthlyData, monthlyColumns);

		contentPane.add(monthlyPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		 monthlyTable = new JTable(monthlyModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			private Class[] columnTypes = new Class[] { Integer.class,
					String.class, Integer.class, Integer.class };
			boolean[] columnEditables = new boolean[] {  false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public Class[] getColumnTypes() {
				return columnTypes;
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public void setColumnTypes(Class[] columnTypes) {
				this.columnTypes = columnTypes;
			}
		};

		monthlyTable.setBounds(20, 375, 862, 243);

		monthlyTable.getColumnModel().setColumnSelectionAllowed(true);
		monthlyTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		monthlyTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);

		monthlyTable.setRowHeight(23);

		monthlyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		monthlyTable.setSurrendersFocusOnKeystroke(true);
		monthlyTable.setCellSelectionEnabled(true);
		monthlyTable.setColumnSelectionAllowed(true);
		monthlyTable.setFillsViewportHeight(true);
		monthlyPane.setViewportView(monthlyTable);

		ExpPane = new JScrollPane();
		ExpPane.setBounds(21, 328, 827, 272);
		contentPane.add(ExpPane);

		Object[][] ExpData = new Object[][] {};
		String[] ExpColumns = new String[] { "No", "ExpID", "EmpID",
				"FirstName", "Amount", "Target's Name", "Exp Purpose", "Date", "Time" };

		final DefaultTableModel ExpModel = new DefaultTableModel(ExpData,
				ExpColumns);

		contentPane.add(ExpPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		 ExpTable = new JTable(ExpModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			private Class[] columnTypes = new Class[] { Integer.class,
					Integer.class, Integer.class, String.class, Integer.class,
					Integer.class, Integer.class, String.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public Class[] getColumnTypes() {
				return columnTypes;
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			public void setColumnTypes(Class[] columnTypes) {
				this.columnTypes = columnTypes;
			}
		};

		ExpTable.setBounds(20, 375, 862, 243);

		ExpTable.getColumnModel().setColumnSelectionAllowed(true);
		ExpTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		ExpTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);

		ExpTable.setRowHeight(23);

		ExpTable.setFillsViewportHeight(true);
		ExpTable.setCellSelectionEnabled(true);
		ExpTable.setColumnSelectionAllowed(true);
		ExpPane.setViewportView(ExpTable);

		ExpTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ExpTable.setSurrendersFocusOnKeystroke(true);
		ExpTable.setFillsViewportHeight(true);
		ExpTable.setColumnSelectionAllowed(true);
		ExpTable.setCellSelectionEnabled(true);
		ExpPane.setViewportView(ExpTable);

		btnPopulateDaily = new JButton("Populate");
		btnPopulateDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(dailyModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true)

					insertDailyExp(dailyModel, frmDate, toDate, frmTime,
							toTime);

			}
		});
		btnPopulateDaily.setBounds(306, 45, 85, 23);
		contentPane.add(btnPopulateDaily);

		btnPrintDaily = new JButton("Print");
		btnPrintDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(dailyModel, dailyTable);
			}
		});
		btnPrintDaily.setBounds(508, 45, 72, 23);
		contentPane.add(btnPrintDaily);

		btnPopulateMonthly = new JButton("Populate");
		btnPopulateMonthly.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(monthlyModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {
					insertMonthlyExp(monthlyModel, frmDate, toDate, frmTime,
							toTime);

				}

			}
		});
		btnPopulateMonthly.setBounds(697, 45, 89, 23);
		contentPane.add(btnPopulateMonthly);

		btnPrintMonthly = new JButton("Print");
		btnPrintMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(monthlyModel, monthlyTable);
			}
		});
		btnPrintMonthly.setBounds(885, 45, 85, 23);
		contentPane.add(btnPrintMonthly);

		totExpT = new JTextField();
		totExpT.setForeground(Color.BLUE);
		totExpT.setFont(new Font("Tahoma", Font.BOLD, 12));
		totExpT.setEditable(false);
		totExpT.setBounds(42, 202, 166, 30);
		contentPane.add(totExpT);
		totExpT.setColumns(10);

		btnPopulateExp = new JButton("Populate ");
		btnPopulateExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(ExpModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true)

				{
					insertExp(ExpModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnPopulateExp.setBounds(126, 635, 132, 23);
		contentPane.add(btnPopulateExp);

		btnUpdateExp = new JButton("Update");
		btnUpdateExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(ExpModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {

					insertExp(ExpModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnUpdateExp.setBounds(306, 635, 122, 23);
		contentPane.add(btnUpdateExp);

		btnPrintExp = new JButton("Print");
		btnPrintExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(ExpModel, ExpTable);
			}
		});
		btnPrintExp.setBounds(497, 635, 106, 23);
		contentPane.add(btnPrintExp);

		btnCloseExp = new JButton("Close ");
		btnCloseExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(ExpModel);

			}
		});
		btnCloseExp.setBounds(644, 635, 117, 23);
		contentPane.add(btnCloseExp);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(380, 11, 223, 23);
		contentPane.add(panel);

		lblDailyExp = new JLabel("Daily Expenditure");
		panel.add(lblDailyExp);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(733, 11, 223, 23);
		contentPane.add(panel_1);

		lblMonthlyExp = new JLabel("Monthly Expenditure");
		panel_1.add(lblMonthlyExp);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(21, 294, 223, 23);
		contentPane.add(panel_2);

		lblExpDetailsTable = new JLabel("Expenditure Details Table");
		panel_2.add(lblExpDetailsTable);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel_3.setBounds(21, 12, 223, 23);
		contentPane.add(panel_3);

		lblObligatoryInfosRequired = new JLabel("Obligatory Infos Required");
		panel_3.add(lblObligatoryInfosRequired);

		btnUpdateDaily = new JButton("Update");
		btnUpdateDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(dailyModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {

					insertDailyExp(dailyModel, frmDate, toDate, frmTime,
							toTime);

				}
			}
		});
		btnUpdateDaily.setBounds(409, 45, 89, 23);
		contentPane.add(btnUpdateDaily);

		btnCloseDaily = new JButton("Close");
		btnCloseDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(dailyModel);

			}
		});
		btnCloseDaily.setBounds(590, 45, 72, 23);
		contentPane.add(btnCloseDaily);

		btnUpdateMonthly = new JButton("Update");
		btnUpdateMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(monthlyModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {
					insertMonthlyExp(monthlyModel, frmDate, toDate, frmTime,
							toTime);

				}

			}
		});
		btnUpdateMonthly.setBounds(796, 45, 79, 23);
		contentPane.add(btnUpdateMonthly);

		btnCloseMonthly = new JButton("Close");
		btnCloseMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(monthlyModel);

			}
		});
		btnCloseMonthly.setBounds(980, 45, 89, 23);
		contentPane.add(btnCloseMonthly);

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
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(619, 11, 89, 23);
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

	void insertExp(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		
		Expenditure ex = new Expenditure();

		int noItems;// = s.ExpCount(frmDate, toDate, frmTime, toTime);

		Exp = ex.getExp(frmDate, toDate, frmTime, toTime);
noItems =Exp.length;
		//Transaction t = new Transaction();
		//String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(Exp[r][1]));
			model.insertRow(r, new Object[] { r + 1, Exp[r][0], Exp[r][1],
					Exp[r][2], format.format(Float.parseFloat(Exp[r][3])), Exp[r][4], Exp[r][5],
					Exp[r][6], Exp[r][7] });
		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}

	
	void insertDailyExp(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		Expenditure s = new Expenditure();

		// int noItems = s.dailyExpCount(frmDate, toDate, frmTime, toTime);

		dailyExp = s.getDailyExp(frmDate, toDate, frmTime, toTime);
		int noItems = dailyExp.length;
		//Transaction t = new Transaction();
		//String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailyExp[r][1]));
			model.insertRow(r, new Object[] { r + 1, dailyExp[r][0],
					format.format(Float.parseFloat(dailyExp[r][1]))

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

	void insertMonthlyExp(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);

		Expenditure s = new Expenditure();

		// int noItems = s.monthlyExpCount(frmDate, toDate, frmTime, toTime);

		monthlyExp = s.getMonthlyExp(frmDate, toDate, frmTime, toTime);
		// JOptionPane.showMessageDialog(null, noItems + "len" +
		// monthlyExp.length);
		//Transaction t = new Transaction();
		//String[] dateTime;
		int noItems = monthlyExp.length;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(monthlyExp[r][1]));
			model.insertRow(r, new Object[] { r + 1, monthlyExp[r][0],
					monthlyExp[r][1], format.format(Float.parseFloat(monthlyExp[r][2]))	});

		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}
	

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
