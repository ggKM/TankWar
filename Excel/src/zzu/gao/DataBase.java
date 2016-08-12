package zzu.gao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

public abstract class DataBase {
	
	 protected static String DBURL = null;
	 protected static Properties prop = null;
	 protected static Connection conn = null;

	public  DataBase(){}
	
	public abstract ArrayList<String> getAllTable();
	public abstract ArrayList<String> getAllFieldByTableName(String tableName);
	public  abstract String DBToExcel(String excelName, String sheetName, String sql);

	public  abstract String excelToDB(String excelName, String sheetName, String sql,
			String[] field_sheet) ;
 }
