<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
<head>
<title>JSP - Hello world</title>
</head>
<body>
	<h1>"Hello World!" привет</h1>
	<br />
	<form action="helloservlet" method="POST">
		<input type="text" name="number" value=""> 
		<br />
		 <input type="submit" name="submit" value="push">
	</form>
	<hr />
	<a href="helloservlet">Hello Servlet</a>
</body>
</html>
