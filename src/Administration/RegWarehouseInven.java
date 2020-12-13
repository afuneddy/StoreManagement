package Administration;

import interfaceClass.IntegerInput;
import interfaceClass.IsDate;

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
import java.sql.Date;

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

import planettaStore.Purchase;
import planettaStore.Supplier;
import planettaStore.WHouseInventory;
import security.SecurityAcc;
//import java.util.Date;

@SuppressWarnings("serial")
public class RegWarehouseInven extends JFrame {

	private JPanel contentPane;
	private JTextField prodIDT;
	private JTextField locT;
	private JTextField limLevelT;
	private JTextField lwuQtyT;
	private JTextField lwuUcpT;
	private JTextField hwuQtyT;
	private JTextField hwuUcpT;
	private JTextField lwuT;
	private JTextField hwuT;
	private JTextField lwupHwuT;
	private JTextField noRetUperLwuT;
	private JTextField tQtyLwuT;
	private JTextField lwuSpT;
	private JTextField hwuSpT;
	private JTextField catT;
	private JTextField purchDateT;
	private JTextField expDateT;
	private JTextField tcostT;
	private String[][] lookup;
	private JTextField purchIDT;
	private Date purchDate;
	int supID;
	private JLabel lblWarehouseInventoryRegistration;
	private JPanel panel_1;
	private JButton btnHelp;
	private JTextField supIDT;
	private JComboBox<String> comboBox;
	private JTextField retPriceT;
	protected static int trackID;
	public static final int classID = 13;
	static int roleid;
	static int empID; static String userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegWarehouseInven frame = new RegWarehouseInven(trackID,roleid, empID, userName);
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
	public RegWarehouseInven(final int trackID,int roleid, final int empID,  String userName) {
		setTitle("Warehouse Inventory Registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 783);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(39, 55, 789, 671);
		contentPane.add(panel);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProductId.setBounds(21, 38, 118, 17);
		panel.add(lblProductId);

		prodIDT = new JTextField();
		prodIDT.setForeground(Color.BLUE);
		prodIDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prodIDT.setEditable(false);
		prodIDT.setColumns(10);
		prodIDT.setBounds(241, 32, 118, 27);

		panel.add(prodIDT);

		final JButton pidGenBtn = new JButton("Generate code");
		pidGenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				WHouseInventory whi = new WHouseInventory();
				prodIDT.setText("" + whi.generateID());

			}
		});
		pidGenBtn
				.setToolTipText("Click here \r\nto generate product retail code");
		pidGenBtn.setForeground(Color.RED);
		pidGenBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		pidGenBtn.setBounds(92, 31, 129, 31);
		panel.add(pidGenBtn);

		JLabel lblLocationAtWarehouse = new JLabel("Location at Warehouse:");
		lblLocationAtWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocationAtWarehouse.setBounds(21, 416, 143, 14);
		panel.add(lblLocationAtWarehouse);

		locT = new JTextField();
		locT.setColumns(10);
		locT.setBounds(174, 409, 314, 31);
		panel.add(locT);

		JLabel lblSetLimitLevel = new JLabel("Set Limit level in LWU:");
		lblSetLimitLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetLimitLevel.setBounds(21, 472, 135, 14);
		panel.add(lblSetLimitLevel);

		limLevelT = new JTextField();
		limLevelT.setColumns(10);
		limLevelT.setBounds(174, 467, 86, 27);
		panel.add(limLevelT);

		JButton wsRegSubBtn = new JButton("Submit");
		wsRegSubBtn.setForeground(Color.BLUE);
		wsRegSubBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				IntegerInput ii = new IntegerInput();
				IsDate id = new IsDate();

				String prID = prodIDT.getText().trim().replaceAll("\\s", "");
				String suID = supIDT.getText().trim().replaceAll("\\s", "");
				String loc = locT.getText().trim();
				String limLevStr = limLevelT.getText().trim()
						.replaceAll("\\s", "");

				String lwuQ = lwuQtyT.getText().trim().replaceAll("\\s", "");
				String lwuUc = lwuUcpT.getText().trim().replaceAll("\\s", "");
				String hwuQ = hwuQtyT.getText().trim().replaceAll("\\s", "");
				String hwuUc = hwuUcpT.getText().trim().replaceAll("\\s", "");

				String lwu = lwuT.getText().trim();
				String hwu = hwuT.getText().trim();
				String lwupHwuStr = lwupHwuT.getText();
				String noRetUperLwuStr = noRetUperLwuT.getText().trim()
						.replaceAll("\\s", "");
				String tQtyLwuStr = tQtyLwuT.getText().trim()
						.replaceAll("\\s", "");
				String lwuSpStr = lwuSpT.getText().trim().replaceAll("\\s", "");
				String hwuSpStr = hwuSpT.getText().trim().replaceAll("\\s", "");
				// JOptionPane.showMessageDialog(null, "works");
				String cat = catT.getText().trim();
				String purchStr = purchIDT.getText().trim()
						.replaceAll("\\s", "");
				String purchDateStr = purchDateT.getText().trim()
						.replaceAll("\\s", "");
				String expDateStr = expDateT.getText().trim()
						.replaceAll("\\s", "");
				String tcostStr = tcostT.getText().trim().replaceAll("\\s", "");
				String retPriceS = retPriceT.getText().trim().replaceAll("\\s", "");
				// String supIdCo = supIdCom.getSelectedItem().toString();

				/**
				 * @param prID
				 *            product wholesale id
				 * @param suID
				 *            supplier id
				 * @param suID
				 *            supplier id
				 * @param suID
				 *            supplier id
				 * @param suID
				 *            supplier id
				 */
				if (!(prID.length() > 0)) {
					JOptionPane
							.showMessageDialog(
									null,
									"Please click \"Generate ID\" button to generate a warehouse ID for this new product");
					prodIDT.requestFocus();
				} else {

					int prodID = Integer.parseInt(prID);
					/*
					 * if (newSup.isSelected() == true) {
					 * 
					 * if (!(suID.length() > 0)) JOptionPane .showMessageDialog(
					 * null,
					 * "Please click \"Generate ID\" button to generate a supplier ID for this new Supplier."
					 * ); else { supID = Integer.parseInt(suID); } }
					 * 
					 * else if (!(oldSup.isSelected() == true))
					 * JOptionPane.showMessageDialog(null,
					 * "Please select supplier ID type."); else { String
					 * selected = supIdCom.getSelectedItem().toString(); if
					 * (selected.equals("--Choose--") || selected.equals(""))
					 * JOptionPane .showMessageDialog( null,
					 * "Supplier not selected.Enter initials of supplier name \n or business name and choose from a searched list below it.\nOR Enter  \" % \" without quotes to list all suppliers."
					 * );
					 * 
					 * else
					 * 
					 * { Supplier sup = new Supplier(); lookup =
					 * sup.searchString(supIDSearchT.getText());
					 * 
					 * for (int r = 0; r < lookup.length; r++)
					 * 
					 * if (selected.equals(lookup[r][0] + " - " + lookup[r][1] +
					 * " - " + lookup[r][2])) { supID =
					 * Integer.parseInt(lookup[r][0]);
					 * 
					 * supIDSearchT.setText(null); break; } else if
					 * (!(lookup[r][0].length() > 0)) {
					 * 
					 * JOptionPane .showMessageDialog(null,
					 * "Sorry!\nProduct code error occurred.Refer code otherwise.  "
					 * ); break; }
					 * 
					 * if (supID == 0) JOptionPane .showMessageDialog(null,
					 * "No supplier with ID: 0. Better still, an error has occurred.\n Sorry."
					 * );
					 */

					if (!(suID.length() > 0)) {
						{
							JOptionPane
									.showMessageDialog(
											null,
											"Please provide supplier ID. Better still, \n start typing name or business name of supplier\n and choose from list that appears below input field");
							supIDT.requestFocus();
						}
					} else if (!(ii.isInteger(suID))) {
						JOptionPane
								.showMessageDialog(null,
										"Please provide whole number value for supplier ID.");
						supIDT.setText(null);
						supIDT.requestFocus();
					} else{
						supID = Integer.parseInt(suID);

					if (!(purchDateStr.length() > 0)) {
						JOptionPane.showMessageDialog(null,
								"Please specify purchase date");
						purchDateT.requestFocus();
					} else {

						if (!(id.isDate(purchDateStr))) {
							JOptionPane
									.showMessageDialog(null,
											"Please use correct date format: yyyy-mm-dd\nFor purchase Date");
							purchDateT.requestFocus();
							purchDateT.setText(null);
						} else {
							purchDate = Date.valueOf(purchDateStr);

							if (!(purchStr.length() > 0)) {
								JOptionPane
										.showMessageDialog(null,
												"Please provide purchase ID of this product.");
								purchIDT.requestFocus();
							} else {
								if (!(ii.isInteger(purchStr))) {
									JOptionPane
											.showMessageDialog(null,
													"Please provide whole number value for purchase ID.");
									purchIDT.setText(null);
									purchIDT.requestFocus();
								} else {

									int purchID = Integer.parseInt(purchStr);
									Purchase p = new Purchase();
									if (!(supID == p.getSup(purchID))) {
										JOptionPane
												.showMessageDialog(
														null,
														"Ensure you have sufficiently identified the supplier linked to the purchase ID you provided.");
										supIDT.requestFocus();
										supIDT.setText(null);
									} else if (purchID != p
											.checkPurchID(purchID)
											|| !purchDate.equals(p
													.getPurchDate(purchID)))

									{
										JOptionPane
												.showMessageDialog(
														null,
														"Purchase ID and date do not match. "

																+ " "
																+ "Do any of the following:\n"
																+ "\n1. Check code and try again.\n2. Check purchase Date and try again. \n3. Make sure you have registered the purchase.");

										purchIDT.setText(null);
										if (purchDate != p
												.getPurchDate(purchID))
											JOptionPane
													.showMessageDialog(null,
															"Hints:Check \"purchase date\" field.");
										if (purchID != p.checkPurchID(purchID))
											JOptionPane
													.showMessageDialog(null,
															"Hints:Rather Check \"purchase ID\" field as a lead");
									}

									else if (!(lwu.length() > 0)) {

										JOptionPane
												.showMessageDialog(null,
														"Please provide Lowest wholesale unit description of product.");
										lwuT.requestFocus();
									} else

									if (!(lwuQ.length() > 0)) {

										JOptionPane.showMessageDialog(null,
												"Please provide LWU quantity.");
										lwuQtyT.requestFocus();
									} else if (!(ii.isInteger(lwuQ))) {
										JOptionPane
												.showMessageDialog(null,
														"Please provide whole number value for LWU quantity.");
										lwuQtyT.setText(null);
										lwuQtyT.requestFocus();
									} else {
										int lwuQty = Integer.parseInt(lwuQ);
										if (!(lwuUc.length() > 0)) {

											JOptionPane
													.showMessageDialog(null,
															"Please provide LWU unit cost price.");

											lwuUcpT.requestFocus();
										} else if (!(ii.isInteger(lwuUc))) {
											JOptionPane
													.showMessageDialog(null,
															"Please provide monetary value for LWU unit cost price.");

											lwuUcpT.setText(null);
											lwuUcpT.requestFocus();

										} else {
											int lwuUcp = Integer
													.parseInt(lwuUc);
											if (!(lwuSpStr.length() > 0))

												JOptionPane
														.showMessageDialog(
																null,
																"Please provide LWU unit wholesale price.");
											else

											if (!(ii.isInteger(lwuSpStr))) {

												JOptionPane
														.showMessageDialog(
																null,
																"Please provide monetary value for LWU wholesale price.");

												lwuSpT.setText(null);
												lwuSpT.requestFocus();

											} else {
												int lwuSp = Integer
														.parseInt(lwuSpStr);

												if (!(hwu.length() > 0)) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Please provide Highest wholesale unit description of this product.");
													hwuT.setText(null);
												} else

												if (!(hwuQ.length() > 0))

												{
													JOptionPane
															.showMessageDialog(
																	null,
																	"Please provide HWU quantity.");
													hwuQtyT.requestFocus();
												} else if (!(ii.isInteger(hwuQ))) {

													JOptionPane
															.showMessageDialog(
																	null,
																	"Please provide whole number value for HWU quantity.");
													hwuQtyT.requestFocus();
													hwuQtyT.setText(null);

												} else {
													int hwuQty = Integer
															.parseInt(hwuQ);
													if (!(hwuUc.length() > 0))

													{
														JOptionPane
																.showMessageDialog(
																		null,
																		"Please provide HWU unit cost price.");

														hwuUcpT.requestFocus();
													} else if (!(ii
															.isInteger(hwuUc))) {
														JOptionPane
																.showMessageDialog(
																		null,
																		"Please provide monetary value for HWU unit cost price.");
														hwuUcpT.requestFocus();
														hwuUcpT.setText(null);

													} else {
														int hwuUcp = Integer
																.parseInt(hwuUc);
														if (!(hwuSpStr.length() > 0))

														{
															JOptionPane
																	.showMessageDialog(
																			null,
																			"Please provide HWU unit wholesale price.");
															hwuSpT.requestFocus();
														} else

														if (!(ii.isInteger(hwuSpStr))) {

															JOptionPane
																	.showMessageDialog(
																			null,
																			"Please provide monetary value for HWU wholesale price.");

															hwuSpT.setText(null);
															hwuSpT.requestFocus();

														}

														else {
															int hwuSp = Integer
																	.parseInt(hwuSpStr);
															if (!(lwupHwuStr
																	.length() > 0)) {
																JOptionPane
																		.showMessageDialog(
																				null,
																				"Please provide number of LWU in a HWU.");
																lwupHwuT.requestFocus();
															} else

															if (!(ii.isInteger(lwupHwuStr))) {
																JOptionPane
																		.showMessageDialog(
																				null,
																				"Please provide whole number value for No of LWU per HWU.");

																lwupHwuT.setText(null);
																lwupHwuT.requestFocus();

															} else {
																int lwupHwu = Integer
																		.parseInt(lwupHwuStr);
																if (!(cat
																		.length() > 0))

																{
																	JOptionPane
																			.showMessageDialog(
																					null,
																					"Please specify product category.");

																	catT.requestFocus();
																} else if (!(noRetUperLwuStr
																		.length() > 0)) {
																	JOptionPane
																			.showMessageDialog(
																					null,
																					"Please specify Number of  retail units per LWU.");
																	noRetUperLwuT
																			.requestFocus();
																} else if (!(ii
																		.isInteger(noRetUperLwuStr))) {

																	JOptionPane
																			.showMessageDialog(
																					null,
																					"Please provide whole number value for No of retail units per HWU.");

																	noRetUperLwuT
																			.setText(null);
																	noRetUperLwuT
																			.requestFocus();

																} else {
																	int noRetUperLwu = Integer
																			.parseInt(noRetUperLwuStr);
																	if (!(tcostStr
																			.length() > 0)) {
																		JOptionPane
																				.showMessageDialog(
																						null,
																						"Please specify Total cost of product.");
																		tcostT.requestFocus();
																	} else

																	if (!(ii.isInteger(tcostStr))) {
																		JOptionPane
																				.showMessageDialog(
																						null,
																						"Please provide monetary value for total cost of this product.");

																		tcostT.setText(null);
																		tcostT.requestFocus();
																	} else {
																		int tCost = Integer
																				.parseInt(tcostStr);
																		if (!(tQtyLwuStr
																				.length() > 0)) {
																			JOptionPane
																					.showMessageDialog(
																							null,
																							"Please specify Total quantity in LWU units.");
																			tQtyLwuT.requestFocus();
																		} else if (!(ii
																				.isInteger(tQtyLwuStr))) {

																			JOptionPane
																					.showMessageDialog(
																							null,
																							"Please provide whole number value for total quantity.");

																			tQtyLwuT.setText(null);
																			tQtyLwuT.requestFocus();

																		} else {
																			int tQtyLwu = Integer
																					.parseInt(tQtyLwuStr);
																			
																			
																			if (!(retPriceS
																					.length() > 0)) {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please specify Retail Price of item.");
																				retPriceT.requestFocus();
																			} else if (!(ii
																					.isInteger(retPriceS))) {

																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please provide whole number value for Retail Price.");

																				retPriceT.setText(null);
																				retPriceT.requestFocus();

																			} else {
																				int retPrice = Integer
																						.parseInt(retPriceS);
																			
																			
																			if (!(loc
																					.length() > 0)) {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please specify location of this product in the warehouse.");
																				locT.requestFocus();
																			} else if (!(limLevStr
																					.length() > 0)) {
																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please set limit level in LWU units");
																				limLevelT
																						.requestFocus();
																			} else if (!(ii
																					.isInteger(limLevStr))) {

																				JOptionPane
																						.showMessageDialog(
																								null,
																								"Please provide whole number value limit level.");

																				limLevelT
																						.setText(null);
																				limLevelT
																						.requestFocus();

																			} else {
																				int limitLevel = Integer
																						.parseInt(limLevStr);

																				if (!(expDateStr

																						.length() > 0)) {
																					JOptionPane
																							.showMessageDialog(
																									null,
																									"Please specify expiration date");
																					expDateT.requestFocus();
																				} else {
																					if (!(id.isDate(expDateStr))) {

																						JOptionPane
																								.showMessageDialog(
																										null,
																										"Please use correct date format: yyyy-mm-dd \nFor Expiration date");

																						expDateT.setText(null);
																						expDateT.requestFocus();
																					} else {
																						Date expDate = Date
																								.valueOf(expDateStr);
																						WHouseInventory whi = new WHouseInventory();

																						int result = whi
																								.registerWSaleInventory(
																										prodID,
																										supID,
																										purchID,
																										noRetUperLwu,
																										lwu,
																										lwuQty,
																										lwuUcp,
																										lwuSp,
																										lwupHwu,
																										hwu,
																										hwuQty,
																										hwuUcp,
																										hwuSp,
																										limitLevel,
																										tCost,
																										tQtyLwu,
																										cat,
																										retPrice,
																										loc,
																										purchDate,
																										expDate);
																						if (result == 1)

																						{
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Successful.");
																							initialiseFields();
																							prodIDT.setEditable(true);
																						} else if (result == 0) {

																							JOptionPane
																									.showMessageDialog(
																											null,
																											"An error occurred.");
																						}

																						else if (result == 2) {
																							lwuUcpT.setText(null);
																							lwuSpT.setText(null);
																							lwuUcpT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else

																						if (result == 3) {
																							hwuUcpT.setText(null);
																							hwuSpT.setText(null);
																							hwuUcpT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 4) {
																							lwupHwuT.setText(null);
																							lwupHwuT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 5) {
																							hwuUcpT.setText(null);
																							lwuUcpT.setText(null);

																							lwuUcpT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 6) {

																							hwuQtyT.setText(null);
																							lwuQtyT.setText(null);
																							tQtyLwuT.setText(null);
																							lwuQtyT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 7) {
																							hwuQtyT.setText(null);
																							lwuQtyT.setText(null);
																							hwuUcpT.setText(null);
																							lwuUcpT.setText(null);
																							tcostT.setText(null);
																							lwuQtyT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 8) {

																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						} else if (result == 9) {
																							tcostT.setText(null);
																							tQtyLwuT.setText(null);
																							tQtyLwuT.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");
																						}

																						else if (result == 10) {
																							noRetUperLwuT
																									.setText(null);
																							noRetUperLwuT
																									.requestFocus();
																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");

																						}
																						else if (result == 11) {
																							noRetUperLwuT
																									.setText(null);
																							noRetUperLwuT
																									.requestFocus();
																							retPriceT
																							.setText(null);
																							JOptionPane
																							.showMessageDialog(
																									null,
																									"Retail Price has not worked out with Number of retail units per LWU and others.\nReview Input and try again.");

																							JOptionPane
																									.showMessageDialog(
																											null,
																											"Registration Unsuccessful.\nPlease Try Again.");

																						}


																						// initialiseFields();
																					}
																				}
																			}

																		}
																	}
																}}
															}
														}
													}
												}
											}

										}
									}
								}}
							}
						}
					}
				}
			}

		});
		wsRegSubBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		wsRegSubBtn.setBounds(174, 624, 107, 23);
		panel.add(wsRegSubBtn);

		JButton wsCanBtn = new JButton("Cancel");
		wsCanBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		wsCanBtn.setForeground(Color.RED);
		wsCanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				initialiseFields();

			}
		});
		wsCanBtn.setBounds(457, 625, 89, 23);
		panel.add(wsCanBtn);

		//ButtonGroup bg = new ButtonGroup();

		JLabel lblUseLowestWholesale = new JLabel("LWU:");
		lblUseLowestWholesale.setToolTipText("Lowest wholesale unit");
		lblUseLowestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseLowestWholesale.setBounds(21, 164, 44, 28);
		panel.add(lblUseLowestWholesale);

		JLabel lblQuantity = new JLabel("LWU Qty:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(269, 171, 73, 14);
		panel.add(lblQuantity);

		lwuQtyT = new JTextField();
		lwuQtyT.setBounds(352, 167, 53, 24);
		panel.add(lwuQtyT);
		lwuQtyT.setColumns(10);

		JLabel lblUcp = new JLabel("LWU ucp:");
		lblUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUcp.setBounds(437, 171, 66, 14);
		panel.add(lblUcp);

		lwuUcpT = new JTextField();
		lwuUcpT.setBounds(502, 166, 66, 27);
		panel.add(lwuUcpT);
		lwuUcpT.setColumns(10);

		JLabel lblUseHighestWholesale = new JLabel("HWU:");
		lblUseHighestWholesale.setToolTipText("Highest wholesale unit");
		lblUseHighestWholesale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUseHighestWholesale.setBounds(21, 216, 53, 28);
		panel.add(lblUseHighestWholesale);

		JLabel lblHwuQty = new JLabel("HWU Qty:");
		lblHwuQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuQty.setBounds(269, 223, 77, 14);
		panel.add(lblHwuQty);

		hwuQtyT = new JTextField();
		hwuQtyT.setColumns(10);
		hwuQtyT.setBounds(352, 218, 53, 27);
		panel.add(hwuQtyT);

		JLabel lblHwuUcp = new JLabel("HWU ucp:");
		lblHwuUcp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuUcp.setBounds(437, 223, 66, 14);
		panel.add(lblHwuUcp);

		hwuUcpT = new JTextField();
		hwuUcpT.setColumns(10);
		hwuUcpT.setBounds(502, 217, 66, 28);
		panel.add(hwuUcpT);

		lwuT = new JTextField();
		lwuT.setColumns(10);
		lwuT.setBounds(64, 169, 171, 23);
		panel.add(lwuT);

		hwuT = new JTextField();
		hwuT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hwuT.setColumns(10);
		hwuT.setBounds(64, 221, 171, 23);
		panel.add(hwuT);

		JLabel lblTotalCostOf = new JLabel("Total Cost of this product:");
		lblTotalCostOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalCostOf.setBounds(506, 282, 155, 14);
		panel.add(lblTotalCostOf);

		JLabel lblTotalQuantityIn = new JLabel("Total Qty in LWU units:");
		lblTotalQuantityIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQuantityIn.setBounds(267, 282, 138, 14);
		panel.add(lblTotalQuantityIn);

		JLabel lblNoOfLwu = new JLabel("No of LWU per HWU:");
		lblNoOfLwu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNoOfLwu.setBounds(21, 281, 143, 17);
		panel.add(lblNoOfLwu);

		lwupHwuT = new JTextField();
		lwupHwuT.setBounds(176, 279, 59, 27);
		panel.add(lwupHwuT);
		lwupHwuT.setColumns(10);

		JLabel lblNoOfRetail = new JLabel("No of Retail Units per LWU:");
		lblNoOfRetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoOfRetail.setBounds(21, 340, 160, 14);
		panel.add(lblNoOfRetail);

		noRetUperLwuT = new JTextField();
		noRetUperLwuT.setBounds(174, 335, 86, 27);
		panel.add(noRetUperLwuT);
		noRetUperLwuT.setColumns(10);

		tQtyLwuT = new JTextField();
		tQtyLwuT.setBounds(415, 277, 86, 27);
		panel.add(tQtyLwuT);
		tQtyLwuT.setColumns(10);

		JLabel lblSp = new JLabel("LWU SP:");
		lblSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSp.setBounds(596, 171, 65, 14);
		panel.add(lblSp);

		lwuSpT = new JTextField();
		lwuSpT.setColumns(10);
		lwuSpT.setBounds(656, 166, 77, 27);
		panel.add(lwuSpT);

		JLabel lblHwuSp = new JLabel("HWU SP:");
		lblHwuSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHwuSp.setBounds(596, 221, 65, 14);
		panel.add(lblHwuSp);

		hwuSpT = new JTextField();
		hwuSpT.setColumns(10);
		hwuSpT.setBounds(656, 218, 77, 27);
		panel.add(hwuSpT);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(269, 340, 73, 14);
		panel.add(lblCategory);

		catT = new JTextField();
		catT.setBounds(336, 335, 155, 27);
		panel.add(catT);
		catT.setColumns(10);

		JLabel lblPurchaseDate = new JLabel("Purchase  Date:");
		lblPurchaseDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPurchaseDate.setBounds(321, 104, 91, 14);
		panel.add(lblPurchaseDate);

		JLabel lblYyyymmdd = new JLabel("YYYY-MM-DD");
		lblYyyymmdd.setBounds(331, 122, 86, 14);
		panel.add(lblYyyymmdd);

		purchDateT = new JTextField();
		purchDateT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		purchDateT.setToolTipText("yyyy-mm-dd");
		purchDateT.setBounds(413, 101, 107, 27);
		panel.add(purchDateT);
		purchDateT.setColumns(10);

		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExpiryDate.setBounds(20, 521, 84, 14);
		panel.add(lblExpiryDate);

		JLabel lblYyyymmdd_1 = new JLabel("yyyy:mm:dd");
		lblYyyymmdd_1.setBounds(20, 546, 86, 14);
		panel.add(lblYyyymmdd_1);

		expDateT = new JTextField();
		expDateT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expDateT.setToolTipText("yyyy-mm-dd");
		expDateT.setBounds(174, 521, 107, 27);
		panel.add(expDateT);
		expDateT.setColumns(10);

		tcostT = new JTextField();
		tcostT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tcostT.setBounds(659, 276, 86, 27);
		panel.add(tcostT);
		tcostT.setColumns(10);

		JLabel lblPurchaseId = new JLabel("Purchase ID:");
		lblPurchaseId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPurchaseId.setBounds(556, 111, 73, 14);
		panel.add(lblPurchaseId);

		purchIDT = new JTextField();
		purchIDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		purchIDT.setBounds(632, 106, 101, 30);
		panel.add(purchIDT);
		purchIDT.setColumns(10);

		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(1000);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"--Choose--", "" }));
		comboBox.setSelectedIndex(1);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (!(comboBox.getSelectedIndex() == 0 || comboBox
						.getSelectedIndex() == 1)) {

					for (int r = 0; r < lookup.length; r++)

						if (comboBox.getSelectedItem().equals(
								lookup[r][0] + " - " + lookup[r][1] + " - "
										+ lookup[r][2])) {
							supIDT.setText(lookup[r][0]);

						}
				}
			}
		});
		comboBox.setBounds(123, 112, 188, 31);
		panel.add(comboBox);

		supIDT = new JTextField();
		supIDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				int count = comboBox.getItemCount();
				if (count > 2)
					for (int i = count - 1; i > 1; i--)
						comboBox.removeItemAt(i);

				Supplier s = new Supplier();
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

					lookup = s.searchString(text);
int size = lookup.length;
if (size== 0) {
	
		JOptionPane.showMessageDialog(null,
				"No Supplier name or business name starting with  "
						+ "\"" + text + "\"  "
						+ "is found."
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
		supIDT.setBounds(123, 86, 188, 27);
		panel.add(supIDT);
		supIDT.setColumns(10);

		JLabel btnSupplierInfo = new JLabel("Supplier ID:");
		btnSupplierInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSupplierInfo.setBounds(21, 85, 86, 53);
		panel.add(btnSupplierInfo);
		
		retPriceT = new JTextField();
		retPriceT.setColumns(10);
		retPriceT.setBounds(596, 335, 155, 27);
		panel.add(retPriceT);
		
		JLabel lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRetailPrice.setBounds(513, 340, 86, 14);
		panel.add(lblRetailPrice);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{supIDT, purchDateT, purchIDT, lwuT, lwuQtyT, lwuUcpT, lwuSpT, hwuT, hwuQtyT, hwuUcpT, hwuSpT, lwupHwuT, tQtyLwuT, tcostT, noRetUperLwuT, catT, retPriceT, locT, limLevelT, expDateT}));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setBounds(159, 11, 496, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblWarehouseInventoryRegistration = new JLabel(
				"WAREHOUSE INVENTORY REGISTRATION");
		lblWarehouseInventoryRegistration.setFont(new Font("Tahoma", Font.BOLD,
				12));
		lblWarehouseInventoryRegistration.setBounds(130, 11, 257, 14);
		panel_1.add(lblWarehouseInventoryRegistration);

		btnHelp = new JButton("HELP ?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null,
						"Help not yet available. Sorry.");

			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHelp.setForeground(Color.BLUE);
		btnHelp.setBounds(804, 11, 89, 23);
		contentPane.add(btnHelp);
		
		JButton button = new JButton("Log Out");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			}
		});
		button.setForeground(Color.RED);
		button.setBounds(681, 11, 89, 23);
		contentPane.add(button);
	}

	public void initialiseFields() {
		purchIDT.setText(null);
		prodIDT.setText(null);
		supIDT.setText(null);
		locT.setText(null);
		limLevelT.setText(null);

		lwuQtyT.setText(null);
		lwuUcpT.setText(null);
		hwuQtyT.setText(null);
		hwuUcpT.setText(null);

		lwuT.setText(null);
		hwuT.setText(null);
		lwupHwuT.setText(null);
		noRetUperLwuT.setText(null);

		tQtyLwuT.setText(null);
		lwuSpT.setText(null);
		hwuSpT.setText(null);

		catT.setText(null);
		purchDateT.setText("" + purchDate);
		expDateT.setText(null);
		tcostT.setText(null);
	}
}
