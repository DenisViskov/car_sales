<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Announcements today</title>
    <style>
        .header {
            background-color: gainsboro;
        }

        td {
            width: 60px;
            height: 60px;
            border: solid 1px silver;
            text-align: center;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>
<table id="table">
    <tr class="header">
        <td>Announcement</td>
        <td>Car brand</td>
        <td>Car model</td>
        <td>Car created</td>
        <td>Car body type</td>
        <td>Car mileage</td>
        <td>description</td>
        <td>photo</td>
        <td>status</td>
        <td>owner</td>
    </tr>
</table>
</body>
</html>

<script>
    window.onload = function () {
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {GET: "Get data"},
            dataType: 'json',
            success: function (data) {
                console.log(data)
            }
        })
    }


</script>
