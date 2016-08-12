import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


import zzu.gao.DataBase;
import zzu.gao.DataBaseManager;
import zzu.gao.ExcelRW;
import zzu.gao.Table;
import zzu.gao.TableCollector;

public class OutTwo extends Frame {

	private OutOne oo = null;
	private OutTwo ot = null;

	private String tableName = null;
private String excelPath = null;

	TextArea ta = null;
	Button btn_next = null;
	Button btn_pre = null;
	TableCollector tables = null;

	public OutTwo(OutOne oo) {
		super("简易-DAE-2D");

		this.oo = oo;
		tables = oo.getTables();
		this.tableName = oo.getTableName();
		this.ot = this;
		this.excelPath = oo.getExcelPath();

		this.setLayout(null);
		this.setFont(new Font("微软雅体", 0, 18));
		setBounds(400, 100, 600, 400);
		setBackground(new Color(171, 205, 239));

		Label L1 = new Label("执行命令及结果:");
		L1.setBounds(150, 100, 200, 20);
		this.add(L1);
		
		ta = new TextArea();
		ta.setEditable(false);
		ta.setBounds(150, 150, 300, 100);
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

	class ListenerForBtn_pre implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ot.dispose();
			oo.setVisible(true);
		}
		
	}
	
	class ListenerForBtn_next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> tablesName = tables.getTablesName();
			Iterator<String> it = tablesName.iterator();
			String sheetName = null;
			String[] field_sheet = null;
			Table table = null;
			while (it.hasNext()) {
				String s = it.next();
				table = tables.getTable(s);
				sheetName = table.getTableName();
				field_sheet = new String[table.getSize()];
			}
			String sql = tables.getInsertStr(field_sheet, ot.tableName);
			DataBase curDB = DataBaseManager.getInstance()
					.produceCurDB();
			ta.setText(curDB.excelToDB(excelPath, sheetName, sql, field_sheet));
			
		}

	}

}
