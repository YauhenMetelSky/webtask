<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Authorization</title>
</head>
<body>
	<div class="w3-container w3-teal">
		<form action="controller" method="POST">
			Login:
			<br /> 
			<input type="text" name="login" required pattern="[A-Za-z0-9_]+@[A-Za-z]+\.[A-Za-z]{2,6}"> 
			<br />
			Password:
			<br /> 
			<input type="password" name="password" required> 
			<br />
			<input type="hidden" name="command" value="log_in" class="w3-input">
					<p>${wrong}</p>
			<input type="submit" name="submit" value="Go">
		</form>
	</div>
</body>
</html>