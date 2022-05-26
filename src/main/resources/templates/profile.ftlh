<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/productsStyle.css">
    <title>Product</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"><@spring.message "products"/></a>
        <div class="test">
            <div class="child2">
                <a href="/"><@spring.message "go_to_products"/></a>
                <a class="buttons-link" href="/registration">
                    <button class="buttons"><@spring.message "sign_up"/></button>
                </a>
                <a class="buttons-link" href="/login">
                    <button class="buttons"><@spring.message "sign_in"/></button>
                </a>
                <a class="buttons-link" href="/profile">
                    <button class="buttons"><@spring.message "profile"/></button>
                </a>
                <a class="buttons-link" href="/cart">
                    <button class="buttons"><@spring.message "my_cart"/></button>
                </a>
            </div>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="buttons"><@spring.message "log_out"/></button>
            </form>
        </div>
    </div>
</nav>

<#if dto??>
    <h1 class="data"><@spring.message "your_credentials"/>!</h1>
    <p><@spring.message "your_name"/>: ${dto.name}</p>
    <p><@spring.message "your_email"/>: ${dto.email}</p>
    <p><@spring.message "your_phone"/>: ${dto.phoneNumber}</p>
    <p><@spring.message "your_address"/>: ${dto.address}</p>
</#if>
<a href="/"><@spring.message "go_to_products"/></a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>