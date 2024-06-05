<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.poscodx.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
<div id="header">
	<h1>MySite</h1>
	<ul>
		<c:choose>
			<c:when test='${empty authUser }'>
			</c:when>
			
			<c:otherwise>

			</c:otherwise>
		</c:choose>
		<%
			if(authUser == null){
		%>
		<li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a>
		<li>
		<li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a>
		<li>
		<% } else{ %>
		<li><a href="${pageContext.request.contextPath}/user?a=updatefor`m">회원정보수정</a>
		<li>
		<li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a>
		<li>
		<li><%=authUser.getName() %>님 안녕하세요 ^^;</li>
		<%} %>
	</ul>
</div>