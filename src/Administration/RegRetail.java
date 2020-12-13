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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.RetailInventory;
import planettaStore.WHouseInventory;
import security.SecurityAcc;
import access.AccessClass;

@SuppressWarnings("serial")
public class RegRetail extends JFrame {

	private JPanel contentPane;
	//private int prodRetID;
	private JTextField prodRetCGen;
	private JTextField prodIDT;
	private JTextField retRegQtyT;
	private JTextField retLoc;
	private JTextField retLim;
	private JTextField retPriceT;
	private JTextField retDescT;
	private JComboBox<String> wProdCom;
	private String[][] lookup;
	public static final int classID = 11;
	static int roleid;
	static int empID; static String userName;
	static int trackID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegRetail frame = new RegRetail(trackID,roleid, empID, userName);
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
	public RegRetail(final int trackID, int roleid, final int empID, String userName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Retail Inventory registration form");
		setBounds(100, 100, 683, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel retRegPanel = new JPanel();
		retRegPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		retRegPanel.setBounds(63, 65, 489, 509);
		contentPane.add(retRegPanel);
		retRegPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Product retail code:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(21, 38, 118, 17);
		retRegPanel.add(lblNewLabel);

		prodRetCGen = new JTextField();
		prodRetCGen.setEditable(false);
		prodRetCGen.setForeground(Color.BLUE);
		prodRetCGen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prodRetCGen.setBounds(251, 34, 128, 27);
		retRegPanel.add(prodRetCGen);
		prodRetCGen.setColumns(10);

		JButton genRetCode = new JButton("Generate ");
		genRetCode
				.setToolTipText("Click here \r\nto generate product retail code");
		genRetCode.setForeground(Color.RED);
		genRetCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		genRetCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RetailInventory ri = new RetailInventory();
				prodRetCGen.setText("" + ri.generateID());
			}
		});
		genRetCode.setBounds(134, 35, 107, 27);
		retRegPanel.add(genRetCode);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProductId.setBounds(21, 89, 73, 14);
		retRegPanel.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		prodIDT.setToolTipText("Warehouse ID of item you are about to register");
		prodIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = wProdCom.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						wProdCom.removeItemAt(i);

				RetailInventory ri = new RetailInventory();
				IntegerInput ii = new IntegerInput();
				String text = prodIDT.getText().trim().replaceAll("\\s", "");

				if (ii.isInteger(text))
					;
				else {

					if (!(text.length() > 0)) {
						wProdCom.setSelectedIndex(1);
						prodIDT.setText(null);
						prodIDT.requestFocus();
					} else

					{

						lookup = ri.searchProd(text);
int size = lookup.length;
if (size == 0) {

		JOptionPane.showMessageDialog(null,
				"No warehouse product starting with"
						+ "\"" + text + "\"  "
						+ "is found."
						+ "\nTry other names.");
		prodIDT.setText(null);
		prodIDT.requestFocus();
	

} else
						for (int r = 0; r < lookup.length; r++) {

								wProdCom.setSelectedIndex(0);
							
							 

								String cmBoxItem = lookup[r][0] + " - "
										+ lookup[r][1];
							
								wProdCom.addItem(cmBoxItem);
							
						}
					}

				}

			}
		});
		prodIDT.setBounds(117, 84, 128, 27);
		retRegPanel.add(prodIDT);
		prodIDT.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(21, 182, 73, 17);
		retRegPanel.add(lblQuantity);

		retRegQtyT = new JTextField();
		retRegQtyT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retRegQtyT.setToolTipText("Number of items about to be added to Retail Inventory");
		retRegQtyT.setBounds(117, 180, 86, 27);
		retRegPanel.add(retRegQtyT);
		retRegQtyT.setColumns(10);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocation.setBounds(21, 265, 59, 14);
		retRegPanel.add(lblLocation);

		retLoc = new JTextField();
		retLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		retLoc.setToolTipText("Address of Item in the Retail house");
		retLoc.setBounds(117, 262, 179, 31);
		retRegPanel.add(retLoc);
		retLoc.setColumns(10);

		JLabel lblLimitLevel = new JLabel("Limit level:");
		lblLimitLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLimitLevel.setBounds(21, 317, 73, 14);
		retRegPanel.add(lblLimitLevel);

		retLim = new JTextField();
		retLim.setFont(new Font("Tahoma", Font.BOLD, 12));
		retLim.setToolTipText("If inventory falls below this level, it would be considered as shortage");
		retLim.setBounds(117, 312, 86, 27);
		retRegPanel.add(retLim);
		retLim.setColumns(10);

		JButton retRegSub = new JButton("Submit");
		retRegSub.setToolTipText("Click here to save on database.");
		retRegSub.setForeground(Color.BLUE);
		retRegSub.setFont(new Font("Tahoma", Font.BOLD, 14));
		retRegSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String lim = retLim.getText().trim().replaceAll("\\s", "");
				String txtqty = retRegQtyT.getText().trim()
						.replaceAll("\\s", "");
				String rp = retPriceT.getText().trim().replaceAll("\\s", "");
				String retDesc = retDescT.getText().trim();
				String loca = retLoc.getText().trim();
				String txtid = prodIDT.getText().trim().replaceAll("\\s", "");
				int prodID;

				String proRetC = prodRetCGen.getText();
				IntegerInput ii = new IntegerInput();

				if (!(proRetC.length() > 0)) {
					JOptionPane
							.showMessageDialog(
									null,
									"Please click \"Generate code\" button to generate a retail code for this new product");

				} else {

					int prodRID = Integer.parseInt(proRetC);

					if (!(txtid.length() > 0))

					{
						JOptionPane
								.showMessageDialog(
										null,
										"Please provide warehouse identity of product.\nBetter still, choose from the dropdown list on the right of ID field.");
						prodIDT.requestFocus();
					} else {

						if (!(ii.isInteger(txtid))) {
							JOptionPane
									.showMessageDialog(null,
											"Please provide whole number value for Product ID.");
							prodIDT.setText(null);
							prodIDT.requestFocus();
						}

						else {
							prodID = Integer.parseInt(txtid);
							WHouseInventory wsi = new WHouseInventory();
							if (prodID != wsi.checkProdId(prodID))// checks
																	// availability
																	// of
																	// product
																	// on
																	// database
							{
								JOptionPane
										.showMessageDialog(
												null,
												"No product with ID : "
														+ prodID
														+ " "

														+ "is found on Database."
														+ "\nPlease, check ID and try again.\nBetter still, start typing product description and choose from the list on its left,OR\n Enter  \" % \" without quotes to display all.");
								prodIDT.setText(null);
								prodIDT.requestFocus();
							}

							else if (!(retDesc.length() > 0))

							{
								JOptionPane
										.showMessageDialog(null,
												"Please specify retail product description.");

								retDescT.requestFocus();
							} else if (!(txtqty.length() > 0))

							{
								JOptionPane
										.showMessageDialog(null,
												"Please specify retail product quantity.");
								retRegQtyT.requestFocus();
							} else if (!(ii.isInteger(txtqty))) {
								JOptionPane
										.showMessageDialog(null,
												"Please provide whole number value for quantity.");
								retRegQtyT.setText(null);
								retRegQtyT.requestFocus();

							} else {
								int qty = Integer.parseInt(txtqty);
								if (!(rp.length() > 0)) {
									JOptionPane
											.showMessageDialog(null,
													"Please specify retail price product.");
									retPriceT.requestFocus();
								} else if (!(ii.isInteger(rp))) {
									JOptionPane
											.showMessageDialog(null,
													"Please provide whole number value for retail price.");
									retPriceT.setText(null);
									retPriceT.requestFocus();
								} else

								{
									int retPrice = Integer.parseInt(rp);
								/**
									 * @param rp
									 *            retail price of product to be
									 *            registered to retail
									 *            inventory.
									 */	
									int decision = -1;
									if(retPrice <= wsi.checkCostPrice(prodID))
										 decision = JOptionPane.showConfirmDialog(null,  "Retail Price is less than or equal to Unit Cost Price\nDo you wish to maintain this price?","Decide", JOptionPane.YES_NO_OPTION);
										
									//JOptionPane.YES_NO_OPTION.showMessageDialog(null, decision);
									if(decision == 1)
									{
										retPriceT.setText(null);
									retPriceT.requestFocus();
									}
									else
									if (!(loca.length() > 0)) {
										JOptionPane
												.showMessageDialog(null,
														"Please specify product retail location.");
										retLoc.requestFocus();
									} else {
										if (!(lim.length() > 0))

										{
											JOptionPane
													.showMessageDialog(null,
															"Please specify retail product limit level.");
											retLim.requestFocus();
										} else {
											if (!(ii.isInteger(lim))) {
												JOptionPane
														.showMessageDialog(
																null,
																"Please provide whole number value for product limit level.");
												retLim.setText(null);
												retLim.requestFocus();

											} else

											{

												int limLev = Integer
														.parseInt(lim);
int dec =-1;
												RetailInventory ri = new RetailInventory();
												//JOptionPane.showMessageDialog(null, ri.checkDoubleReg(prodID,prodRID).length);
												if(ri.checkDoubleReg(prodID,prodRID).length == 1)
													dec=JOptionPane.showConfirmDialog(null, "The following item is already registered in the system with the product ID you provided. \n" + ri.checkDoubleReg(prodID,prodRID)[0]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
												else
													if(ri.checkDoubleReg(prodID,prodRID).length == 2)
														dec=JOptionPane.showConfirmDialog(null, "The following items  are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID,prodRID)[0]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[1]+"\nDo you wish to register with same ID  any way?", "Decision", JOptionPane.YES_NO_OPTION);
													else
														if(ri.checkDoubleReg(prodID,prodRID).length == 3)
															dec=JOptionPane.showConfirmDialog(null, "The following items  are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID,prodRID)[0]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[1]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[2]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
														else
															if(ri.checkDoubleReg(prodID,prodRID).length > 3)
																dec=JOptionPane.showConfirmDialog(null, "The following items and maybe others are already registered in the system with the product ID you provided.\n" + ri.checkDoubleReg(prodID,prodRID)[0]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[1]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[2]+"\n"+ ri.checkDoubleReg(prodID,prodRID)[3]+"\nDo you wish to register with same ID any way?", "Decision", JOptionPane.YES_NO_OPTION);
															if(dec == 1){
																JOptionPane.showMessageDialog(null, "Then go back and retry","Advice", JOptionPane.OK_OPTION);
														initialiseFields();
															}else
																
												if (ri.registerNewRetProd(
														prodRID, retDesc,
														prodID, qty, retPrice,
														loca, limLev)) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Product registration has succeeded");
													initialiseFields();

												}

												else

													JOptionPane
															.showMessageDialog(
																	null,
																	"Product not registered. Please try again.");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		retRegSub.setBounds(117, 377, 107, 23);
		retRegPanel.add(retRegSub);

		wProdCom = new JComboBox<String>();
		wProdCom.setMaximumRowCount(1000);
		wProdCom.setToolTipText("Click the down arrow.");
		wProdCom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				if (!(wProdCom.getSelectedIndex() == 0 || wProdCom
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (wProdCom.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1])) {
							// prodID = Integer.parseInt(lookup[r][0]);

							prodIDT.setText(lookup[r][0]);
						}
				}

			}
		});
		wProdCom.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));

		wProdCom.setSelectedIndex(1);
		wProdCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		wProdCom.setBounds(279, 87, 179, 20);
		retRegPanel.add(wProdCom);

		JLabel lblRetailPrice = new JLabel("Retail price:");
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRetailPrice.setBounds(21, 228, 77, 17);
		retRegPanel.add(lblRetailPrice);

		retPriceT = new JTextField();
		retPriceT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retPriceT.setToolTipText("Retail price of item. Ensure it corresponds to the description you have given above");
		retPriceT.setBounds(117, 226, 179, 27);
		retRegPanel.add(retPriceT);
		retPriceT.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Click here to cancel registration.");
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				retLim.setText(null);
				retRegQtyT.setText(null);
				retLoc.setText(null);
				prodIDT.setText(null);
				retPriceT.setText(null);
				prodRetCGen.setText(null);

			}
		});
		btnCancel.setBounds(279, 378, 89, 23);
		retRegPanel.add(btnCancel);

		retDescT = new JTextField();
		retDescT.setFont(new Font("Tahoma", Font.BOLD, 12));
		retDescT.setToolTipText("Retail description as shall be printed on receipts");
		retDescT.setBounds(117, 135, 179, 27);
		retRegPanel.add(retDescT);
		retDescT.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(21, 141, 68, 14);
		retRegPanel.add(lblDescription);

		JLabel lblNbStartTyping = new JLabel(
				"N.B: Start typing product description on the productID field and choose from list on its right.");
		lblNbStartTyping.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNbStartTyping.setBounds(21, 442, 458, 14);
		retRegPanel.add(lblNbStartTyping);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(89, 90, 14, 14);
		retRegPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 442, 14, 14);
		retRegPanel.add(label_1);
		retRegPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{prodRetCGen, prodIDT, retDescT, retRegQtyT, retPriceT, retLoc, retLim, retRegSub, btnCancel}));

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(63, 11, 489, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegisterNewRetail = new JLabel("REGISTER NEW RETAIL PRODUCT");
		lblRegisterNewRetail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegisterNewRetail.setBounds(131, 11, 199, 30);
		panel.add(lblRegisterNewRetail);

		JButton btnHelp = new JButton("HELP ?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Help not yet available. Sorry.");

			}
		});
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setBounds(578, 11, 89, 23);
		contentPane.add(btnHelp);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
			
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		btnLogOut.setForeground(Color.RED);
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBounds(578, 45, 89, 30);
		contentPane.add(btnLogOut);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {
				retRegPanel, prodRetCGen, genRetCode, prodIDT, retRegQtyT,
				retLoc, retLim, retRegSub, wProdCom, retPriceT }));
	}

	protected Boolean updateRetInventory(int prodRetID, int qty) {

		AccessClass ac = new AccessClass();
		return ac.updateRetInventory(prodRetID, qty);
	}

	public void initialiseFields() {

		retLim.setText(null);
		retRegQtyT.setText(null);
		retLoc.setText(null);
		retDescT.setText(null);
		prodIDT.setText(null);
		retPriceT.setText(null);
		prodRetCGen.setText(null);

	}
}
