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
	String warnInfo = new String("�ѳɹ�����");

	String paraAdd = request.getParameter("add");

	if (paraAdd != null && paraAdd.trim().equals("add")) {
		//System.out.println("add");

	    boolean va = formData.validate();
		if (!va) {
			warnInfo = "��ʽ���ԣ�������Ŀ������������Ϊ�գ�";
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
				out.println("�Ƿ�����!");
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
<title>ExamOnLine-�û�������</title>
<link href="../style/share.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<div id="top">
			<p>&nbsp;���߿���ϵͳ��̨����</p>
			<span id="top1"><a href="adminLoginOut.jsp">ע��</a> </span> <span>����Ա:<%=admin.getName()%></span>
		</div>
		<div id="nav">
			<ul>
				<li><a href="updateUser.jsp" target="_self"> �����û�</a></li>
				<!--������ɫ����-->
				<li class="bar">|</li>
				<li><a href="updateQuestion.jsp" target="_self">������Դ</a></li>

			</ul>
		</div>
		<div id="question">
			<p style="color:red;font-size:20px;"><%=warnInfo%></p>
			<%
				if (q != null) {
			%>
			<table>

<tr>
  <td>��Ŀ��:</td>
  <td><%= q.getId() %></td>
 </tr>

<tr>
  <td>�������ͺ�:</td>
  <td><%= q.getCategoryId() %></td>
 </tr>
<tr>
  <td>��Ŀ����:</td>
  <td><%= q.getCont() %></td>
</tr>
<tr>
  <td>Aѡ��:</td>
  <td><%= q.getOptionA() %></td>
</tr>

<tr>
  <td>Bѡ��:</td>
  <td><%= q.getOptionB() %></td>
</tr>

<tr>
  <td>Cѡ��:</td>
  <td><%= q.getOptionC() %></td>
</tr>

<tr>
  <td>Dѡ��:</td>
  <td><%= q.getOptionD() %></td>
</tr>

<tr><td>��Ŀ��:</td>
  <td><%= q.getAnswer() %></td>
</tr>
</table>
			<%
				}else if(findQuestions!=null && findQuestions.size()>0){
				      Question findQ = findQuestions.get(0);
			%>
<table>

<tr>
  <td>��Ŀ��:</td>
  <td><%= findQ.getId() %></td>
 </tr>

<tr>
  <td>�������ͺ�:</td>
  <td><%= findQ.getCategoryId() %></td>
 </tr>
<tr>
  <td>��Ŀ����:</td>
  <td><%=  findQ.getCont() %></td>
</tr>
<tr>
  <td>Aѡ��:</td>
  <td><%=  findQ.getOptionA() %></td>
</tr>

<tr>
  <td>Bѡ��:</td>
  <td><%=  findQ.getOptionB() %></td>
</tr>

<tr>
  <td>Cѡ��:</td>
  <td><%=  findQ.getOptionC() %></td>
</tr>

<tr>
  <td>Dѡ��:</td>
  <td><%=  findQ.getOptionD() %></td>
</tr>

<tr><td>��Ŀ��:</td>
  <td><%= findQ.getAnswer() %></td>
</tr>
</table>
<% } %>
</div>

	</div>
</body>
</html>

