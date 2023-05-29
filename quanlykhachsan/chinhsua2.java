package quanlykhachsan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
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
import javax.swing.border.BevelBorder;

public class chinhsua2 extends JFrame {

	private JPanel contentPane;
	private final JLabel label1 = new JLabel("New label");
	public JTextField txtgia;
	private JComboBox cbxtinhtrang;
	private JComboBox cbxloaiphong;
	private JComboBox cbxlau;
	private JComboBox cbxphong;
	private static int gia;
	private  static int lau;
	

	public static int a2;
	public static int a1;
	chinhsua a;
	private DataOutputStream out;
	private DataInputStream in;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
					chinhsua2 frame = new chinhsua2();
				
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public chinhsua2() throws  IOException {
		init();
		this.setTitle("Update");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
					case "editroom": 
						JOptionPane.showMessageDialog(null, "Cập nhật thành công phòng "+a.g+".\nChọn Reset để cập nhật bảng.");
						
						dispose();txtgia.setText("");
						
						
						break;
					case "phong1trong": 
						break;
					case "phongt2rong": 
						break;
					}
					out.writeUTF("tatcaphong");
					
					
			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 614, 360);
		contentPane.add(panel);
		panel.setLayout(null);
		label1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label1.setBounds(10, 10, 149, 39);
		panel.add(label1);
		
	
		try {
			a = new chinhsua();
			label1.setText("ID:"+a.g);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
	
		
		
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
		


			public void actionPerformed(ActionEvent e) {
				
			
				
				if(txtgia.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Hãy nhập giá phòng.");	
				}else {
				
					try {
						 gia=Integer.parseInt(txtgia.getText());
                lau=Integer.parseInt(cbxlau.getSelectedItem().toString());
			
				} catch (Exception e2) {
					// TODO: handle exception
				}
					
					try {
						out.writeUTF("edit");
						out.writeUTF("editroom");
						out.writeUTF(a.g+"");
						out.writeUTF(cbxloaiphong.getSelectedItem().toString());
						out.writeUTF(cbxtinhtrang.getSelectedItem().toString());
						out.writeUTF(txtgia.getText());
						out.writeUTF(cbxlau.getSelectedItem().toString());
						out.writeUTF(cbxphong.getSelectedItem().toString());
				
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
				
				
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(482, 317, 122, 31);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtgia.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(352, 317, 122, 31);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Giá(VND):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(273, 12, 95, 34);
		panel.add(lblNewLabel_1);
		
		txtgia = new JTextField();
		txtgia.setBounds(378, 10, 226, 34);
		panel.add(txtgia);
		txtgia.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Loại Phòng:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 114, 106, 31);
		panel.add(lblNewLabel_2);
		
		 cbxloaiphong = new JComboBox();
		cbxloaiphong.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxloaiphong.setModel(new DefaultComboBoxModel(new String[] {"VIP", "Normal"}));
		cbxloaiphong.setBounds(126, 122, 74, 23);
		panel.add(cbxloaiphong);
		
		JLabel lblNewLabel_3 = new JLabel("Tình Trạng:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(273, 120, 95, 25);
		panel.add(lblNewLabel_3);
		
		 cbxtinhtrang = new JComboBox();
		cbxtinhtrang.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxtinhtrang.setModel(new DefaultComboBoxModel(new String[] {"Đang Dùng", "Trống", "Đang bảo trì"}));
		cbxtinhtrang.setBounds(378, 124, 122, 23);
		panel.add(cbxtinhtrang);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 305, 594, 2);
		panel.add(separator);
		
		JLabel lblNewLabel_4 = new JLabel("Lầu:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(10, 244, 74, 23);
		panel.add(lblNewLabel_4);
		
		 cbxlau = new JComboBox();
		cbxlau.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		cbxlau.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxlau.setBounds(126, 244, 58, 25);
		panel.add(cbxlau);
		
		JLabel lblNewLabel_5 = new JLabel("Phòng:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_5.setBounds(273, 244, 74, 21);
		panel.add(lblNewLabel_5);
		
		 cbxphong = new JComboBox();
		cbxphong.setModel(new DefaultComboBoxModel(new String[] {"Đôi", "Đơn"}));
		cbxphong.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxphong.setBounds(378, 244, 95, 23);
		panel.add(cbxphong);
	}
}
