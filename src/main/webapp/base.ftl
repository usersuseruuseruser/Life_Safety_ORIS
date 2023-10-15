<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
</head>
<body>
<div id="header">
    <#if isLoggedIn??>
        <a href="/account">Личный кабинет</a>
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
