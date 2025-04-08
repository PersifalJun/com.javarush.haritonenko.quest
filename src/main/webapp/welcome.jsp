<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать в квест</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<div class="card p-4 shadow">
    <h1 class="mb-4 text-primary">Добро пожаловать в наш квест!</h1>

    <form method="post" action="${pageContext.request.contextPath}/quest-servlet">
        <div class="mb-3">
            <label for="playerName" class="form-label">Введите ваше имя:</label>
            <input type="text" id="playerName" name="playerName" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Начать квест</button>
    </form>

    <hr>
    <p class="text-muted mt-3">Сыграно игр: <strong>${gamesPlayed}</strong></p>
</div>

</body>
</html>
