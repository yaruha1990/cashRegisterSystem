<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

<h2>Update user</h2>
<c:if test="${sessionScope.role != 'admin'}">
    <c:out value="Page is available as you didn't login as admin"></c:out>
    <input type="button" value="Back" onclick="history.back()">
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <form method="post" action="/updateUser">
        <label for="id"><input type="text" disabled name="id" id="id" value="${requestScope.user.id}">ID</label><br>
        <label for="login"><input type="text" name="login" id="login" value="${requestScope.user.login}">Login</label><br>
        <label for="password"><input type="text" disabled name="password" id="password" value="${requestScope.user.password}">Password</label><br>
        <label for="role">Set role</label>
        <select name="role" id="role">
            <option>admin</option>
            <option>cashier</option>
            <option>senior cashier</option>
            <option selected>merchant</option>
        </select>
        <input type="text" hidden name="userId" value="${requestScope.user.id}">
        <input type="text" hidden name="userPassword" value="${requestScope.user.password}">
        <input type="submit" value="Update"/>
    </form>
</c:if>

</body>
</html>
