<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Welcome page</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

  </head>
  <body>

  <div class="panel panel-default">
    <div class="panel-heading">
      <span style="padding: 20px;font-size:xx-large">${sessionScope.title}</span>
    </div>
  </div>

    <div class="panel-heading" align="right">
      <form action="/app/locale" method="get">
        <button type="submit" class="btn btn-default" name="btn" value="UA"><img src="/images/ua.svg" width="20" height="20"></button>
        <button type="submit" id="en" class="btn btn-default" name="btn" value="EN"><img src="/images/gb.svg" width="20" height="20"></button>
      </form>
    </div>
  </div>

  <c:if test="${sessionScope.loaded == null}">
    <script>document.getElementById("en").click()</script>
  </c:if>


  <div style="padding: 20px">
    <form method="post" action="/app/router" role="form">

      <div class="row">
        <div class="form-group col-lg-3">
          <label for="login">${sessionScope.loginAsBtnName}</label>
          <input class="form-control" type="text" name="login" id="login" placeholder="${sessionScope.loginAsBtnName}">
        </div>
      </div>

      <div class="row">
        <div class="form-group col-lg-3">
          <label for="password">${sessionScope.passwordAsBtnName}</label>
          <input class="form-control" type="password" name="password" id="password" placeholder="${sessionScope.passwordAsBtnName}">
        </div>
      </div>

      <button type="submit" class="btn btn-success">${sessionScope.signin}</button>

    </form>
  </div>

  </body>
</html>
