package zzu.gao;

import java.io.IOException;
import java.util.Properties;
public class DataBaseManager {

	private static DataBaseManager DBManager = null;
	private static String DBTYPE = null;
	private static Properties prop = null;
	private static DataBase curDataBase = null;
	
	private String myPackageName = "zzu.gao.";

	private DataBaseManager() {

	}

	public static DataBaseManager getInstance() {
		if (DBManager == null) {

			DBManager = new DataBaseManager();

		}
		return DBManager;
	}

	@SuppressWarnings("unchecked")
	public DataBase produceCurDB() {
		if (curDataBase == null) {
			if (DBTYPE == null) {
				prop = new Properties();
				try {
					prop.load(this.getClass().getClassLoader()
							.getResourceAsStream("config/DB.properties"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				DBTYPE = prop.getProperty("DBTYPE");
				// System.out.println(DBTYPE);
			}

			Class<DataBase> c = null;

			try {
				c = (Class<DataBase>) Class.forName(myPackageName+DBTYPE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("DataBaseManager--classnotfound");
			}

			curDataBase = null;

			try {
				curDataBase = c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.out.println("DataBaseManager--Instantiation");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.out.println("DataBaseManager--IllegalAccess");
			}
		}
		return curDataBase;
	}
}
