<html>
<#include "base.ftl">
<head>
    <script src = "/js/findUser.js"></script>
</head>
<#macro title>Account page</#macro>
<#macro content></#macro>
<#if users?size != 1>
    <input type="text" id="searchUsername">
    <button id="ajax-button">Search User</button>
</#if>
<div id="searchResults"></div>
<div class="user-content">
<#list users as user>
    <h1><strong>${user.name}</strong></h1>
    <#if (user.profilePictureUrl)??>
        <img src=${user.profilePictureUrl}>
    <#else>
        Здесь будет фото вашего профиля
    </#if>
    <p><strong>Email:</strong> ${user.email!"Не указано"}</p>
    <p><strong>Self Info:</strong> ${user.selfInfo!"Не указано"}</p>

    <#if users?size == 1 && username?? && user.name == username>
        <a href="/edit">Изменить информацию</a>
    </#if>
</#list>
</div>

</html>
