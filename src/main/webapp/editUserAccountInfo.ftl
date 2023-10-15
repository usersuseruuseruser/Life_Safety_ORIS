<html>
<#include "base.ftl">
<#macro title>Edit account info</#macro>
<#macro content>
    <form action="account/edit" method="post">
        Name:
        <input type="text" name="name">
        <br>
        Email:
        <input type="text" name="email">
        <br>
        SelfInfo:
        <input type="text" name="selfInfo">
        <br>
        <input type="submit" value="Update">
    </form>
</#macro>
</html>
