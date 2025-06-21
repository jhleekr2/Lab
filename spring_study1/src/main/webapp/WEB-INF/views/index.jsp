<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSRF 토큰 - 백엔드에서 넘어옴 -->
<meta name="_csrf" content="${_csrf.token }">
<meta name="_csrf_header" content="${_csrf.headerName }">
<title>메인 페이지</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/style.css">
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="container">
	<div id="menuAdmin">
		<h2 id="menuAdminH2">공지사항</h2>
		
		<!-- 특별한 기능(jstl라이브러리 이용해서, 세션에 있는 변수를 세팅 조건을 겁니다.) -->
		<!-- 세션 공간에 저장되어 있는 "MANAGER"의 값이 true일떄 작성이라는 버튼이 보이게끔 할 것임 -->
		<c:if test="${MANAGER == true }">
		<button type="button" onclick="location.href='${pageContext.request.contextPath }/noticeAddPage'">공지사항 작성</button>			<!-- location.href="localhost:8080/noticeAddPage -->
		</c:if>
	<div id="menuList">
	<!-- script.js innerHTML에 의해 다음의 태그가 생성된다. -->
	<!--  <div class="menu-item"></div>
			<a href="#" class="menu-link" style="text-decoration:none;color:black;">
				<h3>${menu.title}</h3>
				<p>${menu.content}</p>
				<small>작성자:${menu.writer},작성일:${menu.indate},조회수:${menu.count}</small>
			</a>
			<br>
			<br>
	-->
	<!-- 이때 XSS취약점에 주의해야 한다 -->
	</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="${pageContext.request.contextPath }/resources/js/script.js"></script>

</body>
</html>