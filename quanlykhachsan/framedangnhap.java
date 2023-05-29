package quanlykhachsan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class framedangnhap extends JFrame {

	private JPanel contentPane;
	public static JTextField txtuser;
	private JPasswordField txtpass;
	public static String a;
    private DataInputStream in;
    private DataOutputStream out;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {

		framedangnhap a = new framedangnhap();

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public framedangnhap() throws UnknownHostException, IOException {
		init();

		this.setTitle("QUẢN LÝ KHÁCH SẠN");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void init() throws UnknownHostException, IOException {
		
Socket socket = new Socket("localhost", 98);
        
    	in = new DataInputStream(socket.getInputStream());
    	out = new DataOutputStream(socket.getOutputStream());
    	
    	new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());
			
				while (true) {
					String action =in.readUTF();
					switch (action) {
					case "login": 
						String success =in.readUTF();

						if(success.equals("loginsuccess")) {
							thongtinphong frame = new thongtinphong();
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
		setBounds(100, 100, 1175, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(298, 168, 88, 18);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(298, 41, 141, 21);
		contentPane.add(lblNewLabel);

		txtpass = new JPasswordField();
		txtpass.setBackground(new Color(128, 255, 255));
		txtpass.setBounds(298, 204, 453, 49);
		contentPane.add(txtpass);

		JButton btncreate = new JButton("Create an acount");
		btncreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				try {
				signin 	frame = new signin();
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		});
		btncreate.setForeground(Color.GREEN);
		btncreate.setBackground(new Color(255, 255, 255));
		btncreate.setBounds(399, 383, 255, 21);
		contentPane.add(btncreate);

		JButton btnlogin = new JButton("LOGIN");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "login";
				StringBuilder logininfo=new StringBuilder();
				logininfo.append(txtuser.getText()).append(",").append(txtpass.getText());
				try {

					out.writeUTF(message);
					out.writeUTF(logininfo+"");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
				
			}
		});
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnlogin.setBackground(new Color(0, 128, 64));
		btnlogin.setBounds(299, 303, 453, 49);
		contentPane.add(btnlogin);

		txtuser = new JTextField();
		txtuser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtuser.setColumns(10);
		txtuser.setBackground(new Color(128, 255, 255));
		txtuser.setBounds(299, 72, 453, 49);
		contentPane.add(txtuser);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(framedangnhap.class.getResource("/image/SUPER-DELUXE2 (1) (1).jpg")));
		lblNewLabel_2.setBounds(0, 0, 1161, 513);
		contentPane.add(lblNewLabel_2);
		a = txtuser.getText();
	}
}
