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

import zzu.gao.Table;
import zzu.gao.TableCollector;

public class BridgeTwo extends Frame {

	/**
	 *  version-1
	 */
	private BridgeOne bo = null;
	private BridgeTwo bt = null;

	Choice cho_field = null;
	Choice cho_order = null;
	TextField tf = null;
	Panel p = null;
	Button btn_next = null;
	Button btn_pre = null;

	private static TableCollector tables = null;
	private static HashMap<Label, TextField> lts = new HashMap<Label, TextField>();

	public BridgeTwo(BridgeOne bo) {
		super("简易-DAE-2E");

		this.bo = bo;
		tables = bo.getTables();
		this.bt = this;

		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		Label L = new Label("填写在excel中对应要显示的名字");
		L.setBounds(20, 30, 300, 20);
		this.add(L);

		p = new Panel(null);

		cho_field = new Choice();
		cho_field.setBounds(10, 320, 100, 30);
		this.add(cho_field);

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
				cho_field.add(temp);
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
		sp.setBounds(10, 50, 580, 260);
		sp.add(p);
		this.add(sp);

		cho_order = new Choice();
		cho_order.add("升序");
		cho_order.add("降序");
		cho_order.setBounds(130, 320, 100, 30);
		this.add(cho_order);

		tf = new TextField("请在此填入要导出的个数");
		tf.setBounds(250, 320, 220, 30);
		this.add(tf);

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

	public String getCondition() {
		String where = bo.getCondition();
		String order = cho_order.getSelectedItem();
		if (order.equals("升序")) {
			order = "asc";
		} else {
			order = "desc";
		}
		if (where != null) {
			where = where + " order by " + cho_field.getSelectedItem() + " "
					+ order ;
		} else {
			where = " order by " + cho_field.getSelectedItem() + " " + order;
		}
		String text = tf.getText().trim();
		if (text != null && !text.equals("") && !text.equals("请在此填入要导出的个数")) {
			int count = 0;
			try {
				count = Integer.parseInt(text);
			} catch (NumberFormatException e) {
				count = 0;
			}
			where = where + " limit " + "0," + count;
		}
		return where;
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
			bt.dispose();
			bo.setVisible(true);

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

			bt.setVisible(false);
			new BridgeThree(bt);
			
		}
	}

}
