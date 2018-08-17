<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Administrator page</h1>

    <c:if test="${sessionScope.role != 'admin'}">
        <c:out value="Page is available as you didn't login as admin"></c:out><br>
        <a href="/">To index page</a>
    </c:if>

    <c:if test="${sessionScope.role == 'admin'}">
        <form method="post" action="manageSessions.jsp">
            <label for="sessions">Click here to manage user sessions</label>
            <input type="submit" id="sessions" value="Manage sessions">
        </form><br>

        <form method="get" action="/manageAccount">
            <label for="accounts">Click here to manage user accounts</label>
            <input type="submit" id="accounts" value="Manage accounts">
        </form><br>

        <form method="post" action="/identification">
            Hello, <c:out value="${sessionScope.login}"></c:out>
            <input type="submit" value="logout">
        </form><br>
    </c:if>

</body>
</html>
