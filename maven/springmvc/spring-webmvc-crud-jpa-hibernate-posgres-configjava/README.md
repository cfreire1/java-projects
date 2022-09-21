# Descripcion del proyecto:
* Proyecto Spring web mvc CRUD JPA, conectado a base de datos poscressql
* Configuracion de WebMvcConfig en clases `Java`

# Contenido del proyecto:
Ejemplo de proyecto CRUD
* JDK 8 
* lombok
* Spring MVC v5 + bootstrap
* hibernate + JPA => Config for java 
  * web.xml => AppInitializer.java
  * mvc-dispatcher-servlet.xml => WebMvcConfig.java
  * applicationContext-dao.xml => AppContext.java
* Connect BD Posgresql => For java y properties

# Instalacion
Creacion de BD PostgreSql (Docker)
```
    docker run -p 5432:5432 --name bdpostgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=test1 -d postgres
```
* cargar script: [script.sql](sql/script.sql)
