<%@ page language="java" import="java.util.*,gao.*,java.sql.*"
	pageEncoding="gb18030"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/admin/";
%>
<%@ include file="adminIsOnLine.jsp"%>

<jsp:useBean id="formData" class="gao.QuestionForForm">
	<jsp:setProperty name="formData" property="*" />
</jsp:useBean>

<%
	Question q = null;List<Question> findQuestions = null;
	String warnInfo = new String("已成功处理！");

	String paraAdd = request.getParameter("add");

	if (paraAdd != null && paraAdd.trim().equals("add")) {
		//System.out.println("add");

	    boolean va = formData.validate();
		if (!va) {
			warnInfo = "格式不对：除了题目号外其他不能为空！";
		} else {
			q = new Question();
			q.initFromForm(formData);
			Question.add(q);

		}
	}
	String paraDel = request.getParameter("del");
	if (paraDel != null && paraDel.trim().equals("del")) {
		System.out.println("del");
		String paraId = request.getParameter("questionId");
		int questionId = 0;
		if (paraId != null && !paraId.trim().equals("")) {
			try {
				questionId = Integer.parseInt(paraId);
			} catch (NumberFormatException e) {
				out.println("非法参数!");
			}
//System.out.println(questionId);
			q = new Question(questionId);
			Question.delete(q);
		}

	}
	String paraCha = request.getParameter("upd");
	if (paraCha != null && paraCha.trim().equals("upd")) {
		 //System.out.println(formData.getQuestionId());
		
			q = new Question();
			q.initFromForm(formData);
			Question.update(q);
		
	}
	String paraFin = request.getParameter("fin");
	if (paraFin != null && paraFin.trim().equals("fin")) {
		//System.out.println("Fin");
		String paraCont = request.getParameter("cont");
			
			findQuestions = Question.find(paraCont);
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-用户处理结果</title>
<link href="../style/share.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<div id="top">
			<p>&nbsp;在线考试系统后台管理</p>
			<span id="top1"><a href="adminLoginOut.jsp">注销</a> </span> <span>管理员:<%=admin.getName()%></span>
		</div>
		<div id="nav">
			<ul>
				<li><a href="updateUser.jsp" target="_self"> 管理用户</a></li>
				<!--链接颜色不对-->
				<li class="bar">|</li>
				<li><a href="updateQuestion.jsp" target="_self">管理题源</a></li>

			</ul>
		</div>
		<div id="question">
			<p style="color:red;font-size:20px;"><%=warnInfo%></p>
			<%
				if (q != null) {
			%>
			<table>

<tr>
  <td>题目号:</td>
  <td><%= q.getId() %></td>
 </tr>

<tr>
  <td>所属类型号:</td>
  <td><%= q.getCategoryId() %></td>
 </tr>
<tr>
  <td>题目内容:</td>
  <td><%= q.getCont() %></td>
</tr>
<tr>
  <td>A选项:</td>
  <td><%= q.getOptionA() %></td>
</tr>

<tr>
  <td>B选项:</td>
  <td><%= q.getOptionB() %></td>
</tr>

<tr>
  <td>C选项:</td>
  <td><%= q.getOptionC() %></td>
</tr>

<tr>
  <td>D选项:</td>
  <td><%= q.getOptionD() %></td>
</tr>

<tr><td>题目答案:</td>
  <td><%= q.getAnswer() %></td>
</tr>
</table>
			<%
				}else if(findQuestions!=null && findQuestions.size()>0){
				      Question findQ = findQuestions.get(0);
			%>
<table>

<tr>
  <td>题目号:</td>
  <td><%= findQ.getId() %></td>
 </tr>

<tr>
  <td>所属类型号:</td>
  <td><%= findQ.getCategoryId() %></td>
 </tr>
<tr>
  <td>题目内容:</td>
  <td><%=  findQ.getCont() %></td>
</tr>
<tr>
  <td>A选项:</td>
  <td><%=  findQ.getOptionA() %></td>
</tr>

<tr>
  <td>B选项:</td>
  <td><%=  findQ.getOptionB() %></td>
</tr>

<tr>
  <td>C选项:</td>
  <td><%=  findQ.getOptionC() %></td>
</tr>

<tr>
  <td>D选项:</td>
  <td><%=  findQ.getOptionD() %></td>
</tr>

<tr><td>题目答案:</td>
  <td><%= findQ.getAnswer() %></td>
</tr>
</table>
<% } %>
</div>

	</div>
</body>
</html>

