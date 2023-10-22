<html>
<#include "base.ftl">
<#macro title>Edit account info</#macro>
<#macro content>
    <form action="edit" method="post" enctype="multipart/form-data">
        Name (current name is ${user.name}):
        <input type="text" name="name"><br>

        Email(current email is ${user.email!"no email yet"}):
        <input type="text" name="email"><br>

        SelfInfo(current selfInfo is ${user.selfInfo!"no self info yet"}):
        <input type="text" name="selfInfo"><br>

        <input type="file" name="picture"><br>

        <input type="submit" value="Обновить">
    </form>

</#macro>
</html>
