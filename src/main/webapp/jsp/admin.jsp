<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html> 
<head>
<meta name="viewport" content="width=device-width, initial-scale=1,  shrink-to-fit=no">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<title>Main page</title>
</head>
<body>
<div class="container-sm bg-success text-white">
	<h1>Hello, ${user.name}</h1>
	</div>
	    <main class="container">
        <header class="col-sm-12">
            <span>Bootstrap Calender</span>
        </header>
        <section class="col-sm-12">
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>

        </section>
	<br />
	<div class="container-sm">
	<form action="controller" method="POST">
		<p>
		   <label>Name</label>
			<input type="text" name="name" value="" class="form-control"> </p>
			<input type="hidden" name="command"	value="find_by_name">
			<button type="submit" class="btn btn-primary">Find</button>

	</form>
	</br>
	<form action="controller" method="POST">
	<button type="submit" class="btn btn-primary">Find all users</button>
	<input type="hidden" name="command" value="find_all_users">
	</form>
	</div>
	<br />
	<div class="container-sm bg-success text-white">
	<a href="controller">Hello Servlet</a>	
	</div>
	</script>
    <script type="text/javascript">
        $(function() {
            $('#datetimepicker1').datetimepicker();
        });
    </script>
</body>
</html>
