package quanlykhachsan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class  clientframe extends JFrame {

	private JPanel contentPane;
	private JTextField txtsend;
	private JTextPane textPane;
    private DataInputStream in;
    private DataOutputStream out;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTable table;
	private static DefaultTableModel model;
	String newmessage;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientframe frame = new clientframe();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public clientframe() throws IOException {
		Socket socket = new Socket("localhost", 98);
        
    	in = new DataInputStream(socket.getInputStream());
    	out = new DataOutputStream(socket.getOutputStream());

		new Thread(() -> {
			try {
				in = new DataInputStream(socket.getInputStream());
			
				while (true) {
					
					String action=in.readUTF();
				
					switch (action) {
					case "add": 
						model.setRowCount(0);
					 newmessage=in.readUTF();
					 String[] com=newmessage.split(",");
					 for(int i=0;i<com.length;i++) {
						 model.addRow(new Object[] { com[i] });
					 }
					
					    break;
					case "send": 

					String messagereceive=in.readUTF();
					System.out.println(messagereceive+"client");
						StringBuilder str = new StringBuilder();
						String message = textPane.getText();

						str.append(message + "\n" + messagereceive);
						 textPane.setEditable(true);
						textPane.setText("" + str);
						 textPane.setEditable(false);
						    break;
						
					}

			

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		
		
		
		 JButton buttonsend = new JButton("Send");
		buttonsend.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonsend.setBounds(785, 464, 154, 42);
		buttonsend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = txtsend.getText();
				try {
					out.writeUTF("send");
					out.writeUTF(message);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
				txtsend.setText("");

			}
		});
		buttonsend.setEnabled(false);
		contentPane.add(buttonsend);
		
		JButton buttonstart = new JButton("Start");
		buttonstart.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonstart.setBounds(0, 464, 154, 42);
		buttonstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtsend.getText();
				
				try {
					out.writeUTF("add");
					out.writeUTF(name);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
				buttonstart.setEnabled(false);
				buttonsend.setEnabled(true);
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(buttonstart);
		

		txtsend = new JTextField();
		txtsend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int o;
		                if(e.getKeyCode() == KeyEvent.VK_ENTER){
		                    if(!txtsend.getText().equals(""))
		                        buttonstart.doClick();
		                    o=1;
		                    if(o==1) {
		                    	buttonsend.doClick();
		                    }
		                        
		                }
			}
		});
		txtsend.setBounds(153, 464, 633, 42);
		contentPane.add(txtsend);
		txtsend.setColumns(10);

		 
	 textPane = new JTextPane();
	 textPane.setBounds(0, 10, 780, 454);
		contentPane.add(textPane);
		   textPane.setFont(new Font("Arial", Font.PLAIN, 14));
	        textPane.setForeground(Color.BLACK);
	        textPane.setBackground(Color.WHITE);
	        textPane.setEditable(false);
	        textPane.setText("Enter your name and click start:");
	        
	        panel = new JPanel();
	        panel.setBounds(785, 66, 154, 398);
	        contentPane.add(panel);
	        panel.setLayout(new BorderLayout(0, 0));
	        
	        scrollPane = new JScrollPane();
	        panel.add(scrollPane, BorderLayout.CENTER);
	        
	        table = new JTable();
	        scrollPane.setViewportView(table);
	    	model = new DefaultTableModel();
			model.addColumn("Online");
	

			table.setModel(model);


	}
}
