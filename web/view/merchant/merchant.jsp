<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Merchant page</title>
</head>
<body>
<jsp:include page="/view/header.jsp"></jsp:include>
<jsp:include page="/view/logout.jsp"></jsp:include>

    <div class="panel-heading">
        <span style="padding: 20px;font-size:xx-large">${sessionScope.merchantPage}</span>
    </div>

    <c:if test="${sessionScope.role != 'merchant'}">
        <div style="padding: 20px">
        <c:out value="Page is unavailable because your user role is not \'merchant\'"></c:out><br>
        <a href="/">To index page</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.role == 'merchant'}">

        <div class="row" style="padding: 20px">
            <div class="form-group col-lg-6">
                <form method="post" action="/app/products">
                    <button type="submit" class="btn btn-success" id="productList">${sessionScope.toProductList}</button>
                </form>
            </div>
        </div>
    </c:if>

</body>
</html>
