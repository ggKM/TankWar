package zzu.gao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TableCollector {

	private static ArrayList<Table> selectedTable = null;

	public TableCollector() {
		selectedTable = new ArrayList<Table>();

	}

	public void fillTable(Table table){
		String tableName = table.getTableName();
		Iterator<Table> it = selectedTable.iterator();
		Table t = null;
		while(it.hasNext()){
			t = it.next();
			String name = t.getTableName();
			if(name.equals(tableName)){
				return;
			}
		}
		selectedTable.add(table);
		
	}
	public void fillTable(String tableName,String key,String value){
		Iterator<Table> it = selectedTable.iterator();
		Table table = null;
		while(it.hasNext()){
			table = it.next();
			String name = table.getTableName();
			if(name.equals(tableName)){
				table.fillField(key, value);
				return;
			}
		}
		
		table = new Table(tableName);
		table.fillField(key, value);
		selectedTable.add(table);
	}
	
	/*public void delTable(Table table) {
		String tableName = table.getTableName();
		Iterator<Table> it = selectedTable.iterator();
		Table t = null;
		while(it.hasNext()){
			t = it.next();
			String name = t.getTableName();
			if(name.equals(tableName)){
				selectedTable.remove(table);
			}
		}
		
	}*/
/*
	public boolean existTable(String name) {
		Iterator<Table> it = selectedTable.iterator();
		while (it.hasNext()) {
			Table table = it.next();
			if (table.getTableName().equals(name)) {
				return true;
			}
		}
		return false;
	}
*/
	public int getTableNum(){
		return selectedTable.size();
	}
	public Table getTable(String name){
		Iterator<Table> it = selectedTable.iterator();
		while (it.hasNext()) {
			Table table = it.next();
			if (table.getTableName().equals(name)) {
				return table;
			}
		}
		return null;
	}
	public ArrayList<String> getTablesName() {
		ArrayList<String> tablesName = new ArrayList<String>();
		Iterator<Table> it = selectedTable.iterator();
		while (it.hasNext()) {
			Table table = it.next();
			tablesName.add(table.getTableName());
		}
		return tablesName;
	}
	
	public String getSql(String where){
		ArrayList<String> selectedTable = this.getTablesName();
		Iterator<String> it_table = selectedTable.iterator();
		
		String from = new String();
	
		String select = new String();
		
		while (it_table.hasNext()) {
			String tableName = it_table.next();
			from = from.concat(tableName);
			from = from.concat(",");
			
			Table table = this.getTable(tableName);
			HashMap<String,String> fields = table.getFields(); 
		
			Set<String> keys = fields.keySet();
			Iterator<String> it = keys.iterator();
			
			while (it.hasNext()) {
				String key = it.next();
				String value = fields.get(key);
				if(value==null || value.trim().equals("")){
					value = tableName+"."+key;
				}
				select = select.concat(tableName+"."+key+" as "+"'"+value+"'");
				select = select.concat(",");
			}
		}
		from = from.substring(0,from.length()-1);
		select = select.substring(0,select.length()-1);
		String sql = "select "+select+" from "+from +" "+ where ;
		return sql;
	}
	
	public String getInsertStr(String[] field_sheet,String tableName){
		ArrayList<String> tablesName = this.getTablesName();
		Iterator<String> it = tablesName.iterator();
		String sheetName = null;
		HashMap<String, String> fields = null;
		Table table = null;
		while (it.hasNext()) {
			String s = it.next();
			table = this.getTable(s);
			sheetName = table.getTableName();
			fields = table.getFields();
		}
		Set<String> keys = fields.keySet();
		Iterator<String> it_key = keys.iterator();
		String sql = "insert into " + tableName + "(";
		int i = 0;
		while (it_key.hasNext()) {
			String key = it_key.next();
			sql = sql + fields.get(key) + ",";
			field_sheet[i] = key;
			i++;
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ") values (";
		for (int count = 0; count < field_sheet.length; count++) {
			sql = sql + "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";
		
		return sql;
	}
}
