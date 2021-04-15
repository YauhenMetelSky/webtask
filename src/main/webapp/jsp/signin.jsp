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
<link rel="stylesheet" href="css/signin.css">
</head>
<body>
	<section class="container-fluid bg">
		<section class="row justify-content-center">
			<section class="col-12 col-sm-6 col-md-3">
				<div class="form-container">
					<form action="controller" method="POST">
						<div class="mb-3">
							<label for="login" class="form-label"><fmt:message
									key="label.login" /></label> <input class="form-control" name="login"
								required placeholder=<fmt:message key="label.login"/>>
						</div>
						<div class="mb-3">
							<label for="password" class="form-label"><fmt:message
									key="label.password" /></label> <input type="password"
								class="form-control" name="password" required
								placeholder=<fmt:message key="label.password"/>>
						</div>
						<p>${message}</p>
						<input type="hidden" name="command" value="log_in">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.submit" />
						</button>
					</form>
					<form action="controller" method="post">
						<input type="submit" value=<fmt:message key="label.signup"/>
							class="btn btn-light"> <input type="hidden"
							name="command" value="to_sign_up">
					</form>
				</div>
				<form action="controller" method="post">
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"	id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"><fmt:message key="label.language"/></button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<button class="dropdown-item" type="submit" name="language"
								value="en_US">English</button>
							<button class="dropdown-item" type="submit" name="language"
								value="ru_RU">Russian</button>
							<input type="hidden" name="command" value="set_locale">
						</div>
					</div>
				</form>
			</section>
		</section>
	</section>
</body>
</html>