<%@ page language="java" import="java.util.*,gao.*,java.sql.*"
	pageEncoding="gb18030"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="isOnLine.jsp"%>
<%
	List<Category> categorys = new ArrayList<Category>();
	Category.getAllCategory(categorys);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-��ҳ</title>
<link href="style/share.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<div id="top">
			<p>��ӭ�������߿���ϵͳ</p>
			<span id="top1"><a href="loginOut.jsp">ע��</a> </span><span id="top1"><a
				href="login.jsp">��½</a> </span><span id="top1"><a href="regist.jsp">ע��</a>
			</span> <span>����:<%=user.getName()%></span>
		</div>
		<div id="nav">
			<ul>
				<li><a href="index.jsp" target="_self">��ҳ</a>
				</li>
				

			</ul>
		</div>
		<div id="mid">
			<P>������ࣺ(����������ӿ�ʼ����)</P>
			<%
				for (Category c : categorys) {
			%>
			<p>
				<a href="question.jsp?id=<%=c.getId()%>"><%=c.getCategoryName()%></a>
			</p>
			<%
				}
			%>
		</div>

	</div>
</body>
</html>

