<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 2018-08-27
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Check list</title>
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
            <th scope="col"><c:out value="${sessionScope.createdBy}"/></th>
            <th scope="col"><c:out value="${sessionScope.checkSumAsTblHeader}"/></th>
            <th scope="col"><c:out value="${sessionScope.createdAt}"/></th>
            <th scope="col"><c:out value="${sessionScope.checkDetails}"/></th>
            <th scope="col"><c:out value="${sessionScope.deleteCheck}"/></th>
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
                <td>
                    <button type="submit" name="checkOperation" value="Delete" class="btn btn-danger">${sessionScope.delete}</button>
                </td>
                </form>
            </tr>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:if test="${requestScope.currentPage > 1}">
                <li class="page-item"><a class="page-link" href="/app/checkList?page=${requestScope.currentPage - 1}">Previous</a></li>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="page-item"><a class="page-link" href="#">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/app/checkList?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
            </c:forEach>
            <c:if test="${currentPage < numberOfPages}">
                <li class="page-item"><a class="page-link" href="/app/checkList?page=${currentPage + 1}">Next</a></li>
            </c:if>
        </ul>
    </nav>

</c:if>


</body>
</html>
