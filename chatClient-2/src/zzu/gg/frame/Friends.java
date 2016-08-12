package zzu.gg.frame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import zzu.gg.util.BroadcastReceiver;
import zzu.gg.util.BroadcastSender;
import zzu.gg.util.IPManager;
import zzu.gg.util.Message;
import zzu.gg.util.User;
import zzu.gg.util.WhisperChatServer;

public class Friends extends JFrame {

	private User user = new User();
	private static ArrayList<User> userList = new ArrayList<User>();
	private Friends me = this;
	private BroadcastSender bcs = new BroadcastSender();
	private BroadcastReceiver bcr = new BroadcastReceiver();
	private Message message = null;
	private GroupChatFrame groupFrame = null;
	private Box members;
	private WhisperChatServer wcs = new WhisperChatServer(this.user.getIPPort());

	public static void main(String[] args) {

		new Friends();
	}

	public Friends() {
		super("WO");
		setBounds(100, 20, 270, 580);
		setLayout(null);
		
		JPanel myPanel = new MyPanel();
		FlowLayout gl = new FlowLayout();
		gl.setVgap(10);
		gl.setAlignment(FlowLayout.LEFT);
		myPanel.setLayout(gl);
		myPanel.setBounds(0,0,270, 1000);
		this.add(myPanel);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		userList.add(this.user);
		wcs.start();

		Panel top = new Panel();
		
		MyCanvas mc = new MyCanvas();
		Image image = Toolkit.getDefaultToolkit().getImage("res/12.PNG");
		mc.setImage(image);
		mc.setSize(80, 80);
		top.add(mc);

		Label iam = new Label("WO");
		iam.setText(user.getName());
		top.add(iam);

		Box box = Box.createVerticalBox();
		box.add(top);
		Label group = new Label("WorkGroup:");
		group.addMouseListener(new WorkGroupListener());
		group.addMouseMotionListener(new MouseMoveListener());
		box.add(group);
		
		members = Box.createVerticalBox();
		box.add(members);
		myPanel.add(box);
		
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				exitGroup();
				System.exit(0);
			}
		});

		this.message = new Message(this);
		this.setResizable(false);
		this.setVisible(true);
		init();
		update();
	}

	public void update() {
		members.removeAll();
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			Label l = new Label(u.getName());
			l.addMouseListener(new WhisperListener());
			l.addMouseMotionListener(new MouseMoveListener());
			members.add(l);
		}
		members.validate();

	}

	public void init() {
		if (bcs == null || bcr == null) {
			System.out.println("new MulticastSocket error");
			System.exit(-1);
		}
		String m = Message.createLoadMessage();
		bcs.broadcastMessage(m);
		bcr.start();
	}

	public void addMember(User u) {
		if (!userList.contains(u)) {
			userList.add(u);
		}
	}

	public void removeMember(User u) {
		userList.remove(u);
	}

	public void dropGroupChatFrame() {
		this.groupFrame = null;
	}

	private void exitGroup() {
		this.bcr.close();
		String m = Message.createExitMessage();
		this.bcs.broadcastMessage(m);
		this.bcs.close();
		this.wcs.close();
	}

	class WorkGroupListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && groupFrame == null) {
				groupFrame = new GroupChatFrame("group chat", me);
			}
		}

		
		public void mouseExited(MouseEvent e) {
			((Label)e.getSource()).setBackground(null);
		}

	}

	class WhisperListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				String s = ((Label) e.getSource()).getText();
				User u = getUserByName(s);
				IPManager ipm = new IPManager(u.getIP(), u.getIPPort());
				new WhisperChatFrame(ipm);

			}
		}
		
		public void mouseExited(MouseEvent e) {
			((Label)e.getSource()).setBackground(null);
		}
	}

	public User getUserByName(String name) {
		for (int i = 0; i < userList.size(); i++) {
			User u = userList.get(i);
			if (name.equals(u.getName())) {
				return u;
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public BroadcastSender getBcs() {
		return bcs;
	}

	public BroadcastReceiver getBcr() {
		return bcr;
	}

	public GroupChatFrame getGroupFrame() {
		return groupFrame;
	}

	
	class MyCanvas extends Canvas {
		private Image in;
		private int image_width;
		private int image_height;

		public void setImage(Image in) {
			this.in = in;
		}

		public void paint(Graphics g) {
			g.drawImage(in, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	class MyPanel extends JPanel {
		Image im = Toolkit.getDefaultToolkit().getImage("res/background.png");

		public void paintComponent(Graphics g) {
			g.drawImage(im, 0, 0, null);
		}
	}
	
	class MouseMoveListener extends MouseAdapter {
		public void mouseMoved(MouseEvent e) {
			((Label)e.getSource()).setBackground(Color.lightGray);
		}
		 
	}
	

}
