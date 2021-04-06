<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,  shrink-to-fit=no">
<script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script defer src="js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Result page</title>
</head>
<body>
	<div class="container-sm bg-primary text-white">
		<h1>Result list.</h1>
	</div>
	<br />
	<table class="table table-striped">
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