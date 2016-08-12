<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="java.util.*,java.sql.*,java.net.*"%><%@ page
	import="gao.*"%>
<%
	StringBuffer buf = new StringBuffer();
	String userName = URLDecoder.decode(
			request.getParameter("userName"), "utf-8");

	//System.out.println(userName);
	if (userName == null || userName.trim().equals("")) {
		buf.append("用户名不能为空！");
	} else {
		if (UserMgr.getInstance().find(userName) != null) {

			buf.append("此用户名已被注册");
		} else {
			buf.append("此用户名可用！");
		}

	}

	response.setContentType("text/html;charset=gbk");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server

	response.getWriter().write(buf.toString());
%>