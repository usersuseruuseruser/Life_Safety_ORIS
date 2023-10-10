<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account page</title>
</head>
<body>
<h1><strong>Hello, </strong>${user.name}</h1>
<p><strong>Email:</strong> ${user.email!"Не указано"}</p>
<p><strong>Self Info:</strong> ${user.selfInfo!"Не указано"}</p>
</body>
</html>