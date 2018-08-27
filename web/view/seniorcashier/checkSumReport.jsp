<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 2018-08-27
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Check sum report</title>
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
</c:if>

</body>
</html>
