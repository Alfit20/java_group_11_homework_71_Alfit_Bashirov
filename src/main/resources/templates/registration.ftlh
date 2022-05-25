<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="css/registrationStyle.css">
    <link rel="icon" href="data:,">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="/registration" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3>Registration</h3>
            <input type="text" name="name" placeholder="name" value="${(dto.name)!''}" required autofocus/>
            <input type="email" name="email" placeholder="email" value="${(dto.email)!''}" required/>
            <input type="password" name="password" placeholder="password" value="" required/>
            <input type="text" name="phoneNumber" placeholder="phone" value="${(dto.phone)!''}" required/>
            <input type="text" name="address" placeholder="address" value="${(dto.address)!''}" required/>
            <button type="submit">Registration</button>
            <#if dto.email??>
                <p style="color:red">${dto.email} is already exists!</p>
            </#if>
            <div>
                <#if errors?? >
                    <p>Входные данные с ошибками!</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
        </form>
    </div>
</div>


<script src="/js/main.js"></script>
</body>
</html>