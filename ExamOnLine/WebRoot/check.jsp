<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="java.util.*,java.sql.*,java.net.*"%><%@ page
	import="gao.*"%>
<%
	StringBuffer buf = new StringBuffer();
	String userName = URLDecoder.decode(
			request.getParameter("userName"), "utf-8");

	//System.out.println(userName);
	if (userName == null || userName.trim().equals("")) {
		buf.append("�û�������Ϊ�գ�");
	} else {
		if (UserMgr.getInstance().find(userName) != null) {

			buf.append("���û����ѱ�ע��");
		} else {
			buf.append("���û������ã�");
		}

	}

	response.setContentType("text/html;charset=gbk");
	response.setHeader("Cache-Control", "no-store"); //HTTP1.1
	response.setHeader("Pragma", "no-cache"); //HTTP1.0
	response.setDateHeader("Expires", 0); //prevents catching at proxy server

	response.getWriter().write(buf.toString());
%>