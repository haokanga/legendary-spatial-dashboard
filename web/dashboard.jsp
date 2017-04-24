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
    <body class="serif">
        <h2>Legendary Spatial API Dashboard</h2>
        <h2>Aggregation Analytics</h2>
        <ul>
            <li>Total visits to all the places: <%= request.getAttribute("sum-visits")%></li>
            <li>Average visits to the places: <%= request.getAttribute("avg-visits")%></li>
            <li>Mostly visited place: <%= request.getAttribute("mostly-visited-place")%></li>
            <li>Estimated time to finish the tour: <%= request.getAttribute("time-to-finish")%> minutes</li>
        </ul>
        <div class="row">
            <div class="col-sm-4">                
                <div id="container1"></div>  
            </div>        
            <div class="col-sm-4">             
                <div id="container2">                </div>    
            </div>    
            <div class="col-sm-4">
                <div id="container3"></div>
            </div>
        </div>
    </div>
    <h2>Location-Specific Data</h2>
    <%= request.getAttribute("locations")%>
    <h2>Log Dump</h2>
    <%= request.getAttribute("dump")%>
    <script>
        $(function () {
            Highcharts.chart({
                chart: {
                    renderTo: 'container1',
                    type: 'bar'
                },
                title: {
                    text: 'Visits to each location'
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
            Highcharts.chart('container2', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: 'Nerdy or Jock?'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.y} ({point.percentage:.1f}) %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                        name: 'Percetage',
                        colorByPoint: true,
                        data: [{
                                name: 'Nerdy',
                                y: 12,
                                sliced: true,
                                selected: true
                            }, {
                                name: 'Jock',
                                y: 7
                            }, {
                                name: 'Balanced',
                                y: 26
                            }]
                    }]
            });
            Highcharts.chart({
                chart: {
                    renderTo: 'container3',
                    type: 'bar'
                },
                title: {
                    text: 'Average travel time between locations'
                },
                xAxis: {
                    categories: <%= request.getAttribute("locations-to-travel")%>
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                series: [{name: "minutes", data: <%= request.getAttribute("time-to-travel")%>}]
            });
        });
    </script>
</body>
</html>

