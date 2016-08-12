package zzu.gg.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class IPManager {

	private Socket socket = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private WhisperChatServer wcs = null;
	public IPManager(Socket s,WhisperChatServer wcs) {
		this.wcs = wcs;
		socket = s;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public IPManager(String ip, int port) {
		connect(ip, port);
	}

	public void connect(String ip, int port) {

		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// System.exit(0);
		} catch (IOException e) {
			// System.exit(0);
		}

	}

	public void close() {
		if(this.wcs!=null){
			this.wcs.sockets.remove(socket);
		}
		try {
		if (socket != null) {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			socket = null;
		}
		
			if (dis != null) {
				dis.close();
				dis = null;
			}
			if (dos != null) {
				dos.close();
				dos = null;
			}
			
		} catch (IOException e) {
			System.out.println("非正常退出");
		}
		if(this.wcs!=null){
			this.wcs.sockets.remove(socket);
		}

	}

	public Socket getSocket() {
		return this.socket;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public DataInputStream getDis() {
		return dis;
	}
}
