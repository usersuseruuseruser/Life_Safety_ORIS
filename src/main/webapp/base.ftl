<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
</head>
<body>
<div id="header">
    <a href="/main">На главную</a>
    <#if isLoggedIn == true>
        <a href="/users/${username}">Личный кабинет</a>
        <a href="/logout">Выйти из аккаунта</a>
    <#else>
        <a href="/registration">Зарегистрироваться</a>
        <a href="/login">Войти</a>
    </#if>
</div>

<div class="content1">
    <div class="content"><@content></@content></div>
</div>

</body>
</html>
