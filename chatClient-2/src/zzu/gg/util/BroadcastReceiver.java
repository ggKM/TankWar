package zzu.gg.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BroadcastReceiver extends Thread {

	int port = 9898;
	InetAddress group;
	MulticastSocket socket; // socket sends and receives the packet.
	DatagramPacket packet;
	byte[] buf = new byte[1024];// If the message is longer than the packet's
								// length, the message is truncated.
	boolean running = true;

	public BroadcastReceiver() {
		try {
			socket = new MulticastSocket(port);
			group = InetAddress.getByName("233.0.0.0");
			socket.joinGroup(group); // 加入广播组,加入group后,socket发送的数据报,可以被加入到group中的成员接收到。
			packet = new DatagramPacket(buf, buf.length);

		} catch (IOException e) {
			System.out.println("new MulticastSocket error");
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				socket.receive(packet);
			} catch (IOException e) {
				System.out.println(" MulticastSocket reveive error");
			}
			String message = new String(packet.getData(), 0, packet.getLength());
			Message.parse(message);

		}
	}

	public void close() {
		this.running = false;

		if (socket != null) {
			try {
				socket.leaveGroup(group);
			} catch (IOException e) {
				System.out.println("退出组时出错！");
			}

		}
		
	}

}
