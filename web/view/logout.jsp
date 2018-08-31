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
        <form method="post" action="/app/logout">
            ${sessionScope.hello}, <c:out value="${sessionScope.login}"></c:out>
            <button type="submit" class="btn btn-success" value="logout">${sessionScope.logout}</button>
        </form>
    </div>
</div>
</body>
</html>
