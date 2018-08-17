<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage accounts</title>
</head>
<body>

<h2>List of users</h2>
<jsp:include page="/view/logout.jsp"></jsp:include>
<c:if test="${sessionScope.role != 'admin'}">
    <c:out value="Page is available as you didn't login as admin"></c:out><br>
    <a href="/">To index page</a>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <c:forEach var="user" items="${requestScope.users}">
        <ul>
            <li>ID:<c:out value="${user.id}"/></li>
            <li>Login:<c:out value="${user.login}"/></li>
            <li>Password:<c:out value="${user.password}"/></li>
            <li>Role:<c:out value="${user.role}"/></li>

            <form method="post" action="/deleteUser" style="float: left">
                <input type="number" hidden name="id" value="${user.id}">
                <input type="submit" value="Delete">
            </form>

            <form method="get" action="/updateUser">
                <input type="number" hidden name="id" value="${user.id}">
                <input type="submit" value="Update">
            </form>

        </ul>
        <hr/>
    </c:forEach><br>

    <h2>Create user</h2>

    <form method="post" action="/createUser">
        <label for="login"><input type="text" name="login" id="login" value="">Login</label><br>
        <label for="password"><input type="password" name="password" id="password">Password</label><br>
        <label for="role">Set role</label>
        <select name="role" id="role">
            <option>admin</option>
            <option>cashier</option>
            <option>senior cashier</option>
            <option selected>merchant</option>
        </select>
        <input type="submit" value="Create"/>
    </form>
</c:if>

</body>
</html>
