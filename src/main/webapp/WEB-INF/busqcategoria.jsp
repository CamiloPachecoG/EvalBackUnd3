<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Busqueda Producto</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">Prueba3</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	  	  <c:if test="${registrado == 1 }">
	      <li class="nav-item active">
	        <a class="nav-link" href="/">Inicio</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/producto">Productos</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/categoria">Categorias</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/producto/busquedaCat">Buscar Categoria</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/producto/busqueda">Buscar Producto</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/producto/carrito">Carrito de compra</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/logout">Salir</a>
	      </li>
	      </c:if>
	      <c:if test="${registrado != 1}">
	      <li class="nav-item mr-sm-2">
	        <a class="nav-link" href="/login">Login</a>
	      </li>
	      <li class="nav-item my-2 my-sm-0">
	        <a class="nav-link" href="/login/register">Register</a>
	      </li>
	      </c:if>
	    </ul>
	  </div>
	</nav>
	
	<div class="container justify-content-center align-items-center">
		<div class="card col-md-3 mt-5">
		  <div class="card-body">
		    <h5 class="card-title">Busqueda de producto por categoria</h5>
		    <form action="/producto/busqueda/cat" method="POST">
			  <div class="mb-3">
			    <input type="text" class="form-control" name="nombre" placeholder="Nombre de la categoria">
			  </div>	  
			  <button type="submit" class="btn btn-outline-primary btn-block">Buscar</button>
			  <p><c:out value="${mensaje}"></c:out></p>
			</form>
		  </div>
	  	</div>
	  	
	  	<div class="card col-md-9 mt-5 text-center">
	  		<div class="card-body">
	  			<h5 class="card-title">Lista de resultado</h5>
	  			<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">Nombre</th>
				      <th scope="col">Descripcion</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Cantidad</th>
				      <th scope="col">Categoria</th>
				    </tr>
				  </thead>
				  <tbody>
					<c:forEach var="busq" items="${listaBusqueda}">		
						<tr>
					      <td><c:out value="${busq.nombre}"></c:out></td>
					      <td><c:out value="${busq.descripcion}"></c:out></td>
					      <td><c:out value="${busq.precio}"></c:out></td>
					      <td><c:out value="${busq.cantidad}"></c:out></td>
					      <td><c:out value="${busq.categoria.nombre}"></c:out></td>
					    </tr>
					</c:forEach>
				  </tbody>
				</table>
	  		</div>
	  	</div>
	</div>
	
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</html>