package quanlykhachsan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class chinhsua extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	public JTable table;
	public  DefaultTableModel model;
	private JTextField txtid;
	private JTextField txtgia;
	private static int selectedrow = -1;
	public static Vector v;
	int a = 0;
	private JPanel panel;
	public static int g;
	public static String y;
 private DataOutputStream out;
 private DataInputStream in;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		chinhsua frame1 = new chinhsua();
		frame1.setTitle("QUẢN LÝ KHÁCH SẠN");
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame1.setLocationRelativeTo(null);
		frame1.setExtendedState(MAXIMIZED_BOTH);
		frame1.setResizable(false);
		frame1.setVisible(true);


	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public chinhsua() throws IOException {
	
	
		
Socket socket = new Socket("localhost", 98);
        
    	in = new DataInputStream(socket.getInputStream());
    	out = new DataOutputStream(socket.getOutputStream());
    	
    	new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());
			
				while (true) {
					String action =in.readUTF();
					switch (action) {
					case "searchroom": 
						String phongdadat = in.readUTF();
						System.out.println("client" + phongdadat);
						BufferedReader buff1 = new BufferedReader(new StringReader(phongdadat));

						String line1;
						while ((line1 = buff1.readLine()) != null) {
							if (line1.isEmpty()) {
								continue;
							}
							String[] com = line1.split(",");
							String id = com[0];
							String loaiphong = com[1];
							String tinhtrang = com[2];
							String gia = com[3];
							String lau = com[4];
							String phong = com[5];

							model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });
						}
					
				 
						break;
					case "tatcaphong":
						model.setRowCount(0);
						String tatcaphong = in.readUTF();

						BufferedReader buff = new BufferedReader(new StringReader(tatcaphong));

						String line;
						while ((line = buff.readLine()) != null) {
							if (line.isEmpty()) {
								continue;
							}
							String[] com = line.split(",");
							String id = com[0];
							String loaiphong = com[1];
							String tinhtrang = com[2];
							String gia = com[3];
							String lau = com[4];
							String phong = com[5];

							model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });
						}
						break;
					case "addroom":
						String a=in.readUTF();
						switch (a) {
						case "exist":
							txtid.setText("");
							txtgia.setText("");
							JOptionPane.showMessageDialog(null, "Phòng đã tồn tại.");
							break;
							case "success":
								txtid.setText("");
								txtgia.setText("");
								JOptionPane.showMessageDialog(null, "Thêm thành công.");
								out.writeUTF("tatcaphong");
								break;
						}
						break;
					case "deleteroom":	
						
						JOptionPane.showMessageDialog(null, "Xoá phòng thành công.");
					out.writeUTF("tatcaphong");
						break;
					}
					
					
			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1540, 863);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(0, 64, 64));
		panel.setBounds(0, 53, 226, 784);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("    Admin");
		lblNewLabel_2.setBounds(0, 0, 226, 53);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon(trangchu.class.getResource("/image/user.png")));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel_2);

		JButton btnNewButton_1 = new JButton("Thông tin phòng");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					thongtinphong frame = new thongtinphong();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(trangchu.class.getResource("/image/calendar.png")));
		btnNewButton_1.setBounds(0, 76, 226, 36);
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBackground(new Color(0, 64, 64));
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Đặt Phòng");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					datphong frame = new datphong();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_2.setBounds(0, 112, 226, 36);
		btnNewButton_2.setIcon(new ImageIcon(trangchu.class.getResource("/image/shopping-cart.png")));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_2.setBackground(new Color(0, 64, 64));
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(225, 10, 1301, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("   CHỈNH SỬA PHÒNG");
		lblNewLabel.setBounds(0, 0, 280, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 53, 1301, 351);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionBackground(Color.green);
		table.setSelectionForeground(Color.blue);
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Loại Phòng");
		model.addColumn("Tình Trạng");
		model.addColumn("Giá");
		model.addColumn("Phòng");
		model.addColumn("Lầu");

		model.setRowCount(0);

		Connection c = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:MySQl://localhost:3306/qlks";
			String username = "root";
			String password = "";
			c = DriverManager.getConnection(url, username, password);

			Statement stmt = c.createStatement();

			String sql = "select * from qlks.phong";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String loaiphong = rs.getString("loaiphong");
				String tinhtrang = rs.getString("tinhtrang");
				int gia = rs.getInt("Gia");
				String lau = rs.getString("phong");
				int phong = rs.getInt("lau");

				model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setModel(model);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 255));
		panel_3.setBounds(0, 10, 226, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JButton btnNewButton_4 = new JButton("Đặt Phòng");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(0, 112, 226, 36);
		panel.add(btnNewButton_2);
		btnNewButton_4.setIcon(new ImageIcon(trangchu.class.getResource("/image/shopping-cart.png")));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_4.setBackground(new Color(0, 64, 64));

		JButton btnNewButton_3 = new JButton("Trả Phòng");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					traphong frame = new traphong();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(new ImageIcon(datphong.class.getResource("/image/check.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 64, 64));
		btnNewButton_3.setBounds(0, 145, 226, 36);
		panel.add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel("Online");
		lblNewLabel_3.setIcon(new ImageIcon(trangchu.class
				.getResource("/image/pngtree-green-round-button-icon-cartoon-style-png-image_1897027-_1_ (1).png")));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(29, 53, 197, 23);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_1 = new JLabel("HHOTEL");
		lblNewLabel_1.setBackground(new Color(0, 0, 160));
		lblNewLabel_1.setIcon(new ImageIcon(datphong.class.getResource("/image/hotel.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblNewLabel_1.setBounds(52, 0, 174, 45);
		panel_3.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to log out?","Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					 
					try {
					 framedangnhap	frame = new framedangnhap();
					 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					 frame.setLocationRelativeTo(null);
					 frame.setVisible(true);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
						dispose();
				}

			}
		});
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setIcon(new ImageIcon(trangchu.class.getResource("/image/logout.png")));
		btnNewButton.setBounds(0, 0, 53, 45);
		panel_3.add(btnNewButton);

		JButton btnNewButton_6 = new JButton("Lịch Sử ");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lichsu1 frame = new lichsu1();
			}
		});
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setIcon(new ImageIcon(lichsu.class.getResource("/image/time-past.png")));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setBackground(new Color(0, 64, 64));
		btnNewButton_6.setBounds(0, 180, 226, 36);
		panel.add(btnNewButton_6);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(225, 426, 1301, 336);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_4.setBounds(10, 10, 57, 32);
		panel_4.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Loại Phòng:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_5.setBounds(10, 147, 114, 25);
		panel_4.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Tình Trạng:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_6.setBounds(10, 304, 124, 24);
		panel_4.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Giá(VND):");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_7.setBounds(580, 10, 124, 32);
		panel_4.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Lầu:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_8.setBounds(580, 138, 124, 32);
		panel_4.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Phòng:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_9.setBounds(580, 300, 124, 32);
		panel_4.add(lblNewLabel_9);

		txtid = new JTextField();
		txtid.setBounds(146, 10, 189, 30);
		panel_4.add(txtid);
		txtid.setColumns(10);

		txtgia = new JTextField();
		txtgia.setBounds(704, 10, 234, 30);
		panel_4.add(txtgia);
		txtgia.setColumns(10);

		JComboBox cbxloaiphong = new JComboBox();
		cbxloaiphong.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxloaiphong.setModel(new DefaultComboBoxModel(new String[] { "VIP", "Normal" }));
		cbxloaiphong.setBounds(146, 138, 114, 30);
		panel_4.add(cbxloaiphong);

		JComboBox cbxtinhtrang = new JComboBox();
		cbxtinhtrang.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxtinhtrang.setModel(new DefaultComboBoxModel(new String[] { "Đang Dùng", "Đang bảo trì", "Trống" }));
		cbxtinhtrang.setBounds(144, 301, 137, 32);
		panel_4.add(cbxtinhtrang);

		JComboBox cbxlau = new JComboBox();
		cbxlau.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxlau.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		cbxlau.setBounds(704, 138, 73, 29);
		panel_4.add(cbxlau);

		JComboBox cbxphong = new JComboBox();
		cbxphong.setFont(new Font("Tahoma", Font.BOLD, 17));
		cbxphong.setModel(new DefaultComboBoxModel(new String[] { "Đôi", "Đơn" }));
		cbxphong.setBounds(704, 301, 73, 32);
		panel_4.add(cbxphong);

		JSeparator separator = new JSeparator();
		separator.setBounds(225, 414, 1301, 2);
		contentPane.add(separator);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(225, 775, 1301, 41);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JButton btnNewButton_5 = new JButton("Xoá Phòng");
		btnNewButton_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				selectedrow = table.getSelectedRow();
				if (selectedrow < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn phòng muốn xoá.");
				} else {

					if (JOptionPane.showConfirmDialog(null, "Xác nhận xoá phòng", "Confirm",
							JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {

					} else {
						v = model.getDataVector().get(selectedrow);
						String y = v.firstElement().toString();
					
						try {
							out.writeUTF("edit");	
							out.writeUTF("deleteroom");
							out.writeUTF(y);
						
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						

					}

				}

			}
		});
		JButton btnNewButton_8 = new JButton("Cập Nhật");

		btnNewButton_8.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8.setIcon(new ImageIcon(traphong.class.getResource("/image/refresh.png")));
		btnNewButton_8.setBackground(new Color(0, 64, 64));
		btnNewButton_8.setForeground(new Color(255, 255, 255));
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_8.setBounds(0, 216, 226, 36);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_8_1 = new JButton("ChatRoom");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chatroom frame = new chatroom();
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
		btnNewButton_8_1.setIcon(new ImageIcon(chinhsua.class.getResource("/image/comments-solid (1).png")));
		btnNewButton_8_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8_1.setForeground(Color.WHITE);
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_8_1.setBackground(new Color(0, 64, 64));
		btnNewButton_8_1.setBounds(0, 253, 226, 36);
		panel.add(btnNewButton_8_1);

		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_5.setBounds(1140, 10, 151, 30);
		panel_5.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("Thêm Phòng");
		btnNewButton_7.setBounds(976, 10, 156, 30);
		panel_5.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
			private String id;
			private int id1;
			private int id2;

			public void actionPerformed(ActionEvent e) {
				StringBuilder emty = new StringBuilder();
				if (txtid.getText().equals("")) {
					emty.append("Hãy điền id.");

				}
				if (txtgia.getText().equals("")) {
					emty.append("\nHãy điền giá phòng.");
				}
				if (!emty.isEmpty()) {
					JOptionPane.showMessageDialog(null, emty);
				} else {

					try {
						out.writeUTF("edit");	
						out.writeUTF("addroom");
						out.writeUTF(txtid.getText());
						out.writeUTF(cbxloaiphong.getSelectedItem().toString());
						out.writeUTF(cbxtinhtrang.getSelectedItem().toString());
						out.writeUTF(cbxlau.getSelectedItem().toString());
						out.writeUTF(cbxphong.getSelectedItem().toString());
						out.writeUTF(txtgia.getText());
					
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}

			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		
		
		


		JButton btnNewButton_9 = new JButton("Chỉnh Sửa");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedrow = table.getSelectedRow();

				if (selectedrow < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn phòng muốn chỉnh sửa.");
				} else {
					try {
						v = model.getDataVector().get(selectedrow);
						y = String.valueOf(v.firstElement());
						g = Integer.parseInt(y);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					try {
						chinhsua2	frame = new chinhsua2();
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		

				}

				

			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_9.setBounds(815, 10, 151, 30);
		panel_5.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Reset");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				try {
					out.writeUTF("tatcaphong");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_10.setBounds(662, 10, 146, 31);
		panel_5.add(btnNewButton_10);
		
		JButton btnNewButton_12 = new JButton("Tìm Kiếm");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				if(txtid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hãy điền ID phòng muốn tìm kiếm.");
				}else {
					model.setRowCount(0);

					String id1 =txtid.getText();
					try {
						out.writeUTF("edit");	
						out.writeUTF("searchroom");
						out.writeUTF(id1);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
					
				}
				
				
				
			}
		});
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_12.setBounds(506, 10, 146, 30);
		panel_5.add(btnNewButton_12);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(225, 772, 1301, 5);
		contentPane.add(separator_1);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(225, 816, 1301, 21);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 10, 1301, 2);
		panel_6.add(separator_2);

	}


}
