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
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./CSS/styleLogin.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="login__main">
        <div class="login__datos">
            <a href="/" class="logoMain">
                <img src="./IMG/LogoRaviga2.svg" alt="Logo Raviga">
                <p>Raviga</p>
            </a>
            <a href="/login" class="titleInicio">Volver al inicio</a>
            <div class="login__datos_confirm">
                <p class="titleLogin">Bienvenido a Raviga</p>
                <form:form action="/loginProcess" method="POST" class="form-login-main"  modelAttribute="nuevoLogin">
                    <form:input type="email" path="email" placeholder="Tu correo electronico"/>
                    <form:errors path="email" class="text-danger" />
                    
                    <form:password path="password" placeholder="Ingresa 6 caracteres a mas"/>
					<form:errors path="password" class="text-danger" />
                    <input type="submit" value="Ingresar">
                    <div class="login__datos_confirm_register">
                        <p>¿No tienes cuenta?</p>
                        <a href="/registro">Registrate aquí</a>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="login_banner cropped">
            <img src="./IMG/evento3LoginReg.jpg" alt="">
        </div>
    </div>
</body>
</html>