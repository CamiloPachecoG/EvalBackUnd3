<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
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

	<div class="d-flex justify-content-center mb-4">
		<div class="card col-md-6 mt-5 text-center">
		  <div class="card-body">
		  <h5 class="card-title">Lista de productos en el carrito</h5>
			  <table class="table">
				  <thead>
				    <tr>
				      <th scope="col">Nombre</th>
				      <th scope="col">Precio</th>
				      <th scope="col">Eliminar</th>
				      
				    </tr>
				  </thead>
			 	  <tbody>
					<c:forEach var="producto" items="${listaProductosCarro}">		
						<tr>
					      <td><c:out value="${producto.nombre}"></c:out></td>
					      <td><c:out value="${producto.precio}"></c:out></td>
					      <td><a class="btn btn-sm btn-outline-danger" href="/producto/carro/eliminar/${producto.id}" type="button">Eliminar del carrito</a></td>
					    </tr>
					</c:forEach>
			  	  </tbody>
			</table>
			<h4 class="text-success mt-4">Total a Pagar: $<c:out value = "${totalProd}"/></h4>
			<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
		  		Procesar Compra
			</button>
			
		 </div>
	   </div>
	   
	   <!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Resumen de compra</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<c:forEach var="producto" items="${listaProductosCarro}">
	      	<ul>
				<li><b>Producto:</b> <c:out value="${producto.nombre}"></c:out></li>
				<li><b>Precio: $</b><c:out value="${producto.precio}"></c:out></li>	      	
	      	</ul>
	      	</c:forEach>
	      	<div class="alert alert-info" role="alert">
  				Total: $<c:out value = "${totalProd}"/>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-outline-primary"><a href="/producto/pagar">Pagar</a></button>
	      </div>
	    </div>
	  </div>
	</div>
	
  </div>
	   
	

</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</html>