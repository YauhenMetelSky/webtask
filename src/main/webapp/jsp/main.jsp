<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib uri="customtag" prefix="custom"%>
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

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/elements.css">
<link rel="stylesheet" href="css/content.css">

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>

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
					<p>«Красота — это когда ты остаешься самим собой». Жан-Поль Готье</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel3.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Second slide label</h5>
					<p>«Самое красивое лицо в мире — ваше». Эсте Лаудер</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel2.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Third slide label</h5>
					<p>«Глядя на красивую женщину, я не могу не влюбиться в нее, я от нее без ума. Это как удар молнии и длится столько же: мгновение». Жюль Ренар</p>
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
			<h1>Content for all</h1>
			<br>
			<p> We are waiting for you </p>
		</div>

	</div>

	<div class="container">
		<div class="row">
		
		<c:forEach var="elem" items="${active_procedures_list}" varStatus="status">
			<div class="col">
				<div class="card" style="width: 18rem;">
					<img src="images/${elem.imageName}" class="card-img-top" alt="...">
					<div class="card-body">
					<div class="content_block">
					<a class="content_toggle" href="#"><fmt:message key="label.show"/></a>
						<h5 class="card-title">${elem.name }</h5>
						<p class="card-text">${elem.description}</p>
						<h5 class="card-text"><fmt:formatNumber type="number" maxFractionDigits="2" value="${elem.price}"/> BYN</h5>
					</div>
					<!-- 	<a href="#" class="btn btn-primary">Go somewhere</a> -->
					</div>
				</div>
			</div>
			</c:forEach>


		</div>
	</div>

	<!-- <div class="container-fluid bg"> -->

	<div class="container-fluid">
		<c:import url="footer.jsp" />
	</div>

<script>
$(document).ready(function(){
	$('.content_block').toggleClass('hide');
	$('.content_toggle').click(function(){
		$('.content_block').toggleClass('hide');	
		if ($('.content_block').hasClass('hide')) {
			$('.content_toggle').html('<fmt:message key="label.show"/>');
		} else {
			$('.content_toggle').html('<fmt:message key="label.hide"/>');
		}		
		return false;
	});				
});
</script>

</body>
</html>