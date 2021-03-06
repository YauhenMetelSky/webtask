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
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
	<div class="container-fluid bg">
			<div>
				<h1>
					<fmt:message key="label.appointment" />
				</h1>
				 <form action="controller" method="POST" class="form-container">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<select class="form-control" name="procedure_id" required>
								<option value="${appointment.procedure.procedureId}"><c:out value="${appointment.procedure.name }" /></option>
									<c:forEach var="elem" items="${procedures_list}"
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
									<option value="${appointment.doctor.userId }"><c:out
												value="${appointment.doctor.surname}" /><c:out value=" " /><c:out
												value="${appointment.doctor.name}" /></option>
									<c:forEach var="elem" items="${doctors_list}"
										varStatus="status">
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
								<select class="form-control" name="schedule_id" id="date" required="required">
								<option value="${schedule_id}"><c:out value="${appointment.date}"/></option>
								<c:forEach var="elem" items="${doctor_schedules_list}"
										varStatus="status">
										<option value="${elem.id}"><c:out
												value="${elem.date}" />
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
							<select class="form-control" name="start_time" id="time" required="required">
								<option value="${appointment.startTime}"><c:out value="${appointment.startTime}"/></option>
										<c:forEach var="elem" items="${intervals_list}"
										varStatus="status">
										<option value="${elem}"><c:out
												value="${elem}" />
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" name="command" value="update_appointment">
						<input type="hidden" name="id" value="${appointment.user.userId}">
						<input type="hidden" name="app_id" value="${appointment.id}">
						<button type="submit" class="btn btn-primary btn-block">
							<fmt:message key="label.change_appointment" />
						</button>
					</div>
				</form>
			</div>	
	</div>
		
	<c:import url="footer.jsp" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#doctor_id").change(function() {
				var doctor_id = $('#doctor_id').val();
				$.ajax({
					type : 'POST',
					data : {
						doctor_id : doctor_id,
						command: 'find_all_Schedules_by_user_id_async'
					},
					url : 'ajaxcontroller',
					success : function(result) {
						 console.log(result); 
						console.log(result.length);
						$("#date").empty();
						for(var i =0;i<result.length;i++){
							console.log(result[i]);
							console.log(result[i].id);
							console.log(result[i].date);
							 $("#date").append("<option value='"+result[i].id+"'>"+ result[i].date+"</option>"); 
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
						command: 'find_time_intervals_by_schedule_id_async'
					},
					url : 'ajaxcontroller',
					success : function(result) {
						
						 console.log(result); 
						console.log(result.length);
							$("#time").empty();
							for(var i =0;i<result.length;i++){
								console.log(result[i]);
								 $("#time").append("<option value='"+result[i]+"'>"+ result[i]+"</option>"); 
							} 											                 
					}
				});
			});
		});
	</script>	   
	
</body>
</html>
