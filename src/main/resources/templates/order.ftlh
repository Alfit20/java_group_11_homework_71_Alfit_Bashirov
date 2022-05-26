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
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="/css/productsStyle.css">
    <title>Order</title>
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

<h1><@spring.message "please_leave_review"/></h1>

<div class="container" id="container">
    <div class="row row-cols-2 row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-sm-1 g-4">
        <#list products as product>
            <div class="col" id="itemList">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <a href="${product.product.id}"><p>${product.product.name}</p></a>
                        <a href="${product.product.id}"><img src="/images/${product.product.image}"
                                                             class="card-img-top"></a>
                        <p class="price">${product.product.price} сом</p>
                        <p class="price">${product.product.description}</p>
                    </div>
                    <a href="${product.product.id}">
                        <button type="button" class="btn btn-info"><@spring.message "give_feedback"/></button>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</div>

<script>
    // async function handleClick(e) {
    //     e.preventDefault();
    //     const form = document.getElementById("review_form");
    //     const data = new FormData(form)
    //     const options = {
    //         method: 'post',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: data
    //     };
    //     await fetch("http://localhost:8080/review/" + e.target.id, options);
    // }
    //
    // const buttons = document.querySelectorAll('.btn');
    // buttons.forEach(btn => {
    //     btn.addEventListener('click', handleClick)
    // })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>