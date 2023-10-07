<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
</head>
<body>

<h1>Main Page</h1>

<#if session?? && session["isUserLoggedIn"] == true>
    <a href="/account">Личный кабинет</a>
<#else>
    <a href="/login">Зарегистрироваться</a>
</#if>

</body>
</html>
