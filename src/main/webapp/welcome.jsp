<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добро пожаловать в квест</title>
</head>
<body>
<h1>Добро пожаловать в наш квест!</h1>

<form method="post" action="${pageContext.request.contextPath}/quest-servlet">
    <label for="playerName">Введите ваше имя:</label><br>
    <input type="text" id="playerName" name="playerName" required><br><br>
    <button type="submit">Начать квест</button>
</form>

<br>
<p>Сыграно игр: <strong>${gamesPlayed}</strong></p>
</body>
</html>
