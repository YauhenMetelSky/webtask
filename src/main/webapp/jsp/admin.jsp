<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="pagecontent" />

<!DOCTYPE html>
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,  shrink-to-fit=no">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">

<title>Main page</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container-sm bg-success text-white">
	<h1><fmt:message key="label.hello" />, ${user.name}</h1>
	</div>
     
		<div class="container-sm">
	<form action="controller" method="POST">
		<p>
		   <label>Name</label>
			<input type="text" name="name" value="" class="form-control"> </p>
			<p>${message}</p>			
			<input type="hidden" name="command"	value="find_by_name">
			<button type="submit" class="btn btn-primary"><fmt:message key="label.find" /></button>
	</form>
	</br>
	<form action="controller" method="POST">
	<button type="submit" class="btn btn-primary"><fmt:message key="label.find_all" /></button>
	<input type="hidden" name="command" value="find_all_users">
	</form>
	</div>
	<br />
	<div class="container-sm bg-success text-white">
	<a href="controller">Hello Servlet</a>	
	</div>
	<c:import url="footer.jsp"/>
</body>
</html>
