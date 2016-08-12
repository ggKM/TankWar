import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import zzu.gao.DataBase;
import zzu.gao.DataBaseManager;
import zzu.gao.ExcelRW;
import zzu.gao.TableCollector;

public class BridgeThree extends Frame {

	/**
	 * version-1
	 */
	private String where = null;
	private BridgeTwo bt = null;
	private BridgeThree bth = null;

	Button btn_next = null;
	Button btn_pre = null;
	TextField tf = null;
	Button btn_scan = null;
	TextArea ta = null;
	List lst_excel = null;

	private static TableCollector tables = null;

	public BridgeThree(BridgeTwo bt) {

		super("简易-DAE-2E");

		this.bt = bt;
		tables = bt.getTables();
		this.bth = this;
		this.where = bt.getCondition();
		// System.out.println(where);
		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		tf = new TextField("请选择文件");
		tf.setBounds(10, 30, 400, 30);
		this.add(tf);

		btn_scan = new Button("浏览...");
		btn_scan.setBounds(420, 30, 80, 30);
		btn_scan.addActionListener(new ListenerForBtn_scan());
		this.add(btn_scan);

		lst_excel = new List();
		lst_excel.setBounds(150, 70, 300, 160);
		this.add(lst_excel);

		ta = new TextArea();
		ta.setEditable(false);
		ta.setBounds(150, 250, 300, 100);
		this.add(ta);

		btn_pre = new Button("上一步");
		btn_pre.setBounds(420, 360, 70, 20);
		btn_pre.addActionListener(new ListenerForBtn_pre());
		this.add(btn_pre);

		btn_next = new Button("导入");
		btn_next.setBounds(500, 360, 70, 20);
		btn_next.addActionListener(new ListenerForBtn_next());
		this.add(btn_next);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		this.setResizable(false);
		this.setVisible(true);
	}

	class ListenerForBtn_scan implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FileDialog dia_file = new FileDialog(bth, "选择文件", FileDialog.LOAD);
			dia_file.setVisible(true);
			tf.setText(dia_file.getDirectory() + dia_file.getFile());
			String fp = tf.getText();
			if (fp != null && !fp.trim().equals("")) {
				ExcelRW e = ExcelRW.getInstance(fp);
				if (e != null) {
					String[] s = e.getAllWookbook();
					e.close();
					lst_excel.removeAll();
					for (int i = 0; i < s.length; i++) {
						lst_excel.add(s[i]);
					}
					lst_excel.select(0);
				}
			}
		}

	}

	class ListenerForBtn_pre implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			bth.dispose();
			bt.setVisible(true);
		}

	}

	class ListenerForBtn_next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String filePath = tf.getText();
			String sheet = lst_excel.getSelectedItem();

			if (filePath != null && !filePath.trim().equals("")
					&& (filePath.endsWith(".xls")||filePath.endsWith(".et"))) {

				ExcelRW e1 = ExcelRW.getInstance(filePath);
				if (e1 != null) {
					e1.saveAs();
					e1.close();

					DataBase curDB = DataBaseManager.getInstance()
							.produceCurDB();
					String sql = tables.getSql(where);
					ta.append(sql + "\n");
					ta.append(curDB.DBToExcel(filePath, sheet, sql));
				}
			} else {
				ta.append("请输入正确文件路径！\n");
			}

		}

	}

}
