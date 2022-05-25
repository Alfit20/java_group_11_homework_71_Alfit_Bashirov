<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/registrationStyle.css">
    <link rel="icon" href="data:,">
    <title>Document</title>
</head>
<body>
<form action="/login" class="login-form" method="post">

    <div class="login-page">
        <div>
            <#if error!false >
                <p style="color:red">Неверный эмеил пользователя или пароль!</p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form">
            <input type="email" name="username" placeholder="email" value="${(dto.email)!''}"/>
            <input type="password" name="password" placeholder="password"/>
            <button type="submit">login</button>
            <p class="message">Not registered? <a href="/registration">Create an account</a></p>
            <p class="message">Забыл пароль? <a href="/restore-password">Восстановить пароль</a></p>

        </div>
    </div>
</form>
</body>
</html>