import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import net.proteanit.sql.DbUtils;

import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CharacterType {
	JFrame frame;
	DatabaseConnect dbconncect;
	JComboBox<String> raceComboBox, characterClassComboBox;

	private static DatabaseConnect dbConnect = new DatabaseConnect();
	private JTextField tfremainingpoints;
	private JTextField tfHP;
	private JTextField tfMP;
	private JTextField tfAP;
	private JTextField tfEV;
	private JTextField tfSP;
	private JTextField tfPC;
	private JTextField tfPS;
	private JScrollPane scrollPane;
	private JSpinner spinnerSTR, spinnerDEX, spinnerCON, spinnerWIS, spinnerINT, spinnerCHA;

	public CharacterType() {
		initialize();
	}

	public static void displayCharacter() {
		CharacterType window = new CharacterType();
		window.frame.setVisible(true);
		loadTable();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(102, 51, 0));
		frame.setSize(1219, 777);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(0, 0, 0));
		panelMain.setBounds(0, 0, 1205, 148);
		frame.getContentPane().add(panelMain);
		panelMain.setLayout(null);

		JLabel lblNewLabel = new JLabel("Create a Character");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(235, 40, 658, 98);
		panelMain.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 65));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 1205, 148);
		panelMain.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\budia\\eclipse-workspace\\DND\\Images\\bg1.jpg"));

		JPanel panelJourney = new JPanel();
		panelJourney.setBackground(new Color(255, 204, 102));
		panelJourney.setBorder(new TitledBorder(null, "Your journey begins here!", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelJourney.setBounds(22, 180, 340, 226);
		frame.getContentPane().add(panelJourney);
		panelJourney.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblName.setBounds(38, 32, 55, 32);
		panelJourney.add(lblName);

		JTextField tfname = new JTextField();
		tfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfname.setBounds(103, 32, 187, 32);
		panelJourney.add(tfname);

		JLabel lblClass = new JLabel("Class:");
		lblClass.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblClass.setBounds(38, 93, 55, 32);
		panelJourney.add(lblClass);

		JLabel lblRace = new JLabel("Race:");
		lblRace.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblRace.setBounds(38, 157, 55, 32);
		panelJourney.add(lblRace);

		characterClassComboBox = new JComboBox<>();
		characterClassComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		characterClassComboBox.setBounds(103, 93, 187, 32);
		characterClassComboBox.addItem("--Select Class--");
		panelJourney.add(characterClassComboBox);

		raceComboBox = new JComboBox<>();
		raceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		raceComboBox.setBounds(103, 157, 187, 32);
		raceComboBox.addItem("--Select Race--");
		panelJourney.add(raceComboBox);

		JPanel panelAttributes = new JPanel();
		panelAttributes.setBackground(new Color(255, 204, 102));
		panelAttributes.setBorder(
				new TitledBorder(null, "Set your attributes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAttributes.setBounds(372, 180, 362, 226);
		frame.getContentPane().add(panelAttributes);
		panelAttributes.setLayout(null);

		JLabel lblSTR = new JLabel("STR:");
		lblSTR.setBackground(new Color(240,240,240));
		lblSTR.setToolTipText("Strength");
		lblSTR.setForeground(new Color(255, 0, 0));
		lblSTR.setBounds(52, 35, 54, 21);
		lblSTR.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		panelAttributes.add(lblSTR);

		JLabel lblDEX = new JLabel("DEX:");
		lblDEX.setToolTipText("Dexterity");
		lblDEX.setForeground(new Color(51, 153, 51));
		lblDEX.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblDEX.setBounds(52, 90, 54, 21);
		panelAttributes.add(lblDEX);

		JLabel lblCON = new JLabel("CON:");
		lblCON.setToolTipText("Constitution");
		lblCON.setForeground(new Color(204, 51, 0));
		lblCON.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblCON.setBounds(52, 149, 54, 21);
		panelAttributes.add(lblCON);

		JLabel lblINT = new JLabel("INT:");
		lblINT.setToolTipText("Intelligence");
		lblINT.setForeground(new Color(255, 0, 204));
		lblINT.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblINT.setBounds(209, 35, 54, 21);
		panelAttributes.add(lblINT);

		JLabel lblCHA = new JLabel("CHA:");
		lblCHA.setToolTipText("Charisma");
		lblCHA.setForeground(new Color(0, 0, 0));
		lblCHA.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblCHA.setBounds(209, 149, 54, 21);
		panelAttributes.add(lblCHA);

		JLabel lblWIS = new JLabel("WIS:");
		lblWIS.setToolTipText("Wisdom");
		lblWIS.setForeground(new Color(51, 0, 255));
		lblWIS.setBounds(209, 90, 54, 21);
		panelAttributes.add(lblWIS);
		lblWIS.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

		spinnerSTR = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerSTR.setBounds(116, 35, 30, 24);
		panelAttributes.add(spinnerSTR);

		spinnerDEX = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerDEX.setBounds(116, 90, 30, 24);
		panelAttributes.add(spinnerDEX);

		spinnerCON = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerCON.setBounds(116, 149, 30, 24);
		panelAttributes.add(spinnerCON);

		spinnerINT = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerINT.setBounds(273, 35, 30, 24);
		panelAttributes.add(spinnerINT);

		spinnerWIS = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerWIS.setBounds(273, 90, 30, 24);
		panelAttributes.add(spinnerWIS);

		spinnerCHA = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerCHA.setBounds(273, 149, 30, 24);
		panelAttributes.add(spinnerCHA);

		JLabel lblremainingpoints = new JLabel("Remaining Points:");
		lblremainingpoints.setForeground(new Color(0, 0, 204));
		lblremainingpoints.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblremainingpoints.setBounds(66, 183, 156, 21);
		panelAttributes.add(lblremainingpoints);

		tfremainingpoints = new JTextField();
		tfremainingpoints.setText("4");
		tfremainingpoints.setEditable(false);
		tfremainingpoints.setBounds(232, 180, 45, 26);
		panelAttributes.add(tfremainingpoints);
		tfremainingpoints.setColumns(10);

		JPanel panelDerived = new JPanel();
		panelDerived.setBorder(
				new TitledBorder(null, "DERIVED STATS:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDerived.setBackground(new Color(255, 204, 102));
		panelDerived.setBounds(744, 180, 438, 226);
		frame.getContentPane().add(panelDerived);
		panelDerived.setLayout(null);

		JLabel lblHitpoints = new JLabel("Hit Points:");
		lblHitpoints.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblHitpoints.setBounds(42, 32, 126, 21);
		panelDerived.add(lblHitpoints);

		tfHP = new JTextField();
		tfHP.setEditable(false);
		tfHP.setColumns(10);
		tfHP.setBounds(178, 32, 45, 26);
		panelDerived.add(tfHP);

		JLabel lblManaPoints = new JLabel("Mana Points:");
		lblManaPoints.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblManaPoints.setBounds(42, 81, 126, 21);
		panelDerived.add(lblManaPoints);

		tfMP = new JTextField();
		tfMP.setEditable(false);
		tfMP.setColumns(10);
		tfMP.setBounds(178, 81, 45, 26);
		panelDerived.add(tfMP);

		JLabel lblAttackPower = new JLabel("Attack Power:");
		lblAttackPower.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblAttackPower.setBounds(42, 129, 126, 21);
		panelDerived.add(lblAttackPower);

		tfAP = new JTextField();
		tfAP.setEditable(false);
		tfAP.setColumns(10);
		tfAP.setBounds(178, 129, 45, 26);
		panelDerived.add(tfAP);

		JLabel lblEvasion = new JLabel("Evasion:");
		lblEvasion.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblEvasion.setBounds(121, 179, 126, 21);
		panelDerived.add(lblEvasion);

		tfEV = new JTextField();
		tfEV.setEditable(false);
		tfEV.setColumns(10);
		tfEV.setBounds(257, 179, 45, 26);
		panelDerived.add(tfEV);

		JLabel lblSpellPower = new JLabel("Spell Power:");
		lblSpellPower.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblSpellPower.setBounds(233, 32, 126, 21);
		panelDerived.add(lblSpellPower);

		tfSP = new JTextField();
		tfSP.setEditable(false);
		tfSP.setColumns(10);
		tfSP.setBounds(369, 32, 45, 26);
		panelDerived.add(tfSP);

		JLabel lblPerception = new JLabel("Perception:");
		lblPerception.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPerception.setBounds(233, 81, 126, 21);
		panelDerived.add(lblPerception);

		tfPC = new JTextField();
		tfPC.setEditable(false);
		tfPC.setColumns(10);
		tfPC.setBounds(369, 81, 45, 26);
		panelDerived.add(tfPC);

		JLabel lblPersuasion = new JLabel("Persuasion:");
		lblPersuasion.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPersuasion.setBounds(233, 129, 126, 21);
		panelDerived.add(lblPersuasion);

		tfPS = new JTextField();
		tfPS.setEditable(false);
		tfPS.setColumns(10);
		tfPS.setBounds(369, 130, 45, 26);
		panelDerived.add(tfPS);

		JButton btnsave = new JButton("SAVE");
		btnsave.setBackground(new Color(51, 255, 102));
		btnsave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfremainingpoints.getText().equals("0")) {
				    JOptionPane.showMessageDialog(null, "You must spend all remaining points.");
				    return;
				}
				try {
					createCharacter(tfname.getText(), (String) raceComboBox.getSelectedItem(),
							(String) characterClassComboBox.getSelectedItem(),(int) Login.userid, (int) spinnerSTR.getValue(),
							(int) spinnerDEX.getValue(), (int) spinnerCON.getValue(), (int) spinnerINT.getValue(),
							(int) spinnerWIS.getValue(), (int) spinnerCHA.getValue());
					loadTable();
					tfname.setText("");
					characterClassComboBox.setSelectedIndex(0);
					raceComboBox.setSelectedIndex(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "Error creating character: " + e1.getMessage());
				}
			}
		});
		btnsave.setBounds(22, 416, 103, 50);
		frame.getContentPane().add(btnsave);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfname.setText("");
				tfid.setText("");
				characterClassComboBox.setSelectedIndex(0);
				raceComboBox.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(259, 416, 103, 50);
		frame.getContentPane().add(btnClear);

		JButton btnhome = new JButton("HOME");
		btnhome.setBounds(135, 416, 114, 50);
		frame.getContentPane().add(btnhome);
		btnhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Home.displayHome();
			}
		});
		btnhome.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 416, 810, 295);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(22, 476, 340, 239);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblid = new JLabel("ID #:");
		lblid.setBounds(38, 51, 59, 21);
		lblid.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		panel.add(lblid);

		tfid = new JTextField();
		tfid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				setdefaultValues();
				Connection conn = dbConnect.connect();
				String id = tfid.getText();
				String sql = ("SELECT char_name, race_id, class_id, str, dex, con, intel, wis, cha FROM chartable where char_id = ?");
				try {
					PreparedStatement pst;
					pst = conn.prepareStatement(sql);
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if (rs.next()==true) {
						String name = rs.getString(1);
						int race = rs.getInt(2);
						int classtype = rs.getInt(3);
						int str = rs.getInt(4);
						int dex = rs.getInt(5);
						int con = rs.getInt(6);
						int intel = rs.getInt(7);
						int wis = rs.getInt(8);
						int cha = rs.getInt(9);
						
						tfname.setText(name);
						raceComboBox.setSelectedIndex(race);
						characterClassComboBox.setSelectedIndex(classtype);
						spinnerSTR.setValue(str);
						spinnerDEX.setValue(dex);
						spinnerCON.setValue(con);
						spinnerINT.setValue(intel);
						spinnerWIS.setValue(wis);
						spinnerCHA.setValue(cha);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tfid.setBounds(102, 47, 190, 28);
		tfid.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(tfid);

		JButton btnupdate = new JButton("UPDATE");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String take = tfid.getText();
					int idval = Integer.parseInt(take);
					updateCharacter(tfname.getText(), (String) raceComboBox.getSelectedItem(),
							(String) characterClassComboBox.getSelectedItem(), (int) spinnerSTR.getValue(),
							(int) spinnerDEX.getValue(), (int) spinnerCON.getValue(), (int) spinnerINT.getValue(),
							(int) spinnerWIS.getValue(), (int) spinnerCHA.getValue(), (int) idval);
					loadTable();
					tfname.setText("");
					characterClassComboBox.setSelectedIndex(0);
					raceComboBox.setSelectedIndex(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnupdate.setForeground(new Color(0, 0, 0));
		btnupdate.setBackground(new Color(255, 255, 51));
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnupdate.setBounds(25, 112, 130, 75);
		panel.add(btnupdate);

		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String take = tfid.getText();
					int idval = Integer.parseInt(take);
					deleteCharacter((int) idval);
					loadTable();
					tfname.setText("");
					characterClassComboBox.setSelectedIndex(0);
					raceComboBox.setSelectedIndex(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btndelete.setBackground(new Color(255, 0, 51));
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndelete.setBounds(187, 112, 126, 75);
		panel.add(btndelete);

		try {
			List<String> raceList = getRaces();
			List<String> characterClassList = getCharacterClasses();

			for (String race : raceList) {
				raceComboBox.addItem(race);
			}

			for (String characterClass : characterClassList) {
				characterClassComboBox.addItem(characterClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ChangeListener spinnerListener = e -> updateRemainingPoints();
		spinnerSTR.addChangeListener(spinnerListener);
		spinnerDEX.addChangeListener(spinnerListener);
		spinnerCON.addChangeListener(spinnerListener);
		spinnerINT.addChangeListener(spinnerListener);
		spinnerWIS.addChangeListener(spinnerListener);
		spinnerCHA.addChangeListener(spinnerListener);

		ChangeListener spinnerListener2 = e -> displayDerived();
		spinnerSTR.addChangeListener(spinnerListener2);
		spinnerDEX.addChangeListener(spinnerListener2);
		spinnerCON.addChangeListener(spinnerListener2);
		spinnerINT.addChangeListener(spinnerListener2);
		spinnerWIS.addChangeListener(spinnerListener2);
		spinnerCHA.addChangeListener(spinnerListener2);

	}
	//"SELECT * FROM chartable WHERE user_id = "+ Login.userid//
	private static void loadTable() {
		try {
			Connection conn = dbConnect.connect();
			String query = ("SELECT ch.char_id, ch.char_name,r.race_name AS race_name, cl.class_name AS class_name, ch.str, ch.dex, ch.con, ch.intel, ch.wis, ch.cha FROM chartable ch JOIN race r ON ch.race_id = r.race_id JOIN class cl ON ch.class_id = cl.class_id WHERE ch.user_id = "+ Login.userid);
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void displayDerived() {
		int hp = ((int) spinnerCON.getValue() * 2) + 10;
		tfHP.setText(String.valueOf(hp));

		int mp = ((int) spinnerINT.getValue() * 2) + 10;
		tfMP.setText(String.valueOf(mp));

		int ap = ((int) spinnerSTR.getValue() * 2) + 5;
		tfAP.setText(String.valueOf(ap));

		int ev = ((int) spinnerDEX.getValue() * 2) + 5;
		tfEV.setText(String.valueOf(ev));

		int sp = ((int) spinnerINT.getValue() * 2) + 5;
		tfSP.setText(String.valueOf(sp));

		int pc = ((int) spinnerWIS.getValue() * 2) + 5;
		tfPC.setText(String.valueOf(pc));

		int ps = ((int) spinnerCHA.getValue() * 2) + 5;
		tfPS.setText(String.valueOf(ps));
	}

	int previousSTRValue, previousDEXValue, previousCONValue, previousINTValue, previousWISValue, previousCHAValue;
	private static JTable table;
	private JTextField tfid;

	private void updateRemainingPoints() {
		int totalPoints = (int) spinnerSTR.getValue() + (int) spinnerDEX.getValue() + (int) spinnerCON.getValue()
				+ (int) spinnerINT.getValue() + (int) spinnerWIS.getValue() + (int) spinnerCHA.getValue();

		if (totalPoints > 10) {
			JOptionPane.showMessageDialog(frame, "Total points cannot exceed 10!");
			// Revert spinner values to their previous state
			spinnerSTR.setValue(previousSTRValue);
			spinnerDEX.setValue(previousDEXValue);
			spinnerCON.setValue(previousCONValue);
			spinnerINT.setValue(previousINTValue);
			spinnerWIS.setValue(previousWISValue);
			spinnerCHA.setValue(previousCHAValue);
			return;
		}

		// Update remaining points display
		int remainingPoints = 10 - totalPoints;
		tfremainingpoints.setText(String.valueOf(remainingPoints));

		// Check if any spinner value is less than 1 or greater than 5
		if ((int) spinnerSTR.getValue() < 1 || (int) spinnerSTR.getValue() > 5 || (int) spinnerDEX.getValue() < 1
				|| (int) spinnerDEX.getValue() > 5 || (int) spinnerCON.getValue() < 1 || (int) spinnerCON.getValue() > 5
				|| (int) spinnerINT.getValue() < 1 || (int) spinnerINT.getValue() > 5 || (int) spinnerWIS.getValue() < 1
				|| (int) spinnerWIS.getValue() > 5 || (int) spinnerCHA.getValue() < 1
				|| (int) spinnerCHA.getValue() > 5) {
			JOptionPane.showMessageDialog(frame, "Attribute values must be between 1 and 5!");
		}

		// Update previous values
		previousSTRValue = (int) spinnerSTR.getValue();
		previousDEXValue = (int) spinnerDEX.getValue();
		previousCONValue = (int) spinnerCON.getValue();
		previousINTValue = (int) spinnerINT.getValue();
		previousWISValue = (int) spinnerWIS.getValue();
		previousCHAValue = (int) spinnerCHA.getValue();
	}

	private void createCharacter(String name, String race, String characterClass, int user, int str, int dex, int con, int intel,
			int wis, int cha) throws SQLException {
		Connection conn = dbConnect.connect();
		String query = "INSERT INTO chartable (char_name, race_id, class_id, user_id, str, dex, con, intel, wis, cha) VALUES (?, (SELECT race_id FROM race WHERE race_name = ?), (SELECT class_id FROM class WHERE class_name = ?), ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, race);
		pstmt.setString(3, characterClass);
		pstmt.setInt(4, user);
		pstmt.setInt(5, str);
		pstmt.setInt(6, dex);
		pstmt.setInt(7, con);
		pstmt.setInt(8, intel);
		pstmt.setInt(9, wis);
		pstmt.setInt(10, cha);
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Character Created Successfully");
	}

	private void updateCharacter(String name, String race, String characterClass, int str, int dex, int con, int intel,
			int wis, int cha, int id) throws SQLException {
		Connection conn = dbConnect.connect();
		String query = "UPDATE chartable SET char_name = ?, race_id = (SELECT race_id FROM race WHERE race_name = ?), class_id = (SELECT class_id FROM class WHERE class_name = ?), str = ?, dex = ?, con = ?, intel = ?, wis = ?, cha = ? WHERE char_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, race);
		pstmt.setString(3, characterClass);
		pstmt.setInt(4, str);
		pstmt.setInt(5, dex);
		pstmt.setInt(6, con);
		pstmt.setInt(7, intel);
		pstmt.setInt(8, wis);
		pstmt.setInt(9, cha);
		pstmt.setInt(10, id);
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Character Updated Successfully");
	}

	private void deleteCharacter(int id) throws SQLException {
		Connection conn = dbConnect.connect();
		String query = "DELETE FROM chartable WHERE char_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Character Deleted Successfully");
	}

	private List<String> getRaces() throws SQLException {
		Connection conn = dbConnect.connect();
		String query = "SELECT race_name FROM race";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<String> raceList = new ArrayList<>();
		while (rs.next()) {
			raceList.add(rs.getString("race_name"));
		}
		return raceList;
	}

	private List<String> getCharacterClasses() throws SQLException {
		Connection conn = dbConnect.connect();
		String query = "SELECT class_name FROM class";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<String> characterClassList = new ArrayList<>();
		while (rs.next()) {
			characterClassList.add(rs.getString("class_name"));
		}
		return characterClassList;
	}
	
	private void setdefaultValues() {
		spinnerSTR.setValue(1);
		spinnerDEX.setValue(1);
		spinnerCON.setValue(1);
		spinnerINT.setValue(1);
		spinnerWIS.setValue(1);
		spinnerCHA.setValue(1);
		
	}
}