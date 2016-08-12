package zzu.gg.frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoadFrame extends Frame {

	Socket client = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	String name = null;
	
	private TextField ip1 = null;
	private TextField ip2 = null;
	private TextField ip3 = null;
	private TextField ip4 = null;
	private Label L1 = null;
	private String ipAdress = null;
	public boolean con1 = false;// 判断是否连接
	public boolean con2 = false;// 判断命名是否出错
	public Label text = null;
	private Label user = null;
	private Label mima = null;
	private TextField QQname = null;
	private TextField QQmima = null;
	private Button bt = null;
	
/*	public static void main(String[] args){
		new LoadFrame();
	}*/

	LoadFrame() {
		super("登陆！");
		setBounds(500, 200, 400, 300);
		setLayout(null);
		setBackground(new Color(92, 216, 10));
		QQname = new TextField();
		QQname.setBounds(50, 150, 300, 30);
		QQname.addKeyListener(new EnterListen());
		add(QQname);

		QQmima = new TextField();
		QQmima.addKeyListener(new EnterListen());
		QQmima.setBounds(50, 200, 300, 30);
		add(QQmima);

		mima = new Label("密码:");
		mima.setBounds(5, 200, 50, 30);
		add(mima);

		user = new Label("用户名:");
		user.setBounds(5, 150, 50, 30);
		add(user);

		bt = new Button("登陆！");
		bt.setBounds(150, 90, 100, 50);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.orange);
		bt.addActionListener(new LoadListen());
		add(bt);

		text = new Label("未连接至服务器！");
		text.setAlignment(Label.CENTER);
		text.setBackground(Color.red);
		text.setBounds(50, 50, 300, 30);
		add(text);

		ip1 = new TextField();
		ip1.setBounds(80, 250, 35, 20);
		ip1.addKeyListener(new EnterListen());
		add(ip1);

		ip2 = new TextField();
		ip2.setBounds(120, 250, 35, 20);
		ip2.addKeyListener(new EnterListen());
		add(ip2);

		ip3 = new TextField();
		ip3.setBounds(160, 250, 35, 20);
		ip3.addKeyListener(new EnterListen());
		add(ip3);

		ip4 = new TextField();
		ip4.setBounds(200, 250, 35, 20);
		ip4.addKeyListener(new EnterListen());
		add(ip4);

		L1 = new Label("服务器IP:");
		L1.setBounds(5, 250, 100, 30);
		add(L1);

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setResizable(false);
		this.setVisible(true);

	}

	public void connect() {

		try {

			client = new Socket(ipAdress, 6666);

			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			con1 = true;
		} catch (UnknownHostException e) {
			text.setText("找不到服务器！");
			// System.exit(0);
		} catch (IOException e) {
			text.setText("获取输出流出错或未接入端口,请重启程序尝试！！");
			// System.exit(0);
		}

	}

	private class LoadListen implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String struser = QQname.getText().trim();
			String strmima = QQmima.getText().trim();

			String p1 = ip1.getText();
			String p2 = ip2.getText();
			String p3 = ip3.getText();
			String p4 = ip4.getText();

			if (struser.equals("")) {
				text.setText("不能使用空的用户名，请重新输入！");
			} else if (p1 == "" || p2 == "" || p3 == "" || p4 == "") {
				text.setText("服务器IP不对，请重新输入！");
			} else {
				ipAdress = p1 + "." + p2 + "." + p3 + "." + p4;
				name = struser;

				connect();

				if (con1) {
					try {
						dos.writeUTF(strmima);
						dos.flush();
						if (dis.readUTF().equals("mimadui")) {
							dos.writeUTF(struser);// 第一次输出名字
							dos.flush();
							String mingzi = dis.readUTF();// 第一次接收，判断命名是否成功
							if (mingzi.equals("此名已有人用，请重新注册！")) {
								text.setText(mingzi);
							} else {
								if (mingzi.equals("success")) {
									con2 = true;
								}
							}
						} else {
							text.setText("密码错误，请重新输入！");
						}
					} catch (IOException e1) {
						text.setText("命名时出错！");
						// System.exit(-1);
					}
				}
			}
		}

	}

	private class EnterListen extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if (KeyEvent.VK_ENTER == e.getKeyCode()) {
				String struser = QQname.getText().trim();
				String strmima = QQmima.getText().trim();

				String p1 = ip1.getText();
				String p2 = ip2.getText();
				String p3 = ip3.getText();
				String p4 = ip4.getText();

				if (struser.equals("")) {
					text.setText("不能使用空的用户名，请重新输入！");
				} else if (p1 == "" || p2 == "" || p3 == "" || p4 == "") {
					text.setText("服务器IP不对，请重新输入！");
				} else {
					ipAdress = p1 + "." + p2 + "." + p3 + "." + p4;
					name = struser;

					connect();

					if (con1) {
						try {
							dos.writeUTF(strmima);
							dos.flush();
							if (dis.readUTF().equals("mimadui")) {
								dos.writeUTF(struser);// 第一次输出名字
								dos.flush();
								String mingzi = dis.readUTF();// 第一次接收，判断命名是否成功
								if (mingzi.equals("此名已有人用，请重新注册！")) {
									text.setText(mingzi);
								} else {
									if (mingzi.equals("success")) {
										con2 = true;
									}
								}
							} else {
								text.setText("密码错误，请重新输入！");
							}
						} catch (IOException e1) {
							text.setText("命名时出错！");
							// System.exit(-1);
						}
					}
				}
			}

		}

	}
}