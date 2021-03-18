<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Title</title>
</head>
<body>
	<p>Result: ${numRes}</p>
	<br />
	<table>
	<c:forEach var="elem" items="${lst}" varStatus="status">
		<tr>
			<td><c:out value="${status.count }" /></td>	
			<td><c:out value="${elem }" /></td>
			<td>id:<c:out value="${elem.id }" /></td>
			<td>text:<c:out value="${elem.text }" /></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>