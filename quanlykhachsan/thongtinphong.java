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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class thongtinphong extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private static DefaultTableModel model;
	private JTable table;
	static int a = -1;
	private JTextField txthoten;
	private JTextField txtcccd;
	private JTextField txtsdt;
	private JTextField txtmail;
	private JTextField txtngaydat;
	private JTextField txtngaytra;
	private JTextField txtphong;
	private JTextField txtgia;
	Vector v;
	private int tien;
	private JLabel labelhienthi;
	private DataInputStream in;
	private DataOutputStream out;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		thongtinphong frame = new thongtinphong();

	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public thongtinphong() throws IOException {
		init();
		this.setTitle("QUẢN LÝ KHÁCH SẠN");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void init() throws IOException {

		Socket socket = new Socket("localhost", 98);

		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());

		new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());

				while (true) {
					String action = in.readUTF();
					switch (action) {
					case "tatcaphong":
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
					case "phongdadat":

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
					case "phongtrong":

						String phongtrong = in.readUTF();
						BufferedReader buff2 = new BufferedReader(new StringReader(phongtrong));

						String line2;
						while ((line2 = buff2.readLine()) != null) {
							if (line2.isEmpty()) {
								continue;
							}
							String[] com = line2.split(",");
							String id = com[0];
							String loaiphong = com[1];
							String tinhtrang = com[2];
							String gia = com[3];
							String lau = com[4];
							String phong = com[5];

							model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });
						}

						break;
					case "phongbaotri":

						String phongbaotri = in.readUTF();
						BufferedReader buff3 = new BufferedReader(new StringReader(phongbaotri));

						String line3;
						while ((line3 = buff3.readLine()) != null) {
							if (line3.isEmpty()) {
								continue;
							}
							String[] com = line3.split(",");
							String id = com[0];
							String loaiphong = com[1];
							String tinhtrang = com[2];
							String gia = com[3];
							String lau = com[4];
							String phong = com[5];

							model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });
						}

						break;
					case "tabledangdung":

						String tabledangdung = in.readUTF();
						BufferedReader buff4 = new BufferedReader(new StringReader(tabledangdung));

						String line4;
						while ((line4 = buff4.readLine()) != null) {
							if (line4.isEmpty()) {
								continue;
							}
							String[] com = line4.split(",");
							String hoten = com[0];
							String cccd = com[1];
							String mail = com[2];
							
							String gia = com[3];
							
							String sdt = com[4];
							String ngaytra = com[5];
							
							String ngaydat = com[6];
							String tien1 = com[7];
							

							txthoten.setText(hoten);
							txtcccd.setText(cccd);
							txtmail.setText(mail);
							txtphong.setText(v.firstElement().toString());
							txtsdt.setText(sdt);
							txtngaytra.setText(ngaytra);
							txtngaydat.setText(ngaydat);
							txtgia.setText(tien1);

						}

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
		panel.setBounds(0, 53, 226, 773);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("    Admin");
		lblNewLabel_2.setBounds(0, 0, 226, 53);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon(trangchu.class.getResource("/image/user.png")));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(225, 10, 1301, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("         THÔNG TIN PHÒNG");
		lblNewLabel.setBounds(0, 0, 402, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 104, 1301, 320);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

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
		btnNewButton_4.setIcon(new ImageIcon(trangchu.class.getResource("/image/shopping-cart.png")));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_4.setBackground(new Color(0, 64, 64));

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
		lblNewLabel_1.setBounds(55, 0, 171, 45);
		panel_3.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					framedangnhap frame;
					try {
						frame = new framedangnhap();
						frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						dispose();
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

		JButton btnNewButton_5 = new JButton("Phòng trống");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				model.setRowCount(0);

				String message = "phongtrong";
				try {

					out.writeUTF(message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}

			}
		});

		btnNewButton_5.setBounds(531, 53, 157, 41);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Tất Cả Phòng");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);

				String message = "tatcaphong";
				try {

					out.writeUTF(message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
			}
		});
		btnNewButton_6.setBounds(225, 53, 157, 41);
		contentPane.add(btnNewButton_6);

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		table.setSelectionBackground(Color.green);
		table.setSelectionForeground(Color.blue);
		table.addMouseListener(new MouseAdapter() {

		
			private String tinhtrang;

			@Override
			public void mousePressed(MouseEvent e) {

				int selectedrow = table.getSelectedRow();
				if (selectedrow >= 0) {
					labelhienthi.setText("Chưa Thanh Toán");
					v = model.getDataVector().get(selectedrow);
				}
				tinhtrang = (String) v.elementAt(2);
System.out.println(tinhtrang);
				if (tinhtrang.equals("Trống")) {
					labelhienthi.setText("");
					JOptionPane.showMessageDialog(null, "Phòng Trống.");
					txthoten.setText("");
					txtcccd.setText("");
					txtgia.setText("");
					txtsdt.setText("");
					txtmail.setText("");
					txtngaydat.setText("");
					txtngaytra.setText("");
					txtphong.setText("");
				} else if (tinhtrang.equals("Đang bảo trì")) {
					labelhienthi.setText("");
					JOptionPane.showMessageDialog(null, "Phòng đang bảo trì.");
					txthoten.setText("");
					txtcccd.setText("");
					txtgia.setText("");
					txtsdt.setText("");
					txtmail.setText("");
					txtngaydat.setText("");
					txtngaytra.setText("");
					txtphong.setText("");
				} 
				
				String message = "tabledangdung";
				String idwrite=v.firstElement().toString();
				try {

					out.writeUTF(message);
					out.writeUTF(idwrite);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}

				

			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Loại Phòng");
		model.addColumn("Tình Trạng");
		model.addColumn("Giá");
		model.addColumn("Phòng");
		model.addColumn("Lầu");

		table.setModel(model);

		JButton btnNewButton_7 = new JButton("Phòng đã đặt");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setRowCount(0);

				String message = "phongdadat";
				try {

					out.writeUTF(message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}

			}
		});
		btnNewButton_7.setBounds(381, 53, 151, 41);
		contentPane.add(btnNewButton_7);

		JButton btnNewButton_8 = new JButton("Phòng đang bảo trì");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
			

				String message = "phongbaotri";
				try {

					out.writeUTF(message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}

			}
		});
		btnNewButton_8.setBounds(687, 53, 141, 41);
		contentPane.add(btnNewButton_8);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(225, 434, 1301, 392);
		contentPane.add(panel_5);

		JLabel lblNewLabel_5 = new JLabel("Họ và Tên:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_5.setBounds(0, 10, 122, 36);
		panel_5.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 10, 1301, 2);
		panel_5.add(separator);

		txthoten = new JTextField();
		txthoten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txthoten.setColumns(10);
		txthoten.setBounds(101, 22, 313, 30);
		panel_5.add(txthoten);

		JLabel lblNewLabel_6 = new JLabel("CCCD:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_6.setBounds(0, 106, 95, 24);
		panel_5.add(lblNewLabel_6);

		txtcccd = new JTextField();
		txtcccd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcccd.setColumns(10);
		txtcccd.setBounds(101, 105, 313, 30);
		panel_5.add(txtcccd);

		JLabel lblNewLabel_7 = new JLabel("SĐT:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_7.setBounds(0, 193, 95, 24);
		panel_5.add(lblNewLabel_7);

		txtsdt = new JTextField();
		txtsdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtsdt.setColumns(10);
		txtsdt.setBounds(101, 192, 313, 30);
		panel_5.add(txtsdt);

		JLabel lblNewLabel_8 = new JLabel("Mail:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_8.setBounds(0, 275, 95, 24);
		panel_5.add(lblNewLabel_8);

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtmail.setColumns(10);
		txtmail.setBounds(101, 274, 313, 30);
		panel_5.add(txtmail);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 367, 1301, 2);
		panel_5.add(separator_1);

		JLabel lblNewLabel_9 = new JLabel("Ngày Đặt Phòng:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_9.setBounds(542, 23, 144, 24);
		panel_5.add(lblNewLabel_9);

		txtngaydat = new JTextField();
		txtngaydat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaydat.setColumns(10);
		txtngaydat.setBounds(762, 22, 300, 30);
		panel_5.add(txtngaydat);

		JLabel lblNewLabel_10 = new JLabel("Ngày Trả Phòng:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_10.setBounds(542, 90, 165, 24);
		panel_5.add(lblNewLabel_10);

		txtngaytra = new JTextField();
		txtngaytra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaytra.setColumns(10);
		txtngaytra.setBounds(762, 89, 300, 30);
		panel_5.add(txtngaytra);

		JLabel lblNewLabel_11 = new JLabel("Phòng Đang Dùng:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_11.setBounds(542, 151, 165, 24);
		panel_5.add(lblNewLabel_11);

		txtphong = new JTextField();
		txtphong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtphong.setColumns(10);
		txtphong.setBounds(762, 159, 300, 30);
		panel_5.add(txtphong);

		JButton btnNewButton_6_1 = new JButton("Reset");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txthoten.setText("");
				txtcccd.setText("");
				txtgia.setText("");
				txtsdt.setText("");
				txtmail.setText("");
				txtngaydat.setText("");
				txtngaytra.setText("");
				txtphong.setText("");
				model.setRowCount(0);
			}
		});
		btnNewButton_6_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_6_1.setBounds(1107, 316, 194, 53);
		panel_5.add(btnNewButton_6_1);

		JLabel lblNewLabel_12 = new JLabel("Giá Phòng(VND):");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_12.setBounds(542, 209, 178, 36);
		panel_5.add(lblNewLabel_12);

		txtgia = new JTextField();
		txtgia.setColumns(10);
		txtgia.setBounds(762, 220, 300, 30);
		panel_5.add(txtgia);

		labelhienthi = new JLabel("");
		labelhienthi.setHorizontalAlignment(SwingConstants.CENTER);
		labelhienthi.setForeground(Color.RED);
		labelhienthi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		labelhienthi.setBackground(Color.BLUE);
		labelhienthi.setBounds(762, 274, 300, 30);
		panel_5.add(labelhienthi);

		JLabel lblNewLabel_4 = new JLabel("Thông Tin Phòng");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_4.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/calendar.png")));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(0, 75, 226, 46);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_13 = new JLabel("Đặt Phòng");
		lblNewLabel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/shopping-cart.png")));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_13.setBounds(0, 122, 226, 46);
		panel.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Trả Phòng");
		lblNewLabel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					traphong frame = new traphong();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		lblNewLabel_14.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/check.png")));
		lblNewLabel_14.setForeground(new Color(255, 255, 255));
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_14.setBounds(0, 168, 226, 46);
		panel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Lịch Sử");
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lichsu1 frame = new lichsu1();
			}
		});
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_15.setForeground(new Color(255, 255, 255));
		lblNewLabel_15.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/time-past.png")));
		lblNewLabel_15.setBounds(0, 224, 226, 46);
		panel.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Cập Nhật");
		lblNewLabel_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chinhsua1 frame = new chinhsua1();
			}
		});
		lblNewLabel_16.setForeground(new Color(255, 255, 255));
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_16.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/refresh.png")));
		lblNewLabel_16.setBounds(0, 280, 226, 46);
		panel.add(lblNewLabel_16);
		
		JLabel lblNewLabel_16_1 = new JLabel("Chatroom");
		lblNewLabel_16_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblNewLabel_16_1.setIcon(new ImageIcon(thongtinphong.class.getResource("/image/comments-solid (1).png")));
		lblNewLabel_16_1.setForeground(Color.WHITE);
		lblNewLabel_16_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_16_1.setBounds(0, 328, 226, 46);
		panel.add(lblNewLabel_16_1);
	}
}
