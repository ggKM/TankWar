import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Start extends Frame {

	/**
	 *version-1
	 */
	Button btn_import = null;
	Button btn_export = null;
	Start start = null;
	
	public static void main(String[] args) {

		new Start();
	}

	public Start(){
		super("简易-DAE");
		this.start = this;
		
		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(500, 200, 400, 200);
		setBackground(new Color(171, 205, 239));
		
		Label L1 = new Label("欢迎使用本程序!");
		L1.setBounds(100, 30, 200, 20);
		this.add(L1);
		
		btn_import = new Button("导出到excel");
		btn_import.setBounds(30, 150, 150, 30);
		btn_import.addActionListener(new ListenerForBtn_import());
		this.add(btn_import);
		
		btn_export = new Button("导入到数据库");
		btn_export.setBounds(200, 150, 150, 30);
		btn_export.addActionListener(new ListenerForBtn_export());
		this.add(btn_export);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		this.setResizable(false);
		this.setVisible(true);
	}
	
	class ListenerForBtn_import implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new BridgeZero();
			start.dispose();
		}
	}
	class ListenerForBtn_export implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new OutZero();
			start.dispose();
		}
	}
}
