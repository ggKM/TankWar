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
	String warnInfo = new String("�ѳɹ�����");
	User user = null;
	int legal=0;
	if (paraAdd != null && paraAdd.trim().equals("add")) {
		//System.out.println("add");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		user = new User(userName, passWord);
		legal = UserMgr.getInstance().add(user);
		if (1 == legal) {
			warnInfo = "�û����ѱ�ʹ�ã�";
		} else if(legal!=0) {
			warnInfo = "�û��������벻��Ϊ�գ�";
		}
	}else {
		String paraDel = request.getParameter("del");
		if (paraDel != null && paraDel.trim().equals("del")) {
			//System.out.println("del");
			String userName = request.getParameter("userName");
			 user = new User(userName);
			legal = UserMgr.getInstance().delete(user);
			if(-1==legal){
				warnInfo = "�û�������Ϊ�գ�";
			}else if(0==legal){
				warnInfo = "���û������ڣ� ";
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
					  	warnInfo = "�û�������Ϊ�գ�";
				    }else if(0==legal){
					 	warnInfo = "���û������ڣ� ";
					}else if(-2==legal){
						warnInfo = "�û����벻�ܸ�Ϊ�� �� ";
	
					}//����
				}else {
					String paraFin = request.getParameter("fin");
					if (paraFin != null && paraFin.trim().equals("fin")) {
					//System.out.println("Fin");
					String userName = request.getParameter("userName");
//System.out.println(userName);
					user = UserMgr.getInstance().obscureFind(userName);
						if(user== null){
							warnInfo = "�� �û������� �� ";
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
<title>ExamOnLine-�û�������</title>
<link href="../style/share.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<div id="top">
			<p>���߿���ϵͳ��̨����</p>
			<span id="top1"><a href="adminLoginOut.jsp">ע��</a> </span> <span>����Ա:<%=admin.getName()%></span>
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
		<div id="mid">
		<p style="color:red"><%= warnInfo %></p>
		<% if(user!=null){ %>
			<table>
						<tr>
							<td>�û���:</td>
							<td><%=user.getName()  %>
							</td>
						</tr>
						<tr>
							<td>����:</td>
							<td><%= user.getPassWord() %>
							</td>
						</tr>

					</table>
		<% } %>
		</div>

	</div>
</body>
</html>

