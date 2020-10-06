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
    <title>Announcement</title>
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
            text-align: justify-all;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container" id="cont">
    <h2>Please fill the form fields about you car:</h2>
    <form action="announcement" id="form" enctype="multipart/form-data" method="post">
        <label for="announcementName">Name of announcement:</label><br>
        <input type="text" id="announcementName" required name="announcementName"/><br>
        <label for="carBrand">Car brand:</label><br>
        <input type="text" id="carBrand" required name="carBrand"/><br>
        <label for="carModel">Car model:</label><br>
        <input type="text" id="carModel" required name="carModel"/><br>
        <label for="carBody">Car body type:</label><br>
        <input type="text" id="carBody" required name="carBody"/><br>
        <label for="carMileage">Car mileage:</label><br>
        <input type="text" id="carMileage" required name="carMileage"/><br>
        <label for="carCreated">Car created:</label><br>
        <input type="text" id="carCreated" required name="carCreated"/><br>
        <label for="comment">Description:</label><br/>
        <textarea name="comment" id="comment" placeholder="about car" maxlength="1000"></textarea><br>
        <input type="file" name="file"/><br>
        <input type="submit" id="button" value="create announcement"/>
    </form>
</div>
</body>
</html>
