<%--
  Created by IntelliJ IDEA.
  User: ilnur
  Date: 07.10.2023
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>Main Page</h1>

<%-- Если пользователь вошел в систему --%>

<%
  Boolean isLoggedIn = (Boolean) session.getAttribute("isUserLoggedIn");
  if (isLoggedIn != null && isLoggedIn) {
%>
<a href="/Account">Личный кабинет</a>
<%
} else {
%>
<a href="/login">Залогиниться</a>
<a href="/registration">Зарегистрироваться</a>
<%
  }
%>
</body>
</html>
