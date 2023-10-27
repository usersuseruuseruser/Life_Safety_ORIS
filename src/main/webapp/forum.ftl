<#include "base.ftl">
<head>
    <link rel="stylesheet" href="css/maxForumImgHeight.css">
</head>
<#macro title>Forum Page</#macro>
<#macro content>

    <div class="d-flex justify-content-center align-items-center">
        <h2>Текущие форумы</h2>
    </div>
    <#if isLoggedIn>
        <div class="container mt-2">
            <div class="d-flex justify-content-center align-items-center">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                        data-bs-whatever="">Создать новый тред
                </button>
            </div>
        </div>
    </#if>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/forum" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="threadTitle">Заголовок треда</label>
                            <input type="text" class="form-control" id="threadTitle" name="threadTitle">
                        </div>
                        <div class="form-group">
                            <label for="threadDescription">Описание треда.</label>
                            <textarea class="form-control" id="threadDescription" name="threadDescription"
                                      rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="threadImage">Картинка: </label>
                            <input type="file" class="form-control-file" id="threadImage" name="threadImage">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-primary">Создать</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="container mt-5">
        <div class="row">
            <#if threads??>
                <#list threads as thread>
                    <div class="col mt-3 mb-3">
                        <div class="card shadow bg-light" style="width: 20rem">
                            <#if (thread.image_url)??>
                                <img src="${thread.image_url}" class="card-img-top thread-image" alt="${thread.title} image">
                            <#else>
                                <div class="card-header">
                                    Изображение еще не загружено
                                </div>
                            </#if>
                            <div class="card-body">
                                <h5 class="card-title"><strong>${thread.title}</strong></h5>
                                <p class="card-text">${thread.description!"Описание не указано"}</p>
                                <a href="/forum?id=${thread.forum_thread_id}" class="btn btn-primary float-right">Просмотр</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>

</#macro>
