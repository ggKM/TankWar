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
<title>ExamOnLine-�޸��û���Ϣ</title>
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
<p>���߿���ϵͳ��̨����</p><span id="top1"><a href="adminLoginOut.jsp">ע��</a></span>
<span>����Ա:<%=admin.getName() %></span>
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
<div style="margin:0px auto; margin-top:50px; text-align:center; background-color: #0C0;">��ʾ��Ϣ</div>
<div id="userinfo">
<form action="dealUser.jsp" method="post">


<fieldset><legend>�޸��û���Ϣ</legend>
<font color="#FF0000">��ʾ�����Ӻ͸��Ĳ���������д�û��������룬���Ĳ���ֻ�ܸ������롣��ѯ��ɾ������ֻ��д�û�����</font>
<table>

<tr><td>�û���:</td><td><input type="text" name="userName"/><span id="info"></span></td></tr>
<tr><td>����:</td><td><input type="text" name="passWord"/><span id="info"></span></td></tr>

</table>
<input type="submit" value="add" onclick="setName(this);"/><input type="submit" value="del" onclick="setName(this);"/><input type="submit"  value="upd" onclick="setName(this);"/><input type="submit"  value="fin" onclick="setName(this);"/>
</fieldset>

</form>
</div>
</div>
</body>
</html>
