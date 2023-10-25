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
                                <a href="/test?id=${test.testId}" class="btn btn-primary float-right">Просмотр</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</#macro>
</html>
