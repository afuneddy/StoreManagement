package interfaceClass;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import planettaStore.Employee;
import security.SecurityAcc;
import security.SecurityProtocols;
import Administration.Administration;

public class HomeWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userT;
	private JTextField secLevT;
	private JTextField roleT;
	private JLabel textField_3;
	private JTextField lastLogT;
	private JTextField dateT;
	static int trackID;
	static int secLev;
static int empID;
static String userName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow frame = new HomeWindow(trackID, secLev, empID, userName);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param empID 
	 * @param roleid 
	 * @param empID2 
	 * @param userName 
	 */
	public HomeWindow(final int trackID, final int roleid, final int empID,  final String userName) {
		setTitle("Control Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 549);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		panel.setBounds(22, 11, 202, 31);
		contentPane.add(panel);
		
		JLabel lblUserStatus = new JLabel("User Status");
		lblUserStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserStatus.setBounds(10, 11, 182, 14);
		panel.add(lblUserStatus);
		
		JLabel lblCurrentUser = new JLabel("Current User:");
		lblCurrentUser.setBounds(32, 53, 90, 14);
		contentPane.add(lblCurrentUser);
		
		userT = new JTextField(userName+"");
		userT.setFont(new Font("Tahoma", Font.BOLD, 11));
		userT.setForeground(Color.BLACK);
		userT.setEditable(false);
		userT.setBounds(137, 53, 86, 20);
		contentPane.add(userT);
		userT.setColumns(10);
		
		JLabel lblSecurityLevel = new JLabel("Security Level:");
		lblSecurityLevel.setBounds(32, 86, 90, 14);
		contentPane.add(lblSecurityLevel);
		
		secLevT = new JTextField(roleid+"");
		secLevT.setFont(new Font("Tahoma", Font.BOLD, 11));
		secLevT.setForeground(Color.BLACK);
		secLevT.setEditable(false);
		secLevT.setColumns(10);
		secLevT.setBounds(137, 84, 86, 20);
		contentPane.add(secLevT);
		Employee e=new Employee();
		
		roleT = new JTextField(e.getEmpRoleInfo(roleid)[1]+"");
		roleT.setFont(new Font("Tahoma", Font.BOLD, 11));
		roleT.setForeground(Color.BLACK);
		roleT.setEditable(false);
		roleT.setColumns(10);
		roleT.setBounds(137, 114, 86, 20);
		contentPane.add(roleT);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(32, 116, 90, 14);
		contentPane.add(lblRole);
		
		textField_3 = new JLabel();
		textField_3.setBounds(53, 241, 86, 20);
		contentPane.add(textField_3);
		
		JButton btnSecurityProtocols = new JButton("Security Protocols");
		btnSecurityProtocols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
SecurityAcc s = new SecurityAcc();
				
				
				if(roleid < Integer.parseInt(s.getClassRightsInfo(SecurityProtocols.classID)[2]))	
					JOptionPane.showMessageDialog(null, "Access Denied!\nYou don't have sufficient rights, Sorry.", "Security Message", JOptionPane.WARNING_MESSAGE);
				else
				{
				SecurityProtocols win = new SecurityProtocols(trackID, roleid, empID, userName);
			win.setVisible(true);
				}
			
			}
		});
		btnSecurityProtocols.setBounds(333, 397, 186, 52);
		contentPane.add(btnSecurityProtocols);
		
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
SecurityAcc s = new SecurityAcc();
				
				
				if(roleid < Integer.parseInt(s.getClassRightsInfo(Administration.classID)[2]))	
					JOptionPane.showMessageDialog(null, "Access Denied!\nYou don't have sufficient rights, Sorry.", "Security Message", JOptionPane.WARNING_MESSAGE);
				else
				{
				Administration win = new Administration(trackID, roleid, empID, userName);
			win.AdminLinks.setVisible(true);
				}
			}
		});
		btnAdministration.setBounds(63, 397, 186, 52);
		contentPane.add(btnAdministration);
		
		JButton btnRetailWindow = new JButton("Retail Window");
		btnRetailWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecurityAcc s = new SecurityAcc();
				//JOptionPane.showMessageDialog(null, RetailWindow.classID+"jjj"+s.getClassRightsInfo(RetailWindow.classID)[2]);
				int sl =Integer.parseInt(s.getClassRightsInfo(RetailWindow.classID)[2]);
				
			if(roleid < sl )
				JOptionPane.showMessageDialog(null, "Access Denied!\nYou don't have sufficient rights, Sorry.", "Security Message", JOptionPane.WARNING_MESSAGE);
			else
			{
			RetailWindow win = new RetailWindow(trackID,roleid, empID, userName);
			win.frame.setVisible(true);
			}}
		});
		btnRetailWindow.setBounds(63, 266, 186, 52);
		contentPane.add(btnRetailWindow);
		
		final JButton btnWholesaleWindow = new JButton("Wholesale Window");
		btnWholesaleWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SecurityAcc s = new SecurityAcc();
				
				
				if(roleid < Integer.parseInt(s.getClassRightsInfo(WholesaleWindow.classID)[2]))	
					JOptionPane.showMessageDialog(null, "Access Denied!\nYou don't have sufficient rights, Sorry.", "Security Message", JOptionPane.WARNING_MESSAGE);
				else
				{
				WholesaleWindow win = new WholesaleWindow(trackID,roleid, empID, userName);
				win.setVisible(true);
				}
			}
		});
		btnWholesaleWindow.setBounds(333, 266, 186, 52);
		contentPane.add(btnWholesaleWindow);
		
		lastLogT = new JTextField();
		lastLogT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lastLogT.setForeground(Color.BLACK);
		lastLogT.setEditable(false);
		lastLogT.setColumns(10);
		lastLogT.setBounds(138, 145, 86, 20);
		contentPane.add(lastLogT);
		
		JLabel lblLastLogin = new JLabel("Last Login:");
		lblLastLogin.setBounds(33, 147, 90, 14);
		contentPane.add(lblLastLogin);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(Color.RED);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
			
			}
		});
		btnLogOut.setBounds(510, 59, 175, 41);
		contentPane.add(btnLogOut);
		
		dateT = new JTextField();
		dateT.setEditable(false);
		dateT.setColumns(10);
		dateT.setBounds(510, 22, 175, 20);
		contentPane.add(dateT);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(475, 24, 90, 14);
		contentPane.add(lblDate);
	}
	
	
	
}
