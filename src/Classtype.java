import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Classtype {
	JFrame frame;
	DatabaseConnect dbconncect;
	
	private static DatabaseConnect dbConnect = new DatabaseConnect();
	private static JTable table;
	
	public Classtype() {
		initialize();
	}
	
	public static void displayClass() {
		Classtype window = new Classtype();
		window.frame.setVisible(true);
		loadTable();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(800,450);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
		JButton btnNewButton = new JButton("Create Class");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createClass();
					loadTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		btnNewButton.setBounds(88, 109, 183, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdateRace = new JButton("Update Class");
		btnUpdateRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateClass();
					loadTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdateRace.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		btnUpdateRace.setBounds(88, 179, 183, 41);
		frame.getContentPane().add(btnUpdateRace);
		
		JButton btnDeleteRace = new JButton("Delete Class");
		btnDeleteRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteClass();
					loadTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeleteRace.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		btnDeleteRace.setBounds(88, 249, 183, 41);
		frame.getContentPane().add(btnDeleteRace);
		
		JButton btnNewButton_1 = new JButton("HOME");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Home.displayHome();
			}
		});
		btnNewButton_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		btnNewButton_1.setBounds(10, 10, 97, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 86, 396, 240);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\class.jpg"));
		lblNewLabel.setBounds(0, 0, 786, 413);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void createClass() throws SQLException {
		String className = JOptionPane.showInputDialog("Enter the class name:");

		String sql = "INSERT INTO class (class_name) VALUES (?)";
		try (Connection connection = dbConnect.connect();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, className);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "New class created successfully");
		}
	}
	
	public void updateClass() throws SQLException {
        String input = JOptionPane.showInputDialog("Enter the class id to update:");
        int id = Integer.parseInt(input);

        String className = JOptionPane.showInputDialog("Enter the updated class' name:");

        String sql = "UPDATE class SET class_name = ? WHERE class_id = ?";
        try (Connection connection = dbConnect.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, className);
            statement.setInt(2, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Class updated successfully");
        }
    }

    public void deleteClass() throws SQLException {
        String input = JOptionPane.showInputDialog("Enter the class id to delete:");
        int id = Integer.parseInt(input);

        String sql = "DELETE FROM class WHERE class_id = ?";
        try (Connection connection = dbConnect.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Class deleted successfully");
        }
    }
    
    private static void loadTable() {
		try {
			Connection conn = dbConnect.connect();
			String query = ("SELECT * FROM class");
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
