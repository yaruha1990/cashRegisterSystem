<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manage accounts</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>
<div style="padding: 20px">
<a href="/view/admin/admin.jsp">To admin's page</a>
</div>

<div class="panel-heading">
    <span style="padding: 20px;font-size:xx-large">List of users</span>
</div>

<c:if test="${sessionScope.role != 'admin'}">
    <label style="padding: 20px"><c:out value="Page is available as you didn't login as admin"></c:out></label><br>
    <a style="padding: 20px" href="/">To index page</a>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <c:forEach var="user" items="${requestScope.users}">
        <ul>
            <li>ID:<c:out value="${user.id}"/></li>
            <li>Login:<c:out value="${user.login}"/></li>
            <li>Password:<c:out value="${user.password}"/></li>
            <li>Role:<c:out value="${user.role}"/></li>

            <form method="post" action="/deleteUser" style="float: left">
                <input type="number" hidden name="id" value="${user.id}">
                <button type="submit" class="btn btn-success" value="Delete">Delete</button>
            </form>

            <form method="get" action="/updateUser">
                <input type="number" hidden name="id" value="${user.id}">
                <button class="btn btn-success" type="submit" value="Update">Update</button>
            </form>

        </ul>
        <hr/>
    </c:forEach><br>

    <div class="panel-heading">
        <span style="padding: 20px;font-size:xx-large">Create user</span>
    </div>

    <div class="row" style="padding: 20px">
        <div class="form-group col-lg-6">
            <form method="post" action="/createUser">

                <div class="row">
                    <div class="form-group col-lg-3">
                <label for="login">Login</label>
                <input type="text" class="form-control" name="login" id="login">
                    </div>
                </div>



                <div class="row">
                    <div class="form-group col-lg-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" id="password">
                    </div>
                </div>


                <label for="role">Set role</label>
                <select name="role" id="role">
                    <option>admin</option>
                    <option>cashier</option>
                    <option>senior cashier</option>
                    <option selected>merchant</option>
                </select>
                <button class="btn-success" type="submit" value="Create">Create</button>
            </form>
        </div>
    </div>
</c:if>

</body>
</html>
