<%@ page language="java" import="java.util.*,gao.*,java.sql.*"
	pageEncoding="gb18030"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/admin/";
%>
<%@ include file="adminIsOnLine.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-修改用户信息</title>
<link href="../style/share.css" rel="stylesheet" type="text/css" />
<link href="../style/regist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   function setName(obj){
   		obj.name=obj.value;
   }
</script>
</head>

<body>
<div id="main">
<div id="top">
<p>在线考试系统后台管理</p><span id="top1"><a href="adminLoginOut.jsp">注销</a></span>
<span>管理员:<%=admin.getName() %></span>
</div>
<div id="nav">
			<ul>
				<li><a href="updateUser.jsp" target="_self"> 管理用户</a>
				</li>
				<!--链接颜色不对-->
				<li class="bar">|</li>
				<li><a href="updateQuestion.jsp" target="_self">管理题源</a>
				</li>

			</ul>
		</div>
<div style="margin:0px auto; margin-top:50px; text-align:center; background-color: #0C0;">提示信息</div>
<div id="userinfo">
<form action="dealUser.jsp" method="post">


<fieldset><legend>修改用户信息</legend>
<font color="#FF0000">提示：增加和更改操作必须填写用户名和密码，更改操作只能更改密码。查询和删除可以只填写用户名。</font>
<table>

<tr><td>用户名:</td><td><input type="text" name="userName"/><span id="info"></span></td></tr>
<tr><td>密码:</td><td><input type="text" name="passWord"/><span id="info"></span></td></tr>

</table>
<input type="submit" value="add" onclick="setName(this);"/><input type="submit" value="del" onclick="setName(this);"/><input type="submit"  value="upd" onclick="setName(this);"/><input type="submit"  value="fin" onclick="setName(this);"/>
</fieldset>

</form>
</div>
</div>
</body>
</html>
