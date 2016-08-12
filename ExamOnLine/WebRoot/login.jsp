<%@ page language="java" import="java.util.*,gao.*"
	pageEncoding="GB18030"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="isOnLine.jsp"%>

<%
	String loginStr = request.getParameter("login");
	String warnStr1 = new String();
	String warnStr2 = new String();

	if (loginStr != null && loginStr.equals("login")) {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		byte legal = UserMgr.getInstance().check(userName, passWord);
		if (-1 == legal) {
			warnStr1 += "�û�������Ϊ�գ�";
			user = new User("anonym");
		} else if (-2 == legal) {
			warnStr2 += "���벻��Ϊ�գ�";
			user = new User("anonym");
		} else if (0 == legal) {
			user = new User("anonym");
			warnStr1 += "�û��������ڣ�";
		} else {
			user = UserMgr.getInstance().find(userName);
			if (!user.getPassWord().equals(passWord.trim())) {
				warnStr2 += "�������";
				user = new User("anonym");
			} else {
				session.setAttribute("userName", user.getName());
				session.setAttribute("logined", "true");
				response.sendRedirect("index.jsp");

			}
		}
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-��½</title>
<link href="style/share.css" rel="stylesheet" type="text/css" />
<link href="style/regist.css" rel="stylesheet" type="text/css" />
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
		<div id="userinfo">
			<form action="login.jsp" method="post">
				<input type="hidden" name="login" value="login" />

				<fieldset>
					<legend>�û���½��Ϣ</legend>
					<table>
						<tr>
							<td>�û���:</td>
							<td><input type="text" name="userName" /></td>
							<td><span class="warn"><%=warnStr1%></span>
							</td>
						</tr>
						<tr>
							<td>����:</td>
							<td><input type="password" name="passWord" /></td>
							<td><span class="warn"><%=warnStr2%></span>
							</td>
						</tr>

					</table>
					<input type="submit" value="��½" />
				</fieldset>

			</form>
		</div>
	</div>
</body>
</html>
