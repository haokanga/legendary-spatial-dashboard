<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Legendary Spatial API Dashboard</title>
    </head>
    <body>
        <h2>Legendary Spatial API Dashboard</h2>
        <h2>Aggregation Analytics</h2>
        <ul>
            <li>Total visits to all the places: <%= request.getAttribute("sum-visits")%></li>
            <li>Average visits to the places: <%= request.getAttribute("avg-visits")%></li>
            <li>Mostly visited place: <%= request.getAttribute("mostly-visited-place")%></li>
        </ul>
        <h2>Location-Specific Data</h2>
        <%= request.getAttribute("locations")%>
        <h2>Log Dump</h2>
        <%= request.getAttribute("dump")%>
    </body>
</html>

