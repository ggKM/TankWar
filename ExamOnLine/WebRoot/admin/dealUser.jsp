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
<%
	String paraAdd = request.getParameter("add");
	String warnInfo = new String("已成功处理！");
	User user = null;
	int legal=0;
	if (paraAdd != null && paraAdd.trim().equals("add")) {
		//System.out.println("add");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		user = new User(userName, passWord);
		legal = UserMgr.getInstance().add(user);
		if (1 == legal) {
			warnInfo = "用户名已被使用！";
		} else if(legal!=0) {
			warnInfo = "用户名或密码不能为空！";
		}
	}else {
		String paraDel = request.getParameter("del");
		if (paraDel != null && paraDel.trim().equals("del")) {
			//System.out.println("del");
			String userName = request.getParameter("userName");
			 user = new User(userName);
			legal = UserMgr.getInstance().delete(user);
			if(-1==legal){
				warnInfo = "用户名不能为空！";
			}else if(0==legal){
				warnInfo = "此用户不存在！ ";
			}

		}else {
			String paraCha = request.getParameter("upd");
			if (paraCha != null && paraCha.trim().equals("upd")) {
				//System.out.println("upd");
				String userName = request.getParameter("userName");
				String passWord = request.getParameter("passWord");
				 user = new User(userName,passWord);
			    legal = UserMgr.getInstance().update(user);
				
					if(-1==legal){
					  	warnInfo = "用户名不能为空！";
				    }else if(0==legal){
					 	warnInfo = "此用户不存在！ ";
					}else if(-2==legal){
						warnInfo = "用户密码不能改为空 ！ ";
	
					}//结束
				}else {
					String paraFin = request.getParameter("fin");
					if (paraFin != null && paraFin.trim().equals("fin")) {
					//System.out.println("Fin");
					String userName = request.getParameter("userName");
//System.out.println(userName);
					user = UserMgr.getInstance().obscureFind(userName);
						if(user== null){
							warnInfo = "此 用户不存在 ！ ";
						 }
				     }
				  }	
				}
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
			<p>在线考试系统后台管理</p>
			<span id="top1"><a href="adminLoginOut.jsp">注销</a> </span> <span>管理员:<%=admin.getName()%></span>
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
		<div id="mid">
		<p style="color:red"><%= warnInfo %></p>
		<% if(user!=null){ %>
			<table>
						<tr>
							<td>用户名:</td>
							<td><%=user.getName()  %>
							</td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><%= user.getPassWord() %>
							</td>
						</tr>

					</table>
		<% } %>
		</div>

	</div>
</body>
</html>

