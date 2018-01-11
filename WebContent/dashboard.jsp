<%@page import="java.sql.Date"%>
<%@page import="controllers.BloodPressureController"%>
<%@page import="domain.BloodPressure"%>
<%@ page import="domain.User" %>
<%@ page import="controllers.UserController" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PredigAppWeb</title>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="images/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="images/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="css/themify-icons.css" rel="stylesheet">
    
    <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <!--  script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="js/demo.js"></script>
</head>
<body>
<%
    HttpSession httpSession = request.getSession(false);
    if(session == null || session.getAttribute("loggedInUser") == null){
%>
<h2>Please, <a href="login.jsp">log in</a></h2>
<%
}else {
    String patientNif = request.getParameter("patient");
    String patientId = UserController.getUserId(patientNif);
    String patientName = UserController.getUserName(patientNif);
    BloodPressure bloodPressure = BloodPressureController.getLastBloodPressureByUserId(patientId);
    String systolic[] = BloodPressureController.getBloodPressureSystolic(patientId);
    String diastolic[] = BloodPressureController.getBloodPressureDiastolic(patientId);
    String pulse[] = BloodPressureController.getBloodPressurePulse(patientId);
%>
<div class="wrapper">
    <div class="sidebar" data-background-color="white" data-active-color="danger">

        <!--
            Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
            Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
        -->

        <div class="sidebar-wrapper">
            <div class="logo">
                PredigSystem
            </div>

            <ul class="nav">
                <li class="active">
                    <a href="dashboard.jsp?patient=<%= patientNif %>">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="schedule.jsp?patient=<%= patientNif %>">
                        <i class="ti-user"></i>
                        <p>Patient Schedule</p>
                    </a>
                </li>
                <li>
                    <a href="new_visit.jsp?patient=<%= patientNif %>">
                        <i class="ti-plus"></i>
                        <p>Add new visit</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="#"><%= patientName %></a>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="numbers">
                                            <p>Last systolic measure</p>
                                            <%= bloodPressure != null ? bloodPressure.getSystolic() : "Not yet" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="numbers">
                                            <p>Last diastolic measure</p>
                                            <%= bloodPressure != null ? bloodPressure.getDiastolic() : "Not yet" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="numbers">
                                            <p>Last pulse measure</p>
                                            <%= bloodPressure != null ? bloodPressure.getPulse() : "Not yet" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <div class="card">
                            <div class="content">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="numbers">
                                            <p>Last measure date</p>
                                            <%
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                            %>
                                            <%= bloodPressure != null ? dateFormat.format(new java.util.Date(bloodPressure.getDate())): "Not yet" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Blood pressure normality</h4>
                                <p class="category">Percentage of days with good, regular or bad Blood Pressure</p>
                            </div>
                            <div class="content">
                                <div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>

                                <div class="footer">
                                    <div class="chart-legend">
                                        <i class="fa fa-circle text-info"></i> Good
                                        <i class="fa fa-circle text-danger"></i> Regular
                                        <i class="fa fa-circle text-warning"></i> Bad
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title">Historic chart</h4>
                                <p class="category">Evolution of systolic and diastolic systolic</p>
                            </div>
                            <div class="content">
                                <div id="chartActivity" class="ct-chart"></div>

                                <div class="footer">
                                    <div class="chart-legend">
                                        <i class="fa fa-circle text-info"></i> Systolic
                                        <i class="fa fa-circle text-warning"></i> Diastolic
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

	<script type="text/javascript">

		var data = {
            labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
            series: [
              [<%= systolic[0] %>, <%= systolic[1] %>, <%= systolic[2] %>, <%= systolic[3] %>, <%= systolic[4] %>, 
            	  <%= systolic[5] %>, <%= systolic[6] %>, <%= systolic[7] %>, <%= systolic[8] %>, <%= systolic[9] %>],
            	  
            	  [<%= diastolic[0] %>, <%= diastolic[1] %>, <%= diastolic[2] %>, <%= diastolic[3] %>, <%= diastolic[4] %>, 
                	  <%= diastolic[5] %>, <%= diastolic[6] %>, <%= diastolic[7] %>, <%= diastolic[8] %>, <%= diastolic[9] %>]
            ]
          };

          var options = {
              seriesBarDistance: 10,
              axisX: {
                  showGrid: false
              },
              height: "245px"
          };

          var responsiveOptions = [
            ['screen and (max-width: 640px)', {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function (value) {
                  return value[0];
                }
              }
            }]
          ];


          Chartist.Line('#chartActivity', data, options, responsiveOptions);

	</script>
	
	<script type="text/javascript">

    		var dataPreferences = {
            series: [
                [25, 30, 20, 25]
            ]
        };

        var optionsPreferences = {
            donut: true,
            donutWidth: 40,
            startAngle: 0,
            total: 100,
            showLabel: false,
            axisX: {
                showGrid: false
            }
        };

	    Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);
	
	    Chartist.Pie('#chartPreferences', {
	      labels: [<%= pulse[0] %>,<%= pulse[1] %>,<%= pulse[2] %>],
	      series: [<%= pulse[0] %>, <%= pulse[1] %>, <%= pulse[2] %>]
	    });

	</script>


<%
    }
%>

</body>
	
	

	
	
	
</html>