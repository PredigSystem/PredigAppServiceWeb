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
    String patientNif = request.getParameter("patient").toString();
    String patientId = UserController.getUserId(patientNif);
    String patientName = UserController.getUserName(patientNif);
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
                <li>
                		<a href="ranking.jsp?patient=<%= patientNif %>">
                			<i class="ti-list"></i>
                			<p>Ranking</p>
                		</a>
                <li>
                <li>
                    <a href="schedule.jsp?patient=<%= patientNif %>">
                        <i class="ti-user"></i>
                        <p>Patient Schedule</p>
                    </a>
                </li>
                <li class="active">
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


							<div class="col-lg-4 col-md-4"></div>
								<div class="col-lg-4 col-md-4">
										<div class="card">
												<div class="header">
														<h4 class="title">New Visit</h4>
												</div>
												<div class="content">
														<form method="POST" action="api/visitsDoctor/insertVisit">
																<input type="hidden" id="id" name="id" value ="<%= patientId %>">
																<div class="row">
																		<div class="col-md-12">
																				<div class="form-group">
																						<label>Doctor</label>
																						<input type="text" class="form-control border-input" placeholder="Doctor" value="" id="doctor" name="doctor">
																				</div>
																		</div>
																</div>
																<div class="row">
																		<div class="col-md-6">
																				<div class="form-group">
																						<label>Date</label>
																						<input type="text" class="form-control border-input" placeholder="DD/MM/YYYY" value="" id="date" name="date">
																				</div>
																		</div>
																		<div class="col-md-6">
																				<div class="form-group">
																						<label>Time</label>
																						<input type="text" class="form-control border-input" placeholder="HH:MM" value="" id="time" name="time">
																				</div>
																		</div>
																</div>

																<div class="row">
																		<div class="col-md-12">
																				<div class="form-group">
																						<label>Reason</label>
																						<input type="text" class="form-control border-input" placeholder="Reason" value="" id="reason" name="reason">
																				</div>
																		</div>
																</div>

																<div class="text-center">
																		<button type="submit" class="btn btn-info btn-fill btn-wd">Submit</button>
																</div>
																<div class="clearfix"></div>
														</form>
												</div>
										</div>
								</div>


			</div>

<%
    }
%>

</body>
	
	

	
	
	
</html>