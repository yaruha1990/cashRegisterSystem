<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 20.08.2018
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Find product</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.cashierPage}</span>
</div>

<div style="padding: 20px">
    <a href="openCheck.jsp">${sessionScope.toCheckPage}</a>
</div>

<c:if test="${sessionScope.role != 'cashier'}">
    <div style="padding: 20px">
        <label><c:out value="Page is available as you didn't login as cashier"></c:out></label><br>
        <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'cashier'}">
<div style="padding: 20px">

    <form method="post" action="/app/addProductToCheck" role="form">

        <div class="row">
            <div class="form-group col-lg-3">
                <label for="vendorCode">${sessionScope.vendorCode}</label>
                <input class="form-control" type="text" name="vendorCode" id="vendorCode" placeholder="${sessionScope.vendorCode}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
                <label for="quantity">${sessionScope.quantity}</label>
                <input class="form-control" type="number" min="0" name="quantity" id="quantity" placeholder="${sessionScope.quantity}">
            </div>
        </div>

        <button type="submit" class="btn btn-success">${sessionScope.addProduct}</button>

    </form>
</div>
</c:if>

</body>
</html>
