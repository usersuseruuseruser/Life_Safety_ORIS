<html>
<#include "base.ftl">
<#macro title>Edit account info</#macro>
<#macro content>
    <div class="container mt-5">
        <form action="edit" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="name" class="form-label">Name (current name is ${user.name}):</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email (current email is ${user.email!"no email yet"}):</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>

            <div class="mb-3">
                <label for="selfInfo" class="form-label">SelfInfo (current selfInfo is ${user.selfInfo!"no self info yet"}):</label>
                <input type="text" class="form-control" id="selfInfo" name="selfInfo">
            </div>

            <div class="mb-3">
                <label for="picture" class="form-label">Upload a picture:</label>
                <input type="file" class="form-control" id="picture" name="picture">
            </div>

            <button type="submit" class="btn btn-primary">Обновить</button>
        </form>
    </div>
</#macro>
</html>
