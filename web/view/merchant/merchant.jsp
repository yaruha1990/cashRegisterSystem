<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Merchant page</title>
</head>
<body>
    <h1>Merchant page</h1>

    <c:if test="${sessionScope.role != 'merchant'}">
        <c:out value="Page is available because your user role is not \'merchant\'"></c:out><br>
        <a href="/">To index page</a>
    </c:if>

    <jsp:include page="/view/logout.jsp"></jsp:include>

    <c:if test="${sessionScope.role == 'merchant'}">
        <c:if test="${!empty requestScope.products}">
            <h2>Products in stock</h2>
            <c:forEach var="product" items="${requestScope.products}">
                <ul>
                    <li>Vendor code: <c:out value="${product.vendorCode}"></c:out></li>
                    <li>Product name: <c:out value="${product.productName}"></c:out></li>
                    <li>Price: <c:out value="${product.price}"></c:out></li>
                    <li>Quantity in stock: <c:out value="${product.quantityInStock}"></c:out></li>

                    <form method="get" action="/updateProduct">
                        <input type="number" hidden name="id" value="${product.id}">
                        <input type="submit" value="Update">
                    </form>

                    <hr/>
                </ul>
            </c:forEach>
        </c:if>

        <h2>Create product here</h2>
        <form method="post" action="/createProduct">
            <label for="vendorCode"><input type="text" name="vendorCode" id="vendorCode"> Vendor code</label><br>
            <label for="productName"><input type="text" name="productName" id="productName"> Product name</label><br>
            <label for="price"><input type="number" min="0" name="price" id="price"> Price</label><br>
            <label for="quantity"><input type="number" min="0" name="quantity" id="quantity"> Quantity</label><br>
            <input type="submit" value="Create"/>
        </form>
    </c:if>

</body>
</html>
