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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/elements.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<title>User page</title>
</head>
<body>
	<c:import url="header.jsp" />
	<div class="bgfix">
		<h1>User page</h1>

		<div class="row">
			<div class="col">

				<div>
					<h6>Show me all appointments</h6>
					<form action="controller" method="GET">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.find_all" />
						</button>
						<input type="hidden" name="command"
							value="find_all_appointments_by_user_id">
					</form>
				</div>

				<h1>
					<fmt:message key="label.appointment" />
				</h1>
				<form action="controller" method="POST" class="form-container">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<select class="form-control" name="procedure_id" required>
									<option value="">select procedure</option>
									<c:forEach var="elem" items="${active_procedures_list}"
										varStatus="status">
										<option value="${elem.procedureId}"><c:out
												value="${elem.name }" />
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col">
							<select class="form-control" name="doctor_id" id="doctor_id"
								required="required">
								<option value="">select doctor</option>
								<c:forEach var="elem" items="${doctors_list}" varStatus="status">
									<option value="${elem.userId}"><c:out
											value="${elem.surname}" /><c:out value=" " /><c:out
											value="${elem.name}" />
									</option>
								</c:forEach>
							</select>
						</div>

					</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<select class="form-control" name="schedule_id" id="date"
									required="required">
									<option value="">select date</option>
								</select>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<select class="form-control" name="start_time" id="time"
									required="required">
									<option value="">select time</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" name="command" value="add_appointment">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.appointment" />
						</button>
					</div>
				</form>

			</div>

			<div class="col">
				<h4>Name: ${user.name }</h4>
				<h4>Surname: ${user.surname }</h4>
				<h4>Phone number: ${user.phone }</h4>
				<h4>Email: ${user.email }</h4>
				<div>
					<form action="controller" method="POST">
						<button type="submit" class="btn btn-primary">
							<fmt:message key="label.change" />
						</button>
						<input type="hidden" name="command"
							value="to_change_personal_info">
					</form>
				</div>
			</div>
		</div>
	</div>


	<table class="table table-striped">
		<c:if test="${appointments_list ne null}">
			<tr>
				<th><fmt:message key="label.date" /></th>
				<th><fmt:message key="label.start_time" /></th>
				<th><fmt:message key="label.end_time" /></th>
				<th><fmt:message key="label.doctor" /></th>
				<th><fmt:message key="label.procedure" /></th>
				<th><fmt:message key="label.price" /></th>
				<th><fmt:message key="label.status" /></th>
				<th><fmt:message key="label.change" /></th>
				<th><fmt:message key="label.cancel" /></th>
			</tr>
		</c:if>

		<c:forEach var="elem" items="${appointments_list}" varStatus="status">
			<tr>
				<td><c:out value="${elem.date }" /></td>
				<td><c:out value="${elem.startTime }" /></td>
				<td><c:out value="${elem.endTime }" /></td>
				<td><c:out value="${elem.doctor.name }" />
					<c:out value=" " />
					<c:out value="${elem.doctor.surname} " /></td>
				<td><c:out value="${elem.procedure.name }" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${elem.procedure.price}"/> BYN</td>
				<td><c:out value="${elem.status }" /></td>
					<td> 
						<form action="controller" method="POST">
				<c:if test="${elem.status eq 'CLAIMED' }">
							<button type="submit" class="btn btn-primary">
								<fmt:message key="label.change" />
							</button>
							<input type="hidden" name="app_id" value="${elem.id }"> <input
								type="hidden" name="command" value="to_change_appointment">
				</c:if>
				<c:if test="${elem.status ne 'CLAIMED' }">
							<button type="submit" disabled="disabled" class="btn btn-primary">
								<fmt:message key="label.change" />
							</button>
							<input type="hidden" name="app_id" value="${elem.id }"> <input
								type="hidden" name="command" value="to_change_appointment">
				</c:if>
						</form>
					</td>
					<td>
						<form action="controller" method="POST">
				<c:if test="${(elem.status eq 'CONFIRMED') or (elem.status eq 'CLAIMED') }">
							<button type="submit" class="btn btn-danger">
								<fmt:message key="label.cancel" />
							</button>
							<input type="hidden" name="app_id" value="${elem.id }"> <input
								type="hidden" name="command" value="cancel_appointment">
				</c:if>
				<c:if test="${(elem.status ne 'CONFIRMED') and (elem.status ne 'CLAIMED') }">
							<button type="submit" disabled="disabled" class="btn btn-danger">
								<fmt:message key="label.cancel" />
							</button>
							<input type="hidden" name="app_id" value="${elem.id }"> <input
								type="hidden" name="command" value="cancel_appointment">
				</c:if>
						</form>
					</td>
			</tr>
		</c:forEach>
	</table>


	<c:import url="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
							$("#doctor_id").change(function() {
												var doctor_id = $('#doctor_id').val();
												$.ajax({
															type : 'POST',
															data : {
																doctor_id : doctor_id,
																command : 'find_all_active_schedules_by_doctor_async'
															},
															url : 'ajaxcontroller',
															success : function(result) {
																console.log(result);
																console.log(result.length);
																$("#date").empty();
																if (typeof result == 'object') {
																$("#date").append("<option value=''>select date</option>");
																	console.log("if block");
																for (var i = 0; i < result.length; i++) {
																		console.log(result[i]);
																		console.log(result[i].id);
																		console.log(result[i].date);
																		$("#date").append("<option value='"+result[i].id+"'>"
																								+ result[i].date
																								+ "</option>");
																     	}
																	} else {$("#date").append("<option value=''>"
																								+ result
																								+ "</option>");
																}
															}
														});
											});
						});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
							$("#date").change(function() {
												var schedule_id = $('#date').val();
												$.ajax({
															type : 'POST',
															data : {
																schedule_id : schedule_id,
																command : 'find_time_intervals_by_schedule_id_async'
															},
															url : 'ajaxcontroller',
															success : function(result) {
																console.log(result);
																console.log(result.length);
																$("#time").empty();
																$("#time").append("<option value=''>select time</option>");
																if(typeof result=='object'){
																for (var i = 0; i < result.length; i++) {					
																		console.log(result[i]);
																		$("#time").append(
																						"<option value='"+result[i]+"'>"
																								+ result[i]
																								+ "</option>");
																	} 
																}else {
																		$("#time").append("<option value=''>"
																								+ result
																								+ "</option>");
																	
																}
															}
														});
											});
						});
	</script>

</body>
</html>
