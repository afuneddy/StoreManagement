package security;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Administration.UpdateEmployee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class SecurityProtocols extends JFrame {
	static int roleid; static int empID; static String userName;
	private JPanel contentPane;
	public static int classID=4;
static int trackID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecurityProtocols frame = new SecurityProtocols( trackID, roleid,  empID, userName);
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
	 */
	public SecurityProtocols(final int trackID, final int roleid, final int empID, final String userName) {
		setTitle("Security Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Update Class Rights");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegisterClassRights win = new RegisterClassRights(trackID, roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(41, 171, 223, 86);
		contentPane.add(btnNewButton);
		
		JButton btnViewupdateEmployee = new JButton("View /Update Employee Role");
		btnViewupdateEmployee.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			UpdateEmployee win = new UpdateEmployee(trackID, roleid, empID, userName);
			win.setVisible(true);
		}
	});
		btnViewupdateEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewupdateEmployee.setBounds(349, 171, 236, 86);
		contentPane.add(btnViewupdateEmployee);
		
		JButton btnViewTrackeLogins = new JButton("View Tracked Logins");
		btnViewTrackeLogins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ViewTrackedLogins win = new ViewTrackedLogins(trackID, roleid, empID, userName);
				win.setVisible(true);
			}
		});
		btnViewTrackeLogins.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewTrackeLogins.setBounds(201, 55, 223, 86);
		contentPane.add(btnViewTrackeLogins);
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SecurityAcc s= new SecurityAcc();
				s.insertLogout(trackID, empID);
				System.exit(1);
				
			}
		});
		btnNewButton_1.setBounds(464, 11, 110, 34);
		contentPane.add(btnNewButton_1);
	}
}
