package zzu.gao;

import java.util.HashMap;

public class Table {

	private String tableName = null;
	private HashMap<String,String> fields = null;
	
	public Table(String tableName) {
	
		this.tableName = tableName;
		fields = new  HashMap<String,String> ();
	}
	
	public void fillField(String field,String as){
		fields.put(field, as);
	}
	
	
	
	public String getTableName(){
		return this.tableName;
	}
	
	public int getSize(){
		return this.fields.size();
	}
	public HashMap<String,String> getFields(){
		return this.fields;
	}
	
	
}
