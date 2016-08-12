package gao;

public class UserMgr {
	private static UserMgr mgr;
	private static ABDataBase DB;

	private UserMgr() {
	}

	public static UserMgr getInstance() {
		if (mgr == null) {
			mgr = new UserMgr();
			DB = new MySqlDataBase();
		}
		return mgr;
	}

	public User find(String name) {
		if (name == null) {
			return null;
		}
		if (name.trim().equals("")) {

			return null;

		}
//System.out.println(name);
		User user = DB.find(name);

		return user;
	}
	
	public User obscureFind(String name) {
		if (name == null) {
			return null;
		}
		if (name.trim().equals("")) {

			return null;

		}
//System.out.println(name);
		User user = DB.obscureFind(name);

		return user;
	}
//-1第一参数是空串,-2第二个空，1已有，0正常
	public byte check(String userName, String passWord) {
		if (userName == null || userName.trim().equals("")) {
			return -1;
		} else if (passWord == null || passWord.trim().equals("")) {
			return -2;
		}

		User u = find(userName);
		if (u == null) {
			return 0;
		}
		return 1;
	}
//-1第一参数是空串,-2第二个空，1已有，0正常
	public byte add(User user) {
		byte legal = check(user.getName(), user.getPassWord());
		
		if (0 == legal) {
			DB.add(user);
		}
		return legal;
		
	}
//-1第一参数是空串,0无此user，1正常
	public byte delete(User user) {
			if (user.getName() == null || user.getName().trim().equals("")) {
				return -1;
			} 
			User u = find(user.getName());
			if (u == null) {
				return 0;
			}
		
			DB.delete(user);
			return 1;

	}
//-1第一参数是空串,-2第二个空，0无此user，1正常
	public byte update(User user) {
		byte legal = check(user.getName(), user.getPassWord());
		
		if (1 == legal) {
			DB.update(user);

		} 
		return legal;

	}
}
