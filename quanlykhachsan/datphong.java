package quanlykhachsan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Label;

public class datphong extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;

	private JTable table;
	private DefaultTableModel model;

	private Vector v;

	private int selectedrow = -1;

	int max;
    private DataInputStream in;
    private DataOutputStream out;
	private JTextField txthoten;
	private JTextField txtcccd;
	private JTextField txtsdt;
	private JTextField txtmail;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {

		datphong frame = new datphong();

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public datphong() throws UnknownHostException, IOException {
		init();
		this.setTitle("QUẢN LÝ KHÁCH SẠN");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
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
					case "phongtrong": 
						String phongtrong = in.readUTF();

						BufferedReader buff = new BufferedReader(new StringReader(phongtrong));

						String line;
						while ((line = buff.readLine()) != null) {
							if (line.isEmpty()) {
								continue;
							}
							String[] com = line.split(",");
							String id = com[0];
							String loaiphong1 = com[1];
							String tinhtrang = com[2];
							String gia = com[3];
							String giuong1 = com[4];
						


							model.addRow(new Object[] { id, loaiphong1, tinhtrang, gia, giuong1 });
						}
					
				 
						break;
					case "datphongthanhcong": 
						System.out.println("responsedatphong");
						JOptionPane.showMessageDialog(null, "Đặt phòng thành công!");
						txthoten.setText("");
						txtcccd.setText("");
						txtmail.setText("");
						txtsdt.setText("");
						model.setRowCount(0);
						selectedrow = -1;
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
		setBounds(100, 100, 1540, 863);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 64));
		panel.setBounds(0, 53, 226, 784);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("    Admin");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon(trangchu.class.getResource("/image/user.png")));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(0, 0, 226, 66);
		panel.add(lblNewLabel_2);

		JButton btnNewButton_1 = new JButton("Thông tin phòng");
		btnNewButton_1.setIcon(new ImageIcon(datphong.class.getResource("/image/calendar.png")));
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
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBackground(new Color(0, 64, 64));
		btnNewButton_1.setBounds(0, 76, 226, 36);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Đặt Phòng");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_2.setBounds(0, 112, 226, 36);
		panel.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(trangchu.class.getResource("/image/shopping-cart.png")));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_2.setBackground(new Color(0, 64, 64));

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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(225, 10, 1301, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("   ĐẶT PHÒNG");
		lblNewLabel.setBounds(0, 0, 280, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 53, 1301, 450);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Họ Và Tên:");
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_5.setBounds(10, 22, 147, 33);
		panel_2.add(lblNewLabel_5);

		 txthoten = new JTextField();
		txthoten.setBounds(209, 29, 396, 28);
		panel_2.add(txthoten);
		txthoten.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("CCCD:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_6.setBounds(10, 140, 147, 33);
		panel_2.add(lblNewLabel_6);

		 txtcccd = new JTextField();
		txtcccd.setBounds(209, 140, 396, 33);
		panel_2.add(txtcccd);
		txtcccd.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("SDT:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_7.setBounds(10, 206, 147, 33);
		panel_2.add(lblNewLabel_7);

		 txtsdt = new JTextField();
		txtsdt.setBounds(209, 206, 396, 33);
		panel_2.add(txtsdt);
		txtsdt.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Loại Phòng:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_9.setBounds(10, 281, 120, 33);
		panel_2.add(lblNewLabel_9);

		JComboBox cbxphong = new JComboBox();
		cbxphong.setFont(new Font("Tahoma", Font.BOLD, 19));
		cbxphong.setModel(new DefaultComboBoxModel(new String[] { "VIP", "Normal" }));
		cbxphong.setBounds(140, 281, 120, 31);
		panel_2.add(cbxphong);

		JLabel lblNewLabel_10 = new JLabel("Ngày Đặt Phòng:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_10.setBounds(10, 364, 195, 48);
		panel_2.add(lblNewLabel_10);

		JLabel lblNewLabel_14 = new JLabel("Ngày Trả Phòng:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_14.setBounds(439, 366, 177, 48);
		panel_2.add(lblNewLabel_14);

		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_4.setBounds(10, 76, 147, 33);
		panel_2.add(lblNewLabel_4);

		 txtmail = new JTextField();
		txtmail.setBounds(209, 76, 396, 33);
		panel_2.add(txtmail);
		txtmail.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Phòng:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_8.setBounds(314, 281, 96, 33);
		panel_2.add(lblNewLabel_8);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 19));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Đôi", "Đơn" }));
		comboBox.setBounds(468, 281, 75, 28);
		panel_2.add(comboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(188, 377, 161, 35);
		panel_2.add(dateChooser);

		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(626, 379, 161, 33);
		panel_2.add(dateChooser1);

		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setIcon(new ImageIcon(
				datphong.class.getResource("/image/321479839_686311763229248_3620550856691554887_n (1).jpg")));
		lblNewLabel_11.setBounds(0, 0, 1301, 450);
		panel_2.add(lblNewLabel_11);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 255));
		panel_3.setBounds(0, 10, 226, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("HHOTEL");
		lblNewLabel_1.setIcon(new ImageIcon(datphong.class.getResource("/image/hotel.png")));
		lblNewLabel_1.setBackground(new Color(0, 0, 160));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblNewLabel_1.setBounds(55, 0, 171, 45);
		panel_3.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					try {
						framedangnhap frame = new framedangnhap();
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
			}
		});
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setIcon(new ImageIcon(trangchu.class.getResource("/image/logout.png")));
		btnNewButton.setBounds(0, 0, 53, 45);
		panel_3.add(btnNewButton);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(225, 538, 1301, 238);
		contentPane.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionBackground(Color.green);
		table.setSelectionForeground(Color.blue);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				selectedrow = table.getSelectedRow();
				if (selectedrow >= 0) {

					v = model.getDataVector().get(selectedrow);

				}
			}
		});

		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Loại Phòng");
		model.addColumn("Tình Trạng");
		model.addColumn("Giá");
		model.addColumn("Phòng");
		table.setModel(model);

		JButton btnNewButton_4 = new JButton("Hiển Thị Phòng Trống");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loaiphong = cbxphong.getSelectedItem().toString();
				String giuong = comboBox.getSelectedItem().toString();

				model.setRowCount(0);
   
				try {
					out.writeUTF("datphong");
					out.writeUTF("phongtrong");
					out.writeUTF(loaiphong);
					out.writeUTF(giuong);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnNewButton_4.setBounds(225, 503, 180, 35);
		contentPane.add(btnNewButton_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(225, 775, 1301, 51);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JButton btnNewButton_5 = new JButton("Đặt Phòng");
		btnNewButton_5.addActionListener(new ActionListener() {
			private String loaiphong;
			private int gia;
			private int lau;
			private String phong;
			private java.util.Date today;
			private java.util.Date today1;
			private String todayAsString;
			private String todayAsString1;

			public void actionPerformed(ActionEvent e) {

				StringBuilder emty = new StringBuilder();
				if (txtmail.getText().equals("")) {
					emty.append("Hãy nhập email.	");
				}

				if (txthoten.getText().equals("")) {
					emty.append("\nHãy Nhập Họ và tên.");
				}
				if (txtcccd.getText().equals("")) {
					emty.append("\nHãy nhập CCCD.");
				}
				if (txtsdt.getText().equals("")) {
					emty.append("\nHãy nhập số điện thoại. ");
				}
				if (selectedrow < 0) {
					emty.append("\nHãy chọn phòng.");
				}

				try {

					String pattern = "yyyy-MM-dd HH:mm:ss";
//					  HH:mm:ss

					DateFormat df = new SimpleDateFormat(pattern);

					today = dateChooser.getDate();

					todayAsString = df.format(today);

					String pattern1 = "yyyy-MM-dd HH:mm:ss";
//					  HH:mm:ss

					DateFormat df1 = new SimpleDateFormat(pattern1);

					today1 = dateChooser1.getDate();

					todayAsString1 = df1.format(today1);

				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (today == null) {
					emty.append("\nHãy chọn ngày đặt phòng.");
				}
				if (today1 == null) {
					emty.append("\nHãy chọn ngày trả phòng.");
				}

				if (!emty.isEmpty()) {
					JOptionPane.showMessageDialog(null, emty);
				} else {

					if (txtcccd.getText().length() != 12) {
						JOptionPane.showMessageDialog(null, "Số CCCD không hợp lệ.");

					} else if (txtsdt.getText().length() != 10) {

						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.");
					} else {

                        String fisrtelement=v.firstElement().toString();
						try {
							out.writeUTF("datphong");
							out.writeUTF("buttondatphong");
							out.writeUTF(txthoten.getText());
							out.writeUTF(txtcccd.getText());
							out.writeUTF(txtsdt.getText());
							out.writeUTF(todayAsString);
							out.writeUTF(todayAsString1);
							out.writeUTF(fisrtelement);
							out.writeUTF(txtmail.getText());
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						

					}

				}

			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_5.setBounds(512, 10, 212, 31);
		panel_5.add(btnNewButton_5);

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

		JButton btnNewButton_8 = new JButton("Cập Nhật");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chinhsua1 frame = new chinhsua1();

			}
		});
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
		btnNewButton_8_1.setIcon(new ImageIcon(datphong.class.getResource("/image/comments-solid (1).png")));
		btnNewButton_8_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8_1.setForeground(Color.WHITE);
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_8_1.setBackground(new Color(0, 64, 64));
		btnNewButton_8_1.setBounds(0, 253, 226, 36);
		panel.add(btnNewButton_8_1);
	}
}
