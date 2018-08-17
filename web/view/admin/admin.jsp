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
    <span style="padding: 20px;font-size:xx-large">Administrator page</span>
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
                <label for="sessions" style="font-size: large">Click here to manage user sessions</label>
                <button type="submit" class="btn btn-success" id="sessions" value="Manage sessions">Manage sessions</button>
            </form>
        </div>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="get" action="/manageAccount">
                <label for="accounts" style="font-size: large">Click here to manage user accounts</label>
                <button type="submit" class="btn btn-success" id="accounts" value="Manage accounts">Manage accounts</button>
            </form>
        </div>
    </div>

    <%--<div class="row" style="padding: 20px">--%>
        <%--<div class="form-group col-lg-4">--%>
            <%--<form method="post" action="/identification">--%>
                <%--<label style="font-size: large">Hello, <c:out value="${sessionScope.login}"></c:out></label>--%>
                <%--<button type="submit" class="btn btn-success" value="logout">Logout</button>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>

</c:if>

</body>
</html>
