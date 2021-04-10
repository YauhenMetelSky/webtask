<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1,  shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/signup.css">
</head>
<body>
	<section class="container-fluid bg">
		<section class="row justify-content-center">
			<section class="col-12 col-sm-9 col-md-6">
				<form action="controller" method="POST" class="form-container">
					<h1>Registration</h1>
					<div class="mb-3">
						<input class="form-control" name="login" required
							placeholder="login">
					</div>
					<!-- type="email" pattern="[A-Za-z0-9_]+@[A-Za-z]+\.[A-Za-z]{2,6}" -->
					<div class="mb-3">
						<input type="password" class="form-control" name="password"
							required placeholder="Password">
					</div>
					<div class="mb-3">
						<input type="password" class="form-control"
							name="confirmedpassword" required placeholder="Confirm password">
					</div>
					<div class="mb-3">
						<input type="email" class="form-control" name="email" required
							placeholder="Email">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" name="name" required
							placeholder="Name">
					</div>
					<div class="mb-3">
						<input type="text" class="form-control" name="surname" required
							placeholder="Surname">
					</div>
					<div class="mb-3">
						<input type="phone" class="form-control" name="phone" required
							placeholder="Phone">
					</div>
					<div class="mb-3">
						<input type="hidden" name="command" value="sign_up">
						<button type="submit" class="btn btn-primary btn-block">Submit</button>
					</div>

					<section class="image justify-content-center">
						<img src="images/cat.jpg" class="img-thumbnail" alt="Cinque Terre">
					</section>
				</form>
			</section>
		</section>
	</section>
</body>
</html>