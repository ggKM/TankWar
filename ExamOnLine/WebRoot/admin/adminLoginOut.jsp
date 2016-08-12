<%@ page language="java" import="java.util.*,gao.*" pageEncoding="GB18030"%>

<%
    
    String adminName = (String) session.getAttribute("adminName");
	String logined = (String) session.getAttribute("logined");

	if(adminName!=null && !adminName.trim().equals("")){
	
		if(logined!=null && logined.trim().equals("true")){
			session.setAttribute("logined", "false");
			response.sendRedirect("adminLogin.jsp");

		}else {
			response.sendRedirect("adminLogin.jsp");
		}
	}else {
		response.sendRedirect("adminLogin.jsp");
	}
 %>
