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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/elements.css">
<title>Doctor page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bg">
		<h1>Doctor page content</h1>
		<form action="controller" method="POST">
			<select class="form-check form-check-inline" name="schedule_id">
				<c:forEach var="elem" items="${schedule_list}" varStatus="status">
					<option value="${elem.id}"><c:out value="${elem.date}" /></option>
				</c:forEach>
			</select>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_09_00" value="true"> <label
					class="form-check-label">09:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_09_30" value="true"> <label
					class="form-check-label">09:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_10_00" value="true"> <label
					class="form-check-label">10:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_10_30" value="true"> <label
					class="form-check-label">10:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_11_00" value="true"> <label
					class="form-check-label">11:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_11_30" value="true"> <label
					class="form-check-label">11:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_12_00" value="true"> <label
					class="form-check-label">12:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_12_30" value="true"> <label
					class="form-check-label">12:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_13_00" value="true"> <label
					class="form-check-label">13:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_13_30" value="true"> <label
					class="form-check-label">13:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_14_00" value="true"> <label
					class="form-check-label">14:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_14_30" value="true"> <label
					class="form-check-label">14:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_15_00" value="true"> <label
					class="form-check-label">15:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_15_30" value="true"> <label
					class="form-check-label">15:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_16_00" value="true"> <label
					class="form-check-label">16:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_16_30" value="true"> <label
					class="form-check-label">16:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_17_00" value="true"> <label
					class="form-check-label">17:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_17_30" value="true"> <label
					class="form-check-label">17:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_18_00" value="true"> <label
					class="form-check-label">18:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_18_30" value="true"> <label
					class="form-check-label">18:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_19_00" value="true"> <label
					class="form-check-label">19:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_19_30" value="true"> <label
					class="form-check-label">19:30</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_20_00" value="true"> <label
					class="form-check-label">20:00</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox"
					name="interval_20_30" value="true"> <label
					class="form-check-label">20:30</label>
			</div>
			<input type="hidden" name="command" value="add_schedule">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="label.submit" />
			</button>
		</form>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
