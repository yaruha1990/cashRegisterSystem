<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Senior cashier page</title>
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
    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="/app/checkList">
                <button type="submit" class="btn btn-success">${sessionScope.checkListBtnName}</button>
            </form>
        </div>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="checkSumReport.jsp">
                <button type="submit" class="btn btn-success">${sessionScope.checkSumBtnName}</button>
            </form>
        </div>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="checkDateReport.jsp">
                <button type="submit" class="btn btn-success">${sessionScope.checkDateBtnName}</button>
            </form>
        </div>
    </div>
</c:if>

</body>
</html>
