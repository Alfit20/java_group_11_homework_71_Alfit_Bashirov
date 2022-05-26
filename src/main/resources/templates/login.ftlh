<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/registrationStyle.css">
    <link rel="icon" href="data:,">
    <title>Login</title>
</head>
<body>
<form action="/login" class="login-form" method="post">

    <div class="login-page">

        <div>

            <#if error!false >
                <p style="color:red"><@spring.message "invalid_username_or_password"/>!</p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form">
            <input type="email" name="username" placeholder="<@spring.message "email"/>" value="${(dto.email)!''}"/>
            <input type="password" name="password" placeholder="<@spring.message "password"/>"/>
            <button type="submit"><@spring.message "login"/></button>
            <p class="message"><@spring.message "not_registered"/>? <a href="/registration"><@spring.message "create_an_account"/></a></p>
            <p class="message"><@spring.message "forgot_password"/>? <a href="/restore-password"><@spring.message "restore_password"/></a></p>
            <a href="/"><@spring.message "go_to_products"/></a>
        </div>
    </div>
</form>
</body>
</html>