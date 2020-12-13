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

import planettaStore.Purchase;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewPurchase extends JFrame {
	private String[][] allPurch;
	private String[][] dailyPurch;
	private String[][] monthlyPurch;
	
	
	private JPanel contentPane;
	private JTextField frmDateT;
	private JTextField frmTimeT;
	private JLabel lbltoDate;
	private JTextField toDateT;
	private JLabel label;
	private JTextField toTimeT;
	private JTable dailyTable;
	private JTable monthlyTable;
	private JScrollPane PurchPane;
	private JTable purchTable;
	private JButton btnPopulateDaily;
	private JButton btnPrintDaily;
	private JButton btnPopulateMonthly;
	private JButton btnPrintMonthly;
	private JTextField totPurchT;
	private JButton btnPopAllPurch;
	private JButton btnUpdateAllPurch;
	private JButton btnPrintAllPurch;
	private JButton btnCloseAllPurch;
	private JPanel panel;
	private JLabel lblDailyPurch;
	private JPanel panel_1;
	private JLabel lblMonthlyPurch;
	private JPanel panel_2;
	private JLabel lblPurchDetailsTable;
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
	String currency = "XAF ";
	int flag;
	private String[][] unpaidMonthly;
	private String[][] unpaidDaily;
	protected static int trackid;
	public static final int classID = 30;
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
					ViewPurchase frame = new ViewPurchase(trackid,roleid, empID, userName);
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
	public ViewPurchase(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Purchase Analysis");
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

		final JToggleButton getTotPurch = new JToggleButton("Total Purchase");
		getTotPurch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
if(getTotPurch.isSelected() == true)
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
								Purchase p = new Purchase();
							
								float total = p.getTotalPurch(frmDate, toDate,
										frmTime, toTime);


								totPurchT.setText(currency+format.format(total));

						
				}}
else
	if(getTotPurch.isSelected() == false)
		totPurchT.setText(null);
			}
		});
		getTotPurch
				.setToolTipText("Click to get monetary value of Purchase based on the data provided above");
		getTotPurch.setBounds(56, 173, 142, 30);
		contentPane.add(getTotPurch);

		JScrollPane dailyPane = new JScrollPane();
		dailyPane.setBounds(306, 88, 356, 206);
		contentPane.add(dailyPane);

		Object[][] dailyData = new Object[][] {};
		String[] dailyColumns = new String[] { "No", "Date", "Amount",
				"Unpaid", "Remark" };

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
					String.class, Integer.class, Integer.class, Float.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "rawtypes", "unused" })
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
				"Amount", "Unpaid", "Remark" };

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
					String.class, Integer.class, Integer.class, Integer.class,
					Float.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "rawtypes", "unused" })
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

		PurchPane = new JScrollPane();
		PurchPane.setBounds(21, 328, 827, 272);
		contentPane.add(PurchPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Purch ID", "Sup ID",
				"Bz Name", "Total Cost", "Tot Advance", "Owed",
				"dueDate", "Purch.Date", "Reg Time " };

		final DefaultTableModel purchModel = new DefaultTableModel(data, columns);

		
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		 purchTable = new JTable(purchModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					Integer.class, String.class, Integer.class, Integer.class,
					Integer.class, String.class, String.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		purchTable.setBounds(20, 375, 862, 243);

		purchTable.getColumnModel().setColumnSelectionAllowed(true);
		purchTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		purchTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		purchTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(75);
		purchTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(150);
		purchTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(100);
		purchTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(80);
		purchTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		purchTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(100);

		purchTable.setRowHeight(23);

		
		

		purchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		purchTable.setSurrendersFocusOnKeystroke(true);
		purchTable.setFillsViewportHeight(true);
		purchTable.setColumnSelectionAllowed(true);
		purchTable.setCellSelectionEnabled(true);
		PurchPane.setViewportView(purchTable);

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

					insertDailyPurch(dailyModel, frmDate, toDate, frmTime,
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
					insertMonthlyPurch(monthlyModel, frmDate, toDate, frmTime,
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

		totPurchT = new JTextField();
		totPurchT.setForeground(Color.BLUE);
		totPurchT.setFont(new Font("Tahoma", Font.BOLD, 12));
		totPurchT.setEditable(false);
		totPurchT.setBounds(42, 207, 177, 30);
		contentPane.add(totPurchT);
		totPurchT.setColumns(10);

		btnPopAllPurch = new JButton("Populate ");
		btnPopAllPurch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(purchModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true)

				{
					insertPurch(purchModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnPopAllPurch.setBounds(126, 635, 132, 23);
		contentPane.add(btnPopAllPurch);

		btnUpdateAllPurch = new JButton("Update ");
		btnUpdateAllPurch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(purchModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {

					insertPurch(purchModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnUpdateAllPurch.setBounds(306, 635, 122, 23);
		contentPane.add(btnUpdateAllPurch);

		btnPrintAllPurch = new JButton("Print ");
		btnPrintAllPurch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(purchModel, purchTable);
			}
		});
		btnPrintAllPurch.setBounds(497, 635, 106, 23);
		contentPane.add(btnPrintAllPurch);

		btnCloseAllPurch = new JButton("Close ");
		btnCloseAllPurch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(purchModel);

			}
		});
		btnCloseAllPurch.setBounds(644, 635, 117, 23);
		contentPane.add(btnCloseAllPurch);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(380, 11, 223, 23);
		contentPane.add(panel);

		lblDailyPurch = new JLabel("Daily Puchase");
		panel.add(lblDailyPurch);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(733, 11, 223, 23);
		contentPane.add(panel_1);

		lblMonthlyPurch = new JLabel("Monthly Purchase");
		panel_1.add(lblMonthlyPurch);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(21, 294, 223, 23);
		contentPane.add(panel_2);

		lblPurchDetailsTable = new JLabel("All Purchase");
		panel_2.add(lblPurchDetailsTable);

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

					insertDailyPurch(dailyModel, frmDate, toDate, frmTime,
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
					insertMonthlyPurch(monthlyModel, frmDate, toDate, frmTime,
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
		button.setBounds(623, 11, 89, 23);
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

	void insertPurch(DefaultTableModel myModel, Date frmDate, Date toDate,
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

Purchase p = new Purchase();

int noItems;// = p.PurchCount(frmDate, toDate, frmTime, toTime);

allPurch = p.getPurch(frmDate, toDate, frmTime, toTime);
noItems = allPurch.length;

for (int r = 0; r < noItems; r++) {

	myModel.insertRow(
			r,
			new Object[] {
					r + 1,
					allPurch[r][0],
					allPurch[r][1],
					allPurch[r][7],
					format.format(Float.parseFloat(allPurch[r][2])),
							format.format(Float.parseFloat(allPurch[r][3])),
									format.format(Float.parseFloat(allPurch[r][2])
							- Float.parseFloat(allPurch[r][3])),
					allPurch[r][4], allPurch[r][5],
					allPurch[r][6] });

}
tableUpdateAckn(flag);

	}

	/*void insertDailyGProfit(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
/*if(model.getRowCount() < 1)
	JOptionPane.showMessageDialog(null, "First display Daily Purch and Try again");
else{*//*
		
		
		Purchase s = new Purchase();

		// int noItems = s.dailyPurchCount(frmDate, toDate, frmTime, toTime);

		dailyGProfit = s.getDailyGProfit(frmDate, toDate, frmTime, toTime);
		int noItems = model.getRowCount();
		Transaction t = new Transaction();
		//String[] dateTime;
		NumberFormat format = NumberFormat.getNumberInstance();
		
		format.setMaximumFractionDigits(1);
		
		
		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailyPurch[r][1]));
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1])), r, 3);
		
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1]) / Float.parseFloat(dailyPurch[r][1])*100), r, 4);
			

		} */
	/*	if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}//}*/

	void insertDailyPurch(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		Purchase p = new Purchase();

		// int noItems = s.dailyPurchCount(frmDate, toDate, frmTime, toTime);

		dailyPurch = p.getDailyPurch(frmDate, toDate, frmTime, toTime);
		unpaidDaily = p.unpaidDaily(frmDate, toDate, frmTime, toTime);
		int noItems = dailyPurch.length;
		//Transaction t = new Transaction();
		//String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailyPurch[r][1]));
			model.insertRow(r, new Object[] { r + 1, dailyPurch[r][0],
					format.format(Float.parseFloat(dailyPurch[r][1])), format.format(Float.parseFloat(unpaidDaily[r][1])), null

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

	void insertMonthlyPurch(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);

		Purchase p = new Purchase();

		// int noItems = s.monthlyPurchCount(frmDate, toDate, frmTime, toTime);

		monthlyPurch = p.getMonthlyPurch(frmDate, toDate, frmTime, toTime);
	unpaidMonthly=	p.unpaidMonthlyly(frmDate, toDate, frmTime, toTime);
		// JOptionPane.showMessageDialog(null, noItems + "len" +
		// monthlyPurch.length);
		//Transaction t = new Transaction();
		//String[] dateTime;
		int noItems = monthlyPurch.length;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(monthlyPurch[r][1]));
			model.insertRow(r, new Object[] { r + 1, monthlyPurch[r][0],
					monthlyPurch[r][1], format.format(Float.parseFloat(monthlyPurch[r][2])),format.format(Float.parseFloat(unpaidMonthly[r][2])) , null	});

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
