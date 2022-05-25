<#import "/spring.ftl" as spring />
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
    <link rel="stylesheet" href="/css/productsStyle.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="icon" href="data:,">
    <title>Product</title>
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

<#--    <div class="row row-cols-2 row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-sm-1 g-4">-->
<#--        <div class="col" id="itemList">-->
<#--            <div class="card h-100">-->
<#--                <div class="card-body text-center">-->
<#--                    <p>${product.name}</p>-->
<#--                    <img src="/images/${product.image}" class="card-img-top">-->
<#--                    <p class="price">${product.price} сом</p>-->
<#--                    <p class="price">${product.description}</p>-->
<#--                </div>-->
<#--                <form action="/cart" method="post" id="cart_form">-->
<#--                    <button type="submit" class="cart text-center" id="${product.id}">В корзину-->
<#--                        <input type="hidden" name="id" value="${product.id}"/>-->
<#--                    </button>-->
<#--                </form>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->

<div class="row row-cols-1 row-cols-md-1 g-2  mx-auto d-block main" style="width: 20rem;">
    <div class="col main-block">
        <div class="card text-center">
            <img src="/images/${product.image}" class="card-img-top">
            <div class="card-body block">
                <p class="price">${product.price} сом</p>
                <p class="price">${product.description}</p>
                <form action="/cart" method="post" id="cart_form">
                    <button type="submit" class="cart text-center" id="${product.id}">В корзину
                        <input type="hidden" name="id" value="${product.id}"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class='closed text-center' onclick='showCloseItem(this)'>
    <p class="review">Отзывы(${count})</p>
    <div class='hiddenItem'>
        <#list reviews as review>
            <div class="container">
                <span>${review.author.email}</span>
                <p>${review.text}</p>
                <p>${review.dateAdded}</p>
            </div>
        </#list>
    </div>
</div>


<form action="/review/${product.id}" id="review_form" method="post">
    <#if dto??>
        <p style="color:red">Чтобы написать отзыв нужно сначала оформить заказ</p>
    </#if>
    <label for="message-text" class="col-form-label">Message:</label>
    <textarea rows="5" cols="10" class="form-control"
              id="message-text" name="text" required minlength="2"></textarea>
    <button type="submit" class="btn btn-success">Оставить отзыв</button>
</form>


<script>
    function showCloseItem(item) {
        $(item).toggleClass('closed', 'opened');
    };
</script>
<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>