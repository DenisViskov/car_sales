<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 06.10.2020
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign in</title>
    <style>
        .container {
            padding: 50px;
            position: fixed; top: 50%; left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            background: white;
            outline: 2px solid #000;
            border-radius: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Sign in:</h2>
    <form id="form">
        <label for="login">Login:</label><br>
        <input type="text" id="login" required name="login"/><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" required name="password"/><br>
        <input type="submit" id="button" value="Войти"/>
    </form>
</div>
</body>
</html>
