<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1,  shrink-to-fit=no charset=utf-8" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/elements.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<title>User page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bg">
	<div class="form-inline">
			<form action="controller" method="POST">
				<input type="date" min="2021-05-16" name="date" value="${schedule.date}" required class="form-control"
					placeholder=<fmt:message key="label.date"/>> 
				<input type="time" min="09:00" max="21:00" step="2" name="start_time" value="${schedule.startTime }" required class="form-control"
					placeholder=<fmt:message key="label.start_time"/>> 
				<input	type="time" name="end_time" min="09:00" max="21:00" step="2" value="${schedule.endTime }" required class="form-control"
					placeholder=<fmt:message key="label.end_time"/>> <input
					type="hidden" name="command" value="change_doctor_schedule">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.submit" />
				</button>
			</form>
		</div>
			
	</div>
		
	<c:import url="footer.jsp" />
	   	
</body>
</html>
