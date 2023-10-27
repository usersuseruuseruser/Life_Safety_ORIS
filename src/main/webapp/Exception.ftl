<html>
<#include "base.ftl">
<#macro title>Main page</#macro>
<#macro content>
    <div class="d-flex justify-content-center align-items-center">
        <h2>Exception occurred!</h2>
    </div>
    <div class="d-flex justify-content-center align-items-center">
        <h3>${message}</h3>
    </div>
    <br>
    <div class="d-flex justify-content-center align-items-center">
        <h3>Код ошибки: ${code}</h3>
    </div>
</#macro>
</html>
