<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="isOnLine.jsp"%>
<%

	if (request.getParameter("question") != null
			&& request.getParameter("question").equals("question")) {
		Enumeration e = request.getParameterNames();
		List<String> values = new ArrayList<String>();
		while (e.hasMoreElements()) {
			String paraname = (String) e.nextElement();

			if (paraname.charAt(0) == 'R') {
//System.out.println(paraname);
				//values.add(request.getParameterValues(paraname)[0]);
				Cookie c = new Cookie(paraname,request.getParameterValues(paraname)[0]);
				c.setMaxAge(3600);
				response.addCookie(c);//System.out.println(c.getName()+":"+c.getValue());
			}
		}
		
		//System.out.println(c.getValue());
		if(request.getParameter("result")!=null && request.getParameter("result").trim().equals("result")){
			response.sendRedirect("result.jsp");
			return;
		}
	}
%>

<%
	List<Question> questions = new ArrayList<Question>();
	int pageNo = 1;
	int categoryId = 1;
	int pageSize = 6;
	int totalPages = 0;
	if (isOn) {

		//接收页号
		String pageParam = request.getParameter("pageNo");
		if (pageParam == null || pageParam.trim().equals("")) {
			pageNo = 1;
		} else {
			try {
				pageNo = Integer.parseInt(pageParam);
				if (pageNo <= 0) {
					pageNo = 1;
				}
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}
		// 接收id		
		String categoryStr = request.getParameter("id");

		if (categoryStr == null || categoryStr.trim().equals("")) {
			categoryId = 1;
		} else {
			try {
				categoryId = Integer.parseInt(categoryStr);
				if (categoryId <= 0) {
					categoryId = 1;
				}
			} catch (NumberFormatException e) {
				categoryId = 1;
			}
		}

		totalPages = Question.sepGetQuestions(questions,
				categoryId, pageSize, pageNo);
		if (pageNo > totalPages) {
			pageNo = 1;
		}

	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ExamOnLine-试题</title>
<link href="style/question.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="style/share.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   function setName(obj){
   		obj.name=obj.value;
   }
</script>
</head>

<body>
	<div id="main">
		<div id="top">
			<p>欢迎来到在线考试系统</p>
			<span id="top1"><a href="loginOut.jsp">注销</a> </span><span id="top1"><a
				href="login.jsp">登陆</a> </span><span id="top1"><a href="login.jsp">注册</a> </span> <span>您好:<%=user.getName()%></span>
		</div>
		<div id="nav">
			<ul>
				<li><a href="index.jsp" target="_self">首页</a></li>


			</ul>
		</div>
		<form action="question.jsp?pageNo=<%=pageNo + 1%>&id=<%=categoryId%>"
			method="post">

			<input type="hidden" name="question" value="question" />
			<div id="cont">
				<%
					if (isOn) {
						for (Question q : questions) {
				%>
				<p><%=q.getId() + "." + q.getCont()%></p>
				<br />
				<p>
					A:<%=q.getOptionA()%>&nbsp&nbsp&nbspB:<%=q.getOptionB()%>&nbsp&nbsp&nbspC:<%=q.getOptionC()%>&nbsp&nbsp&nbspD:<%=q.getOptionD()%></p>

				<p>

					A<input type="radio" name="Radio<%=q.getId()%>" value="A" /> B<input
						type="radio" name="Radio<%=q.getId()%>" value="B" /> C<input
						type="radio" name="Radio<%=q.getId()%>" value="C" /> D<input
						type="radio" name="Radio<%=q.getId()%>" value="D" />

				</p>
				<hr color="#999999" />

				<%
					}
					} else {
				%>
				<p style="color:red;">请先登录！</p>
				<%
					}
				%>

			</div>
			<% if(pageNo < totalPages){ %>
			<input type="submit" value="下<%=pageSize%>道" />
			<% } %>
			<input type="submit" value="result" onclick="setName(this);"></a>
		 
		</form>
		
	</div>
</body>
</html>
