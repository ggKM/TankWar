<%@page pageEncoding="gb18030"%>

<%
request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");
	
	
	if (action != null && action.trim().equals("login")) {
		
	String name = request.getParameter("username");
	
	String word = request.getParameter("password");
		if (name == null || !name.trim().equals("admin")) {
			out.println("����Ա���ֲ���");
		} else if (word == null || !word.trim().equals("admin")) {
			out.println("����Ա���벻��");
		} else {
			session.setAttribute("logined", "true");
			response.sendRedirect("article.jsp");
		}

	}
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>bbs������</title>
<meta http-equiv="content-type" content="text/html; charset=GBK">

</head>
<body>
	<form action="login.jsp" method="post">
		<input type="hidden" name="action" value="login" /> ����Ա����<input
			type="text" name="username" /> ���룺<input type="password"
			name="password" /> <input type="submit" value="��½" />
	</form>
</body>
</html>
