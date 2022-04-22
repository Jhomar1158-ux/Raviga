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
<link rel="stylesheet" href="./CSS/style.css" type="text/css">
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
                <img src="./IMG/LogoRaviga2.svg" alt="Logo Raviga">
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
        <div class="carousel">
            <div class="container">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                  <!-- Indicators -->
                  <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                  </ol>
                  
                  <!-- Wrapper for slides -->
                  <div class="carousel-inner">
                    <div class="item active">
                        <img src="./IMG/portada1.jpg" alt="Los Angeles" style="width:100%;">
                    </div>
            
                    <div class="item">
                      <img src="./IMG/portada2.png" alt="Chicago" style="width:100%;">
                    </div>
                  
                    <div class="item">
                      <img src="./IMG/portada3.jpg" alt="New york" style="width:100%;">
                    </div>
                    <div class="item">
                        <img src="./IMG/portada4.jpeg" alt="New york" style="width:100%;">
                      </div>
                  </div>
              
                  <!-- Left and right controls -->
                  <a class="left carousel-control sizeImg" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="right carousel-control sizeImg" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
              </div>
        </div>
        <div class="topnav">
            <div class="search-container">
                <form action="/dashboard">
                <input type="text" name="search">
                <button type="submit"><i class="fa fa-search"></i></button>
                </form>
            </div>
            <button class="buttonCat button1">Robotica</button>
            <button class="buttonCat button1">Industria</button>
            <button class="buttonCat button1">Sector Electrico</button>
            <button class="buttonCat button1">Programacion</button>
            <button class="buttonCat button1">Matematicas</button>
        </div>
        <div class="convocatoriasOwnU">
          <div class="titleConvocatoriasOwnU">
            <i class="fa fa-star iconStyle1" aria-hidden="true"></i>
            <p class="pStyle1">Convocatorias en la ${user_session.universidad}</p>
          </div>
          <div class="mainCard">
            <div class="container2">
            <c:if test="${convocatorias_enmi_u.size() == 0}">
            	<div class="loSentimos">
       	            	<i class="fa fa-frown-o" aria-hidden="true"></i>
       	            	<p>Lo sentimos, no encontramos convocatorias en tu universidad por ahora...</p>
            	</div>
            </c:if>
            <c:if test="${convocatorias_enmi_u.size()>0}">
            	<c:forEach var="i" items="${convocatorias_enmi_u}">
            	<div class="card">
                <div class="card-header">
                  <img src="https://c0.wallpaperflare.com/preview/483/210/436/car-green-4x4-jeep.jpg" alt="rover" />
                </div>
                <div class="card-body">
                  <span class="tag tag-general">${i.categoria.name}</span>
                  <h4>
                    ${i.name}
                  </h4>
                  <p>
                    Deadline: ${i.deadline}
                  </p>
                  <div class="user">
						<c:choose>
							<c:when test="${i.attendees.contains(user)}">
								<a href="/convocatoria-remove/${i.id}" >
									<button class="buttonCard button4">
			                        <i class="fa fa-check-circle" aria-hidden="true"></i>
			                        Guardado
			                      </button>
								</a>
							</c:when>
							<c:otherwise>
								<span>
									<a href="/convocatoria-join/${i.id}">
										<button class="buttonCard button2">Guardar</button>
									</a>
								</span>
							</c:otherwise>

						</c:choose>
						<a href="/convocatoria-show/${i.id}">
								<button class="buttonCard button3">Revisar</button>
						</a>
					
                    
                    
                    <!-- ======================================================= -->
                  </div>
                </div>
              </div>
            </c:forEach>
            </c:if>
            
          </div>
        </div>
        <div class="convocatoriasInOthersU">
          <div class="titleConvocatoriasInOthersU">
            <i class="fa fa-globe iconStyle1" aria-hidden="true"></i>
            <p class="pStyle1">Descubre mas Convocatorias</p>
          </div>
          <div class="mainCard">
	            <div class="container2">
	              <c:forEach var="i" items="${convocatorias_noenmi_u}">
	              	<div class="card">
	                <div class="card-header">
	                  <img src="https://www.newsbtc.com/wp-content/uploads/2020/06/mesut-kaya-LcCdl__-kO0-unsplash-scaled.jpg" alt="ballons" />
	                </div>
	                <div class="card-body">
	                  <span class="tag tag-general">${i.categoria.name}</span>
	                  <h4>
	                      ${i.name}
	                  </h4>
	                  <p>
	                    Deadline: ${i.deadline}
	                  </p>
	                  <div class="user">
	                  	
		                  	<c:choose>
								<c:when test="${i.attendees.contains(user)}">
									<a href="/convocatoria-remove/${i.id}" >
										<button class="buttonCard button4">
				                        <i class="fa fa-check-circle" aria-hidden="true"></i>
				                        Guardado
				                      </button>
									</a>
								</c:when>
								<c:otherwise>
									<span>
										<a href="/convocatoria-join/${i.id}">
											<button class="buttonCard button2">Guardar</button>
										</a>
									</span>
								</c:otherwise>
	
							</c:choose>
							<a href="/convocatoria-show/${i.id}">
									<button class="buttonCard button3">Revisar</button>
							</a>
	                    
	                  </div>
	                </div>
	              </div>
	              </c:forEach>
	          	</div>
	        </div>
        </div>
    </main>
</body>
</html>