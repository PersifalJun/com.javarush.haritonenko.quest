<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Квест — действие</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<div class="card p-4 shadow">
    <h2 class="mb-4">${actionText}</h2>

    <form method="get" action="${pageContext.request.contextPath}/quest-servlet">
        <input type="hidden" name="id" value="${nextId}" />
        <input type="hidden" name="step" value="2" />
        <button type="submit" class="btn btn-primary">Далее</button>
    </form>

    <hr>
    <p class="text-muted mt-3">Игрок: <b>${playerName}</b> | Сыграно игр: <b>${gamesPlayed}</b></p>
</div>

</body>
</html>
