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
            <a href="#!" class="logoMain">
                <img src="./IMG/LogoRaviga2.svg" alt="Logo Raviga">
                <p>Raviga</p>
            </a>
            <a href="#" class="titleInicio">Volver al Iniciar Sesión</a>
            <div class="login__datos_confirm">
                <p class="titleLogin">Bienvenido a Raviga</p>
                <form action="/registroProcess" method="POST" class="form-login-main" modelAttribute="nuevoUsuario">
                    <div class="names">
                        <input type="text" name="firstname" placeholder="Nombres">
                        <input type="text" name="lastname" placeholder="Apellidos">
                    </div>
                    <input type="email" name="email" placeholder="Tu correo electrónico">
                    <input type="password" name="password" placeholder="Contraseña">
                    <input type="password" name="confirm" placeholder="Confirmar contraseña">
                    <div class="location">
                        <select name="selectDepartamento" class="departamentoClass">
                            <option value="">Departamento</option>
                            <option value="Amazonas">Amazonas</option>
                            <option value="Ancash">Ancash</option>
                            <option value="Apurímac">Apurímac</option>
                            <option value="Arequipa">Arequipa</option>
                            <option value="Ayacucho">Ayacucho</option>
                            <option value="Cajamarca">Cajamarca</option>
                            <option value="Callao">Callao</option>
                            <option value="Cuzco">Cuzco </option>
                            <option value="Huancavelica">Huancavelica</option>
                            <option value="Huánuco">Huánuco</option>
                            <option value="Ica">Ica</option>
                            <option value="Junín">Junín</option>
                            <option value="La_Libertad">La Libertad</option>
                            <option value="Lambayeque">Lambayeque</option>
                            <option value="Lima">Lima</option>
                            <option value="Loreto">Loreto</option>
                            <option value="Madre_de_Dios">Madre de Dios</option>
                            <option value="Moquegua">Moquegua</option>
                            <option value="Pasco">Pasco</option>
                            <option value="Piura">Piura</option>
                            <option value="Puno">Puno</option>
                            <option value="San_Martín">San Martín</option>
                            <option value="Tacna">Tacna</option>
                            <option value="Tumbes">Tumbes</option>
                            <option value="Ucayali">Ucayali</option>
                            <!--Fuente: https://codificalo.com/select-dependiente-departamentos-provincias-y-distritos-de-peru-con-puro-javascript/ -->
                        </select>
                        <select name="universidad" class="universidadClass">
                        	<c:forEach var="i" items="${universidades}">
                        		<option value="${i}">${i}</option>
                        	</c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="Ingresar">
                </form>
            </div>
        </div>
        <div class="login_banner cropped">
            <img src="./IMG/evento3LoginReg.jpg" alt="">
        </div>
    </div>
</body>
</html>