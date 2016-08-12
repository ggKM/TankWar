package zzu.gao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public final class Mysql extends DataBase {

	static {
		System.out.println("MySql Load");
	}

	public Mysql() {
		super();
		if (DBURL == null) {
			prop = new Properties();
			try {
				prop.load(this.getClass().getClassLoader()
						.getResourceAsStream("config/DB.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			DBURL = prop.getProperty("DBURL");
		}
	}

	private Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://" + DBURL);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	private void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

	public ArrayList<String> getAllTable() {
		Connection conn = getConn();
		ArrayList<String> allTable = new ArrayList<String>();
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, null, new String[] {
					"TABLE", "VIEW" });
			while (rs.next()) {
				// System.out.println(rs.getString(3));
				allTable.add(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.closeConn(conn);
		}
		return allTable;
	}

	public ArrayList<String> getAllFieldByTableName(String tableName) {
		Connection conn = getConn();
		ArrayList<String> allField = new ArrayList<String>();
		String sql = "select * from " + tableName;

		PreparedStatement preStmt = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;

		try {
			preStmt = conn.prepareStatement(sql);
			if (preStmt != null) {

				rs = preStmt.executeQuery();
				if (rs != null) {
					metaData = rs.getMetaData();

					if (metaData != null) {
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
							String colName = metaData.getColumnName(i);
							allField.add(colName);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.closeConn(conn);
		}
		return allField;
	}

	public String DBToExcel(String excelName, String sheetName, String sql) {

		Connection conn = this.getConn();
		if (conn == null) {
			return "conn--error!";
		}

		PreparedStatement preStmt = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		try {
			preStmt = conn.prepareStatement(sql);
			if (preStmt == null) {
				return "preStmt--error!";
			}
			rs = preStmt.executeQuery();
			if (rs == null) {
				return "rs--error!";
			}
			metaData = rs.getMetaData();
			if (metaData == null) {
				return "metaData--error!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "SQLexception-1!";
		}

		ExcelRW excel = ExcelRW.getInstance(excelName);
		if (excel == null) {
			return "excel--error!";
		}

		int beginRownum = 0;
		int only = 1;

		try {

			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String dataType = metaData.getColumnTypeName(i).toLowerCase();
				// System.out.println(dataType);
				String colName = metaData.getColumnLabel(i);
				// System.out.println(colName);
				int[] location = excel.findLocation(sheetName, colName);
				if (1 == only) {
					beginRownum = location[0];
					only++;
				}
				location[0] = beginRownum;
				// System.out.println(location[0]+";"+location[1]);
				while (rs.next()) {
					Object object = rs.getObject(i);
					excel.writeCell(sheetName, location, colName, object,
							dataType);
					location[0]++;
				}
				rs.absolute(1);
				rs.previous();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "SQLexception-2!";
		} finally {
			if (excel != null) {
				excel.close();
				excel = null;
			}
			this.closeConn(conn);
		}
		return "已完成!";

	}

	@Override
	public String excelToDB(String excelName, String sheetName, String sql,
			String[] field_sheet) {
		String re = new String();
		
		Connection conn = this.getConn();
		if (conn == null) {
			return "conn--error!";
		}

		PreparedStatement preStmt = null;
		try {
			preStmt = conn.prepareStatement(sql);
			if (preStmt == null) {
				return "preStmt--error!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "SQLexception-1!";
		}

		ExcelRW excel = ExcelRW.getInstance(excelName);
		if (excel == null) {
			return "excel--error!";
		}

		int totalRownum = excel.getTotalRownum(sheetName);
		int colnum = 0;

		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
			return "失败";

		}
		try {
			for (int rownum = 1; rownum < totalRownum; rownum++) {
				for (int i = 0; i < field_sheet.length; i++) {
					colnum = excel
							.getColnumByColname(sheetName, field_sheet[i]);
					String[] str = excel.readCell(sheetName, rownum, colnum);

					if (str[0].equals("numeric")) {

						preStmt.setDouble(i + 1, Double.parseDouble(str[1]));

					} else {
						preStmt.setString(i + 1, str[1]);
					}

				}
//System.out.println(preStmt.toString());
				re = re+preStmt.toString()+"\n";
				
				preStmt.executeUpdate();
			}
		} catch (NumberFormatException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
				this.closeConn(conn);
				excel.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return re + "已成功导入到数据库\n";

	}

}
