import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import zzu.gao.*;

public class BridgeOne extends Frame {

	/**
	 *  version-1
	 */
	List lst_table = null;
	TextField tf = null;
	Button btn_next = null;
	Button btn_pre = null;
	
	private BridgeZero bz = null;
	private BridgeOne bo = null;

	private static TableCollector tables = null;

	private static ArrayList<List> lists = new ArrayList<List>();

	public BridgeOne(BridgeZero bz) {
		super("简易-DAE-2E");

		this.bz = bz;
		this.bo = this;
		tables = bz.getTables();

		DataBase curDB = DataBaseManager.getInstance().produceCurDB();

		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		Label L1 = new Label("请选择要导出的表");
		L1.setBounds(20, 30, 200, 20);
		this.add(L1);

		lst_table = new List();
		lst_table.setBounds(10, 60, 200, 250);
		ArrayList<String> selectedTable = tables.getTablesName();
		Iterator<String> it_table = selectedTable.iterator();

		while (it_table.hasNext()) {
			String item_table = it_table.next();
			lst_table.add(item_table);

			List lst_field = new List(4, true);
			lst_field.setName(item_table);
			lst_field.setBounds(380, 60, 200, 250);

			ArrayList<String> allField = curDB
					.getAllFieldByTableName(item_table);
			Iterator<String> it_field = allField.iterator();
			while (it_field.hasNext()) {
				String item_field = it_field.next();
				lst_field.add(item_field);
			}
			this.add(lst_field);
			lists.add(lst_field);

		}

		lst_table.addItemListener(new ListenerForTable());
		this.add(lst_table);

		Label L2 = new Label("请选择要导出的字段");
		L2.setBounds(380, 30, 200, 20);
		this.add(L2);

		tf = new TextField("请在此填入表连接条件");
		tf.setBounds(30, 320, 500, 30);
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

	public String getCondition(){
		String text = this.tf.getText();
		if(text!=null && !text.trim().equals("") && !text.trim().equals("请在此填入表连接条件")){
			return " where "+text;
		}else{
			return null;
		}
	}
	public TableCollector getTables() {
		return tables;
	}

	class ListenerForTable implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			String s = lst_table.getSelectedItem();
			Iterator<List> it = lists.iterator();
			while (it.hasNext()) {
				List list = it.next();
				String listName = list.getName();
				if (s.equals(listName)) {
					list.setVisible(true);

				} else {
					list.setVisible(false);
				}
			}

		}
	}

	class ListenerForBtn_pre implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			bo.dispose();
			bz.setVisible(true);

		}
	}

	class ListenerForBtn_next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean next = false;
			ArrayList<String> selectedTable = tables.getTablesName();
			Iterator<String> it_table = selectedTable.iterator();

			while (it_table.hasNext()) {
				String tableName = it_table.next();
				Table table = tables.getTable(tableName);
				Iterator<List> it_list = lists.iterator();
				while (it_list.hasNext()) {
					List list = it_list.next();
					if (list.getName().equals(tableName)) {
						String[] fields = list.getSelectedItems();
						for (int i = 0; i < fields.length; i++) {
							table.fillField(fields[i], "");
							next = true;
						}
						break;
					}
				}
			}
			if (next) {
				bo.setVisible(false);
				new BridgeTwo(bo);
			}

		}
	}

}