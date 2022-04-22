<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formato (fechas) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./CSS/styleConvocatoria.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<main>
        <div class="login__main">
            <div class="login__datos">
                <a href="/dashboard" class="logoMain">
                    <img src="./IMG/LogoRaviga2.svg" alt="Logo Raviga">
                    <p>Raviga</p>
                </a>
                <a href="/dashboard" class="titleInicio">Página Principal</a>
                <div class="login__datos_confirm">
                    <p class="titleLogin">Detalles de la Convocatoria</p>
                    
                    <form:form action="/create-convocatoria-process" method="POST" class="form-login-main stylebtn1" modelAttribute="convocatoria">
                        <input type="hidden" name="host" value="${user_session.id}">
                        <form:input path="name" placeholder="Nombre"/>
                        <form:errors path="name" class="text-danger" />
                        
                        <form:input path="descripcion" placeholder="Descripcion"/>
                        <form:errors path="descripcion" class="text-danger" />
                        
                        <form:input path="linkInscripcion" placeholder="Link de Inscripcion"/>
                        <form:errors path="linkInscripcion" class="text-danger" />
                        
                        <form:input path="deadline"  type="date" placeholder="Fecha límite"/>
                        <form:errors path="deadline" class="text-danger" />
                        
                        <div class="location">
                            <form:select path="categoria" class="universidadClass">
                            	<c:forEach var="i" items="${categorias}">
                            		<option value="${i.id}">${i.name}</option>
                            	</c:forEach>
                            </form:select>
                            <form:errors path="categoria" class="text-danger" />
                            <form:select path="universidad" class="universidadClass">
                                <c:forEach var="i" items="${universidades}">
                            		<option value="${i}">${i}</option>
                            	</c:forEach>
                            </form:select>
                            <form:errors path="universidad" class="text-danger" />
                            
                        </div>
                        <input type="submit" value="Publicar Convocatoria">
                    </form:form>
                </div>
            </div>
            <div class="login_banner cropped">
                <img src="./IMG/evento4CreateConvocatoria.jpg" alt="">
            </div>
        </div>
    </main>
</body>
</html>