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
    <title>Products</title>
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
<div class="container">
    <form action="/search" class="form" id="search" method="get">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="col-auto">
            <select name="searchBy" id="searchBy">
                <option value="name"><@spring.message "by_name"/></option>
                <option value="price"><@spring.message "by_price"/></option>
                <option value="category"><@spring.message "by_category"/></option>
                <option value="description"><@spring.message "by_description"/></option>
            </select>
            <input type="text" name="search">
            <input type="submit" value="<@spring.message "search"/>">
        </div>
    </form>
</div>

<div class="container" id="container">
    <h1 class="sub-title-2 text-uppercase text-center py-4"><@spring.message "title"/></h1>
    <#--    <#list sessionAttributes as key, value>-->
    <#--        <div class="border"><span class="key-cell">${key}</span><span class="value-cell">${value}</span></div>-->
    <#--    </#list>-->
    <div class="row row-cols-2 row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-sm-1 g-4">
<#--        <form action="/cart" method="post" id="cart_form">-->
            <#list products as product>
                <form action="/cart/${product.id}" method="post" id="cart_form">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="col" id="itemList">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <a href="${product.id}"><p>${product.name}</p></a>

                        <a href="${product.id}"><img src="/images/${product.image}" class="card-img-top"></a>
                        <p class="price">${product.price} сом</p>
                        <p class="price">${product.description}</p>
                    </div>
                    <input type="number" name="quantity" value="1" min="1" max="10" class="quantity" id="quantity"/>
                    <button type="submit" class="cart" id="${product.id}"><@spring.message "in_cart"/></button>



                </div>
            </div>
        </form>
        </#list>
    </div>
</div>

<#include "pagination.ftlh">

<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>