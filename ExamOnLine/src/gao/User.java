package gao;

public class User {
	public User(int id, String name, String passWord) {
		this.id = id;
		this.name = name;
		this.passWord = passWord;
	}
	private int id;
	private String name;
	private String passWord;
	
	
	public User(String name) {
		
		this.name = name;
	}
	public User(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
	public User( String name, String passWord) {
	
		this.name = name;
		this.passWord = passWord;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
