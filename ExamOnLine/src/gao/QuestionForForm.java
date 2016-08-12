package gao;

public class QuestionForForm {
	public QuestionForForm(int id, int categoryId, String cont, String optionA,
			String optionB, String optionC, String optionD, String answer) {
		this.questionId = id;
		this.categoryId = categoryId;
		this.cont = cont;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
	}
	public QuestionForForm() {
		
	}
	
	private int questionId;
	private int categoryId;
	private String cont;
    private String	optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean validate() {
		ABDataBase DB = new MySqlDataBase();
		int[] allId = DB.getAllCategoryId();
		if(allId!=null && allId.length>0){
			int categoryId = getCategoryId();
			boolean b = false;
			for(int k : allId){
				if(k==categoryId){
					b = true;
					break;
				}
			}
			if(!b){
				return false;
			}
		}else{
			return false;
		}
		
		if(getCont()==null || getCont().trim().equals("")){
			return false;
		}
		if(getOptionA()==null || getOptionA().trim().equals("")){
			return false;
		}
		if(getOptionB()==null || getOptionB().trim().equals("")){
			return false;
		}
		if(getOptionC()==null || getOptionC().trim().equals("")){
			return false;
		}
		if(getOptionD()==null || getOptionD().trim().equals("")){
			return false;
		}
		if(getAnswer()==null || getAnswer().trim().equals("")){
			return false;
		}
		
		return true;
		
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int id) {
		this.questionId = id;
	}
}
