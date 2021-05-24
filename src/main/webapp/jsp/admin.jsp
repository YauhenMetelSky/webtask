<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/elements.css">
<title>Admin page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bg">
	<h1>Find by name</h1>
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" name="name" value="" class="form-control"
					placeholder=<fmt:message key="label.name"/>> 
					<input type="hidden" name="command" value="find_by_name">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.find" />
				</button>
			</form>
		</div>
		<%-- <h1>Add schedule</h1>
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="date" name="date" value="" class="form-control" pattern=".*[^<>]"
					placeholder=<fmt:message key="label.date"/>> 
					<input type="hidden" name="command" value="add_schedule">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.submit" />
				</button>
			</form>
		</div> --%>
			<p>${message}</p>
		<br />
		<h1>Add procedure</h1>
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" name="procedure_name" value="" pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.name"/>> 
					<input type="text" name="procedure_image" value="" pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.image"/>>
					<input type="text" name="procedure_price" value="" required pattern="\d+[.]\d+" class="form-control"
					placeholder=<fmt:message key="label.price"/>>
					<input type="text" name="duration" value="" required pattern="\d+" class="form-control"
					placeholder=<fmt:message key="label.duration"/>>
					<input type="text" name="description" value="" required pattern=".*[^<>]" class="form-control"
					placeholder=<fmt:message key="label.description"/>>
					<input type="hidden" name="command" value="add_procedure">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.add" />
				</button>
			</form>
		</div>
			<p>${message}</p>
		<br />
		<h1>Find all users</h1>
		<div>
			<form action="controller" method="POST">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.find_all" />
				</button>
				<input type="hidden" name="command" value="find_all_users">
			</form>
		</div>
		<br />
		<h1>Send email</h1>
		<div class="form-inline">
			<form action="controller" method="POST">
				<input type="text" class="form-control" name="email_to" placeholder="email" />
				<button type="submit" class="btn btn-primary">Send email</button>
				<input type="hidden" name="command" value="send_email">
			</form>
		</div>
	</div>
	<h1>Show all new appointments</h1>
		<div>
			<form action="controller" method="POST">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.find_all" />
				</button>
				<input type="hidden" name="command" value="find_all_new_appointments">
			</form>
		</div>
		<table class="table table-striped">
		<c:if test="${appointments_list ne null}">
			<tr>
				<th><fmt:message key="label.id"/></th>
				<th><fmt:message key="label.date"/></th>
				<th><fmt:message key="label.start_time"/></th>
				<th><fmt:message key="label.end_time"/></th>
				<th><fmt:message key="label.doctor"/></th>
				<th><fmt:message key="label.procedure"/></th>
				<th><fmt:message key="label.client"/></th>
				<th><fmt:message key="label.phone"/></th>
				<th><fmt:message key="label.confirm"/></th>
				<th><fmt:message key="label.change"/></th>			
			</tr>
		</c:if>
		
		<c:forEach var="elem" items="${appointments_list}" varStatus="status">
		<tr>
		<td><c:out value="${elem.id }" /></td>
		<td><c:out value="${elem.date }" /></td>
		<td><c:out value="${elem.startTime }" /></td>
		<td><c:out value="${elem.endTime }" /></td>
		<td><c:out value="${elem.doctor.name }" /><c:out value=" " /><c:out value="${elem.doctor.surname} " /></td>				
		<td><c:out value="${elem.procedure.name }" /></td>
		<td><c:out value="${elem.user.name }" /><c:out value=" " /><c:out value="${elem.user.surname} " /></td>	
		<td><c:out value="${elem.user.phone }" /></td>	
		<td>	
		
		<form action="controller" method="POST">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.confirm"/>
				</button>
				<input type="hidden" name="command" value="#">
			</form>	
			</td>
			<td>	
		<form action="controller" method="POST">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.change"/>
				</button>
				<input type="hidden" name="command" value="#">
			</form>		
		</td>
		</tr>
		</c:forEach>
		</table>
		<br />
	<table class="table table-striped">	
		<c:if test="${list ne null}">
			<tr>
				<th><fmt:message key="label.number" /></th>
				<th><fmt:message key="label.id" /></th>
				<th><fmt:message key="label.name" /></th>
				<th><fmt:message key="label.surname" /></th>
				<th><fmt:message key="label.email" /></th>
				<th><fmt:message key="label.phone" /></th>
				<th><fmt:message key="label.blocked" /></th>
			</tr>
		</c:if>

		<c:forEach var="elem" items="${list}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td><c:out value="${elem.userId }" /></td>
				<td><c:out value="${elem.name }" /></td>
				<td><c:out value="${elem.surname }" /></td>
				<td><c:out value="${elem.email }" /></td>
				<td><c:out value="${elem.phone }" /></td>
				<td><c:out value="${elem.blocked }" /></td>
			</tr>
		</c:forEach>

	</table>

	<c:import url="footer.jsp" />
</body>
</html>
