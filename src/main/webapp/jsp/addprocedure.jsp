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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
<title>Admin page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bg">		
		<h1>Add procedure</h1>
		<div class="form-inline">
		<form action="upload" enctype="multipart/form-data" method="POST">
		Upload file:
		<input type="file" name="file" value="" height="150">
			<button type="submit" class="btn btn-primary">
					<fmt:message key="label.upload" />
				</button>
		</form>
		<hr/>
		${upload_result}
		<hr/>
		${file_name}
		<hr/>		
		</div>
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" name="procedure_name" value="" pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.name"/>> 
					<input type="text" name="procedure_image" value="" pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.image"/>>
					<input type="text" name="procedure_price" value="" required pattern="\d+[.]\d{2,8}" class="form-control"
					placeholder=<fmt:message key="label.price"/>>
					<input type="text" name="duration" value="" required pattern="\d+" class="form-control"
					placeholder=<fmt:message key="label.duration"/>>
					<input type="text" name="description" value="" required pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.description"/>>
					<input type="hidden" name="command" value="add_procedure">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.add" />
				</button>
			</form>
		</div>
			<p>${message}</p>
	
	</div>

	<c:import url="footer.jsp" />
</body>
</html>
