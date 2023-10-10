<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>Main Page</h1>

<#-- Если пользователь вошел в систему -->
<#if isLoggedIn>
    <a href="/Account">Личный кабинет</a>
<#else>
    <a href="/login">Залогиниться</a>
    <a href="/registration">Зарегистрироваться</a>
</#if>

</body>
</html>
