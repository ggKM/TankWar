package zzu.gg.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import zzu.gg.util.IPManager;
import zzu.gg.util.Message;

public final class WhisperChatFrame extends ChatFrame {

	IPManager ipm = null;
	IPMessageReceiver iprt = null ;
	
	public WhisperChatFrame(IPManager ipm) {
		
		super("");
		String s = ipm.getSocket().getInetAddress().getHostName();
		this.setTitle("和"+s+"的对话框");
		this.ipm = ipm;
		this.textField.addActionListener(new SendButtonListener() );
		this.fasong.addActionListener(new SendButtonListener());
		iprt = new IPMessageReceiver();
		Thread t = new Thread(iprt);
		t.start();
	}

	@Override
	public void setTaText(String content) {
		String s = this.textArea.getText();
		if(s!=null && s.trim().length()!=0){
		   s += '\n' ;
		}
		s+=content;
		
		this.textArea.setText(s);
		textArea.setCaretPosition(textArea.getText()
				.length() - 1);		
	}

	@Override
	protected void closeMe() {
		this.setVisible(false);
		this.dispose();	
		this.ipm.close();
	}
	
	class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String s = null;
			try {
				s = InetAddress.getLocalHost().getHostName()+':';
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			String content = textField.getText().trim();
			if(content!=null && !content.trim().equals("")){
				s += content;
				if(sendIPMessage(s)){
					setTaText(s);
					textField.setText("");
				}
				
				
			}
		}

	}
	//接受tcp/ip信息
	private class IPMessageReceiver extends Thread {

		public void run() {
			try {
				while (ipm.getDis() != null) {
					 String s = ipm.getDis().readUTF();
					 setTaText(s);
					 
				}
			} catch (Exception e) {
				setTaText("对方已退出,可以通过双击对方名字建立连接！");
			}

		}
	}
	public boolean sendIPMessage(String s) {
			try {
				
				ipm.getDos().writeUTF(s);
				return true;
			} catch (Exception e) {
				setTaText("对方已退出,可以通过双击对方名字建立连接！");
				return false;
			}

		}
	
}
