<html>
<#include "base.ftl">
<#macro title>Login page</#macro>
<#macro content>
    <form action="login" method="post">
        Login:
        <input type="text" name="login"/>
        <br>
        Password:
        <input type="password" name="password">
        <br>
        <input type="checkbox" name="rememberMe" value="yes"> Запомнить меня
        <br>
        <input type="submit" value="Login">
    </form>
</#macro>
</html>
