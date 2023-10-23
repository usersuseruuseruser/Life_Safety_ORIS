<html>
<#include "base.ftl">
<head>
    <script src = "/js/findUser.js"></script>
    <script src = "/js/filter.js"></script>
</head>
<#macro title>Account page</#macro>
<#macro content></#macro>

<#if users?size != 1>
    <input type="text" id="searchUsername">
    <button id="ajax-button">Search User</button>
    <input class="form-control mb-3" id="myInput" type="text" placeholder="Универсальный поиск">
    <form action="/users" method="post" class="mb-3">
        <div class="input-group">
            <label for="sorting" class="input-group-text">Сортировать:</label>
            <select class="form-select" name="sorting" id="sorting">
                <option value="byPhoto">По наличию фотографии</option>
                <option value="byEmail">По наличию email</option>
                <option value="byName">По алфавитному порядку имени</option>
            </select>
            <button type="submit" class="btn btn-secondary">Применить</button>
        </div>
    </form>
</#if>
<#if users?size != 1>
    <div class="container">
        <div class="row">
            <#list users as user>
                <div class="col mt-3 mb-3">
                    <div class="card shadow bg-light" style="width: 20rem" >
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
                </div>
            </#list>

        </div>
    </div>
<#else>
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
</#if>
<#if users?size == 1 && username?? && users[0].name == username>
    <a class="btn btn-primary" href="/edit" role="button">Изменить информацию</a>
</#if>
</div>

</html>
