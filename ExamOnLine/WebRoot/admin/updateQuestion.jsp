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
<title>ExamOnLine-�޸���Դ��Ϣ</title>
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
<div id="question">
<form action="dealQuestion.jsp" method="post">


<fieldset><legend>�޸���Դ��Ϣ</legend>
<font color="#FF0000">��ʾ�����Ӳ���������д���������ݣ����Ĳ�����������Ŀ����ֻ�ܸ�����Ŀ���ݺʹ𰸣���ѯ����ֻ��д��Ŀ�Ż����ݡ�ɾ����������Ŀ��</font>
<table>

<tr>
  <td>��Ŀ��:</td>
  <td><input type="text" name="questionId"/></td>
 </tr>

<tr>
  <td>�������ͺ�:</td>
  <td><input type="text" name="categoryId"/></td>
 </tr>
<tr>
  <td>��Ŀ����:</td>
  <td><textarea  rows="10" cols="60" name="cont"></textarea></td>
</tr>
<tr>
  <td>Aѡ��:</td>
  <td><textarea rows="5" cols="60" name="optionA"></textarea></td>
</tr>

<tr>
  <td>Bѡ��:</td>
  <td><textarea rows="5" cols="60" name="optionB"></textarea></td>
</tr>

<tr>
  <td>Cѡ��:</td>
  <td><textarea   rows="5" cols="60" name="optionC"></textarea></td>
</tr>

<tr>
  <td>Dѡ��:</td>
  <td><textarea   rows="5" cols="60" name="optionD"></textarea></td>
</tr>

<tr><td>��Ŀ��:</td>
  <td>A<input type="radio" value="A" name="answer"/>B<input type="radio" value="B" name="answer"/>C<input type="radio" value="C" name="answer"/>D<input type="radio" value="D" name="answer"/></td>
</tr>
</table>
<input type="submit" value="add" onclick="setName(this);"/><input type="submit" value="del" onclick="setName(this);"/><input type="submit"  value="upd" onclick="setName(this);"/><input type="submit"  value="fin" onclick="setName(this);"/>
</fieldset>

</form>
</div>
</div>
</body>
</html>
