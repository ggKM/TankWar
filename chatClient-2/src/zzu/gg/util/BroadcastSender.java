package zzu.gg.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BroadcastSender extends Thread {

	InetAddress group;
	MulticastSocket socket;
	int broadcastPort = 9898;

	public BroadcastSender() {
		try {
			group = InetAddress.getByName("233.0.0.0");
			socket = new MulticastSocket(broadcastPort);
			socket.setTimeToLive(1);
			socket.joinGroup(group);
		} catch (IOException e) {
			System.out.println("new MulticastSocket error");
		}
	}

	public void broadcastMessage(String m) {
		byte[] buf = new byte[m.length()];
		buf = m.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
				broadcastPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("MulticastSocket send error");
		}

	}

	public void close() {
		if (socket != null) {
			try {
				socket.leaveGroup(group);
			} catch (IOException e) {
				System.out.println("退出组时出错！");
			}

			socket.close();
			socket = null;
		}

	}

}
