<%@ page language="java" import="java.util.*,gao.*" pageEncoding="GB18030"%>
<%
request.setCharacterEncoding("GBK");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    
    String userName = (String) session.getAttribute("userName");
	String logined = (String) session.getAttribute("logined");
	if(userName!=null && !userName.trim().equals("")){
		if(logined!=null && logined.trim().equals("true")){
			session.setAttribute("logined", "false");
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}else {
		response.sendRedirect("index.jsp");
	}
 %>
