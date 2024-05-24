<%@page import="com.poscodx.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form action="<%=request.getContextPath() %>/guestbook" method="post">

	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" value=" 확인 "></td>
						</tr>
					</table>
				</form>
				<%
					int count = list.size();
					for(GuestBookVo vo : list) {
				%>
				<ul>
						<li>
							<table>
								<tr>
									<td>[<%=count-- %>]</td>
									<td><%=vo.getName() %></td>
									<td><%=vo.getDatetime() %></td>
									<td><a href="<%=request.getContextPath() %>/guestbook?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										<%=vo.getContent().replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\n", "<br>") %>
									</td>
								</tr>
							</table>
							<br>
						</li>
				</ul>
					<%
						}
					%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>