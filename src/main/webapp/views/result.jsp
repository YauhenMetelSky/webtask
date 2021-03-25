<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Result page</title>
</head>
<body>
	<div class="w3-center w3-teal">
		<h1>Result list.</h1>
	</div>
	<br />
	<table class="w3-table w3-striped w3-bordered">
		<tr>
			<th>Number</th>
			<th>User id</th>
			<th>Name</th>
			<th>Surname</th>
		</tr>
		<c:forEach var="elem" items="${lst}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td><c:out value="${elem.id }" /></td>
				<td><c:out value="${elem.name }" /></td>
				<td><c:out value="${elem.surname }" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>