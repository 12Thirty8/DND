import javax.swing.*;
import java.sql.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class Signup {
	
	private JFrame frame;
	private JTextField tfuseracc;
	private JLabel lblNewLabel;
	private JPasswordField tfuserps;
    private DatabaseConnect dbConnect = new DatabaseConnect();
	
	public Signup() {
		initialize();
	}
	
	public static void displaySignup() {
		Signup window = new Signup();
		window.frame.setVisible(true);
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setSize(910,615);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		tfuseracc = new JTextField();
		tfuseracc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfuseracc.setBounds(253, 260, 203, 26);
		frame.getContentPane().add(tfuseracc);
		tfuseracc.setColumns(10);
		
		lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Maiandra GD", Font.BOLD, 20));
		lblNewLabel.setBounds(146, 255, 110, 26);
		frame.getContentPane().add(lblNewLabel);
		
		tfuserps = new JPasswordField();
		tfuserps.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfuserps.setBounds(253, 335, 203, 26);
		frame.getContentPane().add(tfuserps);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Maiandra GD", Font.BOLD, 20));
		lblPassword.setBounds(146, 335, 110, 26);
		frame.getContentPane().add(lblPassword);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfuseracc.getText().trim();
		        String password = new String(tfuserps.getPassword());

		        // Check if the username length is between 6 and 16 characters
		        if (username.length() < 6 || username.length() > 16) {
		            JOptionPane.showMessageDialog(null, "Username must be between 6 and 16 characters.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Check if the password length is between 8 and 40 characters
		        if (password.length() < 8 || password.length() > 40) {
		            JOptionPane.showMessageDialog(null, "Password must be between 8 and 40 characters.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
					try {
					createAccount();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		btnNewButton.setBounds(146, 415, 153, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(51, 0, 0));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		btnClear.setBounds(331, 415, 125, 44);
		frame.getContentPane().add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Create an Account");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.BOLD, 40));
		lblNewLabel_1.setBounds(106, 172, 393, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(51, 0, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.displayLogin();
			}
		});
		btnBack.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		btnBack.setBounds(730, 27, 125, 44);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(51, 0, 0));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\login.jpg"));
		lblNewLabel_2.setBounds(0, 0, 896, 578);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
		private void createAccount() throws SQLException {
			String user = tfuseracc.getText();
			String pass = new String(tfuserps.getPassword());
			String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
			try (Connection connection = dbConnect.connect();
					PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, user);
				statement.setString(2, pass);
				statement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Account created successfully!");
				frame.dispose();
				Login.displayLogin();
			}
		}
}
