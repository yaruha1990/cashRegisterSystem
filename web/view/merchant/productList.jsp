<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 2018-08-25
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Products</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.merchantPage}</span>
</div>

<c:if test="${sessionScope.role != 'merchant'}">
    <div style="padding: 20px">
        <c:out value="Page is unavailable because your user role is not \'merchant\'"></c:out><br>
        <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'merchant'}">

    <c:if test="${!empty requestScope.products}">
        <div class="panel-heading">
            <span style="padding: 20px;font-size:xx-large">${sessionScope.productsInStock}</span>
        </div>
        <c:forEach var="product" items="${requestScope.products}">
            <ul>
                <li>${sessionScope.vendorCode}: <c:out value="${product.vendorCode}"></c:out></li>
                <li>${sessionScope.productName}: <c:out value="${product.productName}"></c:out></li>
                <li>${sessionScope.price}: <c:out value="${product.price}"></c:out></li>
                <li>${sessionScope.quantity}: <c:out value="${product.quantityInStock}"></c:out></li>

                <form method="get" action="/app/updateProductGet">
                    <input type="number" hidden name="id" value="${product.id}">
                    <button type="submit" class="btn-success">${sessionScope.updateAsBtnName}</button>
                </form>

                <hr/>
            </ul>
        </c:forEach>
    </c:if>

    <div class="panel-heading">
        <span style="padding: 20px;font-size:xx-large">${sessionScope.createProduct}</span>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="post" action="/app/createProduct">

                <div class="row">
                    <div class="form-group col-lg-3">
                        <label for="vendorCode">${sessionScope.vendorCode}</label>
                        <input type="text" class="form-control" name="vendorCode" id="vendorCode">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-3">
                        <label for="productName">${sessionScope.productName}</label>
                        <input type="text" class="form-control" name="productName" id="productName">
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-lg-3">
                        <label for="price">${sessionScope.price}</label>
                        <input type="number" class="form-control" min="0" name="price" id="price">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-lg-3">
                        <label for="quantity">${sessionScope.quantity}</label>
                        <input type="number" class="form-control" min="0" name="quantity" id="quantity">
                    </div>
                </div>

                <button type="submit" class="btn-success" value="Create">${sessionScope.create}</button>
            </form>
        </div>
    </div>

</c:if>

</body>
</html>
