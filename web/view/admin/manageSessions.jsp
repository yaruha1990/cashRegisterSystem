<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage sessions</title>
</head>
<body>

<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>



<c:if test="${sessionScope.role != 'admin'}">
    <div style="padding: 20px">
    <c:out value="Page is unavailable as you didn't login as admin"></c:out><br>
    <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <div style="padding: 20px;">
        <button type="button" onclick="history.back()" class="btn btn-success">${sessionScope.back}</button>
    </div>
    <c:forEach items="${applicationScope.sessions}" var="entry">
        <div class="row" style="padding: 20px">
            <div class="form-group col-lg-6">
                <form method="post" action="/app/adminLogout">
                    <ul>
                        <li>${sessionScope.loginAsBtnName}: <c:out value="${entry.value}"></c:out></li>
                        <li>${sessionScope.sessionIdAsLabelName}: <c:out value="${entry.key.id}"></c:out></li>
                        <input type="text" hidden name="sessionId" value="${entry.key.id}">
                        <button type="submit" class="btn btn-success">${sessionScope.stopSession}</button>
                        <hr/>
                    </ul>
                </form>
            </div>
        </div>
    </c:forEach>
</c:if>

</body>
</html>
