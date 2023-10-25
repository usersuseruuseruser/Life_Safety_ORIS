<html>
<#include "base.ftl">
<#macro title>Thread page</#macro>
<#macro content>
<div class="container mt-5">
    <div class="row mb-4">
        <div class="col-md-4">
            <img src="${headInfo.image_url}" alt="Thread Image" class="img-fluid rounded">
        </div>
        <div class="col-md-8">
            <h1>${headInfo.title}</h1>
            <p>${headInfo.description}</p>
        </div>
    </div>
    <#if isLoggedIn>
        <div class="container mt-5">
            <div class="container mt-2">
                <div class="d-flex justify-content-center align-items-center">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            data-bs-whatever="">Ответить в тред
                    </button>
                </div>
            </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/thread" method="POST">
                                <label for="threadDescription">Ваше сообщение</label>
                                <textarea class="form-control" id="threadDescription" name="text" rows="3"></textarea>
                                <input type="hidden" name="threadId" value="${headInfo.forum_thread_id}">
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть
                                    </button>
                                    <button type="submit" class="btn btn-primary">Отправить</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </#if>

    <div class="container mt-5">
        <h2>Сообщения:</h2>
        <ul class="list-group">
            <#if messages?size != 0>
                <#list messages as message>
                    <li class="list-group-item">
                        <strong><a href="/users/${message.username}">${message.username}</a> </strong>: ${message.text}
                        <div class="text-muted" style="font-size: 0.8em;">${message.data?string["yyyy-MM-dd HH:mm:ss"]}</div>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
    </#macro>
</html>
