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
    <div class="card" style="width: 20rem;">
        <#if (user.profilePictureUrl)??>
            <img src="${user.profilePictureUrl}" class="card-img-top" alt="${user.name}'s profile picture">
        <#else>
            <div class="card-header">
                Фото еще не загружено
            </div>
        </#if>
        <div class="card-body">
            <h5 class="card-title"><H3><strong>${user.name}</strong></H3></h5>
            <p class="card-text"><strong>Email:</strong> ${user.email!"Не указано"}</p>
            <p class="card-text"><strong>Self Info:</strong> ${user.selfInfo!"Не указано"}</p>
        </div>
    </div>
</#list>
<#if users?size == 1 && username?? && users[0].name == username>
    <a class="btn btn-primary" href="/edit" role="button">Изменить информацию</a>
</#if>
</div>

</html>
