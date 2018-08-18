<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">${sessionScope.administratorPage}</span>
</div>

<c:if test="${sessionScope.role != 'admin'}">
    <div style="padding: 20px">
    <label><c:out value="Page is available as you didn't login as admin"></c:out></label><br>
    <a href="/">To index page</a>
    </div>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="post" action="manageSessions.jsp">
                <button type="submit" class="btn btn-success" id="sessions">${sessionScope.manageSessions}</button>
            </form>
        </div>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="/manageAccount">
                <button type="submit" class="btn btn-success" id="accounts">${sessionScope.manageAccounts}</button>
            </form>
        </div>
    </div>


</c:if>

</body>
</html>
