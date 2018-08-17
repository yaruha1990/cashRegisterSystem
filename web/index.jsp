<%--
  Created by IntelliJ IDEA.
  User: yaruh
  Date: 16.08.2018
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome page</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
  </head>
  <body>

  <div class="panel panel-default">
    <div class="panel-heading">
      <span style="padding: 20px;font-size:xx-large">Cash Register System</span>
    </div>

    <div class="panel-heading" align="right">
      <form action="/locale" method="get">
        <button type="submit" class="btn btn-default" name="btn" value="UA"><img src="/images/ua.svg" width="20" height="20"></button>
        <button type="submit" id="en" class="btn btn-default" name="btn" value="EN"><img src="/images/gb.svg" width="20" height="20"></button>
      </form>
    </div>
  </div>

  <div style="padding: 20px">
    <form method="get" action="identification" role="form">

      <div class="row">
        <div class="form-group col-lg-3">
          <label for="login">Login</label>
          <input class="form-control" type="text" name="login" id="login" placeholder="Login">
        </div>
      </div>

      <div class="row">
        <div class="form-group col-lg-3">
          <label for="password">Password</label>
          <input class="form-control" type="password" name="password" id="password" placeholder="Password">
        </div>
      </div>

      <button type="submit" class="btn btn-success">Login</button>

    </form>
  </div>

  </body>
</html>
