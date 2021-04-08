<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<fmt:setLocale value="en-US"/>
	<fmt:setBundle basename="pagecontent"/>
	
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1,  shrink-to-fit=no charset=utf-8"/>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/signin.css">
</head>
<body>
	<section class="container-fluid bg">
		<section class="row justify-content-center">
			<section class="col-12 col-sm-6 col-md-3">
			<div class="form-container">
				<form action="controller" method="POST">
					<div class="mb-3">
						<label for="login" class="form-label"><fmt:message key="label.login"/></label> <input
							class="form-control" name="login" required placeholder=<fmt:message key="label.login"/>>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label"><fmt:message key="label.password"/></label> <input
							type="password" class="form-control" name="password" required
							placeholder=<fmt:message key="label.password"/>>
					</div>
					<p>${message}</p>
					<input type="hidden" name="command" value="log_in">
					<button type="submit" class="btn btn-primary btn-block"><fmt:message key="label.submit"/></button>
				</form>
					<form action="controller" method="post">
						<input type="submit" value =<fmt:message key="label.signup"/> class="btn btn-light">
						<input type="hidden" name="command" value="to_sign_up">
					</form>	
					</div>		
			</section>
		</section>
	</section>
</body>
</html>