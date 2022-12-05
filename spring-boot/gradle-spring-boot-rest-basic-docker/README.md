# Descripcion del proyecto:
* Proyecto simple rest con sus anotaciones (get,post,put,delete) y datos dummy para ser consultados
* Ejcutado dentro de un contenedor docker.

# Contenido del proyecto:
* APP:
    * JDK java 8
    * Gradle 

# Instalacion

1. Construccion y ejecucion de contenedor Docker de proyecto SpringBoot Gradle
```
docker build -t img-gradle-spring-boot-rest-basic-docker .
docker run --name gradle-spring-boot-rest-basic-docker -p 8080:8080 img-gradle-spring-boot-rest-basic-docker 
```

2. url
```
http://localhost:8080/category/get-all
```
