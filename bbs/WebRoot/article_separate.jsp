<%@page pageEncoding="gb18030"%><%@page
	import="gao.*,java.util.ArrayList,java.util.*,java.sql.*"%>

<%
	String pageParam = request.getParameter("pageNo");

int pageNo = 1;int pageSize = 2;int totalPages;int totalRecords;int startPos;
if(pageParam==null || pageParam.trim().equals("")) {
	
} else {
	try{
		pageNo = Integer.parseInt(pageParam);
		if(pageNo<=0){pageNo = 1;}
	}catch(NumberFormatException e){
		pageNo = 1;
	}
}
Connection conn = DataBase.getConn();
Statement st = DataBase.getST(conn);
ResultSet rs = DataBase.getQueryRS(st, "select count(*) from article where pid = 0");
rs.next();
totalRecords = rs.getInt(1);
totalPages = (totalRecords % pageSize == 0) ? (totalRecords / pageSize) : (totalRecords / pageSize + 1) ;
if(pageNo > totalPages){ pageNo = 1;}
startPos = (pageNo-1) * pageSize;
String sql = "select * from article where pid = 0 order by pdate desc limit "+startPos+","+pageSize;
rs = DataBase.getQueryRS(st, sql);

List<Article> articles = new ArrayList<Article> ();
try{
	while(rs.next()){
	
		Article article = new Article() ;
		article.initFromRS(rs, 0);
		articles.add(article);
	  }
	
	}catch(SQLException e){
		e.printStackTrace();
	}finally {
		DataBase.close(rs);
	    DataBase.close(st);
	    DataBase.close(conn);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>bbs初级版</title>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>

<script language="JavaScript" type="text/javascript"
	src="images/common.js"></script>

</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td width="140"><a
					href="http://bbs.chinajavaworld.com/index.jspa"><img
						src="images/header-left.gif"
						alt="Java|Java世界_中文论坛|ChinaJavaWorld技术论坛" border="0"> </a></td>
				<td><img src="images/header-stretch.gif" alt="" border="0"
					height="57" width="100%"></td>
				<td width="1%"><img src="images/header-right.gif" alt=""
					border="0"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div id="jive-forumpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="98%"><p class="jive-breadcrumbs">论坛: Java语言*初级版
							(模仿)</p>
						<p class="jive-description">探讨Java语言基础知识,基本语法等 大家一起交流
							共同提高！谢绝任何形式的广告</p>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="jive-buttons">
			<table summary="Buttons" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="jive-icon"><a
							href="http://bbs.chinajavaworld.com/post%21default.jspa?forumID=20"><img
								src="images/post-16x16.gif" alt="发表新主题" border="0" height="16"
								width="16"> </a></td>
						<td class="jive-icon-label"><a id="jive-post-thread"
							href="post.jsp">发表新主题</a> <a
							href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;isBest=1"></a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<table border="0" cellpadding="3" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td><span class="nobreak"> 页: <%=pageNo%> - <span
							class="jive-paginator"> [ <a
								href="article_separate.jsp?pageNo=<%=pageNo - 1%>">上一页</a>
								 |<!-- 第一种方法
								  <form name="form" action="article_separate.jsp" method="post">
									<select name="pageNo" onchange="document.form.submit();">
										<%for (int i = 1; i <= totalPages; i++) {%>
								  
								 	 	<option value="<%=i%>" <%=pageNo == i ? "selected" : ""%>>第<%=i%>页</option>
								  
								 		<%}%>
								 	</select>
								 </form> 
								 -->
								  <select name="pageNo" id="pageNo"
								onchange="var obj = document.getElementById('pageNo');
 											 var index = obj.selectedIndex+1;
  											window.location.href = 'article_separate.jsp?pageNo='+index;">
									<%
										for (int i = 1; i <= totalPages; i++) {
									%>

									<option value="<%=i%>" <%=pageNo == i ? "selected" : ""%>>
										第<%=i%>页
									</option>

									<%
										}
									%>
							</select> 
							| 
							<a href="article_separate.jsp?pageNo=<%=pageNo + 1%>">下一页</a>
								] </span> </span>
					</td>
				</tr>
			</tbody>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%"><div class="jive-thread-list">
							<div class="jive-table">
								<table summary="List of threads" cellpadding="0" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th class="jive-first" colspan="3">主题</th>
											<th class="jive-author"><nobr> 作者 &nbsp; </nobr>
											</th>
											<th class="jive-view-count"><nobr> 浏览 &nbsp; </nobr>
											</th>
											<th class="jive-msg-count" nowrap="nowrap">回复</th>
											<th class="jive-last" nowrap="nowrap">最新帖子</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (Iterator<Article> it = articles.iterator(); it.hasNext();) {

												Article a = it.next();
										%>
										<tr class="jive-even">
											<td class="jive-first" nowrap="nowrap" width="1%"><div
													class="jive-bullet">
													<img src="images/read-16x16.gif" alt="已读" border="0"
														height="16" width="16">
													<!-- div-->
												</div></td>
											<td nowrap="nowrap" width="1%">&nbsp; &nbsp;</td>
											<td class="jive-thread-name" width="95%"><a
												id="jive-thread-1"
												href="article_detail.jsp?id=<%=a.getId()%>"><%=a.getTitle()%></a>
											</td>
											<td class="jive-author" nowrap="nowrap" width="1%"><span
												class=""> <a
													href="http://bbs.chinajavaworld.com/profile.jspa?userID=226030">fei870407</a>
											</span></td>
											<td class="jive-view-count" width="1%">104</td>
											<td class="jive-msg-count" width="1%">5</td>
											<td class="jive-last" nowrap="nowrap" width="1%"><div
													class="jive-last-post">
													<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(a.getPdate())%>
													<br> by: <a
														href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780182#780182"
														title="jingjiangjun" style="">jingjiangjun &#187;</a>
												</div></td>
										</tr>
										<%
											}
										%>
										<tr class="jive-odd">
											<td class="jive-first" nowrap="nowrap" width="1%"><div
													class="jive-bullet">
													<img src="images/read-16x16.gif" alt="已读" border="0"
														height="16" width="16">
													<!-- div-->
												</div></td>
											<td nowrap="nowrap" width="1%">&nbsp; &nbsp;</td>
											<td class="jive-thread-name" width="95%"><a
												id="jive-thread-2"
												href="http://bbs.chinajavaworld.com/thread.jspa?threadID=744234&amp;tstart=25">请
													兄弟们指点下那里 错误，，，</a></td>
											<td class="jive-author" nowrap="nowrap" width="1%"><span
												class=""> <a
													href="http://bbs.chinajavaworld.com/profile.jspa?userID=226028">403783154</a>
											</span></td>
											<td class="jive-view-count" width="1%">52</td>
											<td class="jive-msg-count" width="1%">2</td>
											<td class="jive-last" nowrap="nowrap" width="1%"><div
													class="jive-last-post">
													2007-9-13 上午8:40 <br> by: <a
														href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780172#780172"
														title="downing114" style="">downing114 &#187;</a>
												</div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="jive-legend"></div></td>
				</tr>
			</tbody>
		</table>
		<br> <br>
	</div>
</body>
</html>
