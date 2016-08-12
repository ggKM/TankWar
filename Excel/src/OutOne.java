import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import zzu.gao.DataBase;
import zzu.gao.DataBaseManager;
import zzu.gao.Table;
import zzu.gao.TableCollector;

public class OutOne extends Frame {

	/**
	 *  version-1
	 */
	private OutOne oo = null;

	private OutZero oz = null;
	
	Panel p = null;
	Button btn_next = null;
	Button btn_pre = null;
	Choice cho_dbtable = null;
	
	private String tableName = null;
	private String excelPath = null;
	
	private static TableCollector tables = null;
	private static HashMap<Label, TextField> lts = new HashMap<Label, TextField>();

	public OutOne(OutZero oz) {
		super("简易-DAE-2D");

		this.oz = oz;
		tables = oz.getTables();
		this.excelPath = oz.getExcelPath();
		this.oo = this;

		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		DataBase curDB = DataBaseManager.getInstance().produceCurDB();
		Iterator<String> it_db = null;
		
		Label L1 = new Label("选择数据库表");
		L1.setBounds(20, 30, 300, 20);
		this.add(L1);
		
		cho_dbtable = new Choice();
		cho_dbtable.setBounds(20, 50, 300, 20);
		ArrayList<String> allTable = curDB.getAllTable();
		it_db = allTable.iterator();
		while (it_db.hasNext()) {
			String item = it_db.next();
			cho_dbtable.add(item);
		}
		this.add(cho_dbtable);
		
		Label L2 = new Label("填写在数据库表中对应的字段");
		L2.setBounds(20, 90, 300, 20);
		this.add(L2);

		p = new Panel(null);

		ArrayList<String> selectedTable = tables.getTablesName();
		Iterator<String> it_table = selectedTable.iterator();
		int count = 0;
		String temp = null;
		while (it_table.hasNext()) {
			String tableName = it_table.next();
			Table table = tables.getTable(tableName);
			HashMap<String, String> fields = table.getFields();
			Set<String> keys = fields.keySet();
			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {
				String field = it.next();
				Panel p1 = new Panel(new GridLayout());
				p1.setBounds(0, count * 20 + 5, 550, 20);

				temp = tableName + "." + field;
				Label l1 = new Label(temp);
				p1.add(l1);

				TextField tf1 = new TextField();
				p1.add(tf1);

				lts.put(l1, tf1);
				p.add(p1);
				count++;
			}
		}
		int height = count * 20 + 5;
		p.setSize(550, height);

		ScrollPane sp = new ScrollPane();
		sp.setBounds(10, 110, 580, 240);
		sp.add(p);
		this.add(sp);

		
		btn_pre = new Button("上一步");
		btn_pre.setBounds(420, 360, 70, 20);
		btn_pre.addActionListener(new ListenerForBtn_pre());
		this.add(btn_pre);

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
		return this.excelPath;
	}
	
	public String getTableName(){
		return tableName;
	}

	public TableCollector getTables() {
		return tables;
	}

	class ListenerForBtn_pre implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> selectedTable = tables.getTablesName();
			Iterator<String> it_table = selectedTable.iterator();

			while (it_table.hasNext()) {
				String tableName = it_table.next();
				Table table = tables.getTable(tableName);
				HashMap<String, String> fields = table.getFields();
				fields.clear();
			}
			oo.dispose();
			oz.setVisible(true);

		}
	}

	class ListenerForBtn_next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Set<Label> labels = lts.keySet();
			Iterator<Label> it = labels.iterator();
			while (it.hasNext()) {
				Label l = it.next();
				String[] text = l.getText().split("\\.");
				String tableName = text[0];
				String key = text[1];
				TextField tf = lts.get(l);
				String value = tf.getText();
				tables.fillTable(tableName, key, value);
			}
			tableName = cho_dbtable.getSelectedItem();
			oo.setVisible(false);
			new OutTwo(oo);
			
		}
	}

}
