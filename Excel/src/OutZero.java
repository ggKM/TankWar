import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import zzu.gao.DataBase;
import zzu.gao.DataBaseManager;
import zzu.gao.ExcelRW;
import zzu.gao.Table;
import zzu.gao.TableCollector;

public class OutZero extends Frame {

	/**
	 * version-1
	 */
	private OutZero oz = null;

	Button btn_next = null;
	TextField tf = null;
	Button btn_scan = null;
	List lst_excel = null;
	List lst_field = null;

	private static TableCollector tables = null;


	public OutZero() {

		super("简易-DAE-2D");

		this.oz = this;
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

		Label l1 = new Label("选择工作簿名");
		l1.setBounds(10, 70, 200, 30);
		this.add(l1);

		lst_excel = new List();
		lst_excel.setBounds(10, 110, 200, 240);
		lst_excel.addItemListener(new ListenerForLst_excel());
		this.add(lst_excel);

		Label l2 = new Label("选择导出字段名");
		l2.setBounds(300, 70, 200, 30);
		this.add(l2);

		lst_field = new List(4, true);
		lst_field.setBounds(300, 110, 200, 240);
		this.add(lst_field);

		btn_next = new Button("下一步");
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

	public String getExcelPath(){
		return this.tf.getText();
	}
	public TableCollector getTables() {
		return tables;
	}

	class ListenerForBtn_scan implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FileDialog dia_file = new FileDialog(oz, "选择文件", FileDialog.LOAD);
			dia_file.setVisible(true);
			tf.setText(dia_file.getDirectory() + dia_file.getFile());
			String fp = tf.getText();

			if (fp != null && !fp.trim().equals("")) {

				ExcelRW e = ExcelRW.getInstance(fp);
				if (e != null) {
					String[] s1 = e.getAllWookbook();
					lst_excel.removeAll();
					for (int i = 0; i < s1.length; i++) {
						lst_excel.add(s1[i]);
					}
					lst_excel.select(0);
					lst_field.removeAll();
					String sheetName = lst_excel.getSelectedItem();
					String[] s2 = e.readRow(sheetName, 0);
					if (s2 != null) {
						for (int i = 0; i < s2.length; i++) {
							lst_field.add(s2[i]);
						}
					}
					e.close();
				}
			}
		}

	}

	class ListenerForLst_excel implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			lst_field.removeAll();
			String sheetName = lst_excel.getSelectedItem();
			String fp = tf.getText();
			ExcelRW e = ExcelRW.getInstance(fp);
			if (e != null) {
				String[] s = e.readRow(sheetName, 0);
				if (s != null) {
					for (int i = 0; i < s.length; i++) {
						lst_field.add(s[i]);
					}
				}
				e.close();
			}
		}

	}

	class ListenerForBtn_next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sheetName = lst_excel.getSelectedItem();
			String[] fields = lst_field.getSelectedItems();
			Table table = new Table(sheetName);
			for (int i = 0; i < fields.length; i++) {
				table.fillField(fields[i], "");
			}
			tables = new TableCollector();
			tables.fillTable(table);
			oz.setVisible(false);
			new OutOne(oz);
		}
	}
}
