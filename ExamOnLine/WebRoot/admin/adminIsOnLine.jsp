<%@ page language="java" import="java.util.*,gao.*"
	pageEncoding="GB18030"%>

<%
	String adminName = config.getServletContext().getInitParameter(
			"adminName");
	User admin = null;
	String name = (String) session.getAttribute("adminName");
	String logined = (String) session.getAttribute("logined");
	if (name != null && name.trim().equals(adminName)
			&& logined != null && logined.trim().equals("true")) {

		admin = new User(adminName);

	} else {

		response.sendRedirect("adminLogin.jsp");
		return;
	}
%>
