package zzu.gg.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public abstract class ChatFrame extends Frame {

	 Button fasong = null;
	 TextField textField = null;
	 TextArea textArea = null;

	public abstract void setTaText(String content);
	
	ChatFrame(String title) {
		super(title);
		setBounds(400, 150, 600, 400);
		setLayout(null);
		setBackground(new Color(92, 216, 10));
		this.setResizable(false);

		textArea = new TextArea();
		textArea.setFont(new Font("Œ¢»Ì—≈ÃÂ", 0, 20));
		textArea.setBounds(4, 25, 592, 265);
		textArea.setBackground(new Color(0, 157, 255));
		add(textArea);

		textField = new TextField();
		textField.setFont(new Font("Œ¢»Ì—≈ÃÂ", 0, 20));
		textField.setBounds(4, 320, 592, 75);
		add(textField);

		fasong = new Button("send");
		fasong.setBounds(480, 293, 100, 25);
		add(fasong);

		
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				closeMe();
			}

		});

		this.setVisible(true);
		
		textField.requestFocus();

	}
	protected abstract void closeMe();

	

	
}
