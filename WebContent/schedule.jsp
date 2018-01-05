<%@page import="java.sql.Date"%>
<%@page import="controllers.BloodPressureController"%>
<%@page import="domain.BloodPressure"%>
<%@ page import="domain.User" %>
<%@ page import="controllers.UserController" %>
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
    <link href="css/schedule.css" rel="stylesheet" />

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
	<script src="js/schedule.js"></script>
</head>
<body>
<%
    HttpSession httpSession = request.getSession(false);
    if(session == null || session.getAttribute("loggedInUser") == null){
%>
<h2>Please, <a href="login.jsp">log in</a></h2>
<%
}else {
    String patientNif = request.getParameter("patient").toString();
    String patientId = UserController.getUserId(patientNif);
    String patientName = UserController.getUserName(patientNif);
    BloodPressure bloodPressure = BloodPressureController.getLastBloodPressureByUserId(patientId);
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
                <li>
                    <a href="dashboard.jsp?patient=<%= patientNif %>">
                        <i class="ti-panel"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li  class="active">
                    <a href="schedule.jsp?patient=<%= patientNif %>">
                        <i class="ti-user"></i>
                        <p>Patient Schedule</p>
                    </a>
                </li>
                <li>
                    <a href="new_routine.jsp?patient=<%= patientNif %>">
                        <i class="ti-plus"></i>
                        <p>Add new routine</p>
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
              <br />
              <form >
              	<a href="dashboard.jsp?patient=<%= patientNif %>" class="btn btn-info btn-fill btn-wd" >Insert new visit</a>
              </form>
              <br />
              
              <div class="cd-schedule loading">
              
              	<div class="timeline">
              		<ul>
              			<li><span>09:00</span></li>
              			<li><span>09:30</span></li>
              			<li><span>10:00</span></li>
              			<li><span>10:30</span></li>
              			<li><span>11:00</span></li>
              			<li><span>11:30</span></li>
              			<li><span>12:00</span></li>
              			<li><span>12:30</span></li>
              			<li><span>13:00</span></li>
              			<li><span>13:30</span></li>
              			<li><span>14:00</span></li>
              			<li><span>14:30</span></li>
              			<li><span>15:00</span></li>
              			<li><span>15:30</span></li>
              			<li><span>16:00</span></li>
              			<li><span>16:30</span></li>
              			<li><span>17:00</span></li>
              			<li><span>17:30</span></li>
              			<li><span>18:00</span></li>
              		</ul>
              	</div> <!-- .timeline -->

              	<div class="events">
              		<ul>
              			<li class="events-group">
              				<div class="top-info"><span>Monday</span></div>

              				<ul>
              					<li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">
              						<a href="#0">
              							<em class="event-name">Abs Circuit</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="11:00" data-end="12:30" data-content="event-rowing-workout" data-event="event-2">
              						<a href="#0">
              							<em class="event-name">Rowing Workout</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="14:00" data-end="15:15"  data-content="event-yoga-1" data-event="event-3">
              						<a href="#0">
              							<em class="event-name">Yoga Level 1</em>
              						</a>
              					</li>
              				</ul>
              			</li>

              			<li class="events-group">
              				<div class="top-info"><span>Tuesday</span></div>

              				<ul>
              					<li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">
              						<a href="#0">
              							<em class="event-name">Rowing Workout</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="11:30" data-end="13:00"  data-content="event-restorative-yoga" data-event="event-4">
              						<a href="#0">
              							<em class="event-name">Restorative Yoga</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="13:30" data-end="15:00" data-content="event-abs-circuit" data-event="event-1">
              						<a href="#0">
              							<em class="event-name">Abs Circuit</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">
              						<a href="#0">
              							<em class="event-name">Yoga Level 1</em>
              						</a>
              					</li>
              				</ul>
              			</li>

              			<li class="events-group">
              				<div class="top-info"><span>Wednesday</span></div>

              				<ul>
              					<li class="single-event" data-start="09:00" data-end="10:15" data-content="event-restorative-yoga" data-event="event-4">
              						<a href="#0">
              							<em class="event-name">Restorative Yoga</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="10:45" data-end="11:45" data-content="event-yoga-1" data-event="event-3">
              						<a href="#0">
              							<em class="event-name">Yoga Level 1</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="12:00" data-end="13:45"  data-content="event-rowing-workout" data-event="event-2">
              						<a href="#0">
              							<em class="event-name">Rowing Workout</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="13:45" data-end="15:00" data-content="event-yoga-1" data-event="event-3">
              						<a href="#0">
              							<em class="event-name">Yoga Level 1</em>
              						</a>
              					</li>
              				</ul>
              			</li>

              			<li class="events-group">
              				<div class="top-info"><span>Thursday</span></div>

              				<ul>
              					<li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">
              						<a href="#0">
              							<em class="event-name">Abs Circuit</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="12:00" data-end="13:45" data-content="event-restorative-yoga" data-event="event-4">
              						<a href="#0">
              							<em class="event-name">Restorative Yoga</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="15:30" data-end="16:30" data-content="event-abs-circuit" data-event="event-1">
              						<a href="#0">
              							<em class="event-name">Abs Circuit</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="17:00" data-end="18:30"  data-content="event-rowing-workout" data-event="event-2">
              						<a href="#0">
              							<em class="event-name">Rowing Workout</em>
              						</a>
              					</li>
              				</ul>
              			</li>

              			<li class="events-group">
              				<div class="top-info"><span>Friday</span></div>

              				<ul>
              					<li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">
              						<a href="#0">
              							<em class="event-name">Rowing Workout</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="12:30" data-end="14:00" data-content="event-abs-circuit" data-event="event-1">
              						<a href="#0">
              							<em class="event-name">Abs Circuit</em>
              						</a>
              					</li>

              					<li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">
              						<a href="#0">
              							<em class="event-name">Yoga Level 1</em>
              						</a>
              					</li>
              				</ul>
              			</li>
              		</ul>
              	</div>

              	<div class="event-modal">
              		<header class="header">
              			<div class="content">
              				<span class="event-date"></span>
              				<h3 class="event-name"></h3>
              			</div>

              			<div class="header-bg"></div>
              		</header>

              		<div class="body">
              			<div class="event-info"></div>
              			<div class="body-bg"></div>
              		</div>

              		<a href="table.jsp" class="close">Close</a>
              	</div>

              	<div class="cover-layer"></div>
              </div> <!-- .cd-schedule -->

            </div>
        </div>
      </div>
</div>

<%
    }
%>

</body>	
	
	
</html>