<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Error</title>
</head>
<body>
	<h1>ERROR</h1>
	<h2>Ooops. Something goes wrong.</h2>
	Request from ${pageContext.errorData.requestURI} is failed
	<br /> Status code: ${pageContext.errorData.statusCode}
	<br /> Servlet name: ${pageContext.errorData.servletName}
	<br /> Exception: ${pageContext.exception}
	<br /> Message from exception: ${pageContext.exception.message}
</body>
</html>