<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Upload page</title>
</head>
<body>
	Uploads
	<form action="${pageContext.request.contextPath}/upload"
		enctype="multipart/form-data" method="POST">
		Upload File: 
		<INPUT type="file" name="file1" value="" height="150">
		<INPUT type="submit" value="Upload File">
	</form>
	<hr />
	${upload_result}
	<hr />
	${file_name}
	<hr />
</body>
</html>