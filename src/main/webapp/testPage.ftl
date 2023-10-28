<html>
<#include "base.ftl">
<#macro title>Test page</#macro>
<#macro content>
<div class="container mt-5">
    <div class="row mb-4">
        <div class="col-md-4">
            <img src="${headInfo.imageUrl}" alt="Thread Image" class="img-fluid rounded">
        </div>
        <div class="col-md-8">
            <h1>${headInfo.title}</h1>
        </div>
    </div>
</div>
    <form action="/testResults" method="post" class="mt-5 ms-5">
        <#list questions as question>
            <div class="mb-3">
                <h5 class="mb-2 fw-bold">${question.text}</h5>
                <#list answers as answer>
                    <#if answer.questionId == question.questionId>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="answerGroup_${question.questionId}" value="${answer.answer_id}" id="answer_${answer.answer_id}">
                            <label class="form-check-label fs-5" for="answer_${answer.answer_id}">${answer.text}</label>
                        </div>
                    </#if>
                </#list>
            </div>
        </#list>
        <input type="hidden" name="questions_length" value="${questions?size}">
        <button type="submit" class="btn btn-primary mt-3">Отправить</button>
    </form>

</#macro>
</html>
