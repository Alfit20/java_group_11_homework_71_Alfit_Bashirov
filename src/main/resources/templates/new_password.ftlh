<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="${.locale}">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <#--    <link rel="stylesheet" href="css/style.css">-->
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
    <form action="" method="get">
        <select name="lang" id="lang_select">
            <option value="ru">Русский</option>
            <option value="en">English</option>
        </select>
        <button type="submit"><@spring.message "choose"/></button>
    </form>
</nav>
<h3><@spring.message "your_new_password"/>: ${newPassword}</h3>
<a href="/login"><@spring.message "go_to_login"/></a>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>