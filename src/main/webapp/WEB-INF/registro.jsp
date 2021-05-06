<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<body>
	<div class="vh-100 row justify-content-center align-items-center col-auto">
		<div class="col-md-3">
			<div class="card">
			  <h5 class="card-header text-center">Register</h5>
			  <div class="card-body">
				<form action="/login/registerAdd" method="POST">
				  <div class="form-group">
				    <label for="nombre">Nombre:</label>
				    <input type="text" class="form-control" name="nombre">
				  </div>
				  <div class="form-group">
				    <label for="apellido">Apellido:</label>
				    <input type="text" class="form-control" name="apellido">
				  </div>
				  <div class="form-group">
				    <label for="email">Email:</label>
				    <input type="text" class="form-control" name="email">
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" name="password">
				  </div>
				  <p class="text-danger text-center"><c:out value="${mensajeReg}"></c:out></p>
				  <button type="submit" class="btn btn-outline-primary btn-sm btn-block">Registrar</button>
				  <br>
				  <a href="/login" class="text-center mt-5">Volver al Login!!!</a>
				</form>
			  </div>
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</html>