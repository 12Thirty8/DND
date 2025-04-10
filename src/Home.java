import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Home extends DatabaseConnect {
	JFrame frame;
	JLabel lbluser;

	public Home() {
		initialize();
	}

	public static void displayHome() {
		Home window = new Home();
		window.frame.setVisible(true);
	}

	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(800, 536);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 51, 0));
		panel.setBounds(263, 45, 263, 454);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnSkills_1 = new JButton("Characters");
		btnSkills_1.setForeground(new Color(204, 0, 0));
		btnSkills_1.setBackground(new Color(0, 0, 0));
		btnSkills_1.setBounds(58, 384, 146, 38);
		panel.add(btnSkills_1);
		btnSkills_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CharacterType.displayCharacter();
			}
		});
		btnSkills_1.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\bgchar.jpg"));
		lblNewLabel_2.setBounds(0, 0, 263, 454);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 204, 51));
		panel_1.setBounds(0, 45, 263, 454);
		frame.getContentPane().add(panel_1);

		JButton btnClass = new JButton("Class");
		btnClass.setForeground(new Color(51, 102, 51));
		btnClass.setBackground(new Color(0, 0, 0));
		btnClass.setBounds(58, 384, 146, 38);
		panel_1.add(btnClass);
		btnClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Classtype.displayClass();
			}
		});
		btnClass.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\bgclass.jpg"));
		lblNewLabel_1.setBounds(0, 0, 263, 454);
		panel_1.add(lblNewLabel_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(204, 0, 0));
		panel_1_1.setBounds(524, 45, 259, 454);
		frame.getContentPane().add(panel_1_1);

		JButton btnRace = new JButton("Race");
		btnRace.setForeground(new Color(204, 153, 0));
		btnRace.setBackground(new Color(0, 0, 0));
		btnRace.setBounds(58, 384, 146, 38);
		panel_1_1.add(btnRace);
		btnRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Race.displayRace();
			}
		});
		btnRace.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
		
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setBounds(0, 0, 263, 454);
				panel_1_1.add(lblNewLabel);
				lblNewLabel.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\bgrace.jpg"));
				
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(0, 0, 0));
				panel_2.setBounds(0, 0, 783, 47);
				frame.getContentPane().add(panel_2);
				panel_2.setLayout(null);
				
				JButton btnNewButton = new JButton("Logout");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						Login.displayLogin();
					}
				});
				btnNewButton.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
				btnNewButton.setForeground(new Color(0, 153, 255));
				btnNewButton.setBackground(new Color(0, 0, 0));
				btnNewButton.setBounds(10, 10, 107, 37);
				panel_2.add(btnNewButton);
				

				String text = Login.getUsernameData();
				lbluser = new JLabel("Welcome, "+ text);
				lbluser.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 25));
				lbluser.setForeground(new Color(255, 255, 255));
				lbluser.setBounds(197, 12, 190, 28);
				panel_2.add(lbluser);
	}
}
