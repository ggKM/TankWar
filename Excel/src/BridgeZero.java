import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import zzu.gao.DataBase;
import zzu.gao.DataBaseManager;
import zzu.gao.Table;
import zzu.gao.TableCollector;



public class BridgeZero extends Frame{

	/**
	 *  version-1
	 */
	private static TableCollector tables = null;
	private BridgeZero bz = null;
	
	List table = null;
	
	Button btn_next = null;
	
	public BridgeZero(){
		super("简易-DAE-2E");
		
        this.bz = this;
		
		DataBase curDB = DataBaseManager.getInstance().produceCurDB();
		Iterator<String> it = null;

		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		Label L1 = new Label("请选择所需要的表");
		L1.setBounds(20, 30, 200, 20);
		this.add(L1);
		
		table = new List(4,true);
		table.setBounds(10,60,580, 280);
		ArrayList<String> allTable = curDB.getAllTable();
		it = allTable.iterator();
		while (it.hasNext()) {
			String item = it.next();
			table.add(item);
		}
		this.add(table);
		
		btn_next = new Button("下一步");
		btn_next.setBounds(500, 360, 70, 20);
		btn_next.addActionListener(new ListenerForBtn_next());
		this.add(btn_next );
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		this.setResizable(false);
		this.setVisible(true);
	}
	
	public TableCollector getTables(){
		return tables;
	}
	class ListenerForBtn_next implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] items = table.getSelectedItems();
			int count = items.length;
			if (count > 0) {
				tables = new TableCollector();
				for (int i = 0; i < count; i++) {
					Table table = new Table(items[i]);
					tables.fillTable(table);
				}
				
				bz.setVisible(false);
				new BridgeOne(bz);
			}
		}
	}
}
