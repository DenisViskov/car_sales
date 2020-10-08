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
<body>
<div class="container" id="cont">
    <h2>Sign in:</h2>
    <form id="form">
        <label for="login">Login:</label><br>
        <input type="text" id="login" required name="login"/><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" required name="password"/><br>
        <input type="submit" id="button" value="Войти"/>
    </form>
    <div>
        <button id="registration" type="button">Registration now</button>
    </div>
</div>
</body>
</html>

<script>

    $('#registration').click(function (e) {
        document.location.href = '<%=request.getContextPath()%>/reg.jsp'
    })

    $('#form').submit(function (e) {
        e.preventDefault()
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/sign',
            data: {
                login: document.getElementById('login').value,
                password: document.getElementById('password').value,
            },
            dataType: 'json'
        }).done(function (data) {
            let container = document.getElementById('cont')
            let h2 = document.createElement('h2')
            if (data[0]) {
                h2.innerText = 'You are entered'
                container.appendChild(h2)
                setTimeout(redirectToAddAnnouncement, 3000)
            } else {
                h2.innerText = 'Login or password not same'
                container.appendChild(h2)
            }
        }).fail(function (err) {
            alert(err);
        });
    })

    function redirectToAddAnnouncement() {
        document.location.href = "http://localhost:8080/car_sales/announcement.jsp"
    }
</script>