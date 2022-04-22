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
<link rel="stylesheet" href="/CSS/styleShowConvocatoria.css" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Source+Sans+Pro:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
        <nav>
            <a href="/dashboard" class="logoMain">
                <img src="/IMG/LogoRaviga2.svg" alt="Logo Raviga">
                <p>Raviga</p>
            </a>
            <div class="profile">
            	<a href="/crear-convocatoria" class="profile_a">
                    <i class="fa fa-plus-circle" aria-hidden="true"></i>
                    <p>
                    	Crear Convocatoria
                    </p>
                </a>
                <a href="/profile" class="profile_a">
                    <i class="fa fa-user icon-profile-1" aria-hidden="true"></i>
                    <p>
                    	${user_session.firstname} ${user_session.lastname}
                    </p>
                </a>
                <a href="/logout">
                    <i class="fa fa-sign-out icon-logout-1" aria-hidden="true"></i>
                </a>
            </div>
        </nav>
    </header>
    <main>
        <div class="show__wrp">
            <div class="show__afiche">
                [INSERTAR AFICHE]
            </div>
            <div class="show__content">
                <div class="show__chat container3">
                    <!-- =========== -->
                    <div class="col-md-8">
                        <div class="settings-tray">
                            <div class="friend-drawer no-gutters friend-drawer--grey">
                                <img class="profile-image" src="https://www.clarity-enhanced.net/wp-content/uploads/2020/06/robocop.jpg" alt="">
                                <div class="text">
                                    <span class="tag tag-deadline">Deadline: ${convocatoriaThis.deadline}</span>
                                    <h6>${convocatoriaThis.name}</h6>
                                    <p class="text-muted">${convocatoriaThis.descripcion}</p>
                                    <div class="infoAdicional">
                                        <p>
                                            ${convocatoriaThis.attendees.size()} interesado(s)
                                        </p>
                                        <form action="${convocatoriaThis.linkInscripcion}" class="btnPostular">
                                         
                                            <input type="submit" value="Postular">
                                        </form>
                                    </div>
                                    
                                </div>
                                
                                
                            </div>
                        </div>
                        <div class="chat-panel panelStyle1">
                        	<c:forEach var="i" items="${convocatoriaThis.messages}">
                        		<div class="row no-gutters">
                                <div class="col-md-3 offset-md-9">
                                    <div class="chat-bubble chat-bubble--right">
                                        <p>${i.author.firstname}: ${i.content}</p>
                                    </div>
                                </div>
                            </div>
                        	</c:forEach>
                            
                            <div class="row">
                                <div class="col-12">
                                    <div class="chat-box-tray">
                                    	<form:form action="/convocatoria-message" method="post" modelAttribute="message">
                                    	
										<form:hidden path="author" value="${user_session.id}"/>
										<form:hidden path="convocatoria" value="${convocatoriaThis.id}"/>
										
										<div class="btnSubmit">
											<form:input path="content" class="form-control" placeholder="Consulta lo que desees.."/>
											<form:errors path="content" class="text-danger"/>
											<button type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
										</div>	
                                        </form:form>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============== -->
                </div>
            </div>
        </div>
    </main>
</body>
</html>