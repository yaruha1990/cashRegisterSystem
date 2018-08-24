<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 22.08.2018
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Check created</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.cashierPage}</span>
</div>

<c:if test="${sessionScope.role != 'cashier'}">
    <div style="padding: 20px">
        <label><c:out value="Page is unavailable as you didn't login as cashier"></c:out></label><br>
        <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'cashier'}">
    <div style="padding: 20px">
        <c:out value="${sessionScope.checkCreated}"></c:out><br>
        <a href="/view/cashier/cashier.jsp">${sessionScope.toCashierPage}</a>
    </div>
</c:if>

</body>
</html>
