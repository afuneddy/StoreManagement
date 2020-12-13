package security;

import interfaceClass.HomeWindow;
import interfaceClass.IntegerInput;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import planettaStore.Employee;
import access.EmployeeAcc;

@SuppressWarnings("serial")
public class Authenticate extends JFrame {

	private JPanel contentPane;
	private JTextField userNameT;
	private JPasswordField passwordT;
	private JComboBox<String> roleCombo;
	private JTextField empIDT;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authenticate frame = new Authenticate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	Authenticate frame = new Authenticate();
	frame.setVisible(true);
	
	}

	/**
	 * Create the frame.
	 */
	public Authenticate() {
		setTitle("Store Manager 1.0                                            Developer: Afundoh Edward;  fafundoh@gmail.com");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userNameT = new JTextField();
		userNameT.setBounds(256, 247, 231, 34);
		contentPane.add(userNameT);
		userNameT.setColumns(10);
		
		passwordT = new JPasswordField();
		passwordT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					IntegerInput i = new IntegerInput();
					String empids = empIDT.getText().trim().replaceAll("\\s", "");
					
					String userName = userNameT.getText().trim().replaceAll("\\s", "");

					@SuppressWarnings("deprecation")
					String password = passwordT.getText();
					
					int roleid = 0;
					int empID = 0;
	Employee e= new Employee();
					String[][] all = e.populateCombo();
					String  role = roleCombo.getSelectedItem().toString();
					for (int r = 0; r < all.length; r++)
						if (role
								.equals(all[r][1])) {
							 roleid = Integer.parseInt(all[r][0]);
							break;
						} 
					
					
					if(empids.isEmpty())
						JOptionPane.showMessageDialog(null,
								"Employee ID is needed");
					
						if(i.isInteger(empids))
						 empID = Integer.parseInt(empids);
					
										
					if(matchLogin(roleid, role, empID,  userName, password))
						{
						initialiseFields();
						SecurityAcc s=new SecurityAcc();
						
						int trackID = s.generateLoginID()+1;
						s.insertLogin(trackID, empID);
						
						HomeWindow win = new HomeWindow(trackID, roleid, empID, userName);
					win.setVisible(true);
					JOptionPane.showMessageDialog(null,
							"Welcome! You have successfully logged in.");
			
						}
					
						else {
							JOptionPane.showMessageDialog(null,
									"Role, Employee ID, User Name or Password might not be correct\nPlease, Review and Try Again");
					
						initialiseFields();}
					
				
			}
		});
		passwordT.setBounds(256, 310, 231, 34);
		contentPane.add(passwordT);
		
		 roleCombo = new JComboBox<>();
		roleCombo.setMaximumRowCount(50);
		roleCombo.setBounds(256, 97, 231, 34);
		contentPane.add(roleCombo);
		
		Employee emp = new Employee();
		String[][] items = emp.populateCombo();
		//JOptionPane.showMessageDialog(null, items[1][1]);
		if(items.length > 0){
			roleCombo.insertItemAt("--Choose--", 0);
			roleCombo.setSelectedIndex(0);
		for (int i = 1; i <= items.length; i++)
			roleCombo.insertItemAt(items[i-1][1], i);
		}
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRole.setForeground(Color.BLACK);
		lblRole.setBounds(173, 100, 73, 20);
		contentPane.add(lblRole);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setBounds(173, 254, 73, 20);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(173, 317, 73, 20);
		contentPane.add(lblPassword);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				IntegerInput i = new IntegerInput();
				String empids = empIDT.getText().trim().replaceAll("\\s", "");
				
				String userName = userNameT.getText().trim().replaceAll("\\s", "");

				String password = passwordT.getText();
				
				int roleid = 0;
				int empID = 0;
Employee e= new Employee();
				String[][] all = e.populateCombo();
				String  role = roleCombo.getSelectedItem().toString();
				for (int r = 0; r < all.length; r++)
					if (role
							.equals(all[r][1])) {
						 roleid = Integer.parseInt(all[r][0]);
						break;
					} 
				
				
				if(empids.isEmpty())
					JOptionPane.showMessageDialog(null,
							"Employee ID is needed");
				
					if(i.isInteger(empids))
					 empID = Integer.parseInt(empids);
				
									
				if(matchLogin(roleid, role, empID,  userName, password))
					{
					initialiseFields();
					SecurityAcc s=new SecurityAcc();
					
					int trackID = s.generateLoginID()+1;
					s.insertLogin(trackID, empID);
					
					HomeWindow win = new HomeWindow(trackID, roleid, empID, userName);
				win.setVisible(true);
				JOptionPane.showMessageDialog(null,
						"Welcome! You have successfully logged in.");
		
					}
				
					else {
						JOptionPane.showMessageDialog(null,
								"Role, Employee ID, User Name or Password might not be correct\nPlease, Review and Try Again");
				
					initialiseFields();}
				
					
				
			}
		});
		btnSignIn.setForeground(Color.BLUE);
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignIn.setBounds(394, 407, 95, 40);
		contentPane.add(btnSignIn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				initialiseFields();
			}
		});
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(173, 407, 110, 40);
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		panel.setBounds(142, 11, 392, 45);
		contentPane.add(panel);
		
		JLabel lblLoginToStore = new JLabel("Login to Store Manager 1.0");
		lblLoginToStore.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoginToStore.setBounds(109, 11, 210, 23);
		panel.add(lblLoginToStore);
		
		empIDT = new JTextField();
		empIDT.setColumns(10);
		empIDT.setBounds(256, 178, 231, 34);
		contentPane.add(empIDT);
		
		JLabel lblEmployeeId = new JLabel("Emp ID:");
		lblEmployeeId.setForeground(Color.BLACK);
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmployeeId.setBounds(173, 185, 73, 20);
		contentPane.add(lblEmployeeId);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{roleCombo, empIDT, userNameT, passwordT, btnSignIn, btnCancel}));
	}

public void initialiseFields()
{
	empIDT.setText(null);	
userNameT.setText(null);
passwordT.setText(null);
roleCombo.setSelectedIndex(0);


}


public boolean matchLogin(int roleid, String role, int empID, String userName, String password)
{EmployeeAcc e = new EmployeeAcc();
	SecurityAcc s = new SecurityAcc();
String un = s.getUserName(empID);

String pass = s.getPassword(empID);

int securityLev = s.getSecurityLevel(empID);
String srole= e.getRole(securityLev);


if ((password.equals(pass)) && un.equals(userName) && ((securityLev == roleid) || srole.equals(role)))
	return  true;
else return false;



}
public boolean matchLogin( int empID, String userName, char[] password)
{
	return matchLogin(empID,userName, password);



}
public boolean matchLogin(int empID, char[] password)
{
	
	return matchLogin( empID, password);



}
}
