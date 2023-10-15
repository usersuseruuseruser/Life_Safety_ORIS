<html>
<#include "base.ftl">
<#macro title>Account page</#macro>
<#macro content>
    <h1><strong></strong>${user.name}</h1>
    <p><strong>Email:</strong> ${user.email!"Не указано"}</p>
    <p><strong>Self Info:</strong> ${user.selfInfo!"Не указано"}</p>
    <a href="account/edit">Изменить информацию</a>
</#macro>
</html>
