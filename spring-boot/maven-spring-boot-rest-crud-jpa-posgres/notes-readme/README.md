### Notas:
* Lombok:
    * https://www.youtube.com/watch?v=j_hEdLPDczI&t=65s
    * https://stackoverflow.com/questions/34241718/lombok-builder-and-jpa-default-constructor
    * Generalmente no puede usar @Data en ninguna @Entity! Equals, hashCode y toString pueden entrar en conflicto con la especificación JPA o con "carga diferida
  ```
        // La mejor manera de implmentar anotaciones lombok en entity 
          @Getter
          @Setter
          @NoArgsConstructor
          @AllArgsConstructor
          @Builder
          @Entity
          @Table(name = "product")
          public class Product {...}
  ```
***

### Notas: Aplicacion
##### Patron data mapper (nota: es situacional la implementacion)
* Pag Oficial:
    * https://mapstruct.org/
* Platzi
    * https://platzi.com/clases/1996-java-spring/31511-orientar-nuestra-api-al-dominio-con-mapstruct/
* Tipos de mappers:
    * https://www.baeldung.com/java-performance-mapping-frameworks
* Problema con lombok:
    * https://github.com/mapstruct/mapstruct-examples/blob/master/mapstruct-lombok/pom.xml
* Agregar plugin Intelli-Idea
    * Map Strut:
* Tutoriales:
    * https://www.adictosaltrabajo.com/2020/04/14/como-mapear-objetos-en-java-con-mapstruct/
    * https://www.arquitecturajava.com/java-mapping-con-mapstruts-y-anotaciones/
* Es un patron que consiste , que 2 objetos puedan hacer la misma labor.

| Productos      |          | Product |
|:---------------|:--------:|--------:|
| nombre         |    ->    |    name |
| estado         | ->|   state |
| cantridadStock | ->|   stock |

```
@Mapper(componentModel = "spring")
public interface DemoProductConverter {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "idDemoCategory", target = "idDemoCategory"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "stock", target = "stock"),
            @Mapping(source = "createAt", target = "createAt"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "demoCategory", target = "demoCategoryDTO")
    })
    DemoProductDTO toObject(DemoProduct product);
    List<DemoProductDTO> toList(List<DemoProduct> product);
```

Se logra:
* No exponer la BD a datos de la api
* Desacoplar nuestra api a una BD puntual
* No tener campos Innesesarios en el API

***

### Notas: Base datos
##### BD:
    * https://www.kyocode.com/2019/07/tipos-de-relacion-sql-server/

##### JPA:
* Cuando la entidad de BD es compuesta (Muchas a muchos) se debe hacer implementacion de llave compuesta para los Pk en codigo
    * https://es.stackoverflow.com/questions/151149/jpa-clave-compuesta-para-find
    * ``JoinColumn``
  ```
        @JoinColumn(name = "id",
        insertable = false, // No vamos a insertar
        updatable = false) // No vamos a actualizar
    ```
##### Query Method
* SQL: `SELECT * FROM PRODUCT WHERE ID_CATEGORY = ? ORDER BY NAME ASC;`
* QUERY METHOD: **findBy**IdCategory**OrderBy**Name**Asc**(int idCategory)
* link : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

```
public interface ProductCrudRepository extends CrudRepository<Product,Integer> {
    //querymethod
    List<Product> findByIdCategoryOrderByNameAsc(int idCategory); //Nota: el IDE ayuda autocompletando IntelliIdea

    //querymethod Optional<?>
    Optional<List<Product>> findByStockLessThanAndState(int stock, int state); //Nota: el IDE ayuda autocompletando IntelliIdea

    // Escribirmos sonculta sql conectado a los objetos java
    //querymethod @Query (son mas flexible a la otra de codificar)
    //Si tenemos @Query podemos nombrar el metodo como queramos
    @Query(value = "select * from Product where id_category = ?",nativeQuery = true)
    List<Product> findByIdCategoryTwo(int id);
}
```

***
#### Sonnar link notes
##### Factory method injection should be used in "@Configuration" classes
* https://stackoverflow.com/questions/14779994/annotation-based-factory-methods
```
    //Factory method injection should be used in "@Configuration" classes
    Cuando se utiliza @Autowired, las dependencias deben resolverse cuando se crea una instancia de la clase, 
    lo que puede causar la inicialización temprana de los beans o hacer que el contexto busque en lugares donde
     no debería encontrar el bean. Para evitar este problema complicado y optimizar la forma en que se carga el 
     contexto, las dependencias deben solicitarse lo más tarde posible. Eso significa usar la inyección de 
     parámetros en lugar de la inyección de campo para las dependencias que solo se usan en un solo método @Bean.

      @Configuration
      @EnableWebSecurity
      public class WebSecurityConfig { 
      ...  
          //Incorrecto
          @Autowired
          private JwtFilterRequest jwtFilterRequest;
      
      ..
      
          //correcto
          @Bean
          public JwtFilterRequest getJwtFilterRequest(){
              return new JwtFilterRequest();
          }
      }
```
#### Errores 
* connection refused postgres docker
	* https://stackoverflow.com/questions/48422824/connection-refused-postgres-docker
```
//[1] En su archivo de propiedades, está indicando que postgres se ejecuta en el mismo contenedor que su aplicación Spring Boot (localhost), lo cual no es cierto ya que se ejecuta en un contenedor diferente.
//[2] También puede señalar la ip del puente docker, que generalmente es 172.17.0.1.
```

