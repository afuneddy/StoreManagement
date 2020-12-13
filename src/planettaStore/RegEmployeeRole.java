package planettaStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class RegEmployeeRole extends JFrame {

	private JPanel contentPane;
	private JTextField roleT;
	private JTextField roleIDT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegEmployeeRole frame = new RegEmployeeRole();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegEmployeeRole() {
		setTitle("Register Employee Role");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Role");
		lblNewLabel.setBounds(67, 144, 46, 14);
		contentPane.add(lblNewLabel);
		
		roleT = new JTextField();
		roleT.setBounds(138, 141, 166, 31);
		contentPane.add(roleT);
		roleT.setColumns(10);
		
		JButton roleIDB = new JButton("Generate RoleID");
		roleIDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Employee e = new Employee();
				roleIDT.setText(e.generateRoleID()+"");
			}
		});
		roleIDB.setBounds(47, 86, 140, 31);
		contentPane.add(roleIDB);
		
		roleIDT = new JTextField();
		roleIDT.setEditable(false);
		roleIDT.setColumns(10);
		roleIDT.setBounds(218, 91, 86, 26);
		contentPane.add(roleIDT);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String role = roleT.getText().trim();
				
				int roleid = Integer.parseInt(roleIDT.getText());
				
				Employee e = new Employee();
				if(e.registerRole(roleid, role)){
					JOptionPane.showMessageDialog(null, "Role registration has Succeeded");
				roleIDT.setText(null);
				roleT.setText(null);
				}
				else
					JOptionPane.showMessageDialog(null, "Role registration has Failed");
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(244, 217, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				roleIDT.setText(null);
				roleT.setText(null);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setForeground(Color.MAGENTA);
		btnCancel.setBounds(59, 217, 89, 23);
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.CYAN);
		panel.setBounds(60, 11, 244, 31);
		contentPane.add(panel);
		
		JLabel lblRegisterEmployeeRole = new JLabel("Register Employee Role");
		lblRegisterEmployeeRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterEmployeeRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegisterEmployeeRole.setBounds(41, 11, 168, 14);
		panel.add(lblRegisterEmployeeRole);
	}
}
