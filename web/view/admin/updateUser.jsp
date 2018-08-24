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
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>
<a style="padding: 20px" href="/view/admin/admin.jsp">${sessionScope.toAdminPage}</a><br>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.updateUser}</span>
</div>

<c:if test="${sessionScope.role != 'admin'}">
    <div style="padding: 20px">
    <label><c:out value="Page is unavailable as you didn't login as admin"></c:out></label><br>
    <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <div style="padding: 20px">
    <form method="post" action="/app/updateUserPost" role="form">

        <div class="row">
            <div class="form-group col-lg-3">
                <label for="id">${sessionScope.id}</label>
                <input type="text" class="form-control" disabled name="id" id="id" value="${requestScope.user.id}">
            </div>
        </div>


        <div class="row">
            <div class="form-group col-lg-3">
                <label for="login">${sessionScope.loginAsBtnName}</label>
                <input type="text" class="form-control" name="login" id="login" value="${requestScope.user.login}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
                <label for="password">${sessionScope.passwordAsBtnName}</label>
                <input type="text" class="form-control" name="password" id="password" value="${requestScope.user.password}">
            </div>
        </div>

        <label for="role">${sessionScope.setRole}</label>
        <select name="role" id="role">
            <option value="admin">${sessionScope.admin}</option>
            <option value="cashier">${sessionScope.cashier}</option>
            <option value="senior cashier">${sessionScope.seniorCashier}</option>
            <option value="merchant" selected>${sessionScope.merchant}</option>
        </select>
        <input type="text" hidden name="userId" value="${requestScope.user.id}">
        <input type="text" hidden name="userPassword" value="${requestScope.user.password}">
        <button type="submit" class="btn-success">${sessionScope.updateAsBtnName}</button>
    </form>
    </div>
</c:if>

</body>
</html>
