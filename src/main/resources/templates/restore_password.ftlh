<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search</title>
    <link rel="icon" href="data:,">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/restore.css">
</head>
<body>
<h2 class="text-center">Введите данные для восстановления пароля</h2>
<form action="/restore-password" class="row g-3" method="post">
    <div class="col-auto search">
        <div class="mb-3">
            <label for="email">Введите емеил:</label>
            <input type="email" class="form-control" name="email" placeholder="email" id="email" required autofocus>
            <button type="submit" class="btn btn-primary get">Получить</button>
            <#if dto??>
                <div>
                    <a href="/restore-password/${dto}">Ссылка на ваш новый пароль</a>
                </div>
            </#if>
        </div>
    </div>
</form>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>