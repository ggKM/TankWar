package zzu.gg.util;

import zzu.gg.frame.ChatFrame;
import zzu.gg.frame.Friends;

public class Message {
	private static Friends friends;

	public Message(Friends f) {
		this.friends = f;
	}

	public static String createExitMessage() {
		return "exit" + friends.getUser().getIP() ;
	}

	public static String createLoadMessage() {
		return "load" + friends.getUser().getIP() + '#'+String.valueOf(friends.getUser().getIPPort())+'#'
				+ friends.getUser().getName();
	}

	public static String createChatMessage( String content) {
		return "chat" +friends.getUser().getName()+ ':' + content;
	}

	public static String createInfoMessage() {
		return "info" + friends.getUser().getIP() + '#'+String.valueOf(friends.getUser().getIPPort())+'#'
				+ friends.getUser().getName();
	}

	public static void parse(String m) {
		String byte_4 = m.substring(0, 4);
		String mg = null;
		if (byte_4.equals("load")) {
			String subs = m.substring(4, m.lastIndexOf('#'));
			String ip = subs.substring(0, subs.lastIndexOf('#'));
			String port = subs.substring(subs.lastIndexOf('#')+1,subs.length());
			String name = m.substring(m.lastIndexOf('#') + 1, m.length());
			User u = new User(ip, name,Integer.parseInt(port));
			if (!u.equals(friends.getUser())) {
				friends.addMember(u);
				friends.update();
				mg = createInfoMessage();
				BroadcastSender bcs = new BroadcastSender();
				bcs.broadcastMessage(mg);
			}
		} else if (byte_4.equals("info")) {
			String subs = m.substring(4, m.lastIndexOf('#'));
			String ip = subs.substring(0, subs.lastIndexOf('#'));
			String port = subs.substring(subs.lastIndexOf('#')+1,subs.length());
			String name = m.substring(m.lastIndexOf('#') + 1, m.length());
			User u = new User(ip, name,Integer.parseInt(port));
			friends.addMember(u);
			friends.update();
		} else if (byte_4.equals("chat")) {
			if (friends.getGroupFrame() != null) {
				friends.getGroupFrame().setTaText(m.substring(4, m.length()));
			}
		} else if (byte_4.equals("exit")) {
			String ip = m.substring(4, m.length());
			User u = new User(ip);
			friends.removeMember(u);
			friends.update();
		} 
	}
}
