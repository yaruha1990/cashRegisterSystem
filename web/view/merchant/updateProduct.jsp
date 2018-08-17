<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update product</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">Update product</span>
</div>

<c:if test="${sessionScope.role != 'merchant'}">
    <div style="padding: 20px">
    <c:out value="Page is available because your user role is not \'merchant\'"></c:out><br>
    <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'merchant'}">
    <div style="padding: 20px">
        <label>To product list </label>
        <button type="button" value="Back" onclick="history.back()" class="btn btn-success">Back</button>
    </div>

    <div style="padding: 20px">
    <form method="post" action="/updateProduct" role="form">

        <div class="row">
            <div class="form-group col-lg-3">
        <label for="id">ID</label>
        <input type="text" class="form-control" disabled name="id" id="id" value="${requestScope.product.id}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
        <label for="vendorCode">Vendor code</label>
        <input type="text" class="form-control" name="vendorCode" id="vendorCode" value="${requestScope.product.vendorCode}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
        <label for="productName">Product name</label>
        <input type="text" class="form-control" name="productName" id="productName" value="${requestScope.product.productName}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
        <label for="price">Price</label>
        <input type="number" class="form-control" min="0" name="price" id="price" value="${requestScope.product.price}">
            </div>
        </div>

        <div class="row">
            <div class="form-group col-lg-3">
        <label for="quantity">Quantity</label>
        <input type="number" class="form-control" min="0" name="quantity" id="quantity" value="${requestScope.product.quantityInStock}">
            </div>
        </div>

        <input type="text" hidden name="productId" value="${requestScope.product.id}">
        <button type="submit" class="btn-success" value="Update">Update</button>
    </form>
    </div>
</c:if>
</body>
</html>
