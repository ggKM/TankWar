<%@ page language="java" import="java.util.*" pageEncoding="gb18030"%>
<%
String sessionLogined = (String)session.getAttribute("logined");
if(sessionLogined==null || !sessionLogined.trim().equals("true")){
 response.sendRedirect("login.jsp");
 return;
}

%>

