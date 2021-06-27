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

<!-- <link rel="stylesheet" href="css/main.css"> -->
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
		<c:import url="header.jsp" />
	<div class="container-fluid mybg">
	</br>
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


	</div>
	
		<c:import url="footer.jsp" />

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