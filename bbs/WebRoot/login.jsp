<%@page pageEncoding="gb18030"%>

<%
request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");
	
	
	if (action != null && action.trim().equals("login")) {
		
	String name = request.getParameter("username");
	
	String word = request.getParameter("password");
		if (name == null || !name.trim().equals("admin")) {
			out.println("管理员名字不对");
		} else if (word == null || !word.trim().equals("admin")) {
			out.println("管理员密码不对");
		} else {
			session.setAttribute("logined", "true");
			response.sendRedirect("article.jsp");
		}

	}
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>bbs初级版</title>
<meta http-equiv="content-type" content="text/html; charset=GBK">

</head>
<body>
	<form action="login.jsp" method="post">
		<input type="hidden" name="action" value="login" /> 管理员名：<input
			type="text" name="username" /> 密码：<input type="password"
			name="password" /> <input type="submit" value="登陆" />
	</form>
</body>
</html>
