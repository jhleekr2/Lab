<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeAdd/style.css">
</head>
<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- javascript코드로 form태그의 action기능을 대신하는 기능을 만들음.
일종의 restapi개발방식 -->
<form id="menuForm">
	<div id="container">
		<div id="menuAdmin">
			<h2 id="menuAdminH2">공지사항 작성</h2>
			<br>
			<label for="memID">회원아이디</label>
			<input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${username}" readonly>
			<br>
			<label for="title">제목</label>
			<input type="text" id="title" name="title" placeholder="제목" maxlength="10">
			<br>
			<label for="content">내용</label>
			<input type="text" id="content" name="content" placeholder="내용" maxlength="30">
			<br>
			<label for="content">작성자</label>
			<input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${writer }" readonly>
			<br>
			
			<input type="hidden" id="indate" name="indate">
			<input type="hidden" id="count" name="count">
			<button type="button" id="buttonSubmit">확인</button>
		</div>
	</div>
</form>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/noticeAdd/script.js"></script>
</body>
</html>