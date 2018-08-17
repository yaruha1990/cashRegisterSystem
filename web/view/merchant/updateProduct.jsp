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

<h2>Update product</h2>

<c:if test="${sessionScope.role != 'merchant'}">
    <c:out value="Page is available because your user role is not \'merchant\'"></c:out><br>
    <a href="/">To index page</a>
</c:if>

<c:if test="${sessionScope.role == 'merchant'}">
    <form method="post" action="/updateProduct">
        <label for="id"><input type="text" disabled name="id" id="id" value="${requestScope.product.id}"> ID</label><br>
        <label for="vendorCode"><input type="text" name="vendorCode" id="vendorCode" value="${requestScope.product.vendorCode}"> Vendor code</label><br>
        <label for="productName"><input type="text" name="productName" id="productName" value="${requestScope.product.productName}"> Product name</label><br>
        <label for="price"><input type="number" min="0" name="price" id="price" value="${requestScope.product.price}"> Price</label><br>
        <label for="quantity"><input type="number" min="0" name="quantity" id="quantity" value="${requestScope.product.quantityInStock}"> Quantity</label><br>
        <input type="text" hidden name="productId" value="${requestScope.product.id}">
        <input type="submit" value="Update"/>
    </form>
</c:if>
</body>
</html>
