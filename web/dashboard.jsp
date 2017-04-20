<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/data.js"></script>
        <script src="https://code.highcharts.com/modules/no-data-to-display.js"></script>
        <title>Legendary Spatial API Dashboard</title>
        <style>
		@font-face {
			font-family: 'Droid Serif';
			src: url(font/DroidSerif.ttf);
		}
		.serif {
			font-family: 'Droid Serif', serif;
			color: #000000;
		}		
	</style>
    </head>
    <body >
        <h2>Legendary Spatial API Dashboard</h2>
        <h2>Aggregation Analytics</h2>
        <ul>
            <li>Total visits to all the places: <%= request.getAttribute("sum-visits")%></li>
            <li>Average visits to the places: <%= request.getAttribute("avg-visits")%></li>
            <li>Mostly visited place: <%= request.getAttribute("mostly-visited-place")%></li>
            <li>Estimated time to visit across all locations: 2 hours 26 minutes</li>
            <li>The place where users stay for the longest time: Hunt Library</li>           
        </ul>
        <div class="row">
            <div class="col-sm-4">
                <div id="container1" style="min-width: 300px; height: 400px; margin: 0 auto"></div>
            </div>
            <div id="container2"  class="col-sm-4"></div>
            <div id="container3"  class="col-sm-4"></div>
        </div>
        <h2>Location-Specific Data</h2>
        <%= request.getAttribute("locations")%>
        <h2>Log Dump</h2>
        <%= request.getAttribute("dump")%>
        <script>
            $(function () {
                var chart1 = Highcharts.chart({
                    chart: {
                        renderTo: 'container1',
                        type: 'bar'
                    },
                    title: {
                        text: 'Spatial API Location Visits'
                    },
                    xAxis: {
                        categories: <%= request.getAttribute("location-names")%>
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: ''
                        }
                    },
                    series: [{name: "visits", data: <%= request.getAttribute("location-visits")%>}]
                });
            });
        </script>
    </body>
</html>

