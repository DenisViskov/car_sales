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
            background-size: 100% 100%;
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
                collectAnnouncements(data)
            }
        })
    }

    function collectAnnouncements(data) {
        let table = document.getElementById('table')
        for (key in data) {
            let readyTr = collectTr(data[key])
            table.appendChild(readyTr)
        }
    }

    function collectTr(data) {
        let tr = document.createElement('tr')
        let announcement = document.createElement('td')
        announcement.innerText = data["Announcement"]
        let carBrand = document.createElement('td')
        carBrand.innerText = data["Car brand"]
        let carModel = document.createElement('td')
        carModel.innerText = data["Car model"]
        let carCreated = document.createElement('td')
        carCreated.innerText = data["Car created"]
        let carBodyType = document.createElement('td')
        carBodyType.innerText = data["Car body type"]
        let carMileage = document.createElement('td')
        carMileage.innerText = data["Car mileage"]
        let description = document.createElement('td')
        description.innerText = data["description"]
        let photo = document.createElement('td')
        photo.setAttribute('background', '<%=request.getContextPath()%>/picture?picture=' + data["photo"])
        let status = document.createElement('td')
        status.innerText = data["status"]
        let owner = document.createElement('td')
        owner.innerText = data["owner"]
        tr.appendChild(announcement)
        tr.appendChild(carBrand)
        tr.appendChild(carModel)
        tr.appendChild(carCreated)
        tr.appendChild(carBodyType)
        tr.appendChild(carMileage)
        tr.appendChild(description)
        tr.appendChild(photo)
        tr.appendChild(status)
        tr.appendChild(owner)
        return tr;
    }

</script>
