package quanlykhachsan;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class Server implements Runnable {
	private List<Customer> clients;
	private ServerSocket serverSocket;
	private DataInputStream in;
	DataOutputStream out;
	StringBuilder str;
	private String user1;
	private String pw1;
	private String tien1, tien2;
	private int max;
	private String loaiphong3;
	private int gia3, tien3;
	private int lau3;
	private String phong3;
	private int a2, a1;

	public Server(int port) throws IOException {
		clients = new ArrayList<>();
		serverSocket = new ServerSocket(98);

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				Customer customer = new Customer(null, clientSocket);
				clients.add(customer);

				Thread thread = new Thread(() -> {
					try {
						DataInputStream in = new DataInputStream(clientSocket.getInputStream());
						DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
						Connection c;
						while (true) {
							String action = in.readUTF();
							switch (action) {
							case "tatcaphong":
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.phong";
									ResultSet rs = stmt.executeQuery(sql);
									str = new StringBuilder();

									while (rs.next()) {
										int id = rs.getInt("id");
										String loaiphong = rs.getString("loaiphong");
										String tinhtrang = rs.getString("tinhtrang");
										int gia = rs.getInt("Gia");
										String lau = rs.getString("phong");
										int phong = rs.getInt("lau");

										str.append(id + "," + loaiphong + "," + tinhtrang + "," + gia + "," + lau + ","
												+ phong + "\n");

									}

									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream1 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream1.writeUTF("tatcaphong");
								outputStream1.writeUTF(str + "");

								break;
							case "phongdadat":
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.phong where tinhtrang='Đang dùng';";

									ResultSet rs = stmt.executeQuery(sql);
									str = new StringBuilder();

									str.setLength(0);

									while (rs.next()) {
										int id = rs.getInt("id");
										String loaiphong = rs.getString("loaiphong");
										String tinhtrang = rs.getString("tinhtrang");
										int gia = rs.getInt("Gia");
										String lau = rs.getString("phong");
										int phong = rs.getInt("lau");

										str.append(id + "," + loaiphong + "," + tinhtrang + "," + gia + "," + lau + ","
												+ phong + "\n");

									}

									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream2 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream2.writeUTF("phongdadat");
								outputStream2.writeUTF(str + "");

								break;
							case "phongtrong":
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.phong where tinhtrang='Trống';";

									ResultSet rs = stmt.executeQuery(sql);
									str = new StringBuilder();

									str.setLength(0);

									while (rs.next()) {
										int id = rs.getInt("id");
										String loaiphong = rs.getString("loaiphong");
										String tinhtrang = rs.getString("tinhtrang");
										int gia = rs.getInt("Gia");
										String lau = rs.getString("phong");
										int phong = rs.getInt("lau");

										str.append(id + "," + loaiphong + "," + tinhtrang + "," + gia + "," + lau + ","
												+ phong + "\n");

									}

									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream3 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream3.writeUTF("phongtrong");
								outputStream3.writeUTF(str + "");

								break;
							case "phongbaotri":
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.phong where tinhtrang='Đang bảo trì';";

									ResultSet rs = stmt.executeQuery(sql);
									str = new StringBuilder();

									str.setLength(0);

									while (rs.next()) {
										int id = rs.getInt("id");
										String loaiphong = rs.getString("loaiphong");
										String tinhtrang = rs.getString("tinhtrang");
										int gia = rs.getInt("Gia");
										String lau = rs.getString("phong");
										int phong = rs.getInt("lau");

										str.append(id + "," + loaiphong + "," + tinhtrang + "," + gia + "," + lau + ","
												+ phong + "\n");

									}

									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream4 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream4.writeUTF("phongbaotri");
								outputStream4.writeUTF(str + "");

								break;
							case "tabledangdung":
								int tien;
								String idread = in.readUTF();
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();
									Statement stmt1 = c.createStatement();

									String sql2 = "select * from qlks.phong;";
									ResultSet rs2 = stmt.executeQuery(sql2);
									str = new StringBuilder();

									String sql = "select * from qlks.khachhang where khachhang.idphong=" + idread + ";";
									ResultSet rs = stmt.executeQuery(sql);
									String sql1 = "select * from phong where phong.id=" + idread + ";";
									ResultSet rs1 = stmt1.executeQuery(sql1);
									while (rs1.next()) {
										tien = rs1.getInt("Gia");
										tien1 = String.valueOf(tien);
									}
									while (rs.next()) {
										String hoten = rs.getString("hovaten");
										String cccd = rs.getString("CCCD");
										String sdt = rs.getString("SDT");
										Date ngaydat = rs.getDate("ngaydatphong");
										java.sql.Date ngaytra = rs.getDate("ngaytraphong");

										String mail = rs.getString("email");

										str.append(hoten + "," + cccd + "," + mail + "," + "gia" + "," + sdt + ","
												+ ngaytra + "," + ngaydat + "," + tien1 + "\n");

									}

									rs.close();
									stmt.close();
									stmt1.close();
									c.close();

								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream5 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream5.writeUTF("tabledangdung");
								outputStream5.writeUTF(str + "");

								break;
							case "login":

								String logininfo = in.readUTF();
								c = null;
								BufferedReader buff = new BufferedReader(new StringReader(logininfo));

								String line;
								while ((line = buff.readLine()) != null) {
									if (line.isEmpty()) {
										continue;
									}
									String[] com = line.split(",");
									user1 = com[0];
									pw1 = com[1];

								}

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/dangnhap";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.taikhoan";
									ResultSet rs = stmt.executeQuery(sql);

									int a = 0;
									while (rs.next()) {
										String user = rs.getString("user");
										String pw = rs.getString("pw");
										System.out.print(user1 + pw1 + "server");

										if (user1.equals(user) && pw1.equals(pw)) {
											a += 1;

										}

									}

									if (a == 0) {

										for (Customer client : clients) {

											DataOutputStream outputStream = new DataOutputStream(
													client.getSocket().getOutputStream());
											outputStream.writeUTF("login");
											outputStream.writeUTF("loginfalse");

										}
									} else {

										DataOutputStream outputStream = new DataOutputStream(
												clientSocket.getOutputStream());
										outputStream.writeUTF("login");
										outputStream.writeUTF("loginsuccess");

									}
									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								break;
							case "signup":
								String name = in.readUTF();
								String pass = in.readUTF();
								String chucvu = in.readUTF();

								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/dangnhap";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql1 = "select * from qlks.taikhoan;";

									ResultSet rs1 = stmt.executeQuery(sql1);
									int a = 0;
									while (rs1.next()) {
										String name1 = rs1.getString("user");
										String pass2 = rs1.getString("pw");

										if (name.equals(name1)) {
											JOptionPane.showMessageDialog(null, "Username already exists");
											a += 1;
										}

									}
									if (a == 0) {
										String sql = "insert into qlks.taikhoan values ('" + name + "','" + pass + "','"
												+ chucvu + "');";
										int rs = stmt.executeUpdate(sql);

										if (rs > 0) {

											DataOutputStream outputStream = new DataOutputStream(
													clientSocket.getOutputStream());
											outputStream.writeUTF("signup");
											outputStream.writeUTF("signupsuccess");

										}
									}

									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								break;
							case "datphong":

								String datphong = in.readUTF();

								if (datphong.equals("phongtrong")) {
									String loaiphong2 = in.readUTF();
									String giuong = in.readUTF();

									str = new StringBuilder();
									c = null;

									try {
										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);

										Statement stmt = c.createStatement();
										String sql = "select * from qlks.phong where loaiphong='" + loaiphong2
												+ "' and tinhtrang='Trống' and phong='" + giuong + "';";

										ResultSet rs = stmt.executeQuery(sql);

										while (rs.next()) {
											int id = rs.getInt("id");
											String loaiphong1 = rs.getString("loaiphong");
											String tinhtrang = rs.getString("tinhtrang");
											int gia = rs.getInt("Gia");

											String giuong1 = rs.getString("phong");

											str.append(id + "," + loaiphong1 + "," + tinhtrang + "," + gia + ","
													+ giuong1 + "\n");

										}

										rs.close();
										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									DataOutputStream outputStream = new DataOutputStream(
											clientSocket.getOutputStream());
									outputStream.writeUTF("phongtrong");
									outputStream.writeUTF(str + "");

								} else if (datphong.equals("buttondatphong")) {
									System.out.println("request datphong");
									String hovaten = in.readUTF();
									String cccd = in.readUTF();
									String sdt = in.readUTF();
									String todayAsString = in.readUTF();
									String todayAsString1 = in.readUTF();
									String firstelement = in.readUTF();
									String email = in.readUTF();
									c = null;
									try {

										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);

										Statement stmt = c.createStatement();

										String sql = "insert into qlks.khachhang(hovaten,CCCD,SDT,ngaydatphong,ngaytraphong,idphong,email) values ('"
												+ hovaten + "','" + cccd + "','" + sdt + "','" + todayAsString + "','"
												+ todayAsString1 + "'," + firstelement + ",'" + email + "');";

										int rs = stmt.executeUpdate(sql);
										String sql1 = "update qlks.phong set phong.tinhtrang ='Đang dùng' where phong.id="
												+ firstelement + ";";
										int rs1 = stmt.executeUpdate(sql1);

										if (rs >= 0) {

											String sql3 = "select max(lichsu.idkhach) from lichsu;";

											ResultSet rs3 = stmt.executeQuery(sql3);
											while (rs3.next()) {

												max = rs3.getInt("max(lichsu.idkhach)");

											}
											String sql4 = "select * from phong where phong.id=" + firstelement + ";";

											ResultSet rs4 = stmt.executeQuery(sql4);

											while (rs4.next()) {
												loaiphong3 = rs4.getString("loaiphong");

												gia3 = rs4.getInt("Gia");
												lau3 = rs4.getInt("lau");
												phong3 = rs4.getString("phong");

											}
											String sql5 = "insert into qlks.lichsu values(" + (max + 1) + ",'" + hovaten
													+ "','" + cccd + "','" + sdt + "','" + todayAsString + "','"
													+ todayAsString1 + "'," + firstelement + ",'" + email + "','"
													+ loaiphong3 + "','Đang dùng'," + gia3 + "," + lau3 + ",'" + phong3
													+ "');";

											int rs5 = stmt.executeUpdate(sql5);

											DataOutputStream outputStream = new DataOutputStream(
													clientSocket.getOutputStream());
											outputStream.writeUTF("datphongthanhcong");

										}

										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

								break;
							case "history":

								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/dangnhap";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql = "select * from qlks.lichsu";
									ResultSet rs = stmt.executeQuery(sql);
									str = new StringBuilder();
									while (rs.next()) {

										String hoten = rs.getString("hovaten");
										String cccd = rs.getString("CCCD");
										String sdt = rs.getString("SDT");
										Date ngaydat = rs.getDate("ngaydatphong");
										Date ngaytra = rs.getDate("ngaytraphong");
										String mail = rs.getString("email");

										int id = rs.getInt("idphong");
										String loaiphong = rs.getString("loaiphong");
										String tinhtrang = rs.getString("tinhtrang");
										int gia = rs.getInt("Gia");

										int lau = rs.getInt("lau");
										String phong = rs.getString("phong");

										str.append(hoten + "," + cccd + "," + sdt + "," + ngaydat.toLocaleString() + ","
												+ ngaytra.toLocaleString() + "," + id + "," + mail + "," + loaiphong
												+ "," + tinhtrang + "," + gia + "," + lau + "," + phong + "," + "\n");

									}

									rs.close();
									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
								outputStream.writeUTF("history");
								outputStream.writeUTF(str + "");

								break;
							case "thanhtoan":
								String firstelement = in.readUTF();
								c = null;

								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();

									String sql1 = "update qlks.phong set phong.tinhtrang='Trống' where phong.id="
											+ firstelement + ";";
									int rs1 = stmt.executeUpdate(sql1);

									if (rs1 > 0) {

										String sql2 = "update lichsu set lichsu.tinhtrang='Đã sử dụng' where lichsu.idphong="
												+ firstelement + ";";
										int rs2 = stmt.executeUpdate(sql2);

									}

									stmt.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								DataOutputStream outputStream6 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream6.writeUTF("thanhtoan");

								break;
							case "tablethanhtoan":

								c = null;
								String firstelement2 = in.readUTF();
								try {
									DriverManager.registerDriver(new com.mysql.jdbc.Driver());
									String url = "jdbc:MySQl://localhost:3306/qlks";
									String username = "root";
									String password = "";
									c = DriverManager.getConnection(url, username, password);

									Statement stmt = c.createStatement();
									Statement stmt1 = c.createStatement();
									str = new StringBuilder();
									String sql = "select * from qlks.khachhang where khachhang.idphong=" + firstelement2
											+ ";";
									ResultSet rs = stmt.executeQuery(sql);
									String sql1 = "select * from phong where phong.id=" + firstelement2 + ";";
									ResultSet rs1 = stmt1.executeQuery(sql1);
									while (rs1.next()) {
										tien3 = rs1.getInt("Gia");
										tien2 = String.valueOf(tien3);
										str.append(tien2 + ",");

									}
									while (rs.next()) {
										String hoten = rs.getString("hovaten");
										String cccd = rs.getString("CCCD");
										String sdt = rs.getString("SDT");
										Date ngaydat = rs.getDate("ngaydatphong");
										Date ngaytra = rs.getDate("ngaytraphong");

										Calendar c1 = Calendar.getInstance();
										Calendar c2 = Calendar.getInstance();

										c1.setTime(ngaydat);
										c2.setTime(ngaytra);
										long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
												/ (24 * 3600 * 1000);
										String noDay1 = String.valueOf(noDay);

										str.append(noDay1 + ",");
										String thanhtien1 = String.valueOf(noDay * tien3);

										str.append(thanhtien1 + ",");
										String thue = String.valueOf(noDay * tien3 * 0.1);

										str.append(thue + ",");
										String tongtien = String.valueOf(noDay * tien3 * 0.1 + thanhtien1);

										str.append(tongtien + ",");
										String mail = rs.getString("email");

										str.append(hoten + ",");
										str.append(cccd + ",");
										str.append(mail + ",");
										str.append(firstelement2 + ",");
										str.append(ngaytra.toLocaleString() + ",");
										str.append(ngaydat.toLocaleString() + ",");
										str.append(sdt);

									}

									rs.close();
									stmt.close();
									stmt1.close();
									c.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								DataOutputStream outputStream7 = new DataOutputStream(clientSocket.getOutputStream());
								outputStream7.writeUTF("tablethanhtoan");
								outputStream7.writeUTF(str + "");
								break;
							case "edit":
								String action1 = in.readUTF();
								switch (action1) {
								case "searchroom":
									String id1 = in.readUTF();

									c = null;

									try {
										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);
										str = new StringBuilder();

										Statement stmt = c.createStatement();

										String sql1 = "select * from qlks.phong";
										ResultSet rs1 = stmt.executeQuery(sql1);

										while (rs1.next()) {
											String id = String.valueOf(rs1.getInt("id"));
											String loaiphong = rs1.getString("loaiphong");
											String tinhtrang = rs1.getString("tinhtrang");
											int gia = rs1.getInt("Gia");
											int lau = rs1.getInt("lau");
											String phong = rs1.getString("phong");

											if (id.equals(id1)) {

												str.append(id + "," + loaiphong + "," + tinhtrang + "," + gia + ","
														+ lau + "," + phong + "\n");

											}
										}
										rs1.close();
										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									DataOutputStream outputStream8 = new DataOutputStream(
											clientSocket.getOutputStream());
									outputStream8.writeUTF("searchroom");
									outputStream8.writeUTF(str + "");

									break;
								case "deleteroom":
									c = null;
									String firstelement3 = in.readUTF();

									try {
										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);

										Statement stmt = c.createStatement();

										String sql = "delete from qlks.phong where qlks.phong.id=" + firstelement3
												+ ";";
										int rs = stmt.executeUpdate(sql);
										if (rs > 0) {

											DataOutputStream outputStream10 = new DataOutputStream(
													clientSocket.getOutputStream());

											outputStream10.writeUTF("deleteroom");

										}

										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									break;
								case "editroom":
									String roomnum = in.readUTF();
									String cbxloaiphong = in.readUTF();
									String cbxtinhtrang = in.readUTF();
									String gia2 = in.readUTF();
									String lau2 = in.readUTF();
									String cbxphong = in.readUTF();

									try {
										c = null;
										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);

										Statement stmt = c.createStatement();

										String sql3 = "set foreign_key_checks=0";
										String sql = "delete from qlks.phong where qlks.phong.id=" + roomnum + ";";
										String sql1 = "insert into qlks.phong values (" + roomnum + ",'" + cbxloaiphong
												+ "','" + cbxtinhtrang + "'," + gia2 + "," + lau2 + ",'" + cbxphong
												+ "');";

										int a3 = stmt.executeUpdate(sql3);
										a2 = stmt.executeUpdate(sql);
										a1 = stmt.executeUpdate(sql1);
										if (a1 > 0 && a2 > 0) {

											DataOutputStream outputStream11 = new DataOutputStream(
													clientSocket.getOutputStream());

											outputStream11.writeUTF("editroom");

										}

										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									break;
								case "addroom":
									String id = in.readUTF();
									String loaiphong = in.readUTF();
									String tinhtrang = in.readUTF();
									String lau1 = in.readUTF();

									String phong = in.readUTF();
									String gia1 = in.readUTF();
									int id2;
									c = null;

									try {
										DriverManager.registerDriver(new com.mysql.jdbc.Driver());
										String url = "jdbc:MySQl://localhost:3306/qlks";
										String username = "root";
										String password = "";
										c = DriverManager.getConnection(url, username, password);

										DataOutputStream outputStream9 = new DataOutputStream(
												clientSocket.getOutputStream());

										int id3 = Integer.parseInt(id);
										int gia = Integer.parseInt(gia1);
										int lau = Integer.parseInt(lau1);
										Statement stmt = c.createStatement();

										String sql1 = "select * from qlks.phong";
										ResultSet rs1 = stmt.executeQuery(sql1);
										int a = 0;
										while (rs1.next()) {
											id2 = rs1.getInt("id");

											if (id3 == id2) {

												outputStream9.writeUTF("addroom");
												outputStream9.writeUTF("exist");

												a += 1;
												break;
											}
										}
										if (a == 0) {
											String sql = "insert into qlks.phong values (" + id + ",'" + loaiphong
													+ "','" + tinhtrang + "'," + gia + "," + lau + ",'" + phong + "');";
											int rs = stmt.executeUpdate(sql);
											if (rs > 0) {
												outputStream9.writeUTF("addroom");
												outputStream9.writeUTF("success");

											}
										}

										stmt.close();
										c.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									break;
								}
								break;
							case "add":
								String nameadd = in.readUTF();
								customer.setName(nameadd);
								sendOnlineList();
								break;
							case "send":
								String message = in.readUTF();
								sendToAll(message, customer);
								break;
							case "sendone":
								String message1 = in.readUTF();
								sendToAll(message1, customer);
								break;
							case "emoji":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji");
									out5.writeUTF(customer.getName());
								}

								break;
							case "emoji1":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji1");
									out5.writeUTF(customer.getName());
								}
								break;
							case "emoji2":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji2");
									out5.writeUTF(customer.getName());
								}
								break;
							case "emoji3":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji3");
									out5.writeUTF(customer.getName());
								}
								break;
							case "emoji4":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji4");
									out5.writeUTF(customer.getName());
								}
								break;
							case "emoji5":
								for (Customer client : clients) {
									DataOutputStream out5 = new DataOutputStream(client.getSocket().getOutputStream());
									out5.writeUTF("emoji5");
							
									out5.writeUTF(customer.getName());
								}
								break;

							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						clients.remove(clientSocket);

					}
				});
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendOnlineList() throws IOException {
		StringBuilder builder = new StringBuilder();
		for (Customer client : clients) {
			builder.append(client.getName()).append(",");
		}
		String list = builder.toString();
		for (Customer client : clients) {
			DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
			out.writeUTF("add");
			out.writeUTF(list);
		}
	}

	private void sendToAll(String message, Customer sender) throws IOException {
		for (Customer client : clients) {

			DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
			out.writeUTF("send");
			out.writeUTF(sender.getName() + ": " + message);

		}
	}

	public static void main(String[] args) {
		try {
			Server server = new Server(98);
			new Thread(server).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}