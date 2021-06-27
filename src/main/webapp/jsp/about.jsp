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
<link rel="stylesheet" href="css/elements.css">
</head>
<body>
	
		<c:import url="header.jsp" />

	<div class="container-fluid bg">
		<h1>О нас.</h1>
		<p>Наша клиника была основана в 1992 году. Двумя братьями докторами. Это было тяжелое время. Развал советского союза. Тотальный дефицит и полная неразбериха с финансами и законом.Для того, что бы открыть клинику им пришлось заложить квартиру и продать единственное средство передвижения мотоцикл "Jawa"</p>
		<p>Наша клинка было первое в стране частноре медицинское учереждение, и с тех пор звание первого инноватора мы стараемся сохранить. Мы первыми в стране начали проводить процедуры с использованием лазерной техники, мы первые начали популяризировать инъекционные методики как альтернатива хирургическому вмешательству.</p>
		<p>Оглядываясь на пройденный путь мы можем с гордость сказать, мы первые в своем деле.</p>
		
	<div class="ul_text">
		<ul>
		<li>
		1992 год-основание;
		</li>
			<li>
		1993 год-переезд в центр города;
		</li>
		<li>
		1995 год-первый наёмный доктор;
		</li>
			<li>
		1996 год-первая медицинская лицензия (под номером 1);
		</li>
		<li>
		1997 год-первая 1000 довольных пациентов;
		</li>
			<li>
		1998 год- в штате 3 доктора;
		</li>
		<li>
		2005 год- собственное помещение в центре города, которое до сих пор служит нам штаб-квартирой;
		</li>
		<li>
		2007 год- приехало первое лазерное оборудование;
		</li>
				<li>
		2012 год- первый филиал;
		</li>
						<li>
		2020 год- новые достижения;
		</li>
		</ul>
		</div>
		
	</div>

		<c:import url="footer.jsp" />
	
</body>
</html>