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
<title>Doctor page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="container-fluid bgfix">
		<h1>Doctor page</h1>
		<div class="row">
			<!--  <div class="form-group"> -->
			<div class="col">
			<div>
		<h6>Show all my schedules</h6>
			<form action="controller" method="GET">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.my_schedules" />
				</button>
				<input type="hidden" name="command" value="find_all_schedules_by_doctor">
			</form>
			</div>
					
		<div>
		<h6>Show me actual schedule</h6>
			<form action="controller" method="GET">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.my_active_schedules" />
				</button>
				<input type="hidden" name="command" value="find_all_active_schedules_by_doctor">
			</form>
			</div>
			<div>
			<h6>Show appointments to me</h6>
			<form action="controller" method="GET">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.appointments_to_me" />
				</button>
				<input type="hidden" name="command" value="find_all_appointments_by_doctor_id">
			</form>
			</div>
			<h6>Add schedule</h6>
			<div class="form-inline">
			<form action="controller" method="GET">
				<input type="date" min="2021-05-16" name="date" value="" required class="form-control"
					placeholder=<fmt:message key="label.date"/>> 
				<input type="time" min="09:00" max="21:00" step="2" name="start_time" value="09:00:00" required class="form-control"
					placeholder=<fmt:message key="label.start_time"/>> 
				<input	type="time" name="end_time" min="09:00" max="21:00" step="2" value="21:00:00" required class="form-control"
					placeholder=<fmt:message key="label.end_time"/>> <input
					type="hidden" name="command" value="add_doctor_schedule">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="label.submit" />
				</button>
			</form>
		</div>
		</div>
		<div class="col">
				<h4>Name: ${user.name }</h4>	
				<h4>Surname: ${user.surname }</h4>	
				<h4>Phone number: ${user.phone }</h4>	
				<h4>Email: ${user.email }</h4>			
					<div>
			<form action="controller" method="GET">
				<button type="submit" class="btn btn-primary"><fmt:message key="label.change" />
				</button>
				<input type="hidden" name="command" value="to_change_personal_info">
			</form>
		</div>
			</div>
		</div>
		</div>
		
			<div>
		<c:if test="${doctor_schedules_list ne null}">
			<table class="table table-striped">	
			<tr>
				<th><fmt:message key="label.date" /></th>
				<th><fmt:message key="label.start_time" /></th>
				<th><fmt:message key="label.end_time" /></th>
			</tr>

		<c:forEach var="elem" items="${doctor_schedules_list}" varStatus="status">
			<tr>
				<td><c:out value="${elem.date }" /></td>
				<td><c:out value="${elem.startTime}" /></td>
				<td><c:out value="${elem.endTime }" /></td>
			</tr>
		</c:forEach>
			</table>
			<nav aria-label="Page navigation example">
			<c:if test="${number_of_pages >1}">
						<ul class="pagination">
							<c:forEach begin="1" end="${number_of_pages }" var="i">
								<li class="page-item">
									<form action="controller" method="GET">
										<button type="submit" class="page-link" value="${i }">
											<c:out value="${i }" />
										</button>
										<input type="hidden" name="start_from" value="${i}"> <input
											type="hidden" name="command" value="find_schedules_by_doctor_pagination">
									</form>
								</li>
							</c:forEach>
						</ul>
						</c:if>
					</nav>
					</c:if>
		</div> 
		
			<div>
			<table class="table table-striped">	
		<c:if test="${doctor_active_schedules_list ne null}">
			<tr>
				<th><fmt:message key="label.date" /></th>
				<th><fmt:message key="label.start_time" /></th>
				<th><fmt:message key="label.end_time" /></th>
				<th><fmt:message key="label.action" /></th>
			</tr>
		</c:if>

		<c:forEach var="elem" items="${doctor_active_schedules_list}" varStatus="status">
			<tr>
				<td><c:out value="${elem.date }" /></td>
				<td><c:out value="${elem.startTime}" /></td>
				<td><c:out value="${elem.endTime }" /></td>
					<td>	
		       <form action="controller" method="POST">
				<button type="submit" class="btn btn-danger">
					<fmt:message key="label.change"/>
				</button>
				<input type="hidden" name="doctor_schedule_id" value="${elem.id}">
				<input type="hidden" name="command" value="to_change_schedule">
			</form>	
			</td>
			</tr>
		</c:forEach>
			</table>
		</div> 
		
		 <div>
			
		<table class="table table-striped">
		<c:if test="${appointments_list ne null}">
			<tr>
				<th><fmt:message key="label.date"/></th>
				<th><fmt:message key="label.start_time"/></th>
				<th><fmt:message key="label.end_time"/></th>
				<th><fmt:message key="label.client"/></th>
				<th><fmt:message key="label.procedure"/></th>
			</tr>
		</c:if>
		
		<c:forEach var="elem" items="${appointments_list}" varStatus="status">
		<tr>
		<td><c:out value="${elem.date }" /></td>
		<td><c:out value="${elem.startTime }" /></td>
		<td><c:out value="${elem.endTime }" /></td>
		<td><c:out value="${elem.user.name }" /><c:out value=" " /><c:out value="${elem.user.surname} " /></td>				
		<td><c:out value="${elem.procedure.name }" /></td>		
		</tr>
		</c:forEach>
		</table>
		</div> 	
		
	
	<c:import url="footer.jsp" />
</body>
</html>
