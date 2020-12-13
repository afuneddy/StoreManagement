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
import java.awt.print.PrinterException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Supplier;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class ViewSupplier extends JFrame {

	private JPanel contentPane;
	private JTextField supIDT;
	String[][] allSup;
	int supID;
	private JLabel lblSupplierId;
	private JTextField fNameT;
	private JLabel lblFirstName;
	private JTextField otherNamesT;
	private JLabel lblOtherNames;
	private JTextField phone1T;
	private JLabel lblPhone1;
	private JTextField addressT;
	private JLabel lblAddress;
	private JTextField emailT;
	private JLabel lblEmail;
	private JTextField sampleProdT;
	private JLabel sampleProdslb;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JComboBox<String> comboBox;
	private JButton btnDisplayInfo;
	String[][] lookup;
	private JButton btnPopulateTable;
	private JButton btnUpdateTable;
	private JButton btnCloseTable;
	private JButton btnPrintTable;
	private JLabel lblGrade;
	private JSpinner gradeSpin;
	int grade;
	private JTextField phone2T;
	private JLabel lblPhoneLine;
	private JTextField bzNameT;
	private JLabel lblBusinessName;
	protected static int trackid;
	public static final int classID = 24;
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
					ViewSupplier frame = new ViewSupplier(trackid,roleid, empID, userName);
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
	public ViewSupplier(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("View Supplier");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1086, 750);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		supIDT = new JTextField();
		supIDT.setToolTipText("Start typing name of Supplier and select below");
		supIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Supplier em = new Supplier();
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

					lookup = em.searchString(text);
int size=lookup.length;

if (size==0) {

		JOptionPane.showMessageDialog(null,
				"No Supplier name starting with" + "\""
						+ text + "\"  " + "is found."
						+ "\nTry other names.");
		supIDT.setText(null);
		supIDT.requestFocus();

	
} else 
					for (int r = 0; r < lookup.length; r++) {

						
							comboBox.setSelectedIndex(0);
					

							String cmBoxItem = lookup[r][0] + " - "
									+ lookup[r][1] + " - " + lookup[r][2];

							comboBox.addItem(cmBoxItem);
						

					}

				}

			}
		});
		supIDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		supIDT.setColumns(10);
		supIDT.setBounds(125, 66, 205, 23);
		contentPane.add(supIDT);

		lblSupplierId = new JLabel("Supplier ID:");
		lblSupplierId.setBounds(41, 67, 102, 20);
		contentPane.add(lblSupplierId);

		fNameT = new JTextField();
		fNameT.setForeground(Color.BLUE);
		fNameT.setColumns(10);
		fNameT.setBounds(125, 136, 205, 23);
		contentPane.add(fNameT);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(41, 137, 102, 20);
		contentPane.add(lblFirstName);

		otherNamesT = new JTextField();
		otherNamesT.setForeground(Color.BLUE);
		otherNamesT.setEditable(false);
		otherNamesT.setColumns(10);
		otherNamesT.setBounds(485, 63, 205, 23);
		contentPane.add(otherNamesT);

		lblOtherNames = new JLabel("Other Names:");
		lblOtherNames.setBounds(393, 67, 82, 20);
		contentPane.add(lblOtherNames);

		phone1T = new JTextField();
		phone1T.setForeground(Color.BLUE);
		phone1T.setColumns(10);
		phone1T.setBounds(485, 136, 205, 23);
		contentPane.add(phone1T);

		lblPhone1 = new JLabel("Phone line 1:");
		lblPhone1.setBounds(393, 137, 82, 20);
		contentPane.add(lblPhone1);

		addressT = new JTextField();
		addressT.setForeground(Color.BLUE);
		addressT.setColumns(10);
		addressT.setBounds(798, 66, 217, 23);
		contentPane.add(addressT);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(710, 67, 58, 20);
		contentPane.add(lblAddress);

		emailT = new JTextField();
		emailT.setForeground(Color.BLUE);
		emailT.setColumns(10);
		emailT.setBounds(798, 100, 217, 23);
		contentPane.add(emailT);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(710, 101, 51, 20);
		contentPane.add(lblEmail);

		sampleProdT = new JTextField();
		sampleProdT.setForeground(Color.BLUE);
		sampleProdT.setColumns(10);
		sampleProdT.setBounds(798, 134, 217, 59);
		contentPane.add(sampleProdT);

		sampleProdslb = new JLabel("Sample Products:");
		sampleProdslb.setFont(new Font("Tahoma", Font.BOLD, 10));
		sampleProdslb.setBounds(710, 137, 94, 20);
		contentPane.add(sampleProdslb);

		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(41, 11, 392, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("View  Supplier");
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
		btnNewButton.setBounds(899, 11, 89, 23);
		contentPane.add(btnNewButton);

		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Cancel update and reset fields");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();
				supIDT.setEditable(true);

			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(920, 218, 89, 23);
		contentPane.add(btnCancel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(26, 252, 244, 31);
		contentPane.add(panel_2);

		JLabel lblSupplierTable = new JLabel("Supplier Table");
		lblSupplierTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSupplierTable.setBounds(10, 11, 168, 14);
		panel_2.add(lblSupplierTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 294, 1035, 306);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Sup ID", "First Name",
				"Other Names", "Bz Name", "Phone1 ", "Phone2 ", "Address",
				"Email", "Sample Products ", "Grade" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);

		contentPane.add(scrollPane);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable SupplierTable = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "rawtypes", "unused" })
			Class[] columnTypes = new Class[] { Integer.class, Integer.class,
					String.class, String.class, String.class, Integer.class,
					Integer.class, String.class, String.class, String.class,
					Integer.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		SupplierTable.setBounds(20, 375, 862, 243);

		SupplierTable.getColumnModel().setColumnSelectionAllowed(true);
		SupplierTable.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(35);
		SupplierTable.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		SupplierTable.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(100);
		SupplierTable.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(120);
		SupplierTable.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(120);
		SupplierTable.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(80);
		SupplierTable.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(80);
		SupplierTable.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(120);
		SupplierTable.getTableHeader().getColumnModel().getColumn(8)
				.setPreferredWidth(160);
		SupplierTable.getTableHeader().getColumnModel().getColumn(9)
				.setPreferredWidth(160);
		SupplierTable.getTableHeader().getColumnModel().getColumn(10)
				.setPreferredWidth(45);
		;
		SupplierTable.setRowHeight(23);

		SupplierTable.setFillsViewportHeight(true);
		SupplierTable.setColumnSelectionAllowed(true);
		SupplierTable.setCellSelectionEnabled(true);
		SupplierTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SupplierTable.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(SupplierTable);

		contentPane.add(scrollPane);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(500);
		comboBox.setToolTipText("Select Supplier here");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1] + " - "
										+ lookup[r][2])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							supIDT.setText(lookup[r][0]);
							bzNameT.setText(lookup[r][1]);
							fNameT.setText(lookup[r][2]);
						}
				}

			}
		});

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.setBounds(125, 100, 205, 20);
		contentPane.add(comboBox);

		btnDisplayInfo = new JButton("Display Info");
		btnDisplayInfo.setForeground(Color.BLUE);
		btnDisplayInfo
				.setToolTipText("Click here to view info about Supplier ID entered above");
		btnDisplayInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				displayAction();
			}
		});
		btnDisplayInfo.setBounds(10, 102, 105, 23);
		contentPane.add(btnDisplayInfo);

		comboBox.setSelectedIndex(1);

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
				Supplier sup = new Supplier();

				allSup = sup.getAllSup();
				int noItems = allSup.length;


				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allSup[r][0],
							allSup[r][1], allSup[r][2], allSup[r][3],
							allSup[r][4], allSup[r][5], allSup[r][6],
							allSup[r][7], allSup[r][8], allSup[r][9] });

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

				Supplier sup = new Supplier();

				allSup = sup.getAllSup();
				int noItems = allSup.length;


				for (int r = 0; r < noItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1, allSup[r][0],
							allSup[r][1], allSup[r][2], allSup[r][3],
							allSup[r][4], allSup[r][5], allSup[r][6],
							allSup[r][7], allSup[r][8], allSup[r][9] });

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
						SupplierTable.print();
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

		lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(710, 188, 51, 20);
		contentPane.add(lblGrade);

		gradeSpin = new JSpinner();
		gradeSpin.setForeground(Color.BLUE);
		gradeSpin.setFont(new Font("Tahoma", Font.BOLD, 12));
		gradeSpin.setModel(new SpinnerNumberModel(new Long(0), new Long(-25),
				new Long(50), new Long(1)));
		gradeSpin.setBounds(751, 187, 41, 20);
		contentPane.add(gradeSpin);

		phone2T = new JTextField();
		phone2T.setForeground(Color.BLUE);
		phone2T.setColumns(10);
		phone2T.setBounds(485, 170, 205, 23);
		contentPane.add(phone2T);

		lblPhoneLine = new JLabel("Phone line 2:");
		lblPhoneLine.setBounds(393, 173, 81, 20);
		contentPane.add(lblPhoneLine);

		bzNameT = new JTextField();
		bzNameT.setForeground(Color.BLUE);
		bzNameT.setColumns(10);
		bzNameT.setBounds(485, 97, 205, 23);
		contentPane.add(bzNameT);

		lblBusinessName = new JLabel("Business Name:");
		lblBusinessName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblBusinessName.setBounds(393, 104, 94, 14);
		contentPane.add(lblBusinessName);

		final JToggleButton tglbtnSetFieldsEditable = new JToggleButton(
				"Set Fields Editable");
		tglbtnSetFieldsEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tglbtnSetFieldsEditable.isSelected() == true)
					infoFieldsEditable(false);
				else
					infoFieldsEditable(true);

			}
		});
		tglbtnSetFieldsEditable.setBounds(485, 219, 139, 23);
		contentPane.add(tglbtnSetFieldsEditable);
		
		button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);}
		});
		button.setForeground(Color.RED);
		button.setBounds(777, 11, 89, 23);
		contentPane.add(button);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { supIDT, fNameT, otherNamesT, phone1T,
						addressT, emailT, sampleProdT }));
		infoFieldsEditable(false);
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

	public void displayAction() {
		// row = 0;

		String txtid = supIDT.getText().trim().replaceAll("\\s", "");
		if (!(txtid.length() > 0))

			JOptionPane.showMessageDialog(null, "Enter Supplier ID");

		else {
			IntegerInput ii = new IntegerInput();
			if (!(ii.isInteger(txtid))) {
				JOptionPane.showMessageDialog(null,
						"Please provide whole number value for Supplier ID.");
				supIDT.setText(null);
			} else {

				supID = Integer.parseInt(txtid);
				Supplier sup = new Supplier();
				if (supID != sup.getSupID(supID)) {
					JOptionPane.showMessageDialog(null, "No Supplier with ID: "
							+ supID + " " + "is found on Database."
							+ "\nPlease, check ID and try again.");
					supIDT.setText("");

					supIDT.requestFocus();
				} else {
					String info[] = sup.getSupInfo(supID);
					supIDT.setText(info[0]);

					fNameT.setText(info[1]);

					otherNamesT.setText(info[2]);
					bzNameT.setText(info[3]);
					phone1T.setText(info[4]);
					phone2T.setText(info[5]);

					addressT.setText(info[6]);

					emailT.setText(info[7]);

					sampleProdT.setText(info[8]);
					gradeSpin.setValue(Integer.parseInt(info[9]));

					supIDT.setEditable(false);
					//infoFieldsEditable(true);

				}
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

		fNameT.setEditable(flag);

		phone1T.setEditable(flag);
		phone2T.setEditable(flag);
		bzNameT.setEditable(flag);

		addressT.setEditable(flag);
otherNamesT.setEditable(flag);
		emailT.setEditable(flag);

		sampleProdT.setEditable(flag);

	}
}
