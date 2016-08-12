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
	String regStr = request.getParameter("reg");
	String warnStr1 = new String();
	String warnStr2 = new String();
	String warnStr3 = new String();

	if (regStr != null && regStr.equals("reg")) {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String confirmPassWord = request
				.getParameter("confirmPassWord");
		if (passWord == null || confirmPassWord == null
				|| !passWord.trim().equals(confirmPassWord.trim())) {
			warnStr3 += "��ȷ���������������Ϊ��Ч�ַ���һ�£�";

		} else {
			user = new User(userName, passWord);
			byte legal = UserMgr.getInstance().add(user);
			if (-1 == legal) {
				warnStr1 += "�û�������Ϊ��Ч�ַ���";
				user = new User("anonym");
			} else if (-2 == legal) {
				user = new User("anonym");
				warnStr2 += "�������Ϊ��Ч�ַ���";
			} else if (1 == legal) {
				warnStr1 += "���û���������ע�ᣡ";
				user = new User("anonym");
			} else {
				session.setAttribute("userName", user.getName());
				session.setAttribute("logined", "true");
				response.sendRedirect("index.jsp");
				return;
			}
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-ע��</title>
<link href="style/share.css" rel="stylesheet" type="text/css" />
<link href="style/regist.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
	var req;

	function init() {
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
    function checkUserName() {
  
		 var userName = document.getElementsByName("userName")[0].value;
		  
		if (userName == null || userName == "") {
			document.getElementById("warn1").innerText = "�û�������Ϊ�գ�";
		} else {
			init();	
			var url = "check.jsp?userName="
					+ encodeURI(encodeURI(document.getElementsByName("userName")[0].value));
	
			req.open("GET", url, true);
			req.onreadystatechange = callback;
			req.send(null);
		}
 
	} 
	
	function callback() {
	
		if (4 == req.readyState) {

			if (200 == req.status) {
				//alert(req.responseText);
				document.getElementById("warn1").innerText = req.responseText;
			}
		}
	}
	function check() {
		var userName = document.getElementsByName("userName")[0].value;
		var passWord = document.getElementsByName("passWord")[0].value;
		var confirmPassWord = document.getElementsByName("confirmPassWord")[0].value;
		if (userName == null || userName == "") {
			document.getElementById("warn1").innerText = "�û�������Ϊ�գ�"
			return false;
		} else if (passWord == "") {
			document.getElementById("warn2").innerText = "���벻��Ϊ�գ�"
			return false;
		} else if (confirmPassWord != passWord) {

			document.getElementById("warn3").innerText = "��ȷ���������������һ��";
			return false;
		}
		return true;
	}
</script>
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
			<form action="regist.jsp" method="post">

				<input type="hidden" name="reg" value="reg" />

				<fieldset>
					<legend>�û�ע����Ϣ</legend>
					<table>
						<tr>
							<td>�û���:</td>
							<td><input type="text" name="userName" onblur="checkUserName();" />
							</td>
							<td><span class="warn" id="warn1"><%=warnStr1%></span>
							</td>
						</tr>
						<tr>
							<td>����:</td>
							<td><input type="password" name="passWord" /></td>
							<td><span class="warn" id="warn2"><%=warnStr2%></span>
							</td>
						</tr>
						<tr>
							<td>ȷ������:</td>
							<td><input type="password" name="confirmPassWord" " /></td>
							<td><span class="warn" id="warn3"><%=warnStr3%></span>
							</td>
						</tr>

					</table>
					<input type="submit" value="ע��" onclick="return check();" />
				</fieldset>

			</form>
		</div>
	</div>
</body>
</html>
