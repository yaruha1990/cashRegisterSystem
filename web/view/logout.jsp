<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>

<c:if test="${empty sessionScope.login}">
    <c:redirect url = "/"/>
</c:if>

<div class="row" style="padding: 20px">
    <div class="form-group col-lg-6">
        <form method="post" action="/identification">
            Hello, <c:out value="${sessionScope.login}"></c:out>
            <button type="submit" class="btn btn-success" value="logout">Logout</button>
        </form>
    </div>
</div>
</body>
</html>
