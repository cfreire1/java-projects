# Ejemplo de api rest spring boot con BD H2

Aplicación que expone una API RESTful de creación de usuarios 

* java 8
* Banco de datos en memoriaH2.
* Proceso de build via Gradle.
* Framework Spring.
* Servidor Tomcat (embedded|Integrada)

# Instrucciones
* Ejecutar Run `JavaSpringBootRestUsersApplication.java` 
  * Tras la ejecucion se creara la base de datos en memoria h2
* Consultar a 'http://localhost:8080/users/create-user' en Http(Post) con json request en postman

# Notas
* Base datos en memoria `http://localhost:8080/h2-console`
  * Credenciales
    * user: sa
    * pass: 1234
    * JDBC URL: jdbc:h2:mem:testdb

* Postman
  * [gradle-spring-boot-rest-users.postman_collection.json](postman/gradle-spring-boot-rest-users.postman_collection.json)
* Script sql para revisar estructura
  * [database.sql](sql/database.sql)


