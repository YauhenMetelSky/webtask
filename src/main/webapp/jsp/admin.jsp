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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
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
		<div class="row">
			<!--  <div class="form-group"> -->
			<div class="col">
				<div class="form-inline">
					<form action="controller" method="GET">
						<input type="text" name="name" value="" class="form-control"
							placeholder=<fmt:message key="label.name"/>> <input
							type="hidden" name="command" value="find_by_name">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.find" />
						</button>
					</form>
				</div>
				<br />

				<div class="form-inline"> 
					<form action="controller" method="GET">
						<input type="text" name="surname" value="" class="form-control"
							placeholder=<fmt:message key="label.surname"/>> <input
							type="hidden" name="command" value="find_by_surname">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.find" />
						</button>
					</form>
				</div>
				<br />

				<div class="form-inline">
					<form action="controller" method="POST">
						<input type="text" class="form-control" name="email_to"
							placeholder=<fmt:message key="label.email"/> />
							<input type="text" class="form-control" name="email_text"
							placeholder=<fmt:message key="label.email_text"/> />
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.send_email" />
						</button>
						<input type="hidden" name="command" value="send_email">
					</form>
				</div>
				<br />
				</div>
				<div class="col">
				<h4>Name: ${user.name }</h4>
				<h4>Surname: ${user.surname }</h4>
				<h4>Phone number: ${user.phone }</h4>
				<h4>Email: ${user.email }</h4>
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.change" />
						</button>
						<input type="hidden" name="command"
							value="to_change_personal_info">
					</form>
				</div>
				</div>
				</div>
				
<!-- 				</row>
				<row> -->
            <div class="row">
				<div class="col-sm-3">
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.add_procedure" />
						</button>
						<input type="hidden" name="command" value="to_add_procedure">
					</form>
				</div>
				<br />
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.find_all_procedures" />
						</button>
						<input type="hidden" name="command" value="find_all_procedures">
					</form>
				</div>
				<br />
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.find_all_schedules" />
						</button>
						<input type="hidden" name="command" value="find_all_schedules">
					</form>
				</div>
				<br />
					<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.find_all_users" />
						</button>
						<input type="hidden" name="command" value="find_all_users">
					</form>
				</div>			
				</div>
				<div class="col-sm-3">
					<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.find_all_finished" />
						</button>
						<input type="hidden" name="command" value="find_all_finished_appointments">
					</form>
				</div>
				<br />
				<!-- <h1>Show all new appointments</h1> -->
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.find_all_todays_appointments" />
						</button>
						<input type="hidden" name="command"
							value="find_todays_appointments">
					</form>
				</div>
				<br />
				<div>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.show_all_new_app" />
						</button>
						<input type="hidden" name="command"
							value="find_all_new_appointments">
					</form>
				</div>
				</div>		
				<div class="col">
				<div style="width:100%; height:250px;overflow:scroll">
				<table class="table table-striped" id="app_table">
				</table>
				</div>
			</div>
		</div>

		<br />
			
				<c:if test="${(appointments_list ne null) and (appointments_list[0].status ne 'FINISHED')}">
					<table class="table table-striped">
					<tr>
						<th><fmt:message key="label.id" /></th>
						<th><fmt:message key="label.date" /></th>
						<th><fmt:message key="label.start_time" /></th>
						<th><fmt:message key="label.end_time" /></th>
						<th><fmt:message key="label.doctor" /></th>
						<th><fmt:message key="label.procedure" /></th>
						<th><fmt:message key="label.client" /></th>
						<th><fmt:message key="label.phone" /></th>
						<th><fmt:message key="label.confirm" /></th>
						<th><fmt:message key="label.finish" /></th>
					</tr>
				<c:forEach var="elem" items="${appointments_list}"
					varStatus="status">
					<tr>
						<td><c:out value="${elem.id }" /></td>
						<td><c:out value="${elem.date }" /></td>
						<td><c:out value="${elem.startTime }" /></td>
						<td><c:out value="${elem.endTime }" /></td>
						<td>
						<button class="btn btn-link" id="button_doctor"
								value="${elem.doctor.userId}:${elem.date}">
								<c:out value="${elem.doctor.name }" />
								<c:out value=" " />
								<c:out value="${elem.doctor.surname} " />
							</button>
							</td>
						<td><c:out value="${elem.procedure.name }" /></td>
						<td><c:out value="${elem.user.name }" /> <c:out value=" " />
							<c:out value="${elem.user.surname} " /></td>
						<td><c:out value="${elem.user.phone }" /></td>
						<td>
							<form action="controller" method="POST">
								<button type="submit" class="btn btn-success btn-block">
									<fmt:message key="label.confirm" />
								</button>
								<input type="hidden" name="app_id" value="${elem.id}"> <input
									type="hidden" name="command" value="confirm_appointment">
							</form>
						    <form action="controller" method="POST">
								<button type="submit" class="btn btn-primary btn-block">
									<fmt:message key="label.change" />
								</button>
								<input type="hidden" name="id" value="${elem.user.userId}">
								<input type="hidden" name="app_id" value="${elem.id}"> <input
									type="hidden" name="command" value="to_change_appointment">
							</form>
						<form action="controller" method="POST">
								<button type="submit" class="btn btn-danger btn-block">
									<fmt:message key="label.decline" />
								</button>
								<input type="hidden" name="app_id" value="${elem.id}"> <input
									type="hidden" name="command" value="cancel_appointment">
							</form>
						</td>
						
						<td>
							<form action="controller" method="POST">
								<button type="submit" class="btn btn-danger btn-sm">
									<fmt:message key="label.finish" />
								</button>
								<input type="hidden" name="app_id" value="${elem.id}"> <input
									type="hidden" name="command" value="finish_appointment">
							</form>
						</td> 
						
					</tr>
				</c:forEach>
			</table>			
			<nav aria-label="Page navigation example">
			           <c:if test="${number_of_pages >1}">
						<ul class="pagination">
							<c:forEach begin="1" end="${number_of_pages }" var="i">
								<li class="page-item">
									<form action="controller" method="POST">
										<button type="submit" class="page-link" value="${i }">
											<c:out value="${i }" />
										</button>
										<input type="hidden" name="start_from" value="${i}"> 
										<input type="hidden" name="app_status" value="${appointments_list[0].status}">
										<input type="hidden" name="command" value="find_appointments_pagination">
									</form>
								</li>
							</c:forEach>
						</ul>
						</c:if>
					</nav>
			</c:if>
		
		
				<c:if test="${(appointments_list ne null) and (appointments_list[0].status eq 'FINISHED')}">
			<table class="table table-striped">
					<tr>
						<th><fmt:message key="label.id" /></th>
						<th><fmt:message key="label.date" /></th>
						<th><fmt:message key="label.start_time" /></th>
						<th><fmt:message key="label.end_time" /></th>
						<th><fmt:message key="label.doctor" /></th>
						<th><fmt:message key="label.procedure" /></th>
						<th><fmt:message key="label.client" /></th>
						<th><fmt:message key="label.phone" /></th>
						<th><fmt:message key="label.price" /></th>					
					</tr>
				<c:forEach var="elem" items="${appointments_list}"
					varStatus="status">
					<tr>
						<td><c:out value="${elem.id }" /></td>
						<td><c:out value="${elem.date }" /></td>
						<td><c:out value="${elem.startTime }" /></td>
						<td><c:out value="${elem.endTime }" /></td>
						<td>
						<button class="btn btn-link" id="button_doctor"
								value="${elem.doctor.userId}:${elem.date}">
								<c:out value="${elem.doctor.name }" />
								<c:out value=" " />
								<c:out value="${elem.doctor.surname} " />
							</button>
							</td>
						<td><c:out value="${elem.procedure.name }" /></td>
						<td><c:out value="${elem.user.name }" /> <c:out value=" " />
							<c:out value="${elem.user.surname} " /></td>
						<td><c:out value="${elem.user.phone }" /></td>
						<td><c:out value="${elem.procedure.price }" /></td>
					</tr>
				</c:forEach>
			</table>
			<nav aria-label="Page navigation example">
			      <c:if test="${number_of_pages >1}">
						<ul class="pagination">
							<c:forEach begin="1" end="${number_of_pages }" var="i">
								<li class="page-item">
									<form action="controller" method="POST">
										<button type="submit" class="page-link" value="${i }">
											<c:out value="${i }" />
										</button>
										<input type="hidden" name="start_from" value="${i}"> 
										<input type="hidden" name="app_status" value="${appointments_list[0].status}">
										<input type="hidden" name="command" value="find_appointments_pagination">
									</form>
								</li>
							</c:forEach>
						</ul>
						</c:if>
					</nav>
					
			</c:if>	
				
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
						<th><fmt:message key="label.role" /></th>
						<th><fmt:message key="label.change_role" /></th>
						<th><fmt:message key="label.action" /></th>
					</tr>

					<c:forEach var="elem" items="${list}" varStatus="status">
						<tr>
							<td><c:out value="${status.count }" /></td>
							<td><c:out value="${elem.userId }" /></td>
							<td><c:out value="${elem.name }" /></td>
							<td><c:out value="${elem.surname }" /></td>
							<td><c:out value="${elem.email }" /></td>
							<td><c:out value="${elem.phone }" /></td>
							<td><c:out value="${elem.blocked }" /></td>
							<td><c:out value="${elem.role }" /></td>
							<td>
							<div class="dropdown">
							<form action="controller" method="post">
				<button class="btn btn-danger btn-sm dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<fmt:message key="label.change_role" />
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				            <button type="submit" class="dropdown-item" name="command" value="change_user_role_doctor">
										<fmt:message key="label.change_role_to_doctor" />
									</button>
									
								            <button type="submit" class="dropdown-item" name="command" value="change_user_role_admin">
										<fmt:message key="label.change_role_to_admin" />
									</button>
									
						          <button type="submit" class="dropdown-item" name="command" value="change_user_role_user">
										<fmt:message key="label.change_role_to_user" />
									</button>
									<input type="hidden" name="id" value="${elem.userId}">					
				</div>
				</form>
			</div>
							
							</td>
							<c:if test="${elem.blocked==false}">
								<td>
									<form action="controller" method="POST">
										<button type="submit" class="btn btn-danger btn-sm" >
											<fmt:message key="label.block_user" />
										</button>
										<input type="hidden" name="id" value="${elem.userId}">
										<input type="hidden" name="command" value="block_user">
									</form>
								</td>
							</c:if>
							<c:if test="${elem.blocked==true}">
								<td>
									<form action="controller" method="POST">
										<button type="submit" class="btn btn-success btn-sm">
											<fmt:message key="label.unblock_user" />
										</button>
										<input type="hidden" name="id" value="${elem.userId}">
										<input type="hidden" name="command" value="unblock_user">
									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:forEach begin="1" end="${number_of_pages }" var="i">
								<li class="page-item">
									<form action="controller" method="POST">
										<button type="submit" class="page-link" value="${i }">
											<c:out value="${i }" />
										</button>
										<input type="hidden" name="start_from" value="${i}"> <input
											type="hidden" name="command" value="find_users_pagination">
									</form>
								</li>
							</c:forEach>
						</ul>
					</nav>
				</c:if>
			</table>

			<table class="table table-striped">
				<c:if test="${procedures_list ne null}">
					<tr>
						<th><fmt:message key="label.id" /></th>
						<th><fmt:message key="label.name" /></th>
						<th><fmt:message key="label.image_name" /></th>
						<th><fmt:message key="label.price" /></th>
						<th><fmt:message key="label.is_active" /></th>
						<th><fmt:message key="label.description" /></th>
						<th><fmt:message key="label.duration" /></th>
						<th><fmt:message key="label.change" /></th>
						<th><fmt:message key="label.action" /></th>
					</tr>
				

				<c:forEach var="elem" items="${procedures_list}" varStatus="status">
					<tr>
						<td><c:out value="${elem.procedureId }" /></td>
						<td><c:out value="${elem.name }" /></td>
						<td><c:out value="${elem.imageName }" /></td>
						<td><c:out value="${elem.price }" /></td>
						<td><c:out value="${elem.active }" /></td>
						<td><c:out value="${elem.description }" /></td>
						<td><c:out value="${elem.duration.toMinutes()}" /></td>
						<td>
							<form action="controller" method="POST">
								<button type="submit" class="btn btn-danger">
									<fmt:message key="label.change" />
								</button>
								<input type="hidden" name="procedure_id"
									value="${elem.procedureId}"> <input type="hidden"
									name="command" value="to_change_procedure">
							</form>
						</td>
						<c:if test="${elem.active==false}">
							<td>
								<form action="controller" method="POST">
									<button type="submit" class="btn btn-primary">
										<fmt:message key="label.activate" />
									</button>
									<input type="hidden" name="procedure_id"
										value="${elem.procedureId}"> <input type="hidden"
										name="command" value="activate_procedure">
								</form>
							</td>
						</c:if>
						<c:if test="${elem.active==true}">
							<td>
								<form action="controller" method="POST">
									<button type="submit" class="btn btn-danger">
										<fmt:message key="label.deactivate" />
									</button>
									<input type="hidden" name="procedure_id"
										value="${elem.procedureId}"> <input type="hidden"
										name="command" value="deactivate_procedure">
								</form>
							</td>
						</c:if>
					</tr>
				</c:forEach>
               </c:if>
			</table>

			<table class="table table-striped">
				<c:if test="${doctor_schedules_list ne null}">
					<tr>
						<th><fmt:message key="label.date" /></th>
						<th><fmt:message key="label.doctor" /></th>
						<th><fmt:message key="label.start_time" /></th>
						<th><fmt:message key="label.end_time" /></th>
						<th><fmt:message key="label.change" /></th>
						<th><fmt:message key="label.cancel" /></th>
					</tr>

					<c:forEach var="elem" items="${doctor_schedules_list}"
						varStatus="status">
						<tr>
							<td><c:out value="${elem.date }" /></td>
							<td><c:out value="${elem.doctor.surname }" /> <c:out
									value=" ${elem.doctor.name }" /></td>
							<td><c:out value="${elem.startTime}" /></td>
							<td><c:out value="${elem.endTime }" /></td>
							<td>
								<form action="controller" method="POST">
									<button type="submit" class="btn btn-danger">
										<fmt:message key="label.change" />
									</button>
									<input type="hidden" name="doctor_schedule_id"
										value="${elem.id}"> <input type="hidden"
										name="command" value="to_change_schedule">
								</form>
							</td>
							<td>
								<form action="controller" method="POST">
									<button type="submit" class="btn btn-danger">
										<fmt:message key="label.cancel" />
									</button>
									<input type="hidden" name="doctor_schedule_id"
										value="${elem.id}"> <input type="hidden"
										name="command" value="cancel_schedule">
								</form>
							</td>
						</tr>
					</c:forEach>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:forEach begin="1" end="${number_of_pages }" var="i">
								<li class="page-item">
									<form action="controller" method="POST">
										<button type="submit" class="page-link" value="${i }">
											<c:out value="${i }" />
										</button>
										<input type="hidden" name="start_from" value="${i}"> <input
											type="hidden" name="command"
											value="find_schedules_pagination">
									</form>
								</li>
							</c:forEach>
						</ul>
					</nav>
				</c:if>
			</table>
	</div>

	<c:import url="footer.jsp" />

	<script type="text/javascript">
		$(document)
				.on(
						'click',
						"#button_doctor",
						function() {
							var app_data = $(this).val();
							console.log(app_data);
							$
									.ajax({
										type : 'POST',
										data : {
											app_data : app_data,
											command : 'find_all_appointments_by_doctor_and_date_async'
										},
										url : 'ajaxcontroller',
										success : function(result) {
											console.log(result);
											console.log(result.length);
											console.log(result.length);
											$("#app_table").empty();
											$("#app_table")
													.append(
															"<tr><th>Client</th><th>Start time</th><th>End time</th><th>Procedure</th></tr>");
											for (var i = 0; i < result.length; i++) {
												console.log("**************");
												console.log((typeof result[i])=='object');
							                    if((typeof result[i]=='object')){
												$("#app_table")
														.append(
																"<tr><td>"
																		+ result[i].user.name
																		+ "</td><td>"
																		+ result[i].startTime
																		+ "</td><td>"
																		+ result[i].endTime
																		+ "</td><td>"
																		+ result[i].procedure.name
																		+ "</td></tr>");
											} else {
												$("#app_table").append("<tr><td>"+result+"</td></tr>")
												break;
											}
											}
										}
									});
						});
	</script>
</body>
</html>
