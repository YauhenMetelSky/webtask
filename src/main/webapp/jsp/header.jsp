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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/header.css">

<title>Doctor pro</title>
</head>
<body>
	<div class="container-fluid">
		<div class="btn-toolbar justify-content-between" role="toolbar"
			aria-label="Toolbar with button groups">
			<div class="btn-group" role="group" aria-label="First group">
				<form action="controller" method="post">
					<input type="submit" value=<fmt:message key="label.home"/>
						class="btn btn-light"> <input type="hidden" name="command"
						value="to_main">
				</form>
				<form action="controller" method="post">
					<input type="submit" value=<fmt:message key="label.about"/>
						class="btn btn-light"> <input type="hidden" name="command"
						value="to_about">
				</form>
				<form action="controller" method="post">
					<input type="submit" value=<fmt:message key="label.services"/>
						class="btn btn-light"> <input type="hidden" name="command"
						value="to_services">
				</form>
				<form action="controller" method="post">
					<input type="submit" value=<fmt:message key="label.contact"/>
						class="btn btn-light"> <input type="hidden" name="command"
						value="to_contact">
				</form>
				<form action="controller" method="post">
					<input type="submit" value=<fmt:message key="label.account"/>
						class="btn btn-light"> <input type="hidden" name="command"
						value="to_personal_page">
				</form>


			</div>
			<c:if test="${user.getRole().toString() eq null }">
				<div class="header_button">
					<form action="controller" method="post">
						<input type="submit" value=<fmt:message key="label.login"/>
							class="btn btn-primary btn-sm btn-block"> <input
							type="hidden" name="command" value="to_sign_in">
					</form>
				</div>
			</c:if>
			<c:if test="${user.getRole().toString() ne null }">
				<h3>
					<fmt:message key="label.hello" />
					, ${user.name}
				</h3>
				<div class="header_button">
					<form action="controller" method="post">
						<input type="submit" value=<fmt:message key="label.logout"/>
							class="btn btn-primary btn-sm btn-block"> <input
							type="hidden" name="command" value="log_out">
					</form>
				</div>
			</c:if>

		</div>
	</div>
</body>
</html>