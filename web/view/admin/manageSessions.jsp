<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage sessions</title>
</head>
<body>

<c:if test="${sessionScope.role != 'admin'}">
    <c:out value="Page is available as you didn't login as admin"></c:out><br>
    <a href="/">To index page</a>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <c:forEach items="${applicationScope.sessions}" var="entry">
        <form method="post" action="/adminLogout">
            <ul>
                <li>Login: <c:out value="${entry.value}"></c:out></li>
                <li>SessionId: <c:out value="${entry.key.id}"></c:out></li>
                <input type="text" hidden name="sessionId" value="${entry.key.id}">
                <input type="submit" value="logout">
                <input type="button" value="Back" onclick="history.back()">
                <hr/>
            </ul>
        </form>
    </c:forEach>
</c:if>

</body>
</html>
