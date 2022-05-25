<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="${.locale}">
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
            <h3><@spring.message "registration"/></h3>
            <input type="text" name="name" placeholder="<@spring.message "name"/>" value="${(dto.name)!''}" required autofocus/>
            <input type="email" name="email" placeholder="<@spring.message "email"/>" value="${(dto.email)!''}" required/>
            <input type="password" name="password" placeholder="<@spring.message "password"/>" value="" required/>
            <input type="text" name="phoneNumber" placeholder="<@spring.message "phone"/>" value="${(dto.phone)!''}" required/>
            <input type="text" name="address" placeholder="<@spring.message "address"/>" value="${(dto.address)!''}" required/>
            <button type="submit"><@spring.message "registration"/></button>
            <#if dto.email??>
                <p style="color:red">${dto.email}<@spring.message "is_already_exists"/>!</p>
            </#if>
            <div>
                <#if errors?? >
                    <p><@spring.message "input_data_with_errors"/>!</p>
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