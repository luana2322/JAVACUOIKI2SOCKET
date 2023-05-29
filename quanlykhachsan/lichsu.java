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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class lichsu extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTable table;
	DefaultTableModel model;
	
	   private DataInputStream in;
	    private DataOutputStream out;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {

		lichsu frame = new lichsu();

	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public lichsu() throws UnknownHostException, IOException {
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
							
							if(action.equals("history")) {
								String history = in.readUTF();

								BufferedReader buff = new BufferedReader(new StringReader(history));

								String line;
								while ((line = buff.readLine()) != null) {
									if (line.isEmpty()) {
										continue;
									}
									String[] com = line.split(",");
									String hoten = com[0];
									String cccd = com[1];
									String sdt = com[2];
									String ngaydat = com[3];
									String ngaytra = com[4];
									String id = com[5];
									String mail = com[6];
									String loaiphong = com[7];
									String tinhtrang = com[8];
									String gia = com[9];
									String lau = com[10];
									String phong = com[11];

									model.addRow(new Object[]{hoten,cccd,sdt,ngaydat,ngaytra,id,mail,loaiphong,tinhtrang,gia,lau,phong });
									
								}
								
								
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

		lblNewLabel = new JLabel("     LỊCH SỬ");
		lblNewLabel.setBounds(0, 0, 280, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 53, 1338, 803);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionBackground(Color.green);
		table.setSelectionForeground(Color.blue);
		scrollPane.setViewportView(table);
		model=new DefaultTableModel();
		model.addColumn("Họ và tên");
		model.addColumn("CCCD");
		model.addColumn("Số điện thoại");
		model.addColumn("Ngày đặt phòng");
		model.addColumn("Ngày trả phòng");
		model.addColumn("Số phòng");
		model.addColumn("Email");
		model.addColumn("Loại phòng");
		model.addColumn("Tình trạng");
		model.addColumn("Giá");
		model.addColumn("Lầu");	
		model.addColumn("Phòng");
		
		
		
		
		table.setModel(model);
		
		
		out.writeUTF("history");
		

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
		
		JButton btnNewButton_5 = new JButton("Lịch Sử ");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_5.setIcon(new ImageIcon(lichsu.class.getResource("/image/time-past.png")));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setBackground(new Color(0, 64, 64));
		btnNewButton_5.setBounds(0, 180, 226, 36);
		panel.add(btnNewButton_5);

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
		btnNewButton_8_1.setIcon(new ImageIcon(lichsu.class.getResource("/image/comments-solid (1).png")));
		btnNewButton_8_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_8_1.setForeground(Color.WHITE);
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton_8_1.setBackground(new Color(0, 64, 64));
		btnNewButton_8_1.setBounds(0, 254, 226, 36);
		panel.add(btnNewButton_8_1);
	}
}
