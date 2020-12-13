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

import planettaStore.WHouseInventory;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class WholesaleSalesAnalysis extends JFrame {
	private String[][] sales;
	private String[][] dailySales;
	private String[][] monthlySales;
	
	private String[][] dailyGProfit;
	private String[][] monthlyGProfit;
	private JPanel contentPane;
	private JTextField frmDateT;
	private JTextField frmTimeT;
	private JLabel lbltoDate;
	private JTextField toDateT;
	private JLabel label;
	private JTextField toTimeT;
	private JTable dailyTable;
	private JTable monthlyTable;
	private JScrollPane salesPane;
	private JTable salesTable;
	private JButton btnPopulateDaily;
	private JButton btnPrintDaily;
	private JButton btnPopulateMonthly;
	private JButton btnPrintMonthly;
	private JTextField totSalesT;
	private JButton btnPopulateSales;
	private JButton btnUpdateSales;
	private JButton btnPrintSales;
	private JButton btnCloseSales;
	private JPanel panel;
	private JLabel lblDailySales;
	private JPanel panel_1;
	private JLabel lblMonthlySales;
	private JPanel panel_2;
	private JLabel lblSalesDetailsTable;
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
	
	private JTextField totalGPT;
	protected static int trackid;
	
	public static final int classID = 27;
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
					WholesaleSalesAnalysis frame = new WholesaleSalesAnalysis(trackid,roleid, empID, userName);
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
	public WholesaleSalesAnalysis(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Wholesale Sales & Gross Profit Analysis");
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

		final JToggleButton getTotSales = new JToggleButton("Total Sales");
		getTotSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
if(getTotSales.isSelected() == true)
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
					//WSaleAccess w = new WSaleAccess();
								WHouseInventory whi = new WHouseInventory();
							
								
								float total = whi.getTotalSales(frmDate, toDate,
										frmTime, toTime);


								totSalesT.setText(currency+format.format(total));

						
				}}
else
	if(getTotSales.isSelected() == false)
		totSalesT.setText(null);
			}
		});
		getTotSales
				.setToolTipText("Click to get monetary value of sales based on the data provided above");
		getTotSales.setBounds(21, 166, 117, 30);
		contentPane.add(getTotSales);

		JScrollPane dailyPane = new JScrollPane();
		dailyPane.setBounds(306, 88, 356, 206);
		contentPane.add(dailyPane);

		Object[][] dailyData = new Object[][] {};
		String[] dailyColumns = new String[] { "No", "Date", "Amount",
				"Gross Profit", "Percent" };

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
				"Amount", "Gross Profit", "Percent" };

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

		salesPane = new JScrollPane();
		salesPane.setBounds(21, 328, 827, 272);
		contentPane.add(salesPane);

		Object[][] salesData = new Object[][] {};
		String[] salesColumns = new String[] { "No", "ProdID", "TransID",
				"wsUnit",	"Description", "wsPrice", "Qty", "Total", "Date", "Time" };

		final DefaultTableModel salesModel = new DefaultTableModel(salesData,
				salesColumns);

		contentPane.add(salesPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		 salesTable = new JTable(salesModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			private Class[] columnTypes = new Class[] { Integer.class,
					Integer.class, Integer.class, String.class, Integer.class,
					Integer.class, Integer.class, String.class, String.class,  String.class  };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false };

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

		salesTable.setBounds(20, 375, 862, 243);

		salesTable.getColumnModel().setColumnSelectionAllowed(true);
		salesTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		salesTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(40);
		salesTable.getTableHeader().getColumnModel().getColumn(2)
		.setPreferredWidth(55);
		salesTable.getTableHeader().getColumnModel().getColumn(3)
		.setPreferredWidth(40);
		salesTable.getTableHeader().getColumnModel().getColumn(4)
		.setPreferredWidth(80);
		salesTable.getTableHeader().getColumnModel().getColumn(6)
		.setPreferredWidth(35
				);

		salesTable.setRowHeight(23);

		salesTable.setFillsViewportHeight(true);
		salesTable.setCellSelectionEnabled(true);
		salesTable.setColumnSelectionAllowed(true);
		salesPane.setViewportView(salesTable);

		salesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		salesTable.setSurrendersFocusOnKeystroke(true);
		salesTable.setFillsViewportHeight(true);
		salesTable.setColumnSelectionAllowed(true);
		salesTable.setCellSelectionEnabled(true);
		salesPane.setViewportView(salesTable);

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

					insertDailySales(dailyModel, frmDate, toDate, frmTime,
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
					insertMonthlySales(monthlyModel, frmDate, toDate, frmTime,
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

		totSalesT = new JTextField();
		totSalesT.setForeground(Color.BLUE);
		totSalesT.setFont(new Font("Tahoma", Font.BOLD, 12));
		totSalesT.setEditable(false);
		totSalesT.setBounds(138, 166, 166, 30);
		contentPane.add(totSalesT);
		totSalesT.setColumns(10);

		btnPopulateSales = new JButton("Populate Sales");
		btnPopulateSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(salesModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true)

				{
					insertSales(salesModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnPopulateSales.setBounds(126, 635, 132, 23);
		contentPane.add(btnPopulateSales);

		btnUpdateSales = new JButton("Update Sales");
		btnUpdateSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String frmDateS = frmDateT.getText().trim()
						.replaceAll("\\s", "");
				String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
				String frmTimeS = frmTimeT.getText().trim()
						.replaceAll("\\s", "");
				String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");

				if (validateInput(salesModel, frmDateS, toDateS, frmTimeS,
						toTimeS) == true) {

					insertSales(salesModel, frmDate, toDate, frmTime, toTime);

				}
			}
		});
		btnUpdateSales.setBounds(306, 635, 122, 23);
		contentPane.add(btnUpdateSales);

		btnPrintSales = new JButton("Print Sales");
		btnPrintSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				printTable(salesModel, salesTable);
			}
		});
		btnPrintSales.setBounds(497, 635, 106, 23);
		contentPane.add(btnPrintSales);

		btnCloseSales = new JButton("Close Sales");
		btnCloseSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				closeTable(salesModel);

			}
		});
		btnCloseSales.setBounds(644, 635, 117, 23);
		contentPane.add(btnCloseSales);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(380, 11, 223, 23);
		contentPane.add(panel);

		lblDailySales = new JLabel("Daily Sales");
		panel.add(lblDailySales);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(733, 11, 223, 23);
		contentPane.add(panel_1);

		lblMonthlySales = new JLabel("Monthly Sales");
		panel_1.add(lblMonthlySales);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(21, 294, 223, 23);
		contentPane.add(panel_2);

		lblSalesDetailsTable = new JLabel("Sales Details Table");
		panel_2.add(lblSalesDetailsTable);

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

					insertDailySales(dailyModel, frmDate, toDate, frmTime,
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
					insertMonthlySales(monthlyModel, frmDate, toDate, frmTime,
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
		
		final JToggleButton getTotalGP = new JToggleButton("Total G.Profit");
		getTotalGP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
if(getTotalGP.isSelected() == true)
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
							
								WHouseInventory whi = new WHouseInventory();
								 
								float total = whi.getTotalGProfit(frmDate, toDate,
										frmTime, toTime);

							

								totalGPT.setText(currency+format.format(total));

						
				}}
else
	if(getTotalGP.isSelected() == false)
		totalGPT.setText(null);
				
			}
		});
		getTotalGP.setToolTipText("Click to view total Gross Profit based on the data provided above");
		getTotalGP.setBounds(21, 213, 117, 30);
		contentPane.add(getTotalGP);
		
		totalGPT = new JTextField();
		totalGPT.setForeground(Color.BLUE);
		totalGPT.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalGPT.setEditable(false);
		totalGPT.setColumns(10);
		totalGPT.setBounds(138, 213, 166, 30);
		contentPane.add(totalGPT);
		
		final JToggleButton btnViewDailyGross = new JToggleButton("View Daily Gross Profit");
		btnViewDailyGross.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(btnViewDailyGross.isSelected() == true)
				{
					
								String frmDateS = frmDateT.getText().trim()
										.replaceAll("\\s", "");
								String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
								String frmTimeS = frmTimeT.getText().trim()
										.replaceAll("\\s", "");
								String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");
								

								if (validateInput(dailyModel, frmDateS, toDateS, frmTimeS,
										toTimeS) == true) {

									insertDailySales(dailyModel, frmDate, toDate, frmTime,
											toTime);}
								
								if (validateInputForTotal(frmDateS, toDateS, frmTimeS,
										toTimeS) == true)
									if(dailyModel.getRowCount() > 0)
									btnViewDailyGross.setText("Hide Daily Gross Profit");
								
								insertDailyGProfit(dailyModel, frmDate, toDate,
										frmTime, toTime); 
								
				}
				else
				
					if(btnViewDailyGross.isSelected() == false)
					{
				int rowCount = dailyModel.getRowCount();
						for (int r = rowCount - 1; r >= 0; r--)
							{dailyModel.setValueAt(0, r, 3);

						dailyModel.setValueAt(0, r, 4);}
						btnViewDailyGross.setText("Show Daily Gross Profit");
					}
				
				
				
			}
		});
		btnViewDailyGross.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewDailyGross.setToolTipText("Click to view daily gross profit on the Daily Sales Table above");
		btnViewDailyGross.setBounds(858, 328, 211, 51);
		contentPane.add(btnViewDailyGross);
		
		final JToggleButton btnViewMonthlyGross = new JToggleButton("View Monthly Gross Profit");
		btnViewMonthlyGross.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(btnViewMonthlyGross.isSelected() == true)
				{
				
								String frmDateS = frmDateT.getText().trim()
										.replaceAll("\\s", "");
								String toDateS = toDateT.getText().trim().replaceAll("\\s", "");
								String frmTimeS = frmTimeT.getText().trim()
										.replaceAll("\\s", "");
								String toTimeS = toTimeT.getText().trim().replaceAll("\\s", "");
								
								if (validateInput(monthlyModel, frmDateS, toDateS, frmTimeS,
										toTimeS) == true) {
									insertMonthlySales(monthlyModel, frmDate, toDate, frmTime,
											toTime);}
								
								if (validateInputForTotal(frmDateS, toDateS, frmTimeS,
										toTimeS) == true)
									if(monthlyModel.getRowCount() > 0)
									btnViewMonthlyGross.setText("Hide Monthly Gross Profit");
								
								insertMonthlyGProfit(monthlyModel, frmDate, toDate,
										frmTime, toTime); 
								
				}
				else
				
					if(btnViewMonthlyGross.isSelected() == false)
					{
				int rowCount = monthlyModel.getRowCount();
						for (int r = rowCount - 1; r >= 0; r--)
							{monthlyModel.setValueAt(0, r, 4);

						monthlyModel.setValueAt(0, r, 5);}
						btnViewMonthlyGross.setText("Show Monthly Gross Profit");
					}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnViewMonthlyGross.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewMonthlyGross.setToolTipText("Click to view monthly gross profit on the Monthly Sales Table above");
		btnViewMonthlyGross.setBounds(858, 402, 211, 51);
		contentPane.add(btnViewMonthlyGross);
		
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

	void insertSales(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		
		WHouseInventory s = new WHouseInventory();

		int noItems;// = s.salesCount(frmDate, toDate, frmTime, toTime);

		sales = s.getSales(frmDate, toDate, frmTime, toTime);
noItems =sales.length;
		//Transaction t = new Transaction();
		//String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(sales[r][1]));
			model.insertRow(r, new Object[] { r + 1, sales[r][0], sales[r][1],
					sales[r][2],sales[r][3], format.format(Float.parseFloat(sales[r][4])), sales[r][5], format.format(Float.parseFloat(sales[r][6])),
					sales[r][7], sales[r][8]});
		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}

	void insertDailyGProfit(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
/*if(model.getRowCount() < 1)
	JOptionPane.showMessageDialog(null, "First display Daily Sales and Try again");
else{*/
		
		
		WHouseInventory s = new WHouseInventory();

		// int noItems = s.dailySalesCount(frmDate, toDate, frmTime, toTime);

		dailyGProfit = s.getDailyGProfit(frmDate, toDate, frmTime, toTime);
		int noItems = model.getRowCount();
		//Transaction t = new Transaction();
		//String[] dateTime;
		NumberFormat format = NumberFormat.getNumberInstance();
		
		format.setMaximumFractionDigits(1);
		
		
		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailySales[r][1]));
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1])), r, 3);
		
			model.setValueAt(format.format(Float.parseFloat(dailyGProfit[r][1]) / Float.parseFloat(dailySales[r][1])*100), r, 4);
			

		}
	/*	if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);*/

	}//}

	void insertDailySales(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		WHouseInventory s = new WHouseInventory();

		// int noItems = s.dailySalesCount(frmDate, toDate, frmTime, toTime);

		dailySales = s.getDailySales(frmDate, toDate, frmTime, toTime);
		int noItems = dailySales.length;
		//Transaction t = new Transaction();
		//String[] dateTime;

		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailySales[r][1]));
			model.insertRow(r, new Object[] { r + 1, dailySales[r][0],
					format.format(Float.parseFloat(dailySales[r][1])), 0, 0

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

	void insertMonthlySales(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);

		WHouseInventory s = new WHouseInventory();

		// int noItems = s.monthlySalesCount(frmDate, toDate, frmTime, toTime);

		monthlySales = s.getMonthlySales(frmDate, toDate, frmTime, toTime);
		
		int noItems = monthlySales.length;

		for (int r = 0; r < noItems; r++) {
		//	dateTime = t.getTransDT(Integer.parseInt(monthlySales[r][1]));
			model.insertRow(r, new Object[] { r + 1, monthlySales[r][0],
					monthlySales[r][1], format.format(Float.parseFloat(monthlySales[r][2])), 0, 0	});

		}
		if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);

	}
	void insertMonthlyGProfit(DefaultTableModel model, Date frmDate, Date toDate,
			Time frmTime, Time toTime) {
/*if(model.getRowCount() < 1)
	JOptionPane.showMessageDialog(null, "First populate Monthly Sales Table and Try again");
else{*/
		WHouseInventory s = new WHouseInventory();

		// int noItems = s.dailySalesCount(frmDate, toDate, frmTime, toTime);

		monthlyGProfit = s.getMonthlyGProfit(frmDate, toDate, frmTime, toTime);
		int noItems = model.getRowCount();
		//Transaction t = new Transaction();
		//String[] dateTime;
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(true);
		format.setMaximumFractionDigits(1);
		
		
		for (int r = 0; r < noItems; r++) {
			//dateTime = t.getTransDT(Integer.parseInt(dailySales[r][1]));
			model.setValueAt(format.format(Float.parseFloat(monthlyGProfit[r][2])), r, 4);
		
			model.setValueAt(format.format(Float.parseFloat(monthlyGProfit[r][2]) / Float.parseFloat(monthlySales[r][2])*100), r, 5);
			

		}
		/*if (!(model.getRowCount() > 0))
			JOptionPane
					.showMessageDialog(
							null,
							"Nothing to display based on the date and time intervals you provided. \nRevise and try again.");
		else
			tableUpdateAckn(flag);*/

	}//}


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
