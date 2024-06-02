<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="board">
                <form id="search" action="${pageContext.servletContext.contextPath}/board" method="post">
                	<input type="hidden" name ="a" value="search">
                    <input type="text" id="kwd" name="kwd">
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
					<c:set var="count" value="${fn.length(list) }"/>
					<c:forEach items='${list }' var='vo' varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>	
							<td style="text-align:left; padding-left:${20*vo.depth }px">
							<c:if test='${vo.depth > 0 }'>
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'/>
								
							</c:if>
								<a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.user_name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							
							<c:if test='${vo.user_no == authUser.no }'>
								<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}&user_no=${authUser.no}" class="del">삭제</a></td>
							</c:if>						
							</tr>
					</c:forEach>
									
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
								
				<div class="bottom">
		<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>