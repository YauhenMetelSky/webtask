<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1,  shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
 <link rel="stylesheet" href="css/signin.css"> 
</head>
<body>
	<section class="container-fluid bg">
		<section class="row justify-content-center">
			<section class="col-12 col-sm-6 col-md-3">
				<form action="controller" method="POST" class="form-container">
					<div class="mb-3">
						<label for="login" class="form-label">Email address</label> <input
							type="email" class="form-control" name="login" required
							pattern="[A-Za-z0-9_]+@[A-Za-z]+\.[A-Za-z]{2,6}"
							placeholder="Email address">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" name="password" required
							placeholder="Password">
					</div>
					<p>${wrong}</p>
					<input type="hidden" name="command" value="log_in">
					<button type="submit" class="btn btn-primary btn-block">Submit</button>
					<section class="image justify-content-center">
						<img src="images/cat.jpg" class="img-thumbnail" alt="Cinque Terre">
					</section>
				</form>
			</section>
		</section>
	</section>
</body>
</html>