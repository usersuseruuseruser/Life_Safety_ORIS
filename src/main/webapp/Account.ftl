<html>
<#include "base.ftl">
<#macro title>Account page</#macro>
<#macro content>
    <h1><strong></strong>${user.name}</h1>
    <#if (user.profilePictureUrl)??>
            <img src=${user.profilePictureUrl}>
        <#else>
        Здесь будет фото вашего профиля
    </#if>
    <p><strong>Email:</strong> ${user.email!"Не указано"}</p>
    <p><strong>Self Info:</strong> ${user.selfInfo!"Не указано"}</p>
    <a href="edit">Изменить информацию</a>
</#macro>
</html>
