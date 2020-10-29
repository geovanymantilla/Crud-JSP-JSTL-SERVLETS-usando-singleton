<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<meta charset="ISO-8859-1">
<head>
<title>Aplicación Administrador de Cursos</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> App
					Admin Cursos </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Cursos</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${curso!= null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${curso == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${curso != null}">
                                    Edit Curso
                                </c:if>
						<c:if test="${curso == null}">
                                    Add New Curso
                                </c:if>
					</h2>
				</caption>

				<c:if test="${curso != null}">
					<fieldset class="form-group">
						<label>Codigo Curso</label> <input type="text"
							value="<c:out value='${curso.codigo}' />" class="form-control"
							name="codigo" required="required" readonly>
					</fieldset>

					<fieldset class="form-group">
						<label>Nombre Curso</label> <input type="text"
							value="<c:out value='${curso.nombre}' />" class="form-control"
							name="nombre" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Creditos</label> <input type="text"
							value="<c:out value='${curso.credito}' />" class="form-control"
							name="credito">
					</fieldset>
					<!-- <input type="hidden" name="codigo" value="<c:out value='${curso.codigo}' />" />  -->
				</c:if>

				<c:if test="${curso == null}">
					<fieldset class="form-group">
						<label>Codigo Curso</label> <input type="text"
							value="<c:out value='${curso.codigo}' />" class="form-control"
							name="codigo" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Nombre Curso</label> <input type="text"
							value="<c:out value='${curso.nombre}' />" class="form-control"
							name="nombre" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Creditos</label> <input type="text"
							value="<c:out value='${curso.credito}' />" class="form-control"
							name="credito">
					</fieldset>
				</c:if>

				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>