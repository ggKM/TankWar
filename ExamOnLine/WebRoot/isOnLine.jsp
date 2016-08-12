<%@ page language="java" import="java.util.*,gao.*"
	pageEncoding="GB18030"%>

<%
	boolean isOn = false;
	User user = null;
	String name = (String) session.getAttribute("userName");
	String logined = (String) session.getAttribute("logined");
	if (name != null && !name.trim().equals("")) {
		if (logined != null && logined.trim().equals("true")) {
			isOn = true;
			String userName = (String) session.getAttribute("userName");
			user = new User(userName);
		} else {
			user = new User("anonym");
		}
	} else {
		user = new User("anonym");
	}
%>
