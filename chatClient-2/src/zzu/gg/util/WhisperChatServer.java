package zzu.gg.util;
import java.io.*;
import java.net.*;
import java.util.*;

import zzu.gg.frame.WhisperChatFrame;

public class WhisperChatServer extends Thread {

	ServerSocket server = null;
	boolean stop = true;
	ArrayList<Socket> sockets = new ArrayList<Socket>();
	int port ;
	public WhisperChatServer(int p){
		this.port = p;
	}
	public void run() {
		try {
			server = new ServerSocket(this.port);
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
				sockets.add(client);
				IPManager ipm = new IPManager(client,this);
				new WhisperChatFrame(ipm);
			}	
		} catch (IOException e2) {
			System.out.println("���������˳���");

		}
	}
	public void close(){
		this.stop = true;
		if(server!=null){
			try {
				server.close();
			} catch (IOException e) {
				System.out.println("server �������˳�");
			}
			server=null;
		}
	}

}
