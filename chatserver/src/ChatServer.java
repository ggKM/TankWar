import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

	private String servermima = "chat";
	ServerSocket server = null;
	boolean stop = true;
	List<clientThread> clients = new ArrayList<clientThread>();

	public static void main(String[] args) {

		new ChatServer().start();

	}

	public void start() {
		try {
			server = new ServerSocket(6666);
			stop = false;
			System.out.println("server��ʼ���ɹ���");
		} catch (IOException e1) {
			System.out.println("server��ʼ��ʧ�ܣ�");
			System.exit(-1);
		}

		try {
			while (!stop) {

				Socket client = null;
				client = server.accept();
				clientThread c = new clientThread(client);// ��������
				if (c.success()) {
					if (c.equal()) {
						c.dos.writeUTF("�����������ã�������ע�ᣡ");// ��һ�����
						c.dos.flush();
						c.reset();
						if (c != null)
							c = null;

					} else {
						c.zishen = c;
						c.dos.writeUTF("success");// ��һ�����
						c.dos.flush();
						clients.add(c);
						new Thread(c).start();
						// System.out.println(c.name + "������");
					}
				} else {
					c.reset();
					if (c != null)
						c = null;
					System.out.println("��������ͨ����ȡʧ�ܣ����������һ���߳�δ������");
				}
			}
		} catch (IOException e2) {
			System.out.println("����������ʧ�ܣ�");

		}
	}

	class clientThread implements Runnable {

		private String name = null;
		private boolean chat = false;
		private Socket client = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		clientThread zishen = null;

		clientThread(Socket c) {
			client = c;
			chat = true;

			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				if (dis.readUTF().equals(servermima)) {// ��һ�ν��գ�����
					dos.writeUTF("mimadui");
					this.name = dis.readUTF();
				} else {
					dos.writeUTF("�������");
				}
			} catch (IOException e) {
				System.out.println(name + "�޷���ȡstream��");
			}

		}

		public boolean success() {
			if ((dis != null) && (dos != null) && (name != null)) {
				return true;
			} else {
				return false;
			}
		}

		public void reset() {
			if (dis != null)
				dis = null;
			if (dos != null)
				dos = null;
			if (name != null)
				name = null;
			if (zishen != null) {
				zishen = null;
			}
		}

		public boolean equal() {
			for (int i = 0; i < clients.size(); i++) {
				clientThread ct = clients.get(i);
				if ((ct.name).equals(this.name)) {
					return true;
				}
			}
			return false;
		}

		public boolean setName(String str) {
			if (str.equals("")) {
				return false;
			} else {
				name = str;
				return true;
			}

		}

		public void send(String s) {

			try {
				dos.writeUTF(s);
				dos.flush();

			} catch (IOException e) {
				clients.remove(this);
				this.reset();

			}

		}

		public clientThread match(String s) {
			for (int i = 0; i < clients.size(); i++) {
				clientThread ct = clients.get(i);
				if (ct.name.equals(s)) {
					return ct;
				}
			}
			return null;

		}

		public void run() {

			clientThread ct = null;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < clients.size(); i++) {
				ct = clients.get(i);
				sb.append(ct.name + " ");
			}

			String s = sb.toString();

			try {
				dos.writeUTF(s);
				dos.flush();
				for (int i = 0; i < clients.size(); i++) {
					ct = clients.get(i);
					if (ct != this) {
						ct.send("$" + name);
					}
				}
				while (chat) {
					s = dis.readUTF();
					String duixiang[] = s.split(" ");
					//StringBuffer songBuffer = new StringBuffer();
					synchronized (clients) {
						if (s.equals("������" + " " + name + "�����ߣ�")) {
							dos.close();
							chat = false;

						} else {

							System.out.println(duixiang[1]);

							if (duixiang[0].equals("������") == true) {
								for (int i = 0; i < clients.size(); i++) {
									ct = clients.get(i);
									StringBuffer songBuffer = new StringBuffer(s);
									for(int k=0;k<=duixiang[0].length();k++){
									songBuffer.deleteCharAt( 0);
									}
									String song = songBuffer.toString();
									ct.send(song);

								}
							} else if ((ct = match(duixiang[0])) != null) {
								StringBuffer songBuffer = new StringBuffer(s);
								for(int k=0;k<=duixiang[0].length();k++){
								songBuffer.deleteCharAt( 0);
								}
								String song = songBuffer.toString();
								ct.send("[˽��]" + song);
								this.send("[˽��]" + song);
							} else {
								this.send("server:" + "�Ҳ���" + duixiang[0]);

							}

						}
					}
				}

			} catch (EOFException e5) {
				// System.out.println(name + "�����ߣ�");

			} catch (IOException e3) {
				// System.out.println(name + "���������ߣ�");

			} finally {
				synchronized (clients) {
					clients.remove(zishen);
					for (int i = 0; i < clients.size(); i++) {
						ct = clients.get(i);
						ct.send(name + "�����ߣ�");
					}
				}
				System.out.println(name + "�����ߣ�");
				if (clients.size() == 0) {
					System.out.println("��ȫ�����ߣ�");
				}

				try {
					if (dis != null) {
						dis.close();
					}
					if (dos != null) {
						dos.close();
					}
					if (client != null) {
						client.close();
					}

				} catch (IOException e) {
					System.out.println(name + "�̳߳���");
				}
			}

		}

	}

}
