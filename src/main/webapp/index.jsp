<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QUEST — Главная</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<div class="card p-5 shadow text-center">
    <h1 class="text-primary mb-4"> QUEST!</h1>
    <p class="lead mb-4">Приключение начинается прямо здесь. Готов вступить в игру?</p>

    <a href="${pageContext.request.contextPath}/quest-servlet" class="btn btn-lg btn-success">
         Начать приключение
    </a>

    <hr class="my-4">
    <p class="text-muted"></p>
</div>

</body>
</html>
