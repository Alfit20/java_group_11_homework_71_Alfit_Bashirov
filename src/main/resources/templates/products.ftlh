<!DOCTYPE html>
<html lang="en">
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
        <a class="navbar-brand">Products</a>
        <div class="test">
            <div class="child2">
                <a class="buttons-link" href="/registration">
                    <button class="buttons">Зарегистрироваться</button>
                </a>
                <a class="buttons-link" href="/login">
                    <button class="buttons">Войти</button>
                </a>
                <a class="buttons-link" href="/profile">
                    <button class="buttons">Профиль</button>
                </a>
                <a class="buttons-link" href="/cart">
                    <button class="buttons">Моя корзина</button>
                </a>
            </div>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="buttons">Выйти</button>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <form action="/search" class="form" id="search" method="get">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="col-auto">
            <select name="searchBy" id="searchBy">
                <option value="name">По наименованию</option>
                <option value="price">По цене</option>
                <option value="category">По категориям</option>
                <option value="description">По описанию</option>
            </select>
            <input type="text" name="search">
            <input type="submit" value="Search">
        </div>
    </form>
</div>

<div class="container" id="container">
    <h1 class="sub-title-2 text-uppercase text-center py-4">Интернет-магазин Компьютерной техники</h1>
    <#--    <#list sessionAttributes as key, value>-->
    <#--        <div class="border"><span class="key-cell">${key}</span><span class="value-cell">${value}</span></div>-->
    <#--    </#list>-->
    <div class="row row-cols-2 row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-sm-1 g-4">
        <form action="/cart" method="post" id="cart_form">
            <#list products as product>
            <div class="col" id="itemList">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <a href="${product.id}"><p>${product.name}</p></a>

                        <a href="${product.id}"><img src="/images/${product.image}" class="card-img-top"></a>
                        <p class="price">${product.price} сом</p>
                        <p class="price">${product.description}</p>
                    </div>
                    <button type="submit" class="cart" id="${product.id}">В корзину
                        <input type="hidden" name="id" value="${product.id}"/>
                    </button>

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