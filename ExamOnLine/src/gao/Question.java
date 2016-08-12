package gao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class Question {
	public Question(String cont) {
		this.cont = cont;
	}
	public Question() {
	}
	public Question(int id) {
		this.id = id;
	}

	private int id;
	private int categoryId;
	private String cont;
    private String	optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public void initFromRS(ResultSet rs){
		try {
			this.setId(rs.getInt("id"));
			this.setCategoryId(rs.getInt("categoryId"));
			this.setCont(rs.getString("cont"));
			this.setOptionA(rs.getString("optionA"));
			this.setOptionB(rs.getString("optionB"));
			this.setOptionC(rs.getString("optionC"));
			this.setOptionD(rs.getString("optionD"));
			this.setAnswer(rs.getString("answer"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void initFromForm(QuestionForForm q){
		this.setId(q.getQuestionId());
		this.setCategoryId(q.getCategoryId());
		this.setCont(q.getCont());
		this.setOptionA(q.getOptionA());
		this.setOptionB(q.getOptionB());
		this.setOptionC(q.getOptionC());
		this.setOptionD(q.getOptionD());
		this.setAnswer(q.getAnswer());
	}
	
    public static int sepGetQuestions(List<Question> questions,int id,int pageSize,int pageNo){
    	ABDataBase DB = new MySqlDataBase();
    	return DB.sepGetQuestions(questions, id, pageSize, pageNo);
		
	}
    
    public static void add(Question q){
    	ABDataBase DB = new MySqlDataBase();
    	DB.add(q);
    }
    
    public static void delete(Question q){
    	if(q.getId()!=0){
  
    	ABDataBase DB = new MySqlDataBase();
    	DB.delete(q);
    	}
    }
    
    public static int update(Question q){
    	//System.out.println(q.getId());
    	if(q.getId()!=0){
  
    	ABDataBase DB = new MySqlDataBase();
    	int legal = DB.update(q);
    	return legal;
    	}
    	return -3;
    }
    
    public static List<Question> find(String cont){
    	//System.out.println(q.getId());
    	if(cont!=null && !cont.trim().equals("")){
  
    	ABDataBase DB = new MySqlDataBase();
    	 List<Question> qs = DB.findQuestion(cont);
    	return qs;
    	}
    	return null;
    }
    
    public static boolean isRight(int questionId,String answer){
    	
    	String questionAnswer = getAnswerByQuestionId(questionId);
    	if(questionAnswer!=null && questionAnswer.equals(answer)){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static String getAnswerByQuestionId(int questionId){  
	    ABDataBase DB = new MySqlDataBase();
		String questionAnswer = DB.getAnswerByQuestionId(questionId);
		return questionAnswer;
    }
    
}
