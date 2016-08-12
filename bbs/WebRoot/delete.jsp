<%@page pageEncoding="gb18030"%><%@page
	import="gao.*,java.util.ArrayList,java.util.*,java.sql.*"%>
	<%@ include file="isLogin.jsp" %>
<%!private void deleteTree(Connection conn, int id, boolean isLeaf) {

		Statement st1 = DataBase.getST(conn);

		if (!isLeaf) {
			String sql = "select * from article where pid = " + id;
			ResultSet rs = DataBase.getQueryRS(st1, sql);
			try {
				while (rs.next()) {
					deleteTree(conn, rs.getInt("id"), rs.getInt("isleaf") == 0);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBase.close(rs);
				DataBase.close(st1);
			}

		}
		Statement st2 = DataBase.getST(conn);
		DataBase.executeUpdate(st2, "delete from article where id =" + id);
		DataBase.close(st2);
	}%>

<%
	int id = 0, pid = 0;
	boolean isLeaf = true;
	String url = request.getParameter("from");
	try {
		id = Integer.parseInt(request.getParameter("id"));
		pid = Integer.parseInt(request.getParameter("pid"));
		isLeaf = Boolean.parseBoolean(request.getParameter("isLeaf"));
	} catch (Exception e) {
		out.print("NumberFormatException");
	}
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	boolean autoCommit = true;
	try {
		conn = DataBase.getConn();
		autoCommit = conn.getAutoCommit();
		conn.setAutoCommit(false);
		
		deleteTree(conn, id, isLeaf);
		
		st = DataBase.getST(conn);
		rs = DataBase.getQueryRS(st,
				"select count(*) from article where pid = " + pid);
		rs.next();
		int count = rs.getInt(1);
		if (count <= 0) {
			DataBase.executeUpdate(st,
					"update article set isleaf = 0 where id = " + pid);
		}
		conn.commit();
	} finally {
		conn.setAutoCommit(autoCommit);
		DataBase.close(rs);
		DataBase.close(st);
		DataBase.close(conn);
	}
	response.sendRedirect(url);
%>
