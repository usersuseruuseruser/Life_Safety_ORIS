<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
