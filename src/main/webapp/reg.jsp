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
    <title>sign up</title>
    <style>
        .container {
            padding: 50px;
            position: fixed;
            top: 50%;
            left: 50%;
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
    <h2>registration form:</h2>
    <form id="form">
        <label for="name">Name:</label><br>
        <input type="text" id="name" required name="name"/><br>
        <label for="login">Login:</label><br>
        <input type="text" id="login" required name="login"/><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" required name="password"/><br>
        <label for="confirm_password">Confirm password:</label><br>
        <input type="password" id="confirm_password" required name="confirm_password"/><br>
        <input type="submit" id="button" value="Sign up"/>
    </form>
</div>
</body>
</html>
