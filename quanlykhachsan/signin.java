package quanlykhachsan;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class signin extends JFrame {

	private JPanel contentPane;
	private JTextField txtusernameup;
	private JPasswordField txtpassword;
	private JPasswordField txtconfirmpassword;
	
	private JPasswordField passwordField;
	private JComboBox comboBox;
    private DataInputStream in;
    private DataOutputStream out;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {

		signin a = new signin();
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
		a.setLocationRelativeTo(null);
		a.setVisible(true);

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public signin() throws UnknownHostException, IOException {
Socket socket = new Socket("localhost", 98);
        
    	in = new DataInputStream(socket.getInputStream());
    	out = new DataOutputStream(socket.getOutputStream());
    	
    	new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());
			
				while (true) {
					String action =in.readUTF();
					switch (action) {
					case "signup": 
						String success =in.readUTF();

						if(success.equals("signupsuccess")) {
							JOptionPane.showMessageDialog(null, "Sign Up Success!");
							framedangnhap frame = new framedangnhap();
							
							dispose();
						}else {
							System.out.print("false");
						}
						
				 
						break;
					case "phong1trong": 
						break;
					case "phongt2rong": 
						break;
					}
					
					
			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1127, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0));
		panel.setBounds(0, 0, 1113, 515);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SIGN UP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(247, 28, 677, 456);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtusernameup = new JTextField();
		txtusernameup.setBounds(100, 32, 480, 55);
		panel_1.add(txtusernameup);
		txtusernameup.setColumns(10);

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				StringBuilder emty = new StringBuilder();
				if (txtusernameup.getText().equals("")) {
					emty.append("Username must be entered!");
				}
				if (txtpassword.getText().equals("")) {
					emty.append("\nPassword must be entered!");
				}
				if (!passwordField.getText().equals("2301")) {
					emty.append("\nWrong security code!");
				}
				if (txtconfirmpassword.getText().equals("")) {
					emty.append("\nConfirm password must be entered!");
				}
				if (!emty.isEmpty()) {
					JOptionPane.showMessageDialog(null, emty);
				} else if (!txtpassword.getText().equals(txtconfirmpassword.getText())) {
					JOptionPane.showMessageDialog(null, "Password and confirm password must be the same.");
				} else {

				try {
					out.writeUTF("signup");
					out.writeUTF(txtusernameup.getText());
					out.writeUTF(txtpassword.getText());
					out.writeUTF(String.valueOf(comboBox.getSelectedItem()));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				}

			}
		});
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(229, 370, 351, 41);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(576, 421, 91, 25);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(100, 10, 82, 25);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(100, 97, 132, 25);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(99, 185, 148, 18);
		panel_1.add(lblNewLabel_2);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(100, 120, 480, 55);
		panel_1.add(txtpassword);

		txtconfirmpassword = new JPasswordField();
		txtconfirmpassword.setBounds(100, 202, 480, 55);
		panel_1.add(txtconfirmpassword);

		JLabel lblNewLabel_3 = new JLabel("Security code");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(100, 354, 106, 18);
		panel_1.add(lblNewLabel_3);

		passwordField = new JPasswordField();
		passwordField.setBounds(100, 370, 119, 41);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Position");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(99, 267, 148, 18);
		panel_1.add(lblNewLabel_2_1);
		
		 comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Manager"}));
		comboBox.setBounds(100, 295, 480, 50);
		panel_1.add(comboBox);
	}
}
