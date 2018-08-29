<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Check date report</title>
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

    <div style="padding: 20px">
        <form role="form" action="/app/checkDateReport" method="post">
            <div class="row">
                <div class="form-group col-lg-3">
                    <label for="checkDateFrom">${sessionScope.checkDateFromAsLbl}</label>
                    <input type="date" class="form-control" id="checkDateFrom" name="checkDateFrom">
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-3">
                    <label for="checkDateTo">${sessionScope.checkDateToAsLbl}</label>
                    <input type="date" class="form-control" id="checkDateTo" name="checkDateTo">
                </div>
            </div>

            <button type="submit" class="btn btn-success">${sessionScope.getReportAsBtn}</button>
        </form>
    </div>

    <c:if test="${requestScope.checks != null}">
        <table class="table">
            <caption style="caption-side: top"><c:out value="${sessionScope.checkListTblName}"></c:out></caption>
            <tr>
                <th scope="col"><c:out value="${sessionScope.checkId}"/></th>
                <th scope="col"><c:out value="${sessionScope.createdBy}"/></th>
                <th scope="col"><c:out value="${sessionScope.checkSumAsTblHeader}"/></th>
                <th scope="col"><c:out value="${sessionScope.createdAt}"/></th>
                <th scope="col"><c:out value="${sessionScope.checkDetails}"/></th>
            </tr>
            <c:forEach var="check" items="${requestScope.checks}">
                <tr>
                    <td><c:out value="${check.id}"></c:out></td>
                    <td><c:out value="${check.userLogin}"></c:out></td>
                    <td><c:out value="${check.checkSum}"></c:out></td>
                    <td><c:out value="${check.dateTime}"></c:out></td>
                    <form action="/app/checkOperations">
                        <input type="number" hidden name="checkId" value="${check.id}">
                        <td>
                            <button type="submit" name="checkOperation" value="Details" class="btn btn-info">${sessionScope.show}</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>

    </c:if>
</c:if>

</body>
</html>
