
# Descripcion del proyecto:
* Proyecto Spring web mvc CRUD JPA, conectado a base de datos poscressql
* Configuracion de WebMvcConfig en archivos de configuracion xml

# Contenido del proyecto:
Ejemplo de proyecto CRUD
* JDK 8 
* lombok
* Spring MVC v5 + bootstrap
* hibernate + JPA => Config for xml
  * web.xml
  * DispatcherServlet => mvc-dispatcher-servlet.xml
  * ContextLoaderListener => applicationContext-dao.xml
* Connect BD Posgresql => For xml 

# Instalacion
 Creacion de BD PostgreSql (Docker)
```
    docker run -p 5432:5432 --name bdpostgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=test1 -d postgres
```
* cargar script: [script.sql](sql/script.sql)