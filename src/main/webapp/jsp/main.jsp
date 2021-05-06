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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/elements.css">

</head>
<body>

	<div class="container-fluid">
		<c:import url="header.jsp" />
	</div>

	<div id="carouselExampleCaptions" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleCaptions" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/carousel1.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>First slide label</h5>
					<p>Some representative placeholder content for the first slide.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel3.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Second slide label</h5>
					<p>Some representative placeholder content for the second
						slide.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel2.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Third slide label</h5>
					<p>Some representative placeholder content for the third slide.</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleCaptions"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>

	<div class="row">
		<div class="col">
			<h1>Our clinic is the best of the best</h1>
			<br>
			<p> We are waiting for you </p>
		</div>

		<div class="col">
			<form action="controller" method="POST" class="form-container">
				<h1>
					<fmt:message key="label.appointment" />
				</h1>
				<div class="row">
					<div class="col">
					<div class="form-group">
						<input type="text" class="form-control" name="name"
							pattern=".*[^<>]" required
							placeholder=<fmt:message key="label.name" />>
					</div>
					</div>
					<div class="col">
					<div class="form-group">
						<input type="email" class="form-control" name="email" required
							placeholder=<fmt:message key="label.email" />>
					</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
					<div class="form-group">
						<select class="form-control" required>
							<option disabled="" selected="" value=""><fmt:message key="label.procedure"/></option>
							<option>Speciality 1</option>
							<option>Speciality 2</option>
							<option>Speciality 3</option>
							<option>Speciality 4</option>
							<option>Speciality 5</option>
						</select>
						</div>
					</div>
					<div class="col">
					<div class="form-group">
						<select class="form-control" required="required">
							<option disabled="" selected="" value=""><fmt:message key="label.doctor"/></option>
							<option>Doctor 1</option>
							<option>Doctor 2</option>
							<option>Doctor 3</option>
							<option>Doctor 4</option>
							<option>Doctor 5</option>
						</select>
						</div>
					</div>
				</div>
				
				<div class="row">
				<div class="col">
				<div class="form-group">
				<input type="tel" class="form-control" name="phone" required
						pattern=".*[^<>]" placeholder=<fmt:message key="label.phone" />>
				</div>
				</div>
				<div class="col">
				<div class="form-group">
				<input type="text" id="datepicker1" class="form-control" name="app_date" required
						placeholder=<fmt:message key="label.date" />>	
					</div>			
				</div>
				</div>

				<div class="form-group">
					<input type="hidden" name="command" value="add_appointment">
					<button type="submit" class="btn btn-primary btn-block">
						<fmt:message key="label.appointment" />
					</button>
				</div>
			</form>
		</div>

	</div>

	<div class="container">
		<div class="row">
			<div class="col">
				<div class="card" style="width: 18rem;">
					<img src="images/cat.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card" style="width: 18rem;">
					<img src="images/cat.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card" style="width: 18rem;">
					<img src="images/cat.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="container-fluid bg"> -->

	<div class="container-fluid">
		<c:import url="footer.jsp" />
	</div>

	<!-- date time picker-->
	
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker();
		});
	</script>
</body>
</html>