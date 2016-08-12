import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author gg
 *
 */
public class NetClient {
	TankClient tc;

	private int udpPort;

	String IP; // server IP

	DatagramSocket ds = null;
	
	/**
	 * ���ݳ�����������ͻ���
	 * @param tc ��Ϸ����
	 */
	public NetClient(TankClient tc) {
		this.tc = tc;

	}
	
	/**
	 * ���ӷ�����
	 * @param IP ������IP
	 * @param port �������˿�
	 */
	public void connect(String IP, int port) {

		this.IP = IP;

		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		Socket s = null;
		try {
			s = new Socket(IP, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
			tc.myTank.id = id;

			if (id % 2 == 0)
				tc.myTank.good = false;
			else
				tc.myTank.good = true;

			System.out.println("Connected to server! and server give me a ID:"
					+ id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
					s = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		TankNewMsg msg = new TankNewMsg(tc.myTank);
		send(msg);

		new Thread(new UDPRecvThread()).start();
	}
	
	/**
	 * ������Ϣ
	 * @param msg �����͵���Ϣ
	 */
	public void send(Msg msg) {
		msg.send(ds, IP, TankServer.UDP_PORT);
	}

	private class UDPRecvThread implements Runnable {

		byte[] buf = new byte[1024];

		public void run() {

			while (ds != null) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					parse(dp);
					System.out.println("a packet received from server!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp
					.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int msgType = 0;
			try {
				msgType = dis.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Msg msg = null;
			switch (msgType) {
			case Msg.TANK_NEW_MSG:
				msg = new TankNewMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_NEW_MSG:
				msg = new MissileNewMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.TANK_DEAD_MSG:
				msg = new TankDeadMsg(NetClient.this.tc);
				msg.parse(dis);
				//System.out.println(udpPort + "收到");
				break;
			case Msg.MISSILE_DEAD_MSG:
				msg = new MissileDeadMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			}

		}

	}
	
	/**
	 * ȡ��UDP�˿�(�ͻ��˽���������)
	 * @return
	 */
	public int getUdpPort() {
		return udpPort;
	}
	
	/**
	 * �趨UDP�˿�(�ͻ��˽���������)
	 * @param udpPort
	 */
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
}
