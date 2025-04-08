<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Квест</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<div class="card p-4 shadow">
    <h2 class="mb-4">${question.text}</h2>

    <c:forEach var="answer" items="${answers}">
        <form method="get" action="${pageContext.request.contextPath}/quest-servlet" class="mb-2">
            <input type="hidden" name="answerId" value="${answer.id}" />
            <input type="hidden" name="step" value="1" />
            <button type="submit" class="btn btn-outline-primary w-100">
                    ${answer.buttonText}
            </button>
        </form>
    </c:forEach>

    <c:if test="${question.answers.isEmpty()}">
        <p class="mt-4"><b>Игра завершена.</b></p>
        <a href="${pageContext.request.contextPath}/quest-servlet" class="btn btn-success">Начать заново</a>
    </c:if>

    <hr>
    <p class="text-muted mt-3">Игрок: <b>${playerName}</b> | Сыграно игр: <b>${gamesPlayed}</b></p>
</div>

</body>
</html>
