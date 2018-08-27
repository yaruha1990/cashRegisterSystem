<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 2018-08-27
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Check details</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.seniorCashierPage}</span>
</div>

<c:if test="${sessionScope.role != 'seniorCashier'}">
    <div style="padding: 20px">
        <label><c:out value="Page is unavailable as you didn't login as senior cashier"></c:out></label><br>
        <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'seniorCashier'}">
    <div style="padding: 20px">
    <a href="/view/seniorcashier/seniorcashier.jsp">${sessionScope.toSeniorCashierPage}</a>
    </div>

        <table class="table">
            <caption style="caption-side: top"><c:out value="${sessionScope.checkListTblName}"></c:out></caption>
            <tr>
                <th scope="col"><c:out value="${sessionScope.checkId}"/></th>
                <th scope="col"><c:out value="${sessionScope.productId}"/></th>
                <th scope="col"><c:out value="${sessionScope.vendorCode}"/></th>
                <th scope="col"><c:out value="${sessionScope.productName}"/></th>
                <th scope="col"><c:out value="${sessionScope.quantity}, ${sessionScope.pieces}"/></th>
                <th scope="col"><c:out value="${sessionScope.price}, ${sessionScope.grn}"/></th>
                <th scope="col"><c:out value="${sessionScope.deleteProduct}"/></th>
            </tr>
            <c:forEach var="item" items="${requestScope.check.products}">
                <tr>
                    <td><c:out value="${requestScope.check.id}"></c:out></td>
                    <form method="post" action="/app/deleteProductFromCheck">
                    <td><c:out value="${item.key.id}"></c:out></td>
                    <td><c:out value="${item.key.vendorCode}"></c:out></td>
                    <td><c:out value="${item.key.productName}"></c:out></td>
                    <td><c:out value="${item.value}"></c:out></td>
                    <td><c:out value="${item.key.price}"></c:out></td>
                    <input type="number" hidden name="checkId" value="${check.id}">
                    <input type="number" hidden name="productId" value="${item.key.id}">
                    <td>
                        <button type="submit" class="btn btn-danger">${sessionScope.delete}</button>
                    </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <label><c:out value="${sessionScope.checkSumAsLabel} ${requestScope.checkSum} ${sessionScope.grn}"></c:out></label>
</c:if>
</body>
</html>
