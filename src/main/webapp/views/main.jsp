<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Main page</title>
</head>
<body>
<div class="w3-container w3-teal">
	<h1>Start page.</h1>
	</div>
	<br />
	<div class ="w3-container w3-teal">
	<form action="controller" method="POST">
		<p>
		   <label>Name</label>
			<input type="text" name="name" value="" class="w3-input"> </p>
			<input type="hidden" name="command"	value="find_by_name" class="w3-input">
			<input type="submit" value="Find users" class="w3-input">
	</form>
	</div>
	<br />
	<div class ="w3-center w3-teal">
	<h2>Show all users.</h2>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="show_all_users">
	 <input type="submit" name="command" value="Find users">
	</form>
	<a href="controller">Hello Servlet</a>	
	</div>
</body>
</html>
