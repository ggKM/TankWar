package gao;

import java.util.List;

public class Category {
	
	public Category(int id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}
	private int id;
	private String categoryName;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public static void getAllCategory(List<Category> categorys) {
		ABDataBase DB = new MySqlDataBase();
	    DB.getAllCategory(categorys);
	}
}
