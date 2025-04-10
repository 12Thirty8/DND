import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class Race {
	JFrame frame;
	DatabaseConnect dbconncect;
	
	private static DatabaseConnect dbConnect = new DatabaseConnect();
	private static JTable table;
	
	public Race() {
		initialize();
	}
	
	public static void displayRace() {
		Race window = new Race();
		window.frame.setVisible(true);
		loadTable();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(800,450);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Create Race");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createRace();
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
		
		JButton btnUpdateRace = new JButton("Update Race");
		btnUpdateRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateRace();
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
		
		JButton btnDeleteRace = new JButton("Delete Race");
		btnDeleteRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteRace();
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
		btnNewButton_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 10, 97, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 86, 396, 240);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\race.jpg"));
		lblNewLabel.setBounds(0, 0, 786, 413);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void createRace() throws SQLException {
		String raceName = JOptionPane.showInputDialog("Enter the race name:");

		String sql = "INSERT INTO race (race_name) VALUES (?)";
		try (Connection connection = dbConnect.connect();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, raceName);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "New race created successfully");
		}
	}
	
	public void readRace() throws SQLException {
        String sql = "SELECT * FROM race";
        try (Connection connection = dbConnect.connect();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                StringBuilder skillsList = new StringBuilder();
                while (resultSet.next()) {
                    int id = resultSet.getInt("race_id");
                    String name = resultSet.getString("race_name");
                    skillsList.append("id: ").append(id).append(" - Name: ").append(name).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Races:\n" + skillsList.toString());
            }
        }
    }
	
	public void updateRace() throws SQLException {
        String input = JOptionPane.showInputDialog("Enter the race id to update:");
        int id = Integer.parseInt(input);

        String skillName = JOptionPane.showInputDialog("Enter the updated race's name:");

        String sql = "UPDATE race SET race_name = ? WHERE race_id = ?";
        try (Connection connection = dbConnect.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, skillName);
            statement.setInt(2, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Race updated successfully");
        }
    }

    public void deleteRace() throws SQLException {
        String input = JOptionPane.showInputDialog("Enter the race id to delete:");
        int id = Integer.parseInt(input);

        String sql = "DELETE FROM race WHERE race_id = ?";
        try (Connection connection = dbConnect.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Race deleted successfully");
        }
    }
    
    private static void loadTable() {
		try {
			Connection conn = dbConnect.connect();
			String query = ("SELECT * FROM race");
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
