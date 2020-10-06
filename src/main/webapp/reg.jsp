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

    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body id="body">
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

<script>
    $('#form').submit(function (e) {
        e.preventDefault()
        if (validation()) {
            $.ajax({
                type: 'POST',
                url: '<%=request.getContextPath()%>/registration',
                data: {
                    name: document.getElementById('name').value,
                    login: document.getElementById('login').value,
                    password: document.getElementById('password').value,
                    confirm: document.getElementById('confirm_password').value
                },
                dataType: 'json'
            }).done(function (data) {
                let body = document.getElementById('body')
                if (data[0]) {
                    body.appendChild('<h2>Success<h2/>')
                    setTimeout(redirect, 3000)
                } else {
                    body.appendChild('<h2>That user already exist<h2/>')
                }
            }).fail(function (err) {
                alert(err);
            });
        }
    })

    function redirect() {
        window.location.href = "http://localhost:8080/car_sales/index.jsp"
    }

    function validation() {
        const password = document.getElementById('password').value
        const confirm = document.getElementById('confirm_password').value
        if (password != confirm) {
            alert("passwords not same")
            return false
        }
        return true
    }
</script>