# Descripcion del proyecto:
* Proyecto simple rest con sus anotaciones (get,post,put,delete) y datos dummy para ser consultados
* Ejcutado dentro de un contenedor docker.

# Contenido del proyecto:
* APP:
    * JDK java 8
    * maven

# Instalacion

1. Empaquetar proyecto
```
  mvn clean
  mvn package
```

2. Ejecucion desde contenedor Docker
```
    docker build -t img-spring-boot-rest-basic-docker .
    docker run --name spring-boot-rest-basic-docker -p 8080:8080 img-spring-boot-rest-basic-docker 
```

3. Postman 

