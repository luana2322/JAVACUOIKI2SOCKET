package quanlykhachsan;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class trangchu extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		trangchu frame = new trangchu();

	}

	/**
	 * Create the frame.
	 */
	public trangchu() {
		init();
		this.setTitle("QUẢN LÝ KHÁCH SẠN");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void init() {

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

		lblNewLabel = new JLabel("         TRANG CHỦ");
		lblNewLabel.setBounds(0, 0, 280, 45);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(225, 53, 1338, 803);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(219, 178, 119, 44);
		panel_2.add(dateChooser);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
//				
			
				String pattern = "yyyy-MM-dd   HH:mm:ss";
//				  HH:mm:ss
				// Create an instance of SimpleDateFormat used for formatting 
				// the string representation of date according to the chosen pattern
				DateFormat df = new SimpleDateFormat(pattern);
				 
				// Get the today date using Calendar object.
				java.util.Date today = dateChooser.getDate();        
				// Using DateFormat format method we can create a string 
				// representation of a date with the defined format.
				String todayAsString = df.format(today);
				System.out.println(todayAsString);
				 
				// Print it!
				
				
			}
		});
		btnNewButton_7.setBounds(435, 307, 85, 21);
		panel_2.add(btnNewButton_7);
		
	
		

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
	if(JOptionPane.showConfirmDialog(null, "Do you want to log out?","Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION) {
					
				}else {
					
					try {
						 framedangnhap frame;
						frame = new framedangnhap();
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
	}
}
