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
import java.awt.Component;
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
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextField;

public class traphong extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	public JTable table;
	private DefaultTableModel model;
	private JTextField txthoten;
	private JTextField txtcccd;
	private JTextField txtsdt;
	private JTextField txtmail;
	private JTextField txtngaydat;
	private JTextField txtngaytra;
	private JTextField txtphong;
	private Vector v;
	private JTextField txtthanhtien;
	public JLabel labelhienthi;
	private JTextField txtgiaphong1;
	public JTextField txtsongay;
	private JTextField txtthanhtien1;
	private JTextField txtthue;
	private String tien1;
	private int tien;
	private JTextField txttongtien;
	private int selectedrow = -1;
	private String hoten;
	private DataOutputStream out;
	private DataInputStream in;

	public static void main(String[] args) throws UnknownHostException, IOException {
		traphong frame = new traphong();
	}

	public traphong() throws UnknownHostException, IOException {
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
out.writeUTF("phongdadat");
		new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());

				while (true) {
					String action = in.readUTF();
					switch (action) {
					case "phongdadat":

						String phongdadat = in.readUTF();
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
					case "thanhtoan":
					    
						JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công.");
						
						break;
					case "tablethanhtoan":
						String table = in.readUTF();
						BufferedReader buff2 = new BufferedReader(new StringReader(table));

						String line2;
						while ((line2 = buff2.readLine()) != null) {
							if (line2.isEmpty()) {
								continue;
							}
							String[] com = line2.split(",");
							String tien2 = com[0];
							String noDay1 = com[1];
							String thanhtien1 = com[2];
							String thue = com[3];
							String tongtien = com[4];
							
							String hoten = com[5];
							String cccd = com[6];
							String mail = com[7];
							String firtelement = com[8];
							String ngaydat = com[9];
							String ngaytra = com[10];
							String sdt = com[11];
							
							

							txtthanhtien.setText(tien2);
							txtgiaphong1.setText(tien2);
							txtsongay.setText(noDay1);
							txtthanhtien1.setText(thanhtien1);
							txtthue.setText(thue);
							txttongtien.setText(tongtien);
							txthoten.setText(hoten);
							
							txtcccd.setText(cccd);
							txtmail.setText(mail);
							txtphong.setText(v.firstElement().toString());
							txtsdt.setText(sdt);
							txtngaytra.setText(ngaytra);
							txtngaydat.setText(ngaydat);
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

		lblNewLabel = new JLabel("         TRẢ PHÒNG");
		lblNewLabel.setBounds(0, 0, 280, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 53, 1301, 45);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Các Phòng Đang Sử Dụng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_4.setBounds(0, 0, 291, 45);
		panel_2.add(lblNewLabel_4);

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

				if (JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					try {
						framedangnhap frame = new framedangnhap();
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

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(225, 97, 1301, 212);
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

				labelhienthi.setText("Chưa Thanh Toán");

				selectedrow = table.getSelectedRow();
				if (selectedrow >= 0) {

					v = model.getDataVector().get(selectedrow);
				}
				
				try {
					out.writeUTF("tablethanhtoan");
					out.writeUTF(v.firstElement().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				

			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Loại Phòng");
		model.addColumn("Tình Trạng");
		model.addColumn("Giá");
		model.addColumn("Phòng");
		model.addColumn("Lầu");
		table.setModel(model);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(225, 319, 1301, 379);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Họ và Tên:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_5.setBounds(0, 10, 122, 36);
		panel_5.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 10, 1301, 2);
		panel_5.add(separator);

		txthoten = new JTextField();
		txthoten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txthoten.setBounds(101, 22, 313, 30);
		panel_5.add(txthoten);
		txthoten.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("CCCD:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_6.setBounds(0, 106, 95, 24);
		panel_5.add(lblNewLabel_6);

		txtcccd = new JTextField();
		txtcccd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcccd.setBounds(101, 105, 313, 30);
		panel_5.add(txtcccd);
		txtcccd.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("SĐT:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_7.setBounds(0, 193, 95, 24);
		panel_5.add(lblNewLabel_7);

		txtsdt = new JTextField();
		txtsdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtsdt.setBounds(101, 192, 313, 30);
		panel_5.add(txtsdt);
		txtsdt.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Mail:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_8.setBounds(0, 275, 95, 24);
		panel_5.add(lblNewLabel_8);

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtmail.setBounds(101, 274, 313, 30);
		panel_5.add(txtmail);
		txtmail.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 367, 1301, 2);
		panel_5.add(separator_1);

		JLabel lblNewLabel_9 = new JLabel("Ngày Đặt Phòng:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_9.setBounds(542, 23, 144, 24);
		panel_5.add(lblNewLabel_9);

		txtngaydat = new JTextField();
		txtngaydat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaydat.setBounds(762, 22, 300, 30);
		panel_5.add(txtngaydat);
		txtngaydat.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Ngày Trả Phòng:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_10.setBounds(542, 90, 165, 24);
		panel_5.add(lblNewLabel_10);

		txtngaytra = new JTextField();
		txtngaytra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaytra.setBounds(762, 89, 300, 30);
		panel_5.add(txtngaytra);
		txtngaytra.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Phòng Đang Dùng:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_11.setBounds(542, 151, 165, 24);
		panel_5.add(lblNewLabel_11);

		txtphong = new JTextField();
		txtphong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtphong.setBounds(762, 159, 300, 30);
		panel_5.add(txtphong);
		txtphong.setColumns(10);

		JButton btnNewButton_6 = new JButton("Show");
		btnNewButton_6.setBounds(1107, 316, 194, 53);
		panel_5.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				labelhienthi.setText("");
				txtgiaphong1.setText("");
				txttongtien.setText("");
				txtsongay.setText("");
				txtthue.setText("");
				txtthanhtien1.setText("");
				
				try {
					out.writeUTF("phongdadat");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				txtcccd.setText("");
				txthoten.setText("");
				txtmail.setText("");
				txtngaydat.setText("");
				txtngaytra.setText("");
				txtphong.setText("");
				txtsdt.setText("");
				txtthanhtien.setText("");

			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 19));

		JLabel lblNewLabel_12 = new JLabel("Giá Phòng(VND):");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_12.setBounds(542, 209, 178, 36);
		panel_5.add(lblNewLabel_12);

		txtthanhtien = new JTextField();
		txtthanhtien.setBounds(762, 220, 300, 30);
		panel_5.add(txtthanhtien);
		txtthanhtien.setColumns(10);

		labelhienthi = new JLabel("");
		labelhienthi.setForeground(new Color(255, 0, 0));

		labelhienthi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		labelhienthi.setHorizontalAlignment(SwingConstants.CENTER);
		labelhienthi.setBackground(new Color(0, 0, 255));
		labelhienthi.setBounds(762, 274, 300, 30);
		panel_5.add(labelhienthi);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(225, 700, 1301, 126);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("Giá Phòng(VND):");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_13.setBounds(0, 10, 165, 30);
		panel_6.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Số Ngày :");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_14.setBounds(0, 50, 126, 30);
		panel_6.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Thành Tiền(VND):");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_15.setBounds(0, 95, 165, 26);
		panel_6.add(lblNewLabel_15);

		txtgiaphong1 = new JTextField();
		txtgiaphong1.setBounds(155, 19, 275, 30);
		panel_6.add(txtgiaphong1);
		txtgiaphong1.setColumns(10);

		txtsongay = new JTextField();
		txtsongay.setBounds(155, 57, 275, 30);
		panel_6.add(txtsongay);
		txtsongay.setColumns(10);

		txtthanhtien1 = new JTextField();
		txtthanhtien1.setBounds(154, 97, 276, 29);
		panel_6.add(txtthanhtien1);
		txtthanhtien1.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Thuế VAT(10%):");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_16.setBounds(496, 12, 154, 27);
		panel_6.add(lblNewLabel_16);

		txtthue = new JTextField();
		txtthue.setBounds(650, 19, 275, 30);
		panel_6.add(txtthue);
		txtthue.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Tổng Tiền(VND):");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_17.setBounds(496, 75, 154, 26);
		panel_6.add(lblNewLabel_17);

		txttongtien = new JTextField();
		txttongtien.setBounds(651, 75, 274, 26);
		panel_6.add(txttongtien);
		txttongtien.setColumns(10);

		JButton btnNewButton_5 = new JButton("Thanh Toán");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (selectedrow < 0) {
					if (JOptionPane.showConfirmDialog(null, "Hãy chọn phòng muốn thanh toán.", "Confirmation",
							JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					}
				}
				String p = "Xác nhận thanh toán cho khách hàng " + hoten;
				if (selectedrow >= 0) {
					if (JOptionPane.showConfirmDialog(null, p, "Confirmation",
							JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {

					} else {
						if (JOptionPane.showConfirmDialog(null, "Nhân viên xác nhận khách hàng đã thanh toán.",
								"Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

							model.setRowCount(0);
							labelhienthi.setText("");
						
							try {
								out.writeUTF("thanhtoan");
								out.writeUTF(v.firstElement().toString());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							
							
							txtcccd.setText("");
							txthoten.setText("");
							txtmail.setText("");
							txtngaydat.setText("");
							txtngaytra.setText("");
							txtphong.setText("");
							txtsdt.setText("");
							txtthanhtien.setText("");

							txtgiaphong1.setText("");
							txtsongay.setText("");
							txtthue.setText("");
							txttongtien.setText("");
							txtthanhtien1.setText("");

							model.setRowCount(0);

						try {
							out.writeUTF("phongdadat");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					}
				}

			}
		});
		btnNewButton_5.setIcon(new ImageIcon(traphong.class.getResource("/image/credit-card.png")));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		btnNewButton_5.setBounds(1077, 76, 214, 45);
		panel_6.add(btnNewButton_5);
		model.setRowCount(0);
//		Connection c = null;
//
//		try {
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			String url = "jdbc:MySQl://localhost:3306/qlks";
//			String username = "root";
//			String password = "";
//			c = DriverManager.getConnection(url, username, password);
//
//			Statement stmt = c.createStatement();
//			Statement stmt2 = c.createStatement();
//
//			String sql = "select * from qlks.phong where tinhtrang='Đang dùng';";
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String loaiphong = rs.getString("loaiphong");
//				String tinhtrang = rs.getString("tinhtrang");
//				int gia = rs.getInt("Gia");
//				String lau = rs.getString("phong");
//				int phong = rs.getInt("lau");
//
//				model.addRow(new Object[] { id, loaiphong, tinhtrang, gia, lau, phong });
//
//			}
//
//			rs.close();
//			stmt.close();
//			c.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		JButton btnNewButton_7 = new JButton("Lịch Sử ");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lichsu1 frame = new lichsu1();
			}
		});
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_7.setIcon(new ImageIcon(lichsu.class.getResource("/image/time-past.png")));
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_7.setForeground(new Color(255, 255, 255));
		btnNewButton_7.setBackground(new Color(0, 64, 64));
		btnNewButton_7.setBounds(0, 180, 226, 36);
		panel.add(btnNewButton_7);

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
		btnNewButton_8_1.setIcon(new ImageIcon(traphong.class.getResource("/image/comments-solid (1).png")));
		btnNewButton_8_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8_1.setForeground(Color.WHITE);
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_8_1.setBackground(new Color(0, 64, 64));
		btnNewButton_8_1.setBounds(0, 254, 226, 36);
		panel.add(btnNewButton_8_1);

	}
}
