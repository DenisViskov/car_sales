<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Announcements today</title>
    <style>
        .box div {
            width: 90px;
            display: inline-block;
        }

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
<div class="box">
    <div>
        <button type="button" id="filterPhoto">Show with photo</button>
    </div>
    <div>
        <button type="button" id="filterLastDay">Show the last day</button>
    </div>
    <div>
        <button type="button" id="filerCarBrand">Show with car brand:</button>
    </div>
    <div>
        <input type="text" id="carName" required name="car brand"/>
    </div>
</div>
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
<br>
<button type="button" id="addAnnouncement">Add new announcement</button>
<br><br>
<form>
    <label for="sessionAnnouncements">Select sold announcement:</label>
    <select id="sessionAnnouncements" name="announcement">
    </select>
    <div>
        <button type="button" id="selectAnnouncements">Take off announcement</button>
    </div>
</form>
</body>
</html>

<script>
    window.onload = function () {
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {GET: "GET_announcements"},
            dataType: 'json',
            success: function (data) {
                collectAnnouncements(data)
                getSessionAnnouncements();
            }
        })
    }

    function getSessionAnnouncements() {
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {GET: "GET_session_announcements"},
            dataType: 'json',
            success: function (data) {
                collectSessionAnnouncements(data)
            }
        })
    }

    function collectSessionAnnouncements(data) {
        let select = document.getElementById('sessionAnnouncements')
        for (key in data) {
            let option = document.createElement('option')
            option.setAttribute('value', data[key])
            option.innerText = data[key]
            select.appendChild(option)
        }
    }

    $('#filterPhoto').click(function (e) {
        e.preventDefault()
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {GET: "GET_only_with_photo"},
            dataType: 'json',
            success: function (data) {
                let table = document.getElementById('table')
                let head = table.getElementsByTagName('tr')[0]
                table.innerHTML = ''
                table.appendChild(head)
                collectAnnouncements(data)
            }
        })
    })

    $('#filterLastDay').click(function (e) {
        e.preventDefault()
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {GET: "GET_only_the_last_day"},
            dataType: 'json',
            success: function (data) {
                let table = document.getElementById('table')
                let head = table.getElementsByTagName('tr')[0]
                table.innerHTML = ''
                table.appendChild(head)
                collectAnnouncements(data)
            }
        })
    })

    $('#filerCarBrand').click(function (e) {
        e.preventDefault()
        const brand = document.getElementById('carName').value
        $.ajax({
            type: 'GET',
            url: '<%=request.getContextPath()%>/index',
            data: {
                GET: "GET_cars_by_brand",
                carName: brand
            },
            dataType: 'json',
            success: function (data) {
                let table = document.getElementById('table')
                let head = table.getElementsByTagName('tr')[0]
                table.innerHTML = ''
                table.appendChild(head)
                collectAnnouncements(data)
            }
        })
    })

    $('#addAnnouncement').click(function (e) {
        e.preventDefault()
        document.location.href = '<%=request.getContextPath()%>/announcement.jsp'
    })

    $('#selectAnnouncements').click(function (e) {
        e.preventDefault()
        var select = document.getElementById('sessionAnnouncements').selectedIndex;
        var options = document.getElementsByTagName('option')
        const announcement = options[select].value
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/index',
            data: {announcement: announcement},
            dataType: 'json',
            success: window.location.reload()
        })
    })

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
        status.innerText = data["status"] ? 'sold' : 'active'
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
