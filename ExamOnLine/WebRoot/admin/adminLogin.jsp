<%@ page language="java" import="java.util.*,gao.*"
	pageEncoding="GB18030"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/admin/";
%>
<%
	StringBuffer warnInfo = new StringBuffer("��ʾ:");
	String adminName = config.getServletContext().getInitParameter(
			"adminName");
	User admin = null;
	String name = (String) session.getAttribute("adminName");
	String logined = (String) session.getAttribute("logined");
	if (name != null && name.trim().equals(adminName)
			&& logined != null && logined.trim().equals("true")) {

		admin = new User(adminName);
	} else {
		admin = new User("anonym");
		String adminLogin = request.getParameter("adminLogin");

		if (adminLogin != null
				&& adminLogin.trim().equals("adminLogin")) {

			String paraName = request.getParameter("name");
			String passWord = request.getParameter("passWord");
			if (paraName != null && passWord != null
					&& !paraName.trim().equals("")
					&& !passWord.trim().equals("")) {

				String adminPassWord = config.getServletContext()
						.getInitParameter("adminPassWord");

				if (adminName.equals(paraName)
						&& adminPassWord.equals(passWord)) {
					session.setAttribute("adminName", adminName);
					session.setAttribute("logined", "true");
					admin = new User(adminName);
				} else {
					warnInfo.append("��˶��û��������룡");
				}
			} else {
				warnInfo.append("��˶��û��������룡");
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-����Ա��½</title>
<link href="../style/share.css" rel="stylesheet" type="text/css" />
<link href="../style/regist.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div id="main">
		<div id="top">
			<p>���߿���ϵͳ��̨����</p>
			<span id="top1"><a href="adminLoginOut.jsp">ע��</a> </span><span
				id="top1"><a href="adminLogin.jsp">��½</a> </span> <span>����Ա:<%=admin.getName()%></span>
		</div>
		<div id="nav">
			<ul>
				<li><a href="updateUser.jsp" target="_self"> �����û�</a>
				</li>
				<!--������ɫ����-->
				<li class="bar">|</li>
				<li><a href="updateQuestion.jsp" target="_self">������Դ</a>
				</li>

			</ul>
		</div>
		<div
			style="margin:0px auto; margin-top:50px; text-align:center; background-color: #0C0;"><%=warnInfo.toString()%></div>
		<div id="userinfo">
			<form action="adminLogin.jsp" method="post">

				<input type="hidden" name="adminLogin" value="adminLogin" />
				<fieldset>
					<legend>�û���½��Ϣ</legend>
					<table>
						<tr>
							<td>�û���:</td>
							<td><input type="text" name="name" /><span calss="warn"></span>
							</td>
						</tr>
						<tr>
							<td>����:</td>
							<td><input type="password" name="passWord" /><span calss="warn"></span>
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

