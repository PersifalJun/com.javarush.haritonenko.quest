<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Квест</title>
</head>
<body>
<h2>${question.text}</h2>

<c:forEach var="answer" items="${answers}">
    <form method="get" action="${pageContext.request.contextPath}/quest-servlet">
        <input type="hidden" name="answerId" value="${answer.id}" />
        <input type="hidden" name="step" value="1" />
        <button type="submit">${answer.buttonText}</button>
    </form>
</c:forEach>

<c:if test="${question.answers.isEmpty()}">
    <p><b>Игра завершена.</b></p>
    <a href="${pageContext.request.contextPath}/quest-servlet">Начать заново</a>
</c:if>

<hr>
<p>Игрок: <b>${playerName}</b> | Сыграно игр: <b>${gamesPlayed}</b></p>
</body>
</html>
