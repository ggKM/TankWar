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
			warnStr3 += "请确认两次输入的密码为有效字符且一致！";

		} else {
			user = new User(userName, passWord);
			byte legal = UserMgr.getInstance().add(user);
			if (-1 == legal) {
				warnStr1 += "用户名必须为有效字符！";
				user = new User("anonym");
			} else if (-2 == legal) {
				user = new User("anonym");
				warnStr2 += "密码必须为有效字符！";
			} else if (1 == legal) {
				warnStr1 += "此用户名已有人注册！";
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
<title>ExamOnLine-注册</title>
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
			document.getElementById("warn1").innerText = "用户名不能为空！";
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
			document.getElementById("warn1").innerText = "用户名不能为空！"
			return false;
		} else if (passWord == "") {
			document.getElementById("warn2").innerText = "密码不能为空！"
			return false;
		} else if (confirmPassWord != passWord) {

			document.getElementById("warn3").innerText = "请确认两次输入的密码一致";
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<div id="main">
		<div id="top">
			<p>欢迎来到在线考试系统</p>
			<span id="top1"><a href="loginOut.jsp">注销</a> </span><span id="top1"><a
				href="login.jsp">登陆</a> </span><span id="top1"><a href="regist.jsp">注册</a>
			</span> <span>您好:<%=user.getName()%></span>
		</div>
<div id="nav">
			<ul>
				<li><a href="index.jsp" target="_self">首页</a>
				</li>
				

			</ul>
		</div>
		<div id="userinfo">
			<form action="regist.jsp" method="post">

				<input type="hidden" name="reg" value="reg" />

				<fieldset>
					<legend>用户注册信息</legend>
					<table>
						<tr>
							<td>用户名:</td>
							<td><input type="text" name="userName" onblur="checkUserName();" />
							</td>
							<td><span class="warn" id="warn1"><%=warnStr1%></span>
							</td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password" name="passWord" /></td>
							<td><span class="warn" id="warn2"><%=warnStr2%></span>
							</td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input type="password" name="confirmPassWord" " /></td>
							<td><span class="warn" id="warn3"><%=warnStr3%></span>
							</td>
						</tr>

					</table>
					<input type="submit" value="注册" onclick="return check();" />
				</fieldset>

			</form>
		</div>
	</div>
</body>
</html>
