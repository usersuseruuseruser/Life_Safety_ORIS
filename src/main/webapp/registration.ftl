<html>
<#include "base.ftl">
<#macro title>Registration page</#macro>
<#macro content>
    <form action="registration" method = "post">
        Name:
        <input type="text" name="name">
        <br>
        Login:
        <input type="text" name="login"/>
        <br>
        Password:
        <input type="password" name="password">
        <br>
        <input type="submit" value="Sign up">
    </form>
</#macro>
</html>
