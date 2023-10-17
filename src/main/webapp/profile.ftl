<html>
<head>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src = "/js/findUser.js"></script>
</head>
<#if users?size != 1>
    <input type="text" id="searchUsername">
    <button id="ajax-button">Search User</button>
</#if>
<div id="searchResults"></div>
<#include "base.ftl">

<#macro title>Account page</#macro>

<#macro content>
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

        <#-- Проверка условий для отображения ссылки на редактирование -->
            <#if users?size == 1 && user.name == username>
                <a href="/edit">Изменить информацию</a>
            </#if>
        </#list>
    </div>
</#macro>

</html>
