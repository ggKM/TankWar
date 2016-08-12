package zzu.gao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelRW {

	private static HSSFWorkbook wb = null;
	private static String filePath = null;

	private ExcelRW() {

	}

	public static ExcelRW getInstance(String fp) {
		filePath = fp;
		if (filePath != null && (filePath.trim().endsWith(".xls")||filePath.trim().endsWith(".et"))) {
			try {

				FileInputStream fin = new FileInputStream(filePath);
				POIFSFileSystem POIFfin = new POIFSFileSystem(fin);
				wb = new HSSFWorkbook(POIFfin);
			} catch (FileNotFoundException e) {
				System.out.println("指定的文件不存在或已被占用！");
				// e.printStackTrace();
				return null;
			} catch (IOException e) {
				System.out.println("文件io出错！！");
				// e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return new ExcelRW();
	}

	public String[] getAllWookbook() {

		int count = wb.getNumberOfSheets();
		String[] names = new String[count];
		for (int i = 0; i < count; i++) {
			names[i] = wb.getSheetAt(i).getSheetName();
			// System.out.println(names[i]);
		}

		return names;
	}

	// 返回实际存在的行数,-1表示不存在此工作表或参数不对
	public int getTotalRownum(String sheetName) {
		if (!this.isValid(sheetName)) {
			return -1;
		}
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (!this.isValid(sheet)) {
			return -1;
		}
		int i = 0;
		HSSFRow row = sheet.getRow(i);
		
		while(row!=null){
			i++;
			row = sheet.getRow(i);
		}
		return i;
	}

	// 返回总共有效列数,0表示没有一列,-1表示不存在此工作表
	public int getTotalColnum(String sheetName) {

		if (!this.isValid(sheetName)) {
			return -1;
		}
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return -1;
		}
		HSSFRow row = null;

		int i = 0;

		row = sheet.getRow(0);
		if (row == null) {
			return 0;
		}

		while (row.getCell(i) != null) {
			i++;
		}
		return i;

	}

	// 0初始化不成功，没有此行，不支持此行值得格式
	/*
	 * private int getRownumByRowname(HSSFSheet sheet, String rowName) {
	 * 
	 * int rownum = 0; if (sheet == null) { return rownum; } HSSFRow row = null;
	 * HSSFCell cell = null;
	 * 
	 * int i = 0; String value = null;
	 * 
	 * for (i = 0; i < sheet.getPhysicalNumberOfRows(); i++) { row =
	 * sheet.getRow(i); cell = row.getCell(0);
	 * 
	 * switch (cell.getCellType()) {
	 * 
	 * 
	 * case HSSFCell.CELL_TYPE_FORMULA: value = "FORMULA value=" +
	 * cell.getCellFormula(); break;
	 * 
	 * 
	 * case HSSFCell.CELL_TYPE_NUMERIC: int a = (int)
	 * cell.getNumericCellValue(); value = String.valueOf(a); break;
	 * 
	 * case HSSFCell.CELL_TYPE_STRING: value = cell.getStringCellValue(); break;
	 * 
	 * default:
	 * 
	 * return rownum; }
	 * 
	 * if (value != null && value.trim().equals(rowName)) { rownum = i; break;
	 * 
	 * } } return rownum; }
	 */
	// 若没有此列或者不支持此列值得格式返回总列数，-1表示无此工作表
	public int getColnumByColname(String sheetName, String colName) {

		HSSFSheet sheet = wb.getSheet(sheetName);
		int colnum = 0;
		if (sheet == null) {
			return -1;
		}
		if (!this.isValid(colName)) {
			return colnum;
		}

		HSSFRow row = null;
		HSSFCell cell = null;

		int i = 0;
		String value = null;

		row = sheet.getRow(0);
		if (row == null) {
			return colnum;
		}

		while ((cell = row.getCell(i)) != null) {

			switch (cell.getCellType()) {

			case HSSFCell.CELL_TYPE_NUMERIC:
				int a = (int) cell.getNumericCellValue();
				value = String.valueOf(a);
				break;

			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;

			default:

				return colnum;
			}

			if (value != null && value.trim().equals(colName)) {
				colnum = i;
				break;

			}

			i++;
		}
		return colnum;

	}

	public String[] readCell(String sheetName, int rownum, int colnum) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		HSSFRow row = sheet.getRow(rownum);
		HSSFCell cell = row.getCell(colnum);
		String[] value = new String[2];

		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_FORMULA:
			value[0] = "string";
			value[1] = cell.getCellFormula();
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			value[0] = "numeric";
			double a = cell.getNumericCellValue();
			value[1] = String.valueOf(a);
			break;

		case HSSFCell.CELL_TYPE_STRING:
			value[0] = "string";
			value[1] = cell.getStringCellValue();
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			value[0] = "string";
			value[1] = " ";
			break;

		case HSSFCell.CELL_TYPE_ERROR:
			value[0] = "string";
			value[1] = "error";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value[0] = "string";
			value[1] = Boolean.toString(cell.getBooleanCellValue());

			break;
		default:
			value[0] = "no";
			value[1] = "不支持";
		}
		return value;
	}

	/*
	 * public String readCell(String sheetName, String rowName, String colName)
	 * { if (sheetName == null || rowName == null || colName == null ||
	 * sheetName.trim().equals("") || rowName.trim().equals("") ||
	 * colName.trim().equals("")) { return "参数不正确"; } HSSFSheet sheet =
	 * wb.getSheet(sheetName); if (sheet == null) { return sheetName + "不存在！"; }
	 * HSSFRow row = null; HSSFCell cell = null; int rownum = -1, column = -1;
	 * String value = null;
	 * 
	 * column = this.getColnumByColname(sheet, colName); if (-1 == column) {
	 * return "不存在此列名！"; } else if (-2 == column) { return "不支持此列数据的格式"; }
	 * 
	 * rownum = this.getRownumByRowname(sheet, rowName); if (-1 == rownum) {
	 * return "不存在此行名！"; } else if (-2 == rownum) { return "不支持此行数据的格式"; }
	 * 
	 * row = sheet.getRow(rownum); cell = row.getCell(column);
	 * 
	 * if (cell == null) { value = "blank"; return value; }
	 * 
	 * switch (cell.getCellType()) {
	 * 
	 * case HSSFCell.CELL_TYPE_FORMULA: value = cell.getCellFormula();
	 * 
	 * // System.out.println(cell.getCellFormula()); break;
	 * 
	 * case HSSFCell.CELL_TYPE_NUMERIC: int a = (int)
	 * cell.getNumericCellValue(); value = String.valueOf(a);
	 * 
	 * // System.out.println(cell.getNumericCellValue()); break;
	 * 
	 * case HSSFCell.CELL_TYPE_STRING: value = cell.getStringCellValue();
	 * 
	 * // System.out.println(cell.getStringCellValue()); break;
	 * 
	 * case HSSFCell.CELL_TYPE_BLANK: // System.out.println("blank-"); value =
	 * "blank"; break;
	 * 
	 * case HSSFCell.CELL_TYPE_ERROR: // System.out.println("error-"); value =
	 * "error"; break; case HSSFCell.CELL_TYPE_BOOLEAN: //
	 * System.out.println("boolean-"); value =
	 * Boolean.toString(cell.getBooleanCellValue());
	 * 
	 * break; default: value = "不支持此行某些数据格式！"; }
	 * 
	 * return value;
	 * 
	 * }
	 */
	public String[] readRow(String sheetName, int rownum) {

		String[] firstRow = null;
		if (rownum < 0) {
			return firstRow;
		}
		if (sheetName == null || sheetName.trim().equals("")) {
			return firstRow;
		}
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return firstRow;
		}
		HSSFRow row = null;
		HSSFCell cell = null;

		row = sheet.getRow(rownum);
		if (row == null) {
			return firstRow;
		}

		int totalColnum = this.getTotalColnum(sheetName);
		firstRow = new String[totalColnum];

		for (int i = 0; i < totalColnum; i++) {

			cell = row.getCell(i);
			if (cell == null) {
				firstRow[i] = "blank";
			} else {
				switch (cell.getCellType()) {

				case HSSFCell.CELL_TYPE_FORMULA:
					firstRow[i] = cell.getCellFormula();

					break;

				case HSSFCell.CELL_TYPE_NUMERIC:
					int a = (int) cell.getNumericCellValue();
					firstRow[i] = (String.valueOf(a));
					break;

				case HSSFCell.CELL_TYPE_STRING:
					firstRow[i] = cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_BLANK:
					break;

				case HSSFCell.CELL_TYPE_ERROR:
					firstRow[i] = "error";
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					firstRow[i] = Boolean.toString(cell.getBooleanCellValue());
					break;
				default:
					firstRow[i] = "不支持";
				}
			}
		}
		return firstRow;

	}

	/*
	 * public String readRow(String sheetName, String rowName) {
	 * 
	 * if (sheetName == null || sheetName.trim().equals("") || rowName == null
	 * || rowName.trim().equals("")) { return "参数不正确"; } HSSFSheet sheet =
	 * wb.getSheet(sheetName); if (sheet == null) { return sheetName + "不存在！"; }
	 * HSSFRow row = null; HSSFCell cell = null; int rownum = -1; StringBuffer
	 * value = new StringBuffer();
	 * 
	 * rownum = this.getRownumByRowname(sheet, rowName); if (-1 == rownum) {
	 * return "不存在此行名！"; } else if (-2 == rownum) { return "不支持此行数据的格式"; }
	 * 
	 * row = sheet.getRow(rownum); int totalColumn =
	 * this.getTotalColnum(sheetName);
	 * 
	 * for (int i = 0; i < totalColumn; i++) {
	 * 
	 * cell = row.getCell(i); if (cell == null) { value.append("blank-"); } else
	 * { switch (cell.getCellType()) {
	 * 
	 * case HSSFCell.CELL_TYPE_FORMULA: value.append(cell.getCellFormula());
	 * value.append("-"); // System.out.println(cell.getCellFormula()); //
	 * break;
	 * 
	 * case HSSFCell.CELL_TYPE_NUMERIC: int a = (int)
	 * cell.getNumericCellValue(); value.append(String.valueOf(a));
	 * value.append("-"); // System.out.println(cell.getNumericCellValue());
	 * break;
	 * 
	 * case HSSFCell.CELL_TYPE_STRING: value.append(cell.getStringCellValue());
	 * value.append("-"); // System.out.println(cell.getStringCellValue());
	 * break;
	 * 
	 * case HSSFCell.CELL_TYPE_BLANK: // System.out.println("blank-");
	 * value.append("blank-"); break;
	 * 
	 * case HSSFCell.CELL_TYPE_ERROR: // System.out.println("error-");
	 * value.append("error-"); break; case HSSFCell.CELL_TYPE_BOOLEAN: //
	 * System.out.println("boolean-");
	 * value.append(Boolean.toString(cell.getBooleanCellValue()));
	 * value.append("-"); break; default: return "不支持此行某些数据格式！"; } } } return
	 * value.substring(0, value.length() - 1);
	 * 
	 * }
	 */
	/*
	 * // 通过指定的列名行名插入一个单元格的数据 public String writeCell(String sheetName, String
	 * rowName, String colName, String writeIn) {
	 * 
	 * if (sheetName == null || rowName == null || colName == null ||
	 * sheetName.trim().equals("") || rowName.trim().equals("") ||
	 * colName.trim().equals("")) { return "参数不正确"; } HSSFSheet sheet =
	 * wb.getSheet(sheetName); if (sheet == null) { return sheetName + "不存在！"; }
	 * HSSFRow row = null; HSSFCell cell = null; int rownum = -1, column = -1;
	 * String value = null;
	 * 
	 * column = this.getColnumByColname(sheet, colName); if (-1 == column) {
	 * return "不存在此列名！"; } else if (-2 == column) { return "不支持此列数据的格式"; }
	 * 
	 * rownum = this.getRownumByRowname(sheet, rowName); if (-1 == rownum) {
	 * return "不存在此行名！"; } else if (-2 == rownum) { return "不支持此行数据的格式"; }
	 * 
	 * row = sheet.getRow(rownum); cell = row.getCell(column); if (cell == null)
	 * { cell = row.createCell(column); cell.setCellValue(writeIn); } else {
	 * cell.setCellValue(writeIn); } return writeIn;
	 * 
	 * }
	 */
	public boolean isExistSheet(String sheetName) {
		if (!this.isValid(sheetName)) {
			return false;
		}
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return false;
		}
		return true;
	}

	public boolean isExistCol(String sheetName, String colName) {
		if (!this.isValid(sheetName) || !this.isValid(colName)) {
			return false;
		}

		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return false;
		}
		HSSFRow row = null;
		HSSFCell cell = null;

		int i = 0;
		String value = null;

		row = sheet.getRow(0);
		if (row == null) {
			row = sheet.createRow(0);
		}

		while ((cell = row.getCell(i)) != null
				&& i < this.getTotalColnum(sheetName)) {

			switch (cell.getCellType()) {

			case HSSFCell.CELL_TYPE_NUMERIC:
				int a = (int) cell.getNumericCellValue();
				value = String.valueOf(a);
				break;

			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;

			default:

				return false;
			}

			if (value != null && value.trim().equals(colName)) {

				return true;

			}
			i++;
		}
		return false;
	}

	public void createCol(String sheetName, String colName) {
		if (!this.isValid(sheetName) || !this.isValid(colName)) {
			return;
		}

		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return;
		}
		if (!this.isExistCol(sheetName, colName)) {
			int col = this.getTotalColnum(sheetName);
			HSSFRow row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}
			HSSFCell cell = row.createCell(col);
			cell.setCellValue(colName);
		}
	}

	public int[] findLocation(String sheetName, String colName) {

		int[] location = new int[2];
		location[0] = -1;
		location[1] = -1;

		if (!this.isValid(sheetName) || !this.isValid(colName)) {
			return location;
		}

		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return location;
		}

		int colnum = 0, rownum = 0;

		this.createCol(sheetName, colName);

		colnum = this.getColnumByColname(sheetName, colName);

		rownum = this.getTotalRownum(sheetName);
		location[0] = rownum;
		location[1] = colnum;
		return location;
	}

	// 通过指定位置插入数据
	public Object writeCell(String sheetName, int[] location, String colName,
			Object writeIn, String dataType) {

		if (!this.isValid(sheetName) || !this.isValid(colName)) {
			return "sheetName is invalid";
		}
		if (dataType == null) {
			dataType = "string";
		}

		HSSFSheet sheet = wb.getSheet(sheetName);

		if (sheet == null) {

			return "sheetName is not exist";
		}

		HSSFRow row = sheet.getRow(location[0]);
		if (row == null) {
			row = sheet.createRow(location[0]);
		}
		HSSFCell cell = row.getCell(location[1]);

		if (cell == null) {
			cell = row.createCell(location[1]);
		}

		String javaType = Judger.judgeType(dataType);

		if (javaType.equalsIgnoreCase("Integer")) {
			if (writeIn == null) {
				writeIn = 0;
			}
			cell.setCellValue((Integer) writeIn);

		} else if (javaType.equalsIgnoreCase("Double")) {
			if (writeIn == null) {
				writeIn = 0;
			}
			cell.setCellValue((Double) writeIn);
		} else {
			if (writeIn == null) {
				writeIn = "";
			}
			cell.setCellValue(new HSSFRichTextString(writeIn.toString()));
		}

		return writeIn;

	}

	private boolean isValid(String string) {
		if (string != null && !string.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValid(Object object) {
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}

	public void saveAs() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date d = new Date();
		String fileName = filePath.substring(0, filePath.length() - 4);
		filePath = fileName + "_" + sdf.format(d) + "_old.xls";

		try {

			FileOutputStream fout = new FileOutputStream(filePath);

			wb.write(fout);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void close() {

		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			wb.write(fout);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
