package zzu.gg.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import zzu.gg.util.Message;

public final class GroupChatFrame extends ChatFrame {

	private Friends friends;
	
	GroupChatFrame(String title,Friends f) {
		super(title);
		this.friends = f;
		this.fasong.addActionListener(new SendButtonListener());
		
		this.textField.addActionListener(new SendButtonListener());
	}

	protected void closeMe(){
		this.friends.dropGroupChatFrame();
		this.setVisible(false);
		this.dispose();
	}
	
	class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String s = textField.getText().trim();
			if(s!=null && !s.trim().equals("")){
				String m = Message.createChatMessage( s);
				friends.getBcs().broadcastMessage(m);
				textField.setText("");
			}
		}

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

}
