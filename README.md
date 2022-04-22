# Looking for an opportunity? In Raviga you can find calls for volunteers or members to support or develop different projects from your own university or others.

## Pre-requirements

Knowledge of: **Java**, **Spring Framework**, **Spring Boot**, **Git** y **AWS**.

## List of technologies used and links with more information
- [Amazon Web Service](https://aws.amazon.com/) 
- [Spring Framework](https://spring.io/projects/spring-framework) 
- [Spring Boot](https://spring.io/projects/spring-boot) 
- [Java](https://www.java.com/es/) 
- [Bootstrap](https://getbootstrap.com/) 

**Installation of Dependecies**
```xml
<!-- DEPENDENCIAS PARA BOOTSTRAP -->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>webjars-locator</artifactId>
            <version>0.30</version>
        </dependency>
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>bootstrap</artifactId>
            <version>5.0.1</version>
        </dependency>
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.6.0</version>
        </dependency>
<!-- DEPENDENCIAS PARA MOSTRAR JSPS Y UTILIZAR ETIQUETAS JSTL -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
<!-- DEPENDENCIAS PARA INTEGRAR LA BASE DE DATOS SQL Y UTILIZAR JP -->
<!-- Nota: El proyecto no se ejecutará hasta que se haya creado un esquema y  
            la configuración adecuada en las propiedades de la aplicación está presente para 
            conectarse a una base de datos. -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
<!-- DEPENDENCIA PARA UTILIZAR ANOTACIONES DE VALIDACIÓN -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
 <!-- DEPENDENCIA PARA UTILIZAR BCRYPT  -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
```

