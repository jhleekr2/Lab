<%--
  Created by IntelliJ IDEA.
  User: 12345
  Date: 2025-05-17
  Time: 오후 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
    <title>Hello World</title>
</head>
<body>
    <h2>Hello World</h2>
    <hr>
    현재 날짜와 시간은 <%=java.time.LocalDateTime.now()%>입니다.
    <hr>
    메시지: ${msg}
</body>
</html>
