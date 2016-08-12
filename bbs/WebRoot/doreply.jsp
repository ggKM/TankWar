<%@ page language="java" pageEncoding="GB18030"%><%@page import="gao.*,java.util.ArrayList,java.util.*,java.sql.*" %>
<%
	request.setCharacterEncoding("GBK");
	int pid = Integer.parseInt(request.getParameter("pid"));
	int rootId =  Integer.parseInt(request.getParameter("rootId"));
	String title = request.getParameter("title");
	String cont = request.getParameter("cont");
	
	String sql = "insert into article values (null,?,?,?,?,now(),0)";
	Connection conn = DataBase.getConn();
	
	boolean autoCommit = conn.getAutoCommit();
	conn.setAutoCommit(false);
	
	PreparedStatement pst = DataBase.getPST(conn,sql);
	pst.setInt(1, pid);
	pst.setInt(2,rootId);
	pst.setString(3, title);
	pst.setString(4, cont);
	pst.executeUpdate();
	pst.execute("update article set isleaf = 1 where id = "+pid);
	conn.commit();
	conn.setAutoCommit(autoCommit);
	DataBase.close(pst);DataBase.close(conn);
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>reply success</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    回复成功！
    回复内容为：title:<%= title %> <br>
    content:<%= cont %>
  </body>
</html>
