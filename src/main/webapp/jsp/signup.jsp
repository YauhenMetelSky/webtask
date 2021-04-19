<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
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
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/elements.css">
</head>
<body>
	<c:import url="header.jsp" />
	<section class="container-fluid bgspc">
		<form action="controller" method="POST" class="form-container">
			<h1>
				<fmt:message key="label.registration" />
			</h1>
			<p>${message}</p>
			<div class="mb-3">
				<input class="form-control" name="login" required
					placeholder=<fmt:message key="label.login" />>
			</div>
			<div class="mb-3">
				<input type="password" class="form-control" name="password" required
					placeholder=<fmt:message key="label.password" />>
			</div>
			<div class="mb-3">
				<input type="password" class="form-control"
					name="confirmed_password" required
					placeholder=<fmt:message key="label.confirm_password" />>
			</div>
			<div class="mb-3">
				<input type="email" class="form-control" name="email" required
					placeholder=<fmt:message key="label.email" />>
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" name="name" required
					placeholder=<fmt:message key="label.name" />>
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" name="surname"
					placeholder=<fmt:message key="label.surname" />>
			</div>
			<div class="mb-3">
				<input type="tel" class="form-control" name="phone" required
					placeholder=<fmt:message key="label.phone" />>
			</div>
			<div class="mb-3">
				<input type="hidden" name="command" value="sign_up">
				<button type="submit" class="btn btn-primary btn-block">
					<fmt:message key="label.submit" />
				</button>
			</div>
		</form>
	</section>
	<c:import url="footer.jsp" />
</body>
</html>