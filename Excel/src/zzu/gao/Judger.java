package zzu.gao;

public class Judger {

	public static String judgeType(String dataType){
		if (dataType.equals("int") || dataType.equals("tinyint")
				|| dataType.equals("smallint")
				|| dataType.equals("mediumint")
				|| dataType.equals("integer") || dataType.equals("integer")
				|| dataType.equals("integer")) {
			
			return "Integer";

		} else if (dataType.equals("float") || dataType.equals("double")
				|| dataType.equals("decimal") || dataType.equals("bigint")) {
			
			return "Double";
		} else {
			
			return "String";
		}
	
	}
}
