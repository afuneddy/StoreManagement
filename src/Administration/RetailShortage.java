package Administration;

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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.RetailInventory;
import security.SecurityAcc;

@SuppressWarnings("serial")
public class RetailShortage extends JFrame {

	private JPanel contentPane;
	private JButton btnPopulateTable;
	private JButton btnPrintTable;
	JTable displaytableRetItems;
	String[][] retailShortage;
	DefaultTableModel myModel;
	private JButton btnCloseTable;
	protected static int trackid;
	public static final int classID = 15;
	static int roleid;
	static int empID; static String userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetailShortage frame = new RetailShortage(trackid,roleid, empID, userName);
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
	public RetailShortage(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("View Retail  Shortage");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1064, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 95, 878, 383);
		contentPane.add(scrollPane);

		Object[][] data = new Object[][] {};
		String[] columns = new String[] { "No", "Pro_RID", "Description",
				"Qty Left", "Limit Level", "Location", "Since", "At" };

		final DefaultTableModel myModel = new DefaultTableModel(data, columns);
		/**
		 * @param data
		 *            Retail table data
		 * @param columns
		 *            Retail table columns
		 */

		final JTable displaytableRetItems = new JTable(myModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		displaytableRetItems.getColumnModel().setColumnSelectionAllowed(true);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(0)
				.setPreferredWidth(25);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(1)
				.setPreferredWidth(60);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(2)
				.setPreferredWidth(160);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(3)
				.setPreferredWidth(50);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(4)
				.setPreferredWidth(40);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(5)
				.setPreferredWidth(160);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(6)
				.setPreferredWidth(35);
		displaytableRetItems.getTableHeader().getColumnModel().getColumn(7)
				.setPreferredWidth(30);

		displaytableRetItems.setRowHeight(23);
		contentPane.add(scrollPane);

		displaytableRetItems.setFillsViewportHeight(true);
		displaytableRetItems.setColumnSelectionAllowed(true);
		displaytableRetItems.setCellSelectionEnabled(true);

		displaytableRetItems
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displaytableRetItems.setSurrendersFocusOnKeystroke(true);

		scrollPane.setViewportView(displaytableRetItems);
		infoFieldsEditable(false);

		btnPopulateTable = new JButton("Populate Table");
		btnPopulateTable
				.setToolTipText("Click to display all retail items that have gone below limit.");
		btnPopulateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				RetailInventory ri = new RetailInventory();

				// int noRetItems = ri.getRetInvCount();

				retailShortage = ri.getRetailShortage();
				int noRetItems = retailShortage.length;
				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							retailShortage[r][0], retailShortage[r][1],
							retailShortage[r][2], retailShortage[r][3],
							retailShortage[r][4], retailShortage[r][5],
							retailShortage[r][6] });

				}
				if (flag == 1)
					JOptionPane.showMessageDialog(null,
							"Table successfully updated.");
				else
					JOptionPane.showMessageDialog(null,
							"Table successfully populated.");
			}
		});
		btnPopulateTable.setBounds(132, 504, 123, 23);
		contentPane.add(btnPopulateTable);

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
						displaytableRetItems.print();
					} catch (PrinterException e1) { // TODO Auto-generated catch
													// block
						JOptionPane.showMessageDialog(null,
								"Printing Error occurred:\n " + e1);
					}
				}

			}
		});
		btnPrintTable.setBounds(703, 504, 112, 23);
		contentPane.add(btnPrintTable);

		JButton btnNewButton = new JButton("Update Table");
		btnNewButton.setToolTipText("Click to update table");
		btnNewButton.addActionListener(new ActionListener() {
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

				RetailInventory ri = new RetailInventory();

				retailShortage = ri.getRetailShortage();
				int noRetItems = retailShortage.length;
				for (int r = 0; r < noRetItems; r++) {

					myModel.insertRow(r, new Object[] { r + 1,
							retailShortage[r][0], retailShortage[r][1],
							retailShortage[r][2], retailShortage[r][3],
							retailShortage[r][4], retailShortage[r][5],
							retailShortage[r][6] });

				}
				tableUpdateAckn(flag);

			}
		});
		btnNewButton.setBounds(319, 504, 112, 23);
		contentPane.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setLayout(null);
		panel_1.setBounds(359, 11, 301, 48);
		contentPane.add(panel_1);

		JLabel lblInventoryTable = new JLabel("VIEW ALL RETAIL SHORTAGE");
		lblInventoryTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInventoryTable.setBounds(47, 23, 297, 14);
		panel_1.add(lblInventoryTable);

		JButton btnHelp = new JButton("HELP ?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Help not yet available. Sorry.");
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setBounds(902, 20, 89, 23);
		contentPane.add(btnHelp);

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
		btnCloseTable.setBounds(523, 504, 102, 23);
		contentPane.add(btnCloseTable);
		
		JButton button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(791, 20, 89, 23);
		contentPane.add(button);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { scrollPane, displaytableRetItems }));

	}

	public void infoFieldsEditable(Boolean truthValue) {
	}

	public void tableUpdateAckn(int flag) {

		if (flag == 1)
			JOptionPane.showMessageDialog(null, "Table successfully updated.");
		else
			JOptionPane
					.showMessageDialog(null, "Table successfully populated.");

	}

	protected boolean updateRetInventory(int prodRetID2, int qty) {
		// TODO Auto-generated method stub
		return false;
	}
}
