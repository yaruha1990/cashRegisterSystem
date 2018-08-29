<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Open check</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.cashierPage}</span>
</div>
<div style="padding: 20px">
<a href="cashier.jsp">${sessionScope.toCashierPage}</a>
</div>

<c:if test="${sessionScope.role != 'cashier'}">
    <div style="padding: 20px">
        <label><c:out value="Page is unavailable as you didn't login as cashier"></c:out></label><br>
        <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'cashier'}">
    <c:if test="${sessionScope.check != null}">
        <table class="table">
            <caption style="caption-side: top"><c:out value="${sessionScope.check.dateTime}"></c:out></caption>
            <tr>
                <th scope="col"><c:out value="${sessionScope.vendorCode}"/></th>
                <th scope="col"><c:out value="${sessionScope.productName}"/></th>
                <th scope="col"><c:out value="${sessionScope.quantity}, ${sessionScope.pieces}"/></th>
                <th scope="col"><c:out value="${sessionScope.price}, ${sessionScope.grn}"/></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.check.products}">
                <tr>
                    <td><c:out value="${item.key.vendorCode}"></c:out></td>
                    <td><c:out value="${item.key.productName}"></c:out></td>
                    <td><c:out value="${item.value}"></c:out></td>
                    <td><c:out value="${item.key.price}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
        <label><c:out value="${sessionScope.checkSumAsLabel} ${sessionScope.checkSum} ${sessionScope.grn}"></c:out></label>
    </c:if>


    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="findProduct.jsp">
                <button type="submit" class="btn btn-success">${sessionScope.addProduct}</button>
            </form>
        </div>
    </div>

    <c:if test="${sessionScope.check != null}">
        <div class="row" style="padding: 20px">
            <div class="form-group col-lg-6">
                <form method="post" action="/app/saveCheck">
                    <button type="submit" class="btn btn-success">${sessionScope.saveCheck}</button>
                </form>
            </div>
        </div>
    </c:if>
</c:if>

</body>
</html>
