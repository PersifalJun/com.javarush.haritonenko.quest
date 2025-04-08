<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Квест — действие</title>
</head>
<body>

<h2>${actionText}</h2>

<form method="get" action="${pageContext.request.contextPath}/quest-servlet">
    <input type="hidden" name="id" value="${nextId}" />
    <input type="hidden" name="step" value="2" />
    <button type="submit">Далее</button>
</form>

<hr>
<p>Игрок: <b>${playerName}</b> | Сыграно игр: <b>${gamesPlayed}</b></p>

</body>
</html>
