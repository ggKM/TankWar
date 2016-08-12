<%@ page language="java" import="java.util.*,gao.*,java.sql.*"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("GBK");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="isOnLine.jsp"%>
<%
	float totalScores = 0;
	float unitScore = 5;
	//Cookie[] cookies = request.getCookies();
	/* if (cookies != null) {
		for (Cookie c : cookies) {
				String cookName = c.getName();
				if(cookName.charAt(0)=='R'){
					String cookValue = c.getValue();
					//out.println(cookName + ":" + cookValue);
					int questionId = 0;
					try {
						questionId = Integer.parseInt(cookName
								.substring(5));
//System.out.println(questionId);
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
					if (questionId != 0
							&& Question.isRight(questionId, cookValue)) {
						totalScores += unitScore;
					}
					c.setMaxAge(0);
					response.addCookie(c);
				}
		}
	} */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-得分</title>
<link href="style/share.css" rel="stylesheet" type="text/css" />
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
				<li><a href="index.jsp" target="_self">首页</a></li>


			</ul>
		</div>
		<div id="mid">
			
			<p>您的答案：</p>
<%
Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie c : cookies) {
				String cookName = c.getName();
				if(cookName.charAt(0)=='R'){
					String cookValue = c.getValue();
					//out.println(cookName + ":" + cookValue);
					int questionId = 0;
					try {
						questionId = Integer.parseInt(cookName
								.substring(5));
//System.out.println(questionId);
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
					if (questionId != 0
							&& Question.isRight(questionId, cookValue)) {
						totalScores += unitScore;
					}
					c.setMaxAge(0);
					response.addCookie(c);%>
	<p><%= questionId%>：<%= cookValue %>&nbsp&nbsp&nbsp&nbsp正确答案：<%= Question.getAnswerByQuestionId(questionId) %></p>
<% 				}
		}
	}
	%>
	<P>您的得分：</P>

			<p style="color:red;font-size:30px;">
				<%=totalScores%>分！
			</p>
		</div>

	</div>
</body>
</html>

