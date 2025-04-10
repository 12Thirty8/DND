import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;

public class Login {

    private JFrame frame;
    private static JTextField userfield;
    private JPasswordField psfield;
    private DatabaseConnect dbConnect = new DatabaseConnect();
    public static int userid;

    public Login() {
        initialize();
    }

    public static void displayLogin() {
        Login window = new Login();
        window.frame.setVisible(true);
    }

    public void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(1363, 720);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel userlabel = new JLabel("Username:");
        userlabel.setForeground(new Color(255, 255, 255));
        userlabel.setFont(new Font("Constantia", Font.BOLD, 20));
        userlabel.setBounds(231, 613, 102, 31);
        frame.getContentPane().add(userlabel);

        userfield = new JTextField();
        userfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userfield.setBounds(343, 613, 161, 31);
        frame.getContentPane().add(userfield);
        userfield.setColumns(10);

        JLabel pslabel = new JLabel("Password:");
        pslabel.setForeground(new Color(255, 255, 255));
        pslabel.setFont(new Font("Constantia", Font.BOLD, 20));
        pslabel.setBounds(514, 613, 97, 31);
        frame.getContentPane().add(pslabel);

        psfield = new JPasswordField();
        psfield.setFont(new Font("Tahoma", Font.PLAIN, 20));
        psfield.setBounds(621, 613, 161, 31);
        frame.getContentPane().add(psfield);

        JButton loginbtn = new JButton("Login");
        loginbtn.setForeground(new Color(255, 255, 255));
        loginbtn.setBackground(new Color(128, 0, 0));
        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	getUserId();
            }
        });
        loginbtn.setFont(new Font("Constantia", Font.BOLD, 20));
        loginbtn.setBounds(859, 607, 121, 43);
        frame.getContentPane().add(loginbtn);

        JButton signupbtn = new JButton("Sign Up");
        signupbtn.setForeground(new Color(255, 255, 255));
        signupbtn.setBackground(new Color(128, 0, 0));
        signupbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Signup.displaySignup();
            }
        });signupbtn.setFont(new Font("Constantia", Font.BOLD, 20));
        signupbtn.setBounds(990, 607, 121, 43);
        frame.getContentPane().add(signupbtn);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\title.png"));
        lblNewLabel.setBounds(10, 0, 1337, 673);
        frame.getContentPane().add(lblNewLabel);
    }
    public void getUserId() {
        String username = userfield.getText().trim();
        String password = new String(psfield.getPassword());

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
            Connection con = dbConnect.connect();
            Statement stm = con.createStatement();
            String sql = "SELECT user_id, username, password FROM user where username='" + username + "' and password='" + password + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                userid = rs.getInt("user_id");
                frame.dispose();
                Home home = new Home();
                home.initialize();
                home.frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect." , "Error", JOptionPane.ERROR_MESSAGE);
                userfield.setText("");
                psfield.setText("");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
    	public static String getUsernameData() {
    		return userfield.getText();
    	}
}