<html>
<#include "base.ftl">
<#macro title>Tests page</#macro>
<#macro content>

    <div class="d-flex justify-content-center align-items-center">
        <h2>Список существующих тестов</h2>
    </div>


    <div class="container mt-5">
        <div class="row">
            <#if tests??>
                <#list tests as test>
                    <div class="col mt-3 mb-3">
                        <div class="card shadow bg-light" style="width: 20rem">
                            <#if (test.imageUrl)??>
                                <img src="${test.imageUrl}" class="card-img-top test-image" alt="${test.title} image">
                            <#else>
                                <div class="card-header">
                                    Изображение еще не загружено
                                </div>
                            </#if>
                            <div class="card-body">
                                <h5 class="card-title"><strong>${test.title}</strong></h5>

                                <#if isLoggedIn>
                                    <a href="/test?id=${test.testId}" class="btn btn-primary float-right">Просмотр</a>
                                <#else>
                                    <button class="btn btn-primary float-right" data-bs-toggle="modal" data-bs-target="#loginModal">Просмотр</button>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel">Внимание!</h5>
                    <button type="button" class="close" data-bs-dismiss="modal"="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Сначала войди в систему.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"="modal">Закрыть</button>
                    <a href="/registration" class="btn btn-primary">Зарегистрироваться</a>
                    <a href="/login" class="btn btn-success">Залогиниться</a>
                </div>
            </div>
        </div>
    </div>
</#macro>
</html>
