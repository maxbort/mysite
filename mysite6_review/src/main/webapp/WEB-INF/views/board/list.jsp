<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="board">
                <form id="search" action="${pageContext.servletContext.contextPath}/board" method="get">
                	<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
                </form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>			
					<c:forEach items='${map.list }' var='vo' varStatus="status">
						<tr>
							<td>${map.totalArticle - (map.currentPage - 1)*map.listSize - status.index }</td>	
							<c:choose>
								<c:when test="${vo.depth > 0}">
									<td style="text-align:left; padding-left:${20*vo.depth }px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }?page=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:when>
								<c:otherwise>
									<td class="left" style="text-align:left">
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }?page=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:otherwise>
							</c:choose>
							
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
							<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal" var="authUser"/>
									<c:if test="${authUser.no == vo.userNo }">
										<a href="${pageContext.request.contextPath }/board/delete/${vo.no }?page=${map.currentPage }&kwd=${map.keyword }" class="del" style="background-image:url(${pageContext.request.contextPath }/assets/images/recycle.png)">삭제</a>
								</c:if>
								</sec:authorize>
							</td>
							</tr>
					</c:forEach>
									
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${map.prevPage > 0 }" >
							<li><a href="${pageContext.request.contextPath }/board?page=${map.prevPage }&kwd=${map.keyword }">◀</a></li>
						</c:if>
						
						<c:forEach begin="${map.beginPage }" end="${map.beginPage + map.listSize - 1 }" var="page">
							<c:choose>
								<c:when test="${map.endPage < page }">
									<li>${page }</li>
								</c:when> 
								<c:when test="${map.currentPage == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise> 
									<li><a href="${pageContext.request.contextPath }/board?page=${page }&kwd=${map.keyword }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${map.nextPage > 0 }" >
							<li><a href="${pageContext.request.contextPath }/board?page=${map.nextPage }&kwd=${map.keyword }">▶</a></li>
						</c:if>	
					</ul>
				</div>				
				<div class="bottom">
				<sec:authorize access="isAuthenticated()">
						<a href="${pageContext.request.contextPath }/board/write?p=${map.currentPage }&kwd=${map.keyword }" id="new-book">글쓰기</a>
					</sec:authorize>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>