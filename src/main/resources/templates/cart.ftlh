<#import "/spring.ftl" as spring />
<!doctype html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="icon" href="data:,">
    <#--    <link rel="icon" href="data:,">-->
    <link rel="stylesheet" href="/css/productsStyle.css">
    <title>Cart</title>
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
<table class="table table-dark table-striped">
    <form action="/order/quantity" method="post" id="quantity_form">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                   value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <thead>
        <tr>
            <th scope="col"><@spring.message "name"/></th>
            <th scope="col"><@spring.message "price"/></th>
            <th scope="col"><@spring.message "quantity"/> <input type="submit" value="<@spring.message "update"/>" class="update" id="update"></th>
            <th scope="col"><@spring.message "action"/></th>
        </tr>
        </thead>
        <tbody>
        <#list cartItems as item>
        <tr>
            <td>${item.product.name}</td>
            <td>${item.product.price}</td>
            <td><input type="number" name="quantity" min="1" max="10" value="${item.quantity}" class="quantity"
                       id="quantity"/></td>
    </form>
    <td>
        <form action="/cart/delete/${item.id}" id="delete_form" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                       value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <input type="text" name="taskId" value="${item.id}" hidden>
            <input type="submit" value="<@spring.message "delete"/>" class="delete">
        </form>
    </td>
    </tr>
    </#list>
    </tbody>
</table>



<div class="card border-primary mb-3" style="max-width: 18rem;">
    <div class="card-header"><@spring.message "totals"/></div>
    <table class="table">
        <tbody>
        <tr>
            <th><@spring.message "total_sum"/></th>
            <td>${sum}</td>
        </tr>
        </tbody>
    </table>
    <form action="/order" method="post">
        <button type="submit" class="btn btn-dark"><@spring.message "checkout"/></button>
    </form>
</div>

<script>

    // async function handleClick(e) {
    //     e.preventDefault();
    //     const form = document.getElementById("quantity_form");
    //     const data = new FormData(form)
    //     const options = {
    //         method: 'post',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: data
    //     };
    //     await fetch("http://localhost:8080/order/quantity" + data.get('quantity'), options);
    // }
    //
    // const buttons = document.getElementById('update');
    // console.log(buttons)
    // buttons.addEventListener('submit', handleClick)

    <#--    async function handleClick(e) {-->
    <#--        e.preventDefault();-->
    <#--        const form = document.getElementById("quantity_form");-->
    <#--        const data = new FormData(form)-->
    <#--        console.log(data.get('quantity'));-->
    <#--        await fetch("http://localhost:8080/cart/order" + data.get('quantity'));-->
    <#--    }-->
    <#--    let count = 1;-->
    <#--    const buttons = document.querySelectorAll('#input_');-->
    <#--    buttons.forEach(btn => {-->
    <#--        btn.setAttribute('id', 'input_' + count)-->
    <#--        count++-->
    <#--        btn.addEventListener('click', handleClick)-->
    <#--    })-->

    // let count = 1;
    // const input = document.querySelectorAll('input')
    // input.forEach(btn => {
    //     btn.setAttribute('name', 'quantity_' + count)
    //     count++
    // })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>